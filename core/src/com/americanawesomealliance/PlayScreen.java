package com.americanawesomealliance;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Brannon on 10/5/2015.
 */
public class PlayScreen implements Screen {




    private OrthographicCamera camera;
    private Viewport viewport;
    SpriteBatch batch;

    int highScore;

    BufferedReader in;
    int universe = 1;

    Timer timer;
    Timer.Task task;

    Timer timer2;
    Timer.Task task2;

    Timer timer3;
    Timer.Task task3;

    Timer timer4;
    Timer.Task task4;

    HighScoreText highScoreWriter = new HighScoreText();

    static int clickCounter = 0;


    Sound countdownInitial;
    Sound countdownFinal;

    //////////

    private int counter = 0;
    private static int score = 0;
    private int countdown = 3;



    String seconds = "15";
    String tenthSeconds = "00";

    public PlayScreen() {

    }

    public String getSeconds() {
        return seconds;
    }

    public void setSeconds(String seconds) {
        this.seconds = seconds;
    }

    public String getTenthSeconds() {
        return tenthSeconds;
    }

    public void setTenthSeconds(String tenthSeconds) {
        this.tenthSeconds = tenthSeconds;
    }

    //assets
    Texture legitnessButtonTexture;
    Texture legitnessButtonTexturePressed;
    Button legitnessButton;
    Image legitnessImage;

    Texture rimTexture;
    Image rim;

    Texture playScreenBackgroundTexture;
    Image playScreenBackgroundImage;

    Texture transparentCoverTexture;
    Image transparentCoverImage;

    Texture blackBarTexture;
    Image blackBar;

    Texture oneTexture;
    Image one;

    Texture twoTexture;
    Image two;

    Texture threeTexture;
    Image three;

    Texture fourTexture;
    Image four;

    Texture fiveTexture;
    Image five;

    Texture sixTexture;
    Image six;

    Texture sevenTexture;
    Image seven;

    Texture eightTexture;
    Image eight;

    Texture nineTexture;
    Image nine;

    Texture tenTexture;
    Image ten;



    Sound legitnessSound;

    float x = 1.0f;

    MyGdxGame game;

    final float GAME_WORLD_WIDTH = 1080;
    final float GAME_WORLD_HEIGHT = 1920;

    private Stage stage;
    Label label;
    Label.LabelStyle countdownLabeStyle;

    Label instructions;
    Label.LabelStyle instructionsLabelStyle;

    Label scoreLabel;
    Label.LabelStyle scoreLabelStyle;

    Label timeDown;
    Label.LabelStyle timeDownLabelStyle;

