package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.box2d.Box2DWorld;
import com.mygdx.game.map.Island;
import com.mygdx.game.map.Tile;

import java.util.ArrayList;
import java.util.Collections;

public class GameClass extends ApplicationAdapter {
	OrthographicCamera camera;
	Control control;
	SpriteBatch batch;
	Texture img;
	Island island;
	Hero hero;
	Box2DWorld box2D;

	// Display Size
	private int displayW;
	private int displayH;



	@Override
	public void create () {

		//loads textures
		Media.loadAssets();

		batch = new SpriteBatch();
		//img = new Texture("assets/badlogic.jpg");

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

		//Box2D
		box2D = new Box2DWorld();

		//Island
		island = new Island(box2D);

		//Hero
		hero = new Hero(island.getCenterTile().getPos(), box2D);
		island.getEntities().add(hero);


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

		Collections.sort(island.getEntities());

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


		//Draw all entities
		for (Entity e : island.getEntities()){
			e.draw(batch);
		}

		batch.end();

		//call tick method to draw debug lines, pass in control to check it has debug = true;
		box2D.tick(camera, control);


		//Reset world
		if(control.reset){
			island.reset(box2D);
			hero.reset(box2D, island.getCenterTile().getPos());
			island.getEntities().add(hero);
			control.reset = false;
		}


	} //render

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();

	} //dispose


} //class GameClass