/**
 * AutoArt is licensed under the GPL 3.0. Written by Elias Athey and Tia Smith.
 */

/**
 * The Texture class paints shapes on the path (flow) created by the Structure class.
 */
public class Texture {

    /**
     * The base shape painted
     */
    private String shape;

    /**
     * The pixel-radius of the shapes
     */
    private int shapeRadius;

    /**
     * The colors of the shapes
     */
    private int[] firstColor = new int[3];
	private int[] secondColor = new int[3];

    /**
     * The variance in color of the shapes
     */
    private float colorDiversity;

    /**
     * TODO Unsure what to use this for yet
     */
    private float intensity;

    /**
     * Paints the texture of the image using the flow
     */
    public void paintTexture(Painter painter, Flow flow){
        painter.line(0,0, painter.width, painter.height);
        painter.line(0, painter.height, painter.width, 0);
    }
}
