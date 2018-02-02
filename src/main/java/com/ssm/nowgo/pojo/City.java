package com.ssm.nowgo.pojo;

public class City {
    private Integer id;
    private String name;
    private String spell;
    private Integer letterId;

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

    public Integer getLetterId() {
        return letterId;
    }

    public void setLetterId(Integer letterId) {
        this.letterId = letterId;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }
}
