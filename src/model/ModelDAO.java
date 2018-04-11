package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ModelDAO {
	
	private static ModelDAO instance = new ModelDAO();
	
	public static ModelDAO getInstance() {
		return instance;
	}

	private ModelDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public void save(Model m, String fn) throws IOException {
		
		
		
		//on parse les objets représentatifs d'un état
		
		try{
			FileOutputStream fos = new FileOutputStream(fn+".souss");//créer un nouveau ficher
			ObjectOutputStream oos = new ObjectOutputStream(fos); //on précise que le flux correspond à un objet
			
			oos.writeObject(m);//on écrit m dans le flux oos
			oos.close();//on ferme le flux
			
		}catch(IOException err){
			;
		}
		
	}
	
	public Model load(String name) throws IOException {
		Model m = null;
		try{
			FileInputStream fis = new FileInputStream(name+".souss");//lire un fichier de sauvegarde
			ObjectInputStream ois = new ObjectInputStream(fis);//flux object
			m = (Model)ois.readObject();//
			ois.close();
		}catch(IOException | ClassNotFoundException e){
			;
		}
		
		return m;
	}
	
	public void delete(String name) throws Exception {
		
		File rip = new File(name+".souss");
		
		if (!rip.exists()) {
            throw new Exception("Fichier Introuvable");
        }
//Tester les propriétés et les droits sur le fichier
        if (!rip.canWrite()) {
            throw new Exception("Droit insuffisant pour accéder au fichier");
        }
 
        rip.delete();
		
	}

}
