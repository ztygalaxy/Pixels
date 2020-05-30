package top.tyzhang.Serivces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tyzhang.Entity.Ver;

/**
 * @Description TODO
 * @Author Zhang Tianyu
 * @Date 2020-05-30 15:30
 * @Version 1.0
 **/
@Service("verService")
public class VerService implements IVerService {
    @Autowired
    private VerService verService;

    @Override
    public Ver find() {
        return verService.find();
    }
}