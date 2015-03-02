import com.halfofpoint.jotter.dbc.Application;
import com.halfofpoint.jotter.dbc.Controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { Application.class })
public class ControllerTest {

    @Autowired
    private Controller controller;

    private HandlerExceptionResolver exceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

        Properties exceptionMappings = new Properties();

        exceptionMappings.put("java.lang.Exception", "error/error");
        exceptionMappings.put("java.lang.RuntimeException", "error/error");

        exceptionResolver.setExceptionMappings(exceptionMappings);

        Properties statusCodes = new Properties();
        statusCodes.put("error/error", "500");

        exceptionResolver.setStatusCodes(statusCodes);

        return exceptionResolver;
    }

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setHandlerExceptionResolvers(exceptionResolver())
                .build();
    }

    @Test
    public void getExists() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/prod")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        assert contentAsString.equals("prod");
    }

    @Test
    public void getException() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/absolutelyUnreal")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andDo(print())
                .andExpect(status().is(500))
                .andReturn();

        String contentAsString = mvcResult.getModelAndView().getModel().get("exception").toString();
        assert contentAsString.contains("java.lang.Exception: Invalid environment absolutelyUnreal");
    }
}
