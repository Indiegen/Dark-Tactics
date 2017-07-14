package com.indiegen.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiegen.game.utils.AssetsManager;

public class DarkTactics extends Game implements ApplicationListener {

    private Batch batch;

    public DarkTactics() {

    }

    @Override
    public void create() {
        AssetsManager.loadAssets();
        batch = new SpriteBatch();
        MyScreen myScreen1 = new MyScreen(this, batch);
        setScreen(myScreen1);
    }

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }

}
