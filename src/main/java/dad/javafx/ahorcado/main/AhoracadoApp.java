package dad.javafx.ahorcado.main;

import dad.javafx.ahorcado.root.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AhoracadoApp extends Application {
	RootController rootController;
	@Override
	public void start(Stage primaryStage) throws Exception {
		rootController = new RootController();
		
		Scene scene = new Scene(rootController.getView(),600,500);
		
		primaryStage.setTitle("Acceso a datos");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
