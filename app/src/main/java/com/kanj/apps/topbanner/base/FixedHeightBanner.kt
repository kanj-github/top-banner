package com.kanj.apps.topbanner.base

import android.content.Context
import com.kanj.apps.topbanner.core.BaseBannerCallbacks
import com.kanj.apps.topbanner.core.BaseBannerScene

abstract class FixedHeightBanner<S : BaseBannerScene, T : BaseBannerCallbacks<S>> (mContext: Context, bannerCallbacks: T)
    : Banner<S,T>(mContext, bannerCallbacks) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        val parentHeight = MeasureSpec.getSize(heightMeasureSpec)
        var desiredHeight = convertDpToPx(getHeightInDp())
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        when(heightMode) {
            MeasureSpec.EXACTLY -> desiredHeight = parentHeight
            MeasureSpec.AT_MOST -> if (desiredHeight > parentHeight) desiredHeight = parentHeight
        }

        setMeasuredDimension(parentWidth, desiredHeight)
    }

    override fun getLayoutHeight() = convertDpToPx(getHeightInDp())

    abstract fun getHeightInDp(): Int
}