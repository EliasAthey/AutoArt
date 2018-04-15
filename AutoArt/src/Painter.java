/**
 * AutoArt is licensed under the GPL 3.0. Written by Elias Athey and Tia Smith.
 */

import processing.core.PApplet;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * The Painter class acts as the Driver to paint the image.
 */
public class Painter extends PApplet{

    /**
     * The Painter's tools
     */
    private Background background;
    private Structure structure;
    private Texture texture;

    /**
     * The size of the Painter's canvas
     */
    private final int canvasWidth;
    private final int canvasHeight;

	/**
	 * A mapping from attribute names to their values, here set to their default values
	 */
	public static HashMap attributes = new HashMap<String, String>(){
	    {
            put("bg_Value","128"); // Lightness of background [0,255]
            put("bg_Intensity","0.0"); // [0,1] Intensity of the background -> amount of first color present
            put("bg_FirstColor","0,0,0"); // Colors are of the form <int,int,int> representing R,G,B values
            put("bg_SecondColor","255,255,255");

            put("st_NumEdges","7"); // the number of edges that shapes/lines are painted on
            put("st_Spacing","200"); // the distance between shapes/lines -> negative space

	        put("tx_ShapeRadius", "50");// Size of texture shape (or thickness of line) -> positive space
	        put("tx_Shape","curve"); // Shape (or line type) used for the texture
	        put("tx_ColorDiversity","0.0");// [0,1] - 0 being no diversity, 1 being lots of diversity (maybe every pixel is a different color)
            put("tx_Intensity","0.5"); // [0,1]
            put("tx_FirstColor","255,255,255");
            put("tx_SecondColor","0,0,0");
	    }
	};

    /**
     * Private constructor that creates the painter and tools
     */
    public Painter(int width, int height){
        this.canvasWidth = width;
        this.canvasHeight = height;
        this.background = new Background();
        this.structure = new Structure();
        this.texture = new Texture();
        this.setParams();
    }

    /**
     * The start of everything.
     */
    public void paint(){
        PApplet.runSketch(new String[]{"Painter"}, this);
    }

    /**
     * Sets attributes of the Painter's tools
     */
    private void setParams(){
        Painter.attributes.forEach((Object k, Object v) -> {
            String key = k.toString();
            String value = v.toString();
            String[] param = key.split("_");
            Object tool = null;
            switch(param[0]){
                case "bg":
                    tool = this.background;
                    break;
                case "st":
                    tool = this.structure;
                    break;
                case "tx":
                    tool = this.texture;
                    break;
            }
            try {
                tool.getClass().getMethod("set" + param[1], String.class).invoke(tool, value.trim());
            }
            catch(NoSuchMethodException e){
                System.err.println("Invoking set" + param[1] + " on " + tool.getClass().toString() + " failed.\nNo such method.\n");
                System.exit(3);
            }
            catch(IllegalAccessException e){
                System.err.println("Invoking set" + param[1] + " on " + tool.getClass().toString() + " failed.\nIllegal access.\n");
                System.exit(4);
            }
            catch(InvocationTargetException e){
                System.err.println("Invoking set" + param[1] + " on " + tool.getClass().toString() + " failed.\nInvocation target exception.\n");
                System.exit(5);
            }
        });

    }

    /**
     * Gets the width of the Painter's canvas
     * @return the pixel-width
     */
    public int getCanvasWidth() {
        return this.canvasWidth;
    }

    /**
     * Gets the height of the Painter's canvas
     * @return the pixel-height
     */
    public int getCanvasHeight() {
        return this.canvasHeight;
    }

    /**
     * The settings for the output image
     */
    @Override
    public void settings(){
        size(this.canvasWidth,this.canvasHeight);
    }

    /**
     * Initial drawing, called once
     */
    @Override
    public void setup(){
        this.background.paintBackground(this);
        Flow flow = this.structure.createStructure(this);
        this.texture.paintTexture(this, flow);
        save("aa_image.png");
        System.exit(0);
    }
}
