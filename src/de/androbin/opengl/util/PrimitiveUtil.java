package de.androbin.opengl.util;

import java.awt.geom.*;
import org.lwjgl.opengl.*;

public final class PrimitiveUtil {
  private PrimitiveUtil() {
  }
  
  public static void glRectf( final Rectangle2D.Float rect ) {
    glRectf( rect.x, rect.y, rect.width, rect.height );
  }
  
  public static void glRectf( final float x, final float y,
      final float width, final float height ) {
    final float x2 = x + width;
    final float y2 = y + height;
    
    GL11.glRectf( x, y, x2, y2 );
  }
}