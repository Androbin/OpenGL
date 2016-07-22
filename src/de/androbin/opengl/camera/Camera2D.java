package de.androbin.opengl.camera;

import static de.androbin.lwjgl.util.Vector2fUtil.*;
import static org.lwjgl.opengl.GL11.*;
import jdk.nashorn.internal.objects.annotations.*;
import org.lwjgl.util.vector.*;

public interface Camera2D extends Camera
{
	@ Override
	default void applyCamera()
	{
		final float rotation = getCameraRotation();
		final Vector2f translation = getCameraTranslation();
		
		glTranslatef( -translation.x, -translation.y, 0f );
		
		glTranslatef( 270f, 270f, 0f );
		glRotatef( rotation, 0f, 0f, 1f );
		glTranslatef( -270f, -270f, 0f );
	}
	
	@ Getter
	float getCameraRotation();
	
	@ Getter
	Vector2f getCameraTranslation();
	
	default Vector2f getViewVector( final Vector2f dst )
	{
		return getDirVector( dst, getCameraRotation() );
	}
}