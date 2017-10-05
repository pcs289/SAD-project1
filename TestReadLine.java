import java.io.*;

class TestReadLine {
    public static void main(String[] args) {
        EditableBufferedReader in = new EditableBufferedReader(new InputStreamReader(System.in));
        String str = null;
        try {
        	in.setRaw();
            str = in.readLine();
            
        } catch(IOException e) {
            e.printStackTrace();
        }
        in.unsetRaw();
        System.out.println("\nline is: " + str);
    }
}