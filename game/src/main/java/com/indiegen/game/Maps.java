package com.indiegen.game;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.indiegen.game.utils.AssetsManager;

public class Maps
{
	//Assests assests;
	Batch batch;
	int margen = 64;
	int tileSize= 32;
	float delta=0;
	TextureRegion tileRegion;
	TextureRegion brickRegion;
	TextureRegion grassRegion;
	TextureRegion groundRegion;
	TextureRegion doorRegion;
	TextureRegion torchRegion;
	TextureRegion tallGrass;


	TextureRegion 	dirtyGrass0,dirtyGrass1,dirtyGrass2,
			dirtyGrass3,dirtyGrass4,dirtyGrass5,
			dirtyGrass6,dirtyGrass7,dirtyGrass8;

	TextureRegion 	wall0, wall1, wall2,
			wall3, wall4, wall5,
			wall6, wall7, wall8;


	Texture tilesTexture;



	TextureRegion[] torches;

	Animation torchAnimation;



	public int[][] map=	{
			{ 34 , 35 , 35 , 6 , 35 , 35 , 6 , 35 , 35 , 34 ,},
			{ 31 , 26 , 23 , 23 , 23 , 20 , 1  , 1  , 1  , 37 ,},
			{ 31 , 27 , 24 , 24 , 24 , 21 , 1  , 90 , 1  , 37 ,},
			{ 6  , 27 , 25 , 25 , 25 , 22 , 1  , 1  , 1  , 37 ,},
			{ 31 , 27 , 6  , 36 , 33 , 33 , 30 , 90 , 1  , 37 ,},
			{ 31 , 27 , 34 , 37 , 34 , 34 , 31 , 1  , 90 , 37 ,},
			{ 31 , 27 , 6  , 38 , 35 , 35 , 32 , 1  , 1  , 37 ,},
			{ 31 , 27 , 23 , 23 , 23 , 23 , 23 , 90 , 1  , 37 ,},
			{ 31 , 27 , 1  , 1  , 1  , 1  , 1 , 1 , 1 , 3 ,},
			{ 31 , 27 , 1  , 1  , 1  , 1  , 2 , 2 , 1 , 3 ,},
			{ 31 , 27 , 1  , 1  , 1  , 1  , 1 , 2 , 1 , 3 ,},
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

	public Maps()

	{
		torches = new TextureRegion[3];


		tilesTexture = AssetsManager.getTileTexture();
		tileRegion = new TextureRegion(tilesTexture, tileSize * 20, tileSize * 9, tileSize, tileSize);

		dirtyGrass0 = new TextureRegion(tilesTexture, tileSize * 6, tileSize * 6, tileSize, tileSize);
		dirtyGrass1 = new TextureRegion(tilesTexture, tileSize * 7, tileSize * 6, tileSize, tileSize);
		dirtyGrass2 = new TextureRegion(tilesTexture, tileSize * 8, tileSize * 6, tileSize, tileSize);
		dirtyGrass3 = new TextureRegion(tilesTexture, tileSize * 6, tileSize * 7, tileSize, tileSize);
		dirtyGrass4 = new TextureRegion(tilesTexture, tileSize * 7, tileSize * 7, tileSize, tileSize);
		dirtyGrass5 = new TextureRegion(tilesTexture, tileSize * 8, tileSize * 7, tileSize, tileSize);
		dirtyGrass6 = new TextureRegion(tilesTexture, tileSize * 6, tileSize * 8, tileSize, tileSize);
		dirtyGrass7 = new TextureRegion(tilesTexture, tileSize * 7, tileSize * 8, tileSize, tileSize);
		dirtyGrass8 = new TextureRegion(tilesTexture, tileSize * 8, tileSize * 8, tileSize, tileSize);

		wall0 = new TextureRegion(tilesTexture, tileSize * 18, tileSize * 8, tileSize, tileSize);
		wall1 = new TextureRegion(tilesTexture, tileSize * 19, tileSize * 8, tileSize, tileSize);
		wall2 = new TextureRegion(tilesTexture, tileSize * 20, tileSize * 8, tileSize, tileSize);
		wall3 = new TextureRegion(tilesTexture, tileSize * 18, tileSize * 9, tileSize, tileSize);
		wall4 = new TextureRegion(tilesTexture, tileSize * 19, tileSize * 9, tileSize, tileSize);
		wall5 = new TextureRegion(tilesTexture, tileSize * 20, tileSize * 9, tileSize, tileSize);
		wall6 = new TextureRegion(tilesTexture, tileSize * 18, tileSize * 10, tileSize, tileSize);
		wall7 = new TextureRegion(tilesTexture, tileSize * 19, tileSize * 10, tileSize, tileSize);
		wall8 = new TextureRegion(tilesTexture, tileSize * 20, tileSize * 10, tileSize, tileSize);

		tallGrass = new TextureRegion(tilesTexture,tileSize*17,tileSize * 3, tileSize, tileSize);
		grassRegion = new TextureRegion(tilesTexture, tileSize * 1, tileSize * 1, tileSize, tileSize);
		groundRegion = new TextureRegion(tilesTexture, tileSize * 0, tileSize * 1, tileSize, tileSize);
		doorRegion = new TextureRegion(tilesTexture, tileSize * 5, tileSize * 19, tileSize, tileSize);
		torches[0]=(new TextureRegion(AssetsManager.getTorchTexture(), tileSize * 0, tileSize * 0, tileSize, tileSize));
		torches[1]=(new TextureRegion(AssetsManager.getTorchTexture(), tileSize * 1, tileSize * 0, tileSize, tileSize));
		torches[2]=(new TextureRegion(AssetsManager.getTorchTexture(), tileSize * 0, tileSize * 1, tileSize, tileSize));

		torchAnimation = new Animation(0.2f, torches);
		torchAnimation.setPlayMode(Animation.PlayMode.LOOP);

	}

	public void setMap(int[][] map)
	{
		this.map = map;
	}

	public int[][] getMap()
	{
		return map;
	}

	public void drawMap(Batch batch, BitmapFont font,String text)
	{
		this.batch = batch;
		batch.begin();
		delta += Gdx.graphics.getDeltaTime();

		for (int x = 0; x < map.length; x++)
		{
			for (int y = 0; y < map[y].length; y++)
			{
				if (map[x][y] == 1)
				{
					batch.draw(grassRegion, x * margen, y * margen, margen, margen);
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
					batch.draw(wall4, x * margen, y * margen, margen, margen);

					batch.draw(torchAnimation.getKeyFrame(delta,true), x * margen, y * margen, margen, margen);
				}

				if (map[x][y] == 20)
				{
					batch.draw(dirtyGrass0, x * margen, y * margen, margen, margen);

				}

				if (map[x][y] == 21)
				{
					batch.draw(dirtyGrass1, x * margen, y * margen, margen, margen);

				}

				if (map[x][y] == 22)
				{
					batch.draw(dirtyGrass2, x * margen, y * margen, margen, margen);

				}

				if (map[x][y] == 23)
				{
					batch.draw(dirtyGrass3, x * margen, y * margen, margen, margen);

				}

				if (map[x][y] == 24)
				{
					batch.draw(dirtyGrass4, x * margen, y * margen, margen, margen);

				}

				if (map[x][y] == 25)
				{
					batch.draw(dirtyGrass5, x * margen, y * margen, margen, margen);

				}

				if (map[x][y] == 26)
				{
					batch.draw(dirtyGrass6, x * margen, y * margen, margen, margen);

				}

				if (map[x][y] == 27)
				{
					batch.draw(dirtyGrass7, x * margen, y * margen, margen, margen);

				}

				if (map[x][y] == 28)
				{
					batch.draw(dirtyGrass8, x * margen, y * margen, margen, margen);

				}

				if (map[x][y] == 30)
				{
					batch.draw(wall0, x * margen, y * margen, margen, margen);

				}

				if (map[x][y] == 31)
				{
					batch.draw(wall1, x * margen, y * margen, margen, margen);

				}

				if (map[x][y] == 32)
				{
					batch.draw(wall2, x * margen, y * margen, margen, margen);

				}

				if (map[x][y] == 33)
				{
					batch.draw(wall3, x * margen, y * margen, margen, margen);

				}

				if (map[x][y] == 34)
				{
					batch.draw(wall4, x * margen, y * margen, margen, margen);

				}

				if (map[x][y] == 35)
				{
					batch.draw(wall5, x * margen, y * margen, margen, margen);

				}

				if (map[x][y] == 36)
				{
					batch.draw(wall6, x * margen, y * margen, margen, margen);

				}

				if (map[x][y] == 37)
				{
					batch.draw(wall7, x * margen, y * margen, margen, margen);

				}

				if (map[x][y] == 38)
				{
					batch.draw(wall8, x * margen, y * margen, margen, margen);

				}

				if (map[x][y] == 90)
				{
					batch.draw(tallGrass, x * margen, y * margen, margen, margen);

				}
			}
		}

		font.setColor(Color.RED);
		font.getData().setScale(.5f);
		font.draw(batch, text, 10, 13);
		//text = "";

		batch.end();
	}
}

