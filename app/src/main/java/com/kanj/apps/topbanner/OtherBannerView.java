package com.kanj.apps.topbanner;

import android.content.Context;
import android.view.View;
import com.kanj.apps.topbanner.core.Banner;
import com.kanj.apps.topbanner.core.BaseBannerCallbacks;

/**
 * Created by Kanj Narayan on 22/08/17.
 */

public class OtherBannerView extends Banner<OtherBannerView.OtherCallbacks> {

    public OtherBannerView(Context context, OtherCallbacks callbacks) {
        super(context);
        bannerCallbacks = callbacks;
    }

    @Override
    protected void init(Context context) {
        View.inflate(context, R.layout.view_banner, this);
        findViewById(R.id.banner_text).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                bannerCallbacks.bannerTextClicked();
            }
        });
        findViewById(R.id.banner_close).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                bannerCallbacks.onBannerCloseClicked();
            }
        });
    }

    public interface OtherCallbacks extends BaseBannerCallbacks {
        void bannerTextClicked();
    }
}
