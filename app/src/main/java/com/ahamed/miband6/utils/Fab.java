package com.ahamed.miband6.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gordonwong.materialsheetfab.AnimatedFab;

public class Fab extends FloatingActionButton implements AnimatedFab {


    public Fab(@NonNull Context context) {
        super(context);
    }

    public Fab(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Fab(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void show() {
        show(0, 0);
    }

    @Override
    public void show(float translationX, float translationY) {
        setVisibility(View.VISIBLE);
    }

    @Override
    public void hide() {
        setVisibility(View.GONE);
    }
}
