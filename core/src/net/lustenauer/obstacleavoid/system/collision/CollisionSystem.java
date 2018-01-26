package net.lustenauer.obstacleavoid.system.collision;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Logger;
import net.lustenauer.obstacleavoid.common.Mappers;
import net.lustenauer.obstacleavoid.component.BoundsComponent;
import net.lustenauer.obstacleavoid.component.CollectibleComponent;
import net.lustenauer.obstacleavoid.component.ObstacleComponent;
import net.lustenauer.obstacleavoid.component.PlayerComponent;

/**
 * @author Patric Hollenstein
 */
public class CollisionSystem extends EntitySystem {
    private static final Logger log = new Logger(CollisionSystem.class.getName(), Logger.DEBUG);

    private static final Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class,
            BoundsComponent.class
    ).get();

    private static final Family OBSTACLE_FAMILY = Family.all(
            ObstacleComponent.class,
            BoundsComponent.class
    ).get();

    private static final Family COLLECTIBLE_FAMILY = Family.all(
            CollectibleComponent.class,
            BoundsComponent.class
    ).get();


    private final CollisionListener listener;

    public CollisionSystem(CollisionListener listener) {
        this.listener = listener;
    }

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> players = getEngine().getEntitiesFor(PLAYER_FAMILY);
        ImmutableArray<Entity> obstacles = getEngine().getEntitiesFor(OBSTACLE_FAMILY);
        ImmutableArray<Entity> collectibles = getEngine().getEntitiesFor(COLLECTIBLE_FAMILY);

        for (Entity playerEntity : players) {
            for (Entity obstacleEntity : obstacles) {
                ObstacleComponent obstacle = Mappers.OBSTACLE.get(obstacleEntity);

                if (obstacle.hit) {
                    continue;
                }

                if (checkCollision(playerEntity, obstacleEntity)) {
                    obstacle.hit = true;
                    log.debug("collision with obstacle");
                    listener.hitObstacle();
                }
            }
            for (Entity collectibleEntity : collectibles) {
                CollectibleComponent collectible = Mappers.COLLECTIBLE.get(collectibleEntity);

                if (collectible.hit) {
                    continue;
                }

                if (checkCollision(playerEntity, collectibleEntity)) {
                    collectible.hit = true;
                    log.debug("collision with collectible");
                    listener.hitCollectible(collectibleEntity);
                }

            }
        }
    }

    private boolean checkCollision(Entity entity1, Entity entity2) {
        BoundsComponent bounds1 = Mappers.BOUNDS.get(entity1);
        BoundsComponent bounds2 = Mappers.BOUNDS.get(entity2);

        return Intersector.overlaps(bounds1.bounds, bounds2.bounds);
    }
}
