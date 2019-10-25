package com.YinglishZhi.Spider;

import com.YinglishZhi.DownLoad.DownLoadImage;
import com.YinglishZhi.Utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class JsoupBaidu {

    private static final String BAIDU_IMAGE_URL = "http://image.baidu.com/search/avatarjson";
    private static final String WIDTH = "1680";
    private static final String HEIGHT = "1050";

    public static void getPicture(String keyword, int page, String downloadPath, String width, String height) {
        Document document = null;
        int imgCount = 0;
        String imagerUrl = "";
        String url = BAIDU_IMAGE_URL + "?tn=resultjsonavatarnew&ie=utf-8&word=" + keyword + "&cg=star&pn=" + page * 30 + "&rn=30&itg=0&z=0&fr=&width=" + width + "&height=" + height + "&lm=-1&ic=0&s=0&st=-1&gsm=" + Integer.toHexString(page * 30);
        try {
            document = Jsoup.connect(url).data("query", "java").userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15").timeout(5000).get();

            String XMLSource = document.toString();

            XMLSource = StringEscapeUtils.unescapeHtml3(XMLSource);

            String reg = "objURL\":\"http://.+?\\.jpg";
            Pattern pattern = Pattern.compile(reg);

            Matcher matcher = pattern.matcher(XMLSource);
            while (matcher.find()) {
                imagerUrl = matcher.group().substring(9);
                log.info(keyword + imgCount++ + ":" + imagerUrl);
                DownLoadImage.downloadImg(imagerUrl, downloadPath);
                log.info("下载成功");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String downCatalog = "美女";
        List<String> downCatalogs = StringUtils.String2List(downCatalog);
        for (String keyword : downCatalogs) {
            Runnable r = () -> {
                getPicture(keyword, 1, "/Users/liudezhi/Pictures/AddField/", WIDTH, HEIGHT);
            };
            new Thread(r).start();
        }

    }
}
