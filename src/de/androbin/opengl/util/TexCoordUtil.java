package de.androbin.opengl.util;

import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.*;

public final class TexCoordUtil
{
	private TexCoordUtil()
	{
	}
	
	public static void glTexCoord2f( final Vector2f v )
	{
		GL11.glTexCoord2f( v.x, v.y );
	}
	
	public static void glTexCoord2f( final float ... t )
	{
		GL11.glTexCoord2f( t[ 0 ], t[ 1 ] );
	}
}