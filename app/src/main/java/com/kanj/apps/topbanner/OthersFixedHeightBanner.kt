package com.kanj.apps.topbanner

import android.content.Context
import com.kanj.apps.topbanner.base.FixedHeightBanner
import com.kanj.apps.topbanner.core.OtherBannerScene
import com.kanj.apps.topbanner.core.OthersBannerCallbacks
import kotlinx.android.synthetic.main.view_other_banner.view.*

const val HEIGHT_BANNER_DP = 200
const val PADDING_BANNER_DP = 15

class OthersFixedHeightBanner(context: Context, callbacks: OthersBannerCallbacks)
    : FixedHeightBanner<OtherBannerScene, OthersBannerCallbacks>(context, callbacks), OtherBannerScene {

    override fun getHeightInDp() = HEIGHT_BANNER_DP

    override fun setupView() {
        // lambda arg can be written outside/without ()
        banner_text.setOnClickListener { bannerCallbacks.bannerTextClicked() }
        banner_close.setOnClickListener { bannerCallbacks.onBannerCloseClicked() }
    }

    override fun getTopPaddingInDp() = PADDING_BANNER_DP

    override fun getBottomPaddingInDp() = PADDING_BANNER_DP

    override fun getLeftPaddingInDp() = PADDING_BANNER_DP

    override fun getRightPaddingInDp() = PADDING_BANNER_DP

    override fun getBannerContentLayout() = R.layout.view_other_banner

    override fun getBannerBackgroundColor() = R.color.colorPrimaryDark
}