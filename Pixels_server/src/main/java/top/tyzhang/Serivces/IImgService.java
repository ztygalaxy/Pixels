package top.tyzhang.Serivces;

import top.tyzhang.Entity.Ima;

import java.util.ArrayList;

public interface IImgService {
    public abstract Ima find(Ima ima) throws Exception;

    public abstract int insert(Ima ima) throws Exception;

    public abstract ArrayList<Ima> getAll() throws Exception;
}
