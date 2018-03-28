package model;

import java.io.IOException;

public class ModelDAO {
	
	private static ModelDAO instance = new ModelDAO();
	
	public static ModelDAO getInstance() {
		return instance;
	}

	private ModelDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public void save(Model m) throws IOException {
		
	}
	
	public Model load(String name) throws IOException {
		return null;
	}
	
	public void delete(String name) throws IOException {
		
	}

}
