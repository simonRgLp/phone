package com.example.phone;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.phone.dto.Phone;
import com.example.phone.service.PhoneService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;

@RunWith(SpringRunner.class)
@WebFluxTest
public class PhoneApplicationTests {

	@Autowired
    private WebTestClient webClient;
	
	@MockBean
	private PhoneService phoneService;
	
	@Test
    public void getPhone() throws Exception {
    	
		Phone phone = new Phone(new Long(2), new Long(2), "Iphone 9", "Movil con 6 pulgadas de pantalla, 4 GB RAM, 128 GB ROM", new BigDecimal("950"));
		
		when(phoneService.findById(Mockito.anyLong())).thenReturn(Mono.just(phone));
		
        webClient.get().uri("/v1/phone/{id}", 2).accept(MediaType.APPLICATION_JSON)
        		.exchange()
		        .expectStatus().isOk()
		        .expectBody().json("{\n" + 
		        		"  \"id\": 2,\n" + 
		        		"  \"imageId\": 2,\n" + 
		        		"  \"name\": \"Iphone 9\",\n" + 
		        		"  \"description\": \"Movil con 6 pulgadas de pantalla, 4 GB RAM, 128 GB ROM\",\n" + 
		        		"  \"price\": 950\n" + 
		        		"}");
        
    }
	
	@Test
    public void listPhones() throws Exception {
    	
		Phone phone1 = new Phone(new Long(2), new Long(2), "Iphone 9", "Movil con 6 pulgadas de pantalla, 4 GB RAM, 128 GB ROM", new BigDecimal("950"));
		
		Phone phone2 = new Phone(new Long(3), new Long(3), "Xiaomi MI 5", "Movil con 5 pulgadas de pantalla FullHD, 3 GB RAM, 32 GB ROM", new BigDecimal("500.99"));
		
		when(phoneService.getPhones()).thenReturn(Flux.just(phone1, phone2));
		
        webClient.get().uri("/v1/phone").accept(MediaType.APPLICATION_JSON)
        		.exchange()
		        .expectStatus().isOk()
		        .expectBody().json("[{\n" + 
		        		"  \"id\": 2,\n" + 
		        		"  \"imageId\": 2,\n" + 
		        		"  \"name\": \"Iphone 9\",\n" + 
		        		"  \"description\": \"Movil con 6 pulgadas de pantalla, 4 GB RAM, 128 GB ROM\",\n" + 
		        		"  \"price\": 950\n" + 
		        		"},{" + 
		        		"  \"id\": 3,\n" + 
		        		"  \"imageId\": 3,\n" + 
		        		"  \"name\": \"Xiaomi MI 5\",\n" + 
		        		"  \"description\": \"Movil con 5 pulgadas de pantalla FullHD, 3 GB RAM, 32 GB ROM\",\n" + 
		        		"  \"price\": 500.99\n" + 
		        		"}"
		        		+ "]");
        
    }

}
