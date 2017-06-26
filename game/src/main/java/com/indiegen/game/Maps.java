package com.indiegen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Maps {
    //Assets assets;
    Batch batch;
    int margen = 64;
    int tileSize = 32;
    float delta = 0;
    TextureRegion tileRegion;
    TextureRegion brickRegion;
    TextureRegion grassRegion;
    TextureRegion groundRegion;
    TextureRegion doorRegion;
    TextureRegion torchRegion;

    Texture tilesTexture;

    TextureRegion[] torches;

    Animation torchAnimation;

    Assets assets;

    public int[][] map = {
            {3, 3, 3, 3, 3, 3, 6, 3, 3, 3,},
            {3, 2, 2, 1, 1, 1, 1, 1, 1, 3,},
            {3, 1, 2, 1, 3, 3, 1, 1, 1, 3,},
            {6, 1, 2, 1, 1, 1, 1, 1, 1, 3,},
            {3, 1, 6, 3, 1, 1, 2, 1, 1, 3,},
            {3, 1, 2, 3, 1, 2, 2, 1, 1, 3,},
            {3, 1, 2, 6, 2, 2, 1, 1, 1, 3,},
            {3, 1, 2, 3, 2, 1, 3, 1, 1, 3,},
            {3, 1, 2, 3, 2, 1, 1, 1, 1, 3,},
            {3, 1, 2, 2, 2, 2, 2, 2, 1, 3,},
            {3, 1, 1, 1, 1, 3, 3, 2, 1, 3,},
            {6, 1, 1, 1, 1, 1, 1, 1, 1, 6,},
            {3, 1, 1, 1, 1, 1, 1, 1, 1, 3,},
            {3, 1, 1, 1, 1, 1, 1, 1, 1, 3,},
            {6, 1, 1, 1, 1, 1, 1, 1, 1, 6,},
            {3, 1, 1, 1, 1, 1, 1, 1, 1, 3,},
            {3, 1, 1, 1, 1, 1, 1, 1, 1, 3,},
            {6, 1, 1, 1, 1, 1, 1, 1, 1, 6,},
            {3, 1, 1, 1, 1, 1, 1, 1, 1, 3,},
            {3, 1, 1, 1, 1, 1, 1, 1, 5, 3,},
            {6, 3, 3, 6, 3, 3, 6, 3, 3, 6,},
    };

    public Maps(Assets assets)

    {
        torches = new TextureRegion[3];

        this.assets = assets;
        tilesTexture = assets.tiles;
        tileRegion = new TextureRegion(tilesTexture, tileSize * 19, tileSize * 6, tileSize, tileSize);
        brickRegion = new TextureRegion(tilesTexture, tileSize * 7, tileSize * 7, tileSize, tileSize);
        grassRegion = new TextureRegion(tilesTexture, tileSize * 1, tileSize * 1, tileSize, tileSize);
        groundRegion = new TextureRegion(tilesTexture, tileSize * 0, tileSize * 1, tileSize, tileSize);
        doorRegion = new TextureRegion(tilesTexture, tileSize * 5, tileSize * 19, tileSize, tileSize);
        torches[0] = (new TextureRegion(assets.torch, tileSize * 0, tileSize * 0, tileSize, tileSize));
        torches[1] = (new TextureRegion(assets.torch, tileSize * 1, tileSize * 0, tileSize, tileSize));
        torches[2] = (new TextureRegion(assets.torch, tileSize * 0, tileSize * 1, tileSize, tileSize));

        torchAnimation = new Animation(0.2f, torches);
        torchAnimation.setPlayMode(Animation.PlayMode.LOOP);

    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int[][] getMap() {
        return map;
    }

    public void drawMap(Batch batch, BitmapFont font, String text) {
        this.batch = batch;
        batch.begin();
        delta += Gdx.graphics.getDeltaTime();

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[y].length; y++) {
                if (map[x][y] == 1) {
                    batch.draw(brickRegion, x * margen, y * margen, margen, margen);
                }
                if (map[x][y] == 2) {
                    batch.draw(grassRegion, x * margen, y * margen, margen, margen);
                }
                if (map[x][y] == 0) {
                    batch.draw(groundRegion, x * margen, y * margen, margen, margen);
                }
                if (map[x][y] == 3) {
                    batch.draw(tileRegion, x * margen, y * margen, margen, margen);
                }
                if (map[x][y] == 4) {
                    batch.draw(doorRegion, x * margen, y * margen, margen, margen);
                }
                if (map[x][y] == 5) {
                    batch.draw(doorRegion, x * margen, y * margen, margen, margen);
                }
                if (map[x][y] == 6) {
                    batch.draw(tileRegion, x * margen, y * margen, margen, margen);

                    batch.draw(torchAnimation.getKeyFrame(delta, true), x * margen, y * margen, margen, margen);
                }

            }
        }

        font.setColor(Color.RED);
        //font.setScale(.5f);
        font.getData().scaleX = 0.5f;
        font.getData().scaleY = 0.5f;
        font.draw(batch, text, 10, 13);
        //text = "";

        batch.end();
    }
}
