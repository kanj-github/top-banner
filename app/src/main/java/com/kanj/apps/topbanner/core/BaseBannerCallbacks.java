package com.kanj.apps.topbanner.core;

/**
 * Created by Kanj Narayan on 22/08/17.
 */

public interface BaseBannerCallbacks<T extends Banner> {
    void onBannerDisplayed(T banner);
    void onBannerCloseClicked();
    void onClickOutsideBanner();
}
