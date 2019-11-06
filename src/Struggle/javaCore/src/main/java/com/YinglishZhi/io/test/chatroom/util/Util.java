package com.YinglishZhi.io.test.chatroom.util;

import java.nio.charset.Charset;
import java.util.HashSet;

/**
 * @author LDZ
 * @date 2019-11-05 21:03
 */
public class Util {
    public static Charset charset = Charset.forName("UTF-8");

    // 相当于自定义协议格式，与客户端协商好
    public static String USER_CONTENT_SPILIT = "#@#";

    // 用来记录在线人数，以及昵称
    public static HashSet<String> users = new HashSet<String>();
    public static String USER_EXIST = "system message: user exist, please change a name";
}
