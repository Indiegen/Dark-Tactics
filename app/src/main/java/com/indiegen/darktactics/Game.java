package com.indiegen.darktactics;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.indiegen.game.DarkTactics;

public class Game extends AndroidApplication {
    private DarkTactics initgame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Implement this method
        super.onCreate(savedInstanceState);

        initgame = new DarkTactics();
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();

        initialize(initgame, cfg);

    }

}
