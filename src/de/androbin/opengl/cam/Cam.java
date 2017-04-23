package de.androbin.opengl.cam;

public interface Cam
{
	void applyCamera();
	
	boolean update( final float delta );
}