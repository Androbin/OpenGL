package de.androbin.opengl;

import static de.androbin.math.util.floats.FloatMathUtil.*;
import static de.androbin.lwjgl.util.Vector2fUtil.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.*;

public class Box2D extends Object2D implements Renderable {
  public Box2D( final Box2D box ) {
    this( new Vector2f( box.pos ), new Vector2f( box.size ) );
  }
  
  public Box2D( final Vector2f pos, final Vector2f size ) {
    super( pos, size );
  }
  
  public final boolean contains( final Box2D box ) {
    if ( box == null ) {
      return false;
    }
    
    for ( int x = 0; x < DIM; x++ ) {
      final float p = get( pos, x );
      final float b = get( box.pos, x );
      
      if ( b < p || b + get( box.size, x ) > p + get( size, x ) ) {
        return false;
      }
    }
    
    return true;
  }
  
  public final boolean contains( final Vector2f point ) {
    if ( point == null ) {
      return false;
    }
    
    for ( int x = 0; x < DIM; x++ ) {
      final float p = get( pos, x );
      final float c = get( point, x );
      
      if ( c < p || c > p + get( size, x ) ) {
        return false;
      }
    }
    
    return true;
  }
  
  public final Box2D intersection( final Box2D box ) {
    if ( box == null ) {
      return null;
    }
    
    final Vector2f pos = new Vector2f();
    final Vector2f size = new Vector2f();
    
    for ( int x = 0; x < DIM; x++ ) {
      final float p = get( pos, x );
      final float b = get( box.pos, x );
      
      set( pos, x, max( p, b ) );
      set( size, x, min( p + get( size, x ), b + get( box.size, x ) ) - get( pos, x ) );
    }
    
    return new Box2D( pos, size );
  }
  
  public final boolean intersects( final Box2D box ) {
    if ( box == null ) {
      return false;
    }
    
    for ( int x = 0; x < DIM; x++ ) {
      final float p = get( pos, x );
      final float b = get( box.pos, x );
      
      if ( b >= p + get( size, x ) || b + get( box.size, x ) <= p ) {
        return false;
      }
    }
    
    return true;
  }
  
  @ Override
  public void render() {
    glBegin( GL_QUADS );
    glVertex2f( pos.x /*    */, pos.y + size.y );
    glVertex2f( pos.x + size.x, pos.y + size.y );
    glVertex2f( pos.x + size.x, pos.y /*    */ );
    glVertex2f( pos.x /*    */, pos.y /*    */ );
    glEnd();
  }
  
  public final Box2D union( final Box2D box ) {
    if ( box == null ) {
      return new Box2D( this );
    }
    
    final Vector2f pos = new Vector2f();
    final Vector2f size = new Vector2f();
    
    for ( int x = 0; x < DIM; x++ ) {
      final float p = get( pos, x );
      final float b = get( box.pos, x );
      
      set( pos, x, min( p, b ) );
      set( size, x, max( p + get( size, x ), b + get( box.size, x ) ) - get( pos, x ) );
    }
    
    return new Box2D( pos, size );
  }
}