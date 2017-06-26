package com.indiegen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;

public class Particle {
    int MaxSize = 10;
    int MinSize = 2;

    float maxAlpha = 1f;
    float minAlpha = 0;

    float MaxRed = 1f;
    float MinRed = .5f;
    float red;

    Vector2 vel;
    Vector2 acc;
    Vector2 pos;

    float delta = 1;

    RandomXS128 rand;
    int size;
    float Alpha;
    Color color;
    MyActor actor;

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setAlpha(float alpha) {
        Alpha = alpha;
    }

    public float getAlpha() {
        return Alpha;
    }

    Particle(MyActor actor) {
        this.actor = actor;
        rand = new RandomXS128();
        pos = new Vector2(actor.getX() + actor.getWidth() / 2, actor.getY() + actor.getHeight() / 2);
        vel = new Vector2();
        acc = new Vector2();
        delta = 0;
        //pos.set(100,100);
        vel.set((1f - rand.nextFloat() * 2) * .2f, (1f - rand.nextFloat() * 2) * .2f);
        acc.set(1f, 1f);
        setSize((int) (rand.nextFloat() * (MaxSize - MinSize) + MinSize));
        setAlpha(rand.nextFloat() * (maxAlpha - minAlpha) + minAlpha);
        red = rand.nextFloat() * (MaxRed - MinRed) + MinRed;
        setColor(new Color(red, 0, 0, getAlpha()));

    }

    void animate(float x) {

        delta += Gdx.graphics.getDeltaTime();

        setAlpha(getAlpha() - x * delta * delta);
        setColor(new Color(red, 0, 0, getAlpha()));
        pos.set(pos.x + vel.x * acc.x * (1 / delta), pos.y + vel.y * acc.y * (1 / delta));

    }
}

