package cs355.view.drawable;

import cs355.model.drawing.Shape;

import java.awt.*;

/**
 * Created by allenliao on 1/23/17.
 */
public class DrawableShape {

    private Color color;

    public DrawableShape(Shape s){
        color = s.getColor();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void draw(Graphics2D g2d){

    }

    public void draw_handle(Graphics2D g2d){

    }
}
