package com.indiegen.game;

import android.os.*;
import com.badlogic.gdx.backends.android.*;



public class Game extends AndroidApplication{
	DarkTactics initgame;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		
		
		initgame = new DarkTactics();
		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();

		initialize(initgame,cfg);
		
	}

	@Override
	protected void onDestroy()
	{
		// TODO: Implement this method
		//initgame= null;
		super.onDestroy();
	}



	
	
}
