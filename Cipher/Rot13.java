import java.io.*;
import java.util.*;

public class Rot13{
    
    //doesn't work, but I'm tired - Caspar

    public static void main(String[] args){
	
	try{
	    Rot13 R = new Rot13(args[0]);
	}catch(Exception e){
	    System.out.println("Something's not right");
	}
    }
    
    public Rot13(String input){
	input = Decapitalize(input);
	String output = "";

	for (int i = 0; i < input.length(); i++){
	    if (input.charAt(i) + 13 > 122){
		output += (char) ('a' + (13 - (122-input.charAt(i))));
	    }
	    else {
		if (input.charAt(i) > 122 || input.charAt(i) < 97)
		    output += (char) (input.charAt(i));
		else 
		    output += (char) (input.charAt(i) + 13);
	    }
	}
	System.out.println(output);
    }
     private String Decapitalize(String input){
	String output = "";
	for (int i = 0; i < input.length(); i++){
	    if (input.charAt(i) >= 65 && input.charAt(i) <= 90)
		output += (char) (input.charAt(i) + 32);
	    else
		output += input.charAt(i);
	}
	return output;
    }

}