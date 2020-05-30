package top.tyzhang.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.tyzhang.Entity.User;
import top.tyzhang.Serivces.UserService;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description TODO
 * @Author Zhang Tianyu
 * @Date 2020-05-30 13:38
 * @Version 1.0
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/find")
    public void find(HttpServletResponse response, @RequestParam String name, String pwd) throws Exception {
        System.out.println("控制器OK");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        if (userService.find(new User(name, pwd)) > 0) {
            response.getWriter().print("true");
        } else {
            response.getWriter().print("false");
        }
        return;
    }

    @RequestMapping("/insert")
    public void insert(HttpServletResponse response, @RequestParam String name, String pwd) throws Exception {
        System.out.println("控制器OK");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        if (userService.insert(name, pwd) > 0) {
            response.getWriter().print("true");
        } else {
            response.getWriter().print("false");
        }
        return;
    }
}