package com.novamart.changelogs;

import java.io.IOException;
import java.util.List;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import com.novamart.entities.datalistentities.DataListEntity;
import com.novamart.util.DatalistUtil;
import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import io.mongock.api.annotations.RollbackExecution;

@ChangeUnit(id = "InitDatalist", order = "001", author = "harshitgrd@gmail.com")
public class Id001InitDatalist {
	
	private final String collectionName;
	
	public Id001InitDatalist(Environment env) {
		this.collectionName = env.getProperty("application.configuration-data.datalist-collection");
	}
	
	@BeforeExecution
	public void beforeExecution(MongoTemplate mongoTemplate) {
		mongoTemplate.remove(new Query(), collectionName);
	}
	
	@RollbackBeforeExecution
	public void rollbackBeforeExecution() {
		// Nothing to do
	}
	
	@Execution
	public void execution(MongoTemplate mongoTemplate) throws IOException {
		List<DataListEntity> dataListValues = DatalistUtil.getDataList();
		mongoTemplate.insert(dataListValues, collectionName);
	}
	
	@RollbackExecution
	public void rollBackExecution(MongoTemplate mongoTemplate) {
		mongoTemplate.remove(new Query(), collectionName);
	}
}