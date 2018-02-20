package de.androbin.opengl.util;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL30.*;
import de.androbin.io.*;
import java.io.*;
import java.net.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.*;

public final class TextureUtil {
  private TextureUtil() {
  }
  
  public static boolean generateMipmap( final Texture texture, final float lod_bias ) {
    if ( !GLContext.getCapabilities().OpenGL30 ) {
      return false;
    }
    
    glBindTexture( GL_TEXTURE_2D, texture.getTextureID() );
    glGenerateMipmap( GL_TEXTURE_2D );
    glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST );
    glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST_MIPMAP_LINEAR );
    glTexParameterf( GL_TEXTURE_2D, GL_TEXTURE_LOD_BIAS, lod_bias );
    return true;
  }
  
  public static Texture loadTexture( final String path ) {
    final URL res = DynamicClassLoader.get().getResource( "gfx/" + path );
    
    if ( res == null ) {
      return null;
    }
    
    try ( final InputStream stream = res.openStream() ) {
      return TextureLoader.getTexture( null, stream, GL_NEAREST );
    } catch ( final IOException e ) {
      return null;
    }
  }
}