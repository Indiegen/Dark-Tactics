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

class stdEnemy extends MyActor implements stdActor {

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

                animation = walk;
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
        return dead;
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
    public void drawLabel(int hit) {
        // TODO: Implement this method
        setHP(getHP() - hit);
    }


    @Override
    public void setAttack(int attack) {
        // TODO: Implement this method
    }

    @Override
    public int getAttack() {
        // TODO: Implement this method
        return attack;
    }

    @Override
    public void setPlayerState(stdPlayerState newPlayerState) {
        // TODO: Implement this method
        actorState.exit(this, delta);
        this.actorState = newPlayerState;
        actorState.enter(this, delta);
    }

    @Override
    public stdPlayerState getPlayerState() {
        // TODO: Implement this method
        return actorState;
    }


    @Override
    public void setHP(int HP) {
        // TODO: Implement this method
        this.HP = HP;
    }

    @Override
    public int getHP() {
        // TODO: Implement this method
        return HP;
    }


    @Override
    public void moveRects() {
        // TODO: Implement this method
        rects.clear();

        rects.add(new MyRect(getX(), getY() + margin, margin, margin));
        rects.add(new MyRect(getX(), getY() - margin, margin, margin));
        rects.add(new MyRect(getX() - margin, getY(), margin, margin));
        rects.add(new MyRect(getX() + margin, getY(), margin, margin));

    }

    @Override
    public void attackRects() {
        // TODO: Implement this method
        rects.clear();

        rects.add(new MyRect(getX(), getY() + margin, margin, margin));
        rects.add(new MyRect(getX(), getY() - margin, margin, margin));
        rects.add(new MyRect(getX() - margin, getY(), margin, margin));
        rects.add(new MyRect(getX() + margin, getY(), margin, margin));

    }

    @Override
    public void setX(float x) {
        // TODO: Implement this method
        super.setX(x);

    }

    @Override
    public void setY(float y) {
        // TODO: Implement this method
        super.setY(y);
    }


    @Override
    public int getSpeed() {
        // TODO: Implement this method
        return speed;
    }

    Texture texture;
    TextureRegion[] walkFrames;
    TextureRegion[] attackFrames;
    TextureRegion[] waitFrames;
    TextureRegion currentFrame;
    TextureRegion turnTexture;
    Animation walk;
    Animation attackAnimation;
    Animation waitAnimation;
    Animation animation;
    BoundingBox boundingBox;
    Rectangle rectangle;
    Rectangle rectUp;
    Rectangle rectDown;
    Rectangle rectLeft;
    Rectangle rectRight;
    //int margin;
    int velX = 0;
    Color color;
    int FRAME_COLS = 3;
    int FRAME_ROWS = 1;
    float delta = 0;
    int dir;
    boolean flipX = false;
    boolean flipY = false;
    int state = 0;
    ShapeRenderer shape;
    float curX;
    float curY;
    int HP;
    int maxHP;
    boolean dead = false;
    int fatigue = 0;

    stdPlayerState actorState;
    boolean acting = false;
    int attack;
    BitmapFont font;

