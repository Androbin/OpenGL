package de.androbin.opengl;

import static org.lwjgl.opengl.GL11.*;
import java.util.concurrent.atomic.*;
import org.lwjgl.*;
import org.lwjgl.opengl.*;

public abstract class App implements Renderable
{
	private volatile boolean closeRequested;
	
	protected App()
	{
		initStandardGL();
		initGL();
	}
	
	protected abstract void destroy();
	
	public static void initDisplay( final String title, final DisplayMode display_mode, final boolean vsync )
	{
		try
		{
			if ( display_mode == null )
			{
				Display.setFullscreen( true );
			}
			else
			{
				Display.setDisplayMode( display_mode );
			}
			
			Display.setResizable( true );
			Display.setTitle( title );
			Display.setVSyncEnabled( vsync );
			Display.create();
		}
		catch ( final LWJGLException e )
		{
			e.printStackTrace();
		}
	}
	
	protected abstract void initGL();
	
	public static void initStandardGL()
	{
		glEnable( GL_CULL_FACE );
		glFrontFace( GL_CW );
		glCullFace( GL_BACK );
		
		glEnable( GL_TEXTURE_2D );
		glTexEnvi( GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_REPLACE );
		
		glClearColor( 0f, 0f, 0f, 1f );
		
		glEnable( GL_BLEND );
		glBlendFunc( GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA );
	}
	
	public final boolean isCloseRequested()
	{
		return closeRequested;
	}
	
	protected final void requestClose()
	{
		closeRequested = true;
	}
	
	public static void run( final App app, final int fps )
	{
		long lastFrame = Sys.getTime();
		
		while ( !Display.isCloseRequested() && !app.isCloseRequested() )
		{
			if ( Display.wasResized() )
			{
				glViewport( 0, 0, Display.getWidth(), Display.getHeight() );
			}
			
			final long thisFrame = Sys.getTime();
			final float delta = (float) ( thisFrame - lastFrame ) / Sys.getTimerResolution();
			lastFrame = thisFrame;
			
			app.update( delta );
			app.updateUI( delta );
			app.render();
			
			Display.update();
			Display.sync( fps );
		}
		
		app.destroy();
		Display.destroy();
	}
	
	public static void runParallel( final App app, final int fps, final long delay )
	{
		final AtomicBoolean running = new AtomicBoolean( true );
		
		final Thread updater = new Thread( () ->
		{
			long lastFrame = Sys.getTime();
			
			while ( running.get() )
			{
				try
				{
					Thread.sleep( delay );
				}
				catch ( final InterruptedException ignore )
				{
				}
				
				final long thisFrame = Sys.getTime();
				app.update( (float) ( thisFrame - lastFrame ) / Sys.getTimerResolution() );
				lastFrame = thisFrame;
			}
		} );
		
		updater.start();
		
		long lastFrame = Sys.getTime();
		
		while ( running.get() )
		{
			if ( Display.wasResized() )
			{
				glViewport( 0, 0, Display.getWidth(), Display.getHeight() );
			}
			
			final long thisFrame = Sys.getTime();
			final float delta = (float) ( thisFrame - lastFrame ) / Sys.getTimerResolution();
			lastFrame = thisFrame;
			
			app.updateUI( delta );
			app.render();
			
			Display.update();
			Display.sync( fps );
			
			running.set( !Display.isCloseRequested() && !app.isCloseRequested() );
		}
		
		try
		{
			updater.join();
		}
		catch ( final InterruptedException ignore )
		{
		}
		
		app.destroy();
		Display.destroy();
	}
	
	protected abstract void update( final float delta );
	
	protected void updateUI( final float delta )
	{
	}
}
