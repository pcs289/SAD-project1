import java.io.*;
import java.util.*;

class EditableBufferedReaderController extends BufferedReader{
	
	LineModel line;
	ConsoleView view;
	Reader in;

	public EditableBufferedReaderController(Reader in){
		super(in);
		this.in = in;
		view = new ConsoleView();
		line = new LineModel();
		line.addObserver(view);
	}

	public int read() throws IOException{
		int r = super.read();
		while(r == 27 || r == 91 || r == 79){

			r = super.read();
			
			switch(r){
				case 50:
					if(super.read()==126){
						r = Global.INSERT;
					}
					break;
				case 51:
					if(super.read()==126){
						r = Global.SUPRIMIR;
					}
					break;
				case 65:
					
					r = Global.UP;
					break;
				case 66:

					r = Global.DOWN;
					break;
				case 67:
					
					r = Global.RIGHT;
					break;
				case 68:
					
					r = Global.LEFT;
					break;
				case 70:
					
					r = Global.END;
					break;
				case 72:
					
					r = Global.START;
					break;
			}
		}
		return r;
	}

	public String readLine() throws IOException{
		this.view.setRaw();
		int r = this.read();
		while(r != 13){
			if (r >= Global.INSERT || r == 127){
				this.line.especialChar(r);
			}else{
				this.line.addCharacter((char) r);
			}
			r = this.read();
		}

		String s = this.line.getLineString();
		this.view.unsetRaw();
		return s;
	}

}