package com.kanj.apps.topbanner.core

interface BaseBannerCallbacks<in T : BaseBannerScene>{
    fun onBannerDisplayed(banner: T)
    fun onBannerCloseClicked()
    fun onClickOutsideBanner()
}