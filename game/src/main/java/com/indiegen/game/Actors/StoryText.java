package com.indiegen.game.Actors;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.Align;

public class StoryText extends Actor
{
    Texture background;
    Texture moon;
    Texture column;
    Texture clouds;
    Texture stars;
    Texture icon;
    Texture startGame;
    Texture logoText;
    String text1="Esta es la historia del juego empieza con oliver en su cuarto despertando por el reloj despertador despues";



    TextureRegion[] starsFrames;
    TextureRegion cloud1;
    TextureRegion cloud2;
    Animation twinkle;
    float cloud1X=-50;
    float cloud2X=0;
    float cloud3X=-130;
    float cloud4X=100;

    float delta=0;

    public BitmapFont font;
    public StoryText()
    {
        background = new Texture(Gdx.files.internal("titleScreen.png"));
        stars = new Texture(Gdx.files.internal("stars.png"));
        moon = new Texture(Gdx.files.internal("moon.png"));
        clouds = new Texture(Gdx.files.internal("clouds.png"));
        column = new Texture(Gdx.files.internal("column.png"));
        logoText = new Texture(Gdx.files.internal("LogoText.jpg"));
        icon = new Texture(Gdx.files.internal("icon.jpeg"));

        starsFrames = new TextureRegion[4];
        starsFrames[0] = new TextureRegion(stars,0,0,7,7);
        starsFrames[1] =new TextureRegion(stars,7,0,7,7);
        starsFrames[2] =new TextureRegion(stars,14,0,7,7);
        starsFrames[3] =new TextureRegion(stars,21,0,7,7);

        cloud1 = new TextureRegion(clouds,0,0,83,30);
        cloud2 = new TextureRegion(clouds,83,30,80,30);

        twinkle = new Animation(.3f, starsFrames);
        twinkle.setPlayMode(Animation.PlayMode.LOOP);
        setX(0);
        setY(0);

        font = new BitmapFont();
        font.getData().setScale(1f);

    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        // TODO: Implement this method
        //delta += Gdx.graphics.getDeltaTime();

        batch.draw(background,0,0,background.getWidth(),background.getHeight());
        batch.draw(twinkle.getKeyFrame(delta),50,250);

        batch.draw(twinkle.getKeyFrame(delta+2),100,270);
        batch.draw(twinkle.getKeyFrame(delta+1),15,300);
        batch.draw(twinkle.getKeyFrame(delta+3),100,270);
        batch.draw(twinkle.getKeyFrame(delta+4),170,310);
        batch.draw(twinkle.getKeyFrame(delta+3),110,200);
        batch.draw(twinkle.getKeyFrame(delta+4),30,220);
        //batch.draw(stars,getX(),getY(),stars.getWidth(),stars.getHeight());
        batch.draw(moon,110,(int)(getY()+250+delta*0.2f),moon.getWidth(),moon.getHeight());

        if(cloud1X>120)
            cloud1X=-85;

        if(cloud2X>120)
            cloud2X=-85;

        if(cloud3X>180)
            cloud3X=-85;

        if(cloud4X>185)
            cloud4X=-85;

        batch.draw(cloud1,getX()+cloud1X,getY()+200);
        batch.draw(cloud2,getX()+cloud2X,getY()+250);

        batch.draw(cloud1,getX()+cloud3X,getY()+220);
        batch.draw(cloud2,getX()+cloud4X,getY()+280);


        batch.draw(column,143,176,column.getWidth(),column.getHeight());

        //batch.draw(logoText,180/2-logoText.getWidth()/20,150,logoText.getWidth()/10,logoText.getHeight()/10);
        //batch.draw(icon,180/2-icon.getWidth()/20,190,icon.getWidth()/10,icon.getHeight()/10);

        font.draw(batch,text1,5,(int)(delta*10),180, Align.topLeft,true);
        batch.end();



        batch.begin();

        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta)
    {
        // TODO: Implement this method

        cloud1X+=delta*3;
        cloud2X+=delta*1;
        cloud3X+=delta*2;
        cloud4X+=delta/2;
        this.delta+=delta;
        super.act(delta);
    }

}
