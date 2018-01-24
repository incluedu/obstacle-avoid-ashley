package net.lustenauer.obstacleavoid.system;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import net.lustenauer.obstacleavoid.common.EntityFactory;
import net.lustenauer.obstacleavoid.config.GameConfig;

/**
 * Created by Patric Hollenstein on 21.01.18.
 *
 * @author Patric Hollenstein
 */
public class ObstacleSpawnSystem extends IntervalSystem {

    private final EntityFactory factory;

    public ObstacleSpawnSystem(EntityFactory factory) {
        super(GameConfig.OBSTACLE_SPAWN_TIME);
        this.factory = factory;
    }

    @Override
    protected void updateInterval() {
        float min = 0;
        float max = GameConfig.WORLD_WIDTH - GameConfig.OBSTACLE_SIZE;

        float obstacleX = MathUtils.random(min, max);
        float obstacleY = GameConfig.WORLD_HEIGHT;

        factory.addObstacle(obstacleX, obstacleY);

    }
}
