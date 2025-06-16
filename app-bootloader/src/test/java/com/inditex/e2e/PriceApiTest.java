package com.inditex.e2e;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestPropertySource("classpath:application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PriceApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetPriceForRequestedDate_case1() throws Exception {
        //Given
        String productId = "35455";
        String brandId = "1";
        String requestDate = "2020-06-14T10:00:00.0Z";

        //When
        ResultActions resultActions = mockMvc.perform(get("/price")
                .param("productId", productId)
                .param("brandId", brandId)
                .param("requestDate", requestDate)
                .contentType(MediaType.APPLICATION_JSON));

        //Then
        resultActions.andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("brandId", is(1)))
                .andExpect(jsonPath("productId", is(35455)))
                .andExpect(jsonPath("rate", is(1)))
                .andExpect(jsonPath("startDate", is("2020-06-14T00:00:00Z")))
                .andExpect(jsonPath("endDate", is("2020-12-31T23:59:59Z")))
                .andExpect(jsonPath("price", is(35.50)))
                .andExpect(jsonPath("currency", is("EUR")));
    }

    @Test
    void testGetPriceForRequestedDate_case2() throws Exception {
        //Given
        String productId = "35455";
        String brandId = "1";
        String requestDate = "2020-06-14T16:00:00.0Z";

        //When
        ResultActions resultActions = mockMvc.perform(get("/price")
                .param("productId", productId)
                .param("brandId", brandId)
                .param("requestDate", requestDate)
                .contentType(MediaType.APPLICATION_JSON));
        //Then
        resultActions.andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("brandId", is(1)))
                .andExpect(jsonPath("productId", is(35455)))
                .andExpect(jsonPath("rate", is(2)))
                .andExpect(jsonPath("startDate", is("2020-06-14T15:00:00Z")))
                .andExpect(jsonPath("endDate", is("2020-06-14T18:30:00Z")))
                .andExpect(jsonPath("price", is(25.45)))
                .andExpect(jsonPath("currency", is("EUR")));
    }

    @Test
    void testGetPriceForRequestedDate_case3() throws Exception {
        //Given
        String productId = "35455";
        String brandId = "1";
        String requestDate = "2020-06-14T21:00:00.0Z";

        //When
        ResultActions resultActions = mockMvc.perform(get("/price")
                .param("productId", productId)
                .param("brandId", brandId)
                .param("requestDate", requestDate)
                .contentType(MediaType.APPLICATION_JSON));
        //Then
        resultActions.andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("brandId", is(1)))
                .andExpect(jsonPath("productId", is(35455)))
                .andExpect(jsonPath("rate", is(1)))
                .andExpect(jsonPath("startDate", is("2020-06-14T00:00:00Z")))
                .andExpect(jsonPath("endDate", is("2020-12-31T23:59:59Z")))
                .andExpect(jsonPath("price", is(35.50)))
                .andExpect(jsonPath("currency", is("EUR")));
    }

    @Test
    void testGetPriceForRequestedDate_case4() throws Exception {
        //Given
        String productId = "35455";
        String brandId = "1";
        String requestDate = "2020-06-15T10:00:00.0Z";

        //When
        ResultActions resultActions = mockMvc.perform(get("/price")
                .param("productId", productId)
                .param("brandId", brandId)
                .param("requestDate", requestDate)
                .contentType(MediaType.APPLICATION_JSON));
        //Then
        resultActions.andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("brandId", is(1)))
                .andExpect(jsonPath("productId", is(35455)))
                .andExpect(jsonPath("rate", is(3)))
                .andExpect(jsonPath("startDate", is("2020-06-15T00:00:00Z")))
                .andExpect(jsonPath("endDate", is("2020-06-15T11:00:00Z")))
                .andExpect(jsonPath("price", is(30.50)))
                .andExpect(jsonPath("currency", is("EUR")));
    }

    @Test
    void testGetPriceForRequestedDate_case5() throws Exception {
        //Given
        String productId = "35455";
        String brandId = "1";
        String requestDate = "2020-06-15T21:00:00.0Z";

        //When
        ResultActions resultActions = mockMvc.perform(get("/price")
                .param("productId", productId)
                .param("brandId", brandId)
                .param("requestDate", requestDate)
                .contentType(MediaType.APPLICATION_JSON));
        //Then
        resultActions.andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("brandId", is(1)))
                .andExpect(jsonPath("productId", is(35455)))
                .andExpect(jsonPath("rate", is(4)))
                .andExpect(jsonPath("startDate", is("2020-06-15T16:00:00Z")))
                .andExpect(jsonPath("endDate", is("2020-12-31T23:59:59Z")))
                .andExpect(jsonPath("price", is(38.95)))
                .andExpect(jsonPath("currency", is("EUR")));
    }


    @Test
    void testGetPriceNotFound() throws Exception {
        //Given
        String productId = "35455";
        String brandId = "1";
        String requestDate = "2024-06-15T21:00:00.0Z";

        //When
        ResultActions resultActions = mockMvc.perform(get("/price")
                .param("productId", productId)
                .param("brandId", brandId)
                .param("requestDate", requestDate)
                .contentType(MediaType.APPLICATION_JSON));

        //Then
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    void testGetPriceMissingParams() throws Exception {
        //Given
        String productId = "35455";
        String requestDate = "2024-06-15T21:00:00.0Z";

        //When
        ResultActions resultActions = mockMvc.perform(get("/price")
                .param("product", productId)
                .param("requestDate", requestDate)
                .contentType(MediaType.APPLICATION_JSON));

        //Then
        resultActions.andExpect(status().isBadRequest());
    }

}
