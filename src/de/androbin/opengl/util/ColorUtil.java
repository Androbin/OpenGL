package de.androbin.opengl.util;

import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.*;

public final class ColorUtil
{
	private ColorUtil()
	{
	}
	
	public static void glColor3f( final Vector3f v )
	{
		GL11.glColor3f( v.x, v.y, v.z );
	}
	
	public static void glColor3f( final float ... c )
	{
		GL11.glColor3f( c[ 0 ], c[ 1 ], c[ 2 ] );
	}
	
	public static void glColor4f( final Vector4f v )
	{
		GL11.glColor4f( v.x, v.y, v.z, v.w );
	}
	
	public static void glColor4f( final float ... c )
	{
		GL11.glColor4f( c[ 0 ], c[ 1 ], c[ 2 ], c[ 3 ] );
	}
}