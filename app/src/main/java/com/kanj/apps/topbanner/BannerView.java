package com.kanj.apps.topbanner;

import android.content.Context;
import android.graphics.PixelFormat;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by Kanj Narayan on 21/08/17.
 */

public class BannerView extends FrameLayout {
    private WindowManager windowManager;
    private View inflatedView;

    private ImageView closeButton;
    private Callbacks mCallbacks;

    private BannerView(@NonNull Context context, @NonNull Callbacks callbacks) {
        super (context);
        this.mCallbacks = callbacks;
        init(context);
    }

    private void init(Context context) {
        inflatedView = View.inflate(context, R.layout.view_banner, this);

        closeButton = (ImageView) inflatedView.findViewById(R.id.banner_close);

        windowManager = (WindowManager)
            context.getSystemService(Context.WINDOW_SERVICE);
    }

    public static void show (@NonNull Context context, @NonNull Callbacks callbacks) {
        BannerView view = new BannerView(context, callbacks);
        view.display();
    }

    public void display() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_PANEL,
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            PixelFormat.TRANSLUCENT
        );
        layoutParams.gravity = Gravity.TOP;

        windowManager.addView(this, layoutParams);

        mCallbacks.onDisplayed(this);

        closeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Kanj", "close clicked");
                close();
            }
        });

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Kanj", "banner clicked");
            }
        });
    }

    public void close() {
        windowManager.removeView(this);
        mCallbacks.onClose();
    }

    public interface Callbacks {
        void onDisplayed(BannerView bannerView);
        void onClose();
    }
}
