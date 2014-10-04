package com.dudu.web;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import com.dudu.core.OSearcher;

public class Test {
	
	public static void main(String[] args){
		OSearcher sea = new OSearcher();
		TopDocs docs = sea.getMultiQueryPage("java", null, null);
		System.out.println("总共有" + docs.totalHits + "条记录："); 
        try {
        	for (ScoreDoc scoreDoc : docs.scoreDocs) {  
                int docNum = scoreDoc.doc;
    			Document doc = sea.getIndexSearcher().doc(docNum);
    			System.out.println(doc.getField("contents"));
            }	
        } catch (IOException e) {
			e.printStackTrace();
		}
         
	}
}
