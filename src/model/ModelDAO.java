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
	}
	
	/**
	 * 
	 * @param m Model that we want to save
	 * @param fn name of the save file
	 * @throws IOException
	 */
	public void save(Model m, String fn) throws IOException {
		//we parse the objects of a state
		
		try{
			FileOutputStream fos = new FileOutputStream(fn+".souss");//create new file
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(m);//we write in oos
			
			oos.close();//we close oos
			
		}catch(IOException err){
			System.err.println("Impossible to save the game");
		}
		
	}
	
	/**
	 * 
	 * @param name name of the save file
	 * @return return the model that we loaded
	 * @throws IOException
	 */
	public Model load(String name) throws IOException {
		Model m = null;
		try{
			FileInputStream fis = new FileInputStream(name);//read a save file
			ObjectInputStream ois = new ObjectInputStream(fis);//flux object
			m = (Model)ois.readObject();
			ois.close();
		}catch(IOException | ClassNotFoundException e){
			System.err.println("Impossible to load the file");
		}
		
		return m;
	}
	/**
	 * 
	 * @param name name of the file
	 * @throws Exception impossible to access the file
	 */
	public void delete(String name) throws Exception {
		
		File rip = new File(name+".souss");
		
		if (!rip.exists()) {
            throw new Exception("Impossible to find the file");
        }
		//properties and access on the file
        if (!rip.canWrite()) {
            throw new Exception("You are not allowed to access this file");
        }
 
        rip.delete();
		
	}

}
