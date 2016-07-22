package de.androbin.opengl.mesh;

import static de.androbin.opengl.util.ColorUtil.*;
import static de.androbin.opengl.util.NormalUtil.*;
import static de.androbin.opengl.util.VertexUtil.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.*;

public class NormalColor3fMesh3D extends Mesh3D
{
	protected final Vector3f[]	normals;
	protected final Vector3f[]	colors;
	
	public NormalColor3fMesh3D( final Vector3f[] vertices, final boolean ff, final Vector3f[] colors )
	{
		this( vertices, getNormals( ff, new Vector3f(), new Vector3f(), vertices ), colors );
	}
	
	public NormalColor3fMesh3D( final Vector3f[] vertices, final Vector3f[] normals, final Vector3f[] colors )
	{
		super( vertices );
		this.normals = normals;
		this.colors = colors;
	}
	
	@ Override
	public void render()
	{
		glBegin( GL_POLYGON );
		
		for ( int i = 0; i < vertices.length; i++ )
		{
			glColor3f( colors[ i ] );
			glNormal3f( normals[ i ] );
			glVertex3f( vertices[ i ] );
		}
		
		glEnd();
	}
}