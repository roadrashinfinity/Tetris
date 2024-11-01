package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class leaderboardDB {
	public static String filename = "D:\\IdeaJ\\TetrisJavaFX_OOP\\TetrisJavaFX_OOP-tetrisMain\\leaderboard.txt";
	public static void file() {
		try {
			File myObj = new File(filename);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
		}
	}
	
	public static void readData() {
		try {
            FileReader reader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            
            while ((line = bufferedReader.readLine()) != null) {
            	//UImanager.contText.setText(String.valueOf(line));
            	System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	public static void writeData(String name,int score) {
		try{
	    	File file =new File(filename);
	    	if(!file.exists()) file.createNewFile();
	    	
	    	FileWriter fw = new FileWriter(file,true);
	    	BufferedWriter bw = new BufferedWriter(fw);
	    	bw.write(name+'\t'+score+'\n');
	    	bw.close();
	      }catch(IOException ioe){
	         System.out.println("Exception occurred:");
	    	 ioe.printStackTrace();
	       }
	   }
}
