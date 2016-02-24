package com.blizzblang.input;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class ImgLoader 
{
private static ArrayList<String> Directories = new ArrayList<String>();
private static ArrayList<Texture> Textures = new ArrayList<Texture>();
	public ImgLoader()
	{
		
	}
	public static Texture loadImageGetTex(String i)
	{
		if(Directories.contains(i))
			return Textures.get(Directories.lastIndexOf(i));
		else
		{
			try {
				Texture temp = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(i));
				Textures.add(temp);
				Directories.add(i);	
				System.out.println("PNG "+i+", loaded  - "+temp.getImageWidth()+"x"+temp.getImageHeight());
				return temp;
			} catch (IOException e) {e.printStackTrace();}
			
		}
		System.out.println("Img Failed to load");
		return null;
	}
	public static int loadImage(String i)
	{
		if(Directories.contains(i))
			return Textures.get(Directories.lastIndexOf(i)).getTextureID();
		else
		{
			try {
				Texture temp = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(i));
				Textures.add(temp);
				Directories.add(i);	
				System.out.println("PNG "+i+", loaded  - "+temp.getImageWidth()+"x"+temp.getImageHeight());
				return Textures.get(Directories.lastIndexOf(i)).getTextureID();
			} catch (IOException e) {e.printStackTrace();}
			
		}
		System.out.println("Img Failed to load");
		return 0;
	}
	public static int getTextureFromIndex(int i)
	{
		return Textures.get(i).getTextureID();
	}
	public static int getTextureFromID(int i)
	{
		for(Texture t : Textures)
		{
			if(t.getTextureID() == i)
				return Textures.lastIndexOf(t);
		}
		return 0;
	}
	public static Texture getTexture(int i)
	{
		 for(Texture tex : Textures)
		 {
			 if(tex.getTextureID()==i)return tex;
		 }
		 return null;
	}
}
