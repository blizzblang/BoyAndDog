package com.blizzblang.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import com.blizzblang.game.Main;

public class OpenGL 
{
	public static int Width;
	public static int Height =0;
	public static void initOpenGL(int x,int y)
	{
	      
        try {
            PixelFormat pixelFormat = new PixelFormat();
            ContextAttribs contextAtrributes = new ContextAttribs(3, 2)
                .withForwardCompatible(true)
                .withProfileCore(true);
             
            Display.setDisplayMode(new DisplayMode(x, y));
            Display.setTitle("NaN");
            Display.create(pixelFormat, contextAtrributes);
             
            GL11.glViewport(0, 0, x, y);
        } catch (LWJGLException e) {e.printStackTrace(); System.exit(-1);}
         
        // Setup an XNA like background color
        GL11.glClearColor(0.4f, 0.6f, 0.9f, 0f);
         
        // Map the internal OpenGL coordinate system to the entire screen
        Width = x;
        Height=y;
        GL11.glViewport(0, 0, x, y);
		GL11.glEnable(GL11.GL_DEPTH_TEST);  
		GL11.glDepthFunc(GL11.GL_LESS);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}

	public static void LoopStart() 
	{
		GL11.glViewport(0, 0, Width, Height);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.4f, 0.6f, 0.9f, 0f);
		Main.KeyIn.Update();
	}
	public static void ResetView() 
	{
		GL11.glViewport(0, 0, Width, Height);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0f);
		
	}
	public static void LoopEnd() 
	{
		Display.update();
		
	}

	public static float getAspectRatio() 
	{
	return Width/Height;	
	}

}
