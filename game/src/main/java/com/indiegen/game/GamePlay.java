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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.indiegen.game.Actors.CustomActor;
import com.indiegen.game.Actors.GameEnemy;
import com.indiegen.game.Actors.KingSkeleton;
import com.indiegen.game.Actors.Player;
import com.indiegen.game.enums.GamePlayerState;
import com.indiegen.game.utils.AssetsManager;
import com.indiegen.game.utils.Aura;
import com.indiegen.game.utils.Light;
import com.indiegen.game.utils.RectangleUtils;
import com.indiegen.game.utils.Shadows;
import com.indiegen.game.utils.SlashSwordAttack;
import com.indiegen.game.utils.Torch;

import java.util.ArrayList;
import java.util.Objects;

class GamePlay implements Screen, GestureListener, callBack, Levels, InputProcessor {

    Batch batch;

    Texture enemyTexture;
    Player player;
    CustomActor king;
    CustomActor actingActor;
    CustomActor dummy;
    ArrayList<CustomActor> actors;
    ArrayList<CustomActor> readys;
    FrameBuffer lightBuffer2;
    TextureRegion lightBufferRegion;
    Blood blood;
    Stage stage;
    private Stage uiStage;
    int margen = 64;
    private int hmiHeight;
    private int hmiWidth = 360;
    private float currentzoom = 1f;
    private float newzoom = 1f;
    private String text = "";
    private BitmapFont font;
    private Camera uiCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    private Vector3 touchVec;
    screenState state;
    private Ui ui;
    private Maps maps;
    private MyDialog mydialog;
    private FrameBuffer lightBuffer;
    Music music1;
    private Game thisGame;

    private ShapeRenderer shape;
    private Viewport uiViewport;
    private Camera camera = new OrthographicCamera(hmiWidth, hmiHeight);

    private Light torch1;
    Light torch2;
    Light torch3;
    Light torch4;
    Light torch5;
    Light kingAura;
    Light kingAuraRed;
    SlashSwordAttack slash;
    Shadows treeShadows;

    GamePlay(Game game, Batch batch) {
        thisGame = game;
        this.batch = batch;

        touchVec = new Vector3();
        hmiHeight = 640;
        Viewport viewport = new ScreenViewport(camera);
        uiViewport = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(viewport);
        uiStage = new Stage(uiViewport);
        viewport.apply();
        Table table = new Table();
        table.setFillParent(true);
        uiStage.addActor(table);





        music1 = AssetsManager.getMusic2();


        Texture tilesTexture = AssetsManager.getTileTexture();
        int tileSize = 64;
        TextureRegion tileRegion = new TextureRegion(tilesTexture, tileSize * 19, tileSize * 6, tileSize, tileSize);
        TextureRegion brickRegion = new TextureRegion(tilesTexture, tileSize * 7, tileSize * 7, tileSize, tileSize);
        TextureRegion grassRegion = new TextureRegion(tilesTexture, tileSize * 1, tileSize * 1, tileSize, tileSize);
        TextureRegion groundRegion = new TextureRegion(tilesTexture, tileSize * 0, tileSize * 1, tileSize, tileSize);
        TextureRegion doorRegion = new TextureRegion(tilesTexture, tileSize * 5, tileSize * 19, tileSize, tileSize);
        shape = new ShapeRenderer();
        blood = new Blood(shape);

        dummy = new GameEnemy(tilesTexture, 9999, 9999, "dummy");
        actors = new ArrayList<CustomActor>();
        readys = new ArrayList<CustomActor>();

        InputMultiplexer im = new InputMultiplexer();
        GestureDetector gd = new GestureDetector(this);

        im.addProcessor(gd);
        im.addProcessor(uiStage);
        im.addProcessor(this);


        Gdx.input.setInputProcessor(im);


        font = new BitmapFont();
        ui = new Ui(this);


        mydialog = new MyDialog(this, uiStage);

        table.addActor(ui.getAttackButton());
        table.addActor(ui.getMoveButton());
        table.addActor(ui.getHealthBar());
        table.addActor(ui.getMessage());
        table.addActor(ui.getCloseUp());
        table.addActor(ui.getGuardButton());
        table.addActor(ui.getItemButton());
        table.addActor(ui.getDialogBox());

        initScreen();
        maps = new Maps();


    }

