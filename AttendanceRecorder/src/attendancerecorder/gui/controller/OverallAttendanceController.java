/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.gui.controller;
/*
public class OverallAttendanceController implements Initializable {

    IStudentManager iStudentManager = new StudentManager();
    
    @FXML
    private Button btn_close;
    @FXML
    private PieChart overAllChart;
//    private PieChart dbosChart1;
//    private PieChart dbosChart2;
//    private PieChart itoChart1;
//    private PieChart itoChart2;
//    private PieChart scoChart1;
//    private PieChart scoChart2;
//    private PieChart sdeChart1;
//    private PieChart sdeChart2;
    @FXML
    private Tab tab_overall;
    @FXML
    private Tab tab_sortByDate;
    @FXML
    private JFXButton btn_search;
    @FXML
    private Label lbl_showStatus;

    /**
     * Initializes the controller class.
     */
   /* @Override
    public void initialize(URL url, ResourceBundle rb) {

        initOverallChart();
//        ObservableList<PieChart.Data> dbos1ChartData
//                = FXCollections.observableArrayList(
//                        new PieChart.Data("Present", 30),
//                        new PieChart.Data("Absent", 70));
//        dbosChart1.setData(dbos1ChartData);
//        ObservableList<PieChart.Data> dbos2ChartData
//                = FXCollections.observableArrayList(
//                        new PieChart.Data("Present", 23),
//                        new PieChart.Data("Absent", 77));
//        dbosChart2.setData(dbos2ChartData);
//        ObservableList<PieChart.Data> ito1ChartData
//                = FXCollections.observableArrayList(
//                        new PieChart.Data("Present", 60),
//                        new PieChart.Data("Absent", 40));
//        itoChart1.setData(ito1ChartData);
//        ObservableList<PieChart.Data> ito2ChartData
//                = FXCollections.observableArrayList(
//                        new PieChart.Data("Present", 50),
//                        new PieChart.Data("Absent", 50));
//        itoChart2.setData(ito2ChartData);
//        ObservableList<PieChart.Data> sco1ChartData
//                = FXCollections.observableArrayList(
//                        new PieChart.Data("Present", 70),
//                        new PieChart.Data("Absent", 30));
//        scoChart1.setData(sco1ChartData);
//        ObservableList<PieChart.Data> sco2ChartData
//                = FXCollections.observableArrayList(
//                        new PieChart.Data("Present", 72),
//                        new PieChart.Data("Absent", 28));
//        scoChart2.setData(sco2ChartData);
//        ObservableList<PieChart.Data> sde1ChartData
//                = FXCollections.observableArrayList(
//                        new PieChart.Data("Present", 83),
//                        new PieChart.Data("Absent", 17));
//        sdeChart1.setData(sde1ChartData);
//        ObservableList<PieChart.Data> sde2ChartData
//                = FXCollections.observableArrayList(
//                        new PieChart.Data("Present", 55),
//                        new PieChart.Data("Absent", 45));
//        sdeChart2.setData(sde2ChartData);
 /*   }

    @FXML
    private void clickClose(ActionEvent event) {
        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void click_search(ActionEvent event) {
    }

    private void initOverallChart() {
        ObservableList<PieChart.Data> overallChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Present", 82),
                        new PieChart.Data("Absent", 18));
        overAllChart.setData(overallChartData);
    }

    private void calculateOverallAttendance() {
        List<Attendance> attLst = new ArrayList();
        attLst = iStudentManager.getAllAttendancesById();
        
    }

}
*/