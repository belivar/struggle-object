package com.YinglishZhi.ftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * ftp util
 *
 * @author LDZ
 * @date 2020-01-20 14:14
 */
@Slf4j
public class FtpUtils {

    private String FTP_ADDRESS;

    private int FTP_PORT;

    private String FTP_USERNAME;

    private String FTP_PASSWORD;

    private String BASE_PATH;

    private Boolean b = false;

    /**
     * 2018-6-13 12:39:55
     * 新添，初始化登录ftp，连接失败 返回b 为：false ,成功 为 ：true
     *
     * @param FTP_USERNAME
     * @param FTP_PASSWORD
     * @param BASE_PATH
     */
    public FtpUtils(String FTP_ADDRESS, int FTP_PORT, String FTP_USERNAME, String FTP_PASSWORD, String BASE_PATH) {
        this.FTP_ADDRESS = FTP_ADDRESS;
        this.FTP_PORT = FTP_PORT;
        this.FTP_USERNAME = FTP_USERNAME;
        this.FTP_PASSWORD = FTP_PASSWORD;
        this.BASE_PATH = BASE_PATH;
        b = login(FTP_ADDRESS, FTP_PORT, this.FTP_USERNAME, this.FTP_PASSWORD);
    }

    /**
     * 本地字符编码
     **/
    private static String localCharset = "GBK";

    /**
     * FTP协议里面，规定文件名编码为iso-8859-1
     **/
    private static String serverCharset = "ISO-8859-1";

    /**
     * UTF-8字符编码
     **/
    private static final String CHARSET_UTF8 = "UTF-8";

    /**
     * OPTS UTF8字符串常量
     **/
    private static final String OPTS_UTF8 = "OPTS UTF8";

    /**
     * 设置缓冲区大小4M
     **/
    private static final int BUFFER_SIZE = 1024 * 1024 * 4;

    /**
     * FTPClient对象
     **/
    private static FTPClient ftpClient = null;

