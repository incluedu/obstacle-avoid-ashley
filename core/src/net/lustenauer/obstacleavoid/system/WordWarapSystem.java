package net.lustenauer.obstacleavoid.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.lustenauer.obstacleavoid.common.Mappers;
import net.lustenauer.obstacleavoid.component.PositionComponent;
import net.lustenauer.obstacleavoid.component.WorldWrapComponent;

/**
 * Created by Patric Hollenstein on 21.01.18.
 *
 * @author Patric Hollenstein
 */
public class WordWarapSystem extends IteratingSystem {
    private static final Family FAMILY = Family.all(
            WorldWrapComponent.class,
            PositionComponent.class
    ).get();

    private final Viewport viewport;

    public WordWarapSystem(Viewport viewport) {
        super(FAMILY);
        this.viewport = viewport;
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        PositionComponent postion = Mappers.POSITION.get(entity);
        WorldWrapComponent worldWarp = Mappers.WORLD_WRAP.get(entity);

        postion.x = MathUtils.clamp(postion.x, 0, viewport.getWorldWidth());
        postion.y = MathUtils.clamp(postion.y, 0, viewport.getWorldHeight());
    }
}
