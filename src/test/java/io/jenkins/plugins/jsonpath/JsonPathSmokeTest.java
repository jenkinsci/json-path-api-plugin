package io.jenkins.plugins.jsonpath;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;

public class JsonPathSmokeTest {

    public static class Person {
        public String name;
        public int age;
        public String city;
    }

    @Test
    public void smokeTest() throws Exception {
        String json = "{\"name\":\"John\", \"age\":30, \"city\":\"New York\"}";

        String name = JsonPath.read(json, "$.name");
        int age = JsonPath.read(json, "$.age");
        String city = JsonPath.read(json, "$.city");

        assertEquals("John", name);
        assertEquals(30, age);
        assertEquals("New York", city);

        Person person =
                JsonPath.using(Configuration.defaultConfiguration()).parse(json).read("$", Person.class);
        assertEquals("John", person.name);
        assertEquals(30, person.age);
        assertEquals("New York", person.city);
    }
}