    public stdEnemy(Texture settexture) {
        setHP(20);
        maxHP = 80;
        texture = settexture;


        attack = 10;
        rects = new ArrayList<MyRect>();

        //margin = 64;
        setWidth(margin);
        setHeight(margin);

        boundingBox = new BoundingBox();
        font = new BitmapFont();
        rectangle = new Rectangle(getX(), getY(), margin, margin);
//		rectUp = new Rectangle(getX(), getY() + margin, margin, margin);
//		rectDown = new Rectangle(getX(), getY() - margin, margin, margin);
//		rectLeft = new Rectangle(getX() - margin, getY(), margin, margin);
//		rectRight = new Rectangle(getX() + margin, getY(), margin, margin);
////
//		walkFrames = new TextureRegion[4];
//
//		walkFrames[0] = new TextureRegion(texture, 0, 37, 33, 28);
//        walkFrames[1] = new TextureRegion(texture, 33, 37, 33, 28);
//		walkFrames[2] = new TextureRegion(texture, 66, 37, 33, 28);
//		walkFrames[3] = new TextureRegion(texture, 33, 37, 33, 28);
//
//		walkLeft = new Animation(0.08f, walkFrames);
//		walkFramesR = new TextureRegion[4];
//		walkFramesR[0] = new TextureRegion(texture, 0, 69, 33, 28);
//        walkFramesR[1] = new TextureRegion(texture, 30, 69, 33, 28);
//		walkFramesR[2] = new TextureRegion(texture, 60, 69, 33, 28);
//		walkFramesR[3] = new TextureRegion(texture, 30, 69, 33, 28);
//		walkRight = new Animation(0.08f, walkFramesR);      // #11

        currentFrame = new TextureRegion(texture, 0, 0, 60, 60);
        setX(0);
        setY(0);
        delta = 0f;
        shape = new ShapeRenderer();

        //playerState = stdPlayerState.WAITING;
        actorState = stdPlayerState.WAITING;
        rects.add(new MyRect(getX(), getY(), margin, margin));
        //setTurnTexture(waitFrames[0]);

    }

