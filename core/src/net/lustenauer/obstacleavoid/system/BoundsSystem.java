package net.lustenauer.obstacleavoid.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import net.lustenauer.obstacleavoid.common.Mappers;
import net.lustenauer.obstacleavoid.component.BoundsComponent;
import net.lustenauer.obstacleavoid.component.DimensionComponent;
import net.lustenauer.obstacleavoid.component.PositionComponent;

/**
 * Created by Patric Hollenstein on 21.01.18.
 *
 * @author Patric Hollenstein
 */
public class BoundsSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            BoundsComponent.class,
            DimensionComponent.class
    ).get();

    public BoundsSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BoundsComponent bounds = Mappers.BOUNDS.get(entity);
        PositionComponent position = Mappers.POSITION.get(entity);
        DimensionComponent dimension = Mappers.DIMENSION.get(entity);
        bounds.bounds.x = position.x + dimension.width / 2f;
        bounds.bounds.y = position.y + dimension.height / 2f;
    }
}
