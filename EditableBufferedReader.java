import java.io.*;

class EditableBufferedReader extends BufferedReader{
	
	Reader in;

	Line line;

	public static final int INSERT = 500, SUPRIMIR = 501, START = 502, END = 503, UP = 504, DOWN = 505, LEFT = 506, RIGHT = 507;

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

					System.out.println("Insert");
					r = INSERT;
					break;
				case 51:

					System.out.println("Suprimir");
					r = SUPRIMIR;
					break;
				case 65:
					
					System.out.println("Arriba");
					r = UP;
					break;
				case 66:
					r = DOWN;
					System.out.println("Abajo");
					
					break;
				case 67:
					r = RIGHT;
					System.out.println("Derecha");
					
					break;
				case 68:
					r = LEFT;
					System.out.println("Izquierda");
					
					break;
				case 70:
					r = END;
					System.out.println("Fin");

					break;
				case 72:
					r = START;
					System.out.println("Inicio");

					break;
			}
		}
		return r;
	}

	public String readLine() throws IOException{
		int r = this.read();
		while(r != 13){
			switch(r){
				case INSERT:
				break;
				case SUPRIMIR:
				break;
				case UP:
				break;
				case DOWN:
				break;
				case RIGHT:
				break;
				case LEFT:
				break;
				case START:
				break;
				case END:
				break;
				default:
					this.line.addCharacter((char) r);
				break;
			}
			r = this.read();
		}
		String s = String.valueOf(r);
		return s;
	}
	
	
}