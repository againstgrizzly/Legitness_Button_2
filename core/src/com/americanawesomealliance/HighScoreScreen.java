package com.americanawesomealliance;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Brannon on 10/6/2015.
 */
public class HighScoreScreen implements Screen{

    MyGdxGame game;

    //Camera Code
    private OrthographicCamera camera;
    private Viewport viewport;
    SpriteBatch batch;
    final float GAME_WORLD_WIDTH = 1080;
    final float GAME_WORLD_HEIGHT = 1920;
    private Stage stage;

    int highScore;
    int score;

    float scale = 1.0f*Gdx.graphics.getWidth()/GAME_WORLD_WIDTH;

    //Assets
    Texture backgroundReplayTexture;
    Image backgroundReplayImage;

    Texture facebookButtonTexture;
    Button facebookButton;

    Texture homeButtonTexture;
    Button homeButton;

    Texture twitterButtonTexture;
    Button twitterButton;

    Texture replayButtonTexture;
    Button replayButton;

    Texture legitnessButtonButtonTexture;
    Button legitnessButtonButton;

    Texture legitnessSongButtonTexture;
    Button legitnessSongButton;

    Music legitnessSong;
    Sound clickSound;

    FreeTypeFontGenerator generator;

    BitmapFont highScoreTextFont;
    Label.LabelStyle highScoreTextLabelStyle;
    Label highScoreTextLabel;

    BitmapFont currentScoreTextFont;
    Label.LabelStyle currentScoreTextLabelStyle;
    Label currentScoreTextLabel;

    BitmapFont yourScoreTextFont;
    Label.LabelStyle yourScoreTextLabelStyle;
    Label yourScoreTextLabel;




    BitmapFont currentScoreText;





    public HighScoreScreen(MyGdxGame game){
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, camera);
        viewport.apply();
        camera.position.set(GAME_WORLD_WIDTH / 2, GAME_WORLD_HEIGHT / 2, 0);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        //Sounds & Music
        clickSound = Gdx.audio.newSound(Gdx.files.internal("buttonClick.mp3"));
        legitnessSong = Gdx.audio.newMusic(Gdx.files.internal("./last screen assets/thatWasLegitnessSong.mp3"));

        //Background
        backgroundReplayTexture = new Texture(Gdx.files.internal("./last screen assets/backgroundReplay.png"));
        backgroundReplayTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        backgroundReplayImage = new Image(backgroundReplayTexture);
        backgroundReplayImage.setSize(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT);

        //Facebook Button
        facebookButtonTexture = new Texture(Gdx.files.internal("./last screen assets/facebookButton.png"));
        facebookButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Button.ButtonStyle facebookButtonStyle = new Button.ButtonStyle();
        facebookButtonStyle.up = new TextureRegionDrawable(new TextureRegion(facebookButtonTexture));
        facebookButtonStyle.down = new TextureRegionDrawable(new TextureRegion(facebookButtonTexture));
        facebookButton = new Button(facebookButtonStyle);
        facebookButton.setSize(121, 121);
        facebookButton.setPosition(270-(121/2), 946-(121/2));
        facebookButtonActionListener();

        //Twitter Button
        twitterButtonTexture = new Texture(Gdx.files.internal("./last screen assets/twitterButton.png"));
        twitterButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Button.ButtonStyle twitterButtonStyle = new Button.ButtonStyle();
        twitterButtonStyle.up = new TextureRegionDrawable(new TextureRegion(twitterButtonTexture));
        twitterButtonStyle.down = new TextureRegionDrawable(new TextureRegion(twitterButtonTexture));
        twitterButton = new Button(twitterButtonStyle);
        twitterButton.setSize(121, 121);
        twitterButton.setPosition(810-(121/2), 946-(121/2));
        twitterButtonActionListener();

        //Home Button
        homeButtonTexture = new Texture(Gdx.files.internal("./last screen assets/homeButton.png"));
        homeButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Button.ButtonStyle homeButtonStyle = new Button.ButtonStyle();
        homeButtonStyle.up = new TextureRegionDrawable(new TextureRegion(homeButtonTexture));
        homeButtonStyle.down = new TextureRegionDrawable(new TextureRegion(homeButtonTexture));
        homeButton = new Button(homeButtonStyle);
        homeButton.setSize(121, 121);
        homeButton.setPosition(540-(121/2), 946-(121/2));
        homeButtonActionListener();

