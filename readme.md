### POC app to show various approaches to display a "banner" view overlapping the main display content.

* Banner should be closed when user touches outside it ("Outside" is customizable)
* Banner should be closed on pressing back button
* Views contained within the banner should work as expected

#### Classes
* **BannerView** Attaches itself using WindowManager
* **AbstractTopBannerActivity** Base for activities that want a fixed height banner
* **Banner** FrameLayout banner that is optimized to have 0 nesting of same size layouts
* **FixedHeightBanner** Subclass of Banner with fixed height instead of wrap_content

#### Sample code for
* Kotlin generics
* Making subclass of layout classes in kotlin
* Using `RelativeSizeSpan` (in `MainActivity`)
* `typeface` and `fontFamily` attribute of TextView in `activity_other.xml`
* Using `onMeasure` to get desired height