package com.tab.mint.device.sms.SMSUtils.KDM;

/**
 * Created by tab on 5/13/17.
 */
public class KDMRequiredExtensions {
    private String compositionPlaylistId;
    private String contentTitleText;
    private String contentKeysNotValidBefore;
    private String contentKeysNotValidAfter;

    public String getCompositionPlaylistId() {
        return compositionPlaylistId;
    }

    public void setCompositionPlaylistId(String compositionPlaylistId) {
        this.compositionPlaylistId = compositionPlaylistId;
    }

    public String getContentTitleText() {
        return contentTitleText;
    }

    public void setContentTitleText(String contentTitleText) {
        this.contentTitleText = contentTitleText;
    }

    public String getContentKeysNotValidBefore() {
        return contentKeysNotValidBefore;
    }

    public void setContentKeysNotValidBefore(String contentKeysNotValidBefore) {
        this.contentKeysNotValidBefore = contentKeysNotValidBefore;
    }

    public String getContentKeysNotValidAfter() {
        return contentKeysNotValidAfter;
    }

    public void setContentKeysNotValidAfter(String contentKeysNotValidAfter) {
        this.contentKeysNotValidAfter = contentKeysNotValidAfter;
    }
}
