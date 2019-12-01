package dad.javafx.ahorcado.root;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dad.javafx.ahorcado.palabras.PalabrasController;
import dad.javafx.ahorcado.partida.PartidaController;
import dad.javafx.ahorcado.puntuaciones.PuntuacionesController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

public class RootController implements Initializable {

	private final String PATH_PALABRAS = "./src/main/resources/files/Palabras.txt";

	@FXML
	private TabPane rootTabPane;

	@FXML
	private Tab partidaTab;

	@FXML
	private Tab palabrasTab;

	@FXML
	private Tab puntuacionesTab;

	PalabrasController palabrasController;
	PuntuacionesController puntuacionesController;
	PartidaController partidaController;

	public RootController() throws IOException {
		partidaController = new PartidaController(readPalabrasCSV());
		palabrasController = new PalabrasController(readPalabrasCSV());
		puntuacionesController = new PuntuacionesController();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("RootView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootTabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		partidaTab.setContent(partidaController.getView());
		palabrasTab.setContent(palabrasController.getView());
		puntuacionesTab.setContent(puntuacionesController.getView());

		rootTabPane.getSelectionModel().select(partidaTab);

		partidaController.puntuaciones.addListener((o, ov, nv) -> {
			puntuacionesController.writeCsv(nv);
			puntuacionesController.actualizarPuntuaciones();
			rootTabPane.getSelectionModel().select(puntuacionesTab);
		});
	}

	private List<String> readPalabrasCSV() {
		List<String> datos = new ArrayList<>();
		String separador = ",";

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(PATH_PALABRAS));

			String line;
			while ((line = br.readLine()) != null) {
				String[] datosL = line.split(separador, -1);
				for (int i = 0; i < datosL.length; i++) {
					datos.add(datosL[i]);
				}
			}
			br.close();
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
		return datos;
	}

	public TabPane getView() {
		return rootTabPane;
	}
}
