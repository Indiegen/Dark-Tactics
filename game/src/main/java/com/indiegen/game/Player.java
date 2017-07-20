package com.indiegen.game;

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

import java.util.ArrayList;

class Player extends MyActor implements stdActor {

    public void setPotions(int potions) {
        this.potions = potions;
    }

    public int getPotions() {
        return potions;
    }

    @Override
    public TextureRegion getTurnTexture() {

        return turnTexture;
    }

    @Override
    public void setTurnTexture(TextureRegion turnTexture) {

        this.turnTexture = turnTexture;
    }


    @Override
    public void Acting(Boolean acting) {

        this.acting = acting;
    }


    @Override
    public Boolean isActing() {

        return acting;
    }


    @Override
    public void setFatigue(int fatigue) {

        this.fatigue = fatigue;
    }

    @Override
    public int getFatigue() {

        return this.fatigue;
    }


    @Override
    public Vector2 getPosMap() {


        return new Vector2(getX() / margin, getY() / margin);
    }

    final int ATTACK = 50;

    final int WALK = 20;
    final int GUARD = 20;

    private TextureRegion currentFrame;
    private TextureRegion turnTexture;
    private final Animation attackAnimation;
    private final Animation waitAnimation;
    private final Animation walkAnimation;

    private BoundingBox boundingBox;
    private Rectangle rectangle;

    private final int margin = 64;
    private int velX = 0;
    private Color color;
    private float delta = 0;
    private int dir;
    private boolean flipX = false;
    private boolean flipY = false;
    private int state = 0;
    private final ShapeRenderer shape;
    private float curX;
    private float curY;
    private stdPlayerState actorState;
    private int HP;
    private final int maxHP = 80;
    private boolean dead = false;
    private int fatigue = 0;
    private Boolean acting = false;
    private int potions = 1;
    private int attack;

    public Player(Texture texture) {

        rects = new ArrayList<>();
        boundingBox = new BoundingBox();

        setHP(maxHP);
        setAttack(30);

        setName("player");
        setPosition(margin * 3, margin * 3);
        setWidth(margin);
        attack = 100;
        setHeight(margin);
        rectangle = new Rectangle(getX(), getY(), margin, margin);

        TextureRegion[] attackFrames = new TextureRegion[7];

        attackFrames[0] = new TextureRegion(texture, 0, 0, 32, 32);
        attackFrames[1] = new TextureRegion(texture, 32, 0, 32, 32);
        attackFrames[2] = new TextureRegion(texture, 64, 0, 32, 32);
        attackFrames[3] = new TextureRegion(texture, 96, 0, 32, 32);
        attackFrames[4] = new TextureRegion(texture, 32, 32, 32, 32);
        attackFrames[6] = new TextureRegion(texture, 64, 32, 32, 32);
        attackFrames[5] = new TextureRegion(texture, 64, 32, 32, 32);

        attackAnimation = new Animation(.08f, attackFrames);

        TextureRegion[] waitFrames = new TextureRegion[2];

        waitFrames[0] = new TextureRegion(texture, 0, 0, 32, 32);
        waitFrames[1] = new TextureRegion(texture, 96, 32, 32, 32);

        waitAnimation = new Animation(0.8f, waitFrames);

        TextureRegion[] walkFrames = new TextureRegion[2];

        walkFrames[0] = new TextureRegion(texture, 32, 64, 32, 32);
        walkFrames[1] = new TextureRegion(texture, 32, 64, 32, 32);

        walkAnimation = new Animation(0.8f, walkFrames);

        setAnimation(0);
        currentFrame = getAnimation().getKeyFrame(delta, true);
        delta = 0f;
        shape = new ShapeRenderer();

        actorState = stdPlayerState.WAITING;
        rects.add(new MyRect(getX(), getY(), margin, margin));
        setTurnTexture(new TextureRegion(texture, 64, 64, 16, 16));

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        delta += Gdx.graphics.getDeltaTime();

        if (getPlayerState() == stdPlayerState.BEING_HITTING) {
            batch.setColor(1, 1 - fontAlpha, 1 - fontAlpha, 1);
            font.setColor(1, 0, 0, fontAlpha);
            font.getData().scale(1f);
            font.draw(batch, -getDamage() + " HP", getX(), getY() + margin + margin * (1 - fontAlpha) / 2);
        } else {
            batch.setColor(Color.WHITE);
        }
        if (getPlayerState() == stdPlayerState.ITEM) {
            batch.setColor(1 - fontAlpha, 1, 1 - fontAlpha, 1);
            font.setColor(0, 1, 0, fontAlpha);
            font.getData().scale(1f);
            font.draw(batch, "+40 " + " HP", getX(), getY() + margin + margin * (1 - fontAlpha) / 2);
        } else {
            batch.setColor(Color.WHITE);
        }

        if (getPlayerState() == stdPlayerState.WAITING_TO_MOVE || getPlayerState() == stdPlayerState.ATTACK_TARGETING) {
            batch.end();
            Gdx.gl.glEnable(GL20.GL_BLEND);
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setProjectionMatrix(batch.getProjectionMatrix());

            for (MyRect rect : rects) {
                drawRect(rect);
            }
            shape.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
            batch.begin();
        }

        currentFrame = getAnimation().getKeyFrame(delta, true);
        if (dir == 0 && !currentFrame.isFlipX()) {
            currentFrame.flip(true, false);

        }
        if (dir == 1 && currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        }

        setRectangle(new Rectangle(getX(), getY(), margin, margin));
        batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

    }

