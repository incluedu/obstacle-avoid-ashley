package net.lustenauer.obstacleavoid.system.debug;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.lustenauer.obstacleavoid.common.Mappers;
import net.lustenauer.obstacleavoid.component.BoundsComponent;


public class DebugRenderSystem extends IteratingSystem {
    private static final Logger log = new Logger(DebugRenderSystem.class.getName(), Logger.DEBUG);

    private static final Family FAMILY = Family.all(BoundsComponent.class).get();


    private final Viewport viewport;
    private final ShapeRenderer renderer;

    public DebugRenderSystem(Viewport viewport, ShapeRenderer renderer) {
        super(FAMILY);
        this.viewport = viewport;
        this.renderer = renderer;
    }

    @Override
    public void update(float deltaTime) {
        log.debug("update()");
        Color oldColor = renderer.getColor().cpy();

        viewport.apply();
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.RED);

        super.update(deltaTime);

        renderer.end();
        renderer.setColor(oldColor);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        log.debug("processEntity= " + entity);

        BoundsComponent bc = Mappers.BOUNDS.get(entity);
        renderer.circle(bc.bounds.x, bc.bounds.y, bc.bounds.radius,30);
    }
}
