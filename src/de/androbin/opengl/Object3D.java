package de.androbin.opengl;

import static de.androbin.lwjgl.util.Vector3fUtil.*;
import org.lwjgl.util.vector.*;

public class Object3D {
  public static final int DIM = 3;
  
  public Vector3f pos;
  public Vector3f size;
  
  public Object3D( final Vector3f pos ) {
    this( pos, new Vector3f() );
  }
  
  public Object3D( final Vector3f pos, final Vector3f size ) {
    this.pos = pos;
    this.size = size;
  }
  
  public Object3D collision() {
    return null;
  }
  
  public final float getCenterX() {
    return pos.x + size.x * 0.5f;
  }
  
  public final float getCenterY() {
    return pos.y + size.y * 0.5f;
  }
  
  public final float getCenterZ() {
    return pos.z + size.z * 0.5f;
  }
  
  public int move( final Vector3f v ) {
    int c = 0;
    
    for ( int x = 0; x < DIM; x++ ) {
      if ( get( v, x ) == 0f ) {
        continue;
      }
      
      Vector3f.add( pos, split( v, x ), pos );
      final Object3D box = collision();
      
      if ( box != null ) {
        final float dx = get( v, x ) > 0f ? -get( size, x ) : get( box.size, x );
        set( pos, x, get( box.pos, x ) + dx );
        c |= 1 << x;
      }
    }
    
    return c;
  }
  
  public final void setCenterX( final float centerX ) {
    pos.x = centerX - size.x * 0.5f;
  }
  
  public final void setCenterY( final float centerY ) {
    pos.y = centerY - size.y * 0.5f;
  }
  
  public final void setCenterZ( final float centerZ ) {
    pos.z = centerZ - size.z * 0.5f;
  }
}