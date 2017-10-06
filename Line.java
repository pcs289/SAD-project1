import java.io.*;
import java.util.*;

class Line{
	
	ArrayList<Character> c = new ArrayList<Character>();
	int cursorPosition = 0;

	public void addCharacter(char a){
		ArrayList<Character> aux = new ArrayList<Character>();
		if(this.cursorPosition > 0){
			for(int i = 0; i < this.cursorPosition; i++){
				aux.add(this.c.get(i));
			}
		}
		aux.add(a);
		for(int i = this.cursorPosition; i < this.c.size(); i++){
			aux.add(this.c.get(i));
		}

		this.c = aux;
		this.updateCursorPosition(Global.RIGHT);
		this.printLine();
		//System.out.print(String.format("\033[1C"));
	}

	public void updateCursorPosition(int cas){
		if(cas == Global.LEFT){
			if(this.cursorPosition > 0){
				this.cursorPosition -= 1;
				System.out.print(String.format("\033[1D"));
			}
		}else if(cas == Global.RIGHT){
			if(this.cursorPosition < this.c.size()){
				this.cursorPosition += 1;
				System.out.print(String.format("\033[1C"));
			}
		}
	}

	public void removeCharacter(boolean isDelete){
		ArrayList<Character> aux = new ArrayList<Character>();
		if(isDelete){

		}else{//isSuprimir
			if(this.cursorPosition < this.c.size()){

				for(int i = 0; i < this.cursorPosition - 1; i++){
					aux.add(this.c.get(i));
				}
				for(int j = this.cursorPosition; j < this.c.size(); j++){
					aux.add(this.c.get(j));
				}
			}
		}
		
		this.c = aux;

		this.printLine();
	}

	public void especialChar(int i){

		switch(i){
				case Global.INSERT:
				break;
				case Global.SUPRIMIR:
				this.removeCharacter(false);
				break;
				case Global.UP:
				break;
				case Global.DOWN:
				break;
				case Global.RIGHT:
				
				this.updateCursorPosition(Global.RIGHT);
				
				
				break;
				case Global.LEFT:
				
				this.updateCursorPosition(Global.LEFT);
				
				break;
				case Global.START:
				System.out.print(String.format("\033[%dD", this.cursorPosition));
				this.cursorPosition = 0;
				break;
				case Global.END:
				break;
				default:
				break;
			}
	}

	//Indica l'index del cursor respecte el array de caracters
	public int indexOfCursor(){
		return this.c.size() -1 - this.cursorPosition;
	}

	public void clearScreen(){
		
		System.out.print(String.format("\033[%dD", this.c.size()));
		System.out.print(String.format("\033[K"));
	}

	public void printLine(){
		String s = this.getStringRepresentation(this.c);
		this.clearScreen();
		System.out.print(s);
		System.out.print(String.format("\033[%dD", this.indexOfCursor()));
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