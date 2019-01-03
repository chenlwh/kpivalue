package com.ygsoft.kpiviewer.util;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElasticSearchUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchUtil.class);

	private static TransportClient client = null;

	static {
		if (client == null) {
			init();
		}
	}

	public static TransportClient get() {
		if (client == null) {
			init();
		}
		return client;
	}

	public static void close() {
		if (client != null) {
			client.close();
		}
	}

	public static void init() {
		// 设置集群名称
		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
		// 创建client
		try {
			client = new PreBuiltTransportClient(settings);
			client.addTransportAddress(new TransportAddress(InetAddress.getByName("10.122.4.73"), 9300));

		} catch (Exception e) {
			LOGGER.error(String.format("init search client err:=>msg:[%s]", e.getMessage()), e);
			if (client != null) {
				client.close();
			}
		}

	}
	
    public static void save(String index, String type, String id, String json) {
        IndexRequestBuilder builder = client.prepareIndex(index, type, id);
        IndexResponse response = builder
        		.setSource(json, XContentType.JSON)
                .execute()
                .actionGet();
        LOGGER.info("save index:=>index:{}, type:{}, id:{}, data:{}, rsp:{}", index, type, id, json, response);
    }
 
    public static void delete(String index, String type, String id) {
        DeleteRequestBuilder builder = client.prepareDelete(index, type, id);
        DeleteResponse response = builder.execute().actionGet();
        LOGGER.info("delete index:=>index:{}, type:{}, id:{}, rsp:{}", index, type, id, response);
    }
    
    public static void deleteByQuery(String index, QueryBuilder filter) {
    	BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
//    		    .filter(QueryBuilders.matchQuery("gender", "male"))
    			.filter(filter)
    		    .source(index)                                  
    		    .get();                                             
    	response.getDeleted();  
    }
 
    public static Map<String, Object> get(String index, String type, String id) {
        GetRequestBuilder builder = client.prepareGet(index, type, id);
        GetResponse response = builder.execute().actionGet();
        
        LOGGER.info("get index:=>index:{}, type:{}, id:{}, rsp:{}", index, type, id, response.getSource());
        
        return response.isExists() ? response.getSource() : null;
    }
 
    public static void upsert(String index, String type, String id, Map<String, Object> oldData, Map<String,Object> newData) {
    	IndexRequest indexRequest = new IndexRequest(index, type, id).source(newData);
    	UpdateRequest updateRequest = new UpdateRequest(index, type, id)
    	        .doc(oldData).upsert(indexRequest);              
    	try {
			client.update(updateRequest).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    public static boolean isIndexExists(String index) {
    	IndicesExistsRequest inExistsRequest = new IndicesExistsRequest(index);

    	IndicesExistsResponse inExistsResponse = client.admin().indices()
    	                    .exists(inExistsRequest).actionGet();
    	
    	return inExistsResponse.isExists();
    }
    
	// 创建索引库
	public static void createIndex(String index) {
		if (isIndexExists(index)) {
			LOGGER.info("Index  " + index + " already exits!");
		} else {
			CreateIndexRequest cIndexRequest = new CreateIndexRequest(index);
			CreateIndexResponse cIndexResponse = client.admin().indices().create(cIndexRequest).actionGet();
			if (cIndexResponse.isAcknowledged()) {
				LOGGER.info("create index successfully！");
			} else {
				LOGGER.info("Fail to create index!");
			}
		}
	}
	
    public static void deleteIndex(String index) {
    	if(isIndexExists(index)) {
    		DeleteIndexResponse dResponse = client.admin().indices().prepareDelete(index)
                    .execute().actionGet();
    		LOGGER.info("delete index:=>index:{}, rsp:{}", index, dResponse);
    	}   	
       
    }
    
    public static List<Map<String,Object>> matchAllQuery(String index, String type, String orderField, SortOrder sortOrder) {
    	MatchAllQueryBuilder builder = QueryBuilders.matchAllQuery();
    	return query(index, type, builder, orderField, sortOrder);
    }
    
    public static List<Map<String,Object>> termQuery(String index, String type,TermQueryBuilder builder, String orderField, SortOrder sortOrder) {
    	return query(index, type, builder, orderField, sortOrder);
    }
    
    public static List<Map<String,Object>> groupQuery(String index, String type,TermQueryBuilder builder) {
    	String termName = "daily";
    	SearchRequestBuilder searchBuilder = client.prepareSearch(index)
    			.setTypes(type)
    			.setQuery(builder)
    			.addAggregation(AggregationBuilders.terms(termName).field("insertDay").order(BucketOrder.key(true))
						.subAggregation(AggregationBuilders.max("dailyLoginNum").field("dailyLoginNum"))
						.subAggregation(AggregationBuilders.max("loginNum").field("loginNum"))
						.subAggregation(AggregationBuilders.max("sessionNum").field("sessionNum"))
    					)
    			.setSize(10000)
    			.setSearchType(SearchType.QUERY_THEN_FETCH);

    	SearchResponse searchResponse = searchBuilder.get();
    	
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	Aggregations aggregations = searchResponse.getAggregations();
    	Terms term = aggregations.get(termName);
    	Max dailyLoginNum = null;
    	Max loginNum = null;
    	Max sessionNum = null;
    	for(Terms.Bucket bucket : term.getBuckets()) {
    		dailyLoginNum = bucket.getAggregations().get("dailyLoginNum");
    		loginNum = bucket.getAggregations().get("loginNum");
    		sessionNum = bucket.getAggregations().get("sessionNum");
    		String insertDay = bucket.getKeyAsString().substring(0, 10);
    		
    		Map<String,Object> map = new HashMap<String,Object>();
    		Double dailyLoginNumValue = dailyLoginNum.getValue();
    		Double loginNumValue = loginNum.getValue();
    		Double sessionNumValue = sessionNum.getValue();
    		map.put("dailyLoginNum", dailyLoginNumValue);
    		map.put("loginNum", loginNumValue);
    		map.put("sessionNum", sessionNumValue);
    		map.put("insertDate", insertDay);
    		
    		list.add(map);
    	}
    	return list;
    }
    
    private static List<Map<String,Object>> query(String index, String type,QueryBuilder builder, String orderField, SortOrder sortOrder) {
    	SearchRequestBuilder searchBuilder = client.prepareSearch(index)
    			.setTypes(type)
    			.setQuery(builder)
    			.setSize(10000)
    			.setSearchType(SearchType.QUERY_THEN_FETCH);
    	if(orderField!=null) {
    		FieldSortBuilder sortBuilder = SortBuilders.fieldSort(orderField).order(sortOrder);
    		searchBuilder.addSort(sortBuilder);
    	}
    	SearchResponse searchResponse = searchBuilder.get();
    	SearchHits hits = searchResponse.getHits();
    	
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	for(SearchHit hit : hits) {
    		list.add(hit.getSourceAsMap());
    	}
        
    	return list;
    }
//    
//    public static void main(String[] args) {
//		ElasticSearchUtil.deleteIndex("server_kpi_value");
//	}
	
	
}
