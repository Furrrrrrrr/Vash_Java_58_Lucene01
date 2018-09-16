package cn.itcast.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class QueryIndex {

    @Test
    public void searchIndex() throws Exception {
//        指定索引库存储磁盘位置
        String path = "E:\\0\\lucene_indexRepostory\\索引库1";
//        创建reader对象，读取索引库索引
        DirectoryReader reader = DirectoryReader.open(FSDirectory.open(new File(path)));
//        创建搜索索引库核心对象
        IndexSearcher indexSearcher = new IndexSearcher(reader);
//        指定搜索关键词
        String name = "条";
//        创建查询解析器，对关键词进行解析分词
        /*
         * 指定lucene版本
         * 指定从哪个域字段中进行搜索
         * 指定搜索是用哪个分词器进行查询关键词分词
         *
         * */
        QueryParser queryParser = new QueryParser("title", new IKAnalyzer());

        /*
         * 使用查询解析器对查询关键词进行分词
         * 返回分伺候的包装类对象
         * */
        Query query = queryParser.parse(name);

        /*
         * 使用搜索核心对象查询索引库
         * 返回值是文档概要信息
         * 1.文档id
         * 2.文档得分
         * 3.文档命中总记录数（符合条件的记录数）
         * 返回结果，返回匹配度最高的 n 条数据
         * 匹配规则：得分高，匹配度高（可手动调节）
         *
         * */
        TopDocs topDocs = indexSearcher.search(query, 10);
        /*获取文档得分*/
        int totalHits = topDocs.totalHits;

        System.out.println(totalHits);

        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        /*循环文档数组，获取文档id，文档得分*/
        for (ScoreDoc scoreDoc : scoreDocs) {

            /*文档得分*/
            float score = scoreDoc.score;

            /*文档id， Browse by document number 从0开始产生的文档id*/
            int docID = scoreDoc.doc;

            Document document = indexSearcher.doc(docID);
            String id = document.get("id");
            String title = document.get("title");
            String desc = document.get("desc");
            String content = document.get("content");



            System.out.println("id - " + id);
            System.out.println("title - " + title);
            System.out.println("desc - " + desc);
            System.out.println("content - " + content);
        }
    }

    @Test
    public void testQuery() throws IOException, ParseException {
        String path = "E:\\0\\lucene_indexRepostory\\索引库2";
        DirectoryReader reader = DirectoryReader.open(FSDirectory.open(new File(path)));
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        String name = "网站";
        QueryParser queryParser = new QueryParser("title", new StandardAnalyzer());
        Query query = queryParser.parse(name);
        TopDocs topDocs = indexSearcher.search(query, 10);
        int totalHits = topDocs.totalHits;
        System.out.println(totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            float score = scoreDoc.score;
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            String id = document.get("id");
            String title = document.get("title");
            String desc = document.get("desc");
            String content = document.get("content");
            System.out.println(id + title + desc + content);
        }

    }

    @Test
    public void testQuery1() throws Exception {
        String path = "E:\\0\\lucene_indexRepostory\\索引库3";
        DirectoryReader reader = DirectoryReader.open(FSDirectory.open(new File(path)));
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        String target = "网站";
        QueryParser queryParser = new QueryParser("title", new StandardAnalyzer());
        Query query = queryParser.parse(target);
        TopDocs topDocs = indexSearcher.search(query, 10);
        int totalHits = topDocs.totalHits;
        System.out.println(totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            float score = scoreDoc.score;
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            String id = document.get("id");
            String title = document.get("title");
            String desc = document.get("desc");
            String content = document.get("content");
            System.out.println("id" + id + title + desc + content);
        }
    }


}
