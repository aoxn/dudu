package com.dudu.core;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import com.dudu.util.ConfigOPP;

public class OSearcher {
	//IndexSearcher是一个线程安全的对象，此处不用考虑并发
	IndexSearcher searcher = null;
	IndexReader reader     = null;
	Analyzer analyzer 	   = new StandardAnalyzer(ConfigOPP.LUCENE_VERSION);
	public OSearcher(){
		init();
	}
	public void init(){
		try {
			reader = DirectoryReader.open(FSDirectory.open(new File(ConfigOPP.TOMCAT_INDEX_PATH)));
			searcher = new IndexSearcher(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void closeSearcher(){
		try {
			if(reader!=null)reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private Analyzer getAnalyzer(Analyzer analy){
		if(analy == null) 
			return this.analyzer;
		return analy;
	}
	public IndexSearcher getIndexSearcher(){
		return this.searcher;
	}
	/**
	 * MultiQuery search函数 
	 * @param index
	 * @param line
	 * @return
	 */
	public TopDocs getMultiQueryPage(String line,String[] fields,Analyzer analyzer) {
		TopDocs top = null;
		if(fields == null) fields = new String[]{"contents"};
		MultiFieldQueryParser parser = new MultiFieldQueryParser(fields,getAnalyzer(analyzer));
		try {
			Query qu = parser.parse(line);
			top = searcher.search(qu, 100);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return top;
	}
	/**
	 * TermQuery search函数
	 * @param line
	 * @param fields
	 * @param analyzer
	 * @return
	 */
	public TopDocs getTermQueryPage(String line,String[] fields,Analyzer analyzer) {
		TopDocs top = null;
        QueryParser parser = new QueryParser("contents", getAnalyzer(analyzer));  
        try {
			Query q = parser.parse(line);
			top 	= searcher.search(q, 100);
//			Hits hits = searcher.search(q);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return top;
	}
	

}
