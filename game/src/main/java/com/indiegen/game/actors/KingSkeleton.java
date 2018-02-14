package com.indiegen.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.indiegen.game.enums.GamePlayerState;
import com.indiegen.game.utils.AssetsManager;
import com.indiegen.game.utils.Aura;
import com.indiegen.game.utils.Light;
import com.indiegen.game.utils.RectangleUtils;

public class KingSkeleton extends GameEnemy
{

    Light aura;

    public KingSkeleton(Texture texture, int x, int y, String name){
		super(texture,1,2,"ey");

        setHP(90);
        setMaxHP(90);
        this.setTexture(texture);
        setName("Skeleton " + name);

        int attack = 10;

        int margin = 64;


        BoundingBox boundingBox = new BoundingBox();
        BitmapFont font = new BitmapFont();
        setRectangle(new Rectangle(getX(), getY(), margin, margin));

        setDir(1);

		//Walk
        setWalkFrames(new TextureRegion[4]);

        getWalkFrames()[0] = new TextureRegion(texture, 0, 32, 32, 32);
        getWalkFrames()[1] = new TextureRegion(texture, 32, 32, 32, 32);
        getWalkFrames()[2] = new TextureRegion(texture, 64, 32, 32, 32);
        getWalkFrames()[3] = new TextureRegion(texture, 96, 32, 32, 32);

        setWalkAnimation(new Animation(0.08f, getWalkFrames()));
        getWalkAnimation().setPlayMode(Animation.PlayMode.NORMAL);

		
		//Wait
        setWaitFrames(new TextureRegion[5]);

        getWaitFrames()[0] = new TextureRegion(texture, 0, 0, 32, 32);
        getWaitFrames()[1] = new TextureRegion(texture, 32, 0, 32, 32);
        getWaitFrames()[2] = new TextureRegion(texture, 64, 0, 32, 32);
		getWaitFrames()[3] = new TextureRegion(texture, 96, 0, 32, 32);
		getWaitFrames()[4] = new TextureRegion(texture, 128, 0, 32, 32);
		
        setWaitAnimation(new Animation(0.1f, getWaitFrames()));
        getWaitAnimation().setPlayMode(Animation.PlayMode.NORMAL);
		
		
		
		//Attack
        setAttackFrames(new TextureRegion[3]);

        getAttackFrames()[0] = new TextureRegion(texture, 0, 64, 44, 36);
        getAttackFrames()[1] = new TextureRegion(texture, 44, 64, 44, 36);
        getAttackFrames()[2] = new TextureRegion(texture, 88, 64, 44, 36);
		
        setAttackAnimation(new Animation(0.2f, getAttackFrames()));
        getAttackAnimation().setPlayMode(Animation.PlayMode.NORMAL);

		
		//Dead
		setDeadFrames(new TextureRegion[4]);
		
		getDeadFrames()[0] = new TextureRegion(texture, 0, 132, 32, 32);
        getDeadFrames()[1] = new TextureRegion(texture, 32, 132, 32, 32);
        getDeadFrames()[2] = new TextureRegion(texture, 64, 132, 32, 32);
        getDeadFrames()[3] = new TextureRegion(texture, 96, 132, 32, 32);

		setDeadAnimation(new Animation(0.5f, getDeadFrames()));
        getDeadAnimation().setPlayMode(Animation.PlayMode.NORMAL);
		
        //Hit
        setHitFrames(new TextureRegion[4]);

        getHitFrames()[0] = new TextureRegion(texture, 0, 100, 32, 32);
        getHitFrames()[1] = new TextureRegion(texture, 32, 100, 32, 32);
        getHitFrames()[2] = new TextureRegion(texture, 64, 100, 32, 32);
        getHitFrames()[3] = new TextureRegion(texture, 96, 100, 32, 32);

        setHitAnimation(new Animation(0.4f, getHitFrames()));
        getHitAnimation().setPlayMode(Animation.PlayMode.NORMAL);

        //Sounds

        setWalkSound(AssetsManager.getKingWalkSound());
        setAttackSound(AssetsManager.getKingAttackSound());
        setDeathSound(AssetsManager.getKingDeathSound());



        setAnimation(0);

        TextureRegion currentFrame = getAnimation().getKeyFrame(getDelta(), true);
        setX(x);
        setY(y);
        setDelta(0f);
        ShapeRenderer shape = new ShapeRenderer();

        GamePlayerState actorState = GamePlayerState.WAITING;
        initRects();
        addRect(new RectangleUtils(getX(), getY(), margin, margin));
        setTurnTexture(new TextureRegion(texture, 132, 64, 16, 16));

        aura = new Aura(1000,1000,new Color(1,0,0,1));
	}

	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		// TODO: Implement this method
		super.draw(batch, parentAlpha);
		setDelta(getDelta() + Gdx.graphics.getDeltaTime());
	}

    @Override
    public void drawLights(Batch batch) {
        super.drawLights(batch);
        aura.draw(batch,this);
    }
}
