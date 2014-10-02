package com.dudu.core;

import java.io.IOException;
import java.util.regex.Pattern;

import cn.edu.hfut.dmic.webcollector.crawler.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.model.Page;


public class Crawler extends BreadthCrawler {

	    /*visit函数定制访问每个页面时所需进行的操作*/
	 
	    @Override
		protected void visit(Page page) {
	    	   String question_regex="^http://www.zhihu.com/question/[0-9]+";
		        if(Pattern.matches(question_regex, page.url)){
		            System.out.println("正在抽取"+page.url);
		            /*抽取标题*/
		            String title=page.doc.title();
		            System.out.println(title);
		            /*抽取提问内容*/
		            String question=page.doc.select("div[id=zh-question-detail]").text();
		            System.out.println(question);
		 
		        }
			super.visit(page);
		}

		/*启动爬虫*/
	    public static void main(String[] args) throws IOException{  
	    	Crawler crawler=new Crawler();
	        crawler.addSeed("http://www.zhihu.com/question/21003086");
	        crawler.addRegex("http://www.zhihu.com/.*");
	        crawler.start(5);  
	    }
	 
	 
	

}
