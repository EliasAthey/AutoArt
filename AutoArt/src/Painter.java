/**
 * AutoArt is licensed under the GPL 3.0. Written by Elias Athey and Tia Smith.
 */

import processing.core.PApplet;

/**
 * The Painter class acts as the Driver to paint the image.
 */
public class Painter extends PApplet{

    /**
     * The Painter's tools
     */
    private Background background;
    private Structure structure;
    private Texture texture;

    /**
     * Private constructor that creates the painter and tools
     */
    private Painter(){
        this.background = new Background();
        this.structure = new Structure();
        this.texture = new Texture();
    }

    /**
     * The start of everything.
     * @param args unused
     */
    public static void main(String args[]){
        Painter painter = new Painter();
        PApplet.runSketch(
                new String[]{"Painter"},
                painter);
    }

    /**
     * The settings for the output image
     */
    @Override
    public void settings(){
        size(500,500);
    }

    /**
     * Initial drawing, called once
     */
    @Override
    public void setup(){
        this.background.paintBackground(this);
        Flow flow = this.structure.createStructure();
        this.texture.paintTexture(this, flow);
        save("img.jpg");
    }
}
