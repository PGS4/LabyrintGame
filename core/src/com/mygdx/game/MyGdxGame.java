package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Rectangle;

import sun.rmi.runtime.Log;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    private OrthographicCamera camera;
    private Rectangle bucket;
    private float accelX;
    BitmapFont yourBitmapFontName;
	
	@Override
	public void create () {

		batch = new SpriteBatch();
		img = new Texture(Gdx.files.internal("maincharacter1.png"));

        yourBitmapFontName = new BitmapFont();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);


        bucket = new Rectangle();
        bucket.x = 800 / 2 - 64 / 2;
        bucket.y = 20;
        bucket.width = 64;
        bucket.height = 64;
	}


	@Override
	public void render () {

        accelX = Gdx.input.getAccelerometerX();

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();




        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        yourBitmapFontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        yourBitmapFontName.draw(batch, Math.floor(accelX)+"", 25, 100);

        batch.draw(img, bucket.x, bucket.y);
        batch.end();


        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.x = (int) touchPos.x - 64 / 2;
            bucket.y = (int) touchPos.y - 64 / 2;
        }


        if(Math.floor(accelX) == 0.0) {
            bucket.x = 20;
        }
	}
}
