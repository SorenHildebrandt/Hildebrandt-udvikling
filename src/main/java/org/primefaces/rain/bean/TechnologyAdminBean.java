package org.primefaces.rain.bean;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.primefaces.PrimeFaces;
import org.primefaces.rain.entity.Technology;
import org.primefaces.rain.model.TechnologyModel;
import org.primefaces.event.UnselectEvent;

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

@Named("technologyAdminBean")
@ViewScoped
public class TechnologyAdminBean implements Serializable {

    private static final long serialVersionUID = -6516664185594636224L;


    private String filter = "";
    private List<String> availableTechnologies;
    private List<String> selectedTechnologies;
    private List<Technology> list = new ArrayList<>();
    private List<String> availableCompetanceLevel;
    private List<String> avalibleExperinceYear;
    private String newDocument;
    private Integer id_integer;
    String db_hildebra_db1 = "hildebra_db1";
    String col_technology = "technology";
    private Integer collectionCount_integer;

    private Technology technology = new Technology();

    @Inject
    private transient TechnologyModel technologyModel;

    @Inject
    private transient MongoClient mongoClient;

    public TechnologyAdminBean() {
    }

    @PostConstruct
    public void init() {
        System.out.println("TechnologyAdminBean postconstruct");

        availableTechnologies = Arrays.asList("Java", "Intellij", "JSF", "Jenkins", "Wildfly", "Tomcat", "Github", "Primefaces", "Mongo database", "Lotus Notes udvikling", "SharePoint",
                "Azure", "PowerApps", "Visual studio", "C-sharp", "AngularJS", "Octopus","Teamcity", "Intranet", "Workflow applikationer", "Support", "Second level support", "Investering", "Kundekontakt", "Aktier","Bæredygtighed", "El-biler","Sol, vind og lagring", "E-handel og platforme", "Bøger", "Musik", "Rejser", "Rejsebog", "Vandreture", "Vandreguide", "Fritid");

        availableCompetanceLevel = Arrays.asList("Vælg niveau", "Meget erfaring", "Godt kendskab", "Mindre kendskab");
        avalibleExperinceYear = Arrays.asList("Vælg","2001-nu","2002-nu","2003-nu","2004-nu","2005-nu","2006-nu","2007-nu","2008-nu","2009-nu","2010-nu","2011-nu","2012-nu","2013-nu","2014-nu","2015-nu","2016-nu","2017-nu","2018-nu","2019-nu","2020-nu", "2021-nu");

        newDocument="true";
        id_integer=0;
        collectionCount_integer=0;

        find();
    }

    public void saveTechnology() {
        System.out.println("TechnologyAdminBean saveTechnology");
        MongoCollection<Document> collection = mongoClient.getDatabase(db_hildebra_db1).getCollection(col_technology);

        if ( newDocument == "true" && collectionCount_integer != 0){
            System.out.println("Nyt dokument");
            technologyModel.saveTechnology(technology,newDocument, collectionCount_integer);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
            setNewDocument("false");
        }

        if ( newDocument == "true" && collectionCount_integer == 0){
            System.out.println("Nyt dokument && collectionCount_integer == 0");
            technologyModel.saveTechnology(technology,newDocument, collectionCount_integer);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
            setNewDocument("false");
            collectionCount_integer = Math.toIntExact(collection.countDocuments());
            setCollectionCount_integer(collectionCount_integer);
        }


        if(newDocument == "false" && collectionCount_integer == 0) {
            System.out.println("new" + "Document == false && collectionCount_integer == 0" );
            technologyModel.saveTechnology(technology,newDocument, collectionCount_integer);
        }

        if(newDocument == "false" && collectionCount_integer != 0) {
            System.out.println("newDocument == false & collectionCount_integer != 0" );
            technologyModel.saveTechnology(technology,newDocument, collectionCount_integer);
        }
    }

    public void openNew() throws IOException {
        System.out.println("openNew");
        MongoCollection<Document> collection = mongoClient.getDatabase(db_hildebra_db1).getCollection(col_technology);
        collectionCount_integer = Math.toIntExact(collection.countDocuments());
        collectionCount_integer =  collectionCount_integer + 1;
        setCollectionCount_integer(collectionCount_integer);

        technology.setMenuTop(null);
        technology.setMenuSecondLevel(null);
        technology.setSelectedTechnologies(null);
        technology.setRichText1(null);
        technology.setId_integer(0);
        technology.setSelectedCompetenceLevel(null);
        technology.setSelectedExperienceYear(null);
        setNewDocument("true");
    }

    public void selectTechnology(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        setNewDocument("false");
        setCollectionCount_integer(0);
    }

    public void find() {
        System.out.println("TechnologyAdminBean find");
        list = technologyModel.find(filter);
    }

    public void select(Technology e){
        System.out.println("select e.toString " + e.toString());

        technology=e;

    }

    public void onItemUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();

        FacesMessage msg = new FacesMessage();
        msg.setSummary("Item unselected: " + event.getObject().toString());
        msg.setSeverity(FacesMessage.SEVERITY_INFO);

        context.addMessage(null, msg);
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public List<Technology> getList() {
        return list;
    }

    public void setList(List<Technology> list) {
        this.list = list;
    }

    public List<String> getAvailableTechnologies() {
        return availableTechnologies;
    }

    public void setAvailableTechnologies(List<String> availableTechnologies) {
        this.availableTechnologies = availableTechnologies;
    }

    public List<String> getAvalibleExperinceYear() {
        return avalibleExperinceYear;
    }

    public void setAvalibleExperinceYear(List<String> avalibleExperinceYear) {
        this.avalibleExperinceYear = avalibleExperinceYear;
    }

    public List<String> getAvailableCompetanceLevel() {
        return availableCompetanceLevel;
    }

    public void setAvailableCompetanceLevel(List<String> availableCompetanceLevel) {
        this.availableCompetanceLevel = availableCompetanceLevel;
    }

    public String getNewDocument() {
        return newDocument;
    }

    public void setNewDocument(String newDocument) {
        this.newDocument = newDocument;
    }

    public Integer getCollectionCount_integer() {
        return collectionCount_integer;
    }

    public void setCollectionCount_integer(Integer collectionCount_integer) {
        this.collectionCount_integer = collectionCount_integer;
    }
}
