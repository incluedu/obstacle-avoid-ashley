package net.lustenauer.obstacleavoid.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.lustenauer.obstacleavoid.common.GameManager;
import net.lustenauer.obstacleavoid.config.GameConfig;

/**
 * Created by Patric Hollenstein on 21.01.18.
 *
 * @author Patric Hollenstein
 */
public class HudRenderSystem extends EntitySystem {
    private final Viewport viewport;
    private final SpriteBatch batch;
    private final BitmapFont font;

    private final GlyphLayout layout = new GlyphLayout();

    public HudRenderSystem(Viewport viewport, SpriteBatch batch, BitmapFont font) {
        this.viewport = viewport;
        this.batch = batch;
        this.font = font;
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.setColor(Color.WHITE);
        draw();
        batch.end();
    }

    private void draw() {
        String liveString = "LIVES: " + GameManager.INSTANCE.getLives();
        layout.setText(font, liveString);
        font.draw(batch, liveString, 20, GameConfig.HUD_HEIGHT - layout.height);

        String scoreString = "SCORE: " + GameManager.INSTANCE.getScore();
        layout.setText(font, scoreString);
        font.draw(batch, scoreString, GameConfig.HUD_WIDTH - layout.width - 20,
                GameConfig.HUD_HEIGHT - layout.height);
    }
}
