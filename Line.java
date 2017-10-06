import java.io.*;
import java.util.*;

class Line{
	
	ArrayList<Character> c = new ArrayList<Character>();

	public void addCharacter(char a){
		this.c.add(a);
		
		this.printLine();
	}

	public void especialChar(int i){

		switch(i){
				case Global.INSERT:
				break;
				case Global.SUPRIMIR:
				break;
				case Global.UP:
				break;
				case Global.DOWN:
				break;
				case Global.RIGHT:
				System.out.print(String.format("\033[1C"));
				break;
				case Global.LEFT:
				System.out.print(String.format("\033[1D"));
				break;
				case Global.START:
				System.out.print(String.format("\033[%dD", this.c.size()));
				break;
				case Global.END:
				break;
				default:
				break;
			}
	}

	public void clearScreen(){
		
		System.out.print(String.format("\033[%dD", this.c.size()));
		System.out.print(String.format("\033[K"));
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