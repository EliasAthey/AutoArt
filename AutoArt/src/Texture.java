/**
 * AutoArt is licensed under the GPL 3.0. Written by Elias Athey and Tia Smith.
 */

/**
 * The Texture class paints shapes on the path (flow) created by the Structure class.
 */
public class Texture {

    /**
     * The base shape painted, defaults to a straight line (Angular)
     */
    private String shape = "angular";

    /**
     * The pixel-radius of the shapes, defaults to 50
     */
    private int shapeRadius = 100;

    /**
     * The colors of the texture shapes, defaults to white and black respectively
     */
    private int[] firstColor = {255,255,255};
    private int[] secondColor = {0,0,0};

    /**
     * The variance in color of the shapes, defaults to 0
     */
    private float colorDiversity = 0.0f;

    /**
     * TODO Unsure what to use this for yet
     */
    private float intensity;

    /**
     * Paints the texture of the image using the flow
     */
    public void paintTexture(Painter painter, Flow flow){
    	// Fill in texture shapes with the first color
    	painter.fill(this.firstColor[0], this.firstColor[1], this.firstColor[2]);

    	// if the shape is a line form
    	if(this.shape.equals("angular") || this.shape.equals("curve")){

	    }
	    // else if its just a normal shape, and draw a shape at each flow vertex
	    else{
		    for (int[] point : flow.getPoints()) {
			    this.drawShapeAt(painter, point[0], point[1]);
		    }
	    }
    }

    /**
     * Draws a texture shape at the given coordinates
     * @param painter the painter
     * @param xCoord the x coordinate
     * @param yCoord the y coordinate
     */
    private void drawShapeAt(Painter painter, int xCoord, int yCoord){
		switch(this.shape){
			case "circle":
				painter.ellipse(xCoord, yCoord, this.shapeRadius, this.shapeRadius);
				break;

			case "square":
				break;
		}
    }

	/**
	 * Draws a line form between the given coordinates
	 * @param painter the painter
	 * @param startX the starting x coordinate
	 * @param startY the starting y coordinate
	 * @param endX the ending x coordinate
	 * @param endY the ending y coordinate
	 */
	private void drawLineFrom(Painter painter, int startX, int startY, int endX, int endY){
		switch(this.shape){
			case "angular":
				break;

			case "curve":
				break;
		}
    }

    /**
     * Sets the base shape of the texture
     * @param shape the shape
     */
    public void setShape(String shape){
        this.shape = shape;
    }

    /**
     * Sets the radius of the shapes
     * @param radius the radius as a pixel-width
     */
    public void setShapeRadius(String radius){
        this.shapeRadius = Integer.parseInt(radius);
    }

    /**
     * Sets the primary color of the texture shapes
     * @param rgb comma separated RGB values
     */
    public void setFirstColor(String rgb){
        String[] values = rgb.split(",");
        this.firstColor[0] = Integer.parseInt(values[0]);
        this.firstColor[1] = Integer.parseInt(values[1]);
        this.firstColor[2] = Integer.parseInt(values[2]);
    }

    /**
     * Sets the secondary color of the texture shapes
     * @param rgb comma separated RGB values
     */
    public void setSecondColor(String rgb){
        String[] values = rgb.split(",");
        this.secondColor[0] = Integer.parseInt(values[0]);
        this.secondColor[1] = Integer.parseInt(values[1]);
        this.secondColor[2] = Integer.parseInt(values[2]);
    }

    /**
     * Sets the color diversity of the texture shapes
     * @param diveristy the diversity - float
     */
    public void setColorDiversity(String diveristy){
        this.colorDiversity = Float.parseFloat(diveristy);
    }

    /**
     * Sets the intensity
     * @param intensity the intensity
     */
    public void setIntensity(String intensity){
        this.intensity = Float.parseFloat(intensity);
    }
}
