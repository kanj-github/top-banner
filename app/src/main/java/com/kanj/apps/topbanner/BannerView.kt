package com.kanj.apps.topbanner

import android.content.Context
import android.graphics.PixelFormat
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.view_banner.view.*

/**
 *  Banner using WindowManager
 *  Problems-
 *  1. Can't handle touch outside the banner
 */
class BannerView(val mContext: Context, val mCallbacks: Callbacks) : FrameLayout(mContext) {
    private val windowManager = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager

    init {
        View.inflate(mContext, R.layout.view_banner, this)
    }

    companion object {
        fun show(context: Context, callbacks: Callbacks) {
            BannerView(context, callbacks).display()
        }
    }

    fun display() {
        val layoutParams = WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_PANEL,
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                PixelFormat.TRANSLUCENT
        )
        layoutParams.gravity = Gravity.TOP

        windowManager.addView(this, layoutParams)

        mCallbacks.onDisplayed(this)

        // click listener using lambda
        banner_close.setOnClickListener({
            Log.v("Kanj", "Close Clicked")
            close()
        })

        // // click listener using object
        this.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                Log.v("Kanj", "banner clicked")
            }
        })
    }

    fun close() {
        windowManager.removeView(this)
        mCallbacks.onClose()
    }

    interface Callbacks {
        fun onDisplayed(bannerView: BannerView)
        fun onClose()
    }
}