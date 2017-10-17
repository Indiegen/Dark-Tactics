package com.indiegen.game;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

class Maps
{
	//Assests assests;
	Batch batch;
	private float delta=0;
	private TextureRegion tileRegion;
	private TextureRegion brickRegion;
	private TextureRegion grassRegion;
	private TextureRegion groundRegion;
	private TextureRegion doorRegion;

	private Animation torchAnimation;

	private int[][] map=	{
		{ 3 , 3 , 3 , 3 , 3 , 3 , 6 , 3 , 3 , 3 ,},
		{ 3 , 2 , 2 , 1 , 1 , 1 , 1 , 1 , 1 , 3 ,},
		{ 3 , 1 , 2 , 1 , 3 , 3 , 1 , 1 , 1 , 3 ,},
		{ 6 , 1 , 2 , 1 , 1 , 1 , 1 , 1 , 1 , 3 ,},
		{ 3 , 1 , 6 , 3 , 1 , 1 , 2 , 1 , 1 , 3 ,},
		{ 3 , 1 , 2 , 3 , 1 , 2 , 2 , 1 , 1 , 3 ,},
		{ 3 , 1 , 2 , 6 , 2 , 2 , 1 , 1 , 1 , 3 ,},
		{ 3 , 1 , 2 , 3 , 2 , 1 , 3 , 1 , 1 , 3 ,},
		{ 3 , 1 , 2 , 3 , 2 , 1 , 1 , 1 , 1 , 3 ,},
		{ 3 , 1 , 2 , 2 , 2 , 2 , 2 , 2 , 1 , 3 ,},
		{ 3 , 1 , 1 , 1 , 1 , 3 , 3 , 2 , 1 , 3 ,},
		{ 6 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 6 ,},
		{ 3 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 3 ,},
		{ 3 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 3 ,},
		{ 6 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 6 ,},
		{ 3 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 3 ,},
		{ 3 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 3 ,},
		{ 6 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 6 ,},
		{ 3 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 3 ,},
		{ 3 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 5 , 3 ,},
		{ 6 , 3 , 3 , 6 , 3 , 3 , 6 , 3 , 3 , 6 ,},
	};

	Maps(Assests assests)

	{
		TextureRegion[] torches = new TextureRegion[3];

		Texture tilesTexture = assests.tiles;
		int tileSize = 32;
		tileRegion = new TextureRegion(tilesTexture, tileSize * 19, tileSize * 6, tileSize, tileSize);
		brickRegion = new TextureRegion(tilesTexture, tileSize * 7, tileSize * 7, tileSize, tileSize);
		grassRegion = new TextureRegion(tilesTexture, tileSize * 1, tileSize * 1, tileSize, tileSize);
		groundRegion = new TextureRegion(tilesTexture, tileSize * 0, tileSize * 1, tileSize, tileSize);
		doorRegion = new TextureRegion(tilesTexture, tileSize * 5, tileSize * 19, tileSize, tileSize);
		torches[0]=(new TextureRegion(assests.torch, tileSize * 0, tileSize * 0, tileSize, tileSize));
		torches[1]=(new TextureRegion(assests.torch, tileSize * 1, tileSize * 0, tileSize, tileSize));
		torches[2]=(new TextureRegion(assests.torch, tileSize * 0, tileSize * 1, tileSize, tileSize));
		
		torchAnimation = new Animation(0.2f, torches);
		torchAnimation.setPlayMode(Animation.PlayMode.LOOP);
		
	}

	int[][] getMap()
	{
		return map;
	}

	void drawMap(Batch batch, BitmapFont font, String text)
	{
		this.batch = batch;
		batch.begin();
		delta += Gdx.graphics.getDeltaTime();

		for (int x = 0; x < map.length; x++)
		{
            for (int y = 0; y < map[y].length; y++)
			{
				int margen = 64;
				if (map[x][y] == 1)
				{
                    batch.draw(brickRegion, x * margen, y * margen, margen, margen);
                }
                if (map[x][y] == 2)
				{
                    batch.draw(grassRegion, x * margen, y * margen, margen, margen);
                }
                if (map[x][y] == 0)
				{
                    batch.draw(groundRegion, x * margen, y * margen, margen, margen);
                }
				if (map[x][y] == 3)
				{
                    batch.draw(tileRegion, x * margen, y * margen, margen, margen);
                }
				if (map[x][y] == 4)
				{
                    batch.draw(doorRegion, x * margen, y * margen, margen, margen);
                }
				if (map[x][y] == 5)
				{
                    batch.draw(doorRegion, x * margen, y * margen, margen, margen);
                }
				if (map[x][y] == 6)
				{
                    batch.draw(tileRegion, x * margen, y * margen, margen, margen);
					
					batch.draw(torchAnimation.getKeyFrame(delta,true), x * margen, y * margen, margen, margen);
				}
				
            }
		}

		font.setColor(Color.RED);
		//font.setScale(.5f);
		font.draw(batch, text, 10, 13);
		//text = "";

		batch.end();
	}
}
