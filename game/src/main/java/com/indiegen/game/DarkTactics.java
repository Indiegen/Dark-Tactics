package com.indiegen.game;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;


import com.badlogic.gdx.Game;

public class DarkTactics extends Game implements ApplicationListener
{

	Batch batch;
    
	
	@Override
	public void create()
	{
		batch = new SpriteBatch();
	    //world = new World(new Vector2(1,1),true);
		titleScreens screen1 = new titleScreens(this, batch);
		setScreen(screen1);

	}

	@Override
	public void dispose()
	{

		batch.dispose();
		
		super.dispose();
		//screen1.dispose();
		
		
		
		
	}
	
}
