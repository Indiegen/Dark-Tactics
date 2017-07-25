package com.indiegen.game.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.indiegen.game.enums.GamePlayerState;
import com.indiegen.game.utils.RectangleUtils;

import java.util.ArrayList;

public abstract class CustomActor extends Actor implements GameActor {

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

    @Override
    public void setFontAlpha(float fontAlpha) {

        this.fontAlpha = fontAlpha;
    }

    @Override
    public float getFontAlpha() {

        return fontAlpha;
    }

    @Override
    public void drawLabel(int hit) {


    }


    @Override
    public void setAttack(int attack) {

    }

    @Override
    public int getAttack() {

        return 0;
    }

    @Override
    public void moveRects() {

    }

    @Override
    public void attackRects() {

    }

    @Override
    public int getSpeed() {

        return speed;
    }

    @Override
    public void setPlayerState(GamePlayerState playerState) {

    }

    @Override
    public GamePlayerState getPlayerState() {

        return null;
    }

    @Override
    public void setCurY(float curY) {

    }

    @Override
    public float getCurY() {

        return 0;
    }

    @Override
    public void setCurX(float curX) {

    }

    @Override
    public float getCurX() {

        return 0;
    }

    @Override
    public void setState(int state) {

    }

    @Override
    public int getState() {

        return 0;
    }

    @Override
    public void setFlipY(boolean flipY) {

    }

    @Override
    public boolean getFlipY() {

        return false;
    }

    @Override
    public void setFlipX(boolean flipX) {

    }

    @Override
    public boolean getFlipX() {

        return false;
    }

    @Override
    public void setVelX(int velX) {

    }

    @Override
    public int getVelX() {

        return 0;
    }

    @Override
    public void setDir(int dir) {

    }

    @Override
    public int getDir() {

        return 0;
    }

    @Override
    public void setBoundingBox(BoundingBox boundingBox) {

    }

    @Override
    public BoundingBox getBoundingBox() {

        return null;
    }

    @Override
    public boolean drawRect(RectangleUtils rect) {

        return false;
    }

    @Override
    public boolean isTouched(float x, float y) {

        return false;
    }

    public void initRects(){
        this.rects = new ArrayList<>();
    }

    public ArrayList<RectangleUtils> getRects() {
        return this.rects;
    }

    public void addRect(RectangleUtils rect){
        this.rects.add(rect);
    }

    public void clearRects(){
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
        return new Vector2(getX() / margin, getY() / margin);
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

    public void setDefence(int defence){

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
}
