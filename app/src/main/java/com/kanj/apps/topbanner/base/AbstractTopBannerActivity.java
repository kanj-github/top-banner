package com.kanj.apps.topbanner.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.kanj.apps.topbanner.base.FixedHeightBanner;
import com.kanj.apps.topbanner.core.InvalidBannerOverlayException;

/**
 * Created by Kanj Narayan on 22/08/17.
 */

public abstract class AbstractTopBannerActivity extends AppCompatActivity {
    protected ViewGroup bannerContainer;
    protected FrameLayout bannerOverlayContainer;

    @LayoutRes
    public abstract int getMainContainer();

    @IdRes
    public abstract int getBannerOverlayContainer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getMainContainer());

        bannerContainer = (ViewGroup) findViewById(getBannerOverlayContainer());
        if (bannerContainer != null && bannerContainer instanceof FrameLayout) {
            bannerOverlayContainer = (FrameLayout) bannerContainer;
        }
    }

    public void addViewToBanner(final FixedHeightBanner banner) throws InvalidBannerOverlayException {
        if (bannerOverlayContainer != null) {
            bannerOverlayContainer.setVisibility(View.VISIBLE);
            bannerOverlayContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    banner.bannerCallbacks.onClickOutsideBanner();
                }
            });

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.TOP;
            bannerOverlayContainer.addView(banner, params);
        } else {
            throw new InvalidBannerOverlayException();
        }
    }

    public void removeBanner() {
        if (bannerOverlayContainer != null) {
            bannerOverlayContainer.removeAllViews();
            bannerOverlayContainer.setOnClickListener(null);
            bannerOverlayContainer.setVisibility(View.GONE);
        }
    }
}
