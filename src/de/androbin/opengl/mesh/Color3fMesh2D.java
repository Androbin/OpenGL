package de.androbin.opengl.mesh;

import static de.androbin.opengl.util.ColorUtil.*;
import static de.androbin.opengl.util.VertexUtil.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.*;

public class Color3fMesh2D extends Mesh2D
{
	protected final Vector3f[] colors;
	
	public Color3fMesh2D( final Vector2f[] vertices, final Vector3f[] colors )
	{
		super( vertices );
		this.colors = colors;
	}
	
	@ Override
	public void render()
	{
		glBegin( GL_POLYGON );
		
		for ( int i = 0; i < vertices.length; i++ )
		{
			glColor3f( colors[ i ] );
			glVertex2f( vertices[ i ] );
		}
		
		glEnd();
	}
}