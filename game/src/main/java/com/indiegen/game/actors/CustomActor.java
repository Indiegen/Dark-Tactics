package com.indiegen.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.indiegen.game.enums.GamePlayerState;
import com.indiegen.game.utils.RectangleUtils;

import java.util.ArrayList;

public abstract class CustomActor extends Actor {

    private int damage = 0;
    private ArrayList<RectangleUtils> rects = new ArrayList<>();
    private float fontAlpha;
    private Animation animation;
    private TextureRegion turnTexture;
    private int fatigue = 0;
    private float delta = 0;
    private Animation attackAnimation;
    private Animation waitAnimation;
    private Animation walkAnimation;
    private boolean dead = false;
    private Rectangle rectangle;
    private int HP;
    private int attack;
    private GamePlayerState actorState;
    private float curX;
    private float curY;
    private int velX = 0;
    private Color color;
    private int dir;
    private boolean flipX = false;
    private boolean flipY = false;
    private int state = 0;
    private BoundingBox boundingBox;
    private ShapeRenderer shape;
    private BitmapFont font = new BitmapFont();
    private int margin = 64;
    private int speed = 64;
    private int defence;

    public void drawRect(RectangleUtils rect) {
        getShape().setColor(rect.getColor());
        getShape().rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    public void initRects() {
        this.rects = new ArrayList<>();
    }

    public ArrayList<RectangleUtils> getRects() {
        return this.rects;
    }

    public void addRect(RectangleUtils rect) {
        this.rects.add(rect);
    }

    public void clearRects() {
        this.rects.clear();
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public TextureRegion getTurnTexture() {
        return turnTexture;
    }

    public void setTurnTexture(TextureRegion turnTexture) {
        this.turnTexture = turnTexture;
    }

    public int getFatigue() {
        return fatigue;
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public Vector2 getPosMap() {
        return new Vector2(getX() / getMargin(), getY() / getMargin());
    }

    public float getDelta() {
        return delta;
    }

    public void setDelta(float delta) {
        this.delta = delta;
    }

    public boolean isAnimationFinished() {
        return animation.isAnimationFinished(getDelta());
    }

    public Animation getAttackAnimation() {
        return attackAnimation;
    }

    public Animation getWaitAnimation() {
        return waitAnimation;
    }

    public Animation getWalkAnimation() {
        return walkAnimation;
    }

    public void setAttackAnimation(Animation attackAnimation) {
        this.attackAnimation = attackAnimation;
    }

    public void setWaitAnimation(Animation waitAnimation) {
        this.waitAnimation = waitAnimation;
    }

    public void setWalkAnimation(Animation walkAnimation) {
        this.walkAnimation = walkAnimation;
    }

    public void setAnimation(int animations) {
        setDelta(0);
        switch (animations) {
            case 0:
                animation = getWaitAnimation();
                break;
            case 1:
                animation = getWalkAnimation();
                break;
            case 2:
                animation = getAttackAnimation();
                break;
        }
    }

    public Animation getAnimation() {
        return animation;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void setDamage(int damage) {
        this.damage = damage;
        setHP(getHP() - damage);
    }

    public int getDamage() {
        return damage;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setFontAlpha(float fontAlpha) {
        this.fontAlpha = fontAlpha;
    }

    public float getFontAlpha() {
        return fontAlpha;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void moveRects() {
        clearRects();

        addRect(new RectangleUtils(getX(), getY() + getMargin(), getMargin(), getMargin()));
        addRect(new RectangleUtils(getX(), getY() - getMargin(), getMargin(), getMargin()));
        addRect(new RectangleUtils(getX() - getMargin(), getY(), getMargin(), getMargin()));
        addRect(new RectangleUtils(getX() + getMargin(), getY(), getMargin(), getMargin()));
    }

    public void attackRects() {
        addRect(new RectangleUtils(getX(), getY() + getMargin(), getMargin(), getMargin()));
        addRect(new RectangleUtils(getX(), getY() - getMargin(), getMargin(), getMargin()));
        addRect(new RectangleUtils(getX() - getMargin(), getY(), getMargin(), getMargin()));
        addRect(new RectangleUtils(getX() + getMargin(), getY(), getMargin(), getMargin()));
    }

    public int getSpeed() {
        return speed;
    }

    public GamePlayerState getActorState() {
        return actorState;
    }

    public void setActorState(GamePlayerState actorState) {
        this.actorState = actorState;
    }

    public void setPlayerState(GamePlayerState playerState) {
        getActorState().exit(this, getDelta());
        this.setActorState(playerState);
        getActorState().enter(this, getDelta());
    }

    @Override
    public void act(float delta) {
        getActorState().update(this, delta);
        super.act(delta);
    }

    public float getCurX() {
        return curX;
    }

    public void setCurX(float curX) {
        this.curX = curX;
    }

    public float getCurY() {
        return curY;
    }

    public void setCurY(float curY) {
        this.curY = curY;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public boolean isFlipX() {
        return flipX;
    }

    public void setFlipX(boolean flipX) {
        this.flipX = flipX;
    }

    public boolean isFlipY() {
        return flipY;
    }

    public void setFlipY(boolean flipY) {
        this.flipY = flipY;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public ShapeRenderer getShape() {
        return shape;
    }

    public void setShape(ShapeRenderer shape) {
        this.shape = shape;
    }

    public int getMargin() {
        return margin;
    }

    public BitmapFont getFont() {
        return font;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }
}
