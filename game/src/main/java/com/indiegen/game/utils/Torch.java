package com.indiegen.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by Laptop on 15/12/2017.
 */

public class Torch extends Light {


   public Torch(Batch batch, float x, float y, Color color)
    {
        super(batch, x, y, color);
        setSize(64);
    }

    @Override
    public void draw() {
        super.draw();

    }

    @Override
    public void setX(float x) {
        super.setX(x);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
    }

    @Override
    public float getLightSize() {
        return super.getLightSize();
    }

    @Override
    public void setLightSize(float lightSize) {
        super.setLightSize(lightSize);
    }
}
