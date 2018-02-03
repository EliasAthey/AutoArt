/**
 * AutoArt is licensed under the GPL 3.0. Written by Elias Athey and Tia Smith.
 */

import java.util.ArrayList;

/**
 * A container for the Structure's flow. The flow is just a path
 */
public class Flow {

    /**
     * An ordered list of pixel-positions for this flow
     */
    private ArrayList<int[]> points = new ArrayList<>();

    /**
     * Constructs a Flow given a set of points
     * @param points the ordered list of x,y coordinates
     */
    public Flow(ArrayList<int[]> points){
        this.points = points;
    }

    /**
     * Gets the ordered list of edge-ending coordinates
     * @return the end positions
     */
    public ArrayList<int[]> getPoints() {
        return this.points;
    }
}