    @Override
    public boolean keyDown(int p1) {
        // TODO: Implement this method
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
        // TODO: Implement this method
        return false;
    }

    @Override
    public boolean touchDragged(int p1, int p2, int p3) {
        // TODO: Implement this method
        return false;
    }

    @Override
    public boolean mouseMoved(int p1, int p2) {
        // TODO: Implement this method
        return false;
    }

    @Override
    public boolean scrolled(int p1) {
        // TODO: Implement this method
        return false;
    }

    @Override
    public void initScreen() {
        // TODO: Implement this method

        actors.clear();
        stage.getActors().clear();

        player = new Player(AssetsManager.getHeroTexture());
        player.setPosition(margen, margen);
        hmiHeight = 320;
        hmiWidth = 180;
        //healthBar.setBarHP(80);
        //healthBar.maxHP = 120;

        player.setHeight(margen);

        actors.add(player);

        actingActor = dummy;
        state = screenState.START;

        music1.setLooping(true);
        //music1.play();
        music1.setVolume(0);


        actors.add(new GameEnemy(AssetsManager.getEnemyTexture(), margen * 4, margen * 5, "1"));
        actors.add(new GameEnemy(AssetsManager.getEnemyTexture(), margen * 10, margen * 6, "2"));
        actors.add(new KingSkeleton(AssetsManager.getKingSkeleton(), margen * 3, margen * 2, "king"));
        king = actors.get(3);
        //actors.add(new KingSkeleton2(assests.kingSkeleton, margen * 3, margen * 1, "4"));
        //actors.add(new KingSkeleton(assests.kingSkeleton, margen*1,margen*2,"Boss"));

        for (CustomActor actor : actors) {
            stage.addActor(actor);
        }
        readys.clear();

        stage.addActor(blood);

        mydialog.welcome().show();
        isTurnEnd2();

        lightSetUp();
    }

    public void buttonItem() {
        if (player.getPlayerState() == GamePlayerState.ITEM) {
            player.setPlayerState(GamePlayerState.READY);
            player.getRects().clear();
        } else {
            if (player.getPlayerState() == GamePlayerState.READY && player.getPotions() > 0) {
                player.setPlayerState(GamePlayerState.ITEM);

                player.setHP(player.getHP() + 40);
                player.setPotions(player.getPotions() - 1);
                ui.setUpItemSkin();

                actingActor = player;
                player.setFatigue(player.getFatigue() + 30);

                AssetsManager.getPotionSound().play();
                player.setActionDone(true);
            }
        }


    }

    public void buttonGuard() {
        if (player.getPlayerState() == GamePlayerState.GUARD) {
            player.setPlayerState(GamePlayerState.READY);

        } else {
            if (player.getPlayerState() == GamePlayerState.READY) {
                player.setPlayerState(GamePlayerState.GUARD);
                actingActor = player;
                player.setFatigue(player.getFatigue() + 30);
                player.setActionDone(true);
                player.setDefence(4);

            }
        }
    }

    @Override
    public void buttonAttack() {
        player.getRects().clear();
        if (player.getPlayerState() == GamePlayerState.ATTACK_TARGETING) {
            player.setPlayerState(GamePlayerState.READY);
        } else {
            if (player.getPlayerState() == GamePlayerState.READY) {
                player.setPlayerState(GamePlayerState.ATTACK_TARGETING);
                drawRects(player);
                actingActor = player;

            }
        }

    }

    @Override
    public void buttonMove() {
        player.getRects().clear();
        if (player.getPlayerState() == GamePlayerState.WAITING_TO_MOVE) {
            player.setPlayerState(GamePlayerState.READY);
        } else {
            if (player.getPlayerState() == GamePlayerState.READY
                    && !player.isMoveDone()) {

                player.setPlayerState(GamePlayerState.WAITING_TO_MOVE);
                drawRects(player);
                actingActor = player;
            }
        }


    }

