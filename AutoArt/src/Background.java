/**
 * AutoArt is licensed under the GPL 3.0. Written by Elias Athey and Tia Smith.
 */

/**
 * The Background class paints the background of the image.
 */
public class Background {

    /**
     * The colors of the background, defaults to black and white respectively
     */
    private int[] firstColor = {0,0,0};
	private int[] secondColor = {255,255,255};

	/**
	 * The value (brightness) of the background, defaults to 50%
	 */
	private float value = 0.5f;

    /**
     * How strong/steep the gradient is, defaults to 50%
     */
    private float intensity = 0.5f;

    /**
     * Paints the background
     */
    public void paintBackground(Painter painter){
        painter.background(this.firstColor[0], this.firstColor[1], this.firstColor[2]);
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
		this.value = Float.parseFloat(value);
    }

	/**
	 * Sets the intensity
	 * @param intensity the intensity (gradient intensity)
	 */
	public void setIntensity(String intensity){
		this.intensity = Float.parseFloat(intensity);
    }
}
