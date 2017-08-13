package de.androbin.opengl;

import static de.androbin.math.util.floats.FloatMathUtil.*;
import static de.androbin.lwjgl.util.Vector3fUtil.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.*;

public class Box3D extends Object3D implements Renderable {
  public Box3D( final Box3D box ) {
    this( new Vector3f( box.pos ), new Vector3f( box.size ) );
  }
  
  public Box3D( final Vector3f pos, final Vector3f size ) {
    super( pos, size );
  }
  
  public final boolean contains( final Box3D box ) {
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
  
  public final boolean contains( final Vector3f point ) {
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
  
  public final Box3D intersection( final Box3D box ) {
    if ( box == null ) {
      return null;
    }
    
    final Vector3f pos = new Vector3f();
    final Vector3f size = new Vector3f();
    
    for ( int x = 0; x < DIM; x++ ) {
      final float p = get( pos, x );
      final float b = get( box.pos, x );
      
      set( pos, x, max( p, b ) );
      set( size, x, min( p + get( size, x ), b + get( box.size, x ) ) - get( pos, x ) );
    }
    
    return new Box3D( pos, size );
  }
  
  public final boolean intersects( final Box3D box ) {
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
    
    glNormal3f( -1f, 0f, 0f );
    glVertex3f( pos.x, pos.y + size.y, pos.z /*    */ );
    glVertex3f( pos.x, pos.y + size.y, pos.z + size.z );
    glVertex3f( pos.x, pos.y /*    */, pos.z + size.z );
    glVertex3f( pos.x, pos.y /*    */, pos.z /*    */ );
    
    glNormal3f( 1f, 0f, 0f );
    glVertex3f( pos.x + size.x, pos.y + size.y, pos.z + size.z );
    glVertex3f( pos.x + size.x, pos.y + size.y, pos.z /*    */ );
    glVertex3f( pos.x + size.x, pos.y /*    */, pos.z /*    */ );
    glVertex3f( pos.x + size.x, pos.y /*    */, pos.z + size.z );
    
    glNormal3f( 0f, -1f, 0f );
    glVertex3f( pos.x /*    */, pos.y, pos.z + size.z );
    glVertex3f( pos.x + size.x, pos.y, pos.z + size.z );
    glVertex3f( pos.x + size.x, pos.y, pos.z /*    */ );
    glVertex3f( pos.x /*    */, pos.y, pos.z /*    */ );
    
    glNormal3f( 0f, 1f, 0f );
    glVertex3f( pos.x /*    */, pos.y + size.y, pos.z /*    */ );
    glVertex3f( pos.x + size.x, pos.y + size.y, pos.z /*    */ );
    glVertex3f( pos.x + size.x, pos.y + size.y, pos.z + size.z );
    glVertex3f( pos.x /*    */, pos.y + size.y, pos.z + size.z );
    
    glNormal3f( 0f, 0f, -1f );
    glVertex3f( pos.x + size.x, pos.y + size.y, pos.z );
    glVertex3f( pos.x /*    */, pos.y + size.y, pos.z );
    glVertex3f( pos.x /*    */, pos.y /*    */, pos.z );
    glVertex3f( pos.x + size.x, pos.y /*    */, pos.z );
    
    glNormal3f( 0f, 0f, 1f );
    glVertex3f( pos.x /*    */, pos.y + size.y, pos.z + size.z );
    glVertex3f( pos.x + size.x, pos.y + size.y, pos.z + size.z );
    glVertex3f( pos.x + size.x, pos.y /*    */, pos.z + size.z );
    glVertex3f( pos.x /*    */, pos.y /*    */, pos.z + size.z );
    
    glEnd();
  }
  
  public final Box3D union( final Box3D box ) {
    if ( box == null ) {
      return new Box3D( this );
    }
    
    final Vector3f pos = new Vector3f();
    final Vector3f size = new Vector3f();
    
    for ( int x = 0; x < DIM; x++ ) {
      final float p = get( pos, x );
      final float b = get( box.pos, x );
      
      set( pos, x, min( p, b ) );
      set( size, x, max( p + get( size, x ), b + get( box.size, x ) ) - get( pos, x ) );
    }
    
    return new Box3D( pos, size );
  }
}