package de.androbin.opengl;

import static de.androbin.lwjgl.util.Vector2fUtil.*;
import jdk.nashorn.internal.objects.annotations.*;
import org.lwjgl.util.vector.*;

public class Object2D
{
	protected static final int	DIM	= 2;
	
	protected Vector2f			pos;
	protected Vector2f			size;
	
	public Object2D( final Vector2f pos )
	{
		this( pos, null );
	}
	
	public Object2D( final Vector2f pos, final Vector2f size )
	{
		this.pos = pos;
		this.size = size;
	}
	
	public Object2D collision()
	{
		return null;
	}
	
	@ Getter
	public final float getCenterX()
	{
		return pos.x + size.x * 0.5f;
	}
	
	@ Getter
	public final float getCenterY()
	{
		return pos.y + size.y * 0.5f;
	}
	
	@ Getter
	public final Vector2f getPos()
	{
		return pos;
	}
	
	@ Getter
	public final Vector2f getSize()
	{
		return size;
	}
	
	public final int move( final Vector2f v )
	{
		int c = 0;
		
		for ( int x = 0; x < DIM; x++ )
		{
			Vector2f.add( pos, split( v, x ), pos );
			final Object2D box = collision();
			
			if ( box != null )
			{
				set( pos, x, get( box.pos, x ) + ( get( v, x ) > 0f ? -get( getSize(), x ) : get( box.getSize(), x ) ) );
				c |= 1 << x;
			}
		}
		
		return c;
	}
	
	@ Setter
	public final void setCenterX( final float centerX )
	{
		pos.x = centerX - size.x * 0.5f;
	}
	
	@ Setter
	public final void setCenterY( final float centerY )
	{
		pos.y = centerY - size.y * 0.5f;
	}
	
	@ Setter
	public final void setSize( final Vector2f size )
	{
		this.size = size;
	}
}