package com.blizzblang.game.GUI;

import com.blizzblang.opengl.VBO;
import com.blizzblang.opengl.Vertex;

public class TextBox extends GUIelement 
{
	String Text;
	int Size=32;
	
	public TextBox(String i)
	{
		rend = new VBO();
		Text=i;
		int[] str = new int[i.length()];
		
		rend.addTexture(text.getTex().getTextureID());
		dim[0] = Size*str.length;
		dim[1] = Size;
		for(int c=0;c<str.length;c++)
		{
			float[][] cut = text.getTriangulated(lookupLetter(""+i.charAt(c)));
			Vertex ul = new Vertex(); ul.setXYZ(Size*c, Size, 0);ul.setST(cut[0]);
			Vertex ur = new Vertex(); ur.setXYZ(Size*c+Size, Size, 0);ur.setST(cut[2]);
			Vertex bl = new Vertex(); bl.setXYZ(Size*c, 0, 0);bl.setST(cut[1]);
			Vertex br = new Vertex(); br.setXYZ(Size*c+Size , pos[1], 0);br.setST(cut[4]);
			rend.addVertex(bl);rend.addVertex(ul);rend.addVertex(ur);
			rend.addVertex(br);rend.addVertex(ur);rend.addVertex(bl);
		}
		rend.finalize();
		
	}
	public void changeSize(int i){Size=0+i;}
	public void changeText(String i)
	{
		rend.wipe();
		//rend = new VBO();
		Text=i;
		int[] str = new int[i.length()];
		
		rend.addTexture(text.getTex().getTextureID());
		dim[0] = Size*str.length;
		dim[1] = Size;
		for(int c=0;c<str.length;c++)
		{
			float[][] cut = text.getTriangulated(lookupLetter(""+i.charAt(c)));
			Vertex ul = new Vertex(); ul.setXYZ(+Size*c, Size, 0);ul.setST(cut[0]);
			Vertex ur = new Vertex(); ur.setXYZ(+Size*c+Size, Size, 0);ur.setST(cut[2]);
			Vertex bl = new Vertex(); bl.setXYZ(+Size*c, 0, 0);bl.setST(cut[1]);
			Vertex br = new Vertex(); br.setXYZ(+Size*c+Size , 0, 0);br.setST(cut[4]);
			rend.addVertex(bl);rend.addVertex(ul);rend.addVertex(ur);
			rend.addVertex(br);rend.addVertex(ur);rend.addVertex(bl);
		}
		rend.finalize();
	}
	@Override
	public void onMouseOver()
	{
		

		
	}
	public void Idle()
	{
		
		
	}
}
