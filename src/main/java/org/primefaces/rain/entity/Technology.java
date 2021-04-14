package org.primefaces.rain.entity;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Technology implements Serializable {

    private ObjectId _id;
    private int id_integer;
    private String richText1;
    private String richText2;
    private List <String> selectedTechnologies;
    private String selectedTech_string;
    //private List <String> selectedExperienceYear;
    private String selectedExperienceYear;
    private String selectedCompetenceLevel;
    private String id;
    private String menuTop;
    private String menuSecondLevel;
    public Date buyStockDate;



    public Technology() {
    }

    public Technology(ObjectId _id, int id_integer, String richText1, String richText2, List<String> selectedTechnologies, String selectedTech_string, String selectedExperienceYear, String selectedCompetenceLevel, String id, String menuTop, String menuSecondLevel, Date buyStockDate) {
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
        this.buyStockDate = buyStockDate;
        //, Date buyStockDate
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

    public String
    getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSelectedTech_string() {
        return selectedTech_string;
    }

    public void setSelectedTech_string(String selectedTech_string) {
        this.selectedTech_string = selectedTech_string;
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

    public Date getBuyStockDate() {
        return buyStockDate;
    }

    public void setBuyStockDate(Date buyStockDate) {
        this.buyStockDate = buyStockDate;
    }
}



