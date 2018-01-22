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
     * The variance in shape
     */
    private float shapeDiversity;

    /**
     * The minimum pixel-spacing between shapes
     */
    private int spacing;

    /**
     * The base color of the shapes
     */
    private int[] color = new int[3];

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
    }
}
