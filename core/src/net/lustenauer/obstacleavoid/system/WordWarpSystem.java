package net.lustenauer.obstacleavoid.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.lustenauer.obstacleavoid.common.Mappers;
import net.lustenauer.obstacleavoid.component.DimensionComponent;
import net.lustenauer.obstacleavoid.component.PositionComponent;
import net.lustenauer.obstacleavoid.component.WorldWrapComponent;

/**
 * Created by Patric Hollenstein on 21.01.18.
 *
 * @author Patric Hollenstein
 */
public class WordWarpSystem extends IteratingSystem {
    private static final Family FAMILY = Family.all(
            WorldWrapComponent.class,
            PositionComponent.class,
            DimensionComponent.class
    ).get();

    private final Viewport viewport;

    public WordWarpSystem(Viewport viewport) {
        super(FAMILY);
        this.viewport = viewport;
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        PositionComponent position = Mappers.POSITION.get(entity);
        DimensionComponent dimension = Mappers.DIMENSION.get(entity);

        position.x = MathUtils.clamp(position.x, 0, viewport.getWorldWidth() - dimension.width);
        position.y = MathUtils.clamp(position.y, 0, viewport.getWorldHeight() - dimension.height);
    }
}
