package com.indiegen.game.Actors;


import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.collision.*;
import com.indiegen.game.enums.*;
import com.indiegen.game.utils.*;

public class GameEnemy extends CustomActor
{
    private Texture texture;

    private TextureRegion currentFrame;
    private Animation walk;
    private Animation animation;
    private BoundingBox boundingBox;

    private int velX = 0;
    private Color color;
    private int dir;
    private boolean flipX = false;
    private boolean flipY = false;
    private int state = 0;
    private final ShapeRenderer shape;
    private float curX;
    private float curY;
    private int maxHP;

    private GamePlayerState actorState;
    private final int attack;
    private final BitmapFont font;
    private int defence=0;

    public GameEnemy(Texture texture, int x, int y, String name) {
        setHP(20);
        setMaxHP(80);
        this.setTexture(texture);
        setName("Skeleton " + name);

        attack = 10;

        int margin = 64;
        setWidth(margin * (texture.getWidth() / texture.getHeight()));
        setHeight(margin);

        boundingBox = new BoundingBox();
        font = new BitmapFont();
        setRectangle(new Rectangle(getX(), getY(), margin, margin));

        setDir(1);

        setWalkFrames(new TextureRegion[4]);

        getWalkFrames()[0] = new TextureRegion(texture, 0, 32, 32, 32);
        getWalkFrames()[1] = new TextureRegion(texture, 32, 32, 32, 32);
        getWalkFrames()[2] = new TextureRegion(texture, 64, 32, 32, 32);
        getWalkFrames()[3] = new TextureRegion(texture, 96, 32, 32, 32);

        setWalkAnimation(new Animation(0.08f, getWalkFrames()));
        getWalkAnimation().setPlayMode(Animation.PlayMode.NORMAL);

        setWaitFrames(new TextureRegion[3]);

        getWaitFrames()[0] = new TextureRegion(texture, 0, 0, 32, 32);
        getWaitFrames()[1] = new TextureRegion(texture, 0, 64, 32, 32);
        getWaitFrames()[2] = new TextureRegion(texture, 32, 64, 32, 32);

        setWaitAnimation(new Animation(0.1f, getWaitFrames()));
        getWaitAnimation().setPlayMode(Animation.PlayMode.NORMAL);

        setAttackFrames(new TextureRegion[4]);

        getAttackFrames()[0] = new TextureRegion(texture, 0, 0, 32, 32);
        getAttackFrames()[1] = new TextureRegion(texture, 32, 0, 32, 32);
        getAttackFrames()[2] = new TextureRegion(texture, 64, 0, 32, 32);
        getAttackFrames()[3] = new TextureRegion(texture, 96, 0, 32, 32);


        setAttackAnimation(new Animation(0.2f, getAttackFrames()));
        getAttackAnimation().setPlayMode(Animation.PlayMode.NORMAL);
		
		
//Dead
		setDeadFrames(new TextureRegion[3]);

		getDeadFrames()[0] = new TextureRegion(texture, 0, 108, 44, 36);
        getDeadFrames()[1] = new TextureRegion(texture, 32, 108, 44, 36);
        getDeadFrames()[2] = new TextureRegion(texture, 64, 108, 44, 36);

		setHitAnimation(new Animation(0.1f, getDeadFrames()));
        getHitAnimation().setPlayMode(Animation.PlayMode.NORMAL);

		
        setAnimation(0);

        currentFrame = getAnimation().getKeyFrame(getDelta(), true);
        setX(x);
        setY(y);
        setDelta(0f);
        shape = new ShapeRenderer();

        actorState = GamePlayerState.WAITING;
        initRects();
        addRect(new RectangleUtils(getX(), getY(), margin, margin));
        setTurnTexture(new TextureRegion(texture, 64, 64, 16, 16));
    }

    @Override
    public void drawLabel(int hit) {

        setHP(getHP() - hit);
    }

    @Override
    public void setAttack() {

    }

    @Override
    public int getAttack() {

        return attack;
    }

    @Override
    public int getDefence() {

        return defence;
    }

    @Override
    public void setPlayerState(GamePlayerState newPlayerState) {

        actorState.exit(this, getDelta());
        this.actorState = newPlayerState;
        actorState.enter(this, getDelta());
    }

    @Override
    public GamePlayerState getPlayerState() {

        return actorState;
    }

    @Override
    public void moveRects() {

        clearRects();

        addRect(new RectangleUtils(getX(), getY() + margin, margin, margin));
        addRect(new RectangleUtils(getX(), getY() - margin, margin, margin));
        addRect(new RectangleUtils(getX() - margin, getY(), margin, margin));
        addRect(new RectangleUtils(getX() + margin, getY(), margin, margin));

    }

    @Override
    public void attackRects() {

        clearRects();

        addRect(new RectangleUtils(getX(), getY() + margin, margin, margin));
        addRect(new RectangleUtils(getX(), getY() - margin, margin, margin));
        addRect(new RectangleUtils(getX() - margin, getY(), margin, margin));
        addRect(new RectangleUtils(getX() + margin, getY(), margin, margin));

    }

    @Override
    public int getSpeed() {

        return speed;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        setDelta(getDelta()+Gdx.graphics.getDeltaTime());


        if (getPlayerState() == GamePlayerState.BEING_HITTING) {
            batch.setColor(1, 1 - getFontAlpha(), 1 - getFontAlpha(), 1);
            font.setColor(1, 0, 0, getFontAlpha());
           //font.scale(1f);
            font.draw(batch, -getDamage() + " HP", getX(), getY() + margin + margin * (1 - getFontAlpha()) / 2);
        } else {
            batch.setColor(Color.WHITE);
        }

        if (getPlayerState() == GamePlayerState.WAITING_TO_MOVE || getPlayerState() == GamePlayerState.ATTACK_TARGETING) {
            batch.end();
            Gdx.gl.glEnable(GL20.GL_BLEND);

            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setProjectionMatrix(batch.getProjectionMatrix());

            for (RectangleUtils rect : getRects()) {
                drawRect(rect);
            }
            shape.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
            batch.begin();
        }



        if(getAnimation() != null)
            currentFrame = getAnimation().getKeyFrame(getDelta(), true);
        if (getDir() == 0 && !currentFrame.isFlipX()) {
            currentFrame.flip(true, false);

        }
        if (getDir() == 1 && currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        }

        setWidth(currentFrame.getRegionWidth()*2);
        setHeight(currentFrame.getRegionHeight()*2);

        setRectangle(new Rectangle(getX(), getY(), margin, margin));

        batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        batch.setColor(Color.WHITE);

    }

    public boolean drawRect(RectangleUtils rect) {
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

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }



    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
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
	}
