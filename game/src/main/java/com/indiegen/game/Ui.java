package com.indiegen.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class Ui {
    Skin skin;
    Assets assets;

    Texture tilesTexture;

    TextureRegion tileRegion;
    TextureRegion brickRegion;
    TextureRegion grassRegion;
    TextureRegion groundRegion;
    TextureRegion button1;
    TextureRegion walkbutton;
    Dialog endDialog;

    int tileSize = 32;
    int margen = 64;

    TextButton.TextButtonStyle textButtonStyle;

    TextButton attackButton;
    TextButton moveButton;
    TextButton guardButton;
    TextButton itemButton;
    HealthBar HealthBar;
    Message message;
    CloseUp closeUp;
    Texture guard;
    Texture item;
    Texture item2;
    TextButton.TextButtonStyle itemStyle;

    public Ui(final callBack myCallBack, Assets assets) {

        this.assets = assets;
        skin = new Skin();

        tilesTexture = assets.tiles;
        tileRegion = new TextureRegion(tilesTexture, tileSize * 19, tileSize * 6, tileSize, tileSize);
        brickRegion = new TextureRegion(tilesTexture, tileSize * 7, tileSize * 7, tileSize, tileSize);
        grassRegion = new TextureRegion(tilesTexture, tileSize * 1, tileSize * 1, tileSize, tileSize);
        groundRegion = new TextureRegion(tilesTexture, tileSize * 1, tileSize * 16, tileSize, tileSize);
        button1 = new TextureRegion(assets.button1, 0, 0, 32, 32);
        walkbutton = new TextureRegion(assets.walkbutton, 0, 0, 32, 32);
        guard = assets.guard;
        item = assets.item;
        item2 = assets.item2;
        closeUp = new CloseUp(assets);
        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();

        skin.add("white", button1);
        skin.add("down", groundRegion);
        skin.add("upWalk", walkbutton);
        skin.add("upGuard", guard);
        skin.add("upItem", item);
        skin.add("upItem2", item2);

        // Store the default libgdx font under the name "default".
        BitmapFont bfont = new BitmapFont();
        bfont.getData().scale(0.5f);
        //bfont.scale(0.5f);
        skin.add("default", bfont);

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white");
        textButtonStyle.down = skin.newDrawable("down");
        textButtonStyle.checked = skin.newDrawable("white");
        textButtonStyle.over = skin.newDrawable("white");

        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);


        TextButton.TextButtonStyle textButtonStyle2 = new TextButton.TextButtonStyle();
        textButtonStyle2.up = skin.newDrawable("upWalk");
        textButtonStyle2.down = skin.newDrawable("down");
        textButtonStyle2.checked = skin.newDrawable("upWalk");
        textButtonStyle2.over = skin.newDrawable("upWalk");

        textButtonStyle2.font = skin.getFont("default");
        skin.add("walk", textButtonStyle2);

        TextButton.TextButtonStyle guardStyle = new TextButton.TextButtonStyle();
        guardStyle.up = skin.newDrawable("upGuard");
        guardStyle.down = skin.newDrawable("down");
        guardStyle.checked = skin.newDrawable("upGuard");
        guardStyle.over = skin.newDrawable("upGuard");

        guardStyle.font = skin.getFont("default");
        skin.add("default", guardStyle);

        itemStyle = new TextButton.TextButtonStyle();
        itemStyle.up = skin.newDrawable("upItem");
        itemStyle.down = skin.newDrawable("down");
        itemStyle.checked = skin.newDrawable("upItem");
        itemStyle.over = skin.newDrawable("white");

        itemStyle.font = skin.getFont("default");
        skin.add("default", itemStyle);


        attackButton = new TextButton("", textButtonStyle);
        attackButton.setPosition(1000, 200);
        attackButton.setBounds(0, 0, 64, 64);


        moveButton = new TextButton("", textButtonStyle2);
        moveButton.setPosition(64, 64);
        moveButton.setBounds(0, 0, 64, 64);

        guardButton = new TextButton("", guardStyle);
        guardButton.setPosition(margen * 3, margen);
        guardButton.setBounds(0, 0, margen, margen);

        itemButton = new TextButton("", itemStyle);
        itemButton.setPosition(margen * 4, margen);
        itemButton.setBounds(0, 0, margen, margen);


        HealthBar = new HealthBar();


        message = new Message();

        getAttackButton().addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                myCallBack.buttonAttack();
            }
        });

        getMoveButton().addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                myCallBack.buttonMove();
            }
        });

        getItemButton().addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                myCallBack.buttonItem();
            }
        });

        getGuardButton().addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                myCallBack.buttonGuard();
            }
        });

    }

    public void setUpItemSkin() {
        itemStyle.up = skin.newDrawable("upItem2");
        itemStyle.down = skin.newDrawable("down");
        itemStyle.checked = skin.newDrawable("upItem2");
        itemStyle.over = skin.newDrawable("white");

        itemStyle.font = skin.getFont("default");
        skin.add("default", itemStyle);
    }

    public void setCloseUp(CloseUp closeUp) {
        this.closeUp = closeUp;
    }

    public CloseUp getCloseUp() {
        return closeUp;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }


    public void setHealthBar(HealthBar healthBar) {
        HealthBar = healthBar;
    }

    public HealthBar getHealthBar() {
        return HealthBar;
    }

    public void setMoveButton(TextButton moveButton) {
        this.moveButton = moveButton;
    }

    public TextButton getMoveButton() {
        return moveButton;
    }

    public void setGuardButton(TextButton guardButton) {
        this.guardButton = guardButton;
    }

    public TextButton getGuardButton() {
        return guardButton;
    }

    public void setItemButton(TextButton itemButton) {
        this.itemButton = itemButton;
    }

    public TextButton getItemButton() {
        return itemButton;
    }

    public void setAttackButton(TextButton attackButton) {
        this.attackButton = attackButton;
    }

    public TextButton getAttackButton() {
        return attackButton;
    }


}
