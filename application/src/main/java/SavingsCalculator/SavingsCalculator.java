package SavingsCalculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SavingsCalculator extends Application{   
    

  @Override
  public void start(Stage stage) throws Exception {
   
      MainView mainView = new MainView();

     // 1. Create the main view and add the high level layout
      Scene view = new Scene(mainView.getView(), 800, 600);

      // 2. Show the application
      stage.setScene(view);
      stage.setTitle("Savings calculator");
      stage.show();
  }
    public static void main(String[] args) {
        launch();
    }

}
