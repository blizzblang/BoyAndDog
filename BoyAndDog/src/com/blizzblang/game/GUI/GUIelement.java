package com.blizzblang.game.GUI;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.blizzblang.opengl.EntityShader;
import com.blizzblang.opengl.SpriteBatch;
import com.blizzblang.opengl.TextShader;
import com.blizzblang.opengl.VBO;

public abstract class GUIelement {
	protected int[] pos = new int[2];
	protected int[] dim = new int[2];;
	protected VBO rend;
	protected boolean Hover=false;
	protected boolean Clicked=false;
	protected static SpriteBatch text = new SpriteBatch("res/img/charmap.png",0, 0, 32, 32);
	protected TextShader TxtSdr = new TextShader();
	public GUIelement()
	{
		rend = new VBO();
	}
	public void render()
	{
		Matrix4f p=new Matrix4f();
		p.translate(new Vector3f(pos[0],pos[1],0));
	
		TxtSdr.Bind(p);
		rend.render(p, false);
		TxtSdr.unBind();
	}
	public void setPos(int x,int y)
	{
		setPos(new int[]{x,y});
	}
	public void init(GUI i)
	{
		i.addElement(this);
	}
	public void setPos(int[] p)
	{
		pos = new int[]{p[0],p[1]};
	}
	public void update(int Mx,int My,boolean Mc)
	{
		//System.out.println(Mx+" : "+My+" , "+Mc);
		Mx/=2;
		My/=2;
		Idle();
		if(Mx >= pos[0] && Mx <= pos[0]+dim[0]){
			if(My >= pos[1] && My <= pos[1]+dim[1])
			{
				if(!Mc)
				onMouseOver();
				else
				onMouseClick();
			}}

		
	}
	public void onMouseOver()
	{
		
	}
	public void onMouseClick()
	{
		Clicked=true;
	}
	public void onMouseOff()
	{
		
	}
	public boolean clicked() 
	{
		return Clicked;
	}
	public void Idle()
	{
		Clicked=false;
	}
	protected static int lookupLetter(String a)
	{
		a=a.trim();
		switch(a)
		{
			case "A":return 16*4+1;
			case "a":return 16*6+1;
			case "B":return 16*4+2;
			case "b":return 16*6+2;
			case "C":return 16*4+3;
			case "c":return 16*6+3;
			case "D":return 16*4+4;
			case "d":return 16*6+4;
			case "E":return 16*4+5;
			case "e":return 16*6+5;
			case "F":return 16*4+6;
			case "f":return 16*6+6;
			case "G":return 16*4+7;
			case "g":return 16*6+7;
			case "H":return 16*4+8;
			case "h":return 16*6+8;
			case "I":return 16*4+9;
			case "i":return 16*6+9;
			case "J":return 16*4+10;
			case "j":return 16*6+10;
			case "K":return 16*4+11;
			case "k":return 16*6+11;
			case "L":return 16*4+12;
			case "l":return 16*6+12;
			case "M":return 16*4+13;
			case "m":return 16*6+13;
			case "N":return 16*4+14;
			case "n":return 16*6+14;
			case "O":return 16*4+15;
			case "o":return 16*6+15;
			case "P":return 16*4+16;
			case "p":return 16*6+16;
			case "Q":return 16*4+17;
			case "q":return 16*6+17;
			case "R":return 16*4+18;
			case "r":return 16*6+18;
			case "S":return 16*4+19;
			case "s":return 16*6+19;
			case "T":return 16*4+20;
			case "t":return 16*6+20;
			case "U":return 16*4+21;
			case "u":return 16*6+21;
			case "V":return 16*4+22;
			case "v":return 16*6+22;
			case "W":return 16*4+23;
			case "w":return 16*6+23;
			case "X":return 16*4+24;
			case "x":return 16*6+24;
			case "Y":return 16*4+25;
			case "y":return 16*6+25;
			case "Z":return 16*4+26;
			case "z":return 16*6+26;
			case "0":return 16*3+0;
			case "1":return 16*3+1;
			case "2":return 16*3+2;
			case "3":return 16*3+3;
			case "4":return 16*3+4;
			case "5":return 16*3+5;
			case "6":return 16*3+6;
			case "7":return 16*3+7;
			case "8":return 16*3+8;
			case "9":return 16*3+9;
			case ".":return 16*2+14;
			case ":":return 16*3+10;
			case "'":return 16*2+7;
			case "":return 0;
			case "-":return 16*2+13;
			default:return 1;
		}
	}
}
