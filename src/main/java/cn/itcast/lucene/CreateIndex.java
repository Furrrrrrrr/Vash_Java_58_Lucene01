package cn.itcast.lucene;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class CreateIndex {


    @Test
    public void testLucene() throws Exception {
//          指定索引库存储磁盘位置
        String path = "E:\\0\\lucene_indexRepostory\\索引库1";
//          创建目录对象，关联索引库存储路径
        FSDirectory directory = FSDirectory.open(new File(path));


        // Analyzer analyzer = new StandardAnalyzer();
        // Analyzer analyzer = new CJKAnalyzer();
        Analyzer analyzer = new SmartChineseAnalyzer();
        /*  创建写索引库核心对象配置文件
         * 指定使用Lucene版本 Version.LUCENE_4_10_3
         * 指定使用分词器 analyzer
         * */

        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);

        /*创建写索引库核心对象*/
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

        /*创建文档对象，导入lucene类*/
        Document document = new Document();

        document.add(new StringField("id", "321321", Field.Store.NO));
        document.add(new TextField("title", "返回结果，返回匹配度最高的10条数据", Field.Store.YES));
        document.add(new TextField("desc", "apache开源全文检索引擎工具包，，应用程式接口，之前两技术不是java语言开发的api，lucene不是现成的搜索引擎产品，可以用来制作搜索引擎产品。", Field.Store.YES));
        document.add(new TextField("content", "基本分词器算法：把一个汉字作为一个单词\n" +
                "搜索关键字，将关键字进行分词（分词算法一致，相同的分词器），进行匹配，  查询得到文档对象，页面展示title和desc（展示，所以存储，或者参与搜索存储，不参与搜索不存）", Field.Store.NO));

        indexWriter.addDocument(document);
        indexWriter.commit();
    }

    @Test
    public void textLucene() throws IOException {
//        索引库存储路径
        String path = "E:\\0\\lucene_indexRepostory\\索引库2";
//        目录对象
        FSDirectory directory = FSDirectory.open(new File(path));
//        分词器
        Analyzer analyzer = new IKAnalyzer();
//        写索引库 核心对象配置文件
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
//        写索引库 核心对象
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
//        创建文档对象，导入lucene类
        Document document = new Document();
//
        document.add(new StringField("id", "328901839021", Field.Store.NO));
        document.add(new TextField("title", "起点中文小说网-阅文集团旗下网站", Field.Store.YES));
        document.add(new TextField("desc", "小说阅读,精彩小说尽在起点中文网. 起点中文网提供玄幻小说,武侠小说,原创小说,网游小说,都市小说,言情小说,青春小说,历史小说,军事小说,网游小说,科幻小说,恐怖小说", Field.Store.YES));
        document.add(new TextField("content", "说好的末世呢“小说”一词最早出现于《庄子·外物》：「饰小说以干县令，其于大达亦远矣。」庄子所谓的「小说」，是指琐碎的言论，与今日小说观念相差甚远。直至东汉桓谭《新论》：「小说家合残丛小语，近取譬喻，以作短书，治身理家，有可观之辞。」班固《汉书．艺文志》将「小说家」列为十家之后，其下的定义为：「小说家者流，盖出于稗官，街谈巷语，道听途说[4]者之所造也。」才稍与今日小说的意义相近。而中国小说最大的特色，便自宋代开始具有文言小说与白话小说两种不同的小说系统。文言小说起源于先秦的街谈巷语，是一种小知小道的纪录。在历经魏晋南北朝及隋唐长期的发展，无论是题材或人物的描写，文言小说都有明显的进步，形成笔记与传奇两种小说类型。而白话小说则起源于唐宋时期说话人的话本，故事的取材来自民间，主要表现了百姓的生活及思想意识。但不管文言小说或白话小说都源远流长，呈现各自不同的艺术特色。", Field.Store.NO));
        indexWriter.addDocument(document);
        indexWriter.commit();
    }

    @Test
    public void textLucene1() throws IOException {
        String path = "E:\\0\\lucene_indexRepostory\\索引库3";
        FSDirectory directory = FSDirectory.open(new File(path));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        Document document = new Document();
        document.add(new StringField("id", "3782917", Field.Store.NO));
        document.add(new TextField("title", "阅文集团旗下网站", Field.Store.YES));
        document.add(new TextField("desc", "小说阅读,精彩小说尽在起点中文网. 起点中文网提供玄幻小说,武侠小说,原创小说,网游小说,都市小说,言情小说,青春小说,历史小说,军事小说,网游小说,科幻小说,恐怖小说", Field.Store.YES));
        document.add(new TextField("content", "说好的末世呢“小说”一词最早出现于《庄子·外物》：「饰小说以干县令，其于大达亦远矣。」庄子所谓的「小说」，是指琐碎的言论，与今日小说观念相差甚远。直至东汉桓谭《新论》：「小说家合残丛小语，近取譬喻，以作短书，治身理家，有可观之辞。」班固《汉书．艺文志》将「小说家」列为十家之后，其下的定义为：「小说家者流，盖出于稗官，街谈巷语，道听途说[4]者之所造也。」才稍与今日小说的意义相近。而中国小说最大的特色，便自宋代开始具有文言小说与白话小说两种不同的小说系统。文言小说起源于先秦的街谈巷语，是一种小知小道的纪录。在历经魏晋南北朝及隋唐长期的发展，无论是题材或人物的描写，文言小说都有明显的进步，形成笔记与传奇两种小说类型。而白话小说则起源于唐宋时期说话人的话本，故事的取材来自民间，主要表现了百姓的生活及思想意识。但不管文言小说或白话小说都源远流长，呈现各自不同的艺术特色", Field.Store.NO));
        indexWriter.addDocument(document);
        indexWriter.commit();
    }

}
