package de.androbin.opengl.util;

import static org.lwjgl.opengl.GL11.*;
import java.awt.*;
import java.util.function.*;
import org.lwjgl.*;
import org.lwjgl.opengl.*;
import org.lwjgl.opengl.DisplayMode;

public final class DisplayUtil {
  private DisplayUtil() {
  }
  
  public static DisplayMode createDisplayMode( final float scale ) {
    if ( scale <= 0f ) {
      return null;
    }
    
    final Dimension screen_size = getDesktopDimension();
    
    final int width = (int) ( screen_size.getWidth() * scale );
    final int height = (int) ( screen_size.getHeight() * scale );
    
    return new DisplayMode( width, height );
  }
  
  public static DisplayMode createQuadraticDisplayMode( final float scale ) {
    if ( scale <= 0f ) {
      return null;
    }
    
    final Dimension screen_size = getDesktopDimension();
    final int size = (int) ( Math.min( screen_size.width, screen_size.height ) * scale );
    
    return new DisplayMode( size, size );
  }
  
  public static Dimension getDesktopDimension() {
    final DisplayMode dm = Display.getDesktopDisplayMode();
    return new Dimension( dm.getWidth(), dm.getHeight() );
  }
  
  public static void setFullscreen() throws LWJGLException {
    Display.setDisplayModeAndFullscreen( Display.getDesktopDisplayMode() );
    glViewport( 0, 0, Display.getWidth(), Display.getHeight() );
  }
  
  public static void toggleFullscreen( final Supplier<DisplayMode> dm ) {
    try {
      if ( Display.isFullscreen() ) {
        Display.setDisplayMode( dm.get() );
        glViewport( 0, 0, Display.getWidth(), Display.getHeight() );
      } else {
        setFullscreen();
      }
    } catch ( final LWJGLException e ) {
      e.printStackTrace();
    }
  }
}