package com.indiegen.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.RandomXS128;
import com.indiegen.game.Actors.CustomActor;

/**
 * Created by Laptop on 15/12/2017.
 */

public class Aura extends Light {


   public Aura(Batch batch, float x, float y, Color color)
    {
        super(batch, x, y, color);
        rand = new RandomXS128();
        this.batch=batch;
        this.x = x-getWidth()/2+32;
        this.y = y-getHeight()/2+32;
        this.color = color;
        setMaxSize(2);
        setMinSize(1);
        setTexture(AssetsManager.getLight());
        setSize(64);
    }

    public Aura(Batch batch, float x, float y, float width, float height, Color color)
    {
        super(batch, x, y, color);
        setWidth(width);
        setHeight(height);

    }

    @Override
    public void draw(CustomActor actor) {
        delta=delta+inc*incDir;
        alpha=alpha+inc*incDir;
        setHeight(size*delta*.8f);
        setWidth(size*delta*.8f);
        setX(actor.getX()- getWidth() / 2 +32);
        setY(actor.getY()- getHeight() / 2 +32);

        if(delta > getMaxSize())
        {
            incDir=-1;
            inc = (float)(rand.nextFloat()*.005+.001);
        }

        if(delta<getMinSize()){
            incDir=1;
            inc = (float)(rand.nextFloat()*.009+.006);

        }
        setColor(new Color(1f, 0, 0,(delta-.99f)/2));
        batch.setColor(getColor());
        super.draw(actor);
        batch.setColor(new Color(1, 1, 1,1));
    }

    @Override
    public void drawFix() {
        batch.setColor(getColor());
        setX(getX() - getWidth() / 2 +32);
        setY(getY() - getHeight() / 2 +32);
       super.drawFix();
        batch.setColor(new Color(1, 1, 1,1));
    }
}
