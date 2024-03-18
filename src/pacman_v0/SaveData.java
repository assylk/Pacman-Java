package pacman_v0;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SaveData {
    static List<String> myList = new ArrayList<>();	

    private static final String filepath="/Users/assylchouikh/Dev/Java/pacman_v0/scores/scores.txt";
	public static String getMostScored() throws IOException 
    {	
		Path filePath = Paths.get(filepath);
        Charset charset = StandardCharsets.UTF_8;
        try {
            List<String> lines = Files.readAllLines(filePath, charset);
            for(String line: lines) {
            	myList.add(line);
            }
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
        String m=Collections.max(myList);
		return m;
		
        
    }
	static void SetScore(String score) {
		Path filePath = Paths.get(filepath);
        Charset charset = StandardCharsets.UTF_8;
        try {
            List<String> lines = Files.readAllLines(filePath, charset);
            for(String line: lines) {
            	myList.add(line);
            }
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
        Collections.sort(myList, Collections.reverseOrder());  

	        try {
	            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filepath, true)));
	            out.print("\n"+score);
	            

	            out.close();
	        } catch (IOException e) {
	            //exception handling left as an exercise for the reader
	        }
	}


}
