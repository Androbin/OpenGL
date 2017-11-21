package de.androbin.opengl.util;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import java.nio.*;

public final class ArrayBufferUtil {
  private ArrayBufferUtil() {
  }
  
  public static void colorBuffer( final int cbo, final FloatBuffer buffer ) {
    glBindBuffer( GL_ARRAY_BUFFER, cbo );
    glBufferData( GL_ARRAY_BUFFER, buffer, GL_DYNAMIC_DRAW );
    glColorPointer( 4, GL_FLOAT, 0, 0 );
  }
  
  public static void indexBuffer( final int ibo, final ShortBuffer buffer ) {
    glBindBuffer( GL_ELEMENT_ARRAY_BUFFER, ibo );
    glBufferData( GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW );
  }
  
  public static void vertexBuffer( final int vbo, final FloatBuffer buffer ) {
    glBindBuffer( GL_ARRAY_BUFFER, vbo );
    glBufferData( GL_ARRAY_BUFFER, buffer, GL_DYNAMIC_DRAW );
    glVertexPointer( 4, GL_FLOAT, 0, 0 );
  }
}