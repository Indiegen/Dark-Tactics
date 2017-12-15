package com.indiegen.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.RandomXS128;

public class Light
{
	float delta=0;
	int incDir=1;
	float inc=0;
	RandomXS128 rand;
	Batch batch;
	float x;
	float y;
	float alpha=0;
	Color color;
    float maxSize=2;
    float minSize=1;
    float size=96;

    public float getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(float maxSize) {
        this.maxSize = maxSize;
    }

    public float getMinSize() {
        return minSize;
    }

    public void setMinSize(float minSize) {
        this.minSize = minSize;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }


    public float getLightSize() {
        return lightSize;
    }

    public void setLightSize(float lightSize) {
        this.lightSize = lightSize;
    }

    float lightSize=64;

    public float getX() {
        return x-lightSize/2+32;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y-lightSize/2+32;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Light(Batch batch, int col, int row)
	{
		rand = new RandomXS128();
		this.batch=batch;
		this.x = 64 * col - lightSize / 2 +32;
		this.y = 64 * row - lightSize / 2 +32;
		this.color = new Color(1f, .6f, .3f,1);
		setMaxSize(2);
		setMinSize(1);
	}

	public Light(Batch batch, float x, float y, Color color)
	{
		rand = new RandomXS128();
		this.batch=batch;
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public void draw()
	
	{
		delta=delta+inc*incDir;
		alpha=alpha+inc*incDir;
		lightSize=size*delta;
		if(delta > getMaxSize())
		{
			incDir=-1;
			inc = (float)(rand.nextFloat()*.009+.005);
		}

		if(delta<getMinSize()){
			incDir=1;
			inc = (float)(rand.nextFloat()*.009+.005);

		}
		batch.setColor(color.r, color.g, color.b, delta/2.5f-.05f);
		
		batch.draw(AssetsManager.getLight(), getX(), getY(), lightSize, lightSize);
	}

}