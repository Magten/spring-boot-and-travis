package com.magten.web;

import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.magten.domain.Hunter;
import com.magten.repository.HunterRespositroy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HunterControllerTest {

	private MockMvc mock;

	@Autowired
	private HunterController hunterController;

	@Autowired
	private HunterRespositroy hunterRespositroy;

	@Before
	public void setup() {
		mock = MockMvcBuilders.standaloneSetup(hunterController).build();
	}

	@Test
	public void testAdd() throws Exception {
		String username = "TesterAdd";
		Assert.assertNull(hunterRespositroy.findByUsername(username));
		mock.perform(MockMvcRequestBuilders.get("/hunter/add?username=" + username))
				.andDo(MockMvcResultHandlers.print());
		Assert.assertNotNull(hunterRespositroy.findByUsername(username));
	}

	@Test
	public void testRemove() throws Exception {
		String username = "TesterRemove";
		hunterRespositroy.save(new Hunter(username));
		Assert.assertNotNull(hunterRespositroy.findByUsername(username));
		mock.perform(MockMvcRequestBuilders.get("/hunter/remove?username=" + username))
				.andDo(MockMvcResultHandlers.print());
		Assert.assertNull(hunterRespositroy.findByUsername(username));
	}

	@Test
	public void testFind() throws Exception {
		String username = "TesterFind";
		hunterRespositroy.save(new Hunter(username));
		MvcResult result = mock.perform(MockMvcRequestBuilders.get("/hunter/find?username=" + username))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		Assert.assertTrue(result.getResponse().getContentAsByteArray().length > 0);
	}

	@Test
	public void testGetAll() throws Exception {
		MvcResult result = mock.perform(MockMvcRequestBuilders.get("/hunter/all")).andDo(MockMvcResultHandlers.print())
				.andReturn();
		Assert.assertTrue(new JSONArray(new String(result.getResponse().getContentAsByteArray()))
				.length() == hunterRespositroy.count());
	}
}
