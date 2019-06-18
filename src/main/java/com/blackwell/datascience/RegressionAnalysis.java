package com.blackwell.datascience;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.*;

/**
 * Class fore regression analysis
 * For more info:
 * https://math.semestr.ru/corel/primer.php
 */
public class RegressionAnalysis extends Application {

    private static final Logger LOG = Logger.getLogger(RegressionAnalysis.class);
    private static final String TITLE = "Experience -  Salary";

    private static final int WIDTH = 1080;
    private static final int HEIGHT = 720;

    private Map<Integer, Integer> data = new HashMap<>();
    private final int[] rowData = new int[] {400, 700, 1000, 1300, 1700, 2000, 2100, 2090, 2100, 2150, 2175, 2200, 2225, 2500};
    // private final int[] rowData = new int[] {400, 700, 1000, 1300, 1700, 2000};

    public RegressionAnalysis() {
        for (int i=0; i<rowData.length; ++i)
            data.put((i+1), rowData[i]);
    }

    /**
     * Get correlation.
     * Formula: (xy' - x'*y') / (sX'*sY')
     * @return correlation
     */
    private float getCorrelation() {
        float n = data.size();
        float x = sum(SumType.X) / n;
        float y = sum(SumType.Y) / n;
        float xy = sum(SumType.XY) / n;

        float sX = (float) Math.sqrt((sum(SumType.SQUARE_X)/n) - Math.pow(x, 2));
        float sY = (float) Math.sqrt((sum(SumType.SQUARE_Y)/n) - Math.pow(y, 2));
        float result = (xy - x*y)/(sX*sY);
        LOG.info("Counting correlation: (" + xy + " - " + x + "*" + y + ") / (" + sX + "*" + sY +")");
        LOG.info("Correlation: " + result);
        return result;
    }

    /**
     * Get determination by simply finding square of  correlation.
     * @return determination
     */
    private float getDetermination() {
        return (float) Math.pow(getCorrelation(), 2);
    }

    /**
     * Finds autocorrelation or approximate number of points
     * that didn't match with regression analysis model.
     * Use Durbin-Watson algorithm.
     * @return autocorrelation
     */
    private float getAutocorrelation(){
        float numerator = 0, denominator = 0;
        float priv=0, e, x, y;
        Point p = getCoefficients();
        for(Map.Entry<Integer, Integer> entry : data.entrySet()){
            x = entry.getKey();
            y = entry.getValue();
            e = y - (p.x*x + p.y);
            numerator += Math.pow(e-priv, 2);
            denominator += Math.pow(e, 2);
            LOG.debug(e +"_"+ Math.pow(e-priv, 2) +"_"+ Math.pow(e, 2));
            priv = e;
        }
        LOG.info("Counting autocorrelation: " + numerator + " / " + denominator);
        LOG.info("Autocorrelation: " + (numerator/denominator));
        return numerator / denominator;
    }

    /**
     * Get point with coefficients.
     * p.x - a
     * p.y - b
     * Uses simply formula for line regression:
     * y = a*x + b
     * @return point with coefficients
     */
    private Point getCoefficients() {
        int sumOfX = sum(SumType.X);
        int sumOfY = sum(SumType.Y);
        int sumOfSquaresX = sum(SumType.SQUARE_X);
        int sumOfSquaresY = sum(SumType.SQUARE_Y);
        int n = data.size();
        int xy = sum(SumType.XY);

        LOG.info("Sum of x: " + sumOfX);
        LOG.info("Sum of y: " + sumOfY);
        LOG.info("N: " + n);
        LOG.info("Sum of squares X: " + sumOfSquaresX);
        LOG.info("Sum of squares Y: " + sumOfSquaresY);
        LOG.info("Sum of XY: " + xy);

        float a = (float) ((n*xy - sumOfX*sumOfY) /
                                (n*sumOfSquaresX - Math.pow(sumOfX, 2)));
        LOG.info("Counting coefficient a: (" + n + "*" + xy + "-" + sumOfX*sumOfY +") / ("+
                n*sumOfSquaresX +"-"+ Math.pow(sumOfX, 2) +")");
        LOG.info("Coefficient a: " + a);
        float b = (sumOfY - a * sumOfX) / n;
        LOG.info("Counting coefficient b: (" + sumOfY +"-"+ a +"*"+ sumOfX +") / " + n);
        LOG.info("Coefficient b: " + b);
        return new Point((int) a, (int) b);
    }


    enum SumType {X, Y, SQUARE_X, SQUARE_Y, XY}

    /**
     * Get sum of some elements in data map.
     * @param type SumType defines sum of what will be returned
     * @return sum
     */
    private int sum(SumType type){
        int sum = 0;
        for(Map.Entry<Integer, Integer> entry : data.entrySet()){
            switch (type){
                case X:
                    sum += entry.getKey(); break;
                case Y:
                    sum += entry.getValue(); break;
                case XY:
                    sum += entry.getKey() * entry.getValue(); break;
                case SQUARE_X:
                    sum += Math.pow(entry.getKey(), 2); break;
                case SQUARE_Y:
                    sum += Math.pow(entry.getValue(), 2); break;
            }
        }
        return sum;
    }

    /**
     * Get line regression points in map.
     * Return coordinates of line using formula:
     * y = a*x + b
     * @return map with line regression points
     */
    private Map<Integer, Integer> getLineRegressionData(){
        Map<Integer, Integer> lineData = new HashMap<>();
        Point p = getCoefficients();
        System.out.println(p);
        for(Map.Entry<Integer, Integer> entry : data.entrySet())
            lineData.put(entry.getKey(), p.x*entry.getKey()+p.y);
        return lineData;
    }

    /**
     * Return series for the chart from map.
     * @param map data
     * @param title title of series
     * @return series
     */
    private XYChart.Series<Number, Number> getChartSeries(Map<Integer, Integer> map, String title) {
        //defining a series
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(title);

        //populating the series with data
        for (Map.Entry<Integer, Integer> entry : map.entrySet())
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        return series;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle(TITLE);

        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Experience");
        yAxis.setLabel("Salary");
        //creating the chart
        final LineChart<Number,Number> lineChart = new LineChart<>(xAxis,yAxis);
        lineChart.setTitle(TITLE);

        StackPane root = new StackPane();
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BASELINE_LEFT);
        hBox.setPadding(new Insets(HEIGHT-50,0,0,0));
        Text text = new Text("Correlation: " + getCorrelation()*100 +
                "%\nDetermination: " + getDetermination()*100 + "%" +
                "\nAutocorrelation: " + getAutocorrelation());
        text.setTextAlignment(TextAlignment.LEFT);
        text.setX(0);
        hBox.getChildren().add(text);
        root.getChildren().addAll(hBox, lineChart);
        Scene scene  = new Scene(root,WIDTH,HEIGHT);
        lineChart.getData().add(getChartSeries(data, "actual data"));
        lineChart.getData().add(getChartSeries(getLineRegressionData(), "line data"));

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

