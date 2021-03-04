package application;
	
import java.util.List;
import java.util.Random;
import java.util.Set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class Main extends Application {

    
	@Override
	public void start(Stage primaryStage) {
    
    List<Integer> X = new ArrayList<>();
    X.add(5);
    X.add(7);
    X.add(17);
    X.add(19);
    X.add(21);
    X.add(25);
    X.add(55);

    List<Double> P = new ArrayList<>();
    P.add(0.01);
    P.add(0.05);
    P.add(0.3);
    P.add(0.3);
    P.add(0.3);
    P.add(0.02);
    P.add(0.02);
    List<Double> min = new ArrayList<>();
    min.add(0.0);
    List<Double> max = new ArrayList<>();
    max.add(0.01);
    
    
    Double prev = P.get(0); 
    for(int i = 1; i < P.size(); i++) {
        min.add(prev);
        max.add(prev + P.get(i)); 
        prev = prev + P.get(i);
    }
    
   
    List <Integer> discretValuesList = new ArrayList<>();
    List<Value> discretValues = new ArrayList<>();
    Random random = new Random();
    
    for (int n = 0; n < 20; n++) {

        Double randomNumber = random.nextDouble();

        for (int i = 0; i < min.size(); i++) {
            if (randomNumber >= min.get(i) && randomNumber < max.get(i)) {
                discretValues.add(new Value(0, 0D, String.format("%.2f - %.2f ", min.get(i), max.get(i)), X.get(i)));
                discretValuesList.add(X.get(i));
            }
        }
    }
    
    discretValuesList.forEach(System.out::println);
    
    double mathExp = 0;
    for(Value value : discretValues) {
        mathExp += value.value/discretValues.size();
    }
    
    System.out.println("Math expectation: " + mathExp);
    
    double dispersion = 0;
    for(Value value : discretValues) {
        dispersion += (value.value*value.value)/discretValues.size(); 
    }
    
    dispersion -= mathExp*mathExp;
    
    System.out.println("Dispersion: " + dispersion);
    
   
    Set<Value> set= new HashSet<>();
    for(int i = 0; i < discretValues.size(); i++) {
        int hitRate =  Collections.frequency(discretValuesList, discretValues.get(i).value);
        discretValues.get(i).hitRate = hitRate;
        discretValues.get(i).relativeHitRate = (double) discretValues.get(i).hitRate / 20;
        set.add(discretValues.get(i));
    }
  
    
    ObservableList<Value> values = FXCollections.observableArrayList();
    for(Value v: set) {
        values.add(v);
    }
   

    TableColumn<Value, String> column1 = new TableColumn<>("Interval");
    column1.setCellValueFactory(new PropertyValueFactory<>("interval"));
    TableColumn<Value, Double> column2 = new TableColumn<>("Hit rate");
    column2.setCellValueFactory(new PropertyValueFactory<>("hitRate"));
    TableColumn<Value, Double> column3 = new TableColumn<>("Revative Hit Rate");
    column3.setCellValueFactory(new PropertyValueFactory<>("relativeHitRate"));

    TableView<Value> tableView = new TableView<Value>();
    tableView.setItems(values);
    tableView.getColumns().add(column1);
    tableView.getColumns().add(column2);
    tableView.getColumns().add(column3);

    VBox vbox = new VBox(tableView);
    vbox.getChildren().addAll();

    Scene scene1 = new Scene(vbox);

    primaryStage.setScene(scene1);

    primaryStage.show();
    
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();
    final LineChart<Number,Number> lineChart = 
            new LineChart<Number,Number>(xAxis,yAxis);
    
    XYChart.Series series = new XYChart.Series();
    
    for(Value v: set) {
        series.getData().add(new XYChart.Data(v.value, v.hitRate));
    }
    
    Scene scene3  = new Scene(lineChart,800,600);
    lineChart.getData().add(series);
    
    FlowPane root = new FlowPane();
    root.setPadding(new Insets(10));
    root.setHgap(10);
    root.setVgap(10);

    // Button
    Button button = new Button("Show Table");
    Button button2 = new Button("Show Grafics"); 
    Label label = new Label("");

    button.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            vbox.getChildren().addAll(button2);
            primaryStage.setScene(scene1);
        }
    });
    
    button2.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            primaryStage.setScene(scene3);
        }
    });

    root.getChildren().addAll(button);

    Scene scene2 = new Scene(root, 350, 150);
    primaryStage.setScene(scene2);
    primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
