package me.gleeming.pong;

import lombok.Getter;
import me.gleeming.gengine.Gengine;
import me.gleeming.gengine.game.Gengine2DGame;
import me.gleeming.gengine.game.provider.type.DesktopProvider;
import me.gleeming.pong.screen.GameScreen;

public class Pong extends Gengine2DGame {
    @Getter private static Pong instance;
    public Pong() {
        super(new DesktopProvider("Pong (1.0-SNAPSHOT)", 800, 480));

        instance = this;

        Gengine.getInstance().changeScreen(new GameScreen());
    }
}
