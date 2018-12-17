package project_OOP;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
	  private static int amountUser = 0;
	  private int userId;
	  private InputStream streamIn;
	  private PrintStream streamOut;
	  private String name;
	  private Socket client;
	  private String color;
	  private Socket socketImg;
	  private InputStream streamInImg;
	  private OutputStream streamOutImg;
	  

	  // constructor
	  public User(Socket client, String name) throws IOException {
	    this.streamOut = new PrintStream(client.getOutputStream());
	    this.streamIn = client.getInputStream();
	    this.client = client;
	    this.name = name;
	    this.userId = amountUser;
	    this.color = ColorInt.getColor(this.userId);
	    amountUser += 1;
	  }



	// change color user
	  public void changeColor(String hexColor){
	    // check if it's a valid hexColor
	    Pattern colorPattern = Pattern.compile("#([0-9a-f]{3}|[0-9a-f]{6}|[0-9a-f]{8})");
	    Matcher m = colorPattern.matcher(hexColor);
	    if (m.matches()){
	      Color c = Color.decode(hexColor);
	      // if the Color is too Bright don't change
	      double luma = 0.2126 * c.getRed() + 0.7152 * c.getGreen() + 0.0722 * c.getBlue(); // per ITU-R BT.709
	      if (luma > 160) {
	        this.getOutStream().println("<b>Color Too Bright</b>");
	        return;
	      }
	      this.color = hexColor;
	      this.getOutStream().println("<b>Color changed successfully</b> " + this.toString());
	      return;
	    }
	    this.getOutStream().println("<b>Failed to change color</b>");
	  }

	  
	  //Getter
	  public PrintStream getOutStream(){
	    return this.streamOut;
	  }

	  public InputStream getInputStream(){
	    return this.streamIn;
	  }

	  public String getName(){
	    return this.name;
	  }


	  // print user with his color
	  public String toString(){

	    return "<u><span style='color:"+ this.color
	      +"'>" + this.getName() + "</span></u>";

	  }
	}
class ColorInt {
    public static String[] mColors = {
            "#3079ab", // dark blue
            "#e15258", // red
            "#f9845b", // orange
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#f092b0", // pink
            "#e8d174", // yellow
            "#e39e54", // orange
            "#d64d4d", // red
            "#4d7358", // green
    };

    public static String getColor(int i) {
        return mColors[i % mColors.length];
    }
}
