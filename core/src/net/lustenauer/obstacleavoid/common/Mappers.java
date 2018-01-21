package net.lustenauer.obstacleavoid.common;

import com.badlogic.ashley.core.ComponentMapper;
import net.lustenauer.obstacleavoid.component.BoundsComponent;
import net.lustenauer.obstacleavoid.component.MovementComponent;
import net.lustenauer.obstacleavoid.component.PositionComponent;

public class Mappers {

    public static final ComponentMapper<BoundsComponent> BOUNDS = ComponentMapper.getFor(BoundsComponent.class);

    public static final ComponentMapper<MovementComponent> MOVEMENT = ComponentMapper.getFor(MovementComponent.class);

    public static final ComponentMapper<PositionComponent> POSITION = ComponentMapper.getFor(PositionComponent.class);

    private Mappers() {
    }
}
