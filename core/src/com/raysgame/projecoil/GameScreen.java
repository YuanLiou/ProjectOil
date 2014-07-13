package com.raysgame.projecoil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.raysgame.projecoil.entity.Player;
import com.raysgame.projecoil.screen.ScreenManager;

public class GameScreen implements Screen{
	ProjectOil games;
	OrthographicCamera camera;
	SpriteBatch batch;
	Rectangle screenBounds;
    int characterX, characterY;
    final float speed = 6;
    float stateTime;    //動畫重要
    //AirPlane airPlane; 
    private Player player;
	
	public GameScreen(ProjectOil _games) {
		this.games = _games;
		
		//設定鏡頭
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 1920, 1080);    //設定一個1920x1080的鏡頭，並且縮小會自動拉申，不會失真
		//true 是為了翻轉座標系統 libGDX 用的跟一般數學是一樣的，所以要翻轉
		
		batch = new SpriteBatch();    //For Image Rendering
		
		//取得螢幕邊界
		screenBounds = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//System.out.println("graphics Width: " +screenBounds.width);
		//System.out.println("graphics height: " +screenBounds.height);
		
		//airPlane = new AirPlane(960 - 64, 540);
		player = new Player(new Vector2(960 - 64, 540), new Vector2(0, 0));    //新增主角飛機
		stateTime = 0f;    //還是不知為何要設這個
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.95F, 0.95F, 0.95F, 0.95F);    //test for rendering background color
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);    //畫布之類的吧
		if (ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().render(batch);
		stateTime += Gdx.graphics.getDeltaTime();    //應該取得螢幕刷新時間?
		camera.update();    //60fps 速率更新鏡頭
		batch.setProjectionMatrix(camera.combined);    //遊戲視窗內圖片大小縮放
		generalUpdate();    //控制移動
		batch.begin();
		//Rendering Code Here
		batch.draw(Assets.spriteBackgound, 0, 0);
		//batch.draw(airPlane.image, airPlane.bound.x , airPlane.bound.y);
		batch.end();
	}

	public void generalUpdate() {
				
		//牆壁
		/*
		if (airPlane.bound.x < 0) {
			airPlane.bound.x = 0;
		}
		if (airPlane.bound.y < 0) {
			airPlane.bound.y = 0;
		}
		
		if ((airPlane.bound.x + airPlane.bound.getWidth()) > 1920) {
			airPlane.bound.x = 1919 - airPlane.bound.getWidth();
		}
		if ((airPlane.bound.y + airPlane.bound.getHeight()) > 1080) {
			airPlane.bound.y = 1079 - airPlane.bound.getHeight();
		}*/

	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
    //給 Android 和 iOS 使用，以防使用者跳出App外面
	@Override
	public void pause() {	
		if (ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().pause();
	}

	@Override
	public void resume() {
		if (ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().resume();
	}
    //===========

	@Override
	public void dispose() {
		if (ScreenManager.getCurrentScreen() != null)
		    ScreenManager.getCurrentScreen().dispose();
	}
	
    //沒有使用
	@Override
	public void hide() {}
	
	@Override
	public void resize(int width, int height) {
		if (ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().resize(width, height);
	}

}
