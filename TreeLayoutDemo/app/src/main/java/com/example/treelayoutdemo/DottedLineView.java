package com.example.treelayoutdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/*
 * Teery
 * */
public class DottedLineView extends View {
    static public int ORIENTATION_HORIZONTAL = 0;
    static public int ORIENTATION_VERTICAL = 1;
    private Paint mPaint;
    private int orientation;

    public DottedLineView(Context context) {
        this(context, null);
    }

    public DottedLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        int dashGap, dashLength, dashThickness;
        int color;

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DottedLineView, 0, 0);

        try {
            dashGap = a.getDimensionPixelSize(R.styleable.DottedLineView_dashGap, 5);
            dashLength = a.getDimensionPixelSize(R.styleable.DottedLineView_dashLength, 5);
            dashThickness = a.getDimensionPixelSize(R.styleable.DottedLineView_dashThickness, 3);
            color = a.getColor(R.styleable.DottedLineView_divider_line_color, 0xff000000);
            orientation = a.getInt(R.styleable.DottedLineView_divider_orientation, ORIENTATION_HORIZONTAL);
        } finally {
            a.recycle();
        }

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dashThickness);
        mPaint.setPathEffect(new DashPathEffect(new float[]{dashGap, dashLength,}, 0));
    }

    public void setBgColor(int color) {
        mPaint.setColor(color);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (orientation == ORIENTATION_HORIZONTAL) {
            float center = getHeight() * 0.5f;
            canvas.drawLine(0, center, getWidth(), center, mPaint);
        } else {
            float center = getWidth() * 0.5f;
            canvas.drawLine(center, 0, center, getHeight(), mPaint);
        }
    }
}
