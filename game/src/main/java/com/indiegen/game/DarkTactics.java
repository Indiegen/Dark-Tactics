package com.indiegen.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.indiegen.game.utils.AssetsManager;

public class DarkTactics extends Game implements ApplicationListener {

    @Override
    public void create() {
        AssetsManager.loadAssets();
        MyScreen myScreen1 = new MyScreen(this);
        setScreen(myScreen1);
    }

    @Override
    public void dispose() {
        AssetsManager.dispose();
        super.dispose();
    }

}
