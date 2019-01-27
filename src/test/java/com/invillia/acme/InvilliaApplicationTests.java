package com.invillia.acme;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invillia.acme.domain.Store;
import com.invillia.acme.service.IStoreService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvilliaApplicationTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private IStoreService storeService;

    @Autowired
    ObjectMapper objectMapper;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testHome() throws Exception {

        String URL1 = "/";

        System.out.println(this.mvc.perform(get(URL1)).andDo(print()));

        this.mvc.perform(get(URL1)).andExpect(status().isOk())
                .andExpect(content().string(containsString("stores")));
    }

    @Test
    public void createStore() throws Exception {
        Store store = new Store();
        store.setName("My new name");
        store.setAddress("My new address");

        mvc.perform(post("/store/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(store)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name", is("My new name")))
                .andExpect(jsonPath("id", notNullValue()));
    }

    @Test
    public void findStoreById() throws Exception {

        String URL2 = "/store/id/1";

        System.out.println(this.mvc.perform(get(URL2)).andDo(print()));

        this.mvc.perform(
                get(URL2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo("Dona Benta Fast Food")));
    }

    @Test
    public void getListStoreByName() throws Exception {

        String URL3 = "/store/name/0/Dona Benta Fast Food/1/asc/id";

        System.out.println(this.mvc.perform(get(URL3)).andDo(print()));

        this.mvc.perform(
                get(URL3))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", equalTo("Dona Benta Fast Food")));
    }

    @Test
    public void getListStoreByAdress() throws Exception {

        String URL4 = "/store/address/0/Rua das Comidas/1/asc/id";

        System.out.println(this.mvc.perform(get(URL4)).andDo(print()));

        this.mvc.perform(
                get(URL4))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", equalTo("Dona Benta Fast Food")));
    }

    @Test
    public void findByOrderId() throws Exception {

        String URL1 = "/order/id/1";

        System.out.println(this.mvc.perform(get(URL1)).andDo(print()));

        this.mvc.perform(
                get(URL1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("address", equalTo("Rua dos Famintos")));
    }
}
