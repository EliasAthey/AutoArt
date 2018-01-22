/**
 * AutoArt is licensed under the GPL 3.0. Written by Elias Athey and Tia Smith.
 */

/**
 * The Background class paints the background of the image.
 */
public class Background {

    /**
     * The base color of the background
     */
    private int[] color = new int[3];

    /**
     * The amount that the color varies
     */
    private float colorDiversity;

    /**
     * How strong/steep the gradient is
     */
    private float intensity;

    /**
     * The direction of the gradient
     */
    private int[] gradient = new int[2];

    /**
     * Paints the background
     */
    public void paintBackground(Painter painter){
        painter.background(100, 200, 100);
    }
}
