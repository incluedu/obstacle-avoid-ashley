package net.lustenauer.obstacleavoid.screen.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.lustenauer.obstacleavoid.ObstacleAvoidGame;
import net.lustenauer.obstacleavoid.assets.AssetDescriptors;
import net.lustenauer.obstacleavoid.common.EntityFactory;
import net.lustenauer.obstacleavoid.common.GameManager;
import net.lustenauer.obstacleavoid.config.GameConfig;
import net.lustenauer.obstacleavoid.screen.menu.MenuScreen;
import net.lustenauer.obstacleavoid.system.*;
import net.lustenauer.obstacleavoid.system.collision.CollisionListener;
import net.lustenauer.obstacleavoid.system.collision.CollisionSystem;
import net.lustenauer.obstacleavoid.system.debug.DebugCameraSystem;
import net.lustenauer.obstacleavoid.system.debug.DebugRenderSystem;
import net.lustenauer.obstacleavoid.system.debug.GridRenderSystem;
import net.lustenauer.obstacleavoid.util.GdxUtils;

/**
 * @author Patric Hollenstein
 */
public class GameScreen implements Screen {
    private static final Logger log = new Logger(GameScreen.class.getName(), Logger.DEBUG);

    private final ObstacleAvoidGame game;
    private final AssetManager assetManager;

    private OrthographicCamera camera;
    private Viewport viewport;
    private Viewport hudViewport;
    private ShapeRenderer renderer;
    private PooledEngine engine;
    private EntityFactory factory;
    private Sound hit;

    public GameScreen(ObstacleAvoidGame game) {
        this.game = game;
        this.assetManager = game.getAssetManager();
    }

    @Override
    public void show() {
        log.debug("show");
        GameManager.INSTANCE.reset();
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
        renderer = new ShapeRenderer();
        engine = new PooledEngine();
        factory = new EntityFactory(engine, assetManager);

        BitmapFont font = assetManager.get(AssetDescriptors.FONT);
        hit = assetManager.get(AssetDescriptors.HIT_SOUND);
        SpriteBatch batch = game.getBatch();


        CollisionListener listener = new CollisionListener() {
            @Override
            public void hitObstacle() {
                GameManager.INSTANCE.decrementLives();
                hit.play();

                if (GameManager.INSTANCE.isGameOver()) {
                    GameManager.INSTANCE.updateHighScore();
                } else {
                    engine.removeAllEntities();
                    addEntities();
                }

            }
        };

        engine.addSystem(new DebugCameraSystem(camera, GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y));

        engine.addSystem(new PlayerSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem(new WordWarapSystem(viewport));
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new ObstacleSpawnSystem(factory));
        engine.addSystem(new CleanUpSystem());
        engine.addSystem(new CollisionSystem(listener));
        engine.addSystem(new ScoreSystem());

        engine.addSystem(new RenderSystem(viewport, batch));
        engine.addSystem(new GridRenderSystem(viewport, renderer));
        engine.addSystem(new DebugRenderSystem(viewport, renderer));

        engine.addSystem(new HudRenderSystem(hudViewport, batch, font));

        addEntities();
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();
        engine.update(delta);

        if (GameManager.INSTANCE.isGameOver()) {
            game.setScreen(new MenuScreen(game));
        }
    }

    private void addEntities() {
        factory.addBackground();
        factory.addPlayer();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
