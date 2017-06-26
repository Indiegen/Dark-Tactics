package com.indiegen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {

    public Texture texture = new Texture(Gdx.files.internal("floor.png"));

    //public Texture hero = new Texture(Gdx.files.internal("hero.jpg"));
    public Texture hero21 = new Texture(Gdx.files.internal("hero22.png"));
    public Texture tiles = new Texture(Gdx.files.internal("tiles.png"));
    public Texture enemy = new Texture(Gdx.files.internal("skull.png"));
    public Texture kingSkeleton = new Texture(Gdx.files.internal("Boss4.png"));
    public Texture button1 = new Texture(Gdx.files.internal("sword.png"));
    public Texture walkbutton = new Texture(Gdx.files.internal("walkbutton2.png"));
    public Texture Background = new Texture(Gdx.files.internal("Background.png"));
    public Music music = Gdx.audio.newMusic(Gdx.files.internal("EnemyAttack.mp3"));
    public Music music2 = Gdx.audio.newMusic(Gdx.files.internal("EnemyAttack.mp3"));
    public Sound swordAttackSound = Gdx.audio.newSound(Gdx.files.internal("SwordAttack.mp3"));
    public Sound walkSound = Gdx.audio.newSound(Gdx.files.internal("walk.wav"));
    public Sound potionSound = Gdx.audio.newSound(Gdx.files.internal("potionSound.wav"));
    public Texture hmi = new Texture(Gdx.files.internal("hmi.png"));
    public Texture light = new Texture(Gdx.files.internal("Picture3.png"));
    public Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    public Texture torch = new Texture(Gdx.files.internal("torch.png"));
    public Texture heroCloseUp = new Texture(Gdx.files.internal("heroCloseUp2.png"));
    public Texture guard = new Texture(Gdx.files.internal("guard.png"));
    public Texture item = new Texture(Gdx.files.internal("item.png"));
    public Texture item2 = new Texture(Gdx.files.internal("item2.png"));

}



