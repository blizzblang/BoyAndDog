package com.blizzblang.game.GUI;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;

public class GUI 
{
	ArrayList<GUIelement> elements = new ArrayList<GUIelement>();
	ArrayList<GUIelement> add = new ArrayList<GUIelement>();
	ArrayList<GUIelement> rem = new ArrayList<GUIelement>();
	private void up()
	{
		elements.addAll(add);
		elements.removeAll(rem);
		add.clear();
		rem.clear();
	}
	public GUI()
	{
		
	}
	public void addElement(GUIelement i)
	{
		add.add(i);
	}
	public void remElement(GUIelement i)
	{
		rem.add(i);
	}
	public void update()
	{
		up();
		for(GUIelement i : elements)
		{
			i.Clicked=false;
			i.update(Mouse.getX(), Mouse.getY(), Mouse.isButtonDown(0));
		}
	}

}