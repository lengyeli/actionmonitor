package com.lengyeli.actionmonitor.model;

/**
 * Created by ilengyel on 2017. 10. 09..
 */

import javax.persistence.*;

@Entity
public class SampleData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String text;

    public SampleData() {
    }

    public SampleData(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
