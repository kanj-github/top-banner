package com.kanj.apps.topbanner.core;

/**
 * Created by Kanj Narayan on 22/08/17.
 */

/**
 * Exception thrown when trying to add banner to a BannerProgressAbstractActivity subclass which
 * does not provide a FrameLayout container for banner overlay.
 */
public class InvalidBannerOverlayException extends Exception {
    private static final String MSG = "Banner overlay container is null or not a FrameContainer";
    public InvalidBannerOverlayException() {
        super(MSG);
    }
}
