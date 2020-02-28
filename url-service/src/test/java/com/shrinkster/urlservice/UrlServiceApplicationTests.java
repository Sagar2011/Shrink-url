package com.shrinkster.urlservice;

import com.shrinkster.urlservice.model.Url;
import com.shrinkster.urlservice.repository.UrlRepository;
import com.shrinkster.urlservice.service.UrlCountService;
import com.shrinkster.urlservice.service.UrlService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = UrlServiceApplication.class)
@TestPropertySource("/application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UrlServiceApplicationTests {

	private TestRestTemplate restTemplate = new TestRestTemplate();
	private static Url url;
	private static String link;
	@Autowired
	private UrlService urlService;

	@BeforeAll
	public static void userSetup() {
		url = new Url("https://www.google.com", new Date(), "g@test.com");
	}

	@Test
	@Order(1)
	public void PostUrl(){
		link = urlService.postUrl(url);
	}

	@Test
	@Order(2)
	public void getAllUrl(){
		Assertions.assertEquals(1, urlService.getAllUrl().size());
	}

	@Test
	@Order(3)
	public void getUserUrl(){
		Assertions.assertEquals(1, urlService.getUserUrl("g@test.com").size());
	}

	@Test
	@Order(4)
	public void checkTinyUrl(){
		ResponseEntity<String> result = this.restTemplate.getForEntity(url.getUrlLink(), String.class);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}


}
