package com.tylersuehr.ovaltabs;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Copyright © 2017 Tyler Suehr
 *
 * This represents a single oval-shaped tab. This can be used like a normal view,
 * but its intended functionality is to be instantiated by {@link OvalTabLayout}
 * only.
 *
 * This only supports 'wrap_content' for measuring itself because it should always
 * only be as big as needed to show its content on the screen.
 *
 * This works by drawing a rounded rectangle on the Canvas with Canvas APIs when
 * it is set as selected using {@link #setSelected(boolean)}.
 *
 * @author Tyler Suehr
 * @version 1.0
 */
@SuppressLint("ViewConstructor")
public class OvalTabView extends View {
    private final int sixteenDp;

    /* Stores a reference to all the shared tab properties */
    private TabProperties properties;

    /* Used to paint the rounded rect for selected appearance */
    private final Paint selectedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final RectF selectionBounds = new RectF();

    /* Used to the paint the tab's text */
    private final Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private String text = "";


    public OvalTabView(Context c, TabProperties properties) {
        super(c);
        setBorderlessBackground();
        setClickable(true);

        // Setup immutable properties
        final DisplayMetrics dm = getResources().getDisplayMetrics();
        this.sixteenDp = (int)(16f * dm.density);
        this.properties = properties;

        // Setup the text selectedPaint
        this.textPaint.setColor(properties.accentColor);
        this.textPaint.setTextSize(16f * dm.scaledDensity); // Won't change
        this.textPaint.setTextAlign(Paint.Align.CENTER); // Won't change
        this.textPaint.setTypeface(properties.tabTypeface);

        // Setup selection background selectedPaint
        this.selectedPaint.setColor(properties.accentColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = measureNeededWidth();
        int heightSize = measureNeededHeight();

        // Set the bounds in a rect to draw the rounded background
        this.selectionBounds.set(0, 0, getMeasuredWidth(), getMeasuredHeight());

        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Draw the selected tab, if selected
        if (isSelected()) {
            canvas.drawRoundRect(selectionBounds,
                    properties.cornerRadius,
                    properties.cornerRadius,
                    selectedPaint);
        }

        // Draw the text in the exact center
        canvas.drawText(text,
                (getMeasuredWidth() >> 1),
                (getMeasuredHeight() >> 1) - ((int)(textPaint.descent() + textPaint.ascent()) >> 1),
                textPaint);
    }

    @Override
    public void setSelected(boolean selected) {
        // Update the text paint color
        this.textPaint.setColor(selected
                ? properties.tabSelectedTextColor
                : properties.accentColor);
        super.setSelected(selected);
    }

    /**
     * Sets the tab's text.
     * @param text Tab text
     */
    public void setText(String text) {
        this.text = text;
        invalidate();
    }

    /**
     * Gets this tab's text.
     * @return Tab text
     */
    public String getText() {
        return text;
    }

    /**
     * Measures the minimal needed width for this tab.
     * @return Needed width
     */
    private int measureNeededWidth() {
        return (sixteenDp << 1) + (int)textPaint.measureText(text);
    }

    /**
     * Measures the minimal needed height for this tab.
     * @return Needed height
     */
    private int measureNeededHeight() {
        return sixteenDp - (int)(textPaint.descent() + textPaint.ascent());
    }

    /**
     * Retrieves the default theme, selectableItemBackgroundBorderless, attribute and sets it
     * as the foreground drawable.
     */
    private void setBorderlessBackground() {
        TypedArray a = getContext().obtainStyledAttributes(new int[] { R.attr.selectableItemBackgroundBorderless });
        setBackground(a.getDrawable(0));
        a.recycle();
    }
}