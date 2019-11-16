package com.tylersuehr.ovaltabs;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Copyright © 2017 Tyler Suehr
 *
 * Centralized access to shared properties for all the tabs and tab layout.
 *
 * @author Tyler Suehr
 * @version 1.0
 */
final class TabProperties {
    /* Used for back selection, unselected text color, and outline colors */
    int accentColor;
    /* Used for rounding of the view bounds */
    int cornerRadius;
    /* Used for the width of the outline */
    int outlineWidth;
    /* Used for tab selected text color */
    int tabSelectedTextColor;
    /* Used for tab typeface */
    Typeface tabTypeface;


    TabProperties(Context c, AttributeSet attrs) {
        final float density = c.getResources().getDisplayMetrics().density;
        this.tabTypeface = Typeface.DEFAULT;

        // Set XML attributes
        TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.OvalTabLayout);
        this.accentColor = a.getColor(R.styleable.OvalTabLayout_tab_accentColor, Color.BLACK);
        this.tabSelectedTextColor = a.getColor(R.styleable.OvalTabLayout_tab_tabSelectedTextColor, Color.WHITE);
        this.cornerRadius = a.getDimensionPixelSize(R.styleable.OvalTabLayout_tab_cornerRadius, (int)(16f * density));
        this.outlineWidth = a.getDimensionPixelSize(R.styleable.OvalTabLayout_tab_outlineWidth, (int)(1f * density));
        a.recycle();
    }

    /**
     * Accounts for the outline width when retrieving the corner
     * radius for the view.
     *
     * @return Corner radius with outline
     */
    int getCornerRadiusWithOutline() {
        return cornerRadius - outlineWidth;
    }
}