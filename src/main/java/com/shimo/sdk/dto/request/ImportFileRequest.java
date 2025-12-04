package com.shimo.sdk.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.File;

/**
 * 导入文件请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ImportFileRequest extends BaseRequest {
    
    /**
     * 需要导入的文件类型
     */
    private String type;
    
    /**
     * 需要导入的文件(如果不发送文件,就必须传 fileUrl、fileName)
     */
    private File file;
    
    /**
     * 需要导入的文件下载地址(有参数 file,可以不用传)
     */
    private String fileUrl;
    
    /**
     * 需要导入的文件名称(有参数 file,可以不用传)
     */
    private String fileName;

    /**
     * 导入文件时,指定导入的文件ID
     */
    private String importFontType;
}

