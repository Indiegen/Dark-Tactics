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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.indiegen.game.actors.Blood;
import com.indiegen.game.actors.HealthBar;
import com.indiegen.game.enums.ScreenState;
import com.indiegen.game.utils.AssetsManager;
import com.indiegen.game.utils.Constants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

@SuppressWarnings("CanBeFinal")
public class MyScreen implements Screen, GestureDetector.GestureListener, callBack, Levels, InputProcessor {

    private Game thisGame;
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

    private float currentZoom = 1;
    private float newZoom = 1;

    private String text = "";

    private BitmapFont font;
    private Camera camera;
    private Vector3 touchVec;

    ScreenState state;
    private Ui ui;
    private Maps maps;
    private MyDialog mydialog;
    private FrameBuffer lightBuffer;
    Music music1;

    public MyScreen(Game game) {
        thisGame = game;
        this.batch = new SpriteBatch();

        touchVec = new Vector3();
        margin = 64;
        camera = new OrthographicCamera(Constants.APP_WIDTH, Constants.APP_HEIGHT);
        Viewport viewport = new FitViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT, camera);
        Viewport uiViewport = new FitViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT);
        stage = new Stage(viewport);
        uiStage = new Stage(uiViewport);
        viewport.apply();
        Table table = new Table();
        table.setFillParent(true);
        uiStage.addActor(table);

        music1 = AssetsManager.getMusic();


        texture = AssetsManager.getTexture();
        playerTexture = AssetsManager.getHero21();
        enemyTexture = AssetsManager.getEnemy();
        healthBar = new HealthBar();

        shape = new ShapeRenderer();
        blood = new Blood(shape);
        floor = new stdCharacter(texture);
        dummy = new stdEnemy(enemyTexture, 9999, 9999, "dummy");
        actors = new ArrayList<>();
        ready = new ArrayList<>();


        InputMultiplexer im = new InputMultiplexer();
        GestureDetector gd = new GestureDetector(this);

        im.addProcessor(gd);
        im.addProcessor(uiStage);
        im.addProcessor(this);


        Gdx.input.setInputProcessor(im);


        font = new BitmapFont();
        ui = new Ui(this);

        ui.getAttackButton().setPosition(Constants.APP_WIDTH - margin, 0);
        ui.getMoveButton().setPosition(Constants.APP_WIDTH - margin * 2, 0);
        ui.getGuardButton().setPosition(Constants.APP_WIDTH - margin, 64);
        ui.getItemButton().setPosition(Constants.APP_WIDTH - margin * 2, 64);

        mydialog = new MyDialog(this, uiStage);

        mydialog.welcome().show();

        table.addActor(ui.getAttackButton());
        table.addActor(ui.getMoveButton());
        table.addActor(ui.getHealthBar());
        table.addActor(ui.getMessage());
        table.addActor(ui.getCloseUp());
        table.addActor(ui.getGuardButton());
        table.addActor(ui.getItemButton());

        initScreen();
        maps = new Maps();

    }

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
        healthBar.setBarHP(80);
        healthBar.setMaxHP(120);
        floor.setX(0);
        floor.setY(0);
        floor.setWidth(margin * 8);
        player.setHeight(margin);
        floor.setHeight(8 * .99f * margin * texture.getHeight() / texture.getWidth());
        actors.add(player);

        actingActor = dummy;
        state = ScreenState.START;

        music1.setLooping(true);
        music1.play();
        music1.setVolume(0);

        actors.add(new stdEnemy(enemyTexture, margin * 4, margin * 5, "1"));
        actors.add(new stdEnemy(enemyTexture, margin * 10, margin * 6, "2"));
        actors.add(new stdEnemy(enemyTexture, margin * 5, margin * 5, "3"));
        actors.add(new stdEnemy(enemyTexture, margin * 3, margin, "4"));
        actors.add(new KingSkeleton(AssetsManager.getKingSkeleton(), margin * 18, margin * 6, "Boss"));

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

                AssetsManager.getPotionSound().play();
            }
        }


    }


    public void buttonGuard() {
        if (player.getPlayerState() == stdPlayerState.GUARD) {
            player.setPlayerState(stdPlayerState.READY);
        } else {
            if (player.getPlayerState() == stdPlayerState.READY) {
                player.setPlayerState(stdPlayerState.GUARD);
                actingActor = player;
                player.setFatigue(player.GUARD);
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

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
                actor.setPlayerState(stdPlayerState.WAITING);
            }
            if (actor.getHP() <= 0) {
                actor.dead();
                actor.setPlayerState(stdPlayerState.FINISH);

            }


        }

        if (maps.getMap()[(int) player.getX() / margin][(int) player.getY() / margin] == 5) {
            state = ScreenState.WIN;
        }

        isTurnEnd2();


        if (state == ScreenState.WIN) {
            mydialog.levelCompleted().show();
            state = ScreenState.FINISH;
        }

        if (state != ScreenState.FINISH) {
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
            uiStage.getBatch().draw(actor.getTurnTexture(), Constants.APP_WIDTH - 34 - sangria, Constants.APP_HEIGHT - 16 - 48 * turn, 32, 32);
            sangria = 0;
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
                state = ScreenState.FINISH;

            }

        }
    }


    private void isTurnEnd2() {

        while (ready.size() < 7) {
            for (MyActor actor : actors) {
                actor.setFatigue(actor.getFatigue() - 1);

                if (actor.getFatigue() <= 0) {

                    actor.setPlayerState(stdPlayerState.READY);
                    ready.add(actor);
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

                try {
                    if (maps.getMap()[(int) rect.getX() / margin][(int) rect.getY() / margin] == 3 ||
                            maps.getMap()[(int) rect.getX() / margin][(int) rect.getY() / margin] == 6) {

                        rect.setColor(new Color(1, 0, 0, .2f));
                        rect.isEnable = false;
                    }
                } catch (ArrayIndexOutOfBoundsException excepcion) {

                }

            }
        }
    }

    @Override
    public void resize(int p1, int p2) {


    }


    @Override
    public void show() {


    }

    @Override
    public void hide() {


    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        thisGame.dispose();
        batch.dispose();

    }

    @Override
    public boolean touchDown(float p1, float p2, int p3, int p4) {
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
                        AssetsManager.getSwordAttackSound().play();
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
                AssetsManager.getWalkSound().play();
                actor.setFatigue(20);

            }
        }
    }

    @Override
    public boolean tap(float p1, float p2, int p3, int p4) {

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
                        AssetsManager.getWalkSound().play();
                        player.setFatigue(player.WALK);

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
                                AssetsManager.getSwordAttackSound().play();
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

                                return false;

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


        return false;
    }

    @Override
    public boolean fling(float p1, float p2, int p3) {


        return false;
    }

    @Override
    public boolean pan(float p1, float p2, float deltaX, float deltaY) {

        return false;
    }

    @Override
    public boolean panStop(float p1, float p2, int p3, int p4) {

        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float finalDistance) {

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

        return false;
    }


    private void drawLights() {

        if (lightBuffer != null)
            lightBuffer.dispose();
        lightBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, (int) camera.viewportWidth, (int) camera.viewportHeight, false);

        lightBuffer.getColorBufferTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        TextureRegion lightBufferRegion = new TextureRegion(lightBuffer.getColorBufferTexture(), 0, 0, (int) camera.viewportWidth, (int) camera.viewportHeight);

        lightBufferRegion.flip(false, true);

        lightBuffer.begin();

        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendEquation(GL20.GL_FUNC_REVERSE_SUBTRACT);
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.setColor(0.9f, 0.9f, .9f, .9f);

        float tx = (camera.position.x - camera.viewportWidth * newZoom / 2);
        float ty = (camera.position.y - camera.viewportHeight * newZoom / 2);

        float tw = camera.viewportWidth * newZoom;
        float th = camera.viewportHeight * newZoom;

        float lightSize = 256;

        batch.draw(AssetsManager.getLight(), camera.position.x - AssetsManager.getLight().getWidth() / 2, camera.position.y - AssetsManager.getLight().getHeight() / 2, AssetsManager.getLight().getWidth(), AssetsManager.getLight().getHeight());
        batch.draw(AssetsManager.getLight(), 64 * 3 - lightSize / 4 - 32, -lightSize / 4 - 32, lightSize, lightSize);
        batch.draw(AssetsManager.getLight(), 0 - lightSize / 4 - 32, 64 * 6 - lightSize / 4 - 32, lightSize, lightSize);
        batch.draw(AssetsManager.getLight(), 64 * 4 - lightSize / 4 - 32, 64 * 2 - lightSize / 4 - 32, lightSize, lightSize);
        batch.draw(AssetsManager.getLight(), 64 * 6 - lightSize / 4 - 32, 64 * 3 - lightSize / 4 - 32, lightSize, lightSize);
        batch.end();

        stage.getBatch().begin();
        stage.getBatch().setColor(0.9f, 0.9f, .9f, 1f);
        stage.getBatch().draw(AssetsManager.getLight(), camera.position.x - AssetsManager.getLight().getWidth() / 2, camera.position.y - AssetsManager.getLight().getHeight() / 2, AssetsManager.getLight().getWidth(), AssetsManager.getLight().getHeight());

        stage.getBatch().draw(AssetsManager.getLight(), 64 * 3 - lightSize / 4 - 32, -lightSize / 4 - 32, lightSize, lightSize);
        stage.getBatch().draw(AssetsManager.getLight(), 0 - lightSize / 4 - 32, 64 * 6 - lightSize / 4 - 32, lightSize, lightSize);
        stage.getBatch().draw(AssetsManager.getLight(), 64 * 4 - lightSize / 4 - 32, 64 * 2 - lightSize / 4 - 32, lightSize, lightSize);
        stage.getBatch().draw(AssetsManager.getLight(), 64 * 6 - lightSize / 4 - 32, 64 * 3 - lightSize / 4 - 32, lightSize, lightSize);
        stage.getBatch().end();

        lightBuffer.end();

        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        batch.begin();
        batch.setColor(0.9f, 0.9f, .9f, .9f);
        batch.draw(lightBufferRegion, tx, ty, tw, th);
        batch.end();

        stage.getBatch().begin();
        stage.getBatch().setColor(0.9f, .9f, .9f, .9f);
        stage.getBatch().draw(lightBufferRegion, tx, ty, tw, th);
        stage.getBatch().end();

        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        Gdx.gl.glDisable(GL20.GL_BLEND);
        Gdx.gl.glBlendEquation(GL20.GL_FUNC_ADD);

    }

}
