### POC app to show various approaches to display a "banner" view overlapping the main display content.

* Banner should be closed when user touches outside it
* Banner should be closed on pressing back button
* Views contained within the banner should work as expected

#### Classes
* **BannerView** Attaches itself using WindowManager
* **AbstractTopBannerActivity**
* **Banner**
* **FixedHeightBanner**

#### Sample code for
* Making subclass of layout classes in kotlin
* Using `RelativeSizeSpan` (in `MainActivity`)