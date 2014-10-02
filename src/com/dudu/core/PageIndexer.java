package com.dudu.core;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;

import cn.edu.hfut.dmic.webcollector.model.Page;

public class PageIndexer {
	
	public static void indexPage(IndexWriter iwriter,Page page){
		Document doc = new Document();
	    String text = page.doc.text();
	    doc.add(new StringField("doc", text, StringField.Store.YES));
	    try {
			iwriter.addDocument(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
