package com.blizzblang.game;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.blizzblang.opengl.EntityShader;
import com.blizzblang.opengl.SpriteBatch;
import com.blizzblang.opengl.VBO;
import com.blizzblang.opengl.Vertex;

public class Player extends Entity
{
	private EntityShader sdr = new EntityShader();
	public boolean canJump=false;
	SpriteBatch Anim = new SpriteBatch("res/img/player.png");
	boolean walking=false;
	int Frame=0;
	int MaxFrame=2;
	public Player() 
	{
		super(EntityType.PlayerEntity);
		rendered=new VBO();
		rendered.addTexture(Anim.getTex().getTextureID());
		Anim.cutoutAll(0, 0, 16, 16);
		Pos = new float[]{3,3,0};
	}

	@Override
	public void Tick(ArrayList<Entity> i) 
	{
		Frame+=Main.Delta;Frame%=MaxFrame;
		boolean W_KEY = Main.KeyIn.getKeyDown(Keyboard.KEY_W);
		boolean A_KEY = Main.KeyIn.getKeyDown(Keyboard.KEY_A);
		boolean S_KEY = Main.KeyIn.getKeyDown(Keyboard.KEY_S);
		boolean D_KEY = Main.KeyIn.getKeyDown(Keyboard.KEY_D);
		boolean SP_KEY = Main.KeyIn.getKeyDown(Keyboard.KEY_SPACE);
		float Horizontal=0.1f;
		walking=false;
		if(D_KEY)
		{
			Rotation.x=Horizontal;
			walking=true;
		}
		if(A_KEY)
		{
			Rotation.x=-Horizontal;
			walking=true;
		}
		if(W_KEY && canJump)
		{
			Rotation.y=Horizontal;
			canJump=false;
		}
	}

	@Override
	public void Render(boolean shader) 
	{
		
		Matrix4f p=new Matrix4f();
		p.translate(new Vector3f(Pos[0],Pos[1],0));
		rendered.wipe();
		
		float Size=16;
		Dim[0] = Size;
		Dim[1] = Size;
		float[][] sprite;
		if(!walking)
		{
			if(canJump)
				sprite = Anim.getTriangulated(0);
			else
				sprite = Anim.getTriangulated(3);
		}
		else
		if(Rotation.x > 0)
		{
		sprite = Anim.getTriangulated(Frame+1);
		}
		else
		{
		sprite = Anim.getTriangulated(Frame+1,true,false);
		}
		
		

		Vertex bl = new Vertex(); bl.setXYZ(0, 0, 0);bl.setST(sprite[1]);
		Vertex br = new Vertex(); br.setXYZ(Size, 0, 0);br.setST(sprite[4]);
		Vertex ur = new Vertex(); ur.setXYZ(Size, Size, 0);ur.setST(sprite[2]);
		Vertex ul = new Vertex(); ul.setXYZ(0, Size, 0);ul.setST(sprite[0]);
		
		rendered.addVertex(bl);rendered.addVertex(ul);rendered.addVertex(ur);
		rendered.addVertex(br);rendered.addVertex(ur);rendered.addVertex(bl);
		rendered.finalize();
	
		rendered.render(p, true);

		
	}

}
