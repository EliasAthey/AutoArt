/**
 * AutoArt is licensed under the GPL 3.0. Written by Elias Athey and Tia Smith.
 */

import java.util.ArrayList;

/**
 * The Structure class sets the path (flow) that the Texture will follow.
 */
public class Structure {

	/**
	 * Number of edges used for the structure's Flow, defaults to 1
	 */
	private int numEdges = 7;

	/**
	 * The pixel-length of each edge, defaults to 100
	 */
	private int spacing = 200;

    /**
     * Creates the structure of the image by creating a Flow
     * @return the flow of the image; how the texture will be painted
     */
    public Flow createStructure(Painter painter){
		// The ordered list of points to construct the Flow
    	ArrayList<int[]> endPoints = new ArrayList<>();

		// Add random starting point
		int width = painter.getCanvasWidth();
    	int height = painter.getCanvasHeight();
		int startX = (int)(Math.random() * width);
		int startY = (int)(Math.random() * height);
		endPoints.add(new int[]{startX, startY});

		// Determine a random point for each edge and store in ordered list
		// Each point is this.spacing distance away from the previous point
		int edgeIter = 0;
		while(edgeIter < this.numEdges){
			int[] prevPoint = endPoints.get(edgeIter);
			edgeIter++;
			int xCoord = -1, yCoord = -1;
			boolean validXCoord = false;
			while(!validXCoord){
				xCoord = (int)(Math.random() * width);
				int difference = (int)Math.sqrt(Math.pow(this.spacing, 2) - Math.pow(xCoord - prevPoint[0], 2));

				// Check if the possible coordinate is on the same horizontal plane
				if(difference == 0 && Math.abs(xCoord - prevPoint[0]) <= this.spacing){
					validXCoord = true;
					yCoord = prevPoint[1];
				}
				else if(difference == 0){
					validXCoord = false;
				}
				else{
					// Randomly choose which possible y coordinate is checked first
					int possibleYOne = -1, possibleYTwo = -1;
					if(Math.random() < 0.5){
						possibleYOne = prevPoint[1] + difference;
						possibleYTwo = prevPoint[1] - difference;
					}
					else{
						possibleYOne = prevPoint[1] - difference;
						possibleYTwo = prevPoint[1] + difference;
					}

					// Check the validity of the y coordinates
					if(possibleYOne >= 0 && possibleYOne < height){
						validXCoord = true;
						yCoord = possibleYOne;
					}
					else if(possibleYTwo >= 0  && possibleYTwo < height){
						validXCoord = true;
						yCoord = possibleYTwo;
					}
				}
			}

			// Add the valid coordinates to the list
            endPoints.add(new int[]{xCoord, yCoord});
		}

        /**
         * Return the points as a Flow object
         */
		return new Flow(endPoints);
    }

	/**
	 * Sets the number of edges used in the Flow
	 * @param numEdges the number of edges
	 */
	public void setNumEdges(String numEdges){
		this.numEdges = Integer.parseInt(numEdges);
    }

	/**
	 * Sets the size of spacing between texture objects
	 * @param spacing the spacing as pixel-width
	 */
	public void setSpacing(String spacing){
		this.spacing = Integer.parseInt(spacing);
    }
}
