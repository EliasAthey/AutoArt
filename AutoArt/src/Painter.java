/**
 * AutoArt is licensed under the GPL 3.0. Written by Elias Athey and Tia Smith.
 */

import processing.core.PApplet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    private final int canvasWidth = 1080;
    private final int canvasHeight = 720;

    /**
     * Path to parameter file
     */
    private final String paramFilePath = "params.aa";

    /**
     * The size of the character buffer used to read the parameter file
     */
    private final int numCharsInParamFile = 100;

	/**
	 * A mapping from line numbers in the parameter file to associated attribute names
	 */
	private final HashMap<Integer, String> fileFormat = new HashMap<Integer, String>(){
	    {
            // 3rd place color
	        put(0, "bg_FirstColor");// int,int,int [0,255]

            // 4th place color
            put(1, "bg_SecondColor");// int,int,int [0,255]

            // value light
            put(2, "bg_Value");// float [0,1]

            // space depth (?)
            put(3, "bg_Intensity");// float [0,1]

            // structure line iterations (I think we should randomize the start pt and vertex pts)
            put(4, "st_NumEdges");// int

            // negative space
            put(5, "st_Spacing");// int (pixels)

            // form
            put(6, "tx_Shape");// string

            // positive space
            put(7, "tx_ShapeRadius");// int (pixels)

            // 1st place color
            put(8, "tx_FirstColor");// int,int,int [0,255]

            // 2nd place color
            put(9, "tx_SecondColor");// int,int,int [0,255]

            // roughness (unsure what's meant by invented and visual)
            put(10, "tx_ColorDiversity");// float [0,1]
            put(11, "tx_Intensity");// float [0,1]
	    }
	};

    /**
     * Private constructor that creates the painter and tools
     */
    private Painter(){
        this.background = new Background();
        this.structure = new Structure();
        this.texture = new Texture();
    }

    /**
     * The start of everything.
     * @param args unused
     */
    public static void main(String args[]){
        Painter painter = new Painter();
        painter.setParams();
        PApplet.runSketch(
                new String[]{"Painter"},
                painter);
    }

    /**
     * Reads the parameters file and sets attributes
     */
    private void setParams(){
        File paramFile = null;
        try{
            paramFile = new File(paramFilePath);
            FileReader paramReader = new FileReader(paramFile);
            char[] fileContents = new char[this.numCharsInParamFile];
            if(paramReader.ready()){
                paramReader.read(fileContents);
            }
            String[] lines = String.copyValueOf(fileContents).split("\n");
            for(int lineIter = 0; lineIter < lines.length; lineIter++){
                this.setParam(lines[lineIter], this.fileFormat.get(lineIter));
            }
        }
        catch(FileNotFoundException e){
            System.err.println("Param file not found at " + paramFile.getAbsolutePath() + ".\n");
            System.exit(1);
        }
        catch(IOException e){
            System.err.println("Cannot read param file " + paramFilePath + "\n");
            System.exit(2);
        }
    }

    /**
     * Sets a single attribute of the Painter's tools
     * @param valueText the value of the parameter
     * @param paramText the associated parameter name - see fileFormat
     */
    private void setParam(String valueText, String paramText){
        String[] param = paramText.split("_");
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
            tool.getClass().getMethod("set" + param[1], String.class).invoke(tool, valueText);
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
        save("img.jpg");
    }
}
