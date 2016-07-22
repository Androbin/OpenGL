package de.androbin.opengl.camera;

import static de.androbin.lwjgl.util.Vector3fUtil.*;
import static org.lwjgl.input.Keyboard.*;
import org.lwjgl.util.vector.*;

public class MightyCamera3D implements Camera3D
{
	protected final Vector3f	pos;
	protected Vector3f			dir;
	
	protected float				sensitivity;
	protected float				speed;
	
	public MightyCamera3D( final Vector3f pos, final Vector3f dir, final float sensitivity, final float speed )
	{
		this.pos = pos;
		this.dir = dir;
		
		this.sensitivity = sensitivity;
		this.speed = speed;
	}
	
	@ Override
	public Vector3f getCameraRotation()
	{
		return dir;
	}
	
	@ Override
	public Vector3f getCameraTranslation()
	{
		return pos;
	}
	
	public boolean update( final float delta )
	{
		boolean update = updateCameraRotation( sensitivity );
		final float distance = speed * delta;
		
		final Vector3f dv = new Vector3f();
		
		/**/ if ( isKeyDown( KEY_W ) )
		{
			Vector3f.add( pos, (Vector3f) getDirVector( dv, dir.getY() ).scale( distance ), pos );
			update = true;
		}
		else if ( isKeyDown( KEY_S ) )
		{
			Vector3f.add( pos, (Vector3f) getDirVector( dv, dir.getY() ).scale( -distance ), pos );
			update = true;
		}
		
		/**/ if ( isKeyDown( KEY_A ) )
		{
			Vector3f.add( pos, (Vector3f) getDirVector( dv, dir.getY() - 90f ).scale( distance ), pos );
			update = true;
		}
		else if ( isKeyDown( KEY_D ) )
		{
			Vector3f.add( pos, (Vector3f) getDirVector( dv, dir.getY() + 90f ).scale( distance ), pos );
			update = true;
		}
		
		/**/ if ( isKeyDown( KEY_R ) )
		{
			add( pos, 1, distance );
			update = true;
		}
		else if ( isKeyDown( KEY_F ) )
		{
			sub( pos, 1, distance );
			update = true;
		}
		
		return update;
	}
}