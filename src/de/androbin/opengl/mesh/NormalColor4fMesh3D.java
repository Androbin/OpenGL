package de.androbin.opengl.mesh;

import static de.androbin.opengl.util.ColorUtil.*;
import static de.androbin.opengl.util.NormalUtil.*;
import static de.androbin.opengl.util.VertexUtil.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.*;

public class NormalColor4fMesh3D extends Mesh3D
{
	protected final Vector3f[]	normals;
	protected final Vector4f[]	colors;
	
	public NormalColor4fMesh3D( final Vector3f[] vertices, final boolean ff, final Vector4f[] colors )
	{
		this( vertices, getNormals( ff, new Vector3f(), new Vector3f(), vertices ), colors );
	}
	
	public NormalColor4fMesh3D( final Vector3f[] vertices, final Vector3f[] normals, final Vector4f[] colors )
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
			glColor4f( colors[ i ] );
			glNormal3f( normals[ i ] );
			glVertex3f( vertices[ i ] );
		}
		
		glEnd();
	}
}