package com.kanj.apps.topbanner.core

/**
 * Exception thrown when trying to add banner to a BannerProgressAbstractActivity subclass which
 * does not provide a FrameLayout container for banner overlay.
 */
class InvalidBannerOverlayException : Exception("Banner overlay container is null or not a FrameContainer") {
}