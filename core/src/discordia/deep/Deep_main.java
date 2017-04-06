package discordia.deep;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.UBJsonReader;

public class Deep_main extends ApplicationAdapter {
	ModelBatch batch;
	ModelBuilder modelBuilder;
	Sky sky;
	Sun sun;
    Array<Planet> planets;
    Station station;
	Environment environment;
	PointLight sunL;
	PerspectiveCamera cam;
    InputHandler input;
    InputProcessor ext;
    InputMultiplexer inputs;
	
	@Override
	public void create () {
		batch = new ModelBatch();
		modelBuilder = new ModelBuilder();
		ModelLoader g3dLoader = new G3dModelLoader(new UBJsonReader());
		sun = new Sun(modelBuilder);
        sky = new Sky(g3dLoader);
        planets = new Array<Planet>();
        planets.add(new Planet(modelBuilder, 1, Color.PURPLE, new Vector3(2 ,0, 0)));
        planets.add(new Planet(modelBuilder, 1.4f, Color.BROWN, new Vector3(3.5f, 0, 3.5f)));
        planets.add(new Planet(modelBuilder, .6f, Color.CHARTREUSE, new Vector3(-2.5f, 0, -2.5f)));

        station = new Station(modelBuilder, .8f, Color.GRAY, new Vector3(-2.2f, 0, 2.2f));

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, .15f, .15f, .15f, 1));
		sunL = new PointLight();
		sunL.set(Color.WHITE, 0,0,0, 15);
		environment.add(sunL);

		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //VIEWPORT WIDTH/HEIGHT ?
		cam.position.set(0, 4.5f, 15);
		cam.far = 5000;
		cam.lookAt(0, -1, 0);
		cam.update();

        input = new InputHandler(cam);
        ext = new GestureDetector(new InputHandlerExtended(input));
        inputs = new InputMultiplexer(input, ext);
        Gdx.input.setInputProcessor(inputs);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        input.poll();
        cam.update();

        sky.revolve();
        sun.shine();
        for(Planet planet : planets) planet.revolve();
        station.revolve();

		batch.begin(cam);
		sky.render(batch);
		sun.render(batch);
        for(Planet planet : planets) planet.render(batch, environment);
        station.render(batch, environment);
		batch.end();
    }
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}