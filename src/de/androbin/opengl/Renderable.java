package de.androbin.opengl;

import static org.lwjgl.opengl.GL11.*;

@ FunctionalInterface
public interface Renderable {
  default int createDisplayList() {
    final int id = glGenLists( 1 );
    createDisplayList( id );
    return id;
  }
  
  default void createDisplayList( final int id ) {
    glNewList( id, GL_COMPILE );
    render();
    glEndList();
  }
  
  void render();
}