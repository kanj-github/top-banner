package com.kanj.apps.topbanner.base

import android.content.Context
import android.content.res.Resources
import android.support.annotation.ColorRes
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.kanj.apps.topbanner.core.BaseBannerCallbacks
import com.kanj.apps.topbanner.core.BaseBannerScene

abstract class Banner<S : BaseBannerScene, T : BaseBannerCallbacks<S>> : FrameLayout, BaseBannerScene {
    val bannerCallbacks: T
    protected val inflatedBanner: View

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, bannerCallbacks: T) : super(context, attrs, defStyleAttr) {
        this.bannerCallbacks = bannerCallbacks
        setPadding(
                convertDpToPx(getLeftPaddingInDp()),
                convertDpToPx(getTopPaddingInDp()),
                convertDpToPx(getRightPaddingInDp()),
                convertDpToPx(getBottomPaddingInDp())
        )
        setBackgroundColor(ContextCompat.getColor(context, getBannerBackgroundColor()))
        val params = LayoutParams(LayoutParams.MATCH_PARENT, getLayoutHeight())
        layoutParams = params

        val inflater = LayoutInflater.from(context)
        inflatedBanner = inflater.inflate(getBannerContentLayout(), this, true)
        inflatedBanner?.setOnClickListener {
            // This is needed to override the click listener of overlay
            Log.v("Kanj", "banner view clicked")
        }
        setupView()
    }

    constructor(context: Context, attrs: AttributeSet?, bannerCallbacks: T) : this(context, attrs, 0, bannerCallbacks)

    constructor(context: Context, bannerCallbacks: T) : this(context, null, 0, bannerCallbacks)

    open fun getLayoutHeight() = LayoutParams.WRAP_CONTENT

    abstract fun getTopPaddingInDp(): Int
    abstract fun getBottomPaddingInDp(): Int
    abstract fun getLeftPaddingInDp(): Int
    abstract fun getRightPaddingInDp(): Int
    @ColorRes
    abstract fun getBannerBackgroundColor(): Int
    /**
     *  Return a layout with a merge root element. It will be inflated in a FrameLayout
     */
    @LayoutRes
    abstract fun getBannerContentLayout(): Int

    abstract fun setupView()

    protected fun convertDpToPx(dp: Int) = (dp * Resources.getSystem().displayMetrics.density).toInt()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        bannerCallbacks.onBannerDisplayed(this as S)
    }
}