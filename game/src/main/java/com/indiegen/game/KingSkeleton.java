package com.indiegen.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class KingSkeleton extends stdEnemy implements stdActor {


    public void initialice() {
        // TODO: Implement this method
        setHP(190);
        maxHP = 190;

        int margen = 64;
        setHeight(128);
        setWidth(128);

        setName("Skeleton " + "King");

        //attack = 15;

        rectangle = new Rectangle(getX(), getY(), margen, margen);
        dir = 1;

        texture = getTexture();
        walkFrames = new TextureRegion[1];

        walkFrames[0] = new TextureRegion(texture, 0, 0, margen, margen);
//		walkFrames[1] = new TextureRegion(texture, 32, 32, 32, 32);
//        walkFrames[2] = new TextureRegion(texture, 64, 32, 32, 32);
//		walkFrames[3] = new TextureRegion(texture, 96, 32, 32, 32);
//
        walk = new Animation(0.2f, walkFrames);
        walk.setPlayMode(Animation.PlayMode.NORMAL);

        waitFrames = new TextureRegion[1];

        waitFrames[0] = new TextureRegion(texture, 0, 0, margen, margen);
//		waitFrames[1] = new TextureRegion(texture, 0,64, 32, 32);
//		waitFrames[2] = new TextureRegion(texture, 32,64, 32, 32);
//
        waitAnimation = new Animation(0.8f, waitFrames);
        waitAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        //currentFrame.flip(true, false);

        attackFrames = new TextureRegion[1];

        attackFrames[0] = new TextureRegion(texture, 0, 0, margen, margen);
//		attackFrames[1] = new TextureRegion(texture, 32, 0, 32, 32);
//		attackFrames[2] = new TextureRegion(texture, 64, 0, 32, 32);
//        attackFrames[3] = new TextureRegion(texture, 96, 0, 32, 32);
//
        attackAnimation = new Animation(0.2f, attackFrames);
        attackAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        setTurnTexture(new TextureRegion(texture, 64, 64, 16, 16));

        texture = getTexture();
        rects.add(new MyRect(getX(), getY(), margen, margen));
        //currentFrame= waitAnimation.getKeyFrame(delta,true);

    }

    @Override
    public void moveRects() {
        // TODO: Implement this method
        int margen = 128;
        rects.clear();

        rects.add(new MyRect(getX(), getY() + margen, margen / 2, margen));
        rects.add(new MyRect(getX(), getY() - margen / 2, margen / 2, margen));
        rects.add(new MyRect(getX() - margen / 2, getY(), margen, margen / 2));
        rects.add(new MyRect(getX() + margen, getY(), margen, margen / 2));

    }

    @Override
    public void attackRects() {
        // TODO: Implement this method
        int margen = 128;
        rects.clear();

        rects.add(new MyRect(getX(), getY() + margen, margen / 2, margen));
        rects.add(new MyRect(getX(), getY() - margen / 2, margen / 2, margen));
        rects.add(new MyRect(getX() - margen / 2, getY(), margen, margen / 2));
        rects.add(new MyRect(getX() + margen, getY(), margen, margen / 2));

    }

    KingSkeleton(Texture settexture, int x, int y, String name) {
        super(settexture, x, y, name);

    }

}
