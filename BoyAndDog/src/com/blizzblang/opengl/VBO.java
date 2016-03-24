package com.blizzblang.opengl;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import com.blizzblang.game.Entity;




public class VBO
{
  private ArrayList<Vertex> Verts = new ArrayList<Vertex>();
  private EntityShader shad;
  private int vaoId=0;
  private int vboId=0;
  private int vboiId=0;
  private int TexId=0;
  private int indicesCount=0;
  public VBO()
  {
  vaoId = GL30.glGenVertexArrays();
  vboId = GL15.glGenBuffers();
  vboiId = GL15.glGenBuffers();
  shad = new EntityShader();
  }
  public void addVertex(Vertex  i)
  {
  Verts.add(i);
  }
  public void finalize()
  {
	  
   FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(Verts.size() * Vertex.elementCount);
        for (Vertex i : Verts) 
        {
            verticesBuffer.put(i.getElements());
        }
        indicesCount = Verts.size();
        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indicesCount);
        for (int i=0;i<indicesCount;i++) 
        {
        	
        	indicesBuffer.put( i);
        }
        indicesBuffer.flip();
        verticesBuffer.flip();  
         GL30.glBindVertexArray(vaoId);
         GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
         GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesBuffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(0,Vertex.positionElementCount, GL11.GL_FLOAT, false, Vertex.stride, Vertex.positionByteOffset);
        GL20.glVertexAttribPointer(1, Vertex.colorElementCount, GL11.GL_FLOAT,false, Vertex.stride, Vertex.colorByteOffset);
        GL20.glVertexAttribPointer(2, Vertex.textureElementCount, GL11.GL_FLOAT,  false, Vertex.stride, Vertex.textureByteOffset);
        
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        vboiId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboiId);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
         
  }
  public void render(Entity i,boolean shader)
  {
	  render(i.getMatrix(),shader);
  }

  public void render(Matrix4f i,boolean shader)
  {
	  if(shader)
	 shad.Bind(i);
	 GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
	 GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
     GL13.glActiveTexture(GL13.GL_TEXTURE0);
     GL11.glBindTexture(GL11.GL_TEXTURE_2D, TexId);
     GL30.glBindVertexArray(vaoId);
     GL20.glEnableVertexAttribArray(0);
     GL20.glEnableVertexAttribArray(1);
     GL20.glEnableVertexAttribArray(2);
     GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboiId);
     GL11.glDrawElements(GL11.GL_TRIANGLES, indicesCount, GL11.GL_UNSIGNED_INT, 0);
     GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
     GL20.glDisableVertexAttribArray(0);
     GL20.glDisableVertexAttribArray(1);
     GL20.glDisableVertexAttribArray(2);
     GL30.glBindVertexArray(0);
     if(shader)
     shad.unBind();
  }
	public void addTexture(int i) 
	{
		TexId=i;
		
	}
	public int getTexture() {
		return TexId;
	}
	public void wipe() 
	{
		Verts.clear();
	}
}
