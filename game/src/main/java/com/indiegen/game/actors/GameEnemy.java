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

public class GameEnemy extends CustomActor implements GameActor {

    private Texture texture;
    private TextureRegion[] walkFrames;
    private TextureRegion[] attackFrames;
    private TextureRegion[] waitFrames;
    private TextureRegion currentFrame;
    private TextureRegion turnTexture;
    private Animation walk;
    private Animation attackAnimation;
    private Animation waitAnimation;
    private Animation animation;
    private BoundingBox boundingBox;
    private Rectangle rectangle;

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
    private int HP;
    private int maxHP;
    private boolean dead = false;
    private int fatigue = 0;

    private GamePlayerState actorState;
    private final int attack;
    private final BitmapFont font;

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

        setWalk(new Animation(0.2f, getWalkFrames()));
        getWalk().setPlayMode(Animation.PlayMode.NORMAL);

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

        currentFrame = getAnimation().getKeyFrame(delta, true);
        setX(x);
        setY(y);
        delta = 0f;
        shape = new ShapeRenderer();

        actorState = GamePlayerState.WAITING;
        initRects();
        super.addRect(new RectangleUtils(getX(), getY(), margin, margin));
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

                animation = getWaitAnimation();
                break;
            case 1:

                animation = getWalk();
                break;
            case 2:

                animation = getAttackAnimation();
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

        super.setDamage(damage);
        setHP(getHP() - damage);
    }

    @Override
    public int getDamage() {

        return super.getDamage();
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
    public void setPlayerState(GamePlayerState newPlayerState) {

        actorState.exit(this, delta);
        this.actorState = newPlayerState;
        actorState.enter(this, delta);
    }

    @Override
    public GamePlayerState getPlayerState() {

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

        delta += Gdx.graphics.getDeltaTime();


        if (getPlayerState() == GamePlayerState.BEING_HITTING) {
            batch.setColor(1, 1 - getFontAlpha(), 1 - getFontAlpha(), 1);
            font.setColor(1, 0, 0, getFontAlpha());
            font.getData().scale(1f);
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



        currentFrame = getAnimation().getKeyFrame(delta, true);
        if (getDir() == 0 && !currentFrame.isFlipX()) {
            currentFrame.flip(true, false);

        }
        if (getDir() == 1 && currentFrame.isFlipX()) {
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


    @Override
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

    public void setWalkFrame(TextureRegion frame, int index){
        this.walkFrames[index] = frame;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public Animation getWalk() {
        return walk;
    }

    public void setWalk(Animation walk) {
        this.walk = walk;
    }

    public TextureRegion[] getWaitFrames() {
        return waitFrames;
    }

    public void setWaitFrames(TextureRegion[] waitFrames) {
        this.waitFrames = waitFrames;
    }

    public Animation getWaitAnimation() {
        return waitAnimation;
    }

    public void setWaitAnimation(Animation waitAnimation) {
        this.waitAnimation = waitAnimation;
    }

    public TextureRegion[] getAttackFrames() {
        return attackFrames;
    }

    public void setAttackFrames(TextureRegion[] attackFrames) {
        this.attackFrames = attackFrames;
    }

    public Animation getAttackAnimation() {
        return attackAnimation;
    }

    public void setAttackAnimation(Animation attackAnimation) {
        this.attackAnimation = attackAnimation;
    }
}
