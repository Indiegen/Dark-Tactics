package com.indiegen.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.indiegen.game.utils.AssetsManager;

public class CloseUp extends Actor {

    private Texture closeUp;

    public CloseUp() {
        closeUp = AssetsManager.getHeroCloseUp();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(closeUp, 0, 0, 64, 64);
    }
}
