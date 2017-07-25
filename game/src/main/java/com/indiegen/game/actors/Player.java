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

    private BoundingBox boundingBox;
    private Rectangle rectangle;

    private final int margin = 64;
    private int velX = 0;
    private Color color;
    private int dir;
    private boolean flipX = false;
    private boolean flipY = false;
    private int state = 0;
    private final ShapeRenderer shape;
    private float curX;
    private float curY;
    private GamePlayerState actorState;
    private int HP;
    private final int maxHP = 80;
    private Boolean acting = false;
    private int potions = 1;
    private int attack;

    public Boolean isActing() {
        return acting;
    }

    public Player(Texture texture) {
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
        shape = new ShapeRenderer();

        actorState = GamePlayerState.WAITING;
        initRects();
        super.addRect(new RectangleUtils(getX(), getY(), margin, margin));
        setTurnTexture(new TextureRegion(texture, 64, 64, 16, 16));

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        setDelta(getDelta() + Gdx.graphics.getDeltaTime());

        if (getPlayerState() == GamePlayerState.BEING_HITTING) {
            batch.setColor(1, 1 - super.getFontAlpha(), 1 - super.getFontAlpha(), 1);
            font.setColor(1, 0, 0, super.getFontAlpha());
            font.getData().scale(1f);
            font.draw(batch, -getDamage() + " HP", getX(), getY() + margin + margin * (1 - super.getFontAlpha()) / 2);
        } else {
            batch.setColor(Color.WHITE);
        }
        if (getPlayerState() == GamePlayerState.ITEM) {
            batch.setColor(1 - super.getFontAlpha(), 1, 1 - super.getFontAlpha(), 1);
            font.setColor(0, 1, 0, super.getFontAlpha());
            font.getData().scale(1f);
            font.draw(batch, "+40 " + " HP", getX(), getY() + margin + margin * (1 - super.getFontAlpha()) / 2);
        } else {
            batch.setColor(Color.WHITE);
        }

        if (getPlayerState() == GamePlayerState.WAITING_TO_MOVE || getPlayerState() == GamePlayerState.ATTACK_TARGETING) {
            batch.end();
            Gdx.gl.glEnable(GL20.GL_BLEND);
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setProjectionMatrix(batch.getProjectionMatrix());

            for (RectangleUtils rect : super.getRects()) {
                drawRect(rect);
            }
            shape.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
            batch.begin();
        }

        currentFrame = getAnimation().getKeyFrame(getDelta(), true);
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
    public void setDamage(int damage) {

        super.setDamage(damage);
        setHP(getHP() - damage);
    }

    @Override
    public int getDamage() {

        return super.getDamage();
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

        super.clearRects();

        super.addRect(new RectangleUtils(getX(), getY() + margin, margin, margin));
        super.addRect(new RectangleUtils(getX(), getY() - margin, margin, margin));
        super.addRect(new RectangleUtils(getX() - margin, getY(), margin, margin));
        super.addRect(new RectangleUtils(getX() + margin, getY(), margin, margin));

    }

    @Override
    public void moveRects() {

        super.clearRects();

        super.addRect(new RectangleUtils(getX(), getY() + margin, margin, margin));
        super.addRect(new RectangleUtils(getX(), getY() - margin, margin, margin));
        super.addRect(new RectangleUtils(getX() - margin, getY(), margin, margin));
        super.addRect(new RectangleUtils(getX() + margin, getY(), margin, margin));

    }

    @Override
    public int getSpeed() {

        return speed;
    }

    @Override
    public void setPlayerState(GamePlayerState newPlayerState) {

        getPlayerState().exit(this, getDelta());
        this.actorState = newPlayerState;
        getPlayerState().enter(this, getDelta());
    }

    @Override
    public GamePlayerState getPlayerState() {

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
    public boolean drawRect(RectangleUtils rect) {
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
