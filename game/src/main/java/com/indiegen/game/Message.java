package com.indiegen.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Message extends Actor {

    ShapeRenderer shape;

    BitmapFont font;
    String text;

    public Message() {
        shape = new ShapeRenderer();
        text = "hello world";
        font = new BitmapFont();
        //setBarHP(100);
        //setBarMP(10);

    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        font.setColor(Color.RED);
        font.getData().scale(2f);
        font.draw(batch, text, 30, 100);

    }
}