    /**
     * 下载指定文件到本地
     *
     * @param ftpPath  FTP服务器文件相对路径，例如：test/123
     * @param fileName 要下载的文件名，例如：test.txt
     * @param savePath 保存文件到本地的路径，例如：D:/test
     * @return 成功返回true，否则返回false
     */
    public boolean downloadFile(String ftpPath, String fileName, String savePath) {
        // 登录
        boolean flag = false;
        if (ftpClient != null) {
            try {
                String path = changeEncoding(BASE_PATH + ftpPath);
                // 判断是否存在该目录
                if (!ftpClient.changeWorkingDirectory(path)) {
                    System.out.println(BASE_PATH + ftpPath + "该目录不存在");
                    log.error(BASE_PATH + ftpPath + "该目录不存在");
                    return flag;
                }
                ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
                String[] fs = ftpClient.listNames();
                // 判断该目录下是否有文件
                if (fs == null || fs.length == 0) {
                    System.out.println(BASE_PATH + ftpPath + "该目录不存在");
                    log.error(BASE_PATH + ftpPath + "该目录下没有文件");
                    return flag;
                }
                for (String ff : fs) {
                    String ftpName = new String(ff.getBytes(serverCharset), localCharset);
                    if (ftpName.equals(fileName)) {
                        File file = new File(savePath + '/' + ftpName);
                        try {
                            OutputStream os = new FileOutputStream(file);
                            flag = ftpClient.retrieveFile(ff, os);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            log.error(e.getMessage(), e);
                        }
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("下载文件失败" + e);
                log.error("下载文件失败", e);
            } finally {
                Boolean close = closeConnect();
                System.out.println("连接是否关闭：" + close);
            }
        }
        return flag;
    }

    /**
     * 下载该目录下所有文件到本地
     *
     * @param ftpPath  FTP服务器上的相对路径，例如：test/123
     * @param savePath 保存文件到本地的路径，例如：D:/test
     * @return 成功返回true，否则返回false
     */
    public boolean downloadFiles(String ftpPath, String savePath) {
        // 登录
        boolean flag = false;
        if (ftpClient != null) {
            try {
                String path = changeEncoding(BASE_PATH + ftpPath);
                // 判断是否存在该目录
                if (!ftpClient.changeWorkingDirectory(path)) {
                    System.out.println(BASE_PATH + ftpPath + "该目录不存在");
                    log.error(BASE_PATH + ftpPath + "该目录不存在");
                    return flag;
                }
                ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
                String[] fs = ftpClient.listNames();
                // 判断该目录下是否有文件
                if (fs == null || fs.length == 0) {
                    System.out.println(BASE_PATH + ftpPath + "该目录下没有文件");
                    log.error(BASE_PATH + ftpPath + "该目录下没有文件");
                    return flag;
                }
                for (String ff : fs) {
                    String ftpName = new String(ff.getBytes(serverCharset), localCharset);
                    File file = new File(savePath + '/' + ftpName);
                    try {
                        OutputStream os = new FileOutputStream(file);
                        ftpClient.retrieveFile(ff, os);
                    } catch (Exception e) {
                        System.out.println(e.getMessage() + e);
                        log.error(e.getMessage(), e);
                    }
                }
                flag = true;
            } catch (IOException e) {
                System.out.println("下载文件失败" + e);
                log.error("下载文件失败", e);
            } finally {
                Boolean close = closeConnect();
                System.out.println("连接是否关闭：" + close);
            }
        }
        return flag;
    }

    /**
     * 获取该目录下所有文件,以字节数组返回
     *
     * @param ftpPath FTP服务器上文件所在相对路径，例如：test/123
     * @return Map<String, Object> 其中key为文件名，value为字节数组对象
     */
    public Map<String, byte[]> getFileBytes(String ftpPath) {
        // 登录
        Map<String, byte[]> map = new HashMap<String, byte[]>();
        if (ftpClient != null) {
            try {
                String path = changeEncoding(BASE_PATH + ftpPath);
                // 判断是否存在该目录
                if (!ftpClient.changeWorkingDirectory(path)) {
                    System.out.println(BASE_PATH + ftpPath + "该目录不存在");
                    log.error(BASE_PATH + ftpPath + "该目录不存在");
                    return map;
                }
                ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
                String[] fs = ftpClient.listNames();
                // 判断该目录下是否有文件
                if (fs == null || fs.length == 0) {
                    System.out.println(BASE_PATH + ftpPath + "该目录下没有文件");
                    log.error(BASE_PATH + ftpPath + "该目录下没有文件");
                    return map;
                }
                for (String ff : fs) {
                    try {
                        InputStream is = ftpClient.retrieveFileStream(ff);
                        String ftpName = new String(ff.getBytes(serverCharset), localCharset);
                        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                        byte[] buffer = new byte[BUFFER_SIZE];
                        int readLength = 0;
                        while ((readLength = is.read(buffer, 0, BUFFER_SIZE)) > 0) {
                            byteStream.write(buffer, 0, readLength);
                        }
                        map.put(ftpName, byteStream.toByteArray());
                        ftpClient.completePendingCommand(); // 处理多个文件
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        log.error(e.getMessage(), e);
                    }
                }
            } catch (IOException e) {
                System.out.println("获取文件失败" + e);
                log.error("获取文件失败", e);
            } finally {
                Boolean close = closeConnect();
                System.out.println("连接是否关闭：" + close);
            }
        }
        return map;
    }

    /**
     * 根据名称获取文件，以字节数组返回
     *
     * @param ftpPath  FTP服务器文件相对路径，例如：test/123
     * @param fileName 文件名，例如：test.xls
     * @return byte[] 字节数组对象
     */
    public byte[] getFileBytesByName(String ftpPath, String fileName) {
        // 登录
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        if (ftpClient != null) {
            try {
                String path = changeEncoding(BASE_PATH + ftpPath);
                // 判断是否存在该目录
                if (!ftpClient.changeWorkingDirectory(path)) {
                    System.out.println(BASE_PATH + ftpPath + "该目录不存在");
                    log.error(BASE_PATH + ftpPath + "该目录不存在");
                    return byteStream.toByteArray();
                }
                ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
                String[] fs = ftpClient.listNames();
                // 判断该目录下是否有文件
                if (fs == null || fs.length == 0) {
                    System.out.println(BASE_PATH + ftpPath + "该目录下没有文件");
                    log.error(BASE_PATH + ftpPath + "该目录下没有文件");
                    return byteStream.toByteArray();
                }
                for (String ff : fs) {
                    String ftpName = new String(ff.getBytes(serverCharset), localCharset);
                    if (ftpName.equals(fileName)) {
                        try {
                            InputStream is = ftpClient.retrieveFileStream(ff);
                            byte[] buffer = new byte[BUFFER_SIZE];
                            int len = -1;
                            while ((len = is.read(buffer, 0, BUFFER_SIZE)) != -1) {
                                byteStream.write(buffer, 0, len);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage() + e);
                            log.error(e.getMessage(), e);
                        }
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("获取文件失败" + e);
                log.error("获取文件失败", e);
            } finally {
                Boolean close = closeConnect();
                System.out.println("连接是否关闭：" + close);
            }
        }
        return byteStream.toByteArray();
    }

    /**
     * 获取该目录下所有文件,以输入流返回
     *
     * @param ftpPath FTP服务器上文件相对路径，例如：test/123
     * @return Map<String, InputStream> 其中key为文件名，value为输入流对象
     */
    public Map<String, InputStream> getFileInputStream(String ftpPath) {
        // 登录
        Map<String, InputStream> map = new HashMap<String, InputStream>();
        if (ftpClient != null) {
            try {
                String path = changeEncoding(BASE_PATH + ftpPath);
                // 判断是否存在该目录
                if (!ftpClient.changeWorkingDirectory(path)) {
                    System.out.println(BASE_PATH + ftpPath + "该目录不存在");
                    log.error(BASE_PATH + ftpPath + "该目录不存在");
                    return map;
                }
                ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
                String[] fs = ftpClient.listNames();
                // 判断该目录下是否有文件
                if (fs == null || fs.length == 0) {
                    System.out.println(BASE_PATH + ftpPath + "该目录下没有文件");
                    log.error(BASE_PATH + ftpPath + "该目录下没有文件");
                    return map;
                }
                for (String ff : fs) {
                    String ftpName = new String(ff.getBytes(serverCharset), localCharset);
                    InputStream is = ftpClient.retrieveFileStream(ff);
                    map.put(ftpName, is);
                    ftpClient.completePendingCommand(); // 处理多个文件
                }
            } catch (IOException e) {
                System.out.println("获取文件失败" + e);
                log.error("获取文件失败", e);
            } finally {
                Boolean close = closeConnect();
                System.out.println("连接是否关闭：" + close);
            }
        }
        return map;
    }

    /**
     * 根据名称获取文件，以输入流返回
     *
     * @param ftpPath  FTP服务器上文件相对路径，例如：test/123
     * @param fileName 文件名，例如：test.txt
     * @return InputStream 输入流对象
     */
    public InputStream getInputStreamByName(String ftpPath, String fileName) {
        // 登录
        InputStream input = null;
        if (ftpClient != null) {
            try {
                String path = changeEncoding(BASE_PATH + ftpPath);
                // 判断是否存在该目录
                if (!ftpClient.changeWorkingDirectory(path)) {
                    System.out.println(BASE_PATH + ftpPath + "该目录不存在");
                    log.error(BASE_PATH + ftpPath + "该目录不存在");
                    return input;
                }
                ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
                String[] fs = ftpClient.listNames();
                // 判断该目录下是否有文件
                if (fs == null || fs.length == 0) {
                    System.out.println(BASE_PATH + ftpPath + "该目录下没有文件");
                    log.error(BASE_PATH + ftpPath + "该目录下没有文件");
                    return input;
                }
                for (String ff : fs) {
                    String ftpName = new String(ff.getBytes(serverCharset), localCharset);
                    if (ftpName.equals(fileName)) {
                        input = ftpClient.retrieveFileStream(ff);
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("获取文件失败" + e);
                log.error("获取文件失败", e);
            } finally {
                Boolean connect = closeConnect();
                System.out.println("连接关闭状态：" + connect);
            }
        }
        return input;
    }

    /**
     * 根据文件夹，文件 名称，判断是否存在
     *
     * @param ftpPath  FTP服务器上文件相对路径，例如：test/123
     * @param fileName 文件名，例如：test.txt
     * @return map
     */
    public Map checkoutFtpPathAndFileName(String ftpPath, String fileName) {
        // 登录
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("filePath", false);
        map.put("fileName", false);
        if (ftpClient != null) {
            try {
                String path = changeEncoding(BASE_PATH + ftpPath);
                // 判断是否存在该目录
                if (!ftpClient.changeWorkingDirectory(path)) {
                    System.out.println(BASE_PATH + ftpPath + "该目录不存在");
                    map.put("filePath", false);
                } else {
                    map.put("filePath", true);
                }
                ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
                String[] fs = ftpClient.listNames();
                // 判断该目录下是否有文件
                if (fs == null || fs.length == 0) {
                    System.out.println(BASE_PATH + ftpPath + "该目录下没有文件");
                    map.put("fileName", false);
                }
                for (String ff : fs) {
                    String ftpName = new String(ff.getBytes(serverCharset), localCharset);
                    if (ftpName.equals(fileName)) {
                        map.put("fileName", true);
                    }
                }
            } catch (IOException e) {
                System.out.println("获取文件失败" + e);
                log.error("获取文件失败", e);
            }
        }
        return map;
    }

    /**
     * 删除指定文件
     *
     * @param filePath 文件相对路径，例如：test/123/test.txt
     * @return 成功返回true，否则返回false
     */
    public boolean deleteFile(String filePath) {
        // 登录
        boolean flag = false;
        if (ftpClient != null) {
            try {
                String path = changeEncoding(BASE_PATH + filePath);
                flag = ftpClient.deleteFile(path);
            } catch (IOException e) {
                System.out.println("删除文件失败" + e);
                log.error("删除文件失败", e);
            } finally {
                Boolean close = closeConnect();
                System.out.println("连接是否关闭：" + close);
            }
        }
        return flag;
    }

    /**
     * 删除目录下所有文件
     *
     * @param dirPath 文件相对路径，例如：test/123
     * @return 成功返回true，否则返回false
     */
    public boolean deleteFiles(String dirPath) {
        // 登录
        boolean flag = false;
        if (ftpClient != null) {
            try {
                ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
                String path = changeEncoding(BASE_PATH + dirPath);
                String[] fs = ftpClient.listNames(path);
                // 判断该目录下是否有文件
                if (fs == null || fs.length == 0) {
                    System.out.println(BASE_PATH + dirPath + "该目录下没有文件");
                    log.error(BASE_PATH + dirPath + "该目录下没有文件");
                    return flag;
                }
                for (String ftpFile : fs) {
                    ftpClient.deleteFile(ftpFile);
                }
                flag = true;
            } catch (IOException e) {
                System.out.println("删除文件失败" + e);
                log.error("删除文件失败", e);
            } finally {
                Boolean close = closeConnect();
                System.out.println("连接是否关闭：" + close);
            }
        }
        return flag;
    }

    /**
     * 连接FTP服务器
     *
     * @param address  地址，如：127.0.0.1
     * @param port     端口，如：21
     * @param username 用户名，如：root
     * @param password 密码，如：root
     */
    private Boolean login(String address, int port, String username, String password) {
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(address, port);
            ftpClient.login(username, password);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                closeConnect();
                System.out.println("FTP服务器连接失败：" + "地址：" + address + "  端口：" + port + "  用户名：" + username + "  密码：" + password);
                log.error("FTP服务器连接失败");
            } else {
                b = true;
            }
        } catch (Exception e) {
            System.out.println("FTP登录失败" + e);
            log.error("FTP登录失败", e);
        }
        return b;
    }

    public static void main(String[] args) {
        FtpUtils f = new FtpUtils("ftp://ftp.ncdc.noaa.gov/pub/data/noaa/", 21, "anonymous", null, "/");

        boolean a = f.login("ftp://ftp.ncdc.noaa.gov/pub/data/noaa/", 21, "anonymous", null);
        System.out.println(a);
    }

    /**
     * 关闭FTP连接
     */
    public Boolean closeConnect() {
        Boolean b = false;
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                b = true;
            } catch (IOException e) {
                System.out.println("关闭FTP连接失败" + e);
                log.error("关闭FTP连接失败", e);
            }
        }
        return b;
    }

    /**
     * FTP服务器路径编码转换
     *
     * @param ftpPath FTP服务器路径
     * @return String
     */
    private static String changeEncoding(String ftpPath) {
        String directory = null;
        try {
            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS_UTF8, "ON"))) {
                localCharset = CHARSET_UTF8;
            }
            directory = new String(ftpPath.getBytes(localCharset), serverCharset);
        } catch (Exception e) {
            System.out.println("路径编码转换失败" + e);
            log.error("路径编码转换失败", e);
        }
        return directory;
    }

    /**
     * 在服务器上递归创建目录
     *
     * @param dirPath 上传目录路径
     * @return
     */
    private void createDirectorys(String dirPath) {
        try {
            if (!dirPath.endsWith("/")) {
                dirPath += "/";
            }
            String directory = dirPath.substring(0, dirPath.lastIndexOf("/") + 1);
            ftpClient.makeDirectory("/");
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            while (true) {
                String subDirectory = new String(dirPath.substring(start, end));
                if (!ftpClient.changeWorkingDirectory(subDirectory)) {
                    if (ftpClient.makeDirectory(subDirectory)) {
                        ftpClient.changeWorkingDirectory(subDirectory);
                    } else {
                        System.out.println("创建目录失败");
                        log.info("创建目录失败");
                        return;
                    }
                }
                start = end + 1;
                end = directory.indexOf("/", start);
                //检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("上传目录创建失败" + e);
            log.error("上传目录创建失败", e);
        }
    }


}
