package com.indiegen.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class KingSkeleton extends stdEnemy implements stdActor {


    public void initialice() {
        setHP(190);
        maxHP = 190;

        int margin = 64;
        setHeight(128);
        setWidth(128);

        setName("Skeleton King");

        rectangle = new Rectangle(getX(), getY(), margin, margin);
        dir = 1;

        texture = getTexture();
        walkFrames = new TextureRegion[1];

        walkFrames[0] = new TextureRegion(texture, 0, 0, margin, margin);
        walk = new Animation(0.2f, walkFrames);
        walk.setPlayMode(Animation.PlayMode.NORMAL);

        waitFrames = new TextureRegion[1];

        waitFrames[0] = new TextureRegion(texture, 0, 0, margin, margin);

        waitAnimation = new Animation(0.8f, waitFrames);
        waitAnimation.setPlayMode(Animation.PlayMode.NORMAL);

        attackFrames = new TextureRegion[1];

        attackFrames[0] = new TextureRegion(texture, 0, 0, margin, margin);

        attackAnimation = new Animation(0.2f, attackFrames);
        attackAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        setTurnTexture(new TextureRegion(texture, 64, 64, 16, 16));

        texture = getTexture();
        rects.add(new MyRect(getX(), getY(), margin, margin));

    }

    @Override
    public void moveRects() {

        int margin = 128;
        rects.clear();

        rects.add(new MyRect(getX(), getY() + margin, margin / 2, margin));
        rects.add(new MyRect(getX(), getY() - margin / 2, margin / 2, margin));
        rects.add(new MyRect(getX() - margin / 2, getY(), margin, margin / 2));
        rects.add(new MyRect(getX() + margin, getY(), margin, margin / 2));

    }

    @Override
    public void attackRects() {

        int margin = 128;
        rects.clear();

        rects.add(new MyRect(getX(), getY() + margin, margin / 2, margin));
        rects.add(new MyRect(getX(), getY() - margin / 2, margin / 2, margin));
        rects.add(new MyRect(getX() - margin / 2, getY(), margin, margin / 2));
        rects.add(new MyRect(getX() + margin, getY(), margin, margin / 2));

    }

    KingSkeleton(Texture texture, int x, int y, String name) {

        super(texture, x, y, name);

    }

}
