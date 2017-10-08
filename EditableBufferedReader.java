import java.io.*;

class EditableBufferedReader extends BufferedReader{
	
	Reader in;

	Line line;

	

	public EditableBufferedReader(Reader in) {
		super(in);
		this.line = new Line();
		this.in = in;
	}

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
	
	public int read() throws IOException{
		int r = super.read();
		while(r == 27 || r == 91 || r == 79){

			r = super.read();
			
			switch(r){
				case 50:

					r = Global.INSERT;
					break;
				case 51:

					r = Global.SUPRIMIR;
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
		this.setRaw();
		int r = this.read();
		while(r != 13){
			if (r >= Global.INSERT || r == 127){
				this.line.especialChar(r);
			}else{
				this.line.addCharacter((char) r);
			}
			r = this.read();
		}

		String s = String.valueOf(this.line.getCursorPosition());
		this.unsetRaw();
		return s;
	}
	
	
}