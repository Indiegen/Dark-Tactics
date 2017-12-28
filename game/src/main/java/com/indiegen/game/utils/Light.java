package com.indiegen.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.RandomXS128;
import com.indiegen.game.Actors.CustomActor;

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
    float width=0, Height=0;
    Texture texture;


    public Light(Batch batch, int col, int row)
	{

	}

	public Light(Batch batch, float x, float y, Color color)
	{
        this.batch=batch;
        rand = new RandomXS128();
        setColor(color);
        setTexture(AssetsManager.getLight());
	}

    public void draw()

    {
        batch.draw(getTexture(), getX(), getY(), getWidth(), getHeight());

    }

	public void draw(CustomActor actor)
	
	{
		batch.draw(getTexture(), getX(), getY(), getWidth(), getHeight());

	}

    public void drawFix()
    {
        batch.draw(getTexture(), getX(), getY(), getWidth(), getHeight());

    }

    public void drawFix(CustomActor actor)

    {
        setX(actor.getX()- getWidth() / 2 +32);
        setY(actor.getY()- getHeight() / 2 +32);
        batch.setColor(getColor());
        batch.draw(getTexture(), getX(), getY(), getWidth(), getHeight());
        batch.setColor(new Color(1, 1, 1,1));


    }

    public float getDelta() {
        return delta;
    }

    public void setDelta(float delta) {
        this.delta = delta;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return Height;
    }

    public void setHeight(float height) {
        Height = height;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

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
        setWidth(size);
        setHeight(size);
    }

    public float getLightSize() {
        return lightSize;
    }

    public void setLightSize(float lightSize) {
        this.lightSize = lightSize;
    }

    float lightSize=64;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

}