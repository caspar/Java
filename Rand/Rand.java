import java.io.*;
import java.util.*;

public class Rand{
    
    public static void main(String[] args){
	int length = Integer.valueOf(args[0]);
	int range = Integer.valueOf(args[1]);
        int trials = Integer.valueOf(args[2]);
	int trues = 0;
	
	for (int i = 0; i < trials; i++){
	    if(setup(length, range))
		trues++;

	}
	System.out.println(trues + "/" + trials);
	//long result = ((trues/trials) * 100);
	//System.out.println(result);
	System.out.println(((trues * 100000) / (trials * 1000)) + "%");
	
    }
    
    public static boolean setup(int length, int range){
	
	int[] Randoms = new int[length];
	Random R = new Random();
	
	for (int i = 0; i <Randoms.length; i ++){
	    Randoms[i] = R.nextInt(range);
	}
	//System.out.println(conds(Randoms));
	//System.out.println(Arrays.toString(Randoms));
	return (conds(Randoms));
    }
    
    public static boolean conds(int[] Randoms){
	
	for (int i = 0; i < Randoms.length - 3; i++){
	    if(Randoms[i] == Randoms[i+1] && Randoms[i] == Randoms[i + 2]){
		//System.out.println(Randoms[i]);
		return true;
	    }
	}
	return false;
    }
}

//25 horses
//5 horses
//25, 22, 19, 17, 15, 13, 11, 9, 7, 5, 3, 1

   
