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
    private int shapeRadius = 50;

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

    	// if the shape is a line form, draw a line from each vertex to the next. Either straight (angular) or curved.
    	if(this.shape.equals("angular")){
    		// set line specific parameters
		    painter.strokeWeight(this.shapeRadius);
		    painter.stroke(this.firstColor[0], this.firstColor[1], this.firstColor[2]);
		    painter.noFill();

		    int[] prevPoint = flow.getPoints().get(0);
		    for(int pointIter = 1; pointIter < flow.getPoints().size(); pointIter++){
			    int[] nextPoint = flow.getPoints().get(pointIter);
			    painter.line(prevPoint[0], prevPoint[1], nextPoint[0], nextPoint[1]);
			    prevPoint = nextPoint;
		    }
	    }
	    else if(this.shape.equals("curve")){
    		// set curve specific parameters
		    painter.strokeWeight(this.shapeRadius);
		    painter.stroke(this.firstColor[0], this.firstColor[1], this.firstColor[2]);
		    painter.noFill();

		    int[] prevPoint = flow.getPoints().get(0);
		    int[] prevControl = prevPoint;
		    for(int pointIter = 1; pointIter < flow.getPoints().size(); pointIter++){

		    	// set the ending point and ending control point (the next point after end point)
			    int[] nextPoint = flow.getPoints().get(pointIter);
			    int[] nextControl;
			    if(pointIter < flow.getPoints().size() - 1){
			    	nextControl = flow.getPoints().get(pointIter + 1);
			    }
			    else{
			    	nextControl = flow.getPoints().get(pointIter);
			    }
			    painter.curve(prevControl[0], prevControl[1], prevPoint[0], prevPoint[1], nextPoint[0], nextPoint[1], nextControl[0], nextControl[1]);
			    prevControl = prevPoint;
			    prevPoint = nextPoint;
		    }
	    }
	    // else if its just a normal shape, draw a shape at each flow vertex
	    else{
    		// shape specific parameters
    		painter.fill(this.firstColor[0], this.firstColor[1], this.firstColor[2]);
    		painter.noStroke();

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
				int startX = xCoord - (int)(0.5 * this.shapeRadius);
				int startY = yCoord - (int)(0.5 * this.shapeRadius);
				painter.rect(startX, startY, this.shapeRadius, this.shapeRadius);
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
