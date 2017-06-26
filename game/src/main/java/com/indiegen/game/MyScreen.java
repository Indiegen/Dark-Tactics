package com.indiegen.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

@SuppressWarnings("CanBeFinal")
public class MyScreen implements Screen, GestureDetector.GestureListener, callBack, Levels, InputProcessor {

    @Override
    public boolean keyDown(int p1) {
        return false;
    }

    @Override
    public boolean keyUp(int p1) {
        return false;
    }

    @Override
    public boolean keyTyped(char p1) {
        return false;
    }

    @Override
    public boolean touchDown(int p1, int p2, int p3, int p4) {
        return false;
    }

    @Override
    public boolean touchUp(int p1, int p2, int p3, int p4) {
        return false;
    }

    @Override
    public boolean touchDragged(int p1, int p2, int p3) {
        return false;
    }

    @Override
    public boolean mouseMoved(int p1, int p2) {
        return false;
    }

    @Override
    public boolean scrolled(int p1) {
        return false;
    }


    @Override
    public void initScreen() {
        actors.clear();
        stage.getActors().clear();

        player = new Player(playerTexture);
        player.setPosition(margin, margin);
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
        actors.add(new stdEnemy(enemyTexture, margin * 3, margin, "4"));
        actors.add(new KingSkeleton(assets.kingSkeleton, margin * 18, margin * 6, "Boss"));

        for (MyActor actor : actors) {
            stage.addActor(actor);
        }
        ready.clear();

        stage.addActor(blood);


    }


    public void buttonItem() {
        if (player.getPlayerState() == stdPlayerState.ITEM) {
            player.setPlayerState(stdPlayerState.READY);
        } else {
            if (player.getPlayerState() == stdPlayerState.READY && player.getPotions() > 0) {
                player.setPlayerState(stdPlayerState.ITEM);

                player.setHP(player.getHP() + 40);
                player.setPotions(player.getPotions() - 1);
                ui.setUpItemSkin();

                actingActor = player;
                player.setFatigue(30);

                assets.potionSound.play();
                //thisGame.setScreen(new MyScreen(thisGame,batch));
            }
        }


    }


    public void buttonGuard() {
        if (player.getPlayerState() == stdPlayerState.GUARD) {
            player.setPlayerState(stdPlayerState.READY);
        } else {
            if (player.getPlayerState() == stdPlayerState.READY) {
                player.setPlayerState(stdPlayerState.GUARD);
                //drawRects(player);
                actingActor = player;
                player.setFatigue(player.GUARD);
                //music2.play();
            }
        }
    }


    @Override
    public void buttonAttack() {
        if (player.getPlayerState() == stdPlayerState.ATTACK_TARGETING) {
            player.setPlayerState(stdPlayerState.READY);
        } else {
            if (player.getPlayerState() == stdPlayerState.READY) {
                player.setPlayerState(stdPlayerState.ATTACK_TARGETING);
                drawRects(player);
                actingActor = player;
                //music2.play();
            }
        }

    }

    @Override
    public void buttonMove() {
        if (player.getPlayerState() == stdPlayerState.WAITING_TO_MOVE) {
            player.setPlayerState(stdPlayerState.READY);
        } else {
            if (player.getPlayerState() == stdPlayerState.READY) {

                player.setPlayerState(stdPlayerState.WAITING_TO_MOVE);
                drawRects(player);
                actingActor = player;
            }
        }


    }


    @Override
    public void buttonExit() {
        System.exit(0);
    }

    @Override
    public void buttonRestart() {
        text = "thanks for playing";
        initScreen();
    }


    public enum screenState {
        START,
        FINISH,
        WIN
    }

    private Game thisGame;
    private Assets assets;
    private Batch batch;
    private ShapeRenderer shape;
    Texture texture;
    Texture playerTexture;
    Texture enemyTexture;
    stdCharacter floor;
    Player player;
    MyActor actingActor;
    MyActor dummy;
    ArrayList<MyActor> actors;
    ArrayList<MyActor> ready;
    HealthBar healthBar;
    Blood blood;
    Stage stage;
    private Stage uiStage;

    int margin;
    private int hmiHeight = 1440;
    private int hmiWidth = 2560;
    private float currentZoom = 1;
    private float newZoom = 1;

    private String text = "";

    private BitmapFont font;
    private Camera camera;
    private Vector3 touchVec;

    screenState state;
    private Ui ui;
    private Maps maps;
    private MyDialog mydialog;
    private FrameBuffer lightBuffer;
    Music music1;

