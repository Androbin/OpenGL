package de.androbin.opengl.cam;

import static de.androbin.lwjgl.util.Vector3fUtil.*;
import static de.androbin.math.util.floats.FloatMathUtil.*;
import static de.androbin.opengl.util.MouseUtil.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.*;
import org.lwjgl.util.vector.*;

public interface Cam3D extends Cam {
  @ Override
  default void applyCamera() {
    final Vector3f rotation = getCameraRotation();
    final Vector3f translation = getCameraTranslation();
    
    glRotatef( rotation.z, 0f, 0f, 1f );
    glRotatef( rotation.x, 1f, 0f, 0f );
    glRotatef( rotation.y, 0f, 1f, 0f );
    glTranslatef( -translation.x, -translation.y, -translation.z );
  }
  
  Vector3f getCameraRotation();
  
  Vector3f getCameraTranslation();
  
  default Vector3f getViewVector( final Vector3f dst ) {
    return getDirVector( dst, getCameraRotation() );
  }
  
  default boolean updateCameraRotation( final float sensitivity ) {
    final int mx = Mouse.getDX();
    final int my = Mouse.getDY();
    
    if ( ( mx | my ) == 0 ) {
      return false;
    }
    
    final Vector3f dir = getCameraRotation();
    final float dx = dir.x - my * sensitivity;
    final float dy = dir.y + mx * sensitivity;
    dir.set( bound( -90f, dx, 90f ), dy % 360f );
    
    centerMouse();
    return true;
  }
}