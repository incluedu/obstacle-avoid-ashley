package net.lustenauer.obstacleavoid.common;

import com.badlogic.ashley.core.ComponentMapper;
import net.lustenauer.obstacleavoid.component.BoundsComponent;

public class Mappers {

    public static final ComponentMapper<BoundsComponent> BOUNDS = ComponentMapper.getFor(BoundsComponent.class);

    private Mappers() {
    }
}
