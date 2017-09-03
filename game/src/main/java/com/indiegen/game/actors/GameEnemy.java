package com.indiegen.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.indiegen.game.enums.GamePlayerState;
import com.indiegen.game.utils.RectangleUtils;

public class GameEnemy extends CustomActor {

    private Texture texture;
    private TextureRegion[] walkFrames;
    private TextureRegion[] attackFrames;
    private TextureRegion[] waitFrames;
    private TextureRegion currentFrame;
    private Animation walk;
    private Animation animation;

    private int maxHP;

    public GameEnemy(Texture texture, int x, int y, String name) {
        setHP(20);
        setMaxHP(80);
        this.setTexture(texture);
        setName("Skeleton " + name);
        setAttack(10);
        int margin = 64;
        setWidth(margin * (texture.getWidth() / texture.getHeight()));
        setHeight(margin);

        setBoundingBox(new BoundingBox());
        setRectangle(new Rectangle(getX(), getY(), margin, margin));

        setDir(1);

        setWalkFrames(new TextureRegion[4]);

        getWalkFrames()[0] = new TextureRegion(texture, 0, 32, 32, 32);
        getWalkFrames()[1] = new TextureRegion(texture, 32, 32, 32, 32);
        getWalkFrames()[2] = new TextureRegion(texture, 64, 32, 32, 32);
        getWalkFrames()[3] = new TextureRegion(texture, 96, 32, 32, 32);

        setWalkAnimation(new Animation(0.2f, getWalkFrames()));
        getWalkAnimation().setPlayMode(Animation.PlayMode.NORMAL);

        setWaitFrames(new TextureRegion[3]);

        getWaitFrames()[0] = new TextureRegion(texture, 0, 0, 32, 32);
        getWaitFrames()[1] = new TextureRegion(texture, 0, 64, 32, 32);
        getWaitFrames()[2] = new TextureRegion(texture, 32, 64, 32, 32);

        setWaitAnimation(new Animation(0.8f, getWaitFrames()));
        getWaitAnimation().setPlayMode(Animation.PlayMode.NORMAL);

        setAttackFrames(new TextureRegion[4]);

        getAttackFrames()[0] = new TextureRegion(texture, 0, 0, 32, 32);
        getAttackFrames()[1] = new TextureRegion(texture, 32, 0, 32, 32);
        getAttackFrames()[2] = new TextureRegion(texture, 64, 0, 32, 32);
        getAttackFrames()[3] = new TextureRegion(texture, 96, 0, 32, 32);


        setAttackAnimation(new Animation(0.2f, getAttackFrames()));
        getAttackAnimation().setPlayMode(Animation.PlayMode.NORMAL);

        setAnimation(0);

        currentFrame = getAnimation().getKeyFrame(getDelta(), true);
        setX(x);
        setY(y);
        setDelta(0f);
        setShape(new ShapeRenderer());

        this.setActorState(GamePlayerState.WAITING);
        initRects();
        super.addRect(new RectangleUtils(getX(), getY(), margin, margin));
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
        if (getAnimation() != null)
            currentFrame = getAnimation().getKeyFrame(getDelta(), true);
        if (getDir() == 0 && !currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        }
        if (getDir() == 1 && currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        }
        setWidth(getMargin());
        setHeight(getMargin());
        setRectangle(new Rectangle(getX(), getY(), getMargin(), getMargin()));
        batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        batch.setColor(Color.WHITE);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public TextureRegion[] getWalkFrames() {
        return walkFrames;
    }

    public void setWalkFrames(TextureRegion[] walkFrames) {
        this.walkFrames = walkFrames;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public TextureRegion[] getWaitFrames() {
        return waitFrames;
    }

    public void setWaitFrames(TextureRegion[] waitFrames) {
        this.waitFrames = waitFrames;
    }

    public TextureRegion[] getAttackFrames() {
        return attackFrames;
    }

    public void setAttackFrames(TextureRegion[] attackFrames) {
        this.attackFrames = attackFrames;
    }


}
