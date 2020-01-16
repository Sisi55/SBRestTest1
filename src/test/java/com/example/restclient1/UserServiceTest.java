package com.example.restclient1;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.time.LocalDateTime;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(value=[UserService.class])
public class UserServiceTest extends Specification {

    @Autowired
    private UserService userService;

    @Autowired
    private MockRestServiceServer mockServer;

    @Autowired
    private ObjectMapper objectMapper;

    private String orderApiUrl = "http...";

    def "[Json String 사용] OrderDto 는 OrderAPI의 Json 결과값을 담을 수 있다."(){
        given:
        String expectOrderNo = "1";
        Long expectAmount=1000L;
        LocalDateTime expectOrderDateTime = LocalDateTime.of(2020,1,16,0,0);
        String expectResult = objectMapper.writeValueAsString(new ExpectOrderDto(expectOrderNo, expectAmount, "2018-09-29 00:00:00"));

        mockServer.expect(requestTo(orderApiUrl+expectOrderNo))
                .andRespond(withSuccess(expectResult, MediaType.APPLICATION_JSON));

        when:
        OrderDto response = userService.getUserOrder(expectOrderNo);

        then:
        response.getOrderNo() == expectOrderNo;
        response.getAmount() == expectAmount;
        response.getOrderDateTime() == expectOrderDateTime;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static class ExpectOrderDto{
        private String orderNo;
        private Long amount;
        private String orderDateTime;

    }
}
