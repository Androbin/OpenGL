package de.androbin.opengl.mesh;

import static de.androbin.opengl.util.VertexUtil.*;
import static org.lwjgl.opengl.GL11.*;
import de.androbin.opengl.*;
import org.lwjgl.util.vector.*;

public class Mesh2D implements Renderable
{
	protected final Vector2f[] vertices;
	
	public Mesh2D( final Vector2f[] vertices )
	{
		this.vertices = vertices;
	}
	
	@ Override
	public void render()
	{
		glBegin( GL_POLYGON );
		
		for ( final Vector2f vertex : vertices )
		{
			glVertex2f( vertex );
		}
		
		glEnd();
	}
}