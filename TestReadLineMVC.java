import java.io.*;

class TestReadLineMVC {
    public static void main(String[] args) {
        EditableBufferedReader in = new EditableBufferedReader(new InputStreamReader(System.in));
        String str = null;
        try {
        	
            str = in.readLine();
            
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nString is: " + str);

        
    }
}