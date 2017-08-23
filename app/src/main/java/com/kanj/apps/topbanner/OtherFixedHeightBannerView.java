package com.kanj.apps.topbanner;

import android.content.Context;
import android.view.View;
import com.kanj.apps.topbanner.base.FixedHeightBanner;
import com.kanj.apps.topbanner.core.BaseBannerCallbacks;

/**
 * Created by Kanj Narayan on 22/08/17.
 */

public class OtherFixedHeightBannerView extends FixedHeightBanner<OtherFixedHeightBannerView.OtherCallbacks> {
    private static final int HEIGHT_BANNER_DP = 200;
    private static final int PADDING_BANNER_DP = 15;

    public OtherFixedHeightBannerView(Context context, OtherCallbacks callbacks) {
        super(context, callbacks);
    }

    @Override
    protected void init(Context context) {
        super.init(context);

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

    @Override
    protected int getPaddingInDp() {
        return PADDING_BANNER_DP;
    }

    @Override
    protected int getHeightInDp() {
        return HEIGHT_BANNER_DP;
    }

    @Override
    protected int getBannerContentLayout() {
        return R.layout.view_other_banner;
    }

    @Override
    protected int getBannerBackgroundColor() {
        return R.color.colorPrimaryDark;
    }

    public interface OtherCallbacks extends BaseBannerCallbacks {
        void bannerTextClicked();
    }
}
