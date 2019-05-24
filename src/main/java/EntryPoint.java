import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.opengl.GL;
public class EntryPoint implements Runnable{
private Thread thread;
public long window;
public boolean running;
private void start() {
running = true;
thread = new Thread(this);
thread.start();
}
private void init() {
if(!glfwInit()) {
	System.err.println("failed to initialise glfw");
}
glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
window = glfwCreateWindow(800, 700, "Starcarv", NULL, NULL);
if(window == NULL) {
	System.err.println("failed to create the window");
}

glfwMaximizeWindow(window);
glfwMakeContextCurrent(window);
glfwShowWindow(window);


GL.createCapabilities();
glClearColor(0.2f, 0.0f, 0.2f, 1.0f);
}
private void render() {
glfwSwapBuffers(window);
}
private void update() {
glfwPollEvents();
}
@Override
public void run() {
init();
while(running) {
update();
render();
if(glfwWindowShouldClose(window) == true) {
	running = false;
}
}
}

public static void main(String[] args) {
EntryPoint entry = new EntryPoint();
entry.start();
}
}