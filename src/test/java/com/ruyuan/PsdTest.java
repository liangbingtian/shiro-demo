package com.ruyuan;

import java.security.Key;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.BlowfishCipherService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.ByteSource.Util;
import org.junit.Test;

/**
 * @author liangbingtian
 * @date 2024/02/20 下午2:44
 */
public class PsdTest {

    @Test
    public void test1(){
        //编码Base64 , Hex
        String str = "abc";
        final String base64EncoderStr = Base64.encodeToString(str.getBytes());
        System.out.println();
        final String base64DecoderStr = Base64.decodeToString(base64EncoderStr);
        System.out.println();
        final String encodeToString = Hex.encodeToString(str.getBytes());
        final String string = new String(Hex.decode(encodeToString));
        //加密,不可逆的加密，md5和SHA
        String algorithmName = "MD5";
        int hasIterations = 1024;
        final ByteSource xx = Util.bytes("xx");
        SimpleHash simpleHash = new SimpleHash(algorithmName, str, xx, hasIterations);
        System.out.println(simpleHash);
        algorithmName = "SHA";
        simpleHash = new SimpleHash(algorithmName, str, xx, hasIterations);
        //加密，可逆的加密 Aes Blowfish
        final AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);
        final Key key = aesCipherService.generateNewKey();
        final String encryptStr = aesCipherService.encrypt(str.getBytes(), key.getEncoded()).toHex();
        System.out.println("aes:" + encryptStr);
        //解密
        final String decryptStr = new String(aesCipherService.decrypt(Hex.decode(encryptStr), key.getEncoded()).getBytes());
        System.out.println("aes:" + decryptStr);

        final BlowfishCipherService blowfishCipherService = new BlowfishCipherService();
        aesCipherService.setKeySize(128);
        final Key key1 = blowfishCipherService.generateNewKey();
        final String blowFishEncryptStr = aesCipherService.encrypt(str.getBytes(), key1.getEncoded()).toHex();
        System.out.println("blowFish:" + blowFishEncryptStr);
        //解密
        final String blowFishDecryptStr = new String(aesCipherService.decrypt(Hex.decode(blowFishEncryptStr), key1.getEncoded()).getBytes());
        System.out.println("blowFish:" + blowFishDecryptStr);
    }

    @Test
    public void test2() {
        String str = "123456";
        String algorithmName = "MD5";
        int hasIterations = 102;
        final ByteSource bytes = Util.bytes("xx");
        SimpleHash simpleHash = new SimpleHash(algorithmName, "123456", bytes, hasIterations);
        System.out.println(simpleHash.toHex());
    }

}
