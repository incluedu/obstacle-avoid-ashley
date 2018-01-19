package net.lustenauer.obstacleavoid.system.debug;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.lustenauer.obstacleavoid.util.ViewportUtils;

/**
 * Created by Patric Hollenstein on 18.01.18.
 *
 * @author Patric Hollenstein
 */
public class GridRenderSystem extends EntitySystem {
    private static final Logger log = new Logger(GridRenderSystem.class.getName(), Logger.DEBUG);
    /*
     * ATTRIBUTES
     */
    private final Viewport viewport;
    private final ShapeRenderer renderer;

    /*
     * CONSTRUCTORS
     */

    public GridRenderSystem(Viewport viewport, ShapeRenderer renderer) {
        this.viewport = viewport;
        this.renderer = renderer;
    }

    /*
     * UPDATE
     */
    @Override
    public void update(float deltaTime) {
        log.debug("update");

        viewport.apply();
        ViewportUtils.drawGrid(viewport, renderer);
    }
}
