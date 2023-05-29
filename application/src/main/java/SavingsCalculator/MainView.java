package SavingsCalculator;

import java.util.HashMap;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MainView {
  
    private int monthlySavings = 0;
    private double interestRate = 0.0;
    LineChart<Number, Number> lineChart;
    HashMap<Integer, Double> onlySavings;
    HashMap<Integer, Double> savingsTotal;

    public MainView() {
        
    }

    public Parent getView() {
        BorderPane layout = new BorderPane();

          // create the x and y axis and Line chart
        NumberAxis xAxis= new NumberAxis(0, 30, 2);
        NumberAxis yAxis = new NumberAxis();
        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Calculator");

        layout.setCenter(lineChart);

        //VBox with sliders
        VBox slidersVBox = new VBox();       

        BorderPane borderPaneSlider1 = new BorderPane();
        borderPaneSlider1.setPadding(new Insets(20, 20, 20, 20));
        borderPaneSlider1.setMinHeight(20);

        Label textSlider1 = new Label("Monthly savings");
        textSlider1.setFont(new Font(20));
        Slider slider1 = new Slider(20, 250, 20);
        slider1.setShowTickLabels(true);
        slider1.setMajorTickUnit(10);
        slider1.setBlockIncrement(10);
        slider1.setSnapToTicks(true);
        Label valueSlider1 = new Label("0");
        valueSlider1.setFont(new Font(20));
        slider1.valueProperty().addListener((observable, oldValue, newValue) -> 
        {   
            valueSlider1.setText(String.valueOf(newValue.intValue()));
            monthlySavings = newValue.intValue();
            updateChart();

        });
        borderPaneSlider1.setLeft(textSlider1);    
        borderPaneSlider1.setCenter(slider1);
        BorderPane.setMargin(slider1, new Insets(10));   
        borderPaneSlider1.setRight(valueSlider1);
  

        BorderPane borderPaneSlider2 = new BorderPane();
        borderPaneSlider2.setPadding(new Insets(20, 20, 20, 20));
        borderPaneSlider2.setMinHeight(20);

        Label textSlider2 = new Label("Yearly interest rate");
        textSlider2.setFont(new Font(20));
        Slider slider2 = new Slider(0, 10, 0);
        slider2.setShowTickLabels(true);
        slider2.setMajorTickUnit(1);
        slider2.setBlockIncrement(0.1f);
        slider2.setSnapToTicks(true);
        Label valueSlider2 = new Label("0");
        valueSlider2.setFont(new Font(20));
        slider2.valueProperty().addListener((observable, oldValue, newValue) -> 
        {
            double number = Math.round(newValue.doubleValue()*10.0)/10.0;
           //tring str = String.format("%.1f", String.valueOf(newValue.doubleValue()));
            valueSlider2.setText(String.valueOf(number));
            interestRate = newValue.doubleValue();
            updateChart();
        });
        borderPaneSlider2.setLeft(textSlider2);
        borderPaneSlider2.setCenter(slider2);
        BorderPane.setMargin(slider2, new Insets(10)); 
        borderPaneSlider2.setRight(valueSlider2);

        slidersVBox.getChildren().addAll(borderPaneSlider1, borderPaneSlider2);

        layout.setTop(slidersVBox); 


        return layout;
    }

    private void updateChart() {
        XYChart.Series<Number, Number> onlySavingsChart = new XYChart.Series<>();
        onlySavingsChart.setName("Savings without interest");
        onlySavings = Savings.calculateOnlySavings(monthlySavings);
        onlySavings.entrySet().stream().forEach(pair -> onlySavingsChart.getData().add(new XYChart.Data(pair.getKey(), pair.getValue())) );

        XYChart.Series<Number, Number> totalSavingsChart = new XYChart.Series<>();
        totalSavingsChart.setName("total savings with interest rate");
        savingsTotal = Savings.calculateSavings(monthlySavings, interestRate);
        savingsTotal.entrySet().stream().forEach(pair -> totalSavingsChart.getData().add(new XYChart.Data(pair.getKey(), pair.getValue())) );

        lineChart.getData().clear();
        lineChart.getData().add(onlySavingsChart);
        lineChart.getData().add(totalSavingsChart);

        
    }
}
