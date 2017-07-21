package com.indiegen.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HealthBar extends Actor {

    private ShapeRenderer shape;
    private int barHP;
    private int barMP;
    private int maxHP;
    private BitmapFont font;
    private String text;

    public HealthBar() {
        shape = new ShapeRenderer();
        text = "";
        font = new BitmapFont();
        setBarHP(160);
        setMaxHP(160);
        setBarMP(80);

    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setBarMP(int mP) {
        barMP = mP;
    }

    public int getBarMP() {
        return barMP;
    }

    public void setBarHP(int hP) {
        barHP = hP;
    }

    public int getBarHP() {
        return barHP;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setProjectionMatrix(batch.getProjectionMatrix());

        shape.setColor(Color.ORANGE);
        shape.rect(65, 49, 82, 8);

        shape.setColor(Color.RED);
        shape.rect(66, 50, getBarHP() * 80 / getMaxHP(), 6);

        shape.setColor(Color.BLACK);
        shape.rect(66, 43, 80, 3);

        shape.setColor(Color.CYAN);
        shape.rect(66, 43, barMP, 3);


        shape.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        batch.begin();
        text = barHP + "";
        font.setColor(Color.RED);
        font.getData().scale(.7f);
        font.draw(batch, text, 130, 64);

    }
}
