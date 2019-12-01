package dad.javafx.ahorcado.palabras;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;

public class PalabrasController implements Initializable {

	private final String PATH = "./src/main/resources/files/Palabras.txt";

	@FXML
	private HBox view;

	@FXML
	private ListView<String> palabrasList;

	@FXML
	private Button anadirButton;

	@FXML
	private Button quitarButton;

	// Model
	PalabrasModel model = new PalabrasModel();
	
	List<String> palabras = new ArrayList<String>();

	public PalabrasController(List<String> palabras) throws IOException {
		this.palabras = palabras;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PalabrasView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Bindings

		quitarButton.disableProperty().bind(palabrasList.getSelectionModel().selectedItemProperty().isNull());
		palabrasList.itemsProperty().bind(model.palabrasProperty());
		actualizarPalabras();
		model.seleccionadoPalaProperty().bind(palabrasList.getSelectionModel().selectedItemProperty());
	}

	@FXML
	void onAnadirAction(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Nueva palabra");
		dialog.setHeaderText("Añadir una nueva palabra a la lista");
		dialog.setContentText("Palabra:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			writeCsv(result.get());
			actualizarPalabras();
		}
	}

	@FXML
	void onQuitarAction(ActionEvent event) {
		removeCsv(model.getSeleccionadoPala());
		actualizarPalabras();
	}

	private void actualizarPalabras() {
		model.getPalabras().setAll(this.palabras);
	}

	private void writeCsv(String dato) {
		try {
			FileWriter fw = new FileWriter(PATH, true);
			fw.append(dato.toUpperCase());
			fw.append("\n");
			fw.close();
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}

	private void removeCsv(String dato) {
		StringBuffer datos = new StringBuffer();

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(PATH));

			String line;
			while ((line = br.readLine()) != null) {
				if (!line.equals(dato)) {
					datos.append(line + "\n");
				}
			}

			FileWriter fw = new FileWriter(new File(PATH));
			fw.write(datos.toString());
			
			br.close();
			fw.close();

		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}

	public HBox getView() {
		return view;
	}

}