    @Override
    public void buttonExit() {
        // TODO: Implement this method
        System.exit(0);
    }

    @Override
    public void buttonRestart() {
        // TODO: Implement this method

        text = "thanks for playing";
        state = screenState.FINISH;

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

        for (CustomActor actor : actors) {
            if (actor.getPlayerState() == GamePlayerState.FINISH) {

                if (actor.isActionDone() && actor.isMoveDone()) {
                    actor.setPlayerState(GamePlayerState.WAITING);
                    isTurnEnd2();
                } else {
                    actor.setPlayerState(GamePlayerState.READY);
                }
            }
        }

        if (maps.getMap()[(int) player.getX() / margen][(int) player.getY() / margen] == 5) {
            state = screenState.WIN;
        }

        //isTurnEnd2();


        if (state == screenState.WIN) {
            mydialog.levelCompleted().show();
            state = screenState.FINISH;
        }

        if (state != screenState.FINISH) {
            AIturn();
        }
        ui.getMessage().setText("");

        maps.drawMap(stage.getBatch(), font, text);

        ui.getHealthBar().setBarHP(player.getHP());

        //initDebugger();
		if (actingActor == player && player.getPlayerState() == GamePlayerState.WAITING_TO_MOVE)
		{
			ui.getMessage().setText("Select direction to move" + touchVec.x);
		}
		if ( actingActor != player){
            ui.getMessage().setText("wait your turn");
        }

		if (actingActor == player && player.getPlayerState() == GamePlayerState.ATTACK_TARGETING)
		{
			ui.getMessage().setText("Select Target");
		}


        if (actingActor == player && player.getPlayerState() == GamePlayerState.READY)
        {
            ui.getMessage().setText("Select Action or Move");
        }



        stage.draw();
        stage.act();
        drawLights();
        //newzoom=.6f;

        //uiStage.getBatch().setProjectionMatrix(uiCamera.combined);
        uiStage.draw();
        removeDeads();
        //drawTurns();

    }

    /*private void drawTurns() {
        int turn = 1;
        int sangria = 16;

        for (CustomActor actor : readys) {


            uiStage.getBatch().begin();
            uiStage.getBatch().draw(actor.getTurnTexture(), uiViewport.getScreenWidth() - 34 * 3 - sangria, uiViewport.getScreenHeight() - 160 - 48 * 3 * turn, 32 * 3, 32 * 3);
            sangria = 0;

            uiStage.getBatch().end();

            turn++;
            if (turn >= 7) {
                break;
            }


        }

    }*/

    private void AIturn() {
        for( CustomActor actor : actors)
        {
            if (actor.getPlayerState() == GamePlayerState.READY && !actor.equals(player))
                AImove(actor);
        }

    }

    private void removeDeads() {

        /*Iterator<CustomActor> itr = readys.listIterator();
        while (itr.hasNext()) {
            CustomActor ready = itr.next();

            if (ready.isDead()) {

                itr.remove();
                ready.remove();
            }
        }*/


        for (int i = 0; i < actors.size(); i++) {
            if (actors.get(i).isDead() && !Objects.equals(actors.get(i).getName(), "player")) {

                actors.get(i).remove();
                actors.remove(i);
                break;

            }

            if (actors.get(i).isDead() && Objects.equals(actors.get(i).getName(), "player")) {
                //readys.remove(actors.get(i));
                actors.get(i).remove();
                actors.remove(i);
                mydialog.youAreDead().show();
                state = screenState.FINISH;

            }

        }
    }

    private void initDebugger() {

        shape.begin(ShapeType.Line);
        shape.setColor(Color.RED);

        for (int x = 0; x < 9; x++) {
            shape.line(margen * x, 0, margen * x, hmiHeight);

        }
        for (int x = 0; x < 11; x++) {
            shape.line(0, margen * x, hmiWidth, margen * x);

        }
        shape.end();

    }

