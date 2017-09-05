package com.indiegen.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.indiegen.game.utils.RectangleUtils;

public class KingSkeleton extends GameEnemy {


    public void initialize() {
        setHP(190);
        setMaxHP(190);

        int margin = 64;
        setWidth(margin * (getTexture().getWidth() / getTexture().getHeight()));
        setHeight(margin);

        setName("Skeleton King");

        setRectangle(new Rectangle(getX(), getY(), margin, margin));
        setDir(1);

        setWalkFrames(new TextureRegion[1]);

        getWalkFrames()[0] = new TextureRegion(getTexture(), 0, 0, margin, margin);
        setWalkAnimation(new Animation(0.2f, getWalkFrames()));
        getWalkAnimation().setPlayMode(Animation.PlayMode.NORMAL);

        setWaitFrames(new TextureRegion[1]);

        getWaitFrames()[0] = new TextureRegion(getTexture(), 0, 0, margin, margin);

        setWaitAnimation(new Animation(0.8f, getWaitFrames()));
        getWaitAnimation().setPlayMode(Animation.PlayMode.NORMAL);

        setAttackFrames(new TextureRegion[1]);

        getAttackFrames()[0] = new TextureRegion(getTexture(), 0, 0, margin, margin);

        setAttackAnimation(new Animation(0.2f, getAttackFrames()));
        getAttackAnimation().setPlayMode(Animation.PlayMode.NORMAL);
        setTurnTexture(new TextureRegion(getTexture(), 64, 64, 16, 16));

        super.addRect(new RectangleUtils(getX(), getY(), margin, margin));

    }

    @Override
    public void moveRects() {

        int margin = 128;
        clearRects();

        super.addRect(new RectangleUtils(getX(), getY() + margin, margin / 2, margin));
        super.addRect(new RectangleUtils(getX(), getY() - margin / 2, margin / 2, margin));
        super.addRect(new RectangleUtils(getX() - margin / 2, getY(), margin, margin / 2));
        super.addRect(new RectangleUtils(getX() + margin, getY(), margin, margin / 2));

    }

    @Override
    public void attackRects() {

        int margin = 128;
        super.clearRects();

        super.addRect(new RectangleUtils(getX(), getY() + margin, margin / 2, margin));
        super.addRect(new RectangleUtils(getX(), getY() - margin / 2, margin / 2, margin));
        super.addRect(new RectangleUtils(getX() - margin / 2, getY(), margin, margin / 2));
        super.addRect(new RectangleUtils(getX() + margin, getY(), margin, margin / 2));

    }

    public KingSkeleton(Texture texture, int x, int y, String name) {

        super(texture, x, y, name);
        initialize();

    }

}
