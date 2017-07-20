package com.indiegen.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

public class MyActor extends Actor implements stdActor {

    int damage = 0;
    ArrayList<MyRect> rects;
    boolean dead = false;
    float fontAlpha;
    Animation animation;

    public Texture getTexture() {
        return null;
    }


    public void initialice() {

    }


    public void setDefence(int defence) {

    }


    public int getDefence() {

        return 0;
    }


    @Override
    public TextureRegion getTurnTexture() {

        return null;
    }

    @Override
    public void setTurnTexture(TextureRegion turnTexture) {


    }


    @Override
    public void Acting(Boolean acting) {


    }


    @Override
    public Boolean isActing() {

        return null;
    }

    @Override
    public void setFatigue(int fatigue) {


    }

    @Override
    public int getFatigue() {

        return 0;
    }


    @Override
    public Vector2 getPosMap() {


        return new Vector2(getX() / margin, getY() / margin);
    }

    @Override
    public boolean isAnimationFinished() {

        return true;
    }


    @Override
    public void setAnimation(int animations) {


    }

    @Override
    public Animation getAnimation() {

        return null;
    }


    @Override
    public void dead() {

        dead = true;
    }


    @Override
    public boolean isDead() {

        return false;
    }

    @Override
    public void setRectangle(Rectangle rectangle) {

    }


    @Override
    public void setDamage(int damage) {

        this.damage = damage;
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
    public void setHP(int HP) {

    }

    @Override
    public int getHP() {

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
    public void setPlayerState(stdPlayerState playerState) {

    }

    @Override
    public stdPlayerState getPlayerState() {

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
    public Rectangle getRectangle() {

        return null;
    }

    @Override
    public void setBoundingBox(BoundingBox boundingBox) {

    }

    @Override
    public BoundingBox getBoundingBox() {

        return null;
    }

    @Override
    public boolean drawRect(MyRect rect) {

        return false;
    }

    @Override
    public boolean isTouched(float x, float y) {

        return false;
    }

}
