package de.androbin.opengl.util;

import static de.androbin.collection.util.ObjectCollectionUtil.*;
import static de.androbin.math.util.ints.IntMathUtil.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.*;

public final class NormalUtil
{
	private NormalUtil()
	{
	}
	
	public static Vector3f getNormal( final boolean ff, final Vector3f dest, final Vector3f temp, final Vector3f ... v )
	{
		return ff ? getNormalCW( dest, temp, v ) : getNormalCCW( dest, temp, v );
	}
	
	public static Vector3f getNormalCW( final Vector3f dest, final Vector3f temp, final Vector3f ... v )
	{
		return Vector3f.cross( Vector3f.sub( v[ 2 ], v[ 1 ], temp ), Vector3f.sub( v[ 1 ], v[ 0 ], dest ), dest ).normalise( dest );
	}
	
	public static Vector3f getNormalCCW( final Vector3f dest, final Vector3f temp, final Vector3f ... v )
	{
		return Vector3f.cross( Vector3f.sub( v[ 0 ], v[ 1 ], temp ), Vector3f.sub( v[ 1 ], v[ 2 ], dest ), dest ).normalise( dest );
	}
	
	public static Vector3f[] getNormals( final boolean ff, final Vector3f dest, final Vector3f temp, final Vector3f[] vertices )
	{
		if ( vertices.length < 3 )
		{
			throw new IllegalArgumentException();
		}
		
		if ( vertices.length == 3 )
		{
			return fill( new Vector3f[ 3 ], getNormal( ff, dest, temp, vertices ) );
		}
		
		return fill( new Vector3f[ vertices.length ], i ->
		{
			final Vector3f v1 = vertices[ shiftUp( i - 1, vertices.length ) ];
			final Vector3f v2 = vertices[ i ];
			final Vector3f v3 = vertices[ shiftDown( i + 1, vertices.length ) ];
			
			return getNormal( ff, dest, temp, v1, v2, v3 );
		} );
	}
	
	public static void glNormal3f( final Vector3f v )
	{
		GL11.glNormal3f( v.x, v.y, v.z );
	}
	
	public static void glNormal3f( final float ... n )
	{
		GL11.glNormal3f( n[ 0 ], n[ 1 ], n[ 2 ] );
	}
}