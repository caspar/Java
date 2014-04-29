import dme.forecastiolib.*;
import java.util.*;

public class Weather {
    public static void main(String[] args) throws Exception {

	////////////////////////////////////////
	// INSTANTIZING CLASSES AND VARIABLES //
	////////////////////////////////////////

	ForecastIO fio = new ForecastIO("561be979a0c6974551b2582588c959a9"); //instantiate the class with the API key. 	

	Calendar c = Calendar.getInstance();
	final String[] days = {"Sunday...", "Monday...", "Tuesday..", "Wednesday", "Thursday.", "Friday...", "Saturday."}; //Days of the week for the readout
        final int today = (c.get(c.DAY_OF_WEEK))-1;
	boolean summary = false;
	String lat = "40.701736";
	String lon = "-73.983938";

	
	//////////////////
	// ARG HANDLING //
	//////////////////

	try{ if (args[0].toLowerCase().contains("s")||args[1].toLowerCase().contains("s")) summary = true;} //I will use this block to handle all my args!
	catch(Exception oops){/* Write message in here */}

	try{ if ( isNum(args[0]) && isNum(args[1]) ){ //first two args are coordinates
		lat = args[0]; //sets the latitude 
		lon = args[1]; //sets the longitude 
	    }
	}
	catch(Exception oops){/* Write message in here */}
	
	try{ if ( isNum(args[1]) && isNum(args[2]) ){ //last two args are coordinates
		lat = args[1]; //sets the latitude 
		lon = args[2]; //sets the longitude 
	    }
	}
	catch(Exception oops){/* Write message in here */}

	fio.getForecast(lat, lon); 		
	fio.setExcludeURL("hourly,minutely");
	FIOCurrently currently = new FIOCurrently(fio);
	FIODaily daily = new FIODaily(fio);
      

	System.out.println("Timezone: "+fio.getTimezone()); //for verification of coordinate location service. Later: Entering city as parameter

	String result = "";
	
	result += "Current: " + currently.get().temperature() + "º"; 
        System.out.println(result);

	result = "";

	for(int i = 1; i < 8; i ++){
	    result += "\n" + days[(today-1+i)%7] + "..";
	    result += formatTemps(daily, i);
	    if (summary) result += "\n" + daily.getDay(i).summary();
	}

	  System.out.println(result + "\n");
	
	try{
	    if (args.length != 0 && args[0].equals("AllCurrently")){
		//Print currently data
		System.out.println("\nCurrently\n");
		String [] f  = currently.get().getFieldsArray();
		for(int i = 0; i<f.length;i++)
		    System.out.println(f[i]+": "+currently.get().getByKey(f[i]));
	    }
	    
	    if (args.length !=0 && args[0].equals("AllDaily")){
		//In case there is no daily data available
		if(daily.days()<0)
		    System.out.println("No daily data.");
		else
		    System.out.println("\nDaily:\n");
		//Print daily data
		for(int i = 0; i<daily.days(); i++){
		    String [] h = daily.getDay(i).getFieldsArray();
		    System.out.println("Day #"+(i+1));
		    for(int j=0; j<h.length; j++)
			System.out.println(h[j]+": "+daily.getDay(i).getByKey(h[j]));
		    System.out.println("\n");
		}
	    }
 	}catch(Exception e){}
    }

	
    private static String formatTemps(FIODaily fid, int i){
	String out = "(";
	out+=fid.getDay(i).temperatureMax(); //+"º" ?
	if (out.length()==5)out+=" ";
	out+=" | ";
	out+=fid.getDay(i).temperatureMin(); //+"º" ?
	if (out.length()==13)out+=" ";
	return out + ")";
    }
    
    private static boolean isNum(String input){
	for (int i = 0; i < input.length(); i++){
	    if (Character.isLetter(input.charAt(i)))
		return false;
	}
	return true;
    }

}