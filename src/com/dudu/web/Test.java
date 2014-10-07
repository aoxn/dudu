package com.dudu.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import com.dudu.core.OSearcher;
import com.dudu.core.PageVO;
public class Test {
	
	public static void main(String[] args){
		OSearcher sea = new OSearcher();
		TopDocs docs = sea.getTermQueryPage("oschina", null, null);
		Test.print(docs,sea);
	}
	
	public static void print(TopDocs docs,OSearcher sea){
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
	
	public static String[] printReturn(TopDocs docs,OSearcher sea){
		//System.out.println("总共有" + docs.totalHits + "条记录："); 
		//IndexableField[] strArrayResult=new IndexableField[docs.totalHits];
		String[] strTest=new String[docs.totalHits];
		try {
			int icount=0;
			int tmp = 0;
        	for (ScoreDoc scoreDoc : docs.scoreDocs) { 
        		
                int docNum = scoreDoc.doc;
    			Document doc = sea.getIndexSearcher().doc(docNum);
    			strTest[icount] = doc.getField("contents").stringValue();
    			tmp = strTest[icount].length();
    			if(tmp > 40){
    				strTest[icount]=strTest[icount].substring(0,40)+" ... ";
    			}
    			icount++;
            }	
        } catch (IOException e) {
			e.printStackTrace();
		}
		return strTest;
	}
	public static List<PageVO> getReturn(TopDocs docs,OSearcher sea){
		//System.out.println("总共有" + docs.totalHits + "条记录："); 
		//IndexableField[] strArrayResult=new IndexableField[docs.totalHits];
		List<PageVO> list=new ArrayList<PageVO>();
		try {
			int tmp = 0;
			String str="";
        	for (ScoreDoc scoreDoc : docs.scoreDocs) { 
        		PageVO p = new PageVO();
                int docNum = scoreDoc.doc;
    			Document doc = sea.getIndexSearcher().doc(docNum);
    			str = doc.getField("contents").stringValue();
    			tmp = str.length();
//    			if(tmp > 40){
//    				str=str.substring(0,200)+" ... ";
//    			}
    			p.setTitle(doc.getField("title").stringValue());
    			p.setUrl(doc.getField("site").stringValue());
    			p.setContent(str);
    			list.add(p);
            }	
        } catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
