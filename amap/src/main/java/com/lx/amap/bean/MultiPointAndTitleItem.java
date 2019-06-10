package com.lx.amap.bean;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MultiPointItem;

/**
 * Created by LucianBen on 2019/6/5
 * Describe:
 */
public class MultiPointAndTitleItem extends MultiPointItem {
    private String title;
    private String snippet;

    public MultiPointAndTitleItem(LatLng latLng, String title, String snippet) {
        super(latLng);
        this.title = title;
        this.snippet = snippet;
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSnippet() {
        return snippet;
    }
}
