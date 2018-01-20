package net.lustenauer.obstacleavoid.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Logger;
import net.lustenauer.obstacleavoid.common.Mappers;
import net.lustenauer.obstacleavoid.component.BoundsComponent;
import net.lustenauer.obstacleavoid.component.MovementComponent;
import net.lustenauer.obstacleavoid.component.PlayerComponent;
import net.lustenauer.obstacleavoid.config.GameConfig;

/**
 * Created by Patric Hollenstein on 20.01.18.
 *
 * @author Patric Hollenstein
 */
public class PlayerSystem extends IteratingSystem {
    private static final Logger log = new Logger(PlayerSystem.class.getName(), Logger.DEBUG);

    private static final Family FAMILY = Family.all(
            PlayerComponent.class,
            MovementComponent.class
    ).get();

    public PlayerSystem() {
        super(FAMILY);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MovementComponent movement = Mappers.MOVEMENT.get(entity);

        movement.xSpeed = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            movement.xSpeed = GameConfig.MAX_PLAYER_X_SPEED;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            movement.xSpeed = -GameConfig.MAX_PLAYER_X_SPEED;
        }
    }
}
