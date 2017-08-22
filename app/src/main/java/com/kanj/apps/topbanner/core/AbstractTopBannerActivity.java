package com.kanj.apps.topbanner.core;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Kanj Narayan on 22/08/17.
 */

public abstract class AbstractTopBannerActivity extends AppCompatActivity {
    protected View bannerOverlayView;
    protected ViewGroup bannerContainer;

    @LayoutRes
    public abstract int getMainContainer();

    @IdRes
    public abstract int getBannerOverlayContainer();

    @IdRes
    public abstract int getBannerContainer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getMainContainer());

        bannerOverlayView = findViewById(getBannerOverlayContainer());
        View bannerContainerView = findViewById(getBannerContainer());
        if (bannerContainerView != null && bannerContainerView instanceof ViewGroup) {
            bannerContainer = (ViewGroup) bannerContainerView;
        }
    }

    public void addViewToBanner(final Banner banner) {
        if (bannerOverlayView != null) {
            bannerOverlayView.setVisibility(View.VISIBLE);
            bannerOverlayView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    banner.bannerCallbacks.onClickOutsideBanner();
                }
            });
        }

        if (bannerContainer != null) {
            bannerContainer.addView(banner);
        }
    }

    public void removeBanner() {
        if (bannerContainer != null) {
            bannerContainer.removeAllViews();
        }

        if (bannerOverlayView != null) {
            bannerOverlayView.setVisibility(View.GONE);
            bannerOverlayView.setOnClickListener(null);
        }
    }
}
