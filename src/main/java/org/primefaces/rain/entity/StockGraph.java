package org.primefaces.rain.entity;

import org.bson.types.ObjectId;

import java.io.Serializable;

public class StockGraph implements Serializable {
    private ObjectId _id;
    private int id_integer;
    private String date;
    private int returInvest;

    public StockGraph() {
    }

    public StockGraph(ObjectId _id, int id_integer, String date, int returInvest) {
        this._id = _id;
        this.id_integer = id_integer;
        this.date = date;
        this.returInvest = returInvest;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public int getId_integer() {
        return id_integer;
    }

    public void setId_integer(int id_integer) {
        this.id_integer = id_integer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getReturInvest() {
        return returInvest;
    }

    public void setReturInvest(int returInvest) {
        this.returInvest = returInvest;
    }
}
