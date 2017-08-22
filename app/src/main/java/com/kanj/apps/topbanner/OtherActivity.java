package com.kanj.apps.topbanner;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import com.kanj.apps.topbanner.core.AbstractTopBannerActivity;
import com.kanj.apps.topbanner.core.Banner;

/**
 * Created by Kanj Narayan on 22/08/17.
 */

public class OtherActivity extends AbstractTopBannerActivity implements
    OtherBannerView.OtherCallbacks {
    private TextView bottomText;
    private OtherBannerView mBanner;

    @Override
    public int getMainContainer() {
        return R.layout.activity_other;
    }

    @Override
    public int getBannerOverlayContainer() {
        return R.id.banner_overlay_container;
    }

    @Override
    public int getBannerContainer() {
        return R.id.banner_container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottomText = (TextView) findViewById(R.id.tv_bottom);
        bottomText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Kanj", "bottom text clicked");
                if (mBanner != null) {
                    hideBanner();
                } else {
                    displayBanner();
                }
            }
        });
    }

    private void displayBanner() {
        addViewToBanner(new OtherBannerView(this, this));
    }

    private void hideBanner() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
            InputMethodManager.HIDE_NOT_ALWAYS);
        removeBanner();
        mBanner = null;
    }

    @Override
    public void onBannerDisplayed(Banner banner) {
        if (banner instanceof OtherBannerView) {
            mBanner = (OtherBannerView) banner;
        }
    }

    @Override
    public void onBannerCloseClicked() {
        Log.v("Kanj", "onBannerCloseClicked");
        hideBanner();
    }

    @Override
    public void onClickOutsideBanner() {
        Log.v("Kanj", "onClickOutsideBanner");
        hideBanner();
    }

    @Override
    public void bannerTextClicked() {
        Toast.makeText(this, "Don't touch that!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (mBanner != null) {
            hideBanner();
        } else {
            super.onBackPressed();
        }
    }
}
