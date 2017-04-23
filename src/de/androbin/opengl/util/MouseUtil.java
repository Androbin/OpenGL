package de.androbin.opengl.util;

import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

public final class MouseUtil
{
	private MouseUtil()
	{
	}
	
	public static void centerMouse()
	{
		Mouse.setCursorPosition( Display.getWidth() >> 1, Display.getHeight() >> 1 );
	}
}