    @Override
    public boolean isAnimationFinished() {

        return getAnimation().isAnimationFinished(this.delta);
    }


    @Override
    public void setAnimation(int animations) {

        delta = 0;
        switch (animations) {
            case 0:

                animation = waitAnimation;
                break;
            case 1:

                animation = walkAnimation;
                break;
            case 2:

                animation = attackAnimation;
                break;

        }
    }

    @Override
    public Animation getAnimation() {

        return animation;
    }

    @Override
    public void dead() {

        this.dead = true;
    }


    @Override
    public boolean isDead() {


        return this.dead;
    }


    @Override
    public void setDamage(int damage) {

        this.damage = damage;
        setHP(getHP() - damage);
    }

    @Override
    public int getDamage() {

        return damage;
    }


    @Override
    public void setFontAlpha(float fontAlpha) {

        this.fontAlpha = fontAlpha;
    }

    @Override
    public float getFontAlpha() {

        return fontAlpha;
    }

    @Override
    public void setAttack(int attack) {

        this.attack = attack;
    }

    @Override
    public int getAttack() {

        return attack;
    }

    @Override
    public void setHP(int HP) {

        this.HP = HP;
        if (this.HP > maxHP) {
            this.HP = maxHP;
        }

    }

    @Override
    public int getHP() {

        return HP;
    }

    @Override
    public void attackRects() {

        rects.clear();

        rects.add(new MyRect(getX(), getY() + margin, margin, margin));
        rects.add(new MyRect(getX(), getY() - margin, margin, margin));
        rects.add(new MyRect(getX() - margin, getY(), margin, margin));
        rects.add(new MyRect(getX() + margin, getY(), margin, margin));

    }


    @Override
    public void moveRects() {

        rects.clear();

        rects.add(new MyRect(getX(), getY() + margin, margin, margin));
        rects.add(new MyRect(getX(), getY() - margin, margin, margin));
        rects.add(new MyRect(getX() - margin, getY(), margin, margin));
        rects.add(new MyRect(getX() + margin, getY(), margin, margin));

    }


    @Override
    public int getSpeed() {

        return speed;
    }

    @Override
    public void setPlayerState(stdPlayerState newPlayerState) {

        getPlayerState().exit(this, delta);
        this.actorState = newPlayerState;
        getPlayerState().enter(this, delta);
    }


    @Override
    public stdPlayerState getPlayerState() {

        return actorState;
    }

    @Override
    public void setCurY(float curY) {
        this.curY = curY;
    }

    @Override
    public float getCurY() {
        return curY;
    }

    @Override
    public void setCurX(float curX) {
        this.curX = curX;
    }

    @Override
    public float getCurX() {
        return curX;
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public void setFlipY(boolean flipY) {
        this.flipY = flipY;
    }

    @Override
    public boolean getFlipY() {
        return flipY;
    }

    @Override
    public void setFlipX(boolean flipX) {
        this.flipX = flipX;
    }

    @Override
    public boolean getFlipX() {
        return flipX;
    }

    @Override
    public void setVelX(int velX) {
        this.velX = velX;
    }

    @Override
    public int getVelX() {
        return velX;
    }

    @Override
    public void setDir(int dir) {
        this.dir = dir;
    }

    @Override
    public int getDir() {
        return dir;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    @Override
    public boolean drawRect(MyRect rect) {
        shape.setColor(rect.getColor());
        shape.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        return false;
    }

    @Override
    public boolean isTouched(float x, float y) {

        return false;
    }

    @Override
    public void act(float delta) {

        getPlayerState().update(this, delta);

        super.act(delta);
    }


}
