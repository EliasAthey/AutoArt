import java.io.*;

/**
 * The driver of the program
 */
public class Driver {

	/**
	 * Array of answers read from file
	 */
	private static char[] answers = new char[12];

	public static void main(String[] args){
		Driver.setAnswers(args[0]);
		Driver.convertAnswers();
		Painter painter = new Painter();
		painter.paint();
	}

	/**
	 * Sets the answers character array given path
	 * @param path the path of the answers file
	 */
	private static void setAnswers(String path){
		BufferedReader fileReader = null;
		try{
			fileReader = new BufferedReader(new FileReader(new File(path)));
			fileReader.read(answers);
		}
		catch(FileNotFoundException fnf){
			System.err.println(fnf.getMessage());
			System.exit(1);
		}
		catch(IOException io){
			System.err.println(io.getMessage());
			System.exit(2);
		}
	}

	/**
	 * Convert values in answers array to values in the Painter hashmap
	 */
	private static void convertAnswers(){
		for(int ansIter = 0; ansIter < Driver.answers.length; ansIter++){
			char ans = Driver.answers[ansIter];

			switch(ansIter){
				/**
				 * BG Value
				 */
				case 0:
					if(ans == 'a'){
						Painter.attributes.put("bg_Value","1.0");
					}
					else if(ans == 'b'){
						Painter.attributes.put("bg_Value","0.5");

					}
					else if(ans == 'c'){
						Painter.attributes.put("bg_Value","0");
					}
					break;
				/**
				 * BG Intensity
				 */
				case 1:
					if(ans == 'a'){
						Painter.attributes.put("bg_Intensity","0");
					}
					else if(ans == 'b'){
						Painter.attributes.put("bg_Intensity","0.5");

					}
					else if(ans == 'c'){
						Painter.attributes.put("bg_Intensity","1.0");
					}
					break;
				/**
				 * BG Primary Color
				 */
				case 2:
					if(ans == 'a'){
						Painter.attributes.put("bg_FirstColor","180,0,0");// red
					}
					else if(ans == 'b'){
						Painter.attributes.put("bg_FirstColor","0,0,180");// blue
					}
					break;
				/**
				 * BG Secondary Color
				 */
				case 3:
					if(ans == 'a'){
						Painter.attributes.put("bg_SecondColor","210,180,0");// yellow
					}
					else if(ans == 'b'){
						Painter.attributes.put("bg_SecondColor","0,180,0");// green
					}
					break;
				/**
				 * TX Primary Color
				 */
				case 4:
					if(ans == 'a'){
						Painter.attributes.put("tx_FirstColor","0,0,0");// black
					}
					else if(ans == 'b'){
						Painter.attributes.put("tx_FirstColor","255,255,255");// white
					}
					break;
				/**
				 * TX Secondary Color
				 */
				case 5:
					if(ans == 'a'){
						Painter.attributes.put("tx_SecondColor","110,0,150");// purple
					}
					else if(ans == 'b'){
						Painter.attributes.put("tx_SecondColor","255,100,0");// orange
					}
					break;
				/**
				 * TX Intensity
				 */
				case 6:
					if(ans == 'a'){
						Painter.attributes.put("tx_Intensity","0");
					}
					else if(ans == 'b'){
						Painter.attributes.put("tx_Intensity","0.5");
					}
					else if(ans =='c'){
						Painter.attributes.put("tx_Intensity","1.0");
					}
					break;
				/**
				 * ST Num Edges
				 */
				case 7:
					if(ans == 'a'){
						Painter.attributes.put("st_NumEdges","2");
					}
					else if(ans == 'b'){
						Painter.attributes.put("st_NumEdges","7");
					}
					else if(ans =='c'){
						Painter.attributes.put("st_NumEdges","15");
					}
					break;
				/**
				 * ST Spacing
				 */
				case 8:
					if(ans == 'a'){
						Painter.attributes.put("st_Spacing","100");// spacious
					}
					else if(ans == 'b'){
						Painter.attributes.put("st_Spacing","200");// medium
					}
					else if(ans =='c'){
						Painter.attributes.put("st_Spacing","350");// cramped
					}
					break;
				/**
				 * TX Shape Radius
				 */
				case 9:
					if(ans == 'a'){
						Painter.attributes.put("tx_ShapeRadius","100");// large
					}
					else if(ans == 'b'){
						Painter.attributes.put("tx_ShapeRadius","50");// medium
					}
					else if(ans =='c'){
						Painter.attributes.put("tx_ShapeRadius","25");// small
					}
					break;
				/**
				 * TX Shape
				 */
				case 10:
					if(ans == 'a'){
						Painter.attributes.put("tx_Shape","square");
					}
					else if(ans == 'b'){
						Painter.attributes.put("tx_Shape","circle");
					}
					break;
				/**
				 * TX Color Diversity
				 */
				case 11:
					if(ans == 'a'){
						Painter.attributes.put("tx_ColorDiversity","0.0");
					}
					else if(ans == 'b'){
						Painter.attributes.put("tx_ColorDiversity","0.5");
					}
					else if(ans =='c'){
						Painter.attributes.put("tx_ColorDiversity","1.0");
					}
					break;
				default:
					System.out.println("Too many answers in answers.txt");
					System.exit(1);
			}
		}
		System.out.println(Painter.attributes.toString());
	}
}
