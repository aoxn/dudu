package com.dudu.core;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cn.edu.hfut.dmic.webcollector.crawler.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.model.Page;

import com.dudu.util.ConfigOPP;

public class Crawler extends BreadthCrawler {


	private static Lock lock = new ReentrantLock();
	private PageIndexer pi = null;

	public Crawler() {
		pi = new PageIndexer(lock);
//		pi = new PageIndexer(lock,new NorwegianAnalyzer(ConfigOPP.LUCENE_VERSION));
	}
	
	/* visit函数定制访问每个页面时所需进行的操作 */
	@Override
	protected void visit(Page page) {
		super.visit(page);
		pi.indexPage(page);
		if (pi.getTotalIndexed() > ConfigOPP.MAX_CRAW) {
			System.out.println("Finished " + ConfigOPP.MAX_CRAW + " WebPages!");
			pi.commitAndClose();
			try {
				this.stop();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
	}
	public void commitJob(){
		pi.commitAndClose();
	}
	/* 启动爬虫 */
	public static void main(String[] args) throws IOException {
		Crawler crawler = new Crawler();
		crawler.setCrawl_path("data");
		crawler.setRoot("data/file");
		// crawler.addSeed("http://www.oppland.no/");
		// crawler.addRegex("http://*.oppland.no/.*");
		crawler.addSeed("http://www.oschina.net/");
		crawler.addRegex(".*oschina.net/.*");
		crawler.start(2);
		crawler.commitJob();
	}
}
