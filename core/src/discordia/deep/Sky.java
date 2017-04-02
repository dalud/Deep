package discordia.deep;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

/**
 * Created by Dalud on 2.4.2017.
 */

public class Sky {
    Model sky;
    ModelInstance skyI;

    public Sky(ModelLoader g3dLoader){
        sky = g3dLoader.loadModel(Gdx.files.internal("sky/sky.g3db"));
        skyI = new ModelInstance(sky);
        float scale = .08f;
        skyI.nodes.get(0).scale.set(scale, scale, scale);
        skyI.calculateTransforms();
    }

    public void render(ModelBatch batch) {
        batch.render(skyI);
    }

    public void revolve() {
        skyI.transform.rotate(0, 1, 0, -.03f);
        skyI.transform.rotate(1, 0, 0, -.02f);
        skyI.transform.rotate(0, 1, 1, -.01f);
    }
}