    private boolean isTurnEnd2() {

        boolean calTurn=true;

        while (calTurn) {
            for (CustomActor actor : actors) {
                actor.setFatigue(actor.getFatigue() - 1);
            }
            for (CustomActor actor : actors) {

                if (actor.getFatigue() <= 0) {

                    actor.setPlayerState(GamePlayerState.READY);
                    actingActor = actor;
                    //actingActor.Acting(true);
                    //readys.add(actor);
                    actor.setDefence(0);
                    actor.setActionDone(false);
                    actor.setMoveDone(false);
                    calTurn=false;
                    break;
                }

            }
        }

        return true;

    }

    private void drawRects(CustomActor actor) {
        for (RectangleUtils rect : actor.getRects()) {

            for (CustomActor otherActor : actors) {
                if (rect.contains(otherActor.getX() + margen / 2, otherActor.getY() + margen / 2) && actor != otherActor) {
                    rect.setColor(new Color(1, 0, 0, .2f));
                    rect.setHasTarget(false);
                }

                if (maps.getMap()[(int) rect.getX() / margen][(int) rect.getY() / margen] == 3 ||
                        maps.getMap()[(int) rect.getX() / margen][(int) rect.getY() / margen] == 6) {

                    rect.setColor(new Color(1, 0, 0, .2f));
                    rect.setEnable(false);
                }
            }
        }
    }

    private void AImove(CustomActor actor) {

        RectangleUtils targetRect = new RectangleUtils(9999, 9999, margen, margen);
        if (actor.getPlayerState() == GamePlayerState.READY &&
                !actor.isDead()) {
            actor.setPlayerState(GamePlayerState.WAITING_TO_MOVE);
            drawRects(actor);
            for (RectangleUtils rect : actor.getRects()) {

                if (rect.isEnable() && rect.isHasTarget()) {
                    rect.setDistance((float) (Math.sqrt(Math.pow(player.getX() - rect.getX(), 2) + Math.pow(player.getY() - rect.getY(), 2))));
                    if (actor.isMoveDone()) {
                        actor.setActionDone(true);
                        actor.setPlayerState(GamePlayerState.FINISH);
                    }
                } else {
                    rect.setDistance(9999);
                    if (rect.contains(player.getX() + margen / 2, player.getY() + margen / 2)
                            && actor.getPlayerState() != GamePlayerState.BEING_HITTING
                            && !actor.isActionDone()) {

                        if (actor.getX() < player.getX()) {
                            actor.setDir(1);
                        } else {
                            actor.setDir(0);
                        }
                        player.setDamage(actor.getAttack()-player.getDefence());
                        actor.setPlayerState(GamePlayerState.ATTACK_TARGETING);
                        player.setPlayerState(GamePlayerState.BEING_HITTING);
                        actor.setPlayerState(GamePlayerState.ATTACKING);

                        //AssetsManager.getHeroAttackSound().play();
                        actor.setFatigue(actor.getFatigue() + 50);
                        blood.createBlood(player);
                        actor.setActionDone(true);
                        //readys.remove(0);
                        return;
                    }
                }

            }
            for (RectangleUtils rect : actor.getRects()) {
                if (rect.getDistance() < targetRect.getDistance()) {
                    targetRect = rect;
                }
            }

            if (targetRect.isHasTarget() && !actor.isMoveDone()) {
                actor.setCurX(targetRect.getX());
                actor.setCurY(targetRect.getY());
                if (actor.getX() < player.getX()) {
                    actor.setDir(1);
                } else {
                    actor.setDir(0);
                }
                actor.setPlayerState(GamePlayerState.MOVING);
                //assests.heroWalkSound.play();
                //readys.remove(0);
                actor.setFatigue(actor.getFatigue() + 20);
                actor.setMoveDone(true);

            }
        }
    }

