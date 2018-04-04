package com.templates.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.tomcat.util.codec.binary.Base64;
import java.security.SecureRandom;

public class Salt {

    /**
     * 生成盐
     * @return
     */
    public static String createSalt() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        String salt = Base64.encodeBase64String(bytes);
        System.out.println("salt:" + salt);
        return salt;

    }

    public static String createCredentials(String salt,String credentials) {
        String hashAlgorithmName = "md5";
        int hashIterations = 2;
        String result = new SimpleHash(hashAlgorithmName, credentials,salt, hashIterations).toString();
        System.out.println(result);
        return result;
    }




    public static void main(String[] args) {
//        System.out.println(createSalt());
        System.out.println(createCredentials("/4QFCGXeQN+EpQ1FfkXiJA==","123456"));
    }

}