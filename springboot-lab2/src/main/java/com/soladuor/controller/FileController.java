package com.soladuor.controller;

import com.soladuor.pojo.FileMessage;
import com.soladuor.pojo.FileUser;
import com.soladuor.service.FileService;
import com.soladuor.utils.result.JSONResult;
import com.soladuor.utils.result.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    // 拿到 resources 目录路径（不能直接拿files路径，因为空文件夹最开始不会一起打包）
    static String resourcesPath = ClassLoader.getSystemResource("").getPath();

    // 获取文件列表
    @GetMapping
    public JSONResult getFileList() {
        List<FileMessage> allFileList = fileService.getAllFileList();
        System.out.println("allFileList = " + allFileList);
        return JSONResult.ok(allFileList);
    }

    @DeleteMapping
    public JSONResult deleteFile(String fileId) {
        int i = fileService.deleteFile(fileId);
        if (i > 0) {
            return JSONResult.ok("删除成功");
        }
        return JSONResult.fail("删除失败");
    }

    /*
        文件上传
            (1) 准备：
                1. 导入jar包
                2. 配置 CommonsMultipartResolver 解析器【注：id必须为 multipartResolver】
                    配置字符集和上传文件大小限制
            (2) 实现：
                1. 将type=file的文件域直接入参为 MultipartFile 类型即可解析
                2. 获取文件名
                3. 获取真实路径
                4. 实现上传
            (3) 表单注意事项
                1：form表单 enctype 属性必须为 multipart/form-data
                2：input表单项 type 属性必须为 file
                3：form表单请求方式必须为post
            (4) 文件重名问题：
                1. 使用UUID【32位16进制随机数，全球唯一，重复性问题首先想到的解决思路】
                    长这样：18fe98ff-df5a-4caf-b7f0-c3c0dd754a4a
                2. 使用时间戳

     */

    @RequestMapping("/upload")
    public JSONResult fileUpload(
            @RequestParam("file") MultipartFile file,
            HttpSession session
    ) {
        try {
            // 获取当前用户信息
            FileUser user = (FileUser) session.getAttribute("user");

            // 获取文件名（需要时还可以获取文件类型或者文件大小）
            String filename = file.getOriginalFilename();
            // 获取文件类型 （例如 text/plain 、 image/png 等）
            String contentType = file.getContentType();
            // 按/切割文件类型，用于创建文件类型文件夹
            String fileType;
            if (contentType != null) {
                fileType = contentType.split("/")[0];
            } else {
                fileType = "other";
            }
            // 获取上传真实路径
            // String upRealPath = session.getServletContext().getRealPath(
            //     "/files/" + fileType + File.separator
            // );
            String upRealPath = resourcesPath + "files/" + fileType + File.separator;
            // 上传路径不存在则创建路径
            File filePath = new File(upRealPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            System.out.println("上传路径：" + filePath);
            // 创建UUID
            String uuid = UUID.randomUUID().toString().replace("-", "");

            // 保存到数据库
            fileService.upload(uuid, file.getSize(), user.getId(), filename, fileType);

            // 上传文件【File.separator是系统文件分隔符（防止不同系统路径分隔符不同）】
            File savePath = new File(filePath + File.separator + uuid);
            file.transferTo(savePath);

        } catch (NullPointerException e) {
            e.printStackTrace();
            return JSONResult.build(ResultCodeEnum.UNAUTHORIZED, "无权限");
        } catch (IOException e) {
            e.printStackTrace();
            return JSONResult.fail(e);
            // throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return JSONResult.fail(e.getMessage());
        }
        return JSONResult.ok();
    }

     /*
        文件下载实现步骤：
            1. 使用 ResponseEntity<T> 作为方法返回值
            2. 给 ResponseEntity<byte[]> 设置三个参数（下载的文件、响应头、状态码）
     */

    /**
     * 测试文件下载
     */
    @RequestMapping("/download/{fileType}/{fileId}")
    public ResponseEntity<byte[]> fileDownload(@PathVariable String fileType,
                                               @PathVariable String fileId,
                                               HttpServletRequest request) {
        ResponseEntity<byte[]> responseEntity;

        try {
            FileMessage fileMessage = fileService.getFileMessageById(fileId);
            String fileName = fileMessage.getFileName();

            // 下载次数+1
            fileService.fileDownload(fileId);

            // 1. 设置文件
            // 获取下载文件地址
            // String realPath = request.getServletContext().getRealPath(
            //     "/files/" + fileType + File.separator + fileMessage.getId()
            // );
            String realPath = resourcesPath + "files/" + fileType + File.separator + fileMessage.getId();
            InputStream is = new FileInputStream(realPath);
            byte[] bytes = new byte[is.available()]; // is.available()可以拿到大小
            // 将流读到字节数组中
            is.read(bytes);
            // 2. 设置响应头
            HttpHeaders headers = new HttpHeaders();
            // 设置要下载文件的名字以及下载方式【设置文件格式为附件，让浏览器下载而不是打开资源】
            headers.add("Content-Disposition", "attachment;filename=" + fileName);
            // 处理中文文件名问题
            headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("utf-8"), "ISO-8859-1"));
            // 3. 设置状态码（HttpStatus.OK）
            responseEntity = new ResponseEntity<>(bytes, headers, HttpStatus.OK);
            is.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return responseEntity;
    }

}
