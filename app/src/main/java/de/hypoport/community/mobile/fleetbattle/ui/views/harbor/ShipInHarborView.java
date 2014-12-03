package de.hypoport.community.mobile.fleetbattle.ui.views.harbor;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.hypoport.community.mobile.fleetbattle.R;
import de.hypoport.community.mobile.fleetbattle.engine.rules.ShipPattern;
import de.hypoport.community.mobile.fleetbattle.ui.utilities.ShipResourceResolver;

import static de.hypoport.community.mobile.fleetbattle.ui.utilities.ShipResourceResolver.getResource;

/**
 * Created by Bozan on 27.11.2014.
 */
public class ShipInHarborView extends LinearLayout implements View.OnTouchListener {
    private static final String TAG = ShipInHarborView.class.getSimpleName();
    private ShipPattern shipPattern;
    private View layoutView;
    private int numberOfShips;
    private TextView tvShipCount;


    public ShipInHarborView(Context context) {
        super(context);
        init();
    }

    public ShipInHarborView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShipInHarborView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutView = inflater.inflate(R.layout.ship_in_harbor_view, this, true);
    }

    public void setShipPattern(ShipPattern shipPattern) {
        this.shipPattern = shipPattern;
        numberOfShips = shipPattern.numberOfShips;

        tvShipCount = (TextView) layoutView.findViewById(R.id.tvShipCount);
        updateNumberOfShips();

        initShipView();
    }

    private ImageView initShipView() {
        ImageView view = (ImageView) layoutView.findViewById(R.id.shipView);
        view.setImageResource(getResource(shipPattern.type));
//        view.setAdjustViewBounds(true); // set the ImageView bounds to match the Drawable's dimensions
//        view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        view.setTag(shipPattern.type);
        view.setScaleType(ImageView.ScaleType.FIT_START);
        view.setVisibility(VISIBLE);
        view.setOnTouchListener(this);
        return view;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (numberOfShips <= 0)
            return false;

        ClipData.Item item = new ClipData.Item(shipPattern.type.toString());
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData dragData = new ClipData(shipPattern.type.toString(), mimeTypes, item);

        View.DragShadowBuilder dragShadow = new DragShadowBuilder(v);

        // Starts the drag
        boolean dropSuccess = v.startDrag(dragData,  // the data to be dragged
                dragShadow,  // the drag shadow builder
                this,
                0          // flags (not currently used, set to 0)
        );

        Log.d(TAG, "D&D Result: " + dropSuccess);



        return dropSuccess;
    }

    public void decrementNumberOfShips() {
        numberOfShips--;
        updateNumberOfShips();
        if (numberOfShips==0){
            View ship = findViewById(R.id.shipView);
            ship.setVisibility(GONE);
        }
    }

    private void updateNumberOfShips() {
        tvShipCount.setText("[" + this.numberOfShips + "]");
    }

}
