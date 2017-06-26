package com.indiegen.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;

public class myScreenLevel1 extends MyScreen implements Levels {

    myScreenLevel1(Game game, Batch batch) {
        super(game, batch);

    }

    @Override
    public void initScreen() {

        // TODO: Implement this method
        actors.clear();
        stage.getActors().clear();

        player = new Player(playerTexture);
        player.setPosition(margin * 1, margin * 1);
        //player.setHP(200);
        healthBar.setBarHP(80);
        healthBar.maxHP = 120;
        floor.setX(0);
        floor.setY(0);
        floor.setWidth(margin * 8);
        player.setHeight(margin);
        floor.setHeight(8 * .99f * margin * texture.getHeight() / texture.getWidth());
        actors.add(player);

        actingActor = dummy;
        state = screenState.START;

        music1.setLooping(true);
        music1.play();
        music1.setVolume(0);

        actors.add(new stdEnemy(enemyTexture, margin * 4, margin * 5, "1"));
        actors.add(new stdEnemy(enemyTexture, margin * 10, margin * 6, "2"));
        actors.add(new stdEnemy(enemyTexture, margin * 5, margin * 5, "3"));
        actors.add(new stdEnemy(enemyTexture, margin * 3, margin * 1, "4"));

        for (MyActor actor : actors) {
            stage.addActor(actor);
        }
        ready.clear();

        stage.addActor(blood);

    }


}
