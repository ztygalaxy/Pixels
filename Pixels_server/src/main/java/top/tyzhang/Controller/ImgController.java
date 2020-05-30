package top.tyzhang.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.tyzhang.Serivces.ImgService;

/**
 * @Description TODO
 * @Author Zhang Tianyu
 * @Date 2020-05-30 13:39
 * @Version 1.0
 **/
@Controller
@RequestMapping("/img")
public class ImgController {
    @Autowired
    private ImgService imgService;
    
}