/*
package com.hfkd.qhhealth.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hfkd.qhhealth.common.constant.ConstVal;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Pattern;

*/
/**
 * @author hexq
 * @date 2018/7/16 15:01
 *//*

@SuppressWarnings("all")
public class CrawlerDemo extends WebCrawler{

    */
/**
     * 正则表达式匹配指定的后缀文件
     *//*

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg" + "|png|mp3|mp4|zip|gz))$");

    */
/**
     * 这个方法主要是决定哪些url我们需要抓取，返回true表示是我们需要的，返回false表示不是我们需要的Url
     * 第一个参数referringPage封装了当前爬取的页面信息 第二个参数url封装了当前爬取的页面url信息
     * 在这个例子中，我们指定爬虫忽略具有css，js，git，...扩展名的url，只接受以“http://www.ics.uci.edu/”开头的url。
     * 在这种情况下，我们不需要referringPage参数来做出决定。
     *//*

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();// 得到小写的url
        return !FILTERS.matcher(href).matches() // 正则匹配，过滤掉我们不需要的后缀文件
                && href.startsWith("http://www.boohee.com/food/view_menu?page=");// 只接受以“http://www.ics.uci.edu/”开头的url
    }

    */
/**
     * 当一个页面被提取并准备好被你的程序处理时，这个函数被调用。
     *//*

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();// 获取url
        System.out.println("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData) {// 判断是否是html数据
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();//// 强制类型转换，获取html数据对象
            String text = htmlParseData.getText();//获取页面纯文本（无html标签）
            String html = htmlParseData.getHtml();//获取页面Html
            Set<WebURL> links = htmlParseData.getOutgoingUrls();// 获取页面输出链接

            */
/*String filepath = "/Users/xqh/WorkSpace/crawler/";
            int i = url.lastIndexOf("/");
            String s = url.substring(i, url.length());
            String filename = s + ".html";

            File file = new File(filepath + filename);
            try (FileOutputStream fos = new FileOutputStream(file);
                 BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                //写入文件
                byte[] bytes = html.getBytes();
                bos.write(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }*//*


            Document parse = Jsoup.parse(html);
            Elements items = parse.getElementsByClass("text-box pull-left");

            JSONArray arr = new JSONArray();
            for (Element item : items) {
                JSONObject json = new JSONObject();


                String a = item.getElementsByTag("a").text();
                int i = a.indexOf("，");
                String name;
                if (i > 0) {
                    name = a.substring(0, i);
                } else {
                    name = a;
                }
                json.put("name", name);
                json.put("desc", a);

                String p = item.getElementsByTag("p").text();
                int i1 = p.indexOf("：");
                int i2 = p.indexOf(" ");
                String kCal = p.substring(i1 + 1, i2);
                json.put("kcalPer100g", Integer.parseInt(kCal));
                json.put("sort", ConstVal.FOOD_DISHES);

                arr.add(json);
            }
            File file = new File("/Users/xqh/WorkSpace/crawler/food.json");
            try {
                FileUtils.writeLines(file, arr, true);
            } catch (IOException e) {
                e.printStackTrace();
            }


            System.out.println("纯文本长度: " + text.length());
            System.out.println("html长度: " + html.length());
            System.out.println("链接个数 " + links.size());
        }
    }

    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "/Users/xqh/WorkSpace/crawler";// 定义爬虫数据存储位置
        int numberOfCrawlers = 7;// 定义了7个爬虫，也就是7个线程

        CrawlConfig config = new CrawlConfig();// 定义爬虫配置
        config.setCrawlStorageFolder(crawlStorageFolder);// 设置爬虫文件存储位置

        // 实例化爬虫控制器。
        PageFetcher pageFetcher = new PageFetcher(config);// 实例化页面获取器
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();// 实例化爬虫机器人配置
        // 实例化爬虫机器人对目标服务器的配置，每个网站都有一个robots.txt文件
        // 规定了该网站哪些页面可以爬，哪些页面禁止爬，该类是对robots.txt规范的实现
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        // 实例化爬虫控制器
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        // 对于每次抓取，您需要添加一些种子网址。 这些是抓取的第一个URL，然后抓取工具开始跟随这些页面中的链接
        controller.addSeed("http://www.boohee.com/food/view_menu?page=1");

        // 启动爬虫，爬虫从此刻开始执行爬虫任务，根据以上配置
        controller.start(CrawlerDemo.class, numberOfCrawlers);
    }

}
*/
