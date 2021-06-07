package org.primefaces.rain.entity;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class StockReturnInvest implements Serializable {
    private ObjectId _id;
    private int id_integer;
    private double returnInvestPercent;
    private String selectedStock;
    private Date dateReturnInvest;
    private String id;

    public StockReturnInvest() {
    }

    public StockReturnInvest(ObjectId _id, int id_integer, double returnInvestPercent, String selectedStock, Date dateReturnInvest, String id) {
        this._id = _id;
        this.id_integer = id_integer;
        this.returnInvestPercent = returnInvestPercent;
        this.selectedStock = selectedStock;
        this.dateReturnInvest = dateReturnInvest;
        this.id = id;
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

    public double getReturnInvestPercent() {
        return returnInvestPercent;
    }

    public void setReturnInvestPercent(double returnInvestPercent) {
        this.returnInvestPercent = returnInvestPercent;
    }

    public String getSelectedStock() {
        return selectedStock;
    }

    public void setSelectedStock(String selectedStock) {
        this.selectedStock = selectedStock;
    }

    public Date getDateReturnInvest() {
        return dateReturnInvest;
    }

    public void setDateReturnInvest(Date dateReturnInvest) {
        this.dateReturnInvest = dateReturnInvest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
