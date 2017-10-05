import java.io.*;
import java.util.*;

class Line{
	
	ArrayList<Character> c = new ArrayList<Character>();

	public void addCharacter(char a){
		this.c.add(a);
		this.printLine();
	}

	public void clearScreen(){
		
		System.out.print(String.format("\033[2J"));
		System.out.print(String.format("\033[0;0f"));
	}

	public void printLine(){
		String s = this.getStringRepresentation(this.c);
		this.clearScreen();
		System.out.print(s);
	}

	String getStringRepresentation(ArrayList<Character> list){    
	    StringBuilder builder = new StringBuilder(list.size());
	    for(Character ch: list)
	    {
	        builder.append(ch);
	    }
	    return builder.toString();
	}
}