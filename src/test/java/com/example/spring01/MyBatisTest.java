package com.example.spring01;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//JUnit 4.0으로 현재클래스를 실행시킴
@RunWith(SpringJUnit4ClassRunner.class)

// mybatis에서 참조하는 설정파일의 위치를 알려줌
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })

public class MyBatisTest {

	// 로깅 처리를 위한 코드
	private static final Logger logger = LoggerFactory.getLogger(MyBatisTest.class);

	@Inject // 의존관계 주입(스프링에서 객체를 생성하여 전달), inject == AutoWired
	private SqlSessionFactory sqlFactory;

	@Test // JUnit이 테스트하는 코드
	public void testFactory() {
		// System.out.println("\"sqlFactory : \" + sqlFactory");
		logger.info("sqlFactory : " + sqlFactory);
	}
	
	@Test
	public void testSession() {
		try (SqlSession sqlSession = sqlFactory.openSession()) {
			logger.info("sqlSession : " + sqlSession);
			logger.info("mybatis 연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
