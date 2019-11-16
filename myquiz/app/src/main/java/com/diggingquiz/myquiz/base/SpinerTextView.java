package com.diggingquiz.myquiz.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import com.diggingquiz.myquiz.R;


public class SpinerTextView extends AppCompatTextView implements View.OnClickListener {

    private String mPrompt;
    private CharSequence[] mEntries;
    private int mSelection;
    private OnItemSelectedListener mListener;

    public interface OnItemSelectedListener {
        void onItemSelected(int position);
    }

    public  SpinerTextView(Context context) {
        super(context);
        init(null);
    }

    public SpinerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SpinerTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SpinnerTextView);

            mPrompt = typedArray.getString(R.styleable.SpinnerTextView_android_prompt);
            mEntries = typedArray.getTextArray(R.styleable.SpinnerTextView_android_entries);

            typedArray.recycle();
        }

        mSelection = -1;
        mPrompt = (mPrompt == null)? "" : mPrompt;

        setText(mPrompt);
        setOnClickListener(this);
    }

    public String getSelectedItem() {
        if (mSelection < 0 || mSelection >= mEntries.length) {
            return null;
        } else {
            return mEntries[mSelection].toString();
        }
    }

    public int getSelectedItemPosition() {
        return mSelection;
    }

    public void setSelection(int selection) {
        mSelection = selection;

        if (selection < 0) {
            setText(mPrompt);
        } else if (selection < mEntries.length) {
            setText(mEntries[mSelection]);
        }
    }

    public void setListener(OnItemSelectedListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
        new AlertDialog.Builder(getContext())
                .setTitle(mPrompt)
                .setItems(mEntries, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSelection = which;
                        setText(mEntries[mSelection]);
                        if (mListener != null) {
                            mListener.onItemSelected(which);
                        }
                    }
                })
                .create().show();
    }
}
