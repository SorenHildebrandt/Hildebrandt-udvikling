package org.primefaces.rain.entity;

import org.bson.types.ObjectId;
import org.primefaces.rain.converter.GsonCompatibleDate;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Stock implements Serializable {
    private ObjectId _id;
    private int id_integer;
    private String richText1;
    private String richText2;
    private List<String> selectedTechnologies;
    private File file;

    private String selectedTech_string;
    private String selectedExperienceYear;
    private String selectedCompetenceLevel;
    private String id;
    private String menuTop;
    private String menuSecondLevel;

    private String stockName;
    private String tickerCode;
    private int number;
    private int price;
    private String country;
    private String purcaseDate;
    private String code;
    //private GsonCompatibleDate buyStockDate;
    private Date buyStockDate;
    private Integer stockPrice;
    private String selectedValuta;
    private String returnInvest;
    private List<String> selectedBusiness;
    private String selectedBusiness_string;
    private Integer numberOfStocks;


    public Stock() {
    }

    public Stock(ObjectId _id, int id_integer, String richText1, String richText2, List<String> selectedTechnologies, String selectedTech_string, String selectedExperienceYear, String selectedCompetenceLevel, String id, String menuTop, String menuSecondLevel, String stockName, String tickerCode, int number, int price, String country, String purcaseDate, String code, Date buyStockDate, Integer stockPrice, String selectedValuta, String returnInvest, List<String> selectedBusiness, String selectedBusiness_string, Integer numberOfStocks) {
        this._id = _id;
        this.id_integer = id_integer;
        this.richText1 = richText1;
        this.richText2 = richText2;
        this.selectedTechnologies = selectedTechnologies;
        this.selectedTech_string = selectedTech_string;
        this.selectedExperienceYear = selectedExperienceYear;
        this.selectedCompetenceLevel = selectedCompetenceLevel;
        this.id = id;
        this.menuTop = menuTop;
        this.menuSecondLevel = menuSecondLevel;
        this.stockName = stockName;
        this.tickerCode = tickerCode;
        this.number = number;
        this.price = price;
        this.country = country;
        this.purcaseDate = purcaseDate;
        this.code = code;
        this.buyStockDate = buyStockDate;
        this.stockPrice = stockPrice;
        this.selectedValuta = selectedValuta;
        this.returnInvest = returnInvest;
        this.selectedBusiness = selectedBusiness;
        this.selectedBusiness_string = selectedBusiness_string;
        this.numberOfStocks = numberOfStocks;
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

    public String getRichText1() {
        return richText1;
    }

    public void setRichText1(String richText1) {
        this.richText1 = richText1;
    }

    public String getRichText2() {
        return richText2;
    }

    public void setRichText2(String richText2) {
        this.richText2 = richText2;
    }

    public List<String> getSelectedTechnologies() {
        return selectedTechnologies;
    }

    public void setSelectedTechnologies(List<String> selectedTechnologies) {
        this.selectedTechnologies = selectedTechnologies;
    }

    public String getSelectedTech_string() {
        return selectedTech_string;
    }

    public void setSelectedTech_string(String selectedTech_string) {
        this.selectedTech_string = selectedTech_string;
    }

    public String getSelectedExperienceYear() {
        return selectedExperienceYear;
    }

    public void setSelectedExperienceYear(String selectedExperienceYear) {
        this.selectedExperienceYear = selectedExperienceYear;
    }

    public String getSelectedCompetenceLevel() {
        return selectedCompetenceLevel;
    }

    public void setSelectedCompetenceLevel(String selectedCompetenceLevel) {
        this.selectedCompetenceLevel = selectedCompetenceLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuTop() {
        return menuTop;
    }

    public void setMenuTop(String menuTop) {
        this.menuTop = menuTop;
    }

    public String getMenuSecondLevel() {
        return menuSecondLevel;
    }

    public void setMenuSecondLevel(String menuSecondLevel) {
        this.menuSecondLevel = menuSecondLevel;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getTickerCode() {
        return tickerCode;
    }

    public void setTickerCode(String tickerCode) {
        this.tickerCode = tickerCode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPurcaseDate() {
        return purcaseDate;
    }

    public void setPurcaseDate(String purcaseDate) {
        this.purcaseDate = purcaseDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getBuyStockDate() {
        return buyStockDate;
    }

    public void setBuyStockDate(Date buyStockDate) {
        this.buyStockDate = buyStockDate;
    }

    public Integer getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Integer stockPrice) {
        this.stockPrice = stockPrice;
    }

    public String getReturnInvest() {
        return returnInvest;
    }

    public void setReturnInvest(String returnInvest) {
        this.returnInvest = returnInvest;
    }

    public List<String> getSelectedBusiness() {
        return selectedBusiness;
    }

    public void setSelectedBusiness(List<String> selectedBusiness) {
        this.selectedBusiness = selectedBusiness;
    }

    public Integer getNumberOfStocks() {
        return numberOfStocks;
    }

    public void setNumberOfStocks(Integer numberOfStocks) {
        this.numberOfStocks = numberOfStocks;
    }

    public String getSelectedBusiness_string() {
        return selectedBusiness_string;
    }

    public void setSelectedBusiness_string(String selectedBusiness_string) {
        this.selectedBusiness_string = selectedBusiness_string;
    }

    public String getSelectedValuta() {
        return selectedValuta;
    }

    public void setSelectedValuta(String selectedValuta) {
        this.selectedValuta = selectedValuta;
    }
}
