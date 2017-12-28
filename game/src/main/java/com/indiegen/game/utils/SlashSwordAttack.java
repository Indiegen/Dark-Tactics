package com.indiegen.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.indiegen.game.Actors.CustomActor;

/**
 * Created by Laptop on 18/12/2017.
 */


public class SlashSwordAttack extends Light {
    Animation slashAnimation;
    TextureRegion[] slashFrames;
    Light flash;

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        setDelta(0);
        this.show = show;
    }

    boolean show = false;

    public SlashSwordAttack(Batch batch, float x, float y, Color color) {
        super(batch, x, y, color);
        setWidth(64 * 3);
        setHeight(32 * 3);

        slashFrames = new TextureRegion[3];
        slashFrames[0] = new TextureRegion(AssetsManager.getSlash1());
        slashFrames[1] = new TextureRegion(AssetsManager.getSlash2());
        slashFrames[2] = new TextureRegion(AssetsManager.getSlash3());
        slashAnimation = new Animation(.1f, slashFrames);
        slashAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        flash = new Light(batch, getX(), getY(), new Color(1, 1, 1, .3f));
        flash.setWidth(800);
        flash.setHeight(800);
    }

    @Override
    public void draw(CustomActor actor) {
        if (isShow())
        {
            setDelta(getDelta() + Gdx.graphics.getDeltaTime());

            setTexture(slashAnimation.getKeyFrame(getDelta(), true).getTexture());
            setX(actor.getX() - getWidth() / 2 + 32);
            setY(actor.getY() - getHeight() / 2 + 32);
            batch.setColor(getColor());
            super.draw();
            batch.setColor(1, 1, 1, 1);

            flash.drawFix(actor);
            if (slashAnimation.isAnimationFinished(getDelta())) {
                setShow(false);
            }
        }
    }
}