package top.tyzhang.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.tyzhang.Serivces.VerService;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description TODO
 * @Author Zhang Tianyu
 * @Date 2020-05-30 13:39
 * @Version 1.0
 **/
@Controller
@RequestMapping("/ver")
public class VerController {
    @Autowired
    private VerService verService;

    @RequestMapping("/latest")
    public void find(HttpServletResponse response) throws Exception {
        System.out.println("控制器OK");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(verService.find().getVer());
        return;
    }
}