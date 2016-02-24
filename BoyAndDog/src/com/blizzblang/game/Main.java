package com.blizzblang.game;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.blizzblang.input.ImgLoader;
import com.blizzblang.input.KeyInput;
import com.blizzblang.opengl.GUI;
import com.blizzblang.opengl.OpenGL;
import org.lwjgl.input.Keyboard;
public class Main 
{
public static GameState State=GameState.Pause;
public static GUI screen;
public static KeyInput KeyIn = new KeyInput();
public static ImgLoader ImgLdr = new ImgLoader();
public static Camera cam;
	public static void main(String[] args)
	{
		OpenGL.initOpenGL(800, 600);
		screen = new GUI();
		cam = new Camera(new float[]{0,0,-10});
		while(!Display.isCloseRequested())
		{
			OpenGL.LoopStart();
			KeyIn.Update();
			cam.update();
			if(State==GameState.Quit)break;
			if(State==GameState.MainMenu)
			{
				System.out.println("Main Menu");
				if(KeyIn.getKeyUp(Keyboard.KEY_RETURN))
				{
					State = GameState.Play;
					
				}
			}
			if(State==GameState.Pause)
			{
				//System.out.println("Pause");
				screen.drawString("Nigger", 0, 0, new Color(0.5f,0.5f,0.5f), 1);
				if(KeyIn.getKeyUp(Keyboard.KEY_RETURN))
				{
					State = GameState.Play;
					
				}
			}
			if(State==GameState.Play)
			{
				System.out.println("Play");
				
				if(KeyIn.getKeyUp(Keyboard.KEY_ESCAPE))
				{
					State = GameState.Pause;
					
				}
			}
			OpenGL.LoopEnd();
			
		}
		System.exit(0);
	}
}
enum GameState
{
MainMenu(0),Pause(1),Play(2),Quit(3);
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
