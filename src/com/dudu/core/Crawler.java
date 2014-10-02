package com.dudu.core;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.no.NorwegianAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import cn.edu.hfut.dmic.webcollector.crawler.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.model.Page;

public class Crawler extends BreadthCrawler {
	private static int MAX_CRAW = 3000;
	/* visit函数定制访问每个页面时所需进行的操作 */
	private static int total = 0;
	private static Lock lock = new ReentrantLock();
	private static IndexWriter indexer = null;
	private static String indexPath = "data/index";

	public Crawler() {
		Analyzer analyzer = new NorwegianAnalyzer();
		Directory directory;
		try {
			directory = FSDirectory.open(new File(indexPath));
			IndexWriterConfig config = new IndexWriterConfig(Version.LATEST,
					analyzer);
			indexer = new IndexWriter(directory, config);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void visit(Page page) {
		if (this.getTotal() > Crawler.MAX_CRAW) {
			try {
				System.out.println("Finish " + Crawler.MAX_CRAW + " WebPages!");
				if(indexer!=null)indexer.close();
				this.stop();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.increment();
		this.indexPage(page);
		super.visit(page);
	}

	private int increment() {
		try {
			lock.lock();
			total++;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return Crawler.total;
	}

	private void indexPage(Page page) {
		try {
			lock.lock();
			PageIndexer.indexPage(indexer, page);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	private int getTotal() {
		return Crawler.total;
	}

	/* 启动爬虫 */
	public static void main(String[] args) throws IOException {
		Crawler crawler = new Crawler();
		crawler.setCrawl_path("data");
		crawler.setRoot("data/file");
		// crawler.addSeed("http://www.oppland.no/");
		// crawler.addRegex("http://*.oppland.no/.*");
		crawler.addSeed("http://www.zhihu.com/question/21003086");
		crawler.addRegex("http://www.zhihu.com/.*");
		crawler.start(1);
	}
}
