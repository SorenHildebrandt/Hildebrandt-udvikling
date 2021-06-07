package org.primefaces.rain.bean;
import org.apache.poi.ss.usermodel.charts.LineChartData;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
//import org.primefaces.model.chart.LineChartModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class StockChartJsBean implements Serializable {
    private LineChartModel lineModel;
    private LineChartModel lineModelTesla;
    private LineChartData lineChartData;

    @PostConstruct
    public void init() {
        createLineModel();
    }

    private void createLineModel() {
        lineModel = new LineChartModel();
        lineModelTesla = new LineChartModel();

        ChartData data = new ChartData();
        ChartData teslaData = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();

        List<Object> values = new ArrayList<>();
        List<Object> tesla = new ArrayList<>();
        values.add(65);
        values.add(67);
        values.add(69);
        values.add(70);
        values.add(59);
        values.add(80);
        values.add(81);
        values.add(56);
        values.add(55);
        values.add(40);
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Første datasæt");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        dataSet.setLineTension(0.1);
        data.addChartDataSet(dataSet);


        LineChartDataSet teslaDataSet = new LineChartDataSet();
        tesla.add(65);
        tesla.add(75);
        teslaDataSet.setData(tesla);
        teslaDataSet.setFill(false);
        teslaDataSet.setLabel("Tesla datasæt");
        teslaDataSet.setBorderColor("rgb(75, 192, 192)");
        teslaDataSet.setLineTension(0.1);
        teslaData.addChartDataSet(teslaDataSet);


        List<String> labels = new ArrayList<>();
        labels.add("Januar");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        data.setLabels(labels);

        //Options
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Line chart");
        options.setTitle(title);

        lineModel.setOptions(options);
        lineModel.setData(teslaData);


        //Options
        LineChartOptions optionsTesla = new LineChartOptions();
        Title titleTesla = new Title();
        title.setDisplay(true);
        title.setText("Line chart Tesla");
        options.setTitle(titleTesla);

        lineModelTesla.setOptions(optionsTesla);
        lineModelTesla.setData(teslaData);

    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

    public LineChartModel getLineModelTesla() {
        return lineModelTesla;
    }

    public void setLineModelTesla(LineChartModel lineModelTesla) {
        this.lineModelTesla = lineModelTesla;
    }
}
