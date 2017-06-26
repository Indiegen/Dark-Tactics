package com.indiegen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.scenes.scene2d.Actor;

class stdCharacter extends Actor {
    Texture texture;
    TextureRegion[] walkFramesL;
    TextureRegion[] walkFramesR;
    TextureRegion currentFrame;
    Animation walkLeft;
    Animation walkRight;
    BoundingBox boundingBox;
    Rectangle rectangle;
    Rectangle rectUp;
    Rectangle rectDown;
    Rectangle rectLeft;
    Rectangle rectRight;
    int margen = 64;
    int velX = 0;
    Color color;
    int FRAME_COLS = 3;
    int FRAME_ROWS = 1;
    float stateTime = 0;
    int dir;
    boolean flipX = false;
    boolean flipY = false;
    int state = 0;
    ShapeRenderer shape;
    float curX;
    float curY;


    public stdCharacter(Texture settexture) {
        texture = settexture;
        setX(0);
        setY(0);

        setWidth(texture.getWidth());
        setHeight(texture.getHeight());

        boundingBox = new BoundingBox();

        rectangle = new Rectangle(getX(), getY(), margen, margen);
        rectUp = new Rectangle(getX(), getY() + margen, margen, margen);
        rectDown = new Rectangle(getX(), getY() - margen, margen, margen);
        rectLeft = new Rectangle(getX() - margen, getY(), margen, margen);
        rectRight = new Rectangle(getX() + margen, getY(), margen, margen);

//		walkFramesL = new TextureRegion[4];
//
//		walkFramesL[0] = new TextureRegion(texture, 0, 37, 33, 28);
//        walkFramesL[1] = new TextureRegion(texture, 33, 37, 33, 28);
//		walkFramesL[2] = new TextureRegion(texture, 66, 37, 33, 28);
//		walkFramesL[3] = new TextureRegion(texture, 33, 37, 33, 28);
//
//		walkLeft = new Animation(0.08f, walkFramesL);
//		walkFramesR = new TextureRegion[4];
//		walkFramesR[0] = new TextureRegion(texture, 0, 69, 33, 28);
//        walkFramesR[1] = new TextureRegion(texture, 30, 69, 33, 28);
//		walkFramesR[2] = new TextureRegion(texture, 60, 69, 33, 28);
//		walkFramesR[3] = new TextureRegion(texture, 30, 69, 33, 28);
//		walkRight = new Animation(0.08f, walkFramesR);      // #11
//
        currentFrame = new TextureRegion(texture);
        stateTime = 0f;
        shape = new ShapeRenderer();
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

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Rectangle getRectangle() {
        //rectangle.set(getX(), getY(), getWidth(), getHeight());
        return rectangle;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        // TODO: Implement this method
        stateTime += Gdx.graphics.getDeltaTime();           // #15

		/*
         switch (dir)
		 {

		 case 1:
		 currentFrame = walkLeft.getKeyFrame(stateTime, true);
		 break;
		 case 0:
		 currentFrame = walkRight.getKeyFrame(stateTime, true);
		 break;
		 }
		 //currentFrame=walkFrames[1];
		 //batch.setColor(getX(),getY(),getWidth(),getHeight());
		 batch.draw(currentFrame, getX(), getY(), getWidth()/2, getHeight()/2 , getWidth() * 3, getHeight() * 3, getScaleX(), getScaleY(), getRotation());
		 */
        //batch.draw(currentFrame, getX(), getY(), getWidth()/2, getHeight()/2 , getWidth() , getHeight(), getScaleX(), getScaleY(), getRotation());
        batch.draw(texture, getX(), getY(), 0, 0, getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation(), (int) getWidth() * 0, (int) getHeight() * 0, texture.getWidth(), texture.getHeight(), getFlipX(), getFlipY());
        //batch.draw(texture, getX(), getY(), getWidth()/3,getHeight()/3);
        batch.end();

        rectangle.set(getX(), getY(), margen, margen);
        rectUp.set(getX(), getY() + margen, margen, margen);
        rectDown.set(getX(), getY() - margen, margen, margen);
        rectLeft.set(getX() - margen, getY(), margen, margen);
        rectRight.set(getX() + margen, getY(), margen, margen);

        shape.setProjectionMatrix(batch.getProjectionMatrix());

        Gdx.gl.glEnable(GL20.GL_BLEND);
        //Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(1, 1, 1, .5f);


        shape.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        batch.begin();

    }

    boolean drawRect(Rectangle rect) {

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
        switch (getState()) {

            case 1:
                if (getY() < curY) {
                    //setY(getY()+54*delta);
                } else {
                    setState(0);
                }
                break;

            case 2:
                if (getY() < curY) {
                    setY(getY() + margen * delta);
                } else {
                    setY(curY);
                    setState(0);
                }
                break;
            case 3:
                if (getY() > curY) {
                    setY(getY() - margen * delta);
                } else {
                    setY(curY);
                    setState(0);
                }
                break;
            case 4:
                if (getX() > curX) {
                    setX(getX() - margen * delta);
                } else {
                    setX(curX);
                    setState(0);
                }
                break;
            case 5:
                if (getX() < curX) {
                    setX(getX() + margen * delta);
                } else {
                    setX(curX);
                    setState(0);
                }
                break;


        }

        super.act(delta);
    }

    @Override
    public void scaleBy(float scale) {
        // TODO: Implement this method


        super.scaleBy(scale);
        //rectangle.set(getX(),getY(),getWidth(),getHeight());
    }

    public boolean isTouched(float x, float y) {

        if (state == 0) {

        }
        if (rectangle.contains(x, y) && state == 0) {

            setState(1);
            return true;
        }
        if (rectUp.contains(x, y) && state == 0) {

            setCurX(getX());
            setCurY(getY() + margen);
            setState(2);
            return true;
        }
        if (rectDown.contains(x, y) && state == 0) {
            setCurX(getX());
            setCurY(getY() - margen);
            setState(3);
            return true;
        }
        if (rectLeft.contains(x, y) && state == 0) {
            setCurX(getX() - margen);
            setCurY(getY());
            setState(4);
            return true;
        }
        if (rectRight.contains(x, y) && state == 0) {
            setCurX(getX() + margen);
            setCurY(getY());
            setState(5);
            return true;
        }

        return false;
    }


}
