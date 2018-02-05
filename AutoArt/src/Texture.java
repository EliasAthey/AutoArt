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
     * The primary color of the shapes
     */
    private int[] firstColor = new int[3];

    /**
     * The secondary color of the shapes
     */
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
        // Do something for each point in the flow
        for (int[] point : flow.getPoints()) {
            this.drawShapeAt(point[0], point[1]);
        }
    }

    /**
     * Draws a texture shape at the given coordinates
     * @param xCoord the x coordinate
     * @param yCoord the y coordinate
     */
    private void drawShapeAt(int xCoord, int yCoord){

    }

    /**
     * Sets the base shape of the texture
     * @param shape the shape
     */
    public void setShape(String shape){
        this.shape = shape.trim();
    }

    /**
     * Sets the radius of the shapes
     * @param radius the radius as a pixel-width
     */
    public void setShapeRadius(String radius){
        this.shapeRadius = Integer.parseInt(radius.trim());
    }

    /**
     * Sets the primary color of the texture shapes
     * @param rgb comma separated RGB values
     */
    public void setFirstColor(String rgb){
        String[] values = rgb.split(",");
        this.firstColor[0] = Integer.parseInt(values[0].trim());
        this.firstColor[1] = Integer.parseInt(values[1].trim());
        this.firstColor[2] = Integer.parseInt(values[2].trim());
    }

    /**
     * Sets the secondary color of the texture shapes
     * @param rgb comma separated RGB values
     */
    public void setSecondColor(String rgb){
        String[] values = rgb.split(",");
        this.secondColor[0] = Integer.parseInt(values[0].trim());
        this.secondColor[1] = Integer.parseInt(values[1].trim());
        this.secondColor[2] = Integer.parseInt(values[2].trim());
    }

    /**
     * Sets the color diversity of the texture shapes
     * @param diveristy the diversity - float
     */
    public void setColorDiversity(String diveristy){
        this.colorDiversity = Float.parseFloat(diveristy.trim());
    }

    /**
     * Sets the intensity
     * @param intensity the intensity
     */
    public void setIntensity(String intensity){
        this.intensity = Float.parseFloat(intensity.trim());
    }
}
