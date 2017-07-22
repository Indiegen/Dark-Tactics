package com.indiegen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.indiegen.game.utils.AssetsManager;

public class CustomDialog {

    BitmapFont _fontChat;
    Dialog dialog;
    Stage stage;
    CustomCallBack myCustomCallBack;
    Label.LabelStyle style;
    TextureRegion myTex;


    public CustomDialog(final CustomCallBack myCustomCallBack, Stage stage) {

        this.stage = stage;
        this.myCustomCallBack = myCustomCallBack;
        _fontChat = new BitmapFont();
        style = new Label.LabelStyle(_fontChat, Color.WHITE);
        Label label1 = new Label("Level 1 completed", style);
        label1.setAlignment(Align.center);
        label1.setWrap(true);
        style.fontColor = Color.WHITE;

        Skin tileSkin = new Skin();
        Texture tex = AssetsManager.getHmi();
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        Pixmap pixmap2 = new Pixmap((int) Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap2.setColor(Color.BLACK);
        pixmap2.fill();

        tileSkin.add("white", tex);
        tileSkin.add("default", new BitmapFont());

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = tileSkin.newDrawable("white");
        textButtonStyle.down = tileSkin.newDrawable("white", Color.RED);
        textButtonStyle.checked = tileSkin.newDrawable("white",
                Color.LIGHT_GRAY);
        textButtonStyle.over = tileSkin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = _fontChat;
        textButtonStyle.font.getData().scaleX = 1;
        textButtonStyle.font.getData().scaleY = 1;
        textButtonStyle.fontColor = Color.BLACK;
        tileSkin.add("default", textButtonStyle);

        TextButton btnYes = new TextButton("Exit", tileSkin);
        TextButton btnNo = new TextButton("Restart", tileSkin);

        Skin skinDialog = new Skin(Gdx.files.internal("uiskin.json"));
        dialog = new Dialog("", skinDialog) {
            @Override
            public float getPrefWidth() {
                return 200f;
            }

            @Override
            public float getPrefHeight() {
                return 200f;
            }
        };
        dialog.setModal(true);
        dialog.setMovable(false);
        dialog.setResizable(false);

        btnYes.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {

                dialog.hide();
                dialog.cancel();
                dialog.remove();
                myCustomCallBack.buttonExit();

                return true;
            }

        });


