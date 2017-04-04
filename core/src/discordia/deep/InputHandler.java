package discordia.deep;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by dalud on 3.4.2017.
 */

public class InputHandler implements InputProcessor {
    PerspectiveCamera cam;
    int keycode;
    Vector3 position;
    float distance;
    int initY;

    public InputHandler(PerspectiveCamera cam){
        this.cam = cam;
        position = cam.position;
        distance = position.len();
    }

    public void poll(){
        switch (keycode){
            case Input.Keys.DOWN:
                tilt(.1f);
                break;
            case Input.Keys.UP:
                tilt(-.1f);
                break;
            case Input.Keys.SPACE:
                zoom(-.1f);
                break;
            case Input.Keys.SHIFT_LEFT:
                zoom(.1f);
                break;
        }
        //MITÄHÄN TÄSSÄ TAPAHTUU??? OISKO KOMMENTOINTI PAIKALLAAN...
        if(distance < 8) distance = 8;
        else if(distance > 30) distance = 30;
        position.setLength(distance);
        if(position.z < 4) position.set(position.x, position.y, 4);
        if(position.len() != distance) position.setLength(distance);
        if(position.len() < 8) position.setLength(8);
        if(position.len() > 30) position.setLength(30);

        cam.lookAt(0, -1, 0);
    }

    public void zoom(float delta) {
        distance += delta;
    }

    public void tilt(float delta){
        position.add(0, delta, 0);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        initY = screenY;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        tilt((float)(screenY-initY) / 1000);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        this.keycode = keycode;
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        this.keycode = 0;
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}