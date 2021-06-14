package org.primefaces.rain.bean;

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

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.primefaces.PrimeFaces;
import org.primefaces.event.UnselectEvent;
import org.primefaces.rain.entity.Stock;

import org.primefaces.rain.model.StockModel;

@Named("stockAdminBean")
@ViewScoped
public class StockAdminBean implements Serializable {
    //Easiest method: Alt+Enter on private static final long serialVersionUID = ;
    private static final long serialVersionUID = -8692496505355263282L;

    private String filter="";
    private List<Stock> stocks = new ArrayList<>();
    private Stock stock = new Stock();
    private List<String> avalibleExperinceYear;
    private List<String> availableSubjects;
    private List<String> availableCompetanceLevel;
    private List<String> avaliableValuta;
    private Stock deleteStock;
    private Integer id_integer;
    private String newDocument;
    private List<String> selectedBusiness;
    private List<String> avaliableBusiness;
    String db_hildebra_db1 = "hildebra_db1";
    String col_stock = "stocks";
    private Integer collectionCount_integer;

    private Stock selectedStock;

    @Inject
    private transient StockModel stockModel;

    @Inject
    private transient MongoClient mongoClient;

    public StockAdminBean() {
    }

    @PostConstruct
    public void init(){
        System.out.println("StockAdminBean");
        avalibleExperinceYear = Arrays.asList("Vælg","2001-nu","2002-nu","2003-nu","2004-nu","2005-nu","2006-nu","2007-nu","2008-nu","2009-nu","2010-nu","2011-nu","2012-nu","2013-nu","2014-nu","2015-nu","2016-nu","2017-nu","2018-nu","2019-nu","2020-nu", "2021-nu");
        availableSubjects = Arrays.asList("Velkommen", "Java", "Intellij", "JSF", "Jenkins", "Wildfly", "Tomcat", "Github", "Primefaces", "Mongo database", "Lotus Notes udvikling", "SharePoint",
                "Azure", "PowerApps", "Visual studio", "C-sharp", "AngularJS", "Octopus","Teamcity", "Intranet", "Workflow applikationer", "Support", "Second level support", "Investering", "Kundekontakt", "Aktier","Bæredygtighed", "El-biler","Sol, vind og lagring", "E-handel og platforme", "Bøger", "Musik", "Rejser", "Rejsebog", "Vandreture", "Vandreguide", "Fritid");
        availableCompetanceLevel = Arrays.asList("Vælg niveau", "Meget erfaring", "Godt kendskab", "Mindre kendskab");

        avaliableBusiness = Arrays.asList("Elbiler", "Batteri", "FANG", "Miner", "Vind", "Sol", "Vand", "Hydrogen", "Platforme / Ehandel", "Semiconductors");
        avaliableValuta = Arrays.asList("Vælg", "DKK", "NOK","SEK", "USD","EUR");
        newDocument="true";
        id_integer=0;
        collectionCount_integer=0;

        findStockList();
    }

    public void onItemUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();

        FacesMessage msg = new FacesMessage();
        msg.setSummary("Item unselected: " + event.getObject().toString());
        msg.setSeverity(FacesMessage.SEVERITY_INFO);

        context.addMessage(null, msg);
    }

    private void findStockList() {
        System.out.println("StockAdminBean find");
        stocks = stockModel.findStockList(filter);
    }

    public void saveStock(){
        System.out.println("stockAdminBean saveStock");
        MongoCollection<Document> collection = mongoClient.getDatabase(db_hildebra_db1).getCollection(col_stock);

        if ( newDocument == "true" && collectionCount_integer != 0){
            System.out.println("Nyt dokument");
            stockModel.saveStock(stock,newDocument, collectionCount_integer);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
            setNewDocument("false");
        }

        if ( newDocument == "true" && collectionCount_integer == 0){
            System.out.println("Nyt dokument && collectionCount_integer == 0");
            stockModel.saveStock(stock,newDocument, collectionCount_integer);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
            setNewDocument("false");
            collectionCount_integer = Math.toIntExact(collection.countDocuments());
            setCollectionCount_integer(collectionCount_integer);
        }

        if(newDocument == "false" && collectionCount_integer == 0) {
            System.out.println("newDocument == false && collectionCount_integer == 0" );
            stockModel.saveStock(stock,newDocument, collectionCount_integer);
        }

        if(newDocument == "false" && collectionCount_integer != 0) {
            System.out.println("newDocument == false & collectionCount_integer != 0" );
            stockModel.saveStock(stock,newDocument, collectionCount_integer);

        }
    }

    public void deleteStock() {
        System.out.println("delete product");
        id_integer = stock.getId_integer();
        System.out.println("Id_integer" + id_integer);
        stock.setId_integer(id_integer);
        stockModel.deleteStock(stock);

       this.stocks.remove(this.stock);
        this.stock = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-stocks");

    }

    public void select(Stock e){
        System.out.println("select e.toString " + e.toString());
        stock=e;

    }

    public void selectStock(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        setNewDocument("false");
        setCollectionCount_integer(0);
    }

    public void openNew() throws IOException {
        System.out.println("openNew");
        MongoCollection<Document> collection = mongoClient.getDatabase(db_hildebra_db1).getCollection(col_stock);
        collectionCount_integer = Math.toIntExact(collection.countDocuments());
        collectionCount_integer =  collectionCount_integer + 1;
        setCollectionCount_integer(collectionCount_integer);

        stock.setId_integer(0);
        stock.setStockName(null);
        stock.setSelectedBusiness(null);
        stock.setTickerCode(null);
        stock.setSelectedBusiness(null);
        stock.setStockPrice(0);
        stock.setReturnInvest(null);
        stock.setNumberOfStocks(0);
        stock.setRichText1(null);
        setNewDocument("true");
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public List<String> getAvalibleExperinceYear() {
        return avalibleExperinceYear;
    }

    public void setAvalibleExperinceYear(List<String> avalibleExperinceYear) {
        this.avalibleExperinceYear = avalibleExperinceYear;
    }

    public List<String> getAvailableSubjects() {
        return availableSubjects;
    }

    public void setAvailableSubjects(List<String> availableSubjects) {
        this.availableSubjects = availableSubjects;
    }

    public List<String> getAvailableCompetanceLevel() {
        return availableCompetanceLevel;
    }

    public void setAvailableCompetanceLevel(List<String> availableCompetanceLevel) {
        this.availableCompetanceLevel = availableCompetanceLevel;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public Stock getSelectedStock() {
        return selectedStock;
    }

    public void setSelectedStock(Stock selectedStock) {
        this.selectedStock = selectedStock;
    }

    public String getNewDocument() {
        return newDocument;
    }

    public void setNewDocument(String newDocument) {
        this.newDocument = newDocument;
    }

    public List<String> getAvaliableBusiness() {
        return avaliableBusiness;
    }

    public void setAvaliableBusiness(List<String> avaliableBusiness) {
        this.avaliableBusiness = avaliableBusiness;
    }

    public Integer getCollectionCount_integer() {
        return collectionCount_integer;
    }

    public void setCollectionCount_integer(Integer collectionCount_integer) {
        this.collectionCount_integer = collectionCount_integer;
    }

    public List<String> getAvaliableValuta() {
        return avaliableValuta;
    }

    public void setAvaliableValuta(List<String> avaliableValuta) {
        this.avaliableValuta = avaliableValuta;
    }
}
