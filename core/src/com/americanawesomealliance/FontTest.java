package com.americanawesomealliance;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Brannon on 10/4/2015.
 */



public class FontTest implements Screen {

    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("impact.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

    BitmapFont font12 = generator.generateFont(50); // font size 12 pixels


    OrthographicCamera camera;
    Viewport viewport;
    SpriteBatch batch;

    MyGdxGame game;
    Stage stage;
    Timer timer;
    Timer.Task task;

    //Assets////////////////
    Texture splashBackgroundTexture;
    Sprite splashBackgroundSprite;
    Sound dinoRoar;
    Music sample;
    /////////////////////////////

    final float GAME_WORLD_WIDTH = 1080;
    final float GAME_WORLD_HEIGHT = 1920;

    public FontTest(MyGdxGame game) {

        this.game = game;

    }

    @Override
    public void show() {

        batch = new SpriteBatch();
        float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        System.out.println(aspectRatio);
        camera = new OrthographicCamera();
        viewport = new StretchViewport(GAME_WORLD_WIDTH, GAME_WORLD_WIDTH * aspectRatio, camera);
        viewport.apply();
        camera.position.set(GAME_WORLD_WIDTH / 2, GAME_WORLD_HEIGHT / 2, 0);
        stage = new Stage(viewport);

        //Background//////////////////////////////
        splashBackgroundTexture = new Texture(Gdx.files.internal("splashScreen.png"));
        splashBackgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        splashBackgroundSprite = new Sprite(splashBackgroundTexture);
        splashBackgroundSprite.setSize(1080, 1920);
        splashBackgroundSprite.setPosition(GAME_WORLD_WIDTH/2 - splashBackgroundSprite.getWidth()/2, GAME_WORLD_HEIGHT/2 - splashBackgroundSprite.getHeight()/2);


        ////////////////////////////////////////////

        //Dino Roar//////////////////////
        dinoRoar = Gdx.audio.newSound(Gdx.files.internal("dinoRoar.mp3"));
        dinoRoar.play();
        sample = Gdx.audio.newMusic(Gdx.files.internal("thatWasLegitnessSong.mp3"));
        //////////////////////////////////////////








    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
batch.setProjectionMatrix(camera.combined);
       batch.begin();
        splashBackgroundSprite.draw(batch);
        font12.draw(batch, "hey there parter", 500, 500);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        dinoRoar.dispose();
        splashBackgroundTexture.dispose();

    }

    public void timer() {
        timer = new Timer();
        task = new Timer.Task() {
            @Override
            public void run() {


                game.setScreen(new MainMenuScreen(game));


            }
        };

        timer.scheduleTask(task, 3);

    }
}
