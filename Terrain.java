package modele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class Terrain {

	public static void map(File file) {
		BufferedReader br = null;
		
		try {
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			String line;
			
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}
		
		catch (FileNotFoundException e) {
			System.out.println("Le fichier que vous recherchez n'a pas pu être trouvé : " + file.toString());
		}
		
		catch (IOException e) {
			System.out.println("Impossible de lire le fichier : " + file.toString());
		}
		
		finally {
			try {
				br.close();
			}
			
			catch (IOException e) {
				System.out.println("Impossible de fermer le fichier : " + file.toString());
			}
			
			catch (NullPointerException ex) {
				
			}
		}
	}
}
