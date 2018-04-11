package com.kanj.apps.topbanner.base

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.kanj.apps.topbanner.core.BaseBannerCallbacks
import com.kanj.apps.topbanner.core.BaseBannerScene
import com.kanj.apps.topbanner.core.InvalidBannerOverlayException

abstract class AbstractTopBannerActivity : AppCompatActivity() {
    protected var bannerContainer: ViewGroup? = null
    protected var bannerOverlayContainer: FrameLayout? = null

    @LayoutRes
    abstract fun getMainContainer(): Int

    @IdRes
    abstract fun getBannerOverlayContainer(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getMainContainer())

        bannerContainer = findViewById(getBannerOverlayContainer())
        if (bannerContainer != null && bannerContainer is FrameLayout) {
            bannerOverlayContainer = bannerContainer as FrameLayout
        }
    }

    fun <S : BaseBannerScene, T : BaseBannerCallbacks<S>> addViewToBanner(banner: FixedHeightBanner<S, T>) {
        bannerOverlayContainer?.let {
            it.visibility = View.VISIBLE
            it.setOnClickListener { banner.bannerCallbacks.onClickOutsideBanner() }
            val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT)
            params.gravity = Gravity.TOP
            it.addView(banner, params)
        } ?: throw InvalidBannerOverlayException()
    }

    fun removeBanner() {
        bannerOverlayContainer?.let {
            it.removeAllViews()
            it.setOnClickListener(null)
            it.visibility = View.GONE
        }
    }
}