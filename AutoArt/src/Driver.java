import javax.sound.midi.SysexMessage;
import java.io.*;

/**
 * The driver of the program
 */
public class Driver {

	/**
	 * Array of answers read from file
	 */
	private static char[] answers = new char[8];

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
		boolean lineShape = false;

		for(int ansIter = 0; ansIter < Driver.answers.length; ansIter++){
			char ans = Driver.answers[ansIter];

			// Each answer has a different effect on the values set
			switch(ansIter){
				case 0:
					/**
					 * if a: radius = 25 and shape = square
					 * if b: radius = 50 and shape = circle
					 * if c: radius = 7
					 */
					if(ans == 'a'){
						Painter.attributes.put("tx_ShapeRadius","25");
						Painter.attributes.put("tx_Shape","square");
					}
					else if(ans == 'b'){
						Painter.attributes.put("tx_ShapeRadius","50");
						Painter.attributes.put("tx_Shape","circle");

					}
					else if(ans == 'c'){
						Painter.attributes.put("tx_ShapeRadius","75");
						lineShape = true;
					}
					break;
				case 1:
					/**
					 * if a: bg_Value = 0
					 * if b: bg_Value = 50
					 * if c: bg_Value = 87
					 * if d: bg_Value = 167
					 * if e: bg_Value = 209
					 * if f: bg_Value = 255
					 */
					if(ans == 'a'){
						Painter.attributes.put("bg_Value","0");
					}
					else if(ans == 'b'){
						Painter.attributes.put("bg_Value","50");

					}
					else if(ans == 'c'){
						Painter.attributes.put("bg_Value","87");
					}
					else if(ans == 'd'){
						Painter.attributes.put("bg_Value","167");
					}
					else if(ans == 'e'){
						Painter.attributes.put("bg_Value","209");
					}
					else if(ans == 'f'){
						Painter.attributes.put("bg_Value","255");
					}
					break;
				case 2:
					/**
					 * if a: shape = angular
					 * if b shape = curve
					 */
					if(lineShape){
						if(ans == 'a'){
							Painter.attributes.put("tx_Shape","angular");
						}
						else if(ans == 'b'){
							Painter.attributes.put("tx_Shape","curve");
						}
					}
					break;
				case 3:
					/**
					 * if a: tx_ColorDiversity = 0.25
					 * if b: tx_ColorDiversity = 0.5
					 * if c: tx_ColorDiversity = 0.75
					 * if d: tx_ColorDiversity = 1.0
					 */
					if(ans == 'a'){
						Painter.attributes.put("tx_ColorDiversity","0.25");
					}
					else if(ans == 'b'){
						Painter.attributes.put("tx_ColorDiversity","0.5");
					}
					else if(ans == 'c'){
						Painter.attributes.put("tx_ColorDiversity","0.75");
					}
					else if(ans == 'd'){
						Painter.attributes.put("tx_ColorDiversity","1.0");
					}
					break;
				case 4:
					/**
					 * if a: bg_FirstColor = 255,0,0 (red)
					 * if b: bg_FirstColor = 255,215,0 (yellow)
					 */
					if(ans == 'a'){
						Painter.attributes.put("bg_FirstColor","255,0,0");
					}
					else if(ans == 'b'){
						Painter.attributes.put("bg_FirstColor","255,215,0");
					}
					break;
				case 5:
					/**
					 * if a: bg_SecondColor = 0,0,255 (blue)
					 * if b: bg_SecondColor = 128,0,128 (purple)
					 */
					if(ans == 'a'){
						Painter.attributes.put("bg_SecondColor","0,0,255");
					}
					else if(ans == 'b'){
						Painter.attributes.put("bg_SecondColor","128,0,128");
					}
					break;
				case 6:
					/**
					 * if a: tx_FirstColor = 0,0,0 (black)
					 * if b: tx_FirstColor = 255,255,255 (white)
					 */
					if(ans == 'a'){
						Painter.attributes.put("tx_FirstColor","0,0,0");
					}
					else if(ans == 'b'){
						Painter.attributes.put("tx_FirstColor","255,255,255");
					}
					break;
				case 7:
					/**
					 * if a: tx_SecondColor = 255,165,0 (orange)
					 * if b: tx_SecondColor = 0,128,0 (green)
					 */
					if(ans == 'a'){
						Painter.attributes.put("tx_SecondColor","255,165,0");
					}
					else if(ans == 'b'){
						Painter.attributes.put("tx_SecondColor","0,128,0");
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
