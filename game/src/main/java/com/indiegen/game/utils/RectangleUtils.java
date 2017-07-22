package com.indiegen.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

public class RectangleUtils extends Rectangle {
    private Color color = new Color(1, 1, 1, .2f);
    private int margen = 60;
    private boolean hasTarget = true;
    private boolean isEnable = true;
    private float distance = 9999;

    public RectangleUtils(float x, float y, int width, int height) {
        //this.color.set(1,1,1,1);
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getMargen() {
        return margen;
    }

    public void setMargen(int margen) {
        this.margen = margen;
    }

    public boolean isHasTarget() {
        return hasTarget;
    }

    public void setHasTarget(boolean hasTarget) {
        this.hasTarget = hasTarget;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
