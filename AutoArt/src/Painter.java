/**
 * AutoArt is licensed under the GPL 3.0. Written by Elias Athey and Tia Smith.
 */

import processing.core.PApplet;

import java.awt.*;
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
     * Path to parameter file
     */
    private final String paramFilePath = "./params.aa";

	/**
	 * A mapping from line numbers in the parameter file to associated attribute names
	 */
	private final HashMap fileFormat = new HashMap(){
	    {
            // 3rd place color
	        put(0, "bg_FirstColor");// int,int,int [0,255]

            // 4th place color
            put(2, "bg_SecondColor");// int,int,int [0,255]

            // value light
            put(3, "bg_Value");// float [0,1]

            // space depth (?)
            put(4, "bg_Intensity");// float [0,1]

            // structure line iterations (I think we should randomize the start pt and vertex pts)
            put(5, "st_NumEdges");// int

            // negative space
            put(6, "st_Spacing");// int (pixels)

            // form
            put(7, "tx_Shape");// string

            // positive space
            put(8, "tx_ShapeRadius");// int (pixels)

            // 1st place color
            put(9, "tx_FirstColor");// int,int,int [0,255]

            // 2nd place color
            put(10, "tx_SecondColor");// int,int,int [0,255]

            // roughness (unsure what's meant by invented and visual)
            put(11, "tx_ColorDiveristy");// float [0,1]
            put(12, "tx_Intensity");// float [0,1]
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
        PApplet.runSketch(
                new String[]{"Painter"},
                painter);

        // exit successfully
        System.exit(0);
    }

    /**
     * Sets the attributes of the Painter's tools
     * @return true if the operation was successful, false otherwise
     */
    private void setParams(){
        try{
            File paramFile = new File(paramFilePath);
            FileReader paramReader = new FileReader(paramFile);
            while(paramReader.ready()){
                /**
                 * TODO
                 *
                 * Iterate through lines, passing each line value and associated attribute name to setParam method
                 * close reader
                 */
            }
        }
        catch(FileNotFoundException e){
            System.err.println("Param file not found.\n");
            System.exit(1);
        }
        catch(IOException e){
            System.err.println("Cannot read param file " + paramFilePath + "\n");
            System.exit(2);
        }
    }

    /**
     * Sets an attribute of the Painter's tools
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
            tool.getClass().getMethod("get" + param[1]).invoke(valueText);
        }
        catch(NoSuchMethodException e){
            System.err.println("Invoking get" + param[1] + " on " + tool.getClass().toString() + " failed.\nNo such method.\n");
            System.exit(3);
        }
        catch(IllegalAccessException e){
            System.err.println("Invoking get" + param[1] + " on " + tool.getClass().toString() + " failed.\nIllegal access.\n");
            System.exit(4);
        }
        catch(InvocationTargetException e){
            System.err.println("Invoking get" + param[1] + " on " + tool.getClass().toString() + " failed.\nInvocation target exception.\n");
            System.exit(5);
        }
    }

    /**
     * The settings for the output image
     */
    @Override
    public void settings(){
        size(500,500);
    }

    /**
     * Initial drawing, called once
     */
    @Override
    public void setup(){
        this.background.paintBackground(this);
        Flow flow = this.structure.createStructure();
        this.texture.paintTexture(this, flow);
        save("img.jpg");
	    Shape
    }
}
