package com.markany.serviceImp;


import com.markany.dao.SampleDao;
import com.markany.util.DBConnector;



public class SampleServiceImp {

	public String selectDataResult(String keyString) {
		DBConnector.getConnection();
		SampleDao sampleDao = new SampleDao();
		
		
		
		return sampleDao.selectDataResult(keyString);
	}

	
	

}
