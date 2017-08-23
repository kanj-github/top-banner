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

/**
 * Created by Kanj Narayan on 22/08/17.
 */

public abstract class FixedHeightBanner<T extends BaseBannerCallbacks> extends Banner<T> {
    public FixedHeightBanner(@NonNull Context context, T bannerCallbacks) {
        super(context, bannerCallbacks);
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

        setMeasuredDimension(parentWidth, desiredHeight);
    }


}
