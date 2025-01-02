package io.renren.modules.oss.cloud;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.StrUtil;
import io.renren.common.exception.ErrorCode;
import io.renren.common.exception.RenException;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Slf4j
public class LocalCloudStorageService extends AbstractCloudStorageService {

    public LocalCloudStorageService(CloudStorageConfig config) {
        this.config = config;
    }

    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }


    @Override
    public String upload(InputStream inputStream, String path) {
        log.info("当前的path{}",path);
        String extPath = "";
        String s = FileNameUtil.extName(path);
        if (s.equals("jpg") || s.equals("jpeg") || s.equals("png")) {
            extPath = "img/";
        }else if (s.equals("pdf") || s.equals("doc") || s.equals("docx") ){
            extPath = "pdf/";
        }


        String filePath = config.getLocalPath() + StrUtil.SLASH  + path;


        log.info("写入文件的路径{}",filePath);
        try {
            FileUtil.writeFromStream(inputStream, filePath);
        } catch (IORuntimeException e) {
            e.printStackTrace();
            throw new RenException(ErrorCode.OSS_UPLOAD_FILE_ERROR, e, "");
        }

        return config.getLocalDomain() + StrUtil.SLASH +extPath+ path;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {

        return upload(data, getPath(config.getLocalPrefix(), suffix));
    }


    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getLocalPrefix(), suffix));
    }
}