    public stdEnemy(Texture settexture, int x, int y, String name) {
        setHP(20);
        maxHP = 80;
        texture = settexture;
        setName("Skeleton " + name);

        attack = 10;
        rects = new ArrayList<MyRect>();

        int margen = 64;
        setWidth(margen * (texture.getWidth() / texture.getHeight()));
        setHeight(margen);

        boundingBox = new BoundingBox();
        font = new BitmapFont();
        rectangle = new Rectangle(getX(), getY(), margen, margen);
//		rectUp = new Rectangle(getX(), getY() + margin, margin, margin);
//		rectDown = new Rectangle(getX(), getY() - margin, margin, margin);
//		rectLeft = new Rectangle(getX() - margin, getY(), margin, margin);
//		rectRight = new Rectangle(getX() + margin, getY(), margin, margin);
//
        dir = 1;

        walkFrames = new TextureRegion[4];


        walkFrames[0] = new TextureRegion(texture, 0, 32, 32, 32);
        walkFrames[1] = new TextureRegion(texture, 32, 32, 32, 32);
        walkFrames[2] = new TextureRegion(texture, 64, 32, 32, 32);
        walkFrames[3] = new TextureRegion(texture, 96, 32, 32, 32);
        //walkFrames[4] = new TextureRegion(texture, 32+25, 32+68, 32, 32);
        //walkFrames[6] = new TextureRegion(texture, 64+25, 32+68, 32, 32);
        //walkFrames[5] = new TextureRegion(texture, 64+24, 32+68, 32, 32);

        walk = new Animation(0.2f, walkFrames);
        walk.setPlayMode(Animation.PlayMode.NORMAL);

        waitFrames = new TextureRegion[3];

        //waitFrames[0] = new TextureRegion(texture, 23, 68, 32, 32);
        waitFrames[0] = new TextureRegion(texture, 0, 0, 32, 32);
        waitFrames[1] = new TextureRegion(texture, 0, 64, 32, 32);
        waitFrames[2] = new TextureRegion(texture, 32, 64, 32, 32);
        //walkFrames[1] = new TextureRegion(texture, 32, 32, 32, 32);


        //attackFrames[1] = new TextureRegion(texture, 6, 124, 42, 60);

        waitAnimation = new Animation(0.8f, waitFrames);
        waitAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        //currentFrame.flip(true, false);

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


        //playerState = stdPlayerState.WAITING;
        actorState = stdPlayerState.WAITING;
        rects.add(new MyRect(getX(), getY(), margen, margen));
        setTurnTexture(new TextureRegion(texture, 64, 64, 16, 16));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        // TODO: Implement this method
        delta += Gdx.graphics.getDeltaTime();           // #15


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


//		if (getState() == 0)
//		{
//			batch.end();
//
//			rectangle.set(getX(), getY(), margin, margin);
//			rectUp.set(getX(), getY() + margin, margin, margin);
//			rectDown.set(getX(), getY() - margin, margin, margin);
//			rectLeft.set(getX() - margin, getY(), margin, margin);
//			rectRight.set(getX() + margin, getY(), margin, margin);
//
//			shape.setProjectionMatrix(batch.getProjectionMatrix());
//
//			Gdx.gl.glEnable(GL20.GL_BLEND);
//			//Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
//			shape.begin(ShapeType.Filled);
//			shape.setColor(1, 1, 1, .2f);
//
//			drawRect(rectUp);
//			drawRect(rectDown);
//			drawRect(rectLeft);
//			drawRect(rectRight);
//
//			shape.end();
//			Gdx.gl.glDisable(GL20.GL_BLEND);
//			batch.begin();
//		}

//		switch (getPlayerState())
//		{ 
//			case MOVING:
//				setAnimation(walk);
//				currentFrame = getAnimatin().getKeyFrame(delta, true);
//				break;
//
//			case ATTACKING:
//				setAnimation(attackAnimation);
//				currentFrame = getAnimatin().getKeyFrame(delta, true);
//				break;
//			default:
//				currentFrame = walkFrames[0];
//				delta = 0;
//
//
//		}

        currentFrame = getAnimation().getKeyFrame(delta, true);
        if (dir == 0 && !currentFrame.isFlipX()) {
            currentFrame.flip(true, false);

        }
        if (dir == 1 && currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        }

        setWidth(margin);
        setHeight(margin);
        //currentFrame=walkFrames[1];
        //batch.setColor(getX(),getY(),getWidth(),getHeight());
        //batch.draw(currentFrame, getX(), getY(), getWidth()/2, getHeight()/2 , getWidth() * 3, getHeight() * 3, getScaleX(), getScaleY(), getRotation());

        setRectangle(new Rectangle(getX(), getY(), margin, margin));

        batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        batch.setColor(Color.WHITE);
        //batch.draw(texture, getX(), getY(), 0, 0 , getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation(), (int)getWidth() * 0, (int)getHeight() * 0, texture.getWidth(), texture.getHeight(), getFlipX(), getFlipY());
        //batch.draw(texture, getX(), getY(), getWidth()/3,getHeight()/3);

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

    @Override
    public void setRotation(float degrees) {
        // TODO: Implement this method
        super.setRotation(degrees);
    }

    @Override
    public float getRotation() {
        // TODO: Implement this method
        return super.getRotation();
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
        //rectangle.set(getX(), getY(), getWidth(), getHeight());
        return rectangle;
    }

    public boolean drawRect(MyRect rect) {
        shape.setColor(rect.getColor());
        shape.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

        return false;
    }

    @Override
    public void setPosition(float x, float y) {
        // TODO: Implement this method
        super.setPosition(x, y);
    }

    @Override
    public void act(float delta) {
        // TODO: Implement this method
        actorState.update(this, delta);
        super.act(delta);
    }

    @Override
    public void scaleBy(float scale) {
        // TODO: Implement this method


        super.scaleBy(scale);
        //rectangle.set(getX(),getY(),getWidth(),getHeight());
    }

    public boolean isTouched(float x, float y) {

//		if (state == 0)
//		{
//
//		}
//		if (rectangle.contains(x, y) && state == 0)
//		{
//
//			setState(1);
//			return true;
//		}
//		if (rectUp.contains(x, y) && state == 0)
//		{
//
//			setCurX(getX());
//			setCurY(getY() + margin);
//			setState(2);
//			return true;
//		}
//		if (rectDown.contains(x, y) && state == 0)
//		{
//			setCurX(getX());
//			setCurY(getY() - margin);
//			setState(3);
//			return true;
//		}
//		if (rectLeft.contains(x, y) && state == 0)
//		{
//			setCurX(getX() - margin);
//			setCurY(getY());
//			setState(4);
//			return true;
//		}
//		if (rectRight.contains(x, y) && state == 0)
//		{
//			setCurX(getX() + margin);
//			setCurY(getY());
//			setState(5);
//			return true;
//		}

        return false;
    }


}
