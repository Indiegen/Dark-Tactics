package com.indiegen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.indiegen.game.utils.AssetsManager;

public class Maps {

    private float delta = 0;
    private final TextureRegion tileRegion;
    private final TextureRegion brickRegion;
    private final TextureRegion grassRegion;
    private final TextureRegion groundRegion;
    private final TextureRegion doorRegion;

    private final Animation torchAnimation;

    private int[][] map = {
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

    public Maps() {
        TextureRegion[] torches = new TextureRegion[3];
        Texture tilesTexture = AssetsManager.getTiles();
        int tileSize = 32;
        tileRegion = new TextureRegion(tilesTexture, tileSize * 19, tileSize * 6, tileSize, tileSize);
        brickRegion = new TextureRegion(tilesTexture, tileSize * 7, tileSize * 7, tileSize, tileSize);
        grassRegion = new TextureRegion(tilesTexture, tileSize * 1, tileSize * 1, tileSize, tileSize);
        groundRegion = new TextureRegion(tilesTexture, tileSize * 0, tileSize * 1, tileSize, tileSize);
        doorRegion = new TextureRegion(tilesTexture, tileSize * 5, tileSize * 19, tileSize, tileSize);
        torches[0] = (new TextureRegion(AssetsManager.getTorch(), tileSize * 0, tileSize * 0, tileSize, tileSize));
        torches[1] = (new TextureRegion(AssetsManager.getTorch(), tileSize * 1, tileSize * 0, tileSize, tileSize));
        torches[2] = (new TextureRegion(AssetsManager.getTorch(), tileSize * 0, tileSize * 1, tileSize, tileSize));

        torchAnimation = new Animation(0.2f, torches);
        torchAnimation.setPlayMode(Animation.PlayMode.LOOP);

    }

    public int[][] getMap() {
        return map;
    }

    public void drawMap(Batch batch, BitmapFont font, String text) {
        batch.begin();
        delta += Gdx.graphics.getDeltaTime();

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[y].length; y++) {
                int margin = 64;
                if (map[x][y] == 1) {
                    batch.draw(brickRegion, x * margin, y * margin, margin, margin);
                }
                if (map[x][y] == 2) {
                    batch.draw(grassRegion, x * margin, y * margin, margin, margin);
                }
                if (map[x][y] == 0) {
                    batch.draw(groundRegion, x * margin, y * margin, margin, margin);
                }
                if (map[x][y] == 3) {
                    batch.draw(tileRegion, x * margin, y * margin, margin, margin);
                }
                if (map[x][y] == 4) {
                    batch.draw(doorRegion, x * margin, y * margin, margin, margin);
                }
                if (map[x][y] == 5) {
                    batch.draw(doorRegion, x * margin, y * margin, margin, margin);
                }
                if (map[x][y] == 6) {
                    batch.draw(tileRegion, x * margin, y * margin, margin, margin);
                    batch.draw(torchAnimation.getKeyFrame(delta, true), x * margin, y * margin, margin, margin);
                }

            }
        }

        font.setColor(Color.RED);
        font.getData().scaleX = 0.5f;
        font.getData().scaleY = 0.5f;
        font.draw(batch, text, 10, 13);

        batch.end();
    }
}
