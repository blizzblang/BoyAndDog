package com.blizzblang.opengl;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix;
import org.lwjgl.util.vector.Matrix4f;
import org.newdawn.slick.Color;


public class GUI 
{
ArrayList<box> dialogs = new ArrayList<box>();
SpriteBatch text = new SpriteBatch("res/img/charmap.png");
	public GUI()
	{
		text.cutoutAll(0, 0, 32, 32);
	}
	public void drawString(String i,int x,int y,Color ic,int s)
	{
		quad[] str = new quad[i.length()];
		Matrix4f pos = new Matrix4f();
		VBO textbox = new VBO(pos);
		textbox.addTexture(text.getTex().getTextureID());
		
		for(int c=0;c<str.length;c++)
		{
			float[][] cut = text.getTriangulated(lookupLetter(""+i.charAt(c)));
		Vertex ul = new Vertex(); ul.setXYZ(x+s*c, y+s, 0);ul.setST(cut[0]);
		Vertex ur = new Vertex(); ur.setXYZ(x+s*c+s, y+s, 0);ur.setST(cut[2]);
		Vertex bl = new Vertex(); bl.setXYZ(x+s*c, y, 0);bl.setST(cut[1]);
		Vertex br = new Vertex(); br.setXYZ(x+s*c+s , y, 0);br.setST(cut[4]);
		
		textbox.addVertex(bl);textbox.addVertex(ul);textbox.addVertex(ur);
		textbox.addVertex(br);textbox.addVertex(ur);textbox.addVertex(bl);
		}
		textbox.finalize();
		Matrix4f mat = new Matrix4f();
		textbox.render(mat, true);
	}
	private int lookupLetter(String a)
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
			default:
				return 0;
		}
	}
}
class box
{
	int[] Pos;
	int[] Dim;
	
	public box(int x,int y,int w,int h)
	{
		Pos = new int[]{x,y};
		Dim = new int[]{w,h};
	}
	
}
class quad
{
	private int vaoId = 0;
    private int vboId = 0;
    private int vboiId = 0;
    private int indicesCount = 0;
	public quad()
	{
		// We'll define our quad using 4 vertices of the custom 'Vertex' class
        float w = (float)OpenGL.Width/(float)OpenGL.Height;w=0.5767f;
        float h = (float)OpenGL.Height/(float)OpenGL.Width;h=0.5769f;
        Vertex v0 = new Vertex(); 
        v0.setXYZ(-w, h, 0); v0.setRGB(1, 0, 0); v0.setST(0, 0);
        Vertex v1 = new Vertex(); 
        v1.setXYZ(-w, -h, 0); v1.setRGB(0, 1, 0); v1.setST(0, 1);
        Vertex v2 = new Vertex(); 
        v2.setXYZ(w, -h, 0); v2.setRGB(0, 0, 1); v2.setST(1, 1);
        Vertex v3 = new Vertex(); 
        v3.setXYZ(w,h, 0); v3.setRGB(1, 1, 1); v3.setST(1, 0);
         
        Vertex[] vertices = new Vertex[] {v0, v1, v2, v3};
        // Put each 'Vertex' in one FloatBuffer
        FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length *
                Vertex.elementCount);
        for (int i = 0; i < vertices.length; i++) {
            // Add position, color and texture floats to the buffer
            verticesBuffer.put(vertices[i].getElements());
        }
        verticesBuffer.flip();  
        // OpenGL expects to draw vertices in counter clockwise order by default
        byte[] indices = {
                0, 1, 2,
                2, 3, 0
        };
        indicesCount = indices.length;
        ByteBuffer indicesBuffer = BufferUtils.createByteBuffer(indicesCount);
        indicesBuffer.put(indices);
        indicesBuffer.flip();
         
        // Create a new Vertex Array Object in memory and select it (bind)
        vaoId = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoId);
         
        // Create a new Vertex Buffer Object in memory and select it (bind)
        vboId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesBuffer, GL15.GL_STATIC_DRAW);
         
        // Put the position coordinates in attribute list 0
        GL20.glVertexAttribPointer(0, Vertex.positionElementCount, GL11.GL_FLOAT, 
                false, Vertex.stride, Vertex.positionByteOffset);
        // Put the color components in attribute list 1
        GL20.glVertexAttribPointer(1, Vertex.colorElementCount, GL11.GL_FLOAT, 
                false, Vertex.stride, Vertex.colorByteOffset);
        // Put the texture coordinates in attribute list 2
        GL20.glVertexAttribPointer(2, Vertex.textureElementCount, GL11.GL_FLOAT, 
                false, Vertex.stride, Vertex.textureByteOffset);
         
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
         
        // Deselect (bind to 0) the VAO
        GL30.glBindVertexArray(0);
         
        // Create a new VBO for the indices and select it (bind) - INDICES
        vboiId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboiId);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	public void render(int w,int h)
	{
		
	}
	public void render()
	{

         
        // Bind to the VAO that has all the information about the vertices
        GL30.glBindVertexArray(vaoId);
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
         
        // Bind to the index VBO that has all the information about the order of the vertices
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboiId);
         
        // Draw the vertices
        GL11.glDrawElements(GL11.GL_TRIANGLES, indicesCount, GL11.GL_UNSIGNED_BYTE, 0);
         
        // Put everything back to default (deselect)
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
         
	}

}

