package top.tyzhang.Controller;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.tyzhang.Entity.Ima;
import top.tyzhang.Serivces.ImgService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @RequestMapping("/getAll")
    public void find(HttpServletResponse response) throws Exception {
        System.out.println("控制器OK");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        ArrayList<Ima> imaArrayList = imgService.getAll();
        response.getWriter().write("[");
        for (int i = 0; i < imaArrayList.size() - 1; i++) {
            response.getWriter().append("{\"url\":\"http://192.168.0.7/Cyhs/" + imaArrayList.get(i).getUrl() + ".jpg\"," + "\"author\":\"" + imaArrayList.get(i).getName() + "\"},");
        }
        response.getWriter().append("{\"url\":\"http://192.168.0.7/Cyhs/" + imaArrayList.get(imaArrayList.size() - 1).getUrl() + ".jpg\"," + "\"author\":\"" + imaArrayList.get(imaArrayList.size() - 1).getName() + "\"}");
        response.getWriter().append("]");
        return;
    }

    @RequestMapping("/insert")
    public void insert(HttpServletResponse response, @RequestParam String name, String url) throws Exception {
        System.out.println("控制器OK");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        if (imgService.insert(new Ima(name, url)) > 0) {
            response.getWriter().print("true");
        } else {
            response.getWriter().print("false");
        }
        return;
    }

    @RequestMapping("/fileupload")
    public String fileupload1(HttpServletRequest request) throws Exception {
        System.out.println("文件上传");
        // 使用fileupload完成文件上传
        // 上传位置
        String path = request.getSession().getServletContext().getRealPath("/");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        // 解析request对象，获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest((RequestContext) request);
        for (FileItem item : items) {
            // 进行判断，当前item是否是上传文件项
            if (item.isFormField()) {
                // 普通表单项
            } else {
                // 上传文件项
                // 获取到上传文件名称
                String filename = item.getName();
                // 文件名设唯一值
                String uuid = UUID.randomUUID().toString().replace("-", "");
                filename = uuid + "_" + filename;
                // 完成文件上传
                item.write(new File(path, filename));
                // 删除临时文件
                item.delete();
            }
        }
        return "success";
    }
}