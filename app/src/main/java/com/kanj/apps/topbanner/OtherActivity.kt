package com.kanj.apps.topbanner

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.kanj.apps.topbanner.base.AbstractTopBannerActivity
import com.kanj.apps.topbanner.core.InvalidBannerOverlayException
import com.kanj.apps.topbanner.core.OtherBannerScene
import com.kanj.apps.topbanner.core.OthersBannerCallbacks
import kotlinx.android.synthetic.main.activity_other.*

class OtherActivity : AbstractTopBannerActivity(), OthersBannerCallbacks {
    private var mBanner: OtherBannerScene? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tv_bottom.setOnClickListener({
            Log.v("Kanj", "bottom text clicked")
            if (mBanner != null) {
                hideBanner()
            } else {
                displayBanner()
            }
        })
    }

    private fun displayBanner() {
        try {
            addViewToBanner(OthersFixedHeightBanner(this, this))
        } catch (e: InvalidBannerOverlayException) {
            e.printStackTrace()
        }
    }

    private fun hideBanner() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS)
        removeBanner()
        mBanner = null
    }

    override fun getMainContainer() = R.layout.activity_other

    override fun getBannerOverlayContainer() = R.id.banner_overlay_container

    override fun onBannerCloseClicked() {
        Log.v("Kanj", "onBannerCloseClicked")
        hideBanner()
    }

    override fun onClickOutsideBanner() {
        Log.v("Kanj", "onClickOutsideBanner")
        hideBanner()
    }

    override fun bannerTextClicked() {
        Toast.makeText(this, "Don't touch that!", Toast.LENGTH_SHORT).show()
    }

    override fun onBannerDisplayed(banner: OtherBannerScene) {
        mBanner = banner
    }

    override fun onBackPressed() {
        if (mBanner != null) {
            hideBanner()
        } else {
            super.onBackPressed()
        }
    }
}