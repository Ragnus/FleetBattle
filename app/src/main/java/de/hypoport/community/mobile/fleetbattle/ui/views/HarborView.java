package de.hypoport.community.mobile.fleetbattle.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import de.hypoport.community.mobile.fleetbattle.R;

/**
 * The harbor for all new ships
 */
public class HarborView extends View {
    Paint paint = new Paint();

    public HarborView(Context context) {
        super(context);
    }

    public HarborView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HarborView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);

    }

}
