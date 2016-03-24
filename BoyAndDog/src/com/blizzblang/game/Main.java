package com.blizzblang.game;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.tests.TestBox;

import com.blizzblang.game.GUI.Button;
import com.blizzblang.game.GUI.GUI;
import com.blizzblang.game.GUI.TextBox;
import com.blizzblang.input.ImgLoader;
import com.blizzblang.input.KeyInput;
import com.blizzblang.input.Timer;
import com.blizzblang.opengl.OpenGL;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
public class Main 
{
public static GameState State=GameState.Pause;
public static GUI screen;
public static KeyInput KeyIn = new KeyInput();
public static ImgLoader ImgLdr = new ImgLoader();
public static Camera cam;
public static Entities Ents = new Entities();
private static long lastFrame=0;
public static int Delta=1;
	public static void main(String[] args)
	{
		lastFrame=getTime();
		OpenGL.initOpenGL(800, 600);
		screen = new GUI();
		cam = new Camera(new float[]{0,0,-10});
		Button play = new Button("Play");play.init(screen);
		Button quit = new Button("Quit");quit.setPos(0,40);quit.init(screen);
		
		TextBox title = new TextBox("Don't Jump");title.setPos(0,64*4);title.init(screen);
		TextBox gameover = new TextBox("Game Over");gameover.setPos(0,64*4);gameover.init(screen);
		float tscore =(float) 9E7;
		TextBox score = new TextBox("Score: "+tscore);score.setPos(0,64*4);screen.addElement(score);score.changeSize(16);
		
		Player player = new Player();Ents.add(player);player.setY(120);
		StaticObject building = new StaticObject(new int[]{-256/2,-280}, new int[]{(int) (256*1.5f),(int) (256*1.5f)}, ImgLoader.loadImageGetTex("res/img/building.png"));Ents.add(building);
		
		while(!Display.isCloseRequested())
		{
			Delta=getDelta();
			OpenGL.LoopStart();
			KeyIn.Update();
			cam.update();
			screen.update();

			if(State==GameState.Quit)break;
			else if(State==GameState.MainMenu)
			{
				System.out.println("Main Menu");
				if(KeyIn.getKeyDown(Keyboard.KEY_RETURN))
				{
					State = GameState.Play;
					
				}
			}
			else if(State==GameState.Pause)
			{
				//System.out.println("Pause");
				cam.setOrtho(true);
				play.render();
				quit.render();
				title.render();
				
				if(play.clicked())
					{
					State=GameState.Play;
					play.unclick();
							}
				if(quit.clicked())System.exit(0);
				if(KeyIn.getKeyDown(Keyboard.KEY_RETURN))
				{
					State = GameState.Play;
					
				}
			}
			else if(State==GameState.Play)
			{
				if(player.canJump)
				tscore+=Delta;
				score.changeText("Score: "+tscore);

				cam.setOrtho(true);
				Ents.Update();
				Ents.Render();
				
				score.render();
				if(KeyIn.getKeyDown(Keyboard.KEY_ESCAPE))
				{
					State = GameState.Pause;
					
				}
			}
			else if(State==GameState.GameOver)
			{
				gameover.render();
				play.render();
				quit.render();
			if(play.clicked())
			{
				State=GameState.Play;
				
				player.setY(120);
				player.setX(20);
				play.unclick();
				tscore=0;
			}
			if(quit.clicked())System.exit(0);
			if(KeyIn.getKeyDown(Keyboard.KEY_RETURN))
			{
				State = GameState.Play;
				
				
				player.setY(120);
				player.setX(20);
				tscore=0;
			}
				
			}
			OpenGL.LoopEnd();
			
		}
		System.exit(0);
	}
	public static long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	public static int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
	         
	    return delta;
	}
}
enum GameState
{
MainMenu(0),Pause(1),Play(2),Quit(3),GameOver(4);
private int State;
	private GameState(int t)
	{
		State=t;
	}
	public void Pause()
	{
		State=Pause.gSID();
	}
	public void Play()
	{
		State=Play.gSID();
	}
	public void MainMenu()
	{
		State=MainMenu.gSID();
	}
	public int gSID(){return State;}
}
