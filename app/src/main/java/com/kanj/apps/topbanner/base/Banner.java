package com.kanj.apps.topbanner.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
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

    public Banner(Context context, T bannerCallbacks) {
        this(context, null, 0, bannerCallbacks);
    }

    public Banner(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0, null);
    }

    public Banner(Context context, @Nullable AttributeSet attrs, int defStyleAttr, T bannerCallbacks) {
        super(context, attrs, defStyleAttr);
        init(context, bannerCallbacks);
    }

    protected void init(Context context, T bannerCallbacks) {
        setPadding(
            convertDpToPx(getLeftPaddingInDp()),
            convertDpToPx(getTopPaddingInDp()),
            convertDpToPx(getRightPaddingInDp()),
            convertDpToPx(getBottomPaddingInDp())
        );
        setBackgroundColor(ContextCompat.getColor(context, getBannerBackgroundColor()));
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, getLayoutHeight());
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

        this.bannerCallbacks = bannerCallbacks;
        setupView();
    }

    protected abstract void setupView();

    protected int getLayoutHeight() {
        return LayoutParams.WRAP_CONTENT;
    }

    protected abstract int getTopPaddingInDp();
    protected abstract int getBottomPaddingInDp();
    protected abstract int getLeftPaddingInDp();
    protected abstract int getRightPaddingInDp();

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

    protected int convertDpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
