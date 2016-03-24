package com.blizzblang.game.GUI;

import com.blizzblang.opengl.VBO;
import com.blizzblang.opengl.Vertex;

public class Button extends GUIelement
{
	String Text;
	int Size=34;
	
	public Button(String i)
	{
		Text=i;
		int[] str = new int[i.length()];
		
		rend.addTexture(text.getTex().getTextureID());
		dim[0] = Size*str.length;
		dim[1] = Size;
		for(int c=0;c<str.length;c++)
		{
			float[][] cut = text.getTriangulated(lookupLetter(""+i.charAt(c)));
			Vertex ul = new Vertex(); ul.setXYZ(pos[0]+Size*c, pos[1]+Size, 0);ul.setST(cut[0]);
			Vertex ur = new Vertex(); ur.setXYZ(pos[0]+Size*c+Size, pos[1]+Size, 0);ur.setST(cut[2]);
			Vertex bl = new Vertex(); bl.setXYZ(pos[0]+Size*c, pos[1], 0);bl.setST(cut[1]);
			Vertex br = new Vertex(); br.setXYZ(pos[0]+Size*c+Size , pos[1], 0);br.setST(cut[4]);
			rend.addVertex(bl);rend.addVertex(ul);rend.addVertex(ur);
			rend.addVertex(br);rend.addVertex(ur);rend.addVertex(bl);
		}
		rend.finalize();
	}
	@Override
	public void onMouseOver()
	{
		
		TxtSdr.setColor(0.5f, 0.5f, 0.5f);
		
	}
	public void Idle()
	{
		TxtSdr.setColor(0f, 1f, 1f);
		
	}
	public void unclick() {
		Clicked=false;
		
	}

}
