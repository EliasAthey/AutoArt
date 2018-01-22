/**
 * AutoArt is licensed under the GPL 3.0. Written by Elias Athey and Tia Smith.
 */

import java.util.ArrayList;

/**
 * A container for the Structure's flow
 */
public class Flow {

    /**
     * The number of edges in this flow
     */
    private int numEdges;

    /**
     * The starting pixel-position of this flow
     */
    private int[] startPosition = new int[2];

    /**
     * An ordered list of end pixel-positions for each edge in this flow
     */
    private ArrayList<int[]> endPositions = new ArrayList<>();

    /**
     * Constructs a random flow given a specific number of edges
     * @param numEdges
     */
    public Flow(int numEdges){
        this.numEdges = numEdges;
    }
}
