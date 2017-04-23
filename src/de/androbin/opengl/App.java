package de.androbin.opengl;

import static org.lwjgl.opengl.GL11.*;
import de.androbin.opengl.cam.*;
import org.lwjgl.*;
import org.lwjgl.opengl.*;

public abstract class App implements Renderable {
  protected Cam camera;
  private volatile boolean closeRequested;
  
  protected App() {
    initStandardGL();
    initGL();
  }
  
  protected abstract void destroy();
  
  public static void initDisplay( final String title, final DisplayMode mode,
      final boolean vsync ) {
    try {
      if ( mode == null ) {
        Display.setFullscreen( true );
      } else {
        Display.setDisplayMode( mode );
      }
      
      Display.setResizable( true );
      Display.setTitle( title );
      Display.setVSyncEnabled( vsync );
      Display.create();
    } catch ( final LWJGLException e ) {
      e.printStackTrace();
    }
  }
  
  protected abstract void initGL();
  
  public static void initStandardGL() {
    glEnable( GL_CULL_FACE );
    glFrontFace( GL_CW );
    glCullFace( GL_BACK );
    
    glEnable( GL_TEXTURE_2D );
    glTexEnvi( GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_REPLACE );
    
    glClearColor( 0f, 0f, 0f, 1f );
    
    glEnable( GL_BLEND );
    glBlendFunc( GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA );
  }
  
  protected final void requestClose() {
    closeRequested = true;
  }
  
  public void run( final int fps ) {
    long lastFrame = Sys.getTime();
    
    while ( !Display.isCloseRequested() && !closeRequested ) {
      Display.sync( fps );
      
      if ( Display.wasResized() ) {
        glViewport( 0, 0, Display.getWidth(), Display.getHeight() );
      }
      
      final long thisFrame = Sys.getTime();
      final float delta = (float) ( thisFrame - lastFrame ) / Sys.getTimerResolution();
      lastFrame = thisFrame;
      
      update( delta );
      updateUI( delta );
      render();
      
      Display.update();
    }
    
    destroy();
    Display.destroy();
  }
  
  public void runParallel( final int fps, final long delay ) {
    final Thread updater = new Thread( () -> {
      update( delay / 1000f );
      long lastFrame = System.currentTimeMillis();
      
      while ( !closeRequested ) {
        try {
          Thread.sleep( delay );
        } catch ( final InterruptedException ignore ) {
        }
        
        final long thisFrame = System.currentTimeMillis();
        update( ( thisFrame - lastFrame ) / 1000f );
        lastFrame = thisFrame;
      }
    }, "OpenGL App Parallel Updater" );
    
    updater.setDaemon( true );
    updater.start();
    
    long lastFrame = Sys.getTime();
    
    while ( !closeRequested ) {
      Display.sync( fps );
      
      if ( Display.wasResized() ) {
        glViewport( 0, 0, Display.getWidth(), Display.getHeight() );
      }
      
      final long thisFrame = Sys.getTime();
      final float delta = (float) ( thisFrame - lastFrame ) / Sys.getTimerResolution();
      lastFrame = thisFrame;
      
      updateUI( delta );
      render();
      
      Display.update();
      
      if ( Display.isCloseRequested() ) {
        closeRequested = true;
      }
    }
    
    updater.interrupt();
    
    try {
      updater.join();
    } catch ( final InterruptedException ignore ) {
    }
    
    destroy();
    Display.destroy();
  }
  
  protected abstract void update( final float delta );
  
  protected void updateUI( final float delta ) {
    final Cam camera = this.camera;
    
    if ( camera != null ) {
      camera.update( delta );
    }
  }
}