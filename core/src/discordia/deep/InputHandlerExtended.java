package discordia.deep;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dalud on 3.4.2017.
 */

public class InputHandlerExtended implements GestureDetector.GestureListener {
    InputHandler handler;

    public InputHandlerExtended(InputHandler handler){
        this.handler = handler;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        handler.zoom((initialDistance-distance) / 3000);
        return false;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
         return false;
    }

    @Override
    public void pinchStop() {

    }
}