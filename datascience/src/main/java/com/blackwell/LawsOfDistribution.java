package com.blackwell;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * Laws of Distribution.
 * Class for finding points for distribution laws.
 * For mor info see:
 * https://habr.com/post/263993/
 */
public class LawsOfDistribution extends Application {

    private static final String TITLE = "Laws of Distribution";
    private static final String UNIFORM_DISTRIBUTION = "Uniform Distribution";
    private static final String EXPONENTIAL_DISTRIBUTION = "Exponential Distribution";
    private static final String NORMAL_DISTRIBUTION = "Normal Distribution";

    private static final int WIDTH = 1080;
    private static final int HEIGHT = 720;

    private static final float STEP = 0.1f;


    /**
     * Get map with points for uniform distribution.
     * This method simply use formula: 1 / (b-a)
     * With start and end parameter.
     * This give us simple line on chart.
     * @param a from
     * @param b to
     * @return map with points
     */
    private Map<Float, Float> getUniformDistribution(float a, float b){
        Map<Float, Float> map = new HashMap<>();
        float y = 1 / (b-a);
        map.put(a, y);
        map.put(b, y);
        return map;
    }

    /**
     * Get map with points for exponential distribution.
     * Formula: l*e^(-1*l*x)
     * @param a from
     * @param b to
     * @return map with points
     */
    private Map<Float, Float> getExponentialDistribution(float a, float b){
        Map<Float, Float> map = new HashMap<>();
        float l = b-a;
        for(float x=a; x<=(b+STEP); x+=STEP)
            map.put(x, (float) (l*Math.pow(Math.E, (-1*l*x))));
        return map;
    }

    /**
     * Get map with points for normal distribution.
     * Normal distribution is infinitely divisible.
     * The normal distribution has the maximum differential
     * entropy among all continuous distributions whose
     * dispersion does not exceed a specified value.
     * @param a from
     * @param b to
     * @return map with points
     */
    private Map<Float, Float> getNormalDistribution(float a, float b) {
        Map<Float, Float> map = new HashMap<>();
        float u = (b-a) / 2f;
        float q = (a+b) / 2f;
        float pow, y;
        for(float x=a; x<=(b+STEP); x+=STEP) {
            pow = (float) (Math.pow((x-u), 2) / (-2*Math.pow(q, 2)));
            y = (float) ((1/(q*Math.sqrt(2*Math.PI))) * Math.pow(Math.E, pow));
            map.put(x, y);
        }
        return map;
    }

    /**
     * Get series for chart from map.
     * @param data map with data
     * @param title title
     * @return series for chart
     */
    private XYChart.Series<Number, Number> getSeries(Map<Float, Float> data, String title) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(title);
        for(Map.Entry<Float, Float> entry : data.entrySet())
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        return series;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle(TITLE);

        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        //creating the chart
        final LineChart<Number,Number> lineChart = new LineChart<>(xAxis,yAxis);
        lineChart.setTitle(TITLE);

        final float A = 0f, B = 1f;
        lineChart.getData().add(getSeries(getUniformDistribution(A, B), UNIFORM_DISTRIBUTION));
        lineChart.getData().add(getSeries(getExponentialDistribution(A, B), EXPONENTIAL_DISTRIBUTION));
        lineChart.getData().add(getSeries(getNormalDistribution(A, B), NORMAL_DISTRIBUTION));

        StackPane root = new StackPane();
        root.getChildren().addAll(lineChart);
        Scene scene  = new Scene(root,WIDTH,HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
