package com.rixon.ramodage.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
* User: rixonmathew
* Date: 20/01/13
* Time: 12:17 PM
* This class represents the schema
*/
public class Schema {

    private String name;
    private String type;
    private String separator;

    @JsonProperty("class")
    private String _class;
    private List<Field> fields;


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSeparator() {
        return separator;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    private final static Schema EMPTY_SCHEMA;
    static {
        EMPTY_SCHEMA = new Schema();
        EMPTY_SCHEMA.setFields(new ArrayList<Field>());
        EMPTY_SCHEMA.setName("");
        EMPTY_SCHEMA.setType("");
        EMPTY_SCHEMA.setSeparator("");
    }

    public static Schema emptySchema() {
        return EMPTY_SCHEMA;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }
}