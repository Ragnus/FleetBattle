package de.hypoport.community.mobile.fleetbattle.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by bozan on 23.11.2014.
 */
public class MainMatrixView extends View {

    Paint paint = new Paint();

    public MainMatrixView(Context context) {
        super(context);
    }

    public MainMatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainMatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDraw(Canvas canvas) {
        drawBorder(canvas);
        drawRaster(canvas);
    }

    private void drawBorder(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
    }

    private void drawRaster(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(4);

        for(int i=1; i<10; i++) {
            canvas.drawLine(width / 10 * i, 0, width / 10 * i, height, paint);
            canvas.drawLine(0, height / 10 * i, width, height / 10 * i, paint);
        }
    }
}