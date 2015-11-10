package pl.openpkw.dokument.generator.velocityengine;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.apache.velocity.VelocityContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import pl.openpkw.dokument.generator.VelocityEngine;

public class When_input_contains_ampersand_character {

    @Test
    public void should_not_fail_interpreting_it_as_HTML_entity() throws Exception {
        VelocityEngine velocity = new VelocityEngine();
        velocity.initialize();
        String json = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/sample-input-with-ampersand.json").toURI())));
        Map<String, Object> jsonData = new ObjectMapper().readValue(json, HashMap.class);
        VelocityContext context = new VelocityContext();
        context.put("form", jsonData.get("formData"));

        String result = velocity.process("/templates/2015_parliament_periphery.html", context);
        
        Assert.assertEquals(false, result.contains("hopsa&sasa"));
        Assert.assertEquals(true, result.contains("hopsa&amp;sasa"));
    }
}