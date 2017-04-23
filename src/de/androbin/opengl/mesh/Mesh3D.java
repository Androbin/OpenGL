package de.androbin.opengl.mesh;

import static de.androbin.opengl.util.VertexUtil.*;
import static org.lwjgl.opengl.GL11.*;
import de.androbin.opengl.*;
import org.lwjgl.util.vector.*;

public class Mesh3D implements Renderable
{
	protected final Vector3f[] vertices;
	
	public Mesh3D( final Vector3f[] vertices )
	{
		this.vertices = vertices;
	}
	
	@ Override
	public void render()
	{
		glBegin( GL_POLYGON );
		
		for ( final Vector3f vertex : vertices )
		{
			glVertex3f( vertex );
		}
		
		glEnd();
	}
}