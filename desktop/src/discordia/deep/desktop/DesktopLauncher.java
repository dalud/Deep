package discordia.deep.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import discordia.deep.Deep_main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		System.setProperty("user.name", "user");

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 720;
		config.height = 1280;
		new LwjglApplication(new Deep_main(), config);
	}
}