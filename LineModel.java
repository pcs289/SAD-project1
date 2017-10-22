import java.io.*;
import java.util.*;

class LineModel extends Observable{

	ConsoleView view;
	ArrayList<Character> c = new ArrayList<Character>();
	int cursorPosition = 0;
	boolean isInsert = false;


	public int indexOfCursor(){
		return this.c.size() - this.cursorPosition;
	}

	public void setData(){
		setChanged();
		notifyObservers();
	}

	public void addCharacter(char a){
		
		if(isInsert && (this.cursorPosition < this.c.size())){
			
			this.c.set(this.cursorPosition, a);
			
		} else {

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
		}
		
		this.updateCursorPosition(Global.RIGHT);
		this.setData();
	}

	public void updateCursorPosition(int cas){

		if(cas == Global.LEFT){
			if(this.cursorPosition > 0){
				this.cursorPosition -= 1;
			}
		}else if(cas == Global.RIGHT){
			if(this.cursorPosition < this.c.size()){
				this.cursorPosition += 1;
			}
		}else if(cas == Global.START){
			this.cursorPosition = 0;
		}else if(cas == Global.END){
			this.cursorPosition = this.c.size();
		}

	}

	public void removeCharacter(boolean isDelete){

		ArrayList<Character> aux = new ArrayList<Character>();

		if(isDelete){
		
			for(int i = 0; i < this.cursorPosition - 1; i++){
				aux.add(this.c.get(i));
			}
			for(int i = this.cursorPosition; i < this.c.size(); i++){
				aux.add(this.c.get(i));
			}
			this.updateCursorPosition(Global.LEFT);
			
		}else{//isSuprimir

				for(int i = 0; i < this.cursorPosition; i++){
					aux.add(this.c.get(i));
				}
				for(int j = this.cursorPosition+1; j < this.c.size(); j++){
					aux.add(this.c.get(j));
				}

		}	
		
		this.c = aux;
		this.setData();
	}

	public void especialChar(int i){

		switch(i){
				case Global.INSERT:
				this.isInsert = !this.isInsert;
				break;
				case Global.SUPRIMIR:
				this.removeCharacter(false);
				break;
				case Global.DELETE:
				this.removeCharacter(true);
				break;
				case Global.UP:
				break;
				case Global.DOWN:
				break;
				case Global.RIGHT:
				this.updateCursorPosition(Global.RIGHT);
				this.setData();
				break;
				case Global.LEFT:
				this.updateCursorPosition(Global.LEFT);
				this.setData();
				break;
				case Global.START:
				this.updateCursorPosition(Global.START);
				this.setData();
				break;
				case Global.END:
				this.updateCursorPosition(Global.END);
				this.setData();
				break;
				default:
				break;
			}
	}
	
	String getStringRepresentation(ArrayList<Character> list){    
	    StringBuilder builder = new StringBuilder(list.size());
	    for(Character ch: list)
	    {
	        builder.append(ch);
	    }
	    return builder.toString();
	}

	String getLineString(){
		return this.getStringRepresentation(this.c);
	}
}