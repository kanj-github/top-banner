package com.kanj.apps.topbanner.base;

import android.content.Context;
import android.support.annotation.NonNull;

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

    @Override
    protected int getLayoutHeight() {
        return convertDpToPx(getHeightInDp());
    }

    protected abstract int getHeightInDp();
}
