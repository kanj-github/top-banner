package com.kanj.apps.topbanner

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var banner: BannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val formattableText = getString(R.string.click_desc)
        val largerText = "WindowManager"
        val fullText = String.format(formattableText, largerText)

        val spannableString = SpannableString(fullText)
        val index = fullText.indexOf(largerText)
        spannableString.setSpan(RelativeSizeSpan(1.3f), index,
                index + largerText.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        tv_relative_size_span.text = spannableString
    }

    fun onTopButtonClicked(v: View) {
        BannerView.show(this, object : BannerView.Callbacks{
            override fun onDisplayed(bannerView: BannerView) {
                banner = bannerView
            }

            override fun onClose() {
                showBannerClosedMessage()
                banner = null
            }
        })
    }

    private fun showBannerClosedMessage() {
        Toast.makeText(this, "Closed", Toast.LENGTH_SHORT).show()
    }

    fun onBottomButtonClicked(v: View) {
        startActivity(Intent(this, OtherActivity::class.java))
    }

    override fun onBackPressed() {
        banner?.close() ?: super.onBackPressed()
    }
}