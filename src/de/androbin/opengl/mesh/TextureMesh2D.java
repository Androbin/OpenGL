package de.androbin.opengl.mesh;

import static de.androbin.opengl.util.TexCoordUtil.*;
import static de.androbin.opengl.util.VertexUtil.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.*;

public class TextureMesh2D extends Mesh2D
{
	protected final Vector2f[] texCoords;
	
	public TextureMesh2D( final Vector2f[] vertices, final Vector2f[] texCoords )
	{
		super( vertices );
		this.texCoords = texCoords;
	}
	
	@ Override
	public void render()
	{
		glBegin( GL_POLYGON );
		
		for ( int i = 0; i < vertices.length; i++ )
		{
			glTexCoord2f( texCoords[ i ] );
			glVertex2f( vertices[ i ] );
		}
		
		glEnd();
	}
}