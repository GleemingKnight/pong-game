package me.gleeming.pong.screen;

import lombok.Getter;
import me.gleeming.gengine.color.GengineColor;
import me.gleeming.gengine.draw.DrawQueue;
import me.gleeming.gengine.draw.type.FilledRectangleDrawQueue;
import me.gleeming.gengine.draw.type.TextDrawQueue;
import me.gleeming.gengine.font.type.TTFGengineFont;
import me.gleeming.gengine.screen.GengineScreen;
import me.gleeming.pong.ball.Ball;
import me.gleeming.pong.paddle.Paddle;

import java.awt.*;
import java.util.List;

public class GameScreen extends GengineScreen {
    @Getter private static GameScreen instance;

    @Getter private final Paddle firstPaddle;
    @Getter private final Paddle secondPaddle;

    @Getter private final Ball ball;
    public GameScreen() {
        instance = this;

        this.firstPaddle = new Paddle(35, true);
        this.secondPaddle = new Paddle(800 - 35, false);

        this.ball = new Ball();
    }

    public List<DrawQueue> draw(List<DrawQueue> list) {
        list.add(new FilledRectangleDrawQueue(0, 0, 800, 480, new GengineColor(Color.BLACK.getRed(), Color.BLACK.getGreen(), Color.BLACK.getBlue())));
        list.add(new TextDrawQueue(String.valueOf(firstPaddle.getScore()), 400 - 75, 75).setColor(new GengineColor(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue())));
        list.add(new TextDrawQueue(String.valueOf(secondPaddle.getScore()), 400 + 75, 75).setColor(new GengineColor(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue())));

        list.addAll(firstPaddle.draw());
        list.addAll(secondPaddle.draw());
        list.addAll(ball.draw());

        firstPaddle.update();
        secondPaddle.update();
        ball.update();

        return list;
    }
}
