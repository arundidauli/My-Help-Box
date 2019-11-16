package com.tylersuehr.ovaltabs;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Copyright © 2017 Tyler Suehr
 *
 * This represents the TabLayout-like ViewGroup that will display the oval-shaped
 * tabs. This can also be setup with a ViewPager using {@link #setupWithViewPager(ViewPager)}.
 *
 * @author Tyler Suehr
 * @version 1.0
 */
public class OvalTabLayout extends ViewGroup {
    /* Stores the mutable properties used by this and its children */
    private TabProperties properties;
    /* Needed to draw rounded rect outline */
    private final Paint outlinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final RectF outlineBounds = new RectF();

    /* Stores the selected tab position */
    private int selectedPosition = -1;
    /* Stores a reference to the selected tab view itself */
    private OvalTabView selectedTab;

    /* Stores an observer for tab selection events */
    private OnTabSelectedListener tabListener;


    public OvalTabLayout(Context context) {
        this(context, null);
    }

    public OvalTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OvalTabLayout(Context c, AttributeSet attrs, int defStyleAttr) {
        super(c, attrs, defStyleAttr);

        // Setup the default properties for the tabs
        this.properties = new TabProperties(c, attrs);

        // Setup the outline paint
        this.outlinePaint.setColor(properties.accentColor);
        this.outlinePaint.setStyle(Paint.Style.STROKE); // Won't change
        this.outlinePaint.setStrokeWidth(properties.outlineWidth);

        // Show example tabs in the editor
        if (isInEditMode()) {
            addTab("Tab 1");
            addTab("Tab 2");
            addTab("Tab 3");
            setSelection(0);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println("WeirdTabLayout measured!");

        int totalChildWidth = 0;
        int firstChildHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);

            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            totalChildWidth += child.getMeasuredWidth();

            if (i == 0) {
                firstChildHeight = child.getMeasuredHeight();
            }
        }

        // Store the bounds in this rect (account for stroke width)
        final float halfOutline = (float)properties.outlineWidth / 2;
        this.outlineBounds.set(
                halfOutline, halfOutline,
                totalChildWidth - halfOutline,
                firstChildHeight - halfOutline
        );

        setMeasuredDimension(totalChildWidth, firstChildHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        System.out.println("WeirdTabLayout laid out!");

        int left = 0;
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);

            child.layout(left, 0,
                    left + child.getMeasuredWidth(),
                    child.getMeasuredHeight());

            left += child.getMeasuredWidth();
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        // Draw the rounded rect outline here
        final int rxy = properties.getCornerRadiusWithOutline();
        canvas.drawRoundRect(outlineBounds, rxy, rxy, outlinePaint);
    }

    /**
     * Sets the selected tab using the given position of the tab.
     * @param position Tab position to select
     */
    public void setSelection(int position) {
        // Validate the selected position
        final View selected = getChildAt(position);
        if (selected == null) {
            throw new IllegalArgumentException("Invalid position!");
        } else if (!(selected instanceof OvalTabView)) {
            throw new IllegalStateException("All children must be a WeirdTabView!");
        }

        // Attempt to deselect a previous selection, if possible
        if (selectedTab != null) {
            this.selectedTab.setSelected(false);
        }

        // Store the selection
        this.selectedPosition = position;
        this.selectedTab = (OvalTabView)selected;
        this.selectedTab.setSelected(true);

        // Notify any ViewPager that may exist
        if (getTag() != null) {
            ViewPager pager = (ViewPager)getTag();
            pager.setCurrentItem(selectedPosition);
        }

        // Trigger the callback, if available
        if (tabListener != null) {
            this.tabListener.onTabSelected(selectedPosition, selectedTab);
        }
    }

    @Nullable
    public OvalTabView getSelectedTab() {
        return selectedTab;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setAccentColor(@ColorInt int color) {
        if (properties.accentColor != color) {
            this.properties.accentColor = color;
            invalidate();
        }
    }

    public void setAccentColorResource(@ColorRes int res) {
        setAccentColor(ContextCompat.getColor(getContext(), res));
    }

    public int getAccentColor() {
        return properties.accentColor;
    }

    public void setSelectedTabTextColor(@ColorInt int color) {
        if (properties.tabSelectedTextColor != color) {
            this.properties.tabSelectedTextColor = color;
            invalidate();
        }
    }

    public void setSelectedTabTextColorResource(@ColorRes int res) {
        setSelectedTabTextColor(ContextCompat.getColor(getContext(), res));
    }

    public int getSelectedTabTextColor() {
        return properties.tabSelectedTextColor;
    }

    public void setTabTypeface(Typeface typeface) {
        this.properties.tabTypeface = typeface;
        invalidate();
    }

    public Typeface getTabTypeface() {
        return properties.tabTypeface;
    }

    public void setCornerRadius(int cornerRadius) {
        this.properties.cornerRadius = cornerRadius;
        invalidate();
    }

    public int getCornerRadius() {
        return properties.cornerRadius;
    }

    public void setOutlineWidth(@Px int width) {
        this.properties.outlineWidth = width;
        invalidate();
    }

    public int getOutlineWidth() {
        return properties.outlineWidth;
    }

    public void setOnTabSelectedListener(OnTabSelectedListener listener) {
        this.tabListener = listener;
    }

    public OnTabSelectedListener getOnTabSelectedListener() {
        return tabListener;
    }

    /**
     * Adds a new tab to this ViewGroup using the given title.
     * @param tabTitle Title of the tab to create
     */
    public void addTab(String tabTitle) {
        final OvalTabView tabView = new OvalTabView(getContext(), properties);
        tabView.setOnClickListener(tabClickHandler);
        tabView.setText(tabTitle);
        addView(tabView);
    }

    /**
     * Removes all the tabs in this ViewGroup.
     */
    public void clearTabs() {
        // Clear the current selection
        this.selectedPosition = -1;
        this.selectedTab = null;

        // Clear any referenced ViewPager and its listeners
        if (getTag() != null) {
            ((ViewPager)getTag()).clearOnPageChangeListeners();
            setTag(null);
        }

        // Clear the views from the screen
        removeAllViews();
    }

    /**
     * Sets this ViewGroup up to work in harmony with the Android ViewPager.
     *
     * This will create tabs based on the ViewPager adapter's content and will
     * also handle setting listeners to manage selecting tabs appropriately when
     * the ViewPager is interacted with by the user.
     *
     * @param pager {@link ViewPager}
     */
    public void setupWithViewPager(final ViewPager pager) {
        // Ensure that the given ViewPager isn't null
        final PagerAdapter adapter = pager.getAdapter();
        if (adapter == null) { return; }

        // We need to add a page change listener to the ViewPager
        // so that our tabs can change when the page changes. We
        // will store a reference to the ViewPager using the tag
        // property of this View
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                setSelectionLight(position);
            }

            @Override
            public void onPageScrolled(int position,
                                       float positionOffset,
                                       int positionOffsetPixels) {}

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        setTag(pager);

        // Add tabs for each of data in the pager's adapter
        CharSequence title;
        for (int i = 0; i < adapter.getCount(); i++) {
            title = adapter.getPageTitle(i);
            if (title == null) {
                throw new NullPointerException("Title cannot be null!");
            }
            addTab(title.toString());
        }

        // Set the selection to 0 by default, if possible
        if (adapter.getCount() > 0) {
            setSelection(0);
        }
    }

    /**
     * Sets the selected tab without updating any possible ViewPager.
     * This is to prevent an infinite loop of observers.
     *
     * @param position Position to select
     */
    private void setSelectionLight(int position) {
        // Validate the selected position
        final View selected = getChildAt(position);
        if (selected == null) {
            throw new IllegalArgumentException("Invalid position!");
        } else if (!(selected instanceof OvalTabView)) {
            throw new IllegalStateException("All children must be a WeirdTabView!");
        }

        // Attempt to deselect a previous selection, if possible
        if (selectedTab != null) {
            this.selectedTab.setSelected(false);
        }

        // Store the selection
        this.selectedPosition = position;
        this.selectedTab = (OvalTabView)selected;
        this.selectedTab.setSelected(true);

        // Trigger the callback, if available
        if (tabListener != null) {
            this.tabListener.onTabSelected(selectedPosition, selectedTab);
        }
    }

    /**
     * Callbacks for tab selection events.
     */
    public interface OnTabSelectedListener {
        void onTabSelected(int position, OvalTabView tabView);
    }

    /**
     * Handles selecting the tab view that triggered this listener.
     */
    private final OnClickListener tabClickHandler = new OnClickListener() {
        @Override
        public void onClick(View v) {
            final int position = indexOfChild(v);
            if (position > -1) {
                setSelection(position);
            }
        }
    };
}