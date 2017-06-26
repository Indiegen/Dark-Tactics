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
        // TODO: Implement this method
        return turnTexture;
    }

    @Override
    public void setTurnTexture(TextureRegion turnTexture) {
        // TODO: Implement this method
        this.turnTexture = turnTexture;
    }


    @Override
    public void Acting(Boolean acting) {
        // TODO: Implement this method
        this.acting = acting;
    }


    @Override
    public Boolean isActing() {
        // TODO: Implement this method
        return acting;
    }


    @Override
    public void setFatigue(int fatigue) {
        // TODO: Implement this method
        this.fatigue = fatigue;
    }

    @Override
    public int getFatigue() {
        // TODO: Implement this method
        return this.fatigue;
    }


    @Override
    public Vector2 getPosMap() {
        // TODO: Implement this method

        return new Vector2(getX() / margin, getY() / margin);
    }

    final int ATTACK = 50;

    final int WALK = 20;
    // --Commented out by Inspection (23/06/17 20:53):final int RUN = 50;
    final int GUARD = 20;
    // --Commented out by Inspection (23/06/17 20:53):final int HEAVY_ATTACK = 90;
    // --Commented out by Inspection (23/06/17 20:53):final int ROLL = 30;
    // --Commented out by Inspection (23/06/17 20:53):final int JUMP = 50;
    // --Commented out by Inspection (23/06/17 20:53):final int WAIT = 10;
    // --Commented out by Inspection (23/06/17 20:53):final int ITEM = 50;
    // --Commented out by Inspection (23/06/17 20:53):final int HURTS = 10;


    private TextureRegion currentFrame;
    private TextureRegion turnTexture;
    private final Animation attackAnimation;
    private final Animation waitAnimation;
    private final Animation walkAnimation;

    private BoundingBox boundingBox;
    private Rectangle rectangle;
    // --Commented out by Inspection (23/06/17 20:53):Rectangle rectUp;
    // --Commented out by Inspection (23/06/17 20:53):Rectangle rectDown;
    // --Commented out by Inspection (23/06/17 20:53):Rectangle rectLeft;
    // --Commented out by Inspection (23/06/17 20:53):Rectangle rectRight;

    private final int margin = 64;
    private int velX = 0;
    private Color color;
    // --Commented out by Inspection (23/06/17 20:53):int FRAME_COLS = 3;
    // --Commented out by Inspection (23/06/17 20:53):int FRAME_ROWS = 1;
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
//		attackFrames[1] = new TextureRegion(texture, 32, 32, 32, 32);
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
            //Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
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
        //batch.draw(texture, getX(), getY(), 0, 0 , getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation(), (int)getWidth() * 0, (int)getHeight() * 0, texture.getWidth(), texture.getHeight(), getFlipX(), getFlipY());
        //batch.draw(texture, getX(), getY(), getWidth()/3,getHeight()/3);

    }

    @Override
    public boolean isAnimationFinished() {
        // TODO: Implement this method
        return getAnimation().isAnimationFinished(this.delta);
    }


    @Override
    public void setAnimation(int animations) {
        // TODO: Implement this method
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
        // TODO: Implement this method
        return animation;
    }

    @Override
    public void dead() {
        // TODO: Implement this method
        this.dead = true;
    }


    @Override
    public boolean isDead() {
        // TODO: Implement this method

        return this.dead;
    }


    @Override
    public void setDamage(int damage) {
        // TODO: Implement this method
        this.damage = damage;
        setHP(getHP() - damage);
    }

    @Override
    public int getDamage() {
        // TODO: Implement this method
        return damage;
    }


    @Override
    public void setFontAlpha(float fontAlpha) {
        // TODO: Implement this method
        this.fontAlpha = fontAlpha;
    }

    @Override
    public float getFontAlpha() {
        // TODO: Implement this method
        return fontAlpha;
    }

    @Override
    public void setAttack(int attack) {
        // TODO: Implement this method
        this.attack = attack;
    }

    @Override
    public int getAttack() {
        // TODO: Implement this method
        return attack;
    }

    @Override
    public void setHP(int HP) {
        // TODO: Implement this method
        this.HP = HP;
        if (this.HP > maxHP) {
            this.HP = maxHP;
        }

    }

    @Override
    public int getHP() {
        // TODO: Implement this method
        return HP;
    }


    @Override
    public void attackRects() {
        // TODO: Implement this method
        rects.clear();

        rects.add(new MyRect(getX(), getY() + margin, margin, margin));
        rects.add(new MyRect(getX(), getY() - margin, margin, margin));
        rects.add(new MyRect(getX() - margin, getY(), margin, margin));
        rects.add(new MyRect(getX() + margin, getY(), margin, margin));

        //shape.setColor(1, 0, 0, .2f);
    }


    @Override
    public void moveRects() {
        // TODO: Implement this method
        rects.clear();

        rects.add(new MyRect(getX(), getY() + margin, margin, margin));
        rects.add(new MyRect(getX(), getY() - margin, margin, margin));
        rects.add(new MyRect(getX() - margin, getY(), margin, margin));
        rects.add(new MyRect(getX() + margin, getY(), margin, margin));

        //shape.setColor(1, 1, 1, .2f);
    }


    @Override
    public int getSpeed() {
        // TODO: Implement this method
        return speed;
    }

    @Override
    public void setPlayerState(stdPlayerState newPlayerState) {
        // TODO: Implement this method
        getPlayerState().exit(this, delta);
        this.actorState = newPlayerState;
        getPlayerState().enter(this, delta);
    }


    @Override
    public stdPlayerState getPlayerState() {
        // TODO: Implement this method
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
        //rectangle.set(getX(), getY(), getWidth(), getHeight());
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

        //if (getPlayerState() == stdPlayerState.WAITING_TO_MOVE) {

        //}

        return false;
    }

    @Override
    public void act(float delta) {
        // TODO: Implement this method
        getPlayerState().update(this, delta);

        super.act(delta);
    }


}
