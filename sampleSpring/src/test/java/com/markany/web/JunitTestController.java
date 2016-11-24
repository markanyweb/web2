package com.markany.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.markany.dao.SampleDao;
import com.markany.util.DBConnector;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
							        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
							        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
							      })
public class JunitTestController {
	
	@Before
	public void DB연결(){
		
		System.out.println("JUnit Test start");
		DBConnector.getConnection();
		
	}
	@Test
	public void 한글테스트(){
		String keyString = "2";
		DBConnector.getConnection();
		SampleDao sampleDao = new SampleDao();
		System.out.println("result : " + sampleDao.selectDataResult(keyString));
	}
	@After
	public void DB연결종료(){
		
		DBConnector.closeConnection();
		System.out.println("JUnit Test end");
	}
	
	
	
}
