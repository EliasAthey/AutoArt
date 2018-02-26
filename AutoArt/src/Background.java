/**
 * AutoArt is licensed under the GPL 3.0. Written by Elias Athey and Tia Smith.
 */

import static processing.core.PConstants.PI;

/**
 * The Background class paints the background of the image.
 */
public class Background {

    /**
     * The colors of the background, defaults to black and white respectively
     */
    private int[] firstColor = new int[3];
	private int[] secondColor = new int[3];

	/**
	 * The value (brightness) of the background, defaults to 50%
	 */
	private int value;

    /**
     * How strong/steep the gradient is, defaults to 50%
     */
    private float intensity;

    /**
     * Paints the background
     */
    public void paintBackground(Painter painter){
	    // rotate the canvas before drawing the gradient, use random radian [-PI/2,PI/2]
	    float rotation = (float)(Math.random() * PI) - PI/2;
	    int intensityDifference = (int)((this.intensity - 0.5f) * painter.getCanvasHeight());
	    painter.rotate(rotation);
        this.setGradient(
        		painter,
		        (int)(1.5f * -painter.getCanvasWidth()),
		        (int)(1.5f * -painter.getCanvasHeight() + intensityDifference),
		        4*painter.getCanvasWidth(),
		        4*painter.getCanvasHeight());

	    // return the canvas to its original orientation
	    painter.rotate(-rotation);
    }

	/**
	 * Paints a gradient for the background
	 * @param painter the painter
	 * @param x the starting x coord
	 * @param y the starting y coord
	 * @param w the width
	 * @param h the height
	 */
	private void setGradient(Painter painter, int x, int y, float w, float h) {
		painter.noFill();

		for (float iY = y, iX = x; iY <= y+h && iX <= x+w; iY=iY+0.1f, iX=iX+0.1f) {
			float interY = Painter.map(iY, y, y+h, 0, 1);
			painter.stroke(
					painter.lerpColor(
							painter.color(this.firstColor[0],this.firstColor[1],this.firstColor[2]),
							painter.color(this.secondColor[0],this.secondColor[1],this.secondColor[2]),
							interY));
			painter.line(x, iY, x+w, iY);
		}
	}

	/**
	 * Sets the primary color
	 * @param rgb comma separated RGB values
	 */
	public void setFirstColor(String rgb){
		String[] values = rgb.split(",");
		this.firstColor[0] = Integer.parseInt(values[0]);
		this.firstColor[1] = Integer.parseInt(values[1]);
		this.firstColor[2] = Integer.parseInt(values[2]);
    }

	/**
	 * Sets the secondary color
	 * @param rgb comma separated RGB values
	 */
	public void setSecondColor(String rgb){
		String[] values = rgb.split(",");
		this.secondColor[0] = Integer.parseInt(values[0]);
		this.secondColor[1] = Integer.parseInt(values[1]);
		this.secondColor[2] = Integer.parseInt(values[2]);
    }

	/**
	 * Sets the value
	 * @param value the value (ligthness)
	 */
	public void setValue(String value){
		this.value = Integer.parseInt(value);
    }

	/**
	 * Sets the intensity
	 * @param intensity the intensity (gradient intensity)
	 */
	public void setIntensity(String intensity){
		this.intensity = Float.parseFloat(intensity);
    }
}
