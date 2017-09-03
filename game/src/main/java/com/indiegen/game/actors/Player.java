package com.indiegen.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.indiegen.game.enums.GamePlayerState;
import com.indiegen.game.utils.RectangleUtils;

public class Player extends CustomActor {

    private final int ATTACK = 50;
    private final int WALK = 20;
    private final int GUARD = 20;
    private TextureRegion currentFrame;
    private final int maxHP = 80;
    private int potions = 1;

    public Player(Texture texture) {
        setBoundingBox(new BoundingBox());

        setHP(maxHP);
        setAttack(30);

        setName("player");
        setPosition(getMargin() * 3, getMargin() * 3);
        setWidth(getMargin());
        setAttack(100);
        setHeight(getMargin());
        setRectangle(new Rectangle(getX(), getY(), getMargin(), getMargin()));

        TextureRegion[] attackFrames = new TextureRegion[7];

        attackFrames[0] = new TextureRegion(texture, 0, 0, 32, 32);
        attackFrames[1] = new TextureRegion(texture, 32, 0, 32, 32);
        attackFrames[2] = new TextureRegion(texture, 64, 0, 32, 32);
        attackFrames[3] = new TextureRegion(texture, 96, 0, 32, 32);
        attackFrames[4] = new TextureRegion(texture, 32, 32, 32, 32);
        attackFrames[6] = new TextureRegion(texture, 64, 32, 32, 32);
        attackFrames[5] = new TextureRegion(texture, 64, 32, 32, 32);

        setAttackAnimation(new Animation(.08f, attackFrames));

        TextureRegion[] waitFrames = new TextureRegion[2];

        waitFrames[0] = new TextureRegion(texture, 0, 0, 32, 32);
        waitFrames[1] = new TextureRegion(texture, 96, 32, 32, 32);

        setWaitAnimation(new Animation(0.8f, waitFrames));

        TextureRegion[] walkFrames = new TextureRegion[2];

        walkFrames[0] = new TextureRegion(texture, 32, 64, 32, 32);
        walkFrames[1] = new TextureRegion(texture, 32, 64, 32, 32);

        setWalkAnimation(new Animation(0.8f, walkFrames));

        setAnimation(getWaitAnimation());
        currentFrame = getAnimation().getKeyFrame(getDelta(), true);
        setDelta(0f);
        setShape(new ShapeRenderer());

        setActorState(GamePlayerState.WAITING);
        initRects();
        super.addRect(new RectangleUtils(getX(), getY(), getMargin(), getMargin()));
        setTurnTexture(new TextureRegion(texture, 64, 64, 16, 16));

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        setDelta(getDelta() + Gdx.graphics.getDeltaTime());
        if (getActorState() == GamePlayerState.BEING_HITTING) {
            batch.setColor(1, 1 - getFontAlpha(), 1 - getFontAlpha(), 1);
            getFont().setColor(1, 0, 0, getFontAlpha());
            getFont().getData().scale(1f);
            getFont().draw(batch, -getDamage() + " HP", getX(), getY() + getMargin() + getMargin() * (1 - getFontAlpha()) / 2);
        } else {
            batch.setColor(Color.WHITE);
        }
        if (getActorState() == GamePlayerState.ITEM) {
            batch.setColor(1 - getFontAlpha(), 1, 1 - getFontAlpha(), 1);
            getFont().setColor(0, 1, 0, getFontAlpha());
            getFont().getData().scale(1f);
            getFont().draw(batch, "+40 " + " HP", getX(), getY() + getMargin() + getMargin() * (1 - getFontAlpha()) / 2);
        } else {
            batch.setColor(Color.WHITE);
        }
        if (getActorState() == GamePlayerState.WAITING_TO_MOVE || getActorState() == GamePlayerState.ATTACK_TARGETING) {
            batch.end();
            Gdx.gl.glEnable(GL20.GL_BLEND);
            getShape().begin(ShapeRenderer.ShapeType.Filled);
            getShape().setProjectionMatrix(batch.getProjectionMatrix());
            for (RectangleUtils rect : getRects()) {
                drawRect(rect);
            }
            getShape().end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
            batch.begin();
        }
        currentFrame = getAnimation().getKeyFrame(getDelta(), true);
        if (getDir() == 0 && !currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        }
        if (getDir() == 1 && currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        }
        setRectangle(new Rectangle(getX(), getY(), getMargin(), getMargin()));
        batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public int getATTACK() {
        return ATTACK;
    }

    public int getWALK() {
        return WALK;
    }

    public int getGUARD() {
        return GUARD;
    }

    public void setPotions(int potions) {
        this.potions = potions;
    }

    public int getPotions() {
        return potions;
    }
}
