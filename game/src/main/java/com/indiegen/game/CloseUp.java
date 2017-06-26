package com.indiegen.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CloseUp extends Actor {

    ShapeRenderer shape;
    Texture closeUp;
    Assets assets;

    public CloseUp(Assets assets) {
        this.assets = assets;
        closeUp = assets.heroCloseUp;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(closeUp, 0, 0, 64, 64);
    }
}