    private void playerActions() {
        if (player.getPlayerState() == GamePlayerState.WAITING_TO_MOVE) {
            for (RectangleUtils rect : player.getRects()) {
                if (rect.contains(touchVec.x, touchVec.y)
                        &&
                        rect.isHasTarget() && rect.isEnable()) {
                    player.setCurX(rect.getX());
                    player.setCurY(rect.getY());

                    if (player.getX() < player.getCurX()) {
                        player.setDir(1);
                    } else {
                        player.setDir(0);
                    }

                    player.setPlayerState(GamePlayerState.MOVING);

                    player.setFatigue(player.getFatigue() + 30);
                    player.setMoveDone(true);
                    player.getRects().clear();

                    return;
                }
            }

        }
        if (player.getPlayerState() == GamePlayerState.ATTACK_TARGETING) {
            for (RectangleUtils rect : player.getRects()) {

                if (rect.contains(touchVec.x, touchVec.y)) {
                    for (CustomActor actor : actors) {
                        if (actor.getRectangle().contains(touchVec.x, touchVec.y) && !Objects.equals(actor.getName(), player.getName())) {
                            actor.setDamage(player.getAttack());

                            //actor.setPlayerState(GamePlayerState.BEING_HITTING);
                            //player.setPlayerState(GamePlayerState.ATTACKING);
                            //actor.setHP(actor.getHP()-player.getAttack());
                            //assests.heroAttackSound.play();
                            blood.createBlood(actor);
                            if (actor.getX() > player.getX()) {
                                player.setDir(1);
                            } else {
                                player.setDir(0);
                            }
                            if (actor.getX() == player.getX()) {
                                player.setDir(2);
                            }
                            actor.setPlayerState(GamePlayerState.BEING_HITTING);
                            player.setPlayerState(GamePlayerState.ATTACKING);
                            slash.setShow(true);
                            actingActor = actor;
                            player.setFatigue(player.getFatigue() + 50);
                            player.setActionDone(true);
                            player.getRects().clear();
                            //readys.remove(0);

                            return;
                            //player.setPlayerState(GamePlayerState.WAITING_OTHERS);

                        }
                    }
                }

            }
        }

    }
    @Override
    public boolean touchDown(float p1, float p2, int p3, int p4) {
        // TODO: Implement this method
        currentzoom = newzoom;

        return false;
    }

    @Override
    public boolean tap(float p1, float p2, int p3, int p4) {
        // TODO: Implement this method

        touchVec.set(p1, p2, 0);
        touchVec = camera.unproject(touchVec);

        if (player.getPlayerState() != GamePlayerState.WAITING) {
            playerActions();
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
    public boolean zoom(float inicialDistance, float finalDistance) {
        // TODO: Implement this method

        newzoom = (currentzoom + (inicialDistance - finalDistance) * .001f);
        if (newzoom > 1) {
            newzoom = 1f;
        }
        if (newzoom < .3f) {
            newzoom = .3f;
        }
        ((OrthographicCamera) this.stage.getCamera()).zoom = newzoom;


        return false;
    }

    @Override
    public boolean pinch(Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4) {
        // TODO: Implement this method


        return false;
    }

    private void drawLights() {

        // start rendering to the lightBuffer

        Gdx.gl.glBlendEquation(GL20.GL_FUNC_ADD);

        batch.begin();

        treeShadows.draw();

        torch1.draw();
        torch2.draw();
        torch3.draw();
        torch4.draw();
        torch5.draw();

        king.drawLights(batch);

        slash.draw(batch,player);


        batch.end();

        lightBuffer.begin();

// setup the right blending
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_DST_ALPHA);

        Gdx.gl.glBlendEquation(GL20.GL_FUNC_REVERSE_SUBTRACT);

// set the ambient color values, this is the "global" light of your scene
// imagine it being the sun.  Usually the alpha value is just 1, and you change the darkness/brightness with the Red, Green and Blue values for best effect

        Gdx.gl.glClearColor(0.0f,0.0f,0.0f,.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

// start rendering the lights to our spriteBatch
        batch.begin();

// set the color of your light (red,green,blue,alpha values)
        batch.setColor(1f, 1f, 1f, 1f);

// tx and ty contain the center of the light source
        float tx= (camera.position.x - camera.viewportWidth * newzoom / 2);
        float ty= (camera.position.y - camera.viewportHeight * newzoom / 2);

        float tw=camera.viewportWidth * newzoom;
        float th=camera.viewportHeight * newzoom;

// and render the sprite

        batch.draw(AssetsManager.getLight(), camera.position.x - AssetsManager.getLight().getWidth() / 4, camera.position.y - AssetsManager.getLight().getHeight() / 4, AssetsManager.getLight().getWidth()/2, AssetsManager.getLight().getHeight()/2);

        torch1.draw();
        torch2.draw();
        torch3.draw();
        torch4.draw();
        torch5.draw();

        king.drawLights(batch);
        slash.draw(batch, player);

        batch.end();

        lightBuffer.end();


// now we render the lightBuffer to the default "frame buffer"
// with the right blending !

        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_CONSTANT_ALPHA);
        Gdx.gl.glBlendEquation(GL20.GL_FUNC_ADD);


        batch.begin();
        batch.setColor(1f, 1f, 1f, 1f);
        batch.draw(lightBufferRegion, tx, ty, tw, th);

        batch.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);
// post light-rendering
// you might want to render your statusbar stuff here


    }

