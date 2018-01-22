/**
 * AutoArt is licensed under the GPL 3.0. Written by Elias Athey and Tia Smith.
 */

import processing.core.PApplet;

/**
 * The Painter class acts as the Driver to paint the image.
 */
public class Painter extends PApplet{

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
        size(1000,1000);
    }

    /**
     * Initial drawing, called once
     */
    @Override
    public void setup(){
        background(255);
    }

    /**
     * Repeated drawing, called every frame
     */
    @Override
    public void draw(){
        background((int)(Math.random() * 256)); // this will cause seizures!
    }
}
