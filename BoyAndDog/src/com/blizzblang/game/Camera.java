package com.blizzblang.game;


import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.blizzblang.opengl.OpenGL;



public class Camera 
{
private float[] Pos=new float[3];
private Matrix4f projectionMatrix = new Matrix4f();
private Matrix4f OrthoMatrix = new Matrix4f();
private Matrix4f viewMatrix = new Matrix4f();
private float FOV=60;
private float near_plane = 0.1f;
private float far_plane =100f;
private float onear_plane =-100f;
private float ofar_plane =1f;
private float frustum_length = far_plane - near_plane;
private boolean ortho=false;
private Vector3f Angle = new Vector3f(0,0,0);
	public Camera(float[] pos)
	{
		Pos = new float[]{pos[0],pos[1],pos[2]};
		float y_scale = (float) (1/ Math.tan(Math.toRadians(60) / 2f));
        float x_scale = y_scale / OpenGL.getAspectRatio();
        float frustum_length = far_plane - near_plane;
         
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((far_plane + near_plane) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * near_plane * far_plane) / frustum_length);
        projectionMatrix.m33 = 0;
        float w = (float)OpenGL.Width/(float)OpenGL.Height;w*=w;
        float h = (float)OpenGL.Height/(float)OpenGL.Width;h*=h;
		float left=-w;
		float right=w;
		float top=h;
		float bottom=-h;
		
		OrthoMatrix.m00 = 2/(right-left);
		OrthoMatrix.m30 = -(right+left)/(right-left);
		
		OrthoMatrix.m11 = 2/(top-bottom);
		OrthoMatrix.m31 = -(top+bottom)/(top-bottom);
		
		OrthoMatrix.m22 = -2/(ofar_plane-onear_plane);
		OrthoMatrix.m32 = -(ofar_plane+onear_plane/(ofar_plane-onear_plane));
		
		OrthoMatrix.m33 =1;
		
	}
	public Matrix4f getProjectionMatrix()
	{
		if(ortho)
			return getOrthoMatrix();
		return getPerspectiveMatrix();
	}
	public Matrix4f getPerspectiveMatrix()
	{
		return projectionMatrix;
	}
	public Matrix4f getOrthoMatrix()
	{
		return OrthoMatrix;
	}
	public void update()
	{
		float lmod=2f;
		float mod=0.5f;
		if(Main.KeyIn.getKeyDown(Keyboard.KEY_Q))ortho=true;
		else ortho=false;
		if(Main.KeyIn.getKeyDown(Keyboard.KEY_A))Angle.y -= 0.05*lmod;
		if(Main.KeyIn.getKeyDown(Keyboard.KEY_D))Angle.y += 0.05*lmod;
		if(Main.KeyIn.getKeyDown(Keyboard.KEY_W))Angle.x -= 0.05*lmod;
		if(Main.KeyIn.getKeyDown(Keyboard.KEY_S))Angle.x += 0.05*lmod;
		if(Main.KeyIn.getKeyDown(Keyboard.KEY_T))Pos[2] += 0.01*mod;
		if(Main.KeyIn.getKeyDown(Keyboard.KEY_G))Pos[2] -= 0.01*mod;
		if(Main.KeyIn.getKeyDown(Keyboard.KEY_F))Pos[0] += 0.01*mod;
		if(Main.KeyIn.getKeyDown(Keyboard.KEY_H))Pos[0] -= 0.01*mod;
	}
	public Matrix4f getViewMatrix()
	{
		viewMatrix = new Matrix4f();
		viewMatrix.rotate((float) Math.toRadians(Angle.x), new Vector3f(1,0,0), viewMatrix);
		viewMatrix.rotate((float) Math.toRadians(Angle.y), new Vector3f(0,1,0), viewMatrix);
		viewMatrix.rotate((float) Math.toRadians(Angle.z), new Vector3f(0,0,1), viewMatrix);
		Matrix4f.translate(new Vector3f(Pos[0],Pos[1],Pos[2]),  viewMatrix ,  viewMatrix );

		return viewMatrix;
	}
}
