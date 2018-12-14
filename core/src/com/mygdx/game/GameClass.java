package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.map.Island;
import com.mygdx.game.map.Tile;

import java.util.ArrayList;

public class GameClass extends ApplicationAdapter {
	OrthographicCamera camera;
	Control control;
	SpriteBatch batch;
	Texture img;
	Island island;
	Hero hero;

	// Display Size
	private int displayW;
	private int displayH;



	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("assets/badlogic.jpg");

		// CAMERA
		displayW = Gdx.graphics.getWidth();
		displayH = Gdx.graphics.getHeight();

		// For 800x600 we will get 266*200
		int h = (int) (displayH/Math.floor(displayH/160));
		int w = (int) (displayW/(displayH/ (displayH/Math.floor(displayH/160))));

		camera = new OrthographicCamera(w,h);
		camera.zoom = .4f;

		// Used to capture Keyboard Input
		control = new Control(displayW, displayH, camera);
		Gdx.input.setInputProcessor(control);


		//loads textures
		Media.loadAssets();

		island = new Island();

		hero = new Hero(island.getCenterTile().getPos());

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// GAME LOGIC
		// Reset the direction values

		hero.update(control);
		camera.position.lerp(hero.getPos(), .1f);
		camera.update();

		// GAME DRAW
		batch.setProjectionMatrix(camera.combined);
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		batch.begin();
		//batch.draw(img, 0, 0);
		for (ArrayList<Tile> row : island.chunk.tiles){
			for (Tile tile : row){
				batch.draw(tile.getTexture(), tile.getPos().x, tile.getPos().y, tile.getSize(), tile.getSize() );
				if (tile.getSecondaryTexture() != null) {
					batch.draw(tile.getSecondaryTexture(), tile.getPos().x, tile.getPos().y, tile.getSize(), tile.getSize());
				}
			}
		}

		hero.draw(batch);
		batch.end();

	} //render

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();

	} //dispose


} //class GameClass