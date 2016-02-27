package com.beastpotato.movieinformation.adapters;

import android.support.annotation.LayoutRes;

/**
 * Created by Oleksiy on 1/31/2016.
 */
public class ItemBinder {
    private int layoutItemId, boundVariableId;
    private Object boundObject;

    public ItemBinder(@LayoutRes int layoutItemId, int bindId, Object boundObject) {
        this.layoutItemId = layoutItemId;
        this.boundVariableId = bindId;
        this.boundObject = boundObject;
    }

    public int getLayoutItemId() {
        return layoutItemId;
    }

    public int getBoundVariableId() {
        return boundVariableId;
    }

    public Object getBoundObject() {
        return boundObject;
    }
}
