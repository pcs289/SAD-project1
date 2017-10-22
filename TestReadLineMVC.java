import java.io.*;
import java.util.*;

class TestReadLineMVC {
    public static void main(String[] args) {
        EditableBufferedReaderController in = new EditableBufferedReaderController(new InputStreamReader(System.in));
        String str = null;

        try {
            
            str = in.readLine();
            
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nString is: " + str);

        
    }
}