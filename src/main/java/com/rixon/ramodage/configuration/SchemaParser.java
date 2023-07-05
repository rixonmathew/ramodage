package com.rixon.ramodage.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is responsible for parsing the contents of configuration file into a Schema
 * User: rixon
 * Date: 19/01/13
 * Time: 1:30 PM
 */
public class SchemaParser {

    private static final Logger LOG = LoggerFactory.getLogger(SchemaParser.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Schema parseFromFile(String schemaFileName) {
        Schema schema = null;
        try {
            String jsonString = readInputFile(schemaFileName);
            schema = populateAttributes(jsonString);
        } catch (IOException e) {
            LOG.error("An error occurred while reading the schema file:"+schemaFileName);
            e.printStackTrace();
        }
        return schema;
    }


    private static String readInputFile(String configurationFileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(configurationFileName));
        StringBuilder stringBuffer = new StringBuilder();
        String line = br.readLine();
        stringBuffer.append(line);
        while (line != null) {
            line = br.readLine();
            if (line != null)
                stringBuffer.append(line);
        }
        br.close();
        return stringBuffer.toString();
    }

    private static Schema populateAttributes(String jsonString) throws JsonProcessingException {
        if(jsonString==null|| jsonString.isEmpty()){
            LOG.error("An empty json was specified to SchemaParser");
            return Schema.emptySchema();
        }
        return objectMapper.readValue(jsonString,Schema.class);
    }

    public static Schema parseFromContent(String schemaContent) {
        try {
            return populateAttributes(schemaContent);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
