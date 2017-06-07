package de.androbin.opengl;

import static de.androbin.opengl.util.MouseUtil.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

public abstract class App3D extends App {
  protected float fovy;
  protected float zNear;
  protected float zFar;
  
  protected App3D() {
    this( 60f, 0.125f, 1024f );
  }
  
  protected App3D( final float fovy, final float zNear, final float zFar ) {
    this.fovy = fovy;
    this.zNear = zNear;
    this.zFar = zFar;
    
    gluPerspective( fovy, (float) Display.getWidth() / Display.getHeight(), zNear, zFar );
    glMatrixMode( GL_MODELVIEW );
    glLoadIdentity();
  }
  
  @ Override
  protected void initGL() {
    initStandardGL();
  }
  
  public static void initStandardGL() {
    glEnable( GL_DEPTH_TEST );
    glMatrixMode( GL_PROJECTION );
    glLoadIdentity();
    
    Mouse.setGrabbed( true );
    centerMouse();
  }
}