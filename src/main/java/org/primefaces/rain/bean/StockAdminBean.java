package org.primefaces.rain.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.primefaces.PrimeFaces;
import org.primefaces.rain.domain.Product;
import org.primefaces.rain.entity.Stock;

import org.primefaces.rain.entity.Technology;
import org.primefaces.rain.model.TechnologyModel;

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
    private Stock deleteStock;
    private Integer id_integer;

    private Stock selectedStock;

    @Inject
    private transient TechnologyModel technologyModel;

    public StockAdminBean() {
    }

    @PostConstruct
    public void init(){
        System.out.println("StockAdminBean");
        avalibleExperinceYear = Arrays.asList("Vælg","2001-nu","2002-nu","2003-nu","2004-nu","2005-nu","2006-nu","2007-nu","2008-nu","2009-nu","2010-nu","2011-nu","2012-nu","2013-nu","2014-nu","2015-nu","2016-nu","2017-nu","2018-nu","2019-nu","2020-nu", "2021-nu");
        availableSubjects = Arrays.asList("Velkommen", "Java", "Intellij", "JSF", "Jenkins", "Wildfly", "Tomcat", "Github", "Primefaces", "Mongo database", "Lotus Notes udvikling", "SharePoint",
                "Azure", "PowerApps", "Visual studio", "C-sharp", "AngularJS", "Octopus","Teamcity", "Intranet", "Workflow applikationer", "Support", "Second level support", "Investering", "Kundekontakt", "Aktier","Bæredygtighed", "El-biler","Sol, vind og lagring", "E-handel og platforme", "Bøger", "Musik", "Rejser", "Rejsebog", "Vandreture", "Vandreguide", "Fritid");
        availableCompetanceLevel = Arrays.asList("Vælg niveau", "Meget erfaring", "Godt kendskab", "Mindre kendskab");

        findStockList();
    }

    private void findStockList() {
        System.out.println("StockAdminBean find");
        stocks = technologyModel.findStockList(filter);
    }

    public void saveStock() {
        System.out.println("TechnologyAdminBean create");

        if (this.stock.getCode() == null) {
            this.stock.setCode(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            this.stocks.add(this.stock);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

        technologyModel.saveStock(stock);
        PrimeFaces.current().executeScript("PF('manageStockDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-stocks");
    }

    /*public void deleteStock(Stock e){
    System.out.println("delete stockxxx");
        stock=e;
    }*/

    public void deleteStock() {
        System.out.println("delete product");
        id_integer = stock.getId_integer();
        System.out.println("Id_integer" + id_integer);
        stock.setId_integer(id_integer);
        technologyModel.deleteStock(stock);

       this.stocks.remove(this.stock);
        this.stock = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-stocks");

    }

    public void select(Stock e){
        System.out.println("select e.toString " + e.toString());
        stock=e;

    }

    public void openNew() {
        /*PrimeFaces.current().ajax().update("manageStockDialog");
        System.out.println("OpenNew");
        this.stock= new Stock();
        PrimeFaces.current().ajax().update("manageStockDialog");*/

        System.out.println("Clear felter og opret nyt dokument");
        /*stock.setSelectedTechnologies(null);
        stock.setRichText1(null);
        stock.setId_integer(0);
        stock.setSelectedCompetenceLevel(null);
        stock.setSelectedExperienceYear(null);*/
        this.stock= new Stock();
        this.stock.setStockName(null);
        this.stock.setTickerCode(null);
        //widgetVar="manageStockDialog"

    }

    public Stock getStock() {
        System.out.println("getStock");
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


}
