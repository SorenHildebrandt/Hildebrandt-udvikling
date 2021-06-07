package org.primefaces.rain.bean;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class CartesianGraph implements Serializable {

    private LineChartModel lineModel;
    private LineChartModel lineModelTesla;
    //private Tesla tesla = new Tesla();

    @PostConstruct
    public void init() {
        createLineModel();
    }

    private void createLineModel() {
        lineModel = new LineChartModel();
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> values = new ArrayList<>();

        values.add(65);
        values.add(59);
        values.add(80);
        values.add(81);
        values.add(56);
        values.add(55);
        values.add(40);
        dataSet.setData(values);
        //dataSet.setFill(false);
        dataSet.setLabel("Første datasæt");
        //dataSet.setBorderColor("rgb(75, 192, 192)");
        //dataSet.setLineTension(0.1);
        //data.addChartDataSet(dataSet);
        dataSet.setYaxisID("left-y-axis");


        LineChartDataSet teslaDataSet = new LineChartDataSet();
        List<Object> teslaValues = new ArrayList<>();

        teslaValues.add(44);
        teslaValues.add(66);
        teslaValues.add(77);
        teslaValues.add(80);
        teslaValues.add(81);
        teslaValues.add(85);
        teslaDataSet.setData(teslaValues);
        //teslaDataSet.setFill(false);
        teslaDataSet.setLabel("Tesla datasæt");
        teslaDataSet.setYaxisID("left-y-axis");

        data.addChartDataSet(dataSet);
        data.addChartDataSet(teslaDataSet);
        teslaDataSet.setBorderColor("rgb(75, 192, 192)");
        teslaDataSet.setLineTension(0.1);
        //teslaData.addChartDataSet(teslaDataSet);


        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        data.setLabels(labels);
        lineModel.setData(data);

        //Options 2 kurve
        LineChartOptions options = new LineChartOptions();
        CartesianScales cScales = new CartesianScales();

        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setId("left-y-axis");
        linearAxes.setPosition("left");

        //CartesianLinearAxes linearAxes2 = new CartesianLinearAxes();
        //linearAxes2.setId("right-y-axis");
        //linearAxes2.setPosition("left");

        cScales.addYAxesData(linearAxes);
        //cScales.addYAxesData(linearAxes2);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Cartesian Linear Chart");
        options.setTitle(title);

        lineModel.setOptions(options);

        //Options
        /*LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Line chart");
        options.setTitle(title);*/

        lineModel.setOptions(options);
        lineModel.setData(data);


        //Options
        /*LineChartOptions optionsTesla = new LineChartOptions();
        Title titleTesla = new Title();
        title.setDisplay(true);
        title.setText("Line chart Tesla");
        options.setTitle(titleTesla);*/

        //lineModelTesla.setOptions(optionsTesla);
        // lineModelTesla.setData(teslaData);

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


