package com.indiegen.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * This class will manage all the assets used by the game.
 *
 * There is no need to create new instances of this class in each one of the classes where it is
 * being used, we'll keep this as a static class so there is only one instance across the game
 * and it loads all the assets before the first screen is shown.
 */
public class AssetsManager {

    private AssetsManager() {

    }

    private static Texture texture;
    private static Texture hero21;
    private static Texture tiles;
    private static Texture enemy;
    private static Texture kingSkeleton;
    private static Texture swordButton;
    private static Texture walkbutton;
    private static Texture background;
    private static Texture torch;
    private static Texture heroCloseUp;
    private static Texture guard;
    private static Texture item;
    private static Texture item2;
    private static Texture hmi;
    private static Texture light;
    private static Music music;
    private static Music music2;
    private static Sound swordAttackSound;
    private static Sound walkSound;
    private static Sound potionSound;
    private static Skin skin;

    public static void loadAssets() {
        texture = new Texture(Gdx.files.internal("floor.png"));
        hero21 = new Texture(Gdx.files.internal("hero22.png"));
        tiles = new Texture(Gdx.files.internal("tiles.png"));
        enemy = new Texture(Gdx.files.internal("skull.png"));
        kingSkeleton = new Texture(Gdx.files.internal("Boss4.png"));
        swordButton = new Texture(Gdx.files.internal("sword.png"));
        walkbutton = new Texture(Gdx.files.internal("walkbutton2.png"));
        background = new Texture(Gdx.files.internal("Background.png"));
        torch = new Texture(Gdx.files.internal("torch.png"));
        heroCloseUp = new Texture(Gdx.files.internal("heroCloseUp2.png"));
        guard = new Texture(Gdx.files.internal("guard.png"));
        item = new Texture(Gdx.files.internal("item.png"));
        item2 = new Texture(Gdx.files.internal("item2.png"));
        hmi = new Texture(Gdx.files.internal("hmi.png"));
        light = new Texture(Gdx.files.internal("Picture3.png"));
        music = Gdx.audio.newMusic(Gdx.files.internal("EnemyAttack.mp3"));
        music2 = Gdx.audio.newMusic(Gdx.files.internal("EnemyAttack.mp3"));
        swordAttackSound = Gdx.audio.newSound(Gdx.files.internal("SwordAttack.mp3"));
        walkSound = Gdx.audio.newSound(Gdx.files.internal("walk.wav"));
        potionSound = Gdx.audio.newSound(Gdx.files.internal("potionSound.wav"));
        skin = new Skin(Gdx.files.internal("uiskin.json"));
    }

    /**
     *
     * @return The floor texture asset
     */
    public static Texture getTexture(){
        return texture;
    }

    /**
     *
     * @return The hero22 texture asset
     */
    public static Texture getHero21(){
        return hero21;

    }

    /**
     *
     * @return The tiles texture asset
     */
    public static Texture getTiles(){
        return tiles;
    }

    /**
     *
     * @return The enemy texture asset
     */
    public static Texture getEnemy(){
        return enemy;
    }

    /**
     *
     * @return The King Skeleton texture asset
     */
    public static Texture getKingSkeleton(){
        return kingSkeleton;
    }

    /**
     *
     * @return The sword button texture asset
     */
    public static Texture getSwordButton(){
        return swordButton;
    }

    /**
     *
     * @return The walk button texture asset
     */
    public static Texture getWalkbutton(){
        return walkbutton;
    }

    /**
     *
     * @return The background texture asset
     */
    public static Texture getBackground(){
        return background;
    }

    /**
     *
     * @return The torch texture asset
     */
    public static Texture getTorch(){
        return torch;
    }

    /**
     *
     * @return The hero closeup texture asset
     */
    public static Texture getHeroCloseUp(){
        return heroCloseUp;
    }

    /**
     *
     * @return The guard texture asset
     */
    public static Texture getGuard(){
        return guard;
    }

    /**
     *
     * @return The item texture asset
     */
    public static Texture getItem(){
        return item;
    }

    /**
     *
     * @return The item2 texture asset
     */
    public static Texture getItem2(){
        return item2;
    }

    /**
     *
     * @return The hmi texture asset
     */
    public static Texture getHmi(){
        return hmi;
    }

    /**
     *
     * @return The light texture asset
     */
    public static Texture getLight(){
        return light;
    }

    /**
     *
     * @return The music music asset
     */
    public static Music getMusic(){
        return music;
    }

    /**
     *
     * @return The music2 music asset
     */
    public static Music getMusic2(){
        return music2;
    }

    /**
     *
     * @return The sword attack sound asset
     */
    public static Sound getSwordAttackSound(){
        return swordAttackSound;
    }

    /**
     *
     * @return The walk sound asset
     */
    public static Sound getWalkSound(){
        return walkSound;
    }

    /**
     *
     * @return The potion sound asset
     */
    public static Sound getPotionSound(){
        return potionSound;
    }

    /**
     *
     * @return The skin asset
     */
    public static Skin getSkin(){
        return skin;
    }

    public static void dispose(){
        texture.dispose();
        hero21.dispose();
        tiles.dispose();
        enemy.dispose();
        kingSkeleton.dispose();
        swordButton.dispose();
        walkbutton.dispose();
        background.dispose();
        torch.dispose();
        heroCloseUp.dispose();
        guard.dispose();
        item.dispose();
        item2.dispose();
        hmi.dispose();
        light.dispose();
        music.dispose();
        music2.dispose();
        swordAttackSound.dispose();
        walkSound.dispose();
        potionSound.dispose();
        skin.dispose();
    }

}



