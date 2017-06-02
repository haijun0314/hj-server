package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.jxjz.common.util.HttpUtil;
import org.jxjz.common.util.JsonUtil;
import org.jxjz.common.util.MapUtil;

import com.hjf.core.bean.BaseRespBean;

public class SolrTest {
	
	//solr 服务器地址  
    public static final String solrServerUrl = "http://localhost:8080/solr";  
    //solrhome下的core  
    public static final String solrCroeHome = "my_solr";  
    //待索引、查询字段  
    public static void main(String[] args) throws Exception {  
    	save();
    	search(); 
    }  
    
    //待索引、查询字段  
    public static void save() {  
    	String  data=HttpUtil.httpGet("http://localhost:8081/note?noteList");
    	Map r=(Map) JsonUtil.json2Obj(data, Map.class);
    	List datas=(List) r.get("datas");
        SolrClient client = getSolrClient();  
        List<SolrInputDocument> solrDocs = new ArrayList<SolrInputDocument>();  
        for (int i=0;i<datas.size();i++) {  
            SolrInputDocument doc = new SolrInputDocument();  
            Map m=(Map)datas.get(i);
            doc.addField("id", MapUtils.getInteger(m, "noteId"));  
            doc.addField("content", MapUtils.getString(m, "content"));  
            solrDocs.add(doc);  
        }  
        try {  
            client.add(solrDocs);  
            client.commit();  
        } catch (SolrServerException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
    }    
    
    
    
    
    
    public static void search()throws Exception{
        SolrClient client = getSolrClient();
        
        //创建查询对象
        SolrQuery query = new SolrQuery();
        //q 查询字符串，如果查询所有*:*
        query.set("q", "*:*");
        //fq 过滤条件，过滤是基于查询结果中的过滤
        //query.set("fq", "product_catalog_name:幽默杂货");
        //sort 排序，请注意，如果一个字段没有被索引，那么它是无法排序的
//        query.set("sort", "product_price desc");
        //start row 分页信息，与mysql的limit的两个参数一致效果
        query.setStart(0);
        query.setRows(10);
        //fl 查询哪些结果出来，不写的话，就查询全部，所以我这里就不写了
//        query.set("fl", "");
        //df 默认搜索的域
        query.set("df", "product_keywords");
        
        //======高亮设置===
        //开启高亮
       // query.setHighlight(true);
        //高亮域
       // query.addHighlightField("product_name");
        //前缀
        //query.setHighlightSimplePre("<span style='color:red'>");
        //后缀
        //query.setHighlightSimplePost("</span>");
        
        
        //执行搜索
        QueryResponse queryResponse = client.query(query);
        //搜索结果
        SolrDocumentList results = queryResponse.getResults();
        //查询出来的数量
        long numFound = results.getNumFound();
        System.out.println("总查询出:" + numFound + "条记录");
        
        //遍历搜索记录
        //获取高亮信息
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        for (SolrDocument solrDocument : results) {
            System.out.println("商品id:" + solrDocument.get("id"));
            System.out.println("商品名称 :" + solrDocument.get("content"));
        }
        client.close();
    }   
    
    public static SolrClient getSolrClient(){  
        return new HttpSolrClient(solrServerUrl+"/"+solrCroeHome);  
    }  
}
