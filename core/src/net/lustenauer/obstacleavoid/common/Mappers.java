package net.lustenauer.obstacleavoid.common;

import com.badlogic.ashley.core.ComponentMapper;
import net.lustenauer.obstacleavoid.component.*;

public class Mappers {

    public static final ComponentMapper<BoundsComponent> BOUNDS = ComponentMapper.getFor(BoundsComponent.class);

    public static final ComponentMapper<MovementComponent> MOVEMENT = ComponentMapper.getFor(MovementComponent.class);

    public static final ComponentMapper<PositionComponent> POSITION = ComponentMapper.getFor(PositionComponent.class);

    public static final ComponentMapper<ObstacleComponent> OBSTACLE = ComponentMapper.getFor(ObstacleComponent.class);

    public static final ComponentMapper<TextureComponent> TEXTURE = ComponentMapper.getFor(TextureComponent.class);

    public static final ComponentMapper<DimensionComponent> DIMENSION = ComponentMapper.getFor(DimensionComponent.class);

    public static final ComponentMapper<CollectibleComponent> COLLECTIBLE = ComponentMapper.getFor(CollectibleComponent.class);

    private Mappers() {
    }
}
