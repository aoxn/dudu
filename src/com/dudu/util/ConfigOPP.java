package com.dudu.util;

import org.apache.lucene.util.Version;

public class ConfigOPP {
	public static int MAX_CRAW = 3000;	//爬行多少网页后停止
	public static Version LUCENE_VERSION = Version.LUCENE_4_10_0;
	public static String INDEX_PATH = "data/index";	//索引目录
	public static String TOMCAT_INDEX_PATH = "/Users/spacex/work/java_work/oppsearch/data/index";
	public static String DATA_ROOT  = "data/file";	//下载的页面文件存储目录
	public static String CRAWLER_ROOT = "data/craw";	//爬虫爬行过程目录
}