    public MyScreen(Game game, Batch batch) {
        thisGame = game;
        this.batch = batch;

        touchVec = new Vector3();
        hmiHeight = 640;
        hmiWidth = 360;
        margin = 64;
        camera = new OrthographicCamera(hmiWidth, hmiHeight);
        Viewport viewport = new FitViewport(hmiWidth, hmiHeight, camera);
        Viewport uiViewport = new FitViewport(hmiWidth, hmiHeight);
        stage = new Stage(viewport);
        uiStage = new Stage(uiViewport);
        viewport.apply();
        Table table = new Table();
        table.setFillParent(true);
        uiStage.addActor(table);
        assets = new Assets();

        music1 = assets.music2;


        texture = assets.texture;
        playerTexture = assets.hero21;
        enemyTexture = assets.enemy;
        healthBar = new HealthBar();

        shape = new ShapeRenderer();
        blood = new Blood(shape);
        floor = new stdCharacter(texture);
        dummy = new stdEnemy(enemyTexture, 9999, 9999, "dummy");
        actors = new ArrayList<>();
        ready = new ArrayList<>();


        InputMultiplexer im = new InputMultiplexer();
        GestureDetector gd = new GestureDetector(this);

        //InputProcessor in = new InputProcessor(this);

        im.addProcessor(gd);
        im.addProcessor(uiStage);
        im.addProcessor(this);


        Gdx.input.setInputProcessor(im);


        font = new BitmapFont();
        ui = new Ui(this, assets);

        ui.getAttackButton().setPosition(hmiWidth - margin, 0);
        ui.getMoveButton().setPosition(hmiWidth - margin * 2, 0);
        ui.getGuardButton().setPosition(hmiWidth - margin, 64);
        ui.getItemButton().setPosition(hmiWidth - margin * 2, 64);

        mydialog = new MyDialog(this, uiStage, assets);

        mydialog.welcome().show();


        table.addActor(ui.getAttackButton());
        table.addActor(ui.getMoveButton());
        table.addActor(ui.getHealthBar());
        table.addActor(ui.getMessage());
        table.addActor(ui.getCloseUp());
        table.addActor(ui.getGuardButton());
        table.addActor(ui.getItemButton());


        initScreen();
        maps = new Maps(assets);


    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND); // Or GL20
        camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        shape.setProjectionMatrix(camera.combined);


        if (music1.getPosition() <= 3 && music1.getVolume() <= 1) {
            music1.setVolume(music1.getVolume() + 0.03f);
        }
        if (music1.getPosition() >= 71 && music1.getVolume() >= 0) {
            music1.setVolume(music1.getVolume() - 0.03f);

        }


        for (MyActor actor : actors) {
            if (actor.getPlayerState() == stdPlayerState.FINISH) {
                //actingActor = dummy;
                actor.setPlayerState(stdPlayerState.WAITING);
            }
            if (actor.getHP() <= 0) {
                actor.dead();
                actor.setPlayerState(stdPlayerState.FINISH);

            }


        }

        if (maps.getMap()[(int) player.getX() / margin][(int) player.getY() / margin] == 5) {
            state = screenState.WIN;
        }

        isTurnEnd2();


        if (state == screenState.WIN) {
            mydialog.levelCompleted().show();
            state = screenState.FINISH;
        }

        if (state != screenState.FINISH) {
            AITurn();
        }
        ui.getMessage().setText("");

        maps.drawMap(batch, font, text);
        ui.getHealthBar().setBarHP(player.getHP());

        if (actingActor == player && player.getPlayerState() == stdPlayerState.WAITING_TO_MOVE) {
            ui.getMessage().setText("Select Direction");
        }

        if (actingActor == player && player.getPlayerState() == stdPlayerState.ATTACK_TARGETING) {
            ui.getMessage().setText("Select Target");
        }


        stage.draw();
        stage.act();
        drawLights();

