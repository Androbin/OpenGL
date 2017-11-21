package de.androbin.opengl;

import static de.androbin.lwjgl.util.Vector2fUtil.*;
import org.lwjgl.util.vector.*;

public class Object2D {
  public static final int DIM = 2;
  
  public Vector2f pos;
  public Vector2f size;
  
  public Object2D( final Vector2f pos ) {
    this( pos, null );
  }
  
  public Object2D( final Vector2f pos, final Vector2f size ) {
    this.pos = pos;
    this.size = size;
  }
  
  public Object2D collision() {
    return null;
  }
  
  public final float getCenterX() {
    return pos.x + size.x * 0.5f;
  }
  
  public final float getCenterY() {
    return pos.y + size.y * 0.5f;
  }
  
  public int move( final Vector2f v ) {
    int c = 0;
    
    for ( int x = 0; x < DIM; x++ ) {
      Vector2f.add( pos, split( v, x ), pos );
      final Object2D box = collision();
      
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
}