        btnNo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {

                dialog.hide();
                dialog.cancel();
                dialog.remove();
                myCustomCallBack.buttonRestart();

                return true;
            }

        });

        TextureRegion myTex = new TextureRegion(AssetsManager.getBackground());
        myTex.flip(false, false);
        Drawable drawable = new TextureRegionDrawable(myTex);
        dialog.setBackground(drawable);

        float btnSize = 64f;
        Table t = new Table();
        t.center();
        t.setFillParent(true);

        dialog.getContentTable().center();

        dialog.getContentTable().add(label1);

        t.add(btnYes).width(btnSize).height(btnSize).pad(10f);
        t.add(btnNo).width(btnSize).height(btnSize).pad(10f);

        dialog.getButtonTable().add(t);
        dialog.setName("quitDialog");

    }

    public CustomDialog levelCompleted() {

        Label label1 = new Label("Level 1 completed", style);
        label1.setAlignment(Align.center);
        label1.setWrap(true);
        style.fontColor = Color.WHITE;

        Skin tileSkin = new Skin();
        Texture tex = AssetsManager.getHmi();
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        Pixmap pixmap2 = new Pixmap((int) Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap2.setColor(Color.BLACK);
        pixmap2.fill();

        tileSkin.add("white", tex);
        tileSkin.add("default", new BitmapFont());

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = tileSkin.newDrawable("white");
        textButtonStyle.down = tileSkin.newDrawable("white", Color.RED);
        textButtonStyle.checked = tileSkin.newDrawable("white",
                Color.LIGHT_GRAY);
        textButtonStyle.over = tileSkin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = _fontChat;

        textButtonStyle.font.getData().scale(1);
        textButtonStyle.fontColor = Color.BLACK;
        tileSkin.add("default", textButtonStyle);

        TextButton btnYes = new TextButton("Exit", tileSkin);
        TextButton btnNo = new TextButton("Restart", tileSkin);

        Skin skinDialog = new Skin(Gdx.files.internal("uiskin.json"));
        dialog = new Dialog("", skinDialog) {
            @Override
            public float getPrefWidth() {
                return 200f;
            }

            @Override
            public float getPrefHeight() {
                return 200f;
            }
        };
        dialog.setModal(true);
        dialog.setMovable(false);
        dialog.setResizable(false);

        btnYes.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {

                dialog.hide();
                dialog.cancel();
                dialog.remove();
                myCustomCallBack.buttonExit();
                return true;

            }

        });


        btnNo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {

                dialog.hide();
                dialog.cancel();
                dialog.remove();
                myCustomCallBack.buttonRestart();

                return true;

            }

        });

        myTex = new TextureRegion(AssetsManager.getBackground());
        myTex.flip(false, false);
        Drawable drawable = new TextureRegionDrawable(myTex);
        dialog.setBackground(drawable);

        float btnSize = 64f;
        Table t = new Table();
        t.center();
        t.setFillParent(true);

        dialog.getContentTable().center();

        dialog.getContentTable().add(label1).padTop(50f);

        t.add(btnYes).width(btnSize).height(btnSize).pad(10f).padBottom(50f);
        t.add(btnNo).width(btnSize).height(btnSize).pad(10f).padBottom(50f);

        dialog.getButtonTable().add(t);
        dialog.setName("quitDialog");
        return this;

    }

    public CustomDialog welcome() {


        _fontChat = new BitmapFont();
        Label.LabelStyle style = new Label.LabelStyle(_fontChat, Color.WHITE);
        Label label1 = new Label("Level 1 you need to survive and reach the exit", style);
        label1.setAlignment(Align.center);
        label1.setWrap(true);
        style.fontColor = Color.WHITE;

        Skin tileSkin = new Skin();
        Texture tex = AssetsManager.getHmi();
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        Pixmap pixmap2 = new Pixmap((int) Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap2.setColor(Color.BLACK);
        pixmap2.fill();

        tileSkin.add("white", tex);
        tileSkin.add("default", new BitmapFont());

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = tileSkin.newDrawable("white");
        textButtonStyle.down = tileSkin.newDrawable("white", Color.RED);
        textButtonStyle.checked = tileSkin.newDrawable("white",
                Color.LIGHT_GRAY);
        textButtonStyle.over = tileSkin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = _fontChat;
        textButtonStyle.font.getData().scale(1);
        textButtonStyle.fontColor = Color.BLACK;
        tileSkin.add("default", textButtonStyle);

        TextButton btnYes = new TextButton("Ok", tileSkin);

        Skin skinDialog = new Skin(Gdx.files.internal("uiskin.json"));
        dialog = new Dialog("", skinDialog) {
            @Override
            public float getPrefWidth() {
                return 200f;
            }

            @Override
            public float getPrefHeight() {
                return 200f;
            }
        };
        dialog.setModal(true);
        dialog.setMovable(false);
        dialog.setResizable(false);

        btnYes.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {

                dialog.hide();
                dialog.cancel();
                dialog.remove();

                return true;
            }

        });


        TextureRegion myTex = new TextureRegion(AssetsManager.getBackground());
        myTex.flip(false, false);
        Drawable drawable = new TextureRegionDrawable(myTex);
        dialog.setBackground(drawable);

        float btnSize = 64f;
        Table t = new Table();
        t.center();
        t.setFillParent(true);

        dialog.getContentTable().center();

        dialog.getContentTable().add(label1).width(200).padTop(50f);
        t.add(btnYes).width(btnSize).height(btnSize).pad(10f).padBottom(50f);

        dialog.getButtonTable().add(t);
        dialog.setName("Welcome");


        return this;
    }

    public CustomDialog youAreDead() {

        Label label1 = new Label("You are dead", style);
        label1.setAlignment(Align.center);
        label1.setWrap(true);
        style.fontColor = Color.WHITE;

        Skin tileSkin = new Skin();
        Texture tex = AssetsManager.getHmi();
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        Pixmap pixmap2 = new Pixmap((int) Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap2.setColor(Color.BLACK);
        pixmap2.fill();

        tileSkin.add("white", tex);
        tileSkin.add("default", new BitmapFont());

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = tileSkin.newDrawable("white");
        textButtonStyle.down = tileSkin.newDrawable("white", Color.RED);
        textButtonStyle.checked = tileSkin.newDrawable("white",
                Color.LIGHT_GRAY);
        textButtonStyle.over = tileSkin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = _fontChat;
        textButtonStyle.font.getData().scale(1);
        textButtonStyle.fontColor = Color.BLACK;
        tileSkin.add("default", textButtonStyle);

        TextButton btnYes = new TextButton("Exit", tileSkin);
        TextButton btnNo = new TextButton("Restart", tileSkin);

        Skin skinDialog = new Skin(Gdx.files.internal("uiskin.json"));
        dialog = new Dialog("", skinDialog) {
            @Override
            public float getPrefWidth() {
                return 200f;
            }

            @Override
            public float getPrefHeight() {
                return 200f;
            }
        };
        dialog.setModal(true);
        dialog.setMovable(false);
        dialog.setResizable(false);

        btnYes.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {

                dialog.hide();
                dialog.cancel();
                dialog.remove();
                myCustomCallBack.buttonExit();
                return true;
            }

        });


        btnNo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {

                dialog.hide();
                dialog.cancel();
                dialog.remove();
                myCustomCallBack.buttonRestart();

                return true;
            }

        });

        myTex = new TextureRegion(AssetsManager.getBackground());
        myTex.flip(false, false);
        Drawable drawable = new TextureRegionDrawable(myTex);
        dialog.setBackground(drawable);

        float btnSize = 64f;
        Table t = new Table();
        t.center();
        t.setFillParent(true);

        dialog.getContentTable().center();

        dialog.getContentTable().add(label1).padTop(50f);
        t.add(btnYes).width(btnSize).height(btnSize).pad(10f).padBottom(50f);
        t.add(btnNo).width(btnSize).height(btnSize).pad(10f).padBottom(50f);
        dialog.getButtonTable().add(t);
        dialog.setName("quitDialog");
        return this;

    }

    public void show() {
        dialog.show(stage).setPosition(stage.getWidth() / 2 - dialog.getWidth() / 2, stage.getHeight() / 2 - dialog.getHeight() / 2);
        stage.addActor(dialog);


    }
}

