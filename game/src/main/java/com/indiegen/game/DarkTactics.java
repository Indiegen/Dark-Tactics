package com.indiegen.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.indiegen.game.utils.AssetsManager;

public class DarkTactics extends Game implements ApplicationListener {

    @Override
    public void create() {
        AssetsManager.loadAssets();
        CustomScreen myScreen1 = new CustomScreen(this);
        setScreen(myScreen1);
    }

    @Override
    public void dispose() {
        AssetsManager.dispose();
        super.dispose();
    }

}
