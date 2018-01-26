package net.lustenauer.obstacleavoid.system.collision;


import com.badlogic.ashley.core.Entity;

/**
 * Created by Patric Hollenstein on 22.01.18.
 *
 * @author Patric Hollenstein
 */
public interface CollisionListener {
    void hitObstacle();

    void hitCollectible(Entity collectibleEntity);
}
