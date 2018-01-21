package net.lustenauer.obstacleavoid.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Logger;
import net.lustenauer.obstacleavoid.common.Mappers;
import net.lustenauer.obstacleavoid.component.MovementComponent;
import net.lustenauer.obstacleavoid.component.PositionComponent;

/**
 * Created by Patric Hollenstein on 21.01.18.
 *
 * @author Patric Hollenstein
 */
public class MovementSystem extends IteratingSystem {
    private static final Logger log = new Logger(MovementSystem.class.getName(), Logger.DEBUG);

    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            MovementComponent.class
    ).get();

    public MovementSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);
        MovementComponent movement = Mappers.MOVEMENT.get(entity);

        position.x += movement.xSpeed;
        position.y += movement.ySpeed;
    }
}
