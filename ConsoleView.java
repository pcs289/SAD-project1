import java.io.*;
import java.util.*;
import java.lang.*;	

class ConsoleView implements Observer{

	LineModel line;
	int lastCursorPosition = 0;
		
	public void setRaw(){
		try{
			
			String[] args = new String[] {"/bin/bash", "-c", "stty -echo raw </dev/tty"};
			Process proc = new ProcessBuilder(args).start();

		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void unsetRaw(){
		try{

			String[] args = new String[] {"/bin/bash", "-c", "stty echo cooked </dev/tty"};
			Process proc = new ProcessBuilder(args).start();
		
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable li, Object arg){
		this.line = (LineModel) li;
		this.printLine();
		this.updateCursorPosition();

	}

	public void printLine(){
		String s = this.getStringRepresentation(this.line.c);
		System.out.print(String.format("\033[s"));
		this.clearScreen();
		System.out.print(s);
		System.out.print(String.format("\033[u"));
	}

	public void clearScreen(){		
		System.out.print(String.format("\033[%dD", this.line.c.size()+1));
		System.out.print(String.format("\033[K"));
	}

	String getStringRepresentation(ArrayList<Character> list){    
	    StringBuilder builder = new StringBuilder(list.size());
	    for(Character ch: list)
	    {
	        builder.append(ch);
	    }
	    return builder.toString();
	}

	public void updateCursorPosition(){

		int resta, indexOfCursor;

		resta = this.line.cursorPosition - this.lastCursorPosition;
		indexOfCursor = this.line.c.size() - this.lastCursorPosition;

		if(resta < 0 && Math.abs(resta) == 1){
			System.out.print(String.format("\033[1D"));
			
		}else if(resta > 0 && Math.abs(resta) == 1){
			System.out.print(String.format("\033[1C"));
			
		}else if(resta < 0){
			System.out.print(String.format("\033[%dD", this.lastCursorPosition));
			
		}else if(resta > 0){
			System.out.print(String.format("\033[%dC", indexOfCursor));
		}

		this.lastCursorPosition = this.line.cursorPosition;


	}
}