    public PlayScreen(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {

        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        viewport = new StretchViewport(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, camera);
        viewport.apply();
        camera.position.set(GAME_WORLD_WIDTH / 2, GAME_WORLD_HEIGHT / 2, 0);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        legitnessSound = Gdx.audio.newSound(Gdx.files.internal("legitnessSample.mp3"));
        countdownInitial = Gdx.audio.newSound(Gdx.files.internal("countdownInitial.mp3"));
        countdownFinal = Gdx.audio.newSound(Gdx.files.internal("countdownFinal.mp3"));

        ///Meter////
        blackBarTexture = new Texture(Gdx.files.internal("box.png"));
        blackBarTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        blackBar = new Image(blackBarTexture);
        blackBar.setPosition(540 - blackBar.getWidth()/2, 1692 - blackBar.getHeight()/2);

        oneTexture = new Texture(Gdx.files.internal("1.png"));
        one = new Image(oneTexture);
        one.setPosition(110 - one.getWidth()/2, 1695 - one.getHeight()/2);

        twoTexture = new Texture(Gdx.files.internal("2.png"));
        two = new Image(twoTexture);
        two.setPosition(203 - two.getWidth()/2, 1692.5f - two.getHeight()/2);

        threeTexture = new Texture(Gdx.files.internal("3.png"));
        three = new Image(threeTexture);
        three.setPosition(300 - three.getWidth()/2, 1693 - three.getHeight()/2);

        fourTexture = new Texture(Gdx.files.internal("4.png"));
        four = new Image(fourTexture);
        four.setPosition(398 - four.getWidth()/2, 1694.5f - four.getHeight()/2);

        fiveTexture = new Texture(Gdx.files.internal("5.png"));
        five = new Image(fiveTexture);
        five.setPosition(492 - five.getWidth()/2, 1690.5f - five.getHeight()/2);

        sixTexture = new Texture(Gdx.files.internal("6.png"));
        six = new Image(sixTexture);
        six.setPosition(589.5f - six.getWidth()/2, 1693.5f - six.getHeight()/2);

        sevenTexture = new Texture(Gdx.files.internal("7.png"));
        seven = new Image(sevenTexture);
        seven.setPosition(684 - seven.getWidth()/2, 1690.5f - seven.getHeight()/2);

        eightTexture = new Texture(Gdx.files.internal("8.png"));
        eight = new Image(eightTexture);
        eight.setPosition(778 - eight.getWidth()/2, 1692.5f - eight.getHeight()/2);

        nineTexture = new Texture(Gdx.files.internal("9.png"));
        nine = new Image(nineTexture);
        nine.setPosition(875.5f - nine.getWidth()/2, 1693 - nine.getHeight()/2);

        tenTexture = new Texture(Gdx.files.internal("10.png"));
        ten = new Image(tenTexture);
        ten.setPosition(975.5f - ten.getWidth()/2, 1693.5f - ten.getHeight()/2);



        //Background/////////////////
        playScreenBackgroundTexture = new Texture(Gdx.files.internal("menuScreenBackground.png"));
        playScreenBackgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        playScreenBackgroundImage = new Image(playScreenBackgroundTexture);
        playScreenBackgroundImage.setSize(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT);
        /////////////////////////////

        //Play Screen Button///////////////////////////////////////////////////////////////////////////////////////////////
        legitnessButtonTexture = new Texture(Gdx.files.internal("./Main Menu Screen Assets/legitnessButton.png"));
        legitnessButtonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        legitnessButtonTexturePressed = new Texture(Gdx.files.internal("./Main Menu Screen Assets/legitnessPressedButton.png"));
        legitnessButtonTexturePressed.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(legitnessButtonTexture));
        buttonStyle.down = new TextureRegionDrawable(new TextureRegion(legitnessButtonTexturePressed));
        legitnessButton = new Button(buttonStyle);
        legitnessButton.setSize(531, 531);
        legitnessButton.setPosition(GAME_WORLD_WIDTH / 2, GAME_WORLD_HEIGHT / 2, 0);

        legitnessImage = new Image(legitnessButtonTexture);
        legitnessImage.setSize(531,531);
        legitnessImage.setPosition(GAME_WORLD_WIDTH / 2, GAME_WORLD_HEIGHT / 2, 0);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        rimTexture = new Texture("./Main Menu Screen Assets/buttonRim.png");
        rim = new Image(rimTexture);
        rim.setSize(679, 679);
        rim.setPosition(GAME_WORLD_WIDTH / 2 , GAME_WORLD_HEIGHT / 2, 0);


        ///Transparent Cover////////
        transparentCoverTexture = new Texture(Gdx.files.internal("transparentOverlay.png"));
        transparentCoverTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        transparentCoverImage = new Image(transparentCoverTexture);
        transparentCoverImage.setSize(1000, 1840);
        transparentCoverImage.setPosition(GAME_WORLD_WIDTH / 2 - transparentCoverImage.getWidth() / 2, GAME_WORLD_HEIGHT / 2 - transparentCoverImage.getHeight() / 2);

        //Font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("./Universal Assets/Porter Medium.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        BitmapFont countdownFont = generator.generateFont(300);
        BitmapFont ugh = generator.generateFont(200);
        countdownLabeStyle = new Label.LabelStyle(countdownFont, Color.WHITE);
        label = new Label(String.valueOf(countdown), countdownLabeStyle);
        label.setAlignment(3);
        label.setPosition(GAME_WORLD_WIDTH / 2 - label.getWidth() / 2, GAME_WORLD_HEIGHT / 1.2f - label.getHeight() / 2);

        BitmapFont instructionsFont = generator.generateFont(100);
        instructionsLabelStyle = new Label.LabelStyle(instructionsFont, Color.WHITE);
        instructions = new Label("PRESS THE BUTTON AS MANY TIMES AS YOU CAN IN 15 SECONDS!", instructionsLabelStyle);
        Table table = new Table();
        instructions.setWrap(true);
        instructions.setAlignment(3);
        instructions.setWidth(100);
        table.add(instructions).width(800);
        table.setPosition(GAME_WORLD_WIDTH/2, GAME_WORLD_HEIGHT/2);

        scoreLabelStyle = new Label.LabelStyle(countdownFont, Color.BLACK);
        scoreLabel = new Label(String.valueOf(score), scoreLabelStyle);
        scoreLabel.setAlignment(3);
        scoreLabel.setPosition(GAME_WORLD_WIDTH/2 - label.getWidth()/2, GAME_WORLD_HEIGHT/8);

        timeDownLabelStyle = new Label.LabelStyle(ugh, Color.BLACK);
        timeDown = new Label("00:"+seconds+":"+tenthSeconds, timeDownLabelStyle);
        timeDown.setAlignment(3);
        timeDown.setPosition(GAME_WORLD_WIDTH/2 -400 , GAME_WORLD_HEIGHT*0.7f);




        legitnessButtonActionListener();

        stage.addActor(playScreenBackgroundImage);
        stage.addActor(rim);
        stage.addActor(legitnessButton);
        stage.addActor(scoreLabel);

        stage.addActor(ten);
        stage.addActor(nine);
        stage.addActor(eight);
        stage.addActor(seven);
        stage.addActor(six);
        stage.addActor(five);
        stage.addActor(four);
        stage.addActor(three);
        stage.addActor(two);
        stage.addActor(one);
        stage.addActor(blackBar);

        stage.addActor(timeDown);
        stage.addActor(transparentCoverImage);
        stage.addActor(label);
        stage.addActor(table);

        countdownInitial.play();
       // System.out.println(countdown);


        timer();
        timer3();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        try {
            FileReader();
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));

