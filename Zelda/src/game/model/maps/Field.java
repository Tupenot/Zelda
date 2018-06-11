package game.model.maps;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Field {

    private int[][] map;
    private String filemap;


    public Field(String fileName) {
        filemap = fileName;
    }

    public void initMap() {
        File file = new File(filemap);
        String line = "";
        BufferedReader br;
        try {
        	    br = new BufferedReader(new FileReader(file));
          try {
        	    int w=Integer.parseInt(br.readLine());
				int h=Integer.parseInt(br.readLine());
				map=new int[h][w];
				 int i = 0;
		            StringTokenizer st = new StringTokenizer(line, ", ");
		            do {
		                int j = 0;
		                try {
		                    line = br.readLine();
		                } catch (IOException e) {
		                    e.printStackTrace();
		                }
		                if (line != null) {
		                    st = new StringTokenizer(line, ", ");
		                    while (st.hasMoreTokens()) {
		                        map[i][j] = Integer.parseInt(st.nextToken(", "));
		                        j++;
		                    }
		                    i++;
		                    
		                }
		            } while (line != null);
			   
          }catch(IOException e) {}
           

            try {
                br.close();
            } catch (IOException e) {
                System.out.println("Error : Could not open file");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error : Could not found file");
        }

    }

    public int[][] getMap() {
        return map;
    }

   
}
