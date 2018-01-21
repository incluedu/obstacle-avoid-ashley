package net.lustenauer.obstacleavoid.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Patric Hollenstein on 21.01.18.
 *
 * @author Patric Hollenstein
 */
public class ObstacleComponent implements Component, Pool.Poolable {
    public boolean hit;

    @Override
    public void reset() {
        hit = false;
    }
}
