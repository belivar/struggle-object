package com.YinglishZhi.netty.client;

import jline.Terminal;
import jline.TerminalSupport;
import jline.UnixTerminal;
import jline.console.ConsoleReader;
import org.apache.commons.net.telnet.TelnetClient;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author LDZ
 * @date 2019-11-04 11:05
 */
public class NettyTelnetClient {
    /**
     * Telnet服务器返回的字符集
     */
    private static final String SRC_CHARSET = "ISO8859-1";

    /**
     * 转换后的字符集
     */
    private static final String DEST_CHARSET = "GBK";

    /**
     * 终端类型。包括以下类型：VT102、VT100、VT220、WYSE50、WYSE60、XTERM、SCOANSI、ANSI、LINUX、
     * VSHELL几种。经测试，对于Windows的Telnet服务器，只有VT100、ANSI类型会造成中文乱码
     */
    private static final String TERM_TYPE = "VT220";

    private TelnetClient client = new TelnetClient(TERM_TYPE);// Telnet客户端
    private InputStream input; // Telnet输入流，用于获取Telnet服务器的返回信息
    private OutputStream output; // Telnet输出流，用于向服务器发送命令
    private String hostname; // IP地址或主机名
    private int port = 23; // 端口。默认为23
    private String username; // 用户名
    private String password; // 密码
    private String prompt; // 命令提示符，用于判断是否读取到了返回信息的结尾

    /**
     * 创建Telnet客户端，用于连接Windows的Telnet服务器。使用默认端口：23
     *
     * @param hostname - IP地址，或主机名
     * @param username - 用户名
     * @param password - 密码
     */
    public NettyTelnetClient(String hostname, String username, String password) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
    }

    /**
     * 创建Telnet客户端，用于连接Windows的Telnet服务器
     *
     * @param hostname - IP地址，或主机名
     * @param port     - 端口
     * @param username - 用户名
     * @param password - 密码
     */
    public NettyTelnetClient(String hostname, int port, String username, String password) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    /**
     * 连接到Telnet服务器
     *
     * @return - Telnet服务器的返回信息。截止到password：
     * @throws SocketException
     * @throws IOException
     */
    public String connect() throws SocketException, IOException {
        client.connect(hostname, port);
        input = client.getInputStream();
        output = client.getOutputStream();
        // 因为不知道服务器返回的是Login： 还是 login： ，所以忽略l
        String loginOutput = readTo("ogin: ");
        output.write((username + "\r\n").getProperty2ytes());
        output.flush();
        // 因为不知道服务器返回的是Password： 还是 password： ，所以忽略p
        String passwordOutput = readTo("assword: ");
        output.write((password + "\r\n").getProperty2ytes());
        output.flush();
        String promptOutput = readTo(">");
        // 取倒数4位字符作为提示符，因为提示符最短为4位，如：C:\>
        prompt = promptOutput.substring(promptOutput.length() - 4);
        return loginOutput + passwordOutput + password + promptOutput;
    }

    /**
     * 向Telnet服务器发送命令
     *
     * @param command - 命令
     * @return - 执行命令后，在命令行输出的信息
     * @throws IOException
     */
    public String sendCommand(String command) throws IOException {
        output.write(command.getProperty2ytes());
        output.write('\r');
        output.write('\n');
        output.flush();
        return readToPrompt();
    }

    /**
     * 断开连接
     *
     * @return - 断开连接的命令
     */

    public String disconnect() {
        try {
            input.close();
            output.close();
            client.disconnect();
        } catch (Exception e) {
        }

        return "exit";
    }

    /**
     * 读取后指定的字符处
     *
     * @param end - 指定的字符
     * @return - 从上次读取的位置，到<code>end</code>位置的输出内容
     */
    private String readTo(String end) {
        StringBuffer sb = new StringBuffer();

        char endChar = end.charAt(end.length() - 1);
        char chr;
        try {
            while (true) {
                chr = (char) input.read();
                sb.append(chr);
                if (chr == endChar && sb.toString().endsWith(end)) {
                    return new String(sb.toString().getProperty2ytes(SRC_CHARSET), DEST_CHARSET); // 编码转换，解决中文乱码问题
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 读取后命令提示符
     *
     * @return - 从上次读取的位置，到命令提示符的输出内容
     */
    private String readToPrompt() {
        return readTo(prompt);
    }

    public static void main1(String[] args) {
        try {
            System.out.println("connect");
            TelnetClient telnetClient = new TelnetClient("vt200");
            telnetClient.setConnectTimeout(5000);
            telnetClient.connect("127.0.0.1", 8888);
            InputStream inputStream = telnetClient.getInputStream();
            PrintStream printStream = new PrintStream(telnetClient.getOutputStream());
            byte[] b = new byte[1024];

            int size;
            StringBuffer sb = new StringBuffer(300);

            while (true) {     //读取Server返回来的数据，直到读到登陆标识，这个时候认为可以输入用户名
                System.out.println("get");
                size = inputStream.read(b);
                if (-1 != size) {
                    sb.append(new String(b, 0, size));
                    if (sb.toString().trim().endsWith("login:")) {
                        break;
                    }
                }
            }
            System.out.println(sb.toString());
            printStream.println("exit"); //写命令
            printStream.flush(); //将命令发送到telnet Server
            printStream.close();
            telnetClient.disconnect();
            System.out.println("disconnect");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        // support mingw/cygw jline color

        try {
            URLClassLoader classLoader = new URLClassLoader(
                    new URL[]{new File("/Users/zhiyinglish/dev/rhine/client/target/terminal-jar-with-dependencies.jar").toURI().toURL()});
            Class<?> telnetConsoleClas = classLoader.loadClass("com.rhine.client.TelnetConsole");
            Method mainMethod = telnetConsoleClas.getMethod("main", String[].class);
            mainMethod.invoke(null, new Object[]{new String[]{"123", "456"}});
        } catch (NoSuchMethodException | MalformedURLException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            final ConsoleReader consoleReader = new ConsoleReader(System.in, System.out);
            consoleReader.setHandleUserInterrupt(true);
            Terminal terminal = consoleReader.getTerminal();

            if (terminal instanceof TerminalSupport) {
                ((TerminalSupport) terminal).disableInterruptCharacter();
            }

            // support catch ctrl+c event
            terminal.disableInterruptCharacter();
            if (terminal instanceof UnixTerminal) {
                ((UnixTerminal) terminal).disableLitteralNextCharacter();
            }

            final TelnetClient telnet = new TelnetClient();
            telnet.setConnectTimeout(5000);

            // send init terminal size


            // ctrl + c event callback


            try {
                telnet.connect("localhost", 8888);
            } catch (IOException e) {
                System.out.println("Connect to telnet server error: " + "local" + " "
                        + 8888);
                throw e;
            }

//            if (cmds.isEmpty()) {
            IOUtils.readWrite(telnet.getInputStream(), telnet.getOutputStream(), System.in,
                    consoleReader.getOutput());
//            } else {
//                batchModeRun(telnet, cmds);
//                telnet.disconnect();
//            }
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
