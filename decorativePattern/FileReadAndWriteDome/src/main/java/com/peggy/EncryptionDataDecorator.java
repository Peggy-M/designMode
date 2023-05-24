package com.peggy;

import java.util.Base64;

/**
 * @Projectname: designMode
 * @Filename: EncryptionDataDecorator
 * @Author: peggy
 * @Data:2023/5/24 15:33
 * @Description: 具体装饰者对文件进行加密与解密
 */

public class EncryptionDataDecorator extends DataLoaderDecorator{

    public EncryptionDataDecorator(DataLoader wrapper) {
        super(wrapper);
    }

    @Override
    public String redFile() {
        return super.redFile();
    }

    @Override
    public void write(String data) {
        super.write(data);
    }

    //加密操作
    private String encode(String data){
        try {
            Base64.Encoder encoder=Base64.getEncoder();
            byte[] bytes=data.getBytes();
            return encoder.encodeToString(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //解密操作
    private String decode(String data){
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(data));
    }
}
