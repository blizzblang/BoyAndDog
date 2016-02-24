package com.blizzblang.opengl;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import com.blizzblang.game.Main;
import com.blizzblang.input.ImgLoader;

public class SpriteBatch {
	ArrayList<double[]> cutouts = new ArrayList<double[]>();
	int[] Image;
	Texture tex;
	public SpriteBatch() {
		
	}
	public SpriteBatch(String g ) {
		loadSheet(g);
	}
	public void loadSheet(String s)
	{
		tex = ImgLoader.loadImageGetTex(s);
	}
	public void cutout(int x,int y,int w,int h)
	{
		float nw = (float)w-0.01f;
		float nh = (float)h-0.01f;
		double[] a = new double[]{(double)x/tex.getImageWidth(),(double)y/tex.getImageHeight(),(double)(x+nw)/tex.getImageWidth(),(double)(y+nh)/tex.getImageHeight()};
		cutouts.add(a);
	}
	public void cutoutAll(int x,int y,int w,int h)
	{
		float wslice = w/(float)tex.getImageWidth();
		float hslice = h/(float)tex.getImageHeight();
		float width   = tex.getImageWidth();
		float height  =tex.getImageHeight();
		for(int hl=0;hl<tex.getImageHeight();hl+=w)
		{
			for(int wl=0;wl<tex.getImageWidth();wl+=w)		
			{
				cutouts.add(new double[]{(wl/width),(hl/height),wslice,hslice});
			}
		}
	}
	public  double[] getCutout(int i)
	{
		return cutouts.get(i);
	}
	public float[][] getTriangulated(int cutout)
	{
		float[][] ret = new float[6][2];
		float AlmostZero = (float) (1E-25);
		float x =(float)- cutouts.get(cutout)[0];x+=AlmostZero;
		float y =(float)- cutouts.get(cutout)[1];y+=AlmostZero;
		float w =(float)- cutouts.get(cutout)[2];w-=AlmostZero;
		float h =(float)- cutouts.get(cutout)[3];h-=AlmostZero;
		
		ret[0] = new float[]{x,y};
		ret[1] = new float[]{x,y+h};
		ret[2] = new float[]{x+w,y};
		ret[3] = new float[]{x,y+h};
		ret[4] = new float[]{x+w,y+h};
		ret[5] = new float[]{x+w,y};


		return ret;
	}
	public Texture getTex() {
		return tex;
	}

}
