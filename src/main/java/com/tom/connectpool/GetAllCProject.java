package com.tom.connectpool;

import cn.hutool.http.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class GetAllCProject {

    private static String FETCH_URL = "https://github.com/topics/c?l=c&o=desc&s=stars&page=";

    private GetAllCProject(){

    }
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            String listContent = readHtml(FETCH_URL+i);
            System.out.println(listContent);
        }
    }


    private static String readHtml(String url){
        StringBuffer sb = new StringBuffer();
        String temp=""; //创建临时字符串用于保存每一次读的一行数据，然后html调用append方法写入temp;
        String html = HttpUtil.get(url);
        Document doc= Jsoup.parse(html); //通过Jsoup解析页面,生成一个document对象;
        Elements elements=doc.getElementsByClass("f3 text-gray text-normal lh-condensed");
        for (Element ele: elements) {
            Elements tmp = ele.getElementsByTag("a");
            Element tmpElement = tmp.get(1);
            StringBuffer str = new StringBuffer();
            str.append("git clone ");
            str.append("https://github.com");
            str.append(tmpElement.attr("href")).append(".git");
            sb.append(str+"\n");
        }
        return sb.toString();
    }
}
