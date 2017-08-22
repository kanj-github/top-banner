package com.kanj.apps.topbanner.core;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.kanj.apps.topbanner.R;

/**
 * Created by Kanj Narayan on 22/08/17.
 */

public abstract class Banner<T extends BaseBannerCallbacks> extends FrameLayout {
    public T bannerCallbacks;
    protected View inflatedBanner;

    public Banner(Context context) {
        this(context, null, 0);
    }

    public Banner(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    protected void init(Context context) {
        int padding = convertDpToPx(getPaddingInDp());
        setPadding(padding, padding, padding, padding);
        setBackgroundColor(ContextCompat.getColor(context, getBannerBackgroundColor()));
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
            convertDpToPx(getHeightInDp()));
        setLayoutParams(params);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflatedBanner = inflater.inflate(getBannerContentLayout(), this, true);
        inflatedBanner.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // This is needed to override the click listener of overlay
                Log.v("Kanj", "banner view clicked");
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);

        int desiredHeight = convertDpToPx(getHeightInDp());

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                desiredHeight = parentHeight;
                break;
            case MeasureSpec.AT_MOST:
                if (desiredHeight > parentHeight) {
                    desiredHeight = parentHeight;
                }
                break;
        }

        Log.v("Kanj", "measuring Banner " + parentWidth + "x" + desiredHeight);
        setMeasuredDimension(parentWidth, desiredHeight);
    }

    protected abstract int getPaddingInDp();

    protected abstract int getHeightInDp();

    /**
     *  Return a layout with a merge root element. It will be inflated in a FrameLayout
     */
    @LayoutRes
    protected abstract int getBannerContentLayout();

    @ColorRes
    protected abstract int getBannerBackgroundColor();


    @TargetApi(21)
    public Banner(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
        int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        bannerCallbacks.onBannerDisplayed(this);
    }

    private int convertDpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
