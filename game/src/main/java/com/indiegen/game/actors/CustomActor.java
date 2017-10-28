package com.indiegen.game.Actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.indiegen.game.enums.GamePlayerState;
import com.indiegen.game.utils.RectangleUtils;

import java.util.ArrayList;

public abstract class CustomActor extends Actor implements GameActor {

    private int damage = 0;
    private ArrayList<RectangleUtils> rects = new ArrayList<>();
    private float fontAlpha = 0;
    private Animation animation;
    private TextureRegion turnTexture;
    private int fatigue = 0;
    private float delta = 0;
    private Animation attackAnimation;
    private Animation waitAnimation;
    private Animation walkAnimation;
    private Animation deadAnimation;
    private Animation guardAnimation;
    private Animation hitAnimation;
    private boolean dead = false;
    private Rectangle rectangle;
    private int HP;
    private boolean moveDone = false;
    private boolean actionDone = false;
    private int defence;
    private TextureRegion[] walkFrames;
    private TextureRegion[] attackFrames;
    private TextureRegion[] waitFrames;
    private TextureRegion[] deadFrames;
    private TextureRegion[] guardFrames;
    private TextureRegion[] hitFrames;
    private int attack;



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
    public void moveRects() {

    }

    @Override
    public void attackRects() {

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
    public void setDir(int dir) {

    }

    @Override
    public int getDir() {

        return 0;
    }

    @Override
    public boolean drawRect(RectangleUtils rect) {

        return false;
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
        return new Vector2(getX() / margin, getY() / margin);
    }

    public float getDelta() {
        return delta;
    }

    public void setDelta(float delta) {
        this.delta = delta;
    }

    public boolean isAnimationFinished() {
        return getAnimation().isAnimationFinished(getDelta());
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

    public Animation getHitAnimation() {
        return hitAnimation;
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

    public Animation getDeadAnimation() {
        return deadAnimation;
    }

    public void setDeadAnimation(Animation deadAnimation) {
        this.deadAnimation = deadAnimation;
    }

    public void setHitAnimation(Animation hitAnimation) {
        this.hitAnimation = hitAnimation;
    }

    public Animation getGuardAnimation() {

        return guardAnimation;
    }

    public void setGuardAnimation(Animation guardAnimation) {
        this.guardAnimation = guardAnimation;
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
            case 3:
                animation = getGuardAnimation();
                break;
            case 4:
                animation = getDeadAnimation();
                break;

            case 5:
                animation = getHitAnimation();
                break;
        }
    }

    public Animation getAnimation() {
        return animation;
    }

    public TextureRegion[] getWalkFrames() {
        return walkFrames;
    }

    public void setWalkFrames(TextureRegion[] walkFrames) {
        this.walkFrames = walkFrames;
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

    public TextureRegion[] getDeadFrames() {
        return deadFrames;
    }

    public void setDeadFrames(TextureRegion[] deadFrames) {
        this.deadFrames = deadFrames;
    }

    public TextureRegion[] getGuardFrames() {
        return guardFrames;
    }

    public void setGuardFrames(TextureRegion[] guardFrames) {
        this.guardFrames = guardFrames;
    }

    public TextureRegion[] getHitFrames() {
        return hitFrames;
    }

    public void setHitFrames(TextureRegion[] hitFrames) {
        this.hitFrames = hitFrames;
    }
    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void setDefence(int defence) {
        this.defence = defence;
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

    public void setMoveDone(boolean moveDone) {
        this.moveDone = moveDone;
    }

    public Boolean isMoveDone() {
        return moveDone;
    }

    public void setActionDone(boolean actionDone) {
        this.actionDone = actionDone;
    }

    public Boolean isActionDone() {
        return actionDone;
    }


}
