package net.lustenauer.obstacleavoid.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import net.lustenauer.obstacleavoid.component.BoundsComponent;
import net.lustenauer.obstacleavoid.component.MovementComponent;
import net.lustenauer.obstacleavoid.component.PlayerComponent;
import net.lustenauer.obstacleavoid.config.GameConfig;

public class EntityFactory {

    private final PooledEngine engine;

    public EntityFactory(PooledEngine engine) {
        this.engine = engine;
    }

    public void addPlayer() {
        float x = GameConfig.WORLD_WIDTH / 2f;
        float y = GameConfig.PLAYER_SIZE;

        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.bounds.set(x, y, GameConfig.PLAYER_BOUNDS_RADIUS);

        MovementComponent movement = engine.createComponent(MovementComponent.class);

        PlayerComponent player = engine.createComponent(PlayerComponent.class);


        Entity entity = engine.createEntity();
        entity.add(bounds);
        entity.add(movement);
        entity.add(player);

        engine.addEntity(entity);
    }
}
