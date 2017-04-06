package discordia.deep;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;


/**
 * Created by dalud on 6.4.2017.
 */

public class Station extends Planet {
    int yaw;

    public Station(ModelBuilder modelBuilder, float size, Color color, Vector3 position) {
        super(modelBuilder, size, color, position);
        planetMat = new Material();
        planetMat.set(new ColorAttribute(ColorAttribute.Diffuse, color));
        planetM = modelBuilder.createSphere(size, size, size, 6, 6, planetMat, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        planetI = new ModelInstance(planetM);
        this.position = position;
        planetI.transform.setToTranslation(position);
        speed = 1/position.len();
        yaw = 0;
    }

    @Override
    public void revolve() {
        planetI.transform.setFromEulerAngles(yaw, 0, 0).trn(position);
        position.rotate(speed, 0, 1, 0);
        yaw++;
        if(yaw == 360) yaw = 0;
    }
}