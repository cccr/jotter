package com.halfofpoint.jotter.dbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping
public class Controller {

    @Autowired
    ApplicationContext ctx;

    @RequestMapping(value = "/{environment}", method = RequestMethod.GET)
    public String returnAuth(@PathVariable String environment) throws Exception{
        if (Arrays.binarySearch(ctx.getBeanNamesForType(AuthConfig.class), environment) == -1)
            throw new Exception("Invalid environment " + environment);
        AuthConfig bean = (AuthConfig) ctx.getBean(environment);
        return bean.getUserName();
    }
}
