package com.ramodage.configuration;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;

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

    private static final Logger LOG = Logger.getLogger(SchemaParser.class);

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

    private static Schema populateAttributes(String jsonString) {
        if(jsonString==null|| jsonString.isEmpty()){
            LOG.error("An empty json was specified to SchemaParser");
            return Schema.emptySchema();
        }
        return JSON.parseObject(jsonString,Schema.class);
    }

    public static Schema parseFromContent(String schemaContent) {
        Schema schema = populateAttributes(schemaContent);
        return schema;
    }

}