        //Replay Button
        replayButtonTexture = new Texture(Gdx.files.internal("./last screen assets/replayButton.png"));
        replayButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Button.ButtonStyle replayButtonStyle = new Button.ButtonStyle();
        replayButtonStyle.up = new TextureRegionDrawable(new TextureRegion(replayButtonTexture));
        replayButtonStyle.down = new TextureRegionDrawable(new TextureRegion(replayButtonTexture));
        replayButton = new Button(replayButtonStyle);
        replayButton.setPosition(540-replayButton.getWidth()/2, 710 - replayButton.getHeight()/2);
        replayButtonActionListener();

        //Legitness Button
        legitnessButtonButtonTexture = new Texture(Gdx.files.internal("./last screen assets/legitnessButtonButton.png"));
        legitnessButtonButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Button.ButtonStyle legitnessButtonStyle = new Button.ButtonStyle();
        legitnessButtonStyle.up = new TextureRegionDrawable(new TextureRegion(legitnessButtonButtonTexture));
        legitnessButtonStyle.down = new TextureRegionDrawable(new TextureRegion(legitnessButtonButtonTexture));
        legitnessButtonButton = new Button(legitnessButtonStyle);
        legitnessButtonButton.setPosition(540-legitnessButtonButton.getWidth()/2, 502.02f-legitnessButtonButton.getHeight()/2);

        //Font Generator
        generator = new FreeTypeFontGenerator(Gdx.files.internal("./Universal Assets/Porter Medium.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 141;
        parameter.magFilter = Texture.TextureFilter.MipMapLinearNearest;
        parameter.minFilter = Texture.TextureFilter.MipMapLinearNearest;

        //Your score text
        yourScoreTextFont = generator.generateFont(141);
        yourScoreTextLabelStyle = new Label.LabelStyle(yourScoreTextFont, Color.BLACK);
        yourScoreTextLabel = new Label("YOUR SCORE", yourScoreTextLabelStyle);
        yourScoreTextLabel.setAlignment(3);
        yourScoreTextLabel.setPosition(540 - yourScoreTextLabel.getWidth()/2, 1700f - yourScoreTextLabel.getHeight()/2);

        //High Score Text
        highScoreTextFont = generator.generateFont(120);
        highScoreTextLabelStyle = new Label.LabelStyle(highScoreTextFont, Color.BLACK);
        highScoreTextLabel = new Label("HIGH SCORE: " + String.valueOf(highScore) , highScoreTextLabelStyle);
        highScoreTextLabel.setAlignment(3);
        highScoreTextLabel.setPosition(540 - highScoreTextLabel.getWidth()/2, 1119 - highScoreTextLabel.getHeight()/2);

        //Current Score Text
        currentScoreTextFont = generator.generateFont(535);
        currentScoreTextLabelStyle = new Label.LabelStyle(currentScoreTextFont, Color.RED);
        currentScoreTextLabel = new Label(String.valueOf(score), currentScoreTextLabelStyle);
        currentScoreTextLabel.setAlignment(3);
        currentScoreTextLabel.setPosition(540 - currentScoreTextLabel.getWidth()/2, 1422- currentScoreTextLabel.getHeight()/2);


        legitnessSong.play();
        legitnessSong.setLooping(true);

        stage.addActor(backgroundReplayImage);
        stage.addActor(facebookButton);
        stage.addActor(twitterButton);
        stage.addActor(homeButton);
        stage.addActor(replayButton);
        stage.addActor(legitnessButtonButton);
        stage.addActor(yourScoreTextLabel);
        stage.addActor(highScoreTextLabel);
        stage.addActor(currentScoreTextLabel);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));

        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

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

    }

    public void facebookButtonActionListener(){
        facebookButton.addListener(new ClickListener(){
           public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){

               return true;
           }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Facebook Button pressed");
                legitnessSong.stop();
            }

        });

    }
    public void homeButtonActionListener(){
        homeButton.addListener(new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){

                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Home Button Pressed");
                legitnessSong.stop();
                game.setScreen(new MainMenuScreen(game));
            }

        });

    }
    public void twitterButtonActionListener(){
        twitterButton.addListener(new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){

                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Twitter Button Pressed");
                legitnessSong.stop();

            }

        });

    }
    public void replayButtonActionListener(){
        replayButton.addListener(new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){

                clickSound.play();
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                legitnessSong.stop();
                game.setScreen(new PlayScreen(game));
            }

        });

    }
}
