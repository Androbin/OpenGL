package de.androbin.opengl;

import static de.androbin.lwjgl.util.Vector3fUtil.*;
import org.lwjgl.util.vector.*;

public class Object3D
{
	protected static final int	DIM	= 3;
	
	protected Vector3f			pos;
	protected Vector3f			size;
	
	public Object3D( final Vector3f pos )
	{
		this( pos, new Vector3f() );
	}
	
	public Object3D( final Vector3f pos, final Vector3f size )
	{
		this.pos = pos;
		this.size = size;
	}
	
	public Object3D collision()
	{
		return null;
	}
	
	public final float getCenterX()
	{
		return pos.x + size.x * 0.5f;
	}
	
	public final float getCenterY()
	{
		return pos.y + size.y * 0.5f;
	}
	
	public final float getCenterZ()
	{
		return pos.z + size.z * 0.5f;
	}
	
	public Vector3f getPos()
	{
		return pos;
	}
	
	public final Vector3f getSize()
	{
		return size;
	}
	
	public int move( final Vector3f v )
	{
		int c = 0;
		
		for ( int x = 0; x < DIM; x++ )
		{
			if ( get( v, x ) == 0f )
			{
				continue;
			}
			
			Vector3f.add( pos, split( v, x ), pos );
			final Object3D box = collision();
			
			if ( box != null )
			{
				set( pos, x, get( box.pos, x ) + ( get( v, x ) > 0f ? -get( getSize(), x ) : get( box.getSize(), x ) ) );
				c |= 1 << x;
			}
		}
		
		return c;
	}
	
	public void setPos( final Vector3f pos )
	{
		this.pos = pos;
	}
	
	public void setSize( final Vector3f size )
	{
		this.size = size;
	}
}