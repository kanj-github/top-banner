package com.kanj.apps.topbanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private BannerView banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTopButtonClicked(View v) {
        BannerView.show(this, new BannerView.Callbacks() {
            @Override
            public void onDisplayed(BannerView bannerView) {
                banner = bannerView;
            }

            @Override
            public void onClose() {
                showBannerClosedMessage();
                banner = null;
            }
        });
    }

    public void onBottomButtonClicked(View v) {
        Intent i = new Intent(this, OtherActivity.class);
        startActivity(i);
    }

    private void showBannerClosedMessage() {
        Toast.makeText(this, "Closed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (banner != null) {
            banner.close();
        } else {
            super.onBackPressed();
        }
    }
}
