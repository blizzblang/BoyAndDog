package com.blizzblang.opengl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public abstract class Shader
{
  protected String Frag="";
  protected String Vert="";
  protected int ShaderId;
  public Shader(String vert,String frag)
  {
  Frag=frag;
  Vert=vert;
  }
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
  }
  protected int createShader(String filename, int shaderType) throws Exception {
      int shader = 0;
      try {
          shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType);
           
          if(shader == 0)
              return 0;
           
          ARBShaderObjects.glShaderSourceARB(shader, readFileAsString(filename));
          ARBShaderObjects.glCompileShaderARB(shader);
           
          if (ARBShaderObjects.glGetObjectParameteriARB(shader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE)
              throw new RuntimeException("Error creating shader: " + getLogInfo(shader));
           
          return shader;
      }
      catch(Exception exc) {
          ARBShaderObjects.glDeleteObjectARB(shader);
          throw exc;
      }
  }
  protected static String getLogInfo(int obj) {
      return ARBShaderObjects.glGetInfoLogARB(obj, ARBShaderObjects.glGetObjectParameteriARB(obj, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
  }
  private String readFileAsString(String filename) throws Exception {
      StringBuilder source = new StringBuilder();
       
      FileInputStream in = new FileInputStream(filename);
       
      Exception exception = null;
       
      BufferedReader reader;
      try{
          reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
           
          Exception innerExc= null;
          try {
              String line;
              while((line = reader.readLine()) != null)
                  source.append(line).append('\n');
          }
          catch(Exception exc) {
              exception = exc;
          }
          finally {
              try {
                  reader.close();
              }
              catch(Exception exc) {
                  if(innerExc == null)
                      innerExc = exc;
                  else
                      exc.printStackTrace();
              }
          }
           
          if(innerExc != null)
              throw innerExc;
      }
      catch(Exception exc) {
          exception = exc;
      }
      finally {
          try {
              in.close();
          }
          catch(Exception exc) {
              if(exception == null)
                  exception = exc;
              else
                  exc.printStackTrace();
          }
           
          if(exception != null)
              throw exception;
      }
       
      return source.toString();
  }
  public void setTex(String string, int i)
  {
	    int loc = GL20.glGetUniformLocation(ShaderId, string);
	    GL20.glUniform1i(loc, i);
  }
  public void setUniformf(String string, int i,int j) 
  {
	  
	  GL20.glUniform2f(GL20.glGetUniformLocation(ShaderId, string), i, j );
  }
  public abstract void Bind();
  public abstract void unBind();
}
