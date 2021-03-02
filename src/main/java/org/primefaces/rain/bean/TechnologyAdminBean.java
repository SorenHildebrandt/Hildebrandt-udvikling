package org.primefaces.rain.bean;

import org.primefaces.rain.entity.Technology;
import org.primefaces.rain.model.TechnologyModel;
import org.primefaces.event.UnselectEvent;

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

import static com.mongodb.client.model.Updates.set;

@Named("technologyAdminBean")
@ViewScoped
public class TechnologyAdminBean implements Serializable {
    private Technology technology = new Technology();
    private String filter = "";
    private List<String> availableTechnologies;
    private List<String> selectedTechnologies;
    private List<Technology> list = new ArrayList<>();
    private List<String> availableCompetanceLevel;
    private List<String> avalibleExperinceYear;

    @Inject
    private transient TechnologyModel technologyModel;

    public TechnologyAdminBean() {
    }

    @PostConstruct
    public void init() {
        System.out.println("TechnologyAdminBean postconstruct");

        availableTechnologies = Arrays.asList("Velkommen", "Java", "Intellij", "JSF", "Primefaces", "Mongo database", "Lotus Notes udvikling", "SharePoint",
                "Azure", "Intranet", "Workflow applikationer", "Investering", "Aktier", "Bøger", "Musik", "Rejser", "Fritid", "Kundekontakt");

        availableCompetanceLevel = Arrays.asList("Vælg niveau", "Ekspert", "Godt kendskab", "Mindre kendskab");
        avalibleExperinceYear = Arrays.asList("Vælg","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20");
        find();
    }

    public void create() {
        System.out.println("TechnologyAdminBean create");
        technologyModel.create(technology);
    }

    public void createNew() {
        System.out.println("Clear felter og opret nyt dokument");
        technology.setSelectedTechnologies(null);
        technology.setRichText1(null);
        technology.setId_integer(0);
        technology.setSelectedCompetenceLevel(null);
        technology.setSelectedExperienceYear(null);
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
}
