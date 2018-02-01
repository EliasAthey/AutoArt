/**
 * AutoArt is licensed under the GPL 3.0. Written by Elias Athey and Tia Smith.
 */

/**
 * The Background class paints the background of the image.
 */
public class Background {

    /**
     * The colors of the background
     */
    private int[] firstColor = new int[3];
	private int[] secondColor = new int[3];

	/**
	 * The value (brightness) of the background
	 */
	private float value;

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

	/**
	 * Sets the color given a string of RGB values
	 * @param rgb comma separated RGB values
	 */
	public void setFirstColor(String rgb){

    }
}
