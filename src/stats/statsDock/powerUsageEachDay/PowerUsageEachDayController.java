package stats.statsDock.powerUsageEachDay;

import changescene.CloseWindow;
import control.Factory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import model.DockStatsModel;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PowerUsageEachDayController implements Initializable {

    Factory factory = new Factory();

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private Label headerLbl;

    private double[] days;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        headerLbl.setText("Power Usage Each Day");

        days = factory.getDailyPowerUsage();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        barChart.setLegendVisible(false);
        XYChart.Series set1 = new XYChart.Series<>();

        for(int i = 6; i>=0; i--){
            set1.getData().add(new XYChart.Data("" + LocalDate.now().minusDays(i), days[i]));
        }

        barChart.getData().addAll(set1);
    }

    @FXML
    void closeBarChart(ActionEvent event){
        CloseWindow cs = new CloseWindow(event);
    }
}
