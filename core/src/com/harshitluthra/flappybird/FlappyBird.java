package com.harshitluthra.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {
    SpriteBatch batch;
    Texture background;
    Texture[] birds;
    int flapState = 0;
    float birdY = 0;
    float velocity = 0;
    int gameState = 0;
    float gravity = 1.5f;
    Texture topTube, bottomTube;
    float gap = 400;
    float maxTubeOffset;
    Random randomgenerator;

    float tubeVelocity=4;
    int numberofTubes=4;
    float distanceBetweenTubes;
    float[] tubeX=new float[numberofTubes];
    float[] tubeOffset=new float[numberofTubes];

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("bg.png");
        birds = new Texture[2];
        birds[0] = new Texture("bird.png");
        birds[1] = new Texture("bird2.png");
        birdY = Gdx.graphics.getHeight() / 2 - birds[0].getHeight() / 2;
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        maxTubeOffset = Gdx.graphics.getHeight() / 2 - gap / 2 - 100;
        randomgenerator = new Random();
        distanceBetweenTubes=Gdx.graphics.getWidth() * 3/4;
        for(int i=0;i<numberofTubes;i++){
            tubeOffset[i] = (randomgenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);
            tubeX[i]=Gdx.graphics.getWidth() / 2 - topTube.getWidth() / 2+i*distanceBetweenTubes;

        }
        }

    @Override
    public void render() {
        if (Gdx.input.justTouched()) {

            gameState = 1;
        }
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (gameState != 0) {


            if (Gdx.input.justTouched()) {
                velocity = -20;
              } for(int i=0;i<numberofTubes;i++) {
                if (tubeX[i]< -topTube.getWidth()){
                    tubeX[i]+=numberofTubes*distanceBetweenTubes;
                }
                tubeX[i] -= tubeVelocity;
                batch.draw(topTube, tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i]);
                batch.draw(bottomTube, tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomTube.getHeight() + tubeOffset[i]);
            }
            if (birdY > 0 || velocity < 0) {
                velocity++;
                birdY -= velocity;
            }
        } else {
            if (Gdx.input.justTouched()) {

                gameState = 1;
            }
        }
        if (flapState == 0) flapState = 1;
        else flapState = 0;
        batch.draw(birds[flapState], Gdx.graphics.getWidth() / 2 - birds[flapState].getWidth() / 2, birdY);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();

    }
}