        stage.draw();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.end();

        //When the game ends this writes the score to the text file
       if(universe == 0){
           System.out.println(score);
            legitnessButton.remove();
            stage.addActor(legitnessImage);
           try {
           BufferedReader in = new BufferedReader(new FileReader("./High Score Directory/High Score.txt"));
               highScore = Integer.valueOf(in.readLine());

               if(score > highScore) {

                   highScoreWriter.textWriter(score);
               }

               timeDown.remove();
               timeDown = new Label("00:00:00", timeDownLabelStyle);
               stage.addActor(timeDown);
               timeDown.setAlignment(3);
               timeDown.setPosition(GAME_WORLD_WIDTH/2 -400 , GAME_WORLD_HEIGHT*0.7f);
               HighScoreScreen hs = new HighScoreScreen(game);
               System.out.println(score);
                game.setScreen(hs);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

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

    public int getClickCounter() {
        return clickCounter;
    }

    public void setClickCounter(int clickCounter) {
        this.clickCounter = clickCounter;
    }

    public void legitnessButtonActionListener() {
        legitnessButton.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //System.out.println("Touch Down");
                legitnessSound.stop();
                legitnessSound.play();
                score++;
                clickCounter++;

                if(score < 10) {
                    scoreLabel.remove();
                    scoreLabel.setAlignment(3);
                    scoreLabel = new Label(String.valueOf(score), scoreLabelStyle);
                    scoreLabel.setPosition(GAME_WORLD_WIDTH / 2 - label.getWidth() / 2, GAME_WORLD_HEIGHT / 8);
                    stage.addActor(scoreLabel);
                }

                if(score >= 10 && score < 100){
                    scoreLabel.remove();
                    scoreLabel.setAlignment(3);
                    scoreLabel = new Label(String.valueOf(score), scoreLabelStyle);
                    scoreLabel.setPosition(GAME_WORLD_WIDTH / 2 - label.getWidth() / 2 -60, GAME_WORLD_HEIGHT / 8);
                    stage.addActor(scoreLabel);
                }

                if(score >= 100 && score < 1000){
                    scoreLabel.remove();
                    scoreLabel.setAlignment(3);
                    scoreLabel = new Label(String.valueOf(score), scoreLabelStyle);
                    scoreLabel.setPosition(GAME_WORLD_WIDTH / 2 - label.getWidth() / 2 -125, GAME_WORLD_HEIGHT / 8);
                    stage.addActor(scoreLabel);
                }




                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
               // System.out.println("Touch Up");


            }
        });

    }

    public void timer() {
        timer = new Timer();
        task = new Timer.Task() {
            @Override
            public void run() {
                if (counter < 2) {
                    countdownInitial.play();
                    label.remove();
                    countdown--;
                    //System.out.println(countdown);
                    label = new Label(String.valueOf(countdown), countdownLabeStyle);
                    label.setAlignment(3);
                    label.setPosition(GAME_WORLD_WIDTH / 2 - label.getWidth() / 2, GAME_WORLD_HEIGHT / 1.2f - label.getHeight() / 2);
                    stage.addActor(label);

                }
                if (counter == 2) {
                    countdownFinal.play();
                    label.remove();
                    countdown--;
                   // System.out.println(countdown);
                    label = new Label(String.valueOf(countdown), countdownLabeStyle);
                    label.setAlignment(3);
                    label.setPosition(GAME_WORLD_WIDTH / 2 - label.getWidth() / 2, GAME_WORLD_HEIGHT / 1.2f - label.getHeight() / 2);
                    stage.addActor(label);

                }


                counter++;
                if (counter == 4) {
                    transparentCoverImage.remove();
                    label.remove();
                    instructions.remove();
                    timer2();
                }

            }
        };

        timer.scheduleTask(task, 1, 1, 3);

    }

    public void timer2(){
        timer2 = new Timer();
        task2 = new Timer.Task(){
            @Override
            public void run(){


                    if (tenthSeconds.equals("00")) {
                        timeDown.remove();
                        int numberSeconds = Integer.valueOf(getSeconds());
                        numberSeconds--;
                        setSeconds(numberZeroer(numberSeconds));
                        setTenthSeconds("99");
                        timeDown = new Label("00:" + getSeconds() + ":" + getTenthSeconds(), timeDownLabelStyle);
                        timeDown.setAlignment(3);
                        timeDown.setPosition(GAME_WORLD_WIDTH/2 -400 , GAME_WORLD_HEIGHT*0.7f);
                        stage.addActor(timeDown);
                    }

                    if (!(tenthSeconds.equals("00") )) {
                        timeDown.remove();
                        int numberTenth = Integer.valueOf(getTenthSeconds());
                        numberTenth--;
                        setTenthSeconds(numberZeroer(numberTenth));
                        timeDown = new Label("00:" + getSeconds() + ":" + getTenthSeconds(), timeDownLabelStyle);
                        timeDown.setAlignment(3);
                        timeDown.setPosition(GAME_WORLD_WIDTH/2 -400 , GAME_WORLD_HEIGHT*0.7f);
                        stage.addActor(timeDown);

                    }

                if(seconds.equals("00")&& tenthSeconds.equals("00")){
                    timer2.stop();
                    timeDown.remove();
                    timeDown = new Label("00:00:00", timeDownLabelStyle);
                   stage.addActor(timeDown);
                    timeDown.setAlignment(3);
                    timeDown.setPosition(GAME_WORLD_WIDTH/2 -400 , GAME_WORLD_HEIGHT*0.7f);
                    universe = 0;
                }





            }

        };
        timer2.scheduleTask(task2,1,0.01f,10000);
    }

    public void timer4(){
        timer4 = new Timer();
        task4 = new Timer.Task(){
            @Override
            public void run(){
                timeDown.remove();
                timeDown = new Label("00:00:00", timeDownLabelStyle);
                // stage.addActor(timeDown);
                timeDown.setAlignment(3);
                timeDown.setPosition(GAME_WORLD_WIDTH/2 -400 , GAME_WORLD_HEIGHT*0.7f);
                HighScoreScreen highScoreScreen = new HighScoreScreen(game);
                highScoreScreen.score = score;
                highScoreScreen.highScore = highScore;
                game.setScreen(highScoreScreen);
            }

        };
        timer4.scheduleTask(task4,1,1,1);
    }

    public void timer3(){
        timer3 = new Timer();
        task3 = new Timer.Task(){
            @Override
            public void run(){

                if(getClickCounter() == 0){
                    ten.remove();
                    nine.remove();
                    eight.remove();
                    seven.remove();
                    six.remove();
                    five.remove();
                    four.remove();
                    three.remove();
                    two.remove();
                    one.remove();
                    setClickCounter(0);
                }

                if(getClickCounter() == 1){
                    ten.remove();
                    nine.remove();
                    eight.remove();
                    seven.remove();
                    six.remove();
                    five.remove();
                    four.remove();
                    three.remove();
                    two.remove();
                    one.remove();
                    blackBar.remove();

                    stage.addActor(one);
                    stage.addActor(blackBar);
                    setClickCounter(0);
                }

                if(getClickCounter() == 2){
                    ten.remove();
                    nine.remove();
                    eight.remove();
                    seven.remove();
                    six.remove();
                    five.remove();
                    four.remove();
                    three.remove();
                    two.remove();
                    one.remove();
                    blackBar.remove();

                    stage.addActor(one);
                    stage.addActor(two);
                    stage.addActor(blackBar);
                    setClickCounter(0);
                }

                if(getClickCounter() == 3){
                    ten.remove();
                    nine.remove();
                    eight.remove();
                    seven.remove();
                    six.remove();
                    five.remove();
                    four.remove();
                    three.remove();
                    two.remove();
                    one.remove();
                    blackBar.remove();

                    stage.addActor(one);
                    stage.addActor(two);
                    stage.addActor(three);
                    stage.addActor(blackBar);
                    setClickCounter(0);
                }

                if(getClickCounter() == 4){
                    ten.remove();
                    nine.remove();
                    eight.remove();
                    seven.remove();
                    six.remove();
                    five.remove();
                    four.remove();
                    three.remove();
                    two.remove();
                    one.remove();
                    blackBar.remove();

                    stage.addActor(one);
                    stage.addActor(two);
                    stage.addActor(three);
                    stage.addActor(four);
                    stage.addActor(blackBar);
                    setClickCounter(0);
                }

                if(getClickCounter() == 5){
                    ten.remove();
                    nine.remove();
                    eight.remove();
                    seven.remove();
                    six.remove();
                    five.remove();
                    four.remove();
                    three.remove();
                    two.remove();
                    one.remove();
                    blackBar.remove();

                    stage.addActor(one);
                    stage.addActor(two);
                    stage.addActor(three);
                    stage.addActor(four);
                    stage.addActor(five);
                    stage.addActor(blackBar);
                    setClickCounter(0);
                }

                if(getClickCounter() == 6){
                    ten.remove();
                    nine.remove();
                    eight.remove();
                    seven.remove();
                    six.remove();
                    five.remove();
                    four.remove();
                    three.remove();
                    two.remove();
                    one.remove();
                    blackBar.remove();

                    stage.addActor(one);
                    stage.addActor(two);
                    stage.addActor(three);
                    stage.addActor(four);
                    stage.addActor(five);
                    stage.addActor(six);
                    stage.addActor(blackBar);
                    setClickCounter(0);
                }

                if(getClickCounter() == 7){
                    ten.remove();
                    nine.remove();
                    eight.remove();
                    seven.remove();
                    six.remove();
                    five.remove();
                    four.remove();
                    three.remove();
                    two.remove();
                    one.remove();
                    blackBar.remove();

                    stage.addActor(one);
                    stage.addActor(two);
                    stage.addActor(three);
                    stage.addActor(four);
                    stage.addActor(five);
                    stage.addActor(six);
                    stage.addActor(seven);
                    stage.addActor(blackBar);
                    setClickCounter(0);
                }

                if(getClickCounter() == 8){
                    ten.remove();
                    nine.remove();
                    eight.remove();
                    seven.remove();
                    six.remove();
                    five.remove();
                    four.remove();
                    three.remove();
                    two.remove();
                    one.remove();
                    blackBar.remove();

                    stage.addActor(one);
                    stage.addActor(two);
                    stage.addActor(three);
                    stage.addActor(four);
                    stage.addActor(five);
                    stage.addActor(six);
                    stage.addActor(seven);
                    stage.addActor(eight);
                    stage.addActor(blackBar);
                    setClickCounter(0);
                }

                if(getClickCounter() == 9){
                    ten.remove();
                    nine.remove();
                    eight.remove();
                    seven.remove();
                    six.remove();
                    five.remove();
                    four.remove();
                    three.remove();
                    two.remove();
                    one.remove();
                    blackBar.remove();

                    stage.addActor(one);
                    stage.addActor(two);
                    stage.addActor(three);
                    stage.addActor(four);
                    stage.addActor(five);
                    stage.addActor(six);
                    stage.addActor(seven);
                    stage.addActor(eight);
                    stage.addActor(nine);
                    stage.addActor(blackBar);
                    setClickCounter(0);
                }

                if(getClickCounter() >= 10){
                    ten.remove();
                    nine.remove();
                    eight.remove();
                    seven.remove();
                    six.remove();
                    five.remove();
                    four.remove();
                    three.remove();
                    two.remove();
                    one.remove();
                    blackBar.remove();

                    stage.addActor(one);
                    stage.addActor(two);
                    stage.addActor(three);
                    stage.addActor(four);
                    stage.addActor(five);
                    stage.addActor(six);
                    stage.addActor(seven);
                    stage.addActor(eight);
                    stage.addActor(nine);
                    stage.addActor(ten);
                    stage.addActor(blackBar);
                    setClickCounter(0);
                }


            }

        };
        timer3.scheduleTask(task3,0,0.77f,10000);
    }


    public void FileReader() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("./High Score Directory/High Score.txt"));
        StringBuilder sb = new StringBuilder();
        sb.append(reader.readLine());
        //System.out.println(sb.toString());


    }

    String numberZeroer(int x){

        if(x < 10){
            String y = "0"+Integer.toString(x);
            return y;
        }

        else{
            String y = Integer.toString(x);
            return y;
        }

    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
