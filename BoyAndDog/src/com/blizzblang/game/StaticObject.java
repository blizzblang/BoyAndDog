package com.blizzblang.game;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import com.blizzblang.opengl.VBO;
import com.blizzblang.opengl.Vertex;

public class StaticObject extends Entity{

	public StaticObject(int[] pos,int[] dim,Texture Tex) {
		super(EntityType.Prop);
		rendered = new VBO();
		rendered.addTexture(Tex.getTextureID());
		Pos = new float[]{pos[0],pos[1],0};
		Dim = new float[]{dim[0],dim[1]};
		Vertex bl = new Vertex(); bl.setXYZ(0, 0, 0);bl.setST(new float[]{0,0});
		Vertex br = new Vertex(); br.setXYZ(Dim[0], 0, 0);br.setST(new float[]{-1,0});
		Vertex ur = new Vertex(); ur.setXYZ(Dim[0], Dim[1], 0);ur.setST(new float[]{-1,1});
		Vertex ul = new Vertex(); ul.setXYZ(0, Dim[1], 0);ul.setST(new float[]{0,1});
		
		rendered.addVertex(bl);rendered.addVertex(ul);rendered.addVertex(ur);
		rendered.addVertex(br);rendered.addVertex(ur);rendered.addVertex(bl);
		rendered.finalize();
	}

	@Override
	public void Tick(ArrayList<Entity> i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Render(boolean shader) {
		rendered.render(this, true);
		
	}

}
