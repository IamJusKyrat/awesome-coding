package com.turing.patttern.creational.prototype;

import java.util.List;

public class Statement implements Cloneable {
    private List<String> parameters;
    private String sqlQuery;
    private Record record;

    public Statement(String sqlQuery, List<String> parameters, Record record){
        this.sqlQuery = sqlQuery;
        this.parameters = parameters;
        this.record = record;
    }

    public Statement clone(){
        try{
            return (Statement) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public Record getRecord() {
        return record;
    }
}
