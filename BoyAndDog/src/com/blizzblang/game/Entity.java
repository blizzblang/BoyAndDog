package com.blizzblang.game;

import java.util.ArrayList;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.blizzblang.opengl.VBO;



public abstract class Entity
{
  protected float[] Pos;
  protected float[] Dim;
  protected VBO rendered;
  Vector3f Rotation = new Vector3f(0,0,0);
  private  Matrix4f modelMatrix = new Matrix4f();
  enum EntityType
  {
    PlayerEntity(0),NonPlayerEntity(1),Prop(2);
    private int Type;
    private EntityType(int i)
    {
      Type=i;
    }
  }
  protected EntityType EntType; 
  public Entity(EntityType i)
  {
  EntType = i;
  Pos = new float[]{0,0,0};
  Dim = new float[]{0,0};
  }
  public Matrix4f getMatrix()
  {
	  modelMatrix = new Matrix4f();
      Vector3f modelScale = new Vector3f(1,1,1);
      Vector3f modelPos = new Vector3f(Pos[0],Pos[1],Pos[2]);
      Matrix4f.scale(modelScale, modelMatrix, modelMatrix);
      Matrix4f.translate(modelPos, modelMatrix, modelMatrix);
      Matrix4f.rotate((float) Math.toRadians(Rotation.z), new Vector3f(0, 0, 1), modelMatrix, modelMatrix);
      Matrix4f.rotate((float) Math.toRadians(Rotation.y), new Vector3f(0, 1, 0), modelMatrix, modelMatrix);
      Matrix4f.rotate((float) Math.toRadians(Rotation.x), new Vector3f(1, 0, 0), modelMatrix, modelMatrix);
      return  modelMatrix;
  }
  public boolean doesCollide(Entity i)
  {
	  for(int x=0;x<Dim[0];x++)
		  for(int y=0;y<Dim[1];y++)
		  {
			  if(i.isInBox(Pos[0]+x, Pos[1]+y))
				  return true;
		  }
	  return false;
  }
  public boolean isInBox(float x,float y)
  {
	  if(x>=Pos[0] && x<=Pos[0]+Dim[0])
	  {
		  if(y>=Pos[1] && y<=Pos[1]+Dim[1])
		  {
			  return true;
		  }
	  }
	  return false;
  }
  public float getX(){return Pos[0];}
  public float getY(){return Pos[1];}
  public float getZ(){return Pos[2];}
  public float[] getXYZ(){return Pos;}
  public void setX(float i){Pos[0]=0+i;}
  public void setY(float i){Pos[1]=0+i;}
  public void setZ(float i){Pos[2]=0+i;}
  public void setXYZ(float[] i){setXYZ(i[0],i[1],i[2]);}
  public void setXYZ(float x,float y,float z){Pos = new float[]{x,y,z};}
  public abstract void Tick(ArrayList<Entity> i);
  public void Render(){Render(true);}
  public abstract void Render(boolean shader);
}
