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

public class stdEnemy extends MyActor implements stdActor {

    Texture texture;
    TextureRegion[] walkFrames;
    TextureRegion[] attackFrames;
    TextureRegion[] waitFrames;
    private TextureRegion currentFrame;
    private TextureRegion turnTexture;
    Animation walk;
    Animation attackAnimation;
    Animation waitAnimation;
    private Animation animation;
    private BoundingBox boundingBox;
    Rectangle rectangle;

    private int velX = 0;
    private Color color;
    private float delta = 0;
    int dir;
    private boolean flipX = false;
    private boolean flipY = false;
    private int state = 0;
    private final ShapeRenderer shape;
    private float curX;
    private float curY;
    private int HP;
    int maxHP;
    private boolean dead = false;
    private int fatigue = 0;

    private stdPlayerState actorState;
    private final int attack;
    private final BitmapFont font;

    public stdEnemy(Texture texture, int x, int y, String name) {
        setHP(20);
        maxHP = 80;
        this.texture = texture;
        setName("Skeleton " + name);

        attack = 10;
        rects = new ArrayList<>();

        int margin = 64;
        setWidth(margin * (texture.getWidth() / texture.getHeight()));
        setHeight(margin);

        boundingBox = new BoundingBox();
        font = new BitmapFont();
        rectangle = new Rectangle(getX(), getY(), margin, margin);

        dir = 1;

        walkFrames = new TextureRegion[4];

        walkFrames[0] = new TextureRegion(texture, 0, 32, 32, 32);
        walkFrames[1] = new TextureRegion(texture, 32, 32, 32, 32);
        walkFrames[2] = new TextureRegion(texture, 64, 32, 32, 32);
        walkFrames[3] = new TextureRegion(texture, 96, 32, 32, 32);

        walk = new Animation(0.2f, walkFrames);
        walk.setPlayMode(Animation.PlayMode.NORMAL);

        waitFrames = new TextureRegion[3];

        waitFrames[0] = new TextureRegion(texture, 0, 0, 32, 32);
        waitFrames[1] = new TextureRegion(texture, 0, 64, 32, 32);
        waitFrames[2] = new TextureRegion(texture, 32, 64, 32, 32);

        waitAnimation = new Animation(0.8f, waitFrames);
        waitAnimation.setPlayMode(Animation.PlayMode.NORMAL);

        attackFrames = new TextureRegion[4];

        attackFrames[0] = new TextureRegion(texture, 0, 0, 32, 32);
        attackFrames[1] = new TextureRegion(texture, 32, 0, 32, 32);
        attackFrames[2] = new TextureRegion(texture, 64, 0, 32, 32);
        attackFrames[3] = new TextureRegion(texture, 96, 0, 32, 32);


        attackAnimation = new Animation(0.2f, attackFrames);
        attackAnimation.setPlayMode(Animation.PlayMode.NORMAL);

        setAnimation(0);

        currentFrame = getAnimation().getKeyFrame(delta, true);
        setX(x);
        setY(y);
        delta = 0f;
        shape = new ShapeRenderer();

        actorState = stdPlayerState.WAITING;
        rects.add(new MyRect(getX(), getY(), margin, margin));
        setTurnTexture(new TextureRegion(texture, 64, 64, 16, 16));
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

                animation = walk;
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

        return dead;
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
    public void drawLabel(int hit) {

        setHP(getHP() - hit);
    }


    @Override
    public void setAttack(int attack) {

    }

    @Override
    public int getAttack() {

        return attack;
    }

    @Override
    public void setPlayerState(stdPlayerState newPlayerState) {

        actorState.exit(this, delta);
        this.actorState = newPlayerState;
        actorState.enter(this, delta);
    }

    @Override
    public stdPlayerState getPlayerState() {

        return actorState;
    }


    @Override
    public void setHP(int HP) {

        this.HP = HP;
    }

    @Override
    public int getHP() {

        return HP;
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
    public void attackRects() {

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

        setWidth(margin);
        setHeight(margin);

        setRectangle(new Rectangle(getX(), getY(), margin, margin));

        batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        batch.setColor(Color.WHITE);

    }

    public void setCurY(float curY) {
        this.curY = curY;
    }

    public float getCurY() {
        return curY;
    }

    public void setCurX(float curX) {
        this.curX = curX;
    }

    public float getCurX() {
        return curX;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setFlipY(boolean flipY) {
        this.flipY = flipY;
    }

    public boolean getFlipY() {
        return flipY;
    }

    public void setFlipX(boolean flipX) {
        this.flipX = flipX;
    }

    public boolean getFlipX() {
        return flipX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelX() {
        return velX;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getDir() {
        return dir;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }


    @Override
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public Rectangle getRectangle() {

        return rectangle;
    }

    public boolean drawRect(MyRect rect) {
        shape.setColor(rect.getColor());
        shape.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

        return false;
    }

    @Override
    public void act(float delta) {

        actorState.update(this, delta);
        super.act(delta);
    }

    public boolean isTouched(float x, float y) {

        return false;
    }


}
