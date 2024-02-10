package com.gen.ai.repository;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metlife.ace.automation.annotation.LazyAutowired;
import com.metlife.ace.automation.model.AnnuitantDynamicDetails;
import com.metlife.ace.automation.model.AnnuitantInPaywithBene;
import com.metlife.ace.automation.model.AnnuitantInPaywithJointAnnuitant;
import com.metlife.ace.automation.repository.QueryExecutor;

@Repository
public class AnnuitantDynamicDetailsRepository {
	@LazyAutowired
	private QueryExecutor queryExecutor;

	public List<AnnuitantDynamicDetails> getAnnuitantDyanmicDetails(String sqlQuery, List parametersList) {
		List<AnnuitantDynamicDetails> AnnuitantDynamicDetailsList = new ArrayList();
		List<Object> objectList = queryExecutor.executeNativeSqlQueryResultSet(sqlQuery, parametersList);
		for (Object queryObject : objectList) {
			AnnuitantDynamicDetailsList.add(new AnnuitantDynamicDetails((Object[]) queryObject));
		}
		return AnnuitantDynamicDetailsList;
	}
	

    public List<AnnuitantDynamicDetails> getAnnuitantDyanmicDetails(String sqlQuery){
        List<AnnuitantDynamicDetails> AnnuitantDynamicDetailsList=new ArrayList<>();
        List<Object> objectList=queryExecutor.executeNativeSqlQueryResultSet(sqlQuery);
        for(Object queryObject:objectList){
        	AnnuitantDynamicDetailsList.add(new AnnuitantDynamicDetails((Object[]) queryObject));
        }
        return AnnuitantDynamicDetailsList;
    }
}
	



