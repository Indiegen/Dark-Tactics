package com.indiegen.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.RandomXS128;

/**
 * Created by Laptop on 18/12/2017.
 */


public class Torch extends Light {

    int col=0;
    int row=0;

    public Torch(Batch batch, int col, int row) {
        super(batch, col, row);
        this.batch=batch;
        this.col=col;
        this.row = row;
        rand = new RandomXS128();
        setX(64 * col - lightSize / 2 +32);
        setY(64 * row - lightSize / 2 +32);
        this.color = new Color(1f, .6f, .3f,1);
        setMaxSize(2);
        setMinSize(1);
        setTexture(AssetsManager.getLight());

    }

    @Override
    public void draw() {
        delta=delta+inc*incDir;
        alpha=alpha+inc*incDir;
        setHeight(size*delta);
        setWidth(size*delta);

        if(delta > getMaxSize())
        {
            incDir=-1;
            inc = (float)(rand.nextFloat()*.005+.001);
        }

        if(delta<getMinSize()){
            incDir=1;
            inc = (float)(rand.nextFloat()*.005+.001);

        }
        setColor(new Color(1f, .6f, .3f,delta/2.5f-.05f));
        batch.setColor(getColor());
        setX(64 * col - getWidth() / 2 +32);
        setY(64 * row - getHeight() / 2 +32);
        super.draw();
        batch.setColor(new Color(1, 1, 1,1));
    }
}
