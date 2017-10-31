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
    private static Texture heroTexture;
    private static Texture tileTexture;
    private static Texture enemyTexture;
    private static Texture kingSkeleton;
    private static Texture attackButtonTexture;
    private static Texture walkButtonTexture;
    private static Texture background;
    private static Texture torchTexture;
    private static Texture heroCloseUp;
    private static Texture guard;
    private static Texture item;
    private static Texture item2;
    private static Texture hmi;
    private static Texture light;
    private static Music music;
    private static Music music2;
    private static Sound heroAttackSound;
    private static Sound heroWalkSound;
    private static Sound potionSound;
    private static Skin skin;

    public static void loadAssets() {
        //public Texture hero = new Texture(Gdx.files.internal("hero.jpg"));
        heroTexture = new Texture(Gdx.files.internal("hero22.png"));
        tileTexture = new Texture(Gdx.files.internal("tiles.png"));
        enemyTexture = new Texture(Gdx.files.internal("skull.png"));
        kingSkeleton = new Texture(Gdx.files.internal("sprite_king.png"));
        attackButtonTexture = new Texture(Gdx.files.internal("sword.png"));
        walkButtonTexture = new Texture(Gdx.files.internal("walkbutton2.png"));
        background = new Texture(Gdx.files.internal("Background.png"));
        hmi = new Texture(Gdx.files.internal("hmi.png"));
        light = new Texture(Gdx.files.internal("Picture3.png"));
        torchTexture = new Texture(Gdx.files.internal("torch.png"));
        heroCloseUp = new Texture(Gdx.files.internal("heroCloseUp2.png"));
        guard = new Texture(Gdx.files.internal("guard.png"));
        item = new Texture(Gdx.files.internal("item.png"));
        item2 = new Texture(Gdx.files.internal("item2.png"));

        music = Gdx.audio.newMusic(Gdx.files.internal("EnemyAttack.mp3"));
        music2 = Gdx.audio.newMusic(Gdx.files.internal("EnemyAttack.mp3"));

        heroAttackSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/Hero_Attack.wav"));
        heroWalkSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/Walk_hero.wav"));
        potionSound = Gdx.audio.newSound(Gdx.files.internal("potionSound.wav"));

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
    public static Texture getHeroTexture(){
        return heroTexture;

    }

    /**
     *
     * @return The tileTexture texture asset
     */
    public static Texture getTileTexture(){
        return tileTexture;
    }

    /**
     *
     * @return The enemy texture asset
     */
    public static Texture getEnemyTexture(){
        return enemyTexture;
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
    public static Texture getAttackButtonTexture(){
        return attackButtonTexture;
    }

    /**
     *
     * @return The walk button texture asset
     */
    public static Texture getWalkButtonTexture(){
        return walkButtonTexture;
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
     * @return The torchTexture texture asset
     */
    public static Texture getTorchTexture(){
        return torchTexture;
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
    public static Sound getHeroAttackSound(){
        return heroAttackSound;
    }

    /**
     *
     * @return The walk sound asset
     */
    public static Sound getHeroWalkSound(){
        return heroWalkSound;
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
        heroTexture.dispose();
        tileTexture.dispose();
        enemyTexture.dispose();
        kingSkeleton.dispose();
        attackButtonTexture.dispose();
        walkButtonTexture.dispose();
        background.dispose();
        torchTexture.dispose();
        heroCloseUp.dispose();
        guard.dispose();
        item.dispose();
        item2.dispose();
        hmi.dispose();
        light.dispose();
        music.dispose();
        music2.dispose();
        heroAttackSound.dispose();
        heroWalkSound.dispose();
        potionSound.dispose();
        skin.dispose();
    }

}



