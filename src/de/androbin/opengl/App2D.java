package de.androbin.opengl;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;

public abstract class App2D extends App
{
	@ Override
	protected void initGL()
	{
		initStandardGL();
	}
	
	public static void initStandardGL()
	{
		glMatrixMode( GL_PROJECTION );
		glLoadIdentity();
		
		glOrtho( 0, Display.getWidth(), 0, Display.getHeight(), 1, -1 );
		glMatrixMode( GL_MODELVIEW );
	}
}