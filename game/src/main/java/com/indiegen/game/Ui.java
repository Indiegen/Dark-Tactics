package com.indiegen.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.indiegen.game.utils.AssetsManager;

public class Ui
{
	Skin skin;
	Assests assests;
	
	Texture tilesTexture;
	
	TextureRegion tileRegion;
	TextureRegion brickRegion;
	TextureRegion grassRegion;
	TextureRegion groundRegion;
	TextureRegion button1;
	TextureRegion walkbutton;
	//Dialog  endDialog;
	
	int tileSize=32;
	int margen=64*3;
	
	TextButtonStyle textButtonStyle;
	
	TextButton attackButton;
	TextButton moveButton;
	TextButton guardButton;
	TextButton itemButton;
	TextButton dialogBox;

	HealthBar HealthBar;
	Message message;
	CloseUp closeUp;
	Texture guard;
	Texture item;
	Texture item2;
	TextButtonStyle itemStyle;
	
	public  Ui(final callBack myCallBack, Assests assests){

		this.assests=assests;
		skin = new Skin();
		
		tilesTexture=assests.tiles;
		tileRegion = new TextureRegion(tilesTexture, tileSize * 19, tileSize * 6, tileSize, tileSize);
		brickRegion = new TextureRegion(tilesTexture, tileSize * 7, tileSize * 7, tileSize, tileSize);
		grassRegion = new TextureRegion(tilesTexture, tileSize * 1, tileSize * 1, tileSize, tileSize);
		groundRegion = new TextureRegion(tilesTexture, tileSize * 1, tileSize * 16, tileSize, tileSize);
		button1 = new TextureRegion(assests.attackButton,0,0,32,32);
		walkbutton = new TextureRegion(assests.walkbutton,0,0,32,32);
		guard = assests.guard;
		item = assests.item;
		item2 = assests.item2;
		closeUp = new CloseUp(assests);
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(100, 100, Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fill();

		skin.add("white", button1);
		skin.add("down", groundRegion);
		skin.add("upWalk",walkbutton);
		skin.add("upGuard",guard);
		skin.add("upItem",item);
		skin.add("upItem2",item2);
		skin.add("dialogBox", AssetsManager.getDialogBox());

		// Store the default libgdx font under the name "default".
		BitmapFont bfont=new BitmapFont();
		bfont.getData().setScale(4f);
		skin.add("default", bfont);

		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white");
		textButtonStyle.down = skin.newDrawable("down");
		textButtonStyle.checked = skin.newDrawable("white");
		textButtonStyle.over = skin.newDrawable("white");

		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);
		
		
		TextButtonStyle walkStyle = new TextButtonStyle();
		walkStyle.up = skin.newDrawable("upWalk");
		walkStyle.down = skin.newDrawable("down");
		walkStyle.checked = skin.newDrawable("upWalk");
		walkStyle.over = skin.newDrawable("upWalk");
		
		walkStyle.font = skin.getFont("default");
		skin.add("walk", walkStyle);
		
		TextButtonStyle guardStyle = new TextButtonStyle();
		guardStyle.up = skin.newDrawable("upGuard");
		guardStyle.down = skin.newDrawable("down");
		guardStyle.checked = skin.newDrawable("upGuard");
		guardStyle.over = skin.newDrawable("upGuard");

		guardStyle.font = skin.getFont("default");
		skin.add("default", guardStyle);
		
		itemStyle = new TextButtonStyle();
		itemStyle.up = skin.newDrawable("upItem");
		itemStyle.down = skin.newDrawable("down");
		itemStyle.checked = skin.newDrawable("upItem");
		itemStyle.over = skin.newDrawable("white");

		itemStyle.font = skin.getFont("default");
		skin.add("default", itemStyle);

		TextButtonStyle dialogBoxStyle = new TextButtonStyle();
		dialogBoxStyle.up = skin.newDrawable("dialogBox");
		dialogBoxStyle.down = skin.newDrawable("dialogBox");
		dialogBoxStyle.checked = skin.newDrawable("dialogBox");
		dialogBoxStyle.over = skin.newDrawable("dialogBox");

		dialogBoxStyle.font = skin.getFont("default");
		skin.add("dialogBox", dialogBoxStyle);
		
		attackButton = new TextButton("", textButtonStyle);
		attackButton.setPosition(1000, 200);
		attackButton.setBounds(0,0,margen,margen);
		
		
		moveButton = new TextButton("", walkStyle);
		moveButton.setPosition(margen, margen);
		moveButton.setBounds(0,0,margen,margen);
		
		guardButton = new TextButton("", guardStyle);
		guardButton.setPosition(margen*3, margen);
		guardButton.setBounds(0,0,margen,margen);
		
		itemButton = new TextButton("", itemStyle);
		itemButton.setPosition(margen*4, margen);
		itemButton.setBounds(0,0,margen,margen);

		dialogBox = new TextButton("", dialogBoxStyle);
		dialogBox.setPosition(0, margen *3);
		dialogBox.setBounds(0,0,1080,margen*2);
        dialogBox.getLabel().setAlignment(Align.topLeft);
        dialogBox.getLabel().setWrap(true);
        dialogBox.getLabel().setPosition(5,5);
        dialogBox.pad(50);

        dialogBox.setText("Oliver - \"Te mataré maldito esqueleto\"\n" +
                "Rey esqueleto - \"Jajaja esqueleto?...");

		HealthBar = new HealthBar();
		
		
		message = new Message();
		
		getAttackButton().addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor)
				{
					myCallBack.buttonAttack();
				}
			});

		getMoveButton().addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor)
				{
					myCallBack.buttonMove();
				}
			});

		getItemButton().addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor)
				{
					myCallBack.buttonItem();
				}
			});
			
		getGuardButton().addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor)
				{
					myCallBack.buttonGuard();
				}
			});
		
	}

	public void setUpItemSkin(){
		itemStyle.up = skin.newDrawable("upItem2");
		itemStyle.down = skin.newDrawable("down");
		itemStyle.checked = skin.newDrawable("upItem2");
		itemStyle.over = skin.newDrawable("white");

		itemStyle.font = skin.getFont("default");
		skin.add("default", itemStyle);
	}

	public void setUpAttackSkin(){
		itemStyle.up = skin.newDrawable("upItem2");
		itemStyle.down = skin.newDrawable("down");
		itemStyle.checked = skin.newDrawable("upItem2");
		itemStyle.over = skin.newDrawable("white");

		itemStyle.font = skin.getFont("default");
		skin.add("default", itemStyle);
	}
	
	public void setUpGuardSkin(){
		itemStyle.up = skin.newDrawable("upItem2");
		itemStyle.down = skin.newDrawable("down");
		itemStyle.checked = skin.newDrawable("upItem2");
		itemStyle.over = skin.newDrawable("white");

		itemStyle.font = skin.getFont("default");
		skin.add("default", itemStyle);
	}

	public void setCloseUp(CloseUp closeUp)
	{
		this.closeUp = closeUp;
	}

	public CloseUp getCloseUp()
	{
		return closeUp;
	}

	public void setMessage(Message message)
	{
		this.message = message;
	}

	public Message getMessage()
	{
		return message;
	}

	
		
		
		
		
		
		
	

	public void setHealthBar(HealthBar healthBar)
	{
		HealthBar = healthBar;
	}

	public HealthBar getHealthBar()
	{
		return HealthBar;
	}

	public void setMoveButton(TextButton moveButton)
	{
		this.moveButton = moveButton;
	}

	public TextButton getMoveButton()
	{
		return moveButton;
	}

	public void setGuardButton(TextButton guardButton)
	{
		this.guardButton = guardButton;
	}

	public TextButton getGuardButton()
	{
		return guardButton;
	}

	public void setItemButton(TextButton itemButton)
	{
		this.itemButton = itemButton;
	}

	public TextButton getItemButton()
	{
		return itemButton;
	}

	public void setAttackButton(TextButton attackButton)
	{
		this.attackButton = attackButton;
	}

	public TextButton getAttackButton()
	{
		return attackButton;
	}


	public TextButton getDialogBox() {
		return dialogBox;
	}

	public void setDialogBox(TextButton dialogBox) {
		this.dialogBox = dialogBox;
	}
	
}
