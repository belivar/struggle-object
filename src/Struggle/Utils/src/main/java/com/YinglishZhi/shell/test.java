package com.YinglishZhi.shell;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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


    public static  void exexAncsy() {
        List<String> command = new ArrayList<>();
        command.add("java");
        command.add("-jar /Users/zhiyinglish/dev/rhine/terminal/target/terminal-jar-with-dependencies.jar");
        ProcessBuilder pb = new ProcessBuilder(command);
        try {
            final Process proc = pb.start();
            Thread redirectStdout = new Thread(() -> {
                InputStream inputStream = proc.getInputStream();
                try {
                    IOUtils.copy(inputStream, System.out);
                } catch (IOException e) {
                    IOUtils.close(inputStream);
                }

            });

            Thread redirectStderr = new Thread(() -> {
                InputStream inputStream = proc.getErrorStream();
                try {
                    IOUtils.copy(inputStream, System.err);
                } catch (IOException e) {
                    IOUtils.close(inputStream);
                }

            });
            redirectStdout.start();
            redirectStderr.start();
            redirectStdout.join();
            redirectStderr.join();

            int exitValue = proc.exitValue();
            if (exitValue != 0) {
                System.exit(1);
            }
        } catch (Throwable e) {
            // ignore
        }
        System.out.println(1111);
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
        exec("java -jar /Users/zhiyinglish/dev/rhine/terminal/target/terminal-jar-with-dependencies.jar");
//        signAndEncryptByGPG("/Users/zhiyinglish/security/yf/yftext.txt", "6438F71DFF2A4B24F7C29796C584213910581A93", "6438F71DFF2A4B24F7C29796C584213910581A93");
//        exexAncsy();
    }
}
