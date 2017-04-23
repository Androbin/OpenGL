package de.androbin.opengl.mesh;

import static de.androbin.opengl.util.ColorUtil.*;
import static de.androbin.opengl.util.VertexUtil.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.*;

public class Color4fMesh3D extends Mesh3D
{
	protected final Vector4f[] colors;
	
	public Color4fMesh3D( final Vector3f[] vertices, final Vector4f[] colors )
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
			glColor4f( colors[ i ] );
			glVertex3f( vertices[ i ] );
		}
		
		glEnd();
	}
}