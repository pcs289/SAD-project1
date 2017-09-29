import java.io.*;

class EditableBufferedReader extends BufferedReader{
	
	Reader in;
	Runtime rt;

	public EditableBufferedReader(Reader in) {
		super(in);

		this.in = in;
		rt = Runtime.getRuntime();
	}

	public void setRaw(){
		try{

			rt.exec("stty -echo raw");

		}catch(IOException e){

		}
	}

	public void unsetRaw(){
		try{

			rt.exec("stty echo cooked");
		
		}catch(IOException e){
			
		}
	}
	
	public int read() throws IOException{
		int r = super.read();
		while(r == 27 || r == 91){

			r = super.read();
			switch(r){
				case 65:
					
					System.out.println("Arriba");
					
					break;
				case 66:
					
					System.out.println("Abajo");
					
					break;
				case 67:
					
					System.out.println("Derecha");
					
					break;
				case 68:
					
					System.out.println("Izquierda");
					
					break;
			}
		}
		return r;
	}

	public String readLine() throws IOException{
		int r = this.read();
		String s = String.valueOf(r);
		return s;
	}
	
	
}