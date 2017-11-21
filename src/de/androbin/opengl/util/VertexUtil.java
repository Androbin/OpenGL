package de.androbin.opengl.util;

import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.*;

public final class VertexUtil {
  private VertexUtil() {
  }
  
  public static void glVertex2f( final Vector2f v ) {
    GL11.glVertex2f( v.x, v.y );
  }
  
  public static void glVertex2f( final float ... v ) {
    GL11.glVertex2f( v[ 0 ], v[ 1 ] );
  }
  
  public static void glVertex3f( final Vector3f v ) {
    GL11.glVertex3f( v.x, v.y, v.z );
  }
  
  public static void glVertex3f( final float ... v ) {
    GL11.glVertex3f( v[ 0 ], v[ 1 ], v[ 2 ] );
  }
}