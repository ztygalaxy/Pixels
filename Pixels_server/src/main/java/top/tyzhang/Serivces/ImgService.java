package top.tyzhang.Serivces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tyzhang.Dao.ImgDao;
import top.tyzhang.Entity.Ima;

import java.util.ArrayList;

/**
 * @Description TODO
 * @Author Zhang Tianyu
 * @Date 2020-05-30 15:37
 * @Version 1.0
 **/
@Service("imgService")
public class ImgService implements IImgService {
    @Autowired
    private ImgDao imgDao;


    @Override
    public Ima find(Ima ima) throws Exception {
        return imgDao.find(ima);
    }

    @Override
    public int insert(Ima ima) throws Exception {
        try {
            imgDao.insert(ima);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public ArrayList<Ima> getAll() throws Exception {
        return imgDao.getAll();
    }
}