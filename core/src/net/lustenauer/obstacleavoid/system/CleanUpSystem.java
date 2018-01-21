package net.lustenauer.obstacleavoid.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import net.lustenauer.obstacleavoid.common.Mappers;
import net.lustenauer.obstacleavoid.component.CleanUpComponent;
import net.lustenauer.obstacleavoid.component.PositionComponent;
import net.lustenauer.obstacleavoid.config.GameConfig;

/**
 * Created by Patric Hollenstein on 21.01.18.
 *
 * @author Patric Hollenstein
 */
public class CleanUpSystem extends IteratingSystem {
    private static final Family FAMILY = Family.all(
            CleanUpComponent.class,
            PositionComponent.class
    ).get();

    public CleanUpSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);

        if (position.y < -GameConfig.OBSTACLE_SIZE) {
            getEngine().removeEntity(entity);
        }
    }
}
