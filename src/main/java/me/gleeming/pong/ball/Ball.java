package me.gleeming.pong.ball;

import lombok.Getter;
import me.gleeming.gengine.color.GengineColor;
import me.gleeming.gengine.draw.DrawQueue;
import me.gleeming.gengine.draw.type.FilledOvalDrawQueue;
import me.gleeming.gengine.math.Rectangle;
import me.gleeming.pong.screen.GameScreen;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Ball {
    @Getter private int x, y, xSpeed, ySpeed;
    @Getter private final int width = 15, height = 15;

    private boolean disabled;

    public Ball() {
        x = 800 / 2;
        y = 480 / 2;

        xSpeed = 3;
        ySpeed = 3;
    }

    public void update() {
        if(y - height < 0 || y > 480 - height) ySpeed = -ySpeed;
        if(x - width < 15) {
            x = 400;
            y = 240;
            xSpeed = 0;
            ySpeed = 0;

            disabled = true;
            GameScreen.getInstance().getSecondPaddle().setScore(GameScreen.getInstance().getSecondPaddle().getScore() + 1);

            new Timer().schedule(new TimerTask() {
                public void run() {
                    disabled = false;

                    xSpeed = -3;
                    ySpeed = -3;
                }
            }, 1000);
            return;
        } else if(x + width > (800 - 30)){
            x = 400;
            y = 240;
            xSpeed = 0;
            ySpeed = 0;

            disabled = true;
            GameScreen.getInstance().getFirstPaddle().setScore(GameScreen.getInstance().getFirstPaddle().getScore() + 1);

            new Timer().schedule(new TimerTask() {
                public void run() {
                    disabled = false;

                    xSpeed = 3;
                    ySpeed = 3;
                }
            }, 1000);
            return;
        } else if(GameScreen.getInstance().getFirstPaddle().getRectangle().colliding(getRectangle()) || GameScreen.getInstance().getSecondPaddle().getRectangle().colliding(getRectangle())  && !disabled) {
            xSpeed = -xSpeed;
        }

        x = x + xSpeed;
        y = y + ySpeed;
    }

    public List<DrawQueue> draw() {
        List<DrawQueue> queues = new ArrayList<>();

        queues.add(new FilledOvalDrawQueue(x, y, width, height, new GengineColor(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue())));

        return queues;
    }

    public Rectangle getRectangle() { return new Rectangle(x, y, width, height); }
}