    @Override
    public void resize(int p1, int p2) {
        // Fakedlight system (alpha blending)

        //hmiHeight=360;
        //hmiWidth=640;
        camera.viewportHeight = p2 / 2;
        camera.viewportWidth = p1 / 2;


        uiViewport.setScreenWidth(p1);
        uiViewport.setScreenHeight(p2);
        uiCamera.position.set(uiCamera.viewportWidth / 2, uiCamera.viewportHeight / 2, 0);
//		uiCamera.update();
        //uiViewport.setCamera(uiCamera);

        ((OrthographicCamera) this.uiStage.getCamera()).setToOrtho(false, p1, p2);


        if (p1 > p2) {
            ui.getAttackButton().setPosition(p1 - margen * 3 - 160, 0);
            ui.getMoveButton().setPosition(p1 - margen * 3 * 2 - 160, 0);
            ui.getGuardButton().setPosition(p1 - margen * 3 - 160, margen * 3);
            ui.getItemButton().setPosition(p1 - margen * 3 * 2 - 160, margen * 3);
            ui.getDialogBox().setPosition(0, margen * 5);

        } else {
            ui.getAttackButton().setPosition(p1 - margen * 3, 0);
            ui.getMoveButton().setPosition(p1 - margen * 3 * 2, 0);
            ui.getGuardButton().setPosition(p1 - margen * 3, margen * 3);
            ui.getItemButton().setPosition(p1 - margen * 3 * 2, margen * 3);
            ui.getDialogBox().setPosition(0, margen * 6);

        }
// if lightBuffer was created before, dispose, we recreate a new one
        if (lightBuffer != null)
            lightBuffer.dispose();

        lightBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, (int)camera.viewportWidth, (int)camera.viewportHeight, false);
        lightBuffer.getColorBufferTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        lightBufferRegion = new TextureRegion(lightBuffer.getColorBufferTexture(), 0, 0, (int)camera.viewportWidth, (int)camera.viewportHeight);
        lightBufferRegion.flip(false, true);



    }

    @Override
    public void show() {
        //((OrthographicCamera)this.stage.getCamera()).zoom=.6f;


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

    enum screenState {
        START,
        RUNNING,
        FINISH,
        WIN,
        LOST
    }

    void lightSetUp()
    {
        torch1 = new Torch(batch,3,0);
        torch2 = new Torch(batch,0,6);
        torch3 = new Torch(batch,4,2);
        torch4 = new Torch(batch,6,2);
        torch5 = new Torch(batch,0,3);
        kingAura = new Aura(batch, king.getX(),king.getY(),200,200,new Color(1,1,1,.5f));
        kingAuraRed = new Aura(batch, king.getX(),king.getY(),new Color(1,0,0,1));
        slash = new SlashSwordAttack(batch,player.getX(),player.getY(), new Color(0,0,1,1));
        treeShadows = new Shadows(batch);

    }


}
