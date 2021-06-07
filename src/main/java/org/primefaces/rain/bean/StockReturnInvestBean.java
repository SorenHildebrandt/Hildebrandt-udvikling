package org.primefaces.rain.bean;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.primefaces.PrimeFaces;
import org.primefaces.rain.entity.StockReturnInvest;
import org.primefaces.rain.model.StockReturnInvestModel;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named("stockReturnInvestBean")
@ViewScoped
public class StockReturnInvestBean implements Serializable  {
    private static final long serialVersionUID = 4035043022022977550L;

    private StockReturnInvest stockReturnInvest = new StockReturnInvest();
    private List<StockReturnInvest> stocksReturnInvest = new ArrayList<>();
    private List<String> avaliableStock;
    private String newDocument;
    private Integer id_integer;
    private Integer collectionCount_integer;
    private String filter="";

    String db_hildebra_db1 = "hildebra_db1";
    String col_stock = "stockReturnInvest";

    @Inject
    private transient StockReturnInvestModel stockReturnInvestModel;

    @Inject
    private transient MongoClient mongoClient;

    public StockReturnInvestBean() {
    }

    @PostConstruct
    public void init(){
        System.out.println("StockReturnInvestBean");
        avaliableStock = Arrays.asList("Vælg aktie", "Albemarle", "Amazon", "Alphabet");
        newDocument="true";
        id_integer=0;
        collectionCount_integer=0;

        findStockList();

    }

    public void deleteStock() {
        System.out.println("delete product");
        id_integer = stockReturnInvest.getId_integer();
        System.out.println("Id_integer" + id_integer);
        stockReturnInvest.setId_integer(id_integer);
        stockReturnInvestModel.deleteStock(stockReturnInvest);

        this.stocksReturnInvest.remove(this.stockReturnInvest);
        this.stockReturnInvest = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-stocks");

    }

    public void selectStock(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        setNewDocument("false");
        setCollectionCount_integer(0);
    }

    private void findStockList() {System.out.println("StockAdminBean find");
        stocksReturnInvest = stockReturnInvestModel.findStockList(filter);
    }


    public void saveStockReturnInvest(){
        System.out.println("stockReturnInvestBean saveStock");
        MongoCollection<Document> collection = mongoClient.getDatabase(db_hildebra_db1).getCollection(col_stock);
        if ( newDocument == "true" && collectionCount_integer != 0){
            System.out.println("Nyt dokument");
            stockReturnInvestModel.saveStockReturnInvest(stockReturnInvest,newDocument, collectionCount_integer);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
            setNewDocument("false");
        }

        if ( newDocument == "true" && collectionCount_integer == 0){
            System.out.println("Nyt dokument && collectionCount_integer == 0");
            stockReturnInvestModel.saveStockReturnInvest(stockReturnInvest,newDocument, collectionCount_integer);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
            setNewDocument("false");
            collectionCount_integer = Math.toIntExact(collection.countDocuments());
            setCollectionCount_integer(collectionCount_integer);
        }

        if(newDocument == "false" && collectionCount_integer == 0) {
            System.out.println("newDocument == false && collectionCount_integer == 0" );
            stockReturnInvestModel.saveStockReturnInvest(stockReturnInvest,newDocument, collectionCount_integer);
        }

        if(newDocument == "false" && collectionCount_integer != 0) {
            System.out.println("newDocument == false & collectionCount_integer != 0" );
            stockReturnInvestModel.saveStockReturnInvest(stockReturnInvest,newDocument, collectionCount_integer);
        }
    }

    public void openNew() throws IOException {
        System.out.println("openNew");
        MongoCollection<Document> collection = mongoClient.getDatabase(db_hildebra_db1).getCollection(col_stock);
        collectionCount_integer = Math.toIntExact(collection.countDocuments());
        collectionCount_integer =  collectionCount_integer + 1;
        setCollectionCount_integer(collectionCount_integer);

        stockReturnInvest.setId_integer(0);
        stockReturnInvest.setDateReturnInvest(null);
        stockReturnInvest.setReturnInvestPercent(0);
        stockReturnInvest.setSelectedStock(null);
        setNewDocument("true");
    }

    public StockReturnInvest getStockReturnInvest() {
        return stockReturnInvest;
    }

    public void setStockReturnInvest(StockReturnInvest stockReturnInvest) {
        this.stockReturnInvest = stockReturnInvest;
    }

    public void setStocksReturnInvest(List<StockReturnInvest> stocksReturnInvest) {
        this.stocksReturnInvest = stocksReturnInvest;
    }

    public List<StockReturnInvest> getStocksReturnInvest() {
        return stocksReturnInvest;
    }

    public List<String> getAvaliableStock() {
        return avaliableStock;
    }

    public void setAvaliableStock(List<String> avaliableStock) {
        this.avaliableStock = avaliableStock;
    }

    public String getNewDocument() {
        return newDocument;
    }

    public void setNewDocument(String newDocument) {
        this.newDocument = newDocument;
    }

    public Integer getId_integer() {
        return id_integer;
    }

    public void setId_integer(Integer id_integer) {
        this.id_integer = id_integer;
    }

    public Integer getCollectionCount_integer() {
        return collectionCount_integer;
    }

    public void setCollectionCount_integer(Integer collectionCount_integer) {
        this.collectionCount_integer = collectionCount_integer;
    }

    public String getDb_hildebra_db1() {
        return db_hildebra_db1;
    }

    public void setDb_hildebra_db1(String db_hildebra_db1) {
        this.db_hildebra_db1 = db_hildebra_db1;
    }

    public String getCol_stock() {
        return col_stock;
    }

    public void setCol_stock(String col_stock) {
        this.col_stock = col_stock;
    }

    public StockReturnInvestModel getStockReturnInvestModel() {
        return stockReturnInvestModel;
    }

    public void setStockReturnInvestModel(StockReturnInvestModel stockReturnInvestModel) {
        this.stockReturnInvestModel = stockReturnInvestModel;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }
}
