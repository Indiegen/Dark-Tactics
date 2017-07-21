package com.indiegen.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.indiegen.game.utils.AssetsManager;

public class CloseUp extends Actor {

    private final Texture closeUp = AssetsManager.getHeroCloseUp();

    public CloseUp() {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(closeUp, 0, 0, 64, 64);
    }
}
