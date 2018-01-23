package net.lustenauer.obstacleavoid.system;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import net.lustenauer.obstacleavoid.common.GameManager;
import net.lustenauer.obstacleavoid.config.GameConfig;

/**
 * Created by Patric Hollenstein on 23.01.18.
 *
 * @author Patric Hollenstein
 */
public class ScoreSystem extends IntervalSystem {
    public ScoreSystem() {
        super(GameConfig.SCORE_MAX_TIME);
    }

    @Override
    protected void updateInterval() {
        GameManager.INSTANCE.updateScore(MathUtils.random(1,5));
    }
}
