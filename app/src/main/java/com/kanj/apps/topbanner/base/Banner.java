package com.kanj.apps.topbanner.base;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.kanj.apps.topbanner.core.BaseBannerCallbacks;
import com.kanj.apps.topbanner.core.BaseBannerScene;

/**
 * Created by voldemort on 23/8/17.
 */

public abstract class Banner<T extends BaseBannerCallbacks> extends FrameLayout implements BaseBannerScene {
    public T bannerCallbacks;
    protected View inflatedBanner;

    public Banner(@NonNull Context context, T bannerCallbacks) {
        this(context, null, 0);
        this.bannerCallbacks = bannerCallbacks;
    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
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

    /**
     *  Return a layout with a merge root element. It will be inflated in a FrameLayout
     */
    @LayoutRes
    protected abstract int getBannerContentLayout();

    @ColorRes
    protected abstract int getBannerBackgroundColor();

    protected abstract int getPaddingInDp();

    protected abstract int getHeightInDp();

    protected int convertDpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        bannerCallbacks.onBannerDisplayed(this);
    }
}