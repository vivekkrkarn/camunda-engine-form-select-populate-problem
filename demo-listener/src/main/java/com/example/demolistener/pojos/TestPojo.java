package com.example.demolistener.pojos;

import java.io.Serializable;

public class TestPojo implements Serializable {

    private Integer id;
    private String name;
    private String value;
    private String localName;

    public TestPojo() {
    }

    public TestPojo(Integer id, String name, String value, String localName) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.localName = localName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }
}