        uiStage.draw();
        removeDead();
        drawTurns();

    }

    private void drawTurns() {
        int turn = 1;
        int sangria = 16;

        for (MyActor actor : ready) {


            uiStage.getBatch().begin();
            uiStage.getBatch().draw(actor.getTurnTexture(), hmiWidth - 34 - sangria, hmiHeight - 16 - 48 * turn, 32, 32);
            sangria = 0;

            //font.setColor(Color.RED);
            //font.setScale(.5f);
            //font.draw(uiStage.getBatch(), actor.getName()+" "+actor.getPlayerState(), hmiWidth - 100, hmiHeight - 128 - 34 * turn);
            uiStage.getBatch().end();

            turn++;
            if (turn >= 7) {
                break;
            }


        }
    }

    private void AITurn() {
        if (ready.size() > 0) {
            if (!Objects.equals(ready.get(0).getName(), "player")
                    && ready.get(0).getPlayerState() == stdPlayerState.READY
                    ) {
                AIMove(ready.get(0));
            }

            if (ready.get(0).getPlayerState() == stdPlayerState.WAITING)
                ready.remove(0);

        }

    }

    private void removeDead() {
        Iterator<MyActor> itr = ready.listIterator();
        while (itr.hasNext()) {
            MyActor ready = itr.next();

            if (ready.isDead()) {
                //text=text+" "+ready.getName()+" "+ready.getPlayerState();
                itr.remove();
                ready.remove();
            }
        }


        for (int i = 0; i < actors.size(); i++) {


            if (actors.get(i).isDead() && !Objects.equals(actors.get(i).getName(), "player")) {
                actors.get(i).remove();
                actors.remove(i);

                break;

            }

            if (actors.get(i).isDead() && Objects.equals(actors.get(i).getName(), "player")) {
                ready.remove(actors.get(i));
                actors.get(i).remove();

                actors.remove(i);
                mydialog.youAreDead().show();
                state = screenState.FINISH;

            }

        }
    }


    private void isTurnEnd2() {

        while (ready.size() < 7) {
            for (MyActor actor : actors) {
                actor.setFatigue(actor.getFatigue() - 1);

                if (actor.getFatigue() <= 0) {

                    actor.setPlayerState(stdPlayerState.READY);
                    //actingActor = actor;
                    //actingActor.Acting(true);
                    ready.add(actor);
                    //actor.setDefence(0);
                    actor.setFatigue(20);
                }
            }
        }

    }


    private void drawRects(MyActor actor) {
        for (MyRect rect : actor.rects) {

            for (MyActor otherActor : actors) {
                if (rect.contains(otherActor.getX() + margin / 2, otherActor.getY() + margin / 2) && actor != otherActor) {
                    rect.setColor(new Color(1, 0, 0, .2f));
                    rect.hasTarget = false;
                }

                if (maps.getMap()[(int) rect.getX() / margin][(int) rect.getY() / margin] == 3 ||
                        maps.getMap()[(int) rect.getX() / margin][(int) rect.getY() / margin] == 6) {

                    rect.setColor(new Color(1, 0, 0, .2f));
                    rect.isEnable = false;
                }
            }
        }
    }

    @Override
    public void resize(int p1, int p2) {

// if lightBuffer was created before, dispose, we recreate a new one


    }


    @Override
    public void show() {

        // TODO: Implement this method
    }

    @Override
    public void hide() {


        // TODO: Implement this method
    }

    @Override
    public void pause() {
        // TODO: Implement this method
    }

    @Override
    public void resume() {
        // TODO: Implement this method
    }

    @Override
    public void dispose() {
        stage.dispose();
        thisGame.dispose();
        // TODO: Implement this method
    }

