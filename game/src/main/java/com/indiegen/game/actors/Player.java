package com.indiegen.game.Actors;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.collision.*;
import com.indiegen.game.enums.*;
import com.indiegen.game.utils.*;

public class Player extends CustomActor
{


    private TextureRegion currentFrame;

    private BoundingBox boundingBox;

    private final int margin = 64;
    private int velX = 0;
    private Color color;
    private int dir;
    private boolean flipX = false;
    private boolean flipY = false;
    private int state = 0;
    private ShapeRenderer shape;
    private float curX;
    private float curY;
    private GamePlayerState actorState;
    private int potions = 1;
    private int attack;
    private int defence=0;

    public Boolean isActing()
	{
        return false;
    }

    public Player(Texture texture)
	{
        boundingBox = new BoundingBox();

        int maxHP = 80;
        setHP(maxHP);
        setAttack(getATTACK());

        setName("player");
        setPosition(margin * 3, margin * 3);
        setWidth(margin);
        attack = 100;
        setHeight(margin);
        setRectangle(new Rectangle(getX(), getY(), margin, margin));

        setAttackFrames(new TextureRegion[7]);
        getAttackFrames()[0] = new TextureRegion(texture, 0, 0, 32, 32);
        getAttackFrames()[1] = new TextureRegion(texture, 32, 0, 32, 32);
        getAttackFrames()[2] = new TextureRegion(texture, 64, 0, 32, 32);
        getAttackFrames()[3] = new TextureRegion(texture, 96, 0, 32, 32);
        getAttackFrames()[4] = new TextureRegion(texture, 32, 32, 32, 32);
        getAttackFrames()[6] = new TextureRegion(texture, 64, 32, 32, 32);
        getAttackFrames()[5] = new TextureRegion(texture, 64, 32, 32, 32);

        setAttackAnimation(new Animation(.08f,getAttackFrames()));

        TextureRegion[] waitFrames = new TextureRegion[2];

        waitFrames[0] = new TextureRegion(texture, 0, 0, 32, 32);
        waitFrames[1] = new TextureRegion(texture, 96, 32, 32, 32);

        setWaitAnimation(new Animation(0.8f, waitFrames));

        TextureRegion[] walkFrames = new TextureRegion[2];

        walkFrames[0] = new TextureRegion(texture, 32, 64, 32, 32);
        walkFrames[1] = new TextureRegion(texture, 32, 64, 32, 32);

        setWalkAnimation(new Animation(0.8f, walkFrames));

        setGuardFrames(new TextureRegion[1]);

        getGuardFrames()[0] = new TextureRegion(texture,98,64,32,32);

        setGuardAnimation( new Animation(0.8f, getGuardFrames()));

//DEAD

        setDeadFrames(new TextureRegion[4]);

        getDeadFrames()[0] = new TextureRegion(texture, 0, 132, 32, 32);
        getDeadFrames()[1] = new TextureRegion(texture, 32, 132, 32, 32);
        getDeadFrames()[2] = new TextureRegion(texture, 64, 132, 32, 32);
        getDeadFrames()[3] = new TextureRegion(texture, 96, 132, 32, 32);

        setDeadAnimation(new Animation(0.4f, getDeadFrames()));
        getDeadAnimation().setPlayMode(Animation.PlayMode.NORMAL);

        //Hit
        setHitFrames(new TextureRegion[4]);

        getHitFrames()[0] = new TextureRegion(texture, 0, 0, 32, 32);
        getHitFrames()[1] = new TextureRegion(texture, 32, 0, 32, 32);
        getHitFrames()[2] = new TextureRegion(texture, 64, 0, 32, 32);
        getHitFrames()[3] = new TextureRegion(texture, 96, 0, 32, 32);

        setHitAnimation(new Animation(0.4f, waitFrames));//////////////ADD HIT ANIMATION
        getHitAnimation().setPlayMode(Animation.PlayMode.NORMAL);
		
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
    public void draw(Batch batch, float parentAlpha)
	{
        super.draw(batch, parentAlpha);
        setDelta(getDelta() + Gdx.graphics.getDeltaTime());
        batch.setColor(Color.WHITE);

        if (getPlayerState() == GamePlayerState.BEING_HITTING)
		{
            batch.setColor(1, 1 - super.getFontAlpha(), 1 - super.getFontAlpha(), 1);
            font.setColor(1, 0, 0, super.getFontAlpha());
            //font.getData().scale(1f);
            font.draw(batch, -getDamage() + " HP", getX(), getY() + margin + margin * (1 - super.getFontAlpha()) / 2);
        }

        if (getPlayerState() == GamePlayerState.ITEM)
		{
            batch.setColor(1 - super.getFontAlpha(), 1, 1 - super.getFontAlpha(), 1);
            font.setColor(0, 1, 0, super.getFontAlpha());
            //font.scale(1f);
            font.draw(batch, "+40 " + " HP", getX(), getY() + margin + margin * (1 - super.getFontAlpha()) / 2);
        }


        if (getPlayerState() == GamePlayerState.WAITING_TO_MOVE || getPlayerState() == GamePlayerState.ATTACK_TARGETING||true)
		{
            batch.end();
            Gdx.gl.glEnable(GL20.GL_BLEND);
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setProjectionMatrix(batch.getProjectionMatrix());

            for (RectangleUtils rect : super.getRects())
			{
                drawRect(rect);
            }
            shape.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
            batch.begin();
        }

        currentFrame = getAnimation().getKeyFrame(getDelta(), true);
        if (dir == 0 && !currentFrame.isFlipX())
		{
            currentFrame.flip(true, false);

        }
        if (dir == 1 && currentFrame.isFlipX())
		{
            currentFrame.flip(true, false);
        }

        setRectangle(new Rectangle(getX(), getY(), margin, margin));
        batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

    }

    @Override
    public int getDefence() {
        return defence;
    }


    @Override
    public void attackRects()
	{

        super.clearRects();

        super.addRect(new RectangleUtils(getX(), getY() + margin, margin, margin));
        super.addRect(new RectangleUtils(getX(), getY() - margin, margin, margin));
        super.addRect(new RectangleUtils(getX() - margin, getY(), margin, margin));
        super.addRect(new RectangleUtils(getX() + margin, getY(), margin, margin));

    }

    @Override
    public void moveRects()
	{

        super.clearRects();

        super.addRect(new RectangleUtils(getX(), getY() + margin, margin, margin));
        super.addRect(new RectangleUtils(getX(), getY() - margin, margin, margin));
        super.addRect(new RectangleUtils(getX() - margin, getY(), margin, margin));
        super.addRect(new RectangleUtils(getX() + margin, getY(), margin, margin));

    }



    @Override
    public void setPlayerState(GamePlayerState newPlayerState)
	{

        getPlayerState().exit(this, getDelta());
        this.actorState = newPlayerState;
        getPlayerState().enter(this, getDelta());
    }

    @Override
    public GamePlayerState getPlayerState()
	{

        return actorState;
    }

    @Override
    public void setCurY(float curY)
	{
        this.curY = curY;
    }

    @Override
    public float getCurY()
	{
        return curY;
    }

    @Override
    public void setCurX(float curX)
	{
        this.curX = curX;
    }

    @Override
    public float getCurX()
	{
        return curX;
    }


    @Override
    public void setDir(int dir)
	{
        this.dir = dir;
    }

    @Override
    public int getDir()
	{
        return dir;
    }

    @Override
    public void setColor(Color color)
	{
        this.color = color;
    }

    @Override
    public Color getColor()
	{
        return color;
    }


    @Override
    public boolean drawRect(RectangleUtils rect)
	{
        shape.setColor(rect.getColor());
        shape.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        return false;
    }



    @Override
    public void act(float delta)
	{
        getPlayerState().update(this, delta);
        super.act(delta);
    }

    public int getATTACK()
	{
        return 50;
    }

    public int getWALK()
	{
        return 20;
    }

    public int getGUARD()
	{
        return 20;
    }

    public void setPotions(int potions)
	{
        this.potions = potions;
    }

    public int getPotions()
	{
        return potions;
    }

}
	
