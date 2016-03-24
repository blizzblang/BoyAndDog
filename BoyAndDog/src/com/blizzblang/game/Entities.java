package com.blizzblang.game;

import java.util.ArrayList;

public class Entities 
{
ArrayList<Entity> Ents = new ArrayList<Entity>();
ArrayList<Entity> a = new ArrayList<Entity>();
ArrayList<Entity> r = new ArrayList<Entity>();
	private void up()
	{
		Ents.addAll(a);a.clear();
		Ents.removeAll(r);r.clear();
	}
	public void add(Entity i)
	{
		a.add(i);
	}
	public void rem(Entity i)
	{
		r.add(i);
	}
	public void Update()
	{
		up();
		int D = Main.Delta;
		for(Entity i : Ents)
		{
			i.Tick(Ents);
			if(i instanceof Player)
			{
				if(i.getY() < 0)
				{
					System.out.println(i.getY() + " < 0");
					Main.State=GameState.GameOver;
				}
				else
				{
					System.out.println(i.getY() + " >= 0");
				}
				i.Rotation.y -= 0.0005*D;
			}
			for(Entity ii : Ents)
			{
				
				if(ii==i)continue;
				
				i.setX(i.getX()+i.Rotation.x*D);
				if(i.doesCollide(ii))
				{
					i.setX(i.getX()-i.Rotation.x*D);
				}
				
				i.setY(i.getY()+i.Rotation.y*D);
				if(i.doesCollide(ii))
				{
					i.setY(i.getY()-i.Rotation.y*D);
					if(i instanceof Player)
						((Player)i).canJump=true;
					
				}
				else
				{
					if(i instanceof Player)
					{
						if(i.Rotation.y > 0)
						((Player)i).canJump=false;
					}
				}
			}
			i.Rotation.x*=0.50f;
			//i.setX(i.getX()+i.Rotation.x);
			//i.setY(i.getY()+i.Rotation.y);
			//i.Rotation.x=0;i.Rotation.y=0;
		}
	}
	public void Render()
	{
		for(Entity i : Ents)
			i.Render();
	}
}
