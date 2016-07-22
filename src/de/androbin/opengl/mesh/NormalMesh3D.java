package de.androbin.opengl.mesh;

import static de.androbin.opengl.util.NormalUtil.*;
import static de.androbin.opengl.util.VertexUtil.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.*;

public class NormalMesh3D extends Mesh3D
{
	protected final Vector3f[] normals;
	
	public NormalMesh3D( final Vector3f[] vertices, final boolean ff )
	{
		this( vertices, getNormals( ff, new Vector3f(), new Vector3f(), vertices ) );
	}
	
	public NormalMesh3D( final Vector3f[] vertices, final Vector3f[] normals )
	{
		super( vertices );
		this.normals = normals;
	}
	
	@ Override
	public void render()
	{
		glBegin( GL_POLYGON );
		
		for ( int i = 0; i < vertices.length; i++ )
		{
			glNormal3f( normals[ i ] );
			glVertex3f( vertices[ i ] );
		}
		
		glEnd();
	}
}