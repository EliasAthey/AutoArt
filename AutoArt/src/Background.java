/**
 * AutoArt is licensed under the GPL 3.0. Written by Elias Athey and Tia Smith.
 */

/**
 * The Background class paints the background of the image.
 */
public class Background {

    /**
     * Background attributes for color and gradient
     */
    private int[] color = new int[3];
    private float colorDiversity;
    private float intensity;
    private int[] gradient = new int[2];

    /**
     * Paints the background
     */
    public void paintBackground(Painter painter){
        painter.background(100, 200, 100);
    }
}
