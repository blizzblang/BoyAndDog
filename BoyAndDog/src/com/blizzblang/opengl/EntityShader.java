package com.blizzblang.opengl;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;

import com.blizzblang.game.Entity;
import com.blizzblang.game.Main;


public class EntityShader extends Shader
{
	private Matrix4f projection = Main.cam.getProjectionMatrix();
	private Matrix4f model;
	private Matrix4f view = Main.cam.getViewMatrix();
    private int projectionMatrixLocation;
    private int viewMatrixLocation;
    private int modelMatrixLocation;  
    private FloatBuffer matrix44Buffer = BufferUtils.createFloatBuffer(16);
	public EntityShader(Matrix4f i) 
	{
		super("res/sdr/base/EntityShader.vert","res/sdr/base/EntityShader.frag");
		model = i;
		init();
	}
	@Override
	protected void init()
	{
		 int vertShader = 0, fragShader = 0;
	       
	       try 
	       {
	           vertShader = createShader(Vert,ARBVertexShader.GL_VERTEX_SHADER_ARB);
	           fragShader = createShader(Frag,ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
	       }
	       catch(Exception exc) 
	       {
	           exc.printStackTrace();
	           return;
	       }
	       finally 
	       {
	           if(vertShader == 0 || fragShader == 0)
	               return;
	       }
	        
	       ShaderId = ARBShaderObjects.glCreateProgramObjectARB();
	        
	       if(ShaderId == 0)return;
	       ARBShaderObjects.glAttachObjectARB(ShaderId, vertShader);
	       ARBShaderObjects.glAttachObjectARB(ShaderId, fragShader);
	       GL20.glBindAttribLocation(ShaderId, 0, "in_Position");
	       GL20.glBindAttribLocation(ShaderId, 1, "in_Color");
	       GL20.glBindAttribLocation(ShaderId, 2, "in_TextureCoord");
	       ARBShaderObjects.glLinkProgramARB(ShaderId);
	       if (ARBShaderObjects.glGetObjectParameteriARB(ShaderId, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE) 
	       {
	           System.err.println(getLogInfo(ShaderId));
	           return;
	       }
	        
	       ARBShaderObjects.glValidateProgramARB(ShaderId);
	       if (ARBShaderObjects.glGetObjectParameteriARB(ShaderId, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE) 
	       {
	           System.err.println(getLogInfo(ShaderId));
	           return;
	       }
	        projectionMatrixLocation = GL20.glGetUniformLocation( ShaderId,"projectionMatrix");
	        viewMatrixLocation = GL20.glGetUniformLocation( ShaderId, "viewMatrix");
	        modelMatrixLocation = GL20.glGetUniformLocation( ShaderId, "modelMatrix");
	}
	public void Bind()
	{
		
	}
	public void Bind(Matrix4f i) 
	{
		Bind(i,Main.cam.getViewMatrix(),Main.cam.getProjectionMatrix());
	}
	public void Bind(Entity i) 
	{
		Bind(i.getMatrix(),Main.cam.getViewMatrix(),Main.cam.getProjectionMatrix());
	}
	public void Bind(Matrix4f model,Matrix4f view,Matrix4f proj) 
	{
		 	ARBShaderObjects.glUseProgramObjectARB(ShaderId);
		 	proj.store(matrix44Buffer); matrix44Buffer.flip();
	        
	        GL20.glUniformMatrix4(projectionMatrixLocation, false, matrix44Buffer);
	        
	        view.store(matrix44Buffer); matrix44Buffer.flip();
	        
	        GL20.glUniformMatrix4(viewMatrixLocation, false, matrix44Buffer);
	        model.store(matrix44Buffer); matrix44Buffer.flip();
	        
	        GL20.glUniformMatrix4(modelMatrixLocation, false, matrix44Buffer);        
	}
	@Override
	public void unBind() 
	{
		 ARBShaderObjects.glUseProgramObjectARB(0);
		
	}



}
