package com.weixin.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.weixin.infra.Config;
import com.weixin.web.controller.shop.BindingController;

public class BindingControllerStandaloneSetupTest {
	
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		BindingController bindingController = new BindingController();
		mockMvc = MockMvcBuilders.standaloneSetup(bindingController).build();
	}
	
	@Test  
	public void testView() throws Exception {  
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/binding/{{openid}}",1234)) 
	            .andExpect(MockMvcResultMatchers.view().name(Config.VIEWS_SHOP + "register")).andReturn();
	            //.andExpect(MockMvcResultMatchers.model().attributeExists("user"))  
	            //.andDo(MockMvcResultHandlers.print())  
	           // .andReturn();  
	      
	   // Assert.assertNotNull(result.getModelAndView().getModel().get("user"));  
	}  
}
