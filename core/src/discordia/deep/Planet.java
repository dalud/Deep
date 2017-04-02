package discordia.deep;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Dalud on 2.4.2017.
 */

public class Planet {
    Model planetM;
    ModelInstance planetI;
    Material planetMat;
    float speed;
    Vector3 direction;

    public Planet(ModelBuilder modelBuilder, float size, Color color, Vector3 position){
        planetMat = new Material();
        planetMat.set(new ColorAttribute(ColorAttribute.Diffuse, color));
        planetM = modelBuilder.createSphere(size, size, size, 100, 100, planetMat, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        planetI = new ModelInstance(planetM);
        planetI.transform.setToTranslation(position);
        speed = .05f/position.len();
        direction = new Vector3();
    }

    public void render(ModelBatch batch, Environment environment) {
        batch.render(planetI, environment);
    }

    public void revolve() {
        planetI.transform.getTranslation(direction);
        direction.setLength(speed);
        direction.rotate(90, 0,1,0);
        planetI.transform.translate(direction);
    }
}