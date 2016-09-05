package com.shuntai.model.bean;

/**
 * Created by hadoop on 2016/9/5.
 */
public enum ButtonType {
    click,
    view,
    scancode_push,
    scancode_waitmsg,
    pic_sysphoto,
    pic_photo_or_album,
    pic_weixin,
    location_select,
    media_id,
    view_limited,
    popups,
    text,
    img,
    voice,
    video,
    news;

    private ButtonType() {
    }
}
