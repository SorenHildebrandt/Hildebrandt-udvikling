package org.primefaces.rain.bean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.rain.entity.Stocks;
import org.primefaces.rain.model.TechnologyModel;

@Named("stochAdminBean")
@ViewScoped
public class StockAdminBean implements Serializable {
    //Easiest method: Alt+Enter on private static final long serialVersionUID = ;
    private static final long serialVersionUID = -8692496505355263282L;

    private String filter="";
    private List<Stocks> stocks = new ArrayList<>();

    @Inject
    private transient TechnologyModel technologyModel;

    public StockAdminBean() {
    }

    @PostConstruct
    public void init(){
        findStockList();
    }

    private void findStockList() {
        System.out.println("StockAdminBean find");
        stocks = technologyModel.findStockList(filter);
    }
}
