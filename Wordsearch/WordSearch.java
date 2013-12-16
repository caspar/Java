import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class WordSearch {
  
    
    Random R = new Random();
    
    private ArrayList<String> wordList;
    public ArrayList<String> usedWords = new ArrayList<String>();
    public int length = 0; //length of usedWords
    private char[][] board;
    private int rows;
    private int columns;
    
    private void readWords(String filename){
	wordList = new ArrayList<String>();
	try{
	    Scanner sc = new Scanner(new File(filename));
	    while(sc.hasNext()){
		String s = sc.nextLine();
		wordList.add(s);
	    }
	}catch (Exception e) {
	    System.out.println(e);
	    System.exit(0);
	}
    }
    
    public WordSearch(int r, int c) {
	//CONSTRUCT THE BOARD//
	// *Border with |'s and -'s	
	rows = r;
	columns = c;    
	board = new char[r][c];
	for (int i = 1; i < r-1; i++){
	    board[i][0]='|';
	    board[i][c-1]='|';
	}
	for (int i = 0; i < c; i++){
	    board[0][i] = '-';
	    board[r-1][i] = '-';
	}
       	for (int i=1;i<r-1;i++) 
	    for (int j=1;j<c-1;j++) 
		board[i][j]=' ';
	readWords("Instruments.txt");
    }
    
    public WordSearch() {
	this(30,120);
    }
    
    public String ReadList(){
	return "";
    }
    
    public boolean RandWords(int reps){
	// will fill board with with "reps" words.
	// next: dictate length (/complexity!) of words.
	
    // NEEDED: A way to ensure that no words are used twice (Should probably be integrated into AddWord()
    //         so we know which/how many words are on the board. *
    
	int stats = 0;
 	for (int i = 0; i < reps; i++){    
	    int dx = R.nextInt(3)-1;
	    int dy = R.nextInt(3)-1;
	    int c = R.nextInt(columns);
	    int r = R.nextInt(rows);
	    String spaces = "";
        // *Maybe add to a list  which has all the indexes of the used words?
        // OR: Just go through the words in order!
	    String word = (wordList.get(i)); //(instruments[R.nextInt(wordList.length())]);
	    if ((AddWord (r, c, dx, dy, word))){
		for (int s = 0; s < (15 - word.length()); s++)
		    spaces = spaces + " ";
		usedWords.add(word + spaces);
		length++;
		stats++;
	    }
	}
	//System.out.println(stats + " ----> " + (stats*10000)/(reps*100)+"%");
	return true;
    }
    

   
    
    public boolean AddWord (int r, int c, int dx, int dy, String word){
	//  AddWord (int r, int c, int dx, int dy, String word)
	// dx: 1 for right, -1 for left, 0 for stationary
	// dy: 1 for down, -1 for up, 0 for stationary
       	
	if (dx == 0 && dy == 0){
	    //System.out.println("dx and dy cannot both equal zero");
	    return false; //this would mean that the word was not 
	                  //sprouting in any direction. 
	                  //Only the first letter would render.
	}
	if (dx > 1 || dx < -1 || dy > 1 || dy < -1){
	    System.out.println("dx and dy must both be within range.");
	    return false;
	}
	int i = 0;
	
	//dx: "X-Direction" --  (-1) for Left, (1) for Right, (0) for neither;
	//dy: "Y-Direction" -- ditto;
		
	try{
	    while (i < word.length()){
		if (board[r + (dy*i)][c + (dx*i)] != ' ' 
		    && board[r + (dy*i)][c + (dx*i)] != word.charAt(i))
		    return false;
		i++;
	    }
	}
	catch (Exception e){
	    return false;
	}
	
	i = 0;
	while (i < word.length()){
	    board[r + (dy*i)][c + (dx*i)] = word.charAt(i);
	    i++;
	}
	return true;
    }
    
    public void FillIn(){
	for (int x = 0; x < rows; x++)
	    for (int y = 0; y < columns; y++)
		if (board[x][y] == ' ')
		    board[x][y] = (char)(R.nextInt(26) + 'A');
    } 
    
    public String toString() {
	String s = "";
	System.out.println("");
	for (int i=0;i<board.length;i++) {
	    for (int j=0;j<board[i].length;j++) {
		s=s+board[i][j];
	    }
	    s=s+"\n";
	}
	return s;
    }    
}
