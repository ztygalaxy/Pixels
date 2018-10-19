package Dao;
import java.util.ArrayList;

import Entity.Ima;

public interface ImgDao {
	public abstract Ima find(String name) throws Exception;
	public abstract int insert(String name,String author) throws Exception;
	public abstract ArrayList<Ima> getAll() throws Exception;
}