////////////////////////////////////////////////////////////////////

    @Override
    public boolean touchDown(float p1, float p2, int p3, int p4) {
        // TODO: Implement this method

        //camera.position.set(p1, p2 , 0);
        currentZoom = newZoom;


        return false;
    }

    private void AIMove(MyActor actor) {


        MyRect targetRect = new MyRect(9999, 9999, margin, margin);
        if (actor.getPlayerState() == stdPlayerState.READY && !actor.isDead()) {
            actor.setPlayerState(stdPlayerState.WAITING_TO_MOVE);
            drawRects(actor);
            for (MyRect rect : actor.rects) {

                if (rect.isEnable() && rect.hasTarget) {
                    rect.distance = (float) (Math.sqrt(Math.pow(player.getX() - rect.getX(), 2) + Math.pow(player.getY() - rect.getY(), 2)));
                } else {
                    rect.distance = 9999;
                    if (rect.contains(player.getX() + margin / 2, player.getY() + margin / 2) && actor.getPlayerState() != stdPlayerState.BEING_HITTING) {

                        if (actor.getX() < player.getX()) {
                            actor.setDir(1);
                        } else {
                            actor.setDir(0);
                        }
                        player.setDamage(actor.getAttack());
                        actor.setPlayerState(stdPlayerState.ATTACK_TARGETING);
                        player.setPlayerState(stdPlayerState.BEING_HITTING);
                        actor.setPlayerState(stdPlayerState.ATTACKING);
                        //enemy.setHP(enemy.getHP()-player.getAttack());
                        //assets
                        assets.swordAttackSound.play();
                        actor.setFatigue(50);
                        blood.createBlood(player);
                        return;
                    }
                }

            }
            for (MyRect rect : actor.rects) {
                if (rect.distance < targetRect.distance) {
                    targetRect = rect;
                }
            }

            if (targetRect.hasTarget()) {
                actor.setCurX(targetRect.getX());
                actor.setCurY(targetRect.getY());
                if (actor.getX() < player.getX()) {
                    actor.setDir(1);
                } else {
                    actor.setDir(0);
                }
                actor.setPlayerState(stdPlayerState.MOVING);
                assets.walkSound.play();
                //ready.remove(0);
                actor.setFatigue(20);

            }
        }
    }

    @Override
    public boolean tap(float p1, float p2, int p3, int p4) {
        // TODO: Implement this method

        touchVec.set(p1, p2, 0);
        touchVec = camera.unproject(touchVec);

        if (player.getPlayerState() != stdPlayerState.WAITING) {
            if (player.getPlayerState() == stdPlayerState.WAITING_TO_MOVE) {
                for (MyRect rect : player.rects) {
                    if (rect.contains(touchVec.x, touchVec.y)
                            &&
                            rect.hasTarget() && rect.isEnable()) {
                        player.setCurX(rect.getX());
                        player.setCurY(rect.getY());

                        if (player.getX() < player.getCurX()) {
                            player.setDir(1);
                        } else {
                            player.setDir(0);
                        }


                        player.setPlayerState(stdPlayerState.MOVING);
                        assets.walkSound.play();
                        player.setFatigue(player.WALK);


                        //ready.remove(0);
                        return false;
                    }
                }

            }
            if (player.getPlayerState() == stdPlayerState.ATTACK_TARGETING) {
                for (MyRect rect : player.rects) {

                    if (rect.contains(touchVec.x, touchVec.y)) {
                        for (MyActor actor : actors) {
                            if (actor.getRectangle().contains(touchVec.x, touchVec.y) && !Objects.equals(actor.getName(), player.getName())) {
                                actor.setDamage(player.getAttack());
                                actor.setPlayerState(stdPlayerState.BEING_HITTING);
                                player.setPlayerState(stdPlayerState.ATTACKING);
                                //enemy.setHP(enemy.getHP()-player.getAttack());
                                assets.swordAttackSound.play();
                                blood.createBlood(actor);
                                if (actor.getX() > player.getX()) {
                                    player.setDir(1);
                                } else {
                                    player.setDir(0);
                                }
                                if (actor.getX() == player.getX()) {
                                    player.setDir(2);
                                }

                                actingActor = actor;
                                player.setFatigue(player.ATTACK);
                                //ready.remove(0);

                                return false;
                                //player.setPlayerState(stdPlayerState.WAITING_OTHERS);

                            }
                        }
                    }

                }
            }
        }


        return false;
    }

    @Override
    public boolean longPress(float p1, float p2) {
        // TODO: Implement this method

        return false;
    }

    @Override
    public boolean fling(float p1, float p2, int p3) {


        return false;
    }

    @Override
    public boolean pan(float p1, float p2, float deltaX, float deltaY) {
        // TODO: Implement this method
        //touchX=touchX- deltaX/(Gdx.graphics.getWidth()/hmiWidth);
        //touchY=touchY+ deltaY/(Gdx.graphics.getWidth()/hmiWidth);

        return false;
    }

    @Override
    public boolean panStop(float p1, float p2, int p3, int p4) {
        // TODO: Implement this method
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float finalDistance) {
        // TODO: Implement this method

        newZoom = (currentZoom + (initialDistance - finalDistance) * .005f);
        if (newZoom > 3) {
            newZoom = 3;
        }
        if (newZoom < .5f) {
            newZoom = 0.5f;
        }
        ((OrthographicCamera) this.stage.getCamera()).zoom = newZoom;
        return false;
    }


    @Override
    public boolean pinch(Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4) {
        // TODO: Implement this method


        return false;
    }


    private void drawLights() {

        // start rendering to the lightBuffer

        if (lightBuffer != null)
            lightBuffer.dispose();
        lightBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, (int) camera.viewportWidth, (int) camera.viewportHeight, false);

        lightBuffer.getColorBufferTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        TextureRegion lightBufferRegion = new TextureRegion(lightBuffer.getColorBufferTexture(), 0, 0, (int) camera.viewportWidth, (int) camera.viewportHeight);

        lightBufferRegion.flip(false, true);


        lightBuffer.begin();

