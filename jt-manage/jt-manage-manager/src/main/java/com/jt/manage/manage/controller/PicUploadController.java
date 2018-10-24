package com.jt.manage.manage.controller;

import com.jt.common.vo.PicUploadResult;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PicUploadController {
    //不使用业务层编写代码,实际,企业中一定会在业务层编写逻辑
    /*
     * 1 判断合法 后缀 木马
     * 2 生成存放路径和网络相对访问url路径
     * 3 重命名
     * 4 存盘,返回数据 PicUploadResult
     */
    @RequestMapping("pic/upload")
    @ResponseBody
    public PicUploadResult picUpload(MultipartFile uploadFile) {
        //判断合法,后缀和木马(ImageIO)
        PicUploadResult result = new PicUploadResult();
        //获取文件名称
        String oldFileName = uploadFile.getOriginalFilename();
        //.jpg
        String extFileName = oldFileName.
                substring(oldFileName.lastIndexOf("."));
        //判断是否在 .jpg.png.gif等等图片后缀的合法范围
        //如果不满足正则,返回error=1的result对象
        if (!extFileName.matches("^.(jpg|png|gif)$")) {
            result.setError(1);
            return result;
        }
        //判断,用java提供的封装图片流的对象BufferImage,如果其中
        //有宽高,说明正常,没有宽高说明不是图片
        try {
            BufferedImage bufImage =
                    ImageIO.read(uploadFile.getInputStream());
            //如果不是图片,获取高或宽时会抛异常
            result.setHeight(bufImage.getHeight() + "");
            result.setWidth(bufImage.getWidth() + "");
            //生成一个公共路径 images/+yyyy/MM/dd的文件夹
            //dir=/images/2018/08/24/
            String dir = "/images/" +
                    new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/";
            //编写一个url虚拟路径的前缀
            //http://image.jt.com/images/2018/08/24/
            String urlPrefix = "http://image.jt.com/" + dir;
            //存盘路径前缀
            String path = "d:/jt-upload/" + dir;
            //创建目录
            File _dir = new File(path);
            if (!_dir.exists()) {
                _dir.mkdirs();
            }
            //重命名文件;文件名在一个短时间内唯一 当前时间毫秒+三位随机数
            String fileName = System.currentTimeMillis() + "" +
                    RandomUtils.nextInt(100, 999);
            //存盘,流的输出
            result.setUrl(urlPrefix + fileName);
            uploadFile.transferTo(new File(path + fileName));
        } catch (Exception e) {
            result.setError(1);
            return result;
        }
        return result;
    }


}
