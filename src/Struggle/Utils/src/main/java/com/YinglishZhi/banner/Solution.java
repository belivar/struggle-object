package com.YinglishZhi.banner;

import com.YinglishZhi.Utils.IOUtil;
import com.taobao.text.Color;
import com.taobao.text.Decoration;
import com.taobao.text.ui.TableElement;
import com.taobao.text.util.RenderUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import static com.taobao.text.ui.Element.label;

/**
 * @author LDZ
 * @date 2019-10-24 18:20
 */
@Slf4j
public class Solution {
    private static final String LOGO_LOCATION = "/com/YinglishZhi/res/logo.txt";
//    private static final String CREDIT_LOCATION = "/com/taobao/arthas/core/res/thanks.txt";
//    private static final String VERSION_LOCATION = "/com/taobao/arthas/core/res/version";
//    private static final String WIKI = "https://alibaba.github.io/arthas";
//    private static final String TUTORIALS = "https://alibaba.github.io/arthas/arthas-tutorials";

    private static String LOGO = "Welcome to Arthas";
    private static String VERSION = "unknown";
    private static String THANKS = "";


    static {
        try {
            InputStream inputStream = Solution.class.getResourceAsStream(LOGO_LOCATION);
            if (inputStream == null){
                System.out.println(666);
            }
            String logoText = com.YinglishZhi.Utils.IOUtil.toString(inputStream);
//            THANKS = IOUtil.toString(ShellServerOptions.class.getResourceAsStream(CREDIT_LOCATION));
//            InputStream versionInputStream = ShellServerOptions.class.getResourceAsStream(VERSION_LOCATION);
//            if (versionInputStream != null) {
//                VERSION = IOUtil.toString(versionInputStream).trim();
//            } else {
//                String implementationVersion = ArthasBanner.class.getPackage().getImplementationVersion();
//                if (implementationVersion != null) {
//                    VERSION = implementationVersion;
//                }
//            }

            StringBuilder sb = new StringBuilder();
            String[] LOGOS = new String[5];
            int i = 0, j = 0;
            for (String line : logoText.split("\n")) {
                sb.append(line);
                sb.append("\n");
                if (i++ == 5) {
                    LOGOS[j++] = sb.toString();
                    i = 0;
                    sb.setLength(0);
                }
            }

            TableElement logoTable = new TableElement();
            logoTable.row(label(LOGOS[0]).style(Decoration.bold.fg(Color.red)),
                    label(LOGOS[1]).style(Decoration.bold.fg(Color.yellow)),
                    label(LOGOS[2]).style(Decoration.bold.fg(Color.cyan)),
                    label(LOGOS[3]).style(Decoration.bold.fg(Color.magenta)),
                    label(LOGOS[4]).style(Decoration.bold.fg(Color.green))
//                    label(LOGOS[5]).style(Decoration.bold.fg(Color.blue))
            );
            LOGO = RenderUtil.render(logoTable);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

//    public static String wiki() {
//        return WIKI;
//    }

//    public static String tutorials() {
//        return TUTORIALS;
//    }

    public static String credit() {
        return THANKS;
    }

    public static String version() {
        return VERSION;
    }

    public static String logo() {
        return LOGO;
    }

    public static String plainTextLogo() {
        return RenderUtil.ansiToPlainText(LOGO);
    }

    public static String welcome() {
        return welcome(Collections.<String, String>emptyMap());
    }

    public static String welcome(Map<String, String> infos) {
        log.info("arthas version: " + version());
//        TableElement table = new TableElement().rightCellPadding(1)
//                .row("wiki", wiki())
//                .row("tutorials", tutorials())
//                .row("version", version())
//                .row("pid", PidUtils.currentPid())
//                .row("time", DateUtils.getCurrentDate());
//        for (Map.Entry<String, String> entry : infos.entrySet()) {
//            table.row(entry.getKey(), entry.getValue());
//        }

        return logo() + "\n";
    }

    public static void main(String[] args) {
        String s = welcome();
        System.out.println(s);
    }
}
