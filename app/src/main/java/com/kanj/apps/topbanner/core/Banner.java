package com.kanj.apps.topbanner.core;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by Kanj Narayan on 22/08/17.
 */

public abstract class Banner<T extends BaseBannerCallbacks> extends FrameLayout {
    public T bannerCallbacks;

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

    @TargetApi(21)
    public Banner(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
        int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    protected abstract void init(Context context);

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        bannerCallbacks.onBannerDisplayed(this);
    }
}
