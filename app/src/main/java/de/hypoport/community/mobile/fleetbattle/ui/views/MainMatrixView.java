package de.hypoport.community.mobile.fleetbattle.ui.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import de.hypoport.community.mobile.fleetbattle.R;
import de.hypoport.community.mobile.fleetbattle.engine.GameEngine;
import de.hypoport.community.mobile.fleetbattle.engine.Orientation;
import de.hypoport.community.mobile.fleetbattle.engine.Player;
import de.hypoport.community.mobile.fleetbattle.engine.Segment;
import de.hypoport.community.mobile.fleetbattle.engine.Ship;
import de.hypoport.community.mobile.fleetbattle.engine.rules.ShipType;
import de.hypoport.community.mobile.fleetbattle.ui.views.harbor.ShipInHarborView;

import static com.google.common.base.Optional.of;
import static de.hypoport.community.mobile.fleetbattle.ui.utilities.ShipResourceResolver.getResource;
import static java.util.Arrays.asList;

/**
 * Created by bozan on 23.11.2014.
 */

// TODO Placement View Von MatrixView ableiten
public class MainMatrixView extends View implements View.OnDragListener, View.OnTouchListener {
    private static final String TAG = MainMatrixView.class.getSimpleName();
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;

    // TODO Aus der Gaming Engine
    public static final int FILD_SIZE = 10;

    private GameEngine gameEngine = GameEngine.getInstance();
    private List<Ship> ships = gameEngine.getShipList(Player.LOCAL_PLAYER);

// MPi-Todo    private List<Ship> shipsold =         new ArrayList<>(asList( createDropShip(Orientation.VERTICAL, ShipType.DESTROYER, new Field(5, 5)),
//            createDropShip(Orientation.HORIZONTAL, ShipType.BATTLESHIP, new Field(3, 8))
//
//    ));

    Paint paint = new Paint();

    public MainMatrixView(Context context) {
        super(context);
        init();
    }

    public MainMatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MainMatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setAntiAlias(true);
        setOnDragListener(this);
        setOnTouchListener(this);
    }

    private Field createField(float x, float y) {
        float scaleX = x * WIDTH / getWidth();
        float scaleY = y * HEIGHT / getHeight();

        return new Field(Math.min(FILD_SIZE - 1, (int) (scaleX / MainMatrixView.FILD_SIZE / 10)),
                         Math.min(FILD_SIZE - 1, (int) (scaleY / MainMatrixView.FILD_SIZE / 10)));
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                Log.d(TAG, "ACTION_DRAG_STARTED");
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                Log.d(TAG, "ACTION_DRAG_ENTERED X:" + event.getX() + " Y:" + event.getY());
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                Log.d(TAG, "ACTION_DRAG_EXITED");
                break;
            case DragEvent.ACTION_DRAG_LOCATION:
                // TODO Check Drop-Area
                Log.d(TAG, "ACTION_DRAG_LOCATION X:" + event.getX() + " Y:" + event.getY());
                Log.d(TAG, "FIELD :" + createField(event.getX(), event.getY()));
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                Log.d(TAG, "ACTION_DRAG_ENDED");
                break;
            case DragEvent.ACTION_DROP:
                ShipType ship = getDroppedShipType(event);
                ships.add(gameEngine.createDropShip(Orientation.VERTICAL, ship, createField(event.getX(), event.getY())));
                Log.d(TAG, "ACTION_DROP ClipData: " + event.getClipData() + " Ship: " + ship);


                ShipInHarborView shipInHarborView = (ShipInHarborView) event.getLocalState();
                shipInHarborView.decrementNumberOfShips();

                invalidate();
                break;
        }

        return true;
    }

    private ShipType getDroppedShipType(DragEvent event) {
        return ShipType.valueOf((String) event.getClipData().getItemAt(0).getText());
    }

    @Override
    public void onDraw(Canvas canvas) {
        Rect src = new Rect(0, 0, WIDTH, HEIGHT);
        Rect dst = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
        Paint p = new Paint();
        p.setAntiAlias(true);
        canvas.drawBitmap(createContent(), src, dst, p);
    }

    private Bitmap createContent() {
        Bitmap map = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(map);

        drawBorder(canvas);
        drawRaster(canvas);
        drawShips(canvas);

        return map;
    }

    private void drawBorder(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, WIDTH, HEIGHT, paint);
    }

    // TODO Das kann noch schöner werden
    private void drawRaster(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);

        for (int i = 1; i < FILD_SIZE; i++) {
            canvas.drawLine(WIDTH / 10 * i, 0, WIDTH / 10 * i, HEIGHT, paint);
            canvas.drawLine(0, HEIGHT / 10 * i, WIDTH, HEIGHT / 10 * i, paint);
        }
    }

    private void drawShips(Canvas canvas) {
        for (Ship ship : ships) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), getResource(ship.getType()));

            // Rotate Image
            if (ship.getOrientation() == Orientation.HORIZONTAL) {
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
            }

            // Calculate Target Rect
            Segment first = ship.getSegments().get(0);
            Segment last = ship.getSegments().get(ship.getSize() - 1);
            Rect dst = new Rect(
                    first.getX() * 100,
                    first.getY() * 100,
                    (last.getX() + 1) * 100,
                    (last.getY() + 1) * 100);

            // Draw the bitmap
            canvas.drawBitmap(bmp,
                    new Rect(0, 0, bmp.getWidth(), bmp.getHeight()),
                    dst, paint);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d(TAG, "ON_TOUCH event FIELD :" + createField(event.getX(), event.getY()));
        }
        return true;
    }


    // TODO Evtl. in Gameengine (Placement Engine)
    // MPi-Todo
//    private Ship createDropShip(Orientation orientation, ShipType type, Field center) {
//        Ship ship = new Ship(type, of(orientation));
//        ship.getSegments().clear();
//        for (int i = 0; i < type.size; i++) {
//            if (orientation == Orientation.HORIZONTAL) {
//                ship.getSegments().add(i, new Segment(center.x - type.size / 2 + i, center.y));
//            } else {
//                ship.getSegments().add(i, new Segment(center.x, center.y - type.size / 2 + i));
//            }
//        }
//        return ship;
//    }
}