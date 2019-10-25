package com.YinglishZhi.shell;

import java.io.*;

/**
 * shell
 *
 * @author LDZ
 * @date 2019-10-21 10:03
 */
public class test {

    private static String exec(String command) {
        StringBuilder result = new StringBuilder();
        Process pro;
        Runtime runtime = Runtime.getRuntime();
        try {
            pro = runtime.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(pro.getOutputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line).append("\n");
            }
            in.close();
            out.close();
            pro.destroy();

            in = new BufferedReader(new InputStreamReader(pro.getErrorStream()));    //错误信息
//            StringBuilder error = new StringBuilder();
//            while ((line = in.readLine()) != null) {
//                error.append(line).append("\n");
//            }
//            System.out.println(error);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void signAndEncryptByGPG(String filename, String pubKey, String priKey) {
        String outFile = filename + ".asc";
        String command = "gpg --sign --encrypt --armor " +
                " -r " + priKey +
                " -u " + pubKey +
                " -o " + outFile +
                " " + filename;
        System.out.println(command);
        String result = exec(command);
        System.out.println(result);
    }

    public static void main(String[] args) {
        exec("lll");
//        signAndEncryptByGPG("/Users/zhiyinglish/security/yf/yftext.txt", "6438F71DFF2A4B24F7C29796C584213910581A93", "6438F71DFF2A4B24F7C29796C584213910581A93");
    }
}
