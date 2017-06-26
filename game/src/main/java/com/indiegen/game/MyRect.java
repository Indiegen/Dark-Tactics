package com.indiegen.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class MyRect extends Rectangle {
    Color color = new Color(1, 1, 1, .2f);
    int margen = 60;
    boolean hasTarget = true;
    boolean isEnable = true;
    float distance = 9999;

    MyRect(int x, int y, float R, float G, float B, float alpha) {
        color.set(R, G, B, alpha);
        this.x = x * margen;
        this.y = y * margen;
        this.height = margen;
        this.width = margen;
    }

    MyRect(int x, int y, Color color) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.height = margen;
        this.width = margen;
    }

    MyRect(float x, float y, int width, int height) {
        //this.color.set(1,1,1,1);
        this.x = x;
        this.y = y;
        this.height = width;
        this.width = height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public boolean hasTarget() {
        return hasTarget;
    }

    public void drawRect(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(this.color);
        shapeRenderer.rect(this.x, this.y, this.width, this.height);
    }
}
