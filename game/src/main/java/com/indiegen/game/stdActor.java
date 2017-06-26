package com.indiegen.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.BoundingBox;

public interface stdActor {

    Vector2 getPosMap();

    void setAnimation(int animations);

    Animation getAnimation();

    boolean isAnimationFinished();

    void setDamage(int damage);

    int getDamage();

    boolean isDead();

    void dead();

    void setFontAlpha(float fontAlpha);

    float getFontAlpha();


    void setAttack(int attack);

    int getAttack();

    void setHP(int HP);

    int getHP();

    void drawLabel(int hit);

    void setCurY(float curY);

    float getCurY();

    void setCurX(float curX);

    float getCurX();

    void setState(int state);

    int getState();

    void setFlipY(boolean flipY);

    boolean getFlipY();

    void setFlipX(boolean flipX);

    boolean getFlipX();

    void setVelX(int velX);

    int getVelX();

    void setDir(int dir);

    int getDir();

    void setColor(Color color);

    Color getColor();

    void setRectangle(Rectangle rectangle);

    Rectangle getRectangle();

    void setBoundingBox(BoundingBox boundingBox);

    BoundingBox getBoundingBox();

    boolean drawRect(MyRect rect);

    boolean isTouched(float x, float y);

    void setPlayerState(stdPlayerState playerState);

    stdPlayerState getPlayerState();

    void moveRects();

    void attackRects();

    int getSpeed();

    BitmapFont font = new BitmapFont();
    int margin = 60;
    int speed = 60;
    int attack = 0;

    void setFatigue(int fatigue);

    int getFatigue();

    Boolean isActing();

    void Acting(Boolean acting);

    TextureRegion getTurnTexture();

    void setTurnTexture(TextureRegion turnTexture);
    //public enum stdState{WAITING, ACTING, MOVING, ATTACKING, CHARGING, GUARDING, ITEM_USING,GET_HITTING }
}