// setup the right blending
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendEquation(GL20.GL_FUNC_REVERSE_SUBTRACT);

// set the ambient color values, this is the "global" light of your scene
// imagine it being the sun.  Usually the alpha value is just 1, and you change the darkness/brightness with the Red, Green and Blue values for best effect

        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

// start rendering the lights to our spriteBatch
        batch.begin();


// set the color of your light (red,green,blue,alpha values)
        batch.setColor(0.9f, 0.9f, .9f, .9f);

// tx and ty contain the center of the light source
        float tx = (camera.position.x - camera.viewportWidth * newZoom / 2);
        float ty = (camera.position.y - camera.viewportHeight * newZoom / 2);

// tw will be the size of the light source based on the "distance"
// (the light image is 128x128)
// and 96 is the "distance"  
// Experiment with this value between based on your DarkTactics resolution
// my lights are 8 up to 128 in distance
        //float tw=assets.light.getWidth();

// make sure the center is still the center based on the "distance"
        float tw = camera.viewportWidth * newZoom;
        float th = camera.viewportHeight * newZoom;

        float lightSize = 256;


// and render the sprite
        batch.draw(assets.light, camera.position.x - assets.light.getWidth() / 2, camera.position.y - assets.light.getHeight() / 2, assets.light.getWidth(), assets.light.getHeight());
        batch.draw(assets.light, 64 * 3 - lightSize / 4 - 32, -lightSize / 4 - 32, lightSize, lightSize);
        batch.draw(assets.light, 0 - lightSize / 4 - 32, 64 * 6 - lightSize / 4 - 32, lightSize, lightSize);
        batch.draw(assets.light, 64 * 4 - lightSize / 4 - 32, 64 * 2 - lightSize / 4 - 32, lightSize, lightSize);
        batch.draw(assets.light, 64 * 6 - lightSize / 4 - 32, 64 * 3 - lightSize / 4 - 32, lightSize, lightSize);
        batch.end();

        stage.getBatch().begin();
        stage.getBatch().setColor(0.9f, 0.9f, .9f, 1f);
        stage.getBatch().draw(assets.light, camera.position.x - assets.light.getWidth() / 2, camera.position.y - assets.light.getHeight() / 2, assets.light.getWidth(), assets.light.getHeight());
        //stage.getBatch().setColor(0.9f, 0.0f, .0f, 1f);
        stage.getBatch().draw(assets.light, 64 * 3 - lightSize / 4 - 32, -lightSize / 4 - 32, lightSize, lightSize);
        stage.getBatch().draw(assets.light, 0 - lightSize / 4 - 32, 64 * 6 - lightSize / 4 - 32, lightSize, lightSize);
        stage.getBatch().draw(assets.light, 64 * 4 - lightSize / 4 - 32, 64 * 2 - lightSize / 4 - 32, lightSize, lightSize);
        stage.getBatch().draw(assets.light, 64 * 6 - lightSize / 4 - 32, 64 * 3 - lightSize / 4 - 32, lightSize, lightSize);
        //stage.getBatch().draw(assets.light, 0, 0, 400, 400);
        stage.getBatch().end();


        lightBuffer.end();


// now we render the lightBuffer to the default "frame buffer"
// with the right blending !

        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        //Gdx.gl.glBlendEquation(GL20.GL_FUNC_ADD);


        batch.begin();
        batch.setColor(0.9f, 0.9f, .9f, .9f);
        batch.draw(lightBufferRegion, tx, ty, tw, th);
        //batch.draw(lightBufferRegion, tx+64*3, ty, tw, th);
        batch.end();

        stage.getBatch().begin();
        stage.getBatch().setColor(0.9f, .9f, .9f, .9f);
        stage.getBatch().draw(lightBufferRegion, tx, ty, tw, th);
        //stage.getBatch().draw(lightBufferRegion, tx+64*3, ty, tw, th);
        stage.getBatch().end();


        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        Gdx.gl.glDisable(GL20.GL_BLEND);
        Gdx.gl.glBlendEquation(GL20.GL_FUNC_ADD);
        //Gdx.gl20.glBlendFunc(
// post light-rendering


    }

}
