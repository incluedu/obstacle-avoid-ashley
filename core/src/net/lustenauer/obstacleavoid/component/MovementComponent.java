package net.lustenauer.obstacleavoid.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Patric Hollenstein on 20.01.18.
 *
 * @author Patric Hollenstein
 */
public class MovementComponent implements Component, Pool.Poolable {
    public float xSpeed;
    public float ySpeed;

    @Override
    public void reset() {
        xSpeed = 0f;
        ySpeed = 0f;
    }
}
