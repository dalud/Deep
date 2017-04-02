package discordia.deep;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

/**
 * Created by Dalud on 2.4.2017.
 */

public class Sun {
    Model sunCore, sunHalo;
    ModelInstance sunCI, sunHI;
    Material sunHM;
    float haloIntensity, haloSlider;

    public Sun(ModelBuilder modelBuilder){
        sunCore = modelBuilder.createSphere(2, 2, 2, 100, 100, new Material(), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        sunCI = new ModelInstance(sunCore);

        sunHM = new Material();
        sunHM.set(new ColorAttribute(ColorAttribute.Diffuse, 1, 1, 0, 1));
        sunHM.set(new BlendingAttribute(true, .3f));
        sunHalo = modelBuilder.createSphere(2.15f, 2.15f, 2.15f, 100, 100, sunHM, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        sunHI = new ModelInstance(sunHalo);
        haloSlider = 0;
    }

    public void render(ModelBatch batch){
        batch.render(sunCI);
        batch.render(sunHI);
    }

    public void shine(){
        sunHI.materials.first().set(new BlendingAttribute(true, haloIntensity));
        haloSlider += .03f;
        if(haloSlider > Math.PI*2) haloSlider = 0;
        haloIntensity = (float) ((1+Math.sin(haloSlider))*.15f+.3f);
    }
}
