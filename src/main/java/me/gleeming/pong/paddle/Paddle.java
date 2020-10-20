package me.gleeming.pong.paddle;

import lombok.Getter;
import lombok.Setter;
import me.gleeming.gengine.Gengine;
import me.gleeming.gengine.color.GengineColor;
import me.gleeming.gengine.draw.DrawQueue;
import me.gleeming.gengine.draw.type.FilledMathRectangleDrawQueue;
import me.gleeming.gengine.draw.type.FilledRectangleDrawQueue;
import me.gleeming.gengine.game.provider.type.DesktopProvider;
import me.gleeming.gengine.input.type.GengineDesktopInput;
import me.gleeming.gengine.math.Rectangle;
import me.gleeming.gengine.resource.Resource;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Paddle {
    @Getter @Setter private int x, y;

    @Getter private final int width = 10, height = 50;
    @Getter private final boolean first;

    @Getter @Setter private int score;

    public Paddle(int x, boolean first) {
        this.x = x;
        this.y = 240;
        this.first = first;
    }

    public void update() {
        if(first) {
            if(DesktopProvider.getInput().isPressed(GengineDesktopInput.Key.W) && (y - 5 > 0 + 10)) y = y - 5;
            if(DesktopProvider.getInput().isPressed(GengineDesktopInput.Key.S) && (y + 5 < 480 - height)) y = y + 5;
        } else {
            if(DesktopProvider.getInput().isPressed(GengineDesktopInput.Key.O) && (y - 5 > 0 + 10)) y = y - 5;
            if(DesktopProvider.getInput().isPressed(GengineDesktopInput.Key.L) && (y + 5 < 480 - height)) y = y + 5;
        }
    }

    public List<DrawQueue> draw() {
        List<DrawQueue> drawQueues = new ArrayList<>();

        drawQueues.add(new FilledMathRectangleDrawQueue(getRectangle(), new GengineColor(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue())));

        return drawQueues;
    }

    public Rectangle getRectangle() { return new Rectangle(x, y, width, height); }
}
