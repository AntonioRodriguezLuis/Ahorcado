package dad.javafx.ahorcado.puntuaciones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class PuntuacionesController implements Initializable {

	private final String PATH = "./src/main/resources/files/Puntuaciones.txt";

	@FXML
	private HBox view;

	@FXML
	private ListView<Puntuaciones> puntuacionesList;

	private PuntuacionesModel model = new PuntuacionesModel();

	public PuntuacionesController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PuntuacionesView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		puntuacionesList.itemsProperty().bind(model.puntuacionesProperty());
		actualizarPuntuaciones();
	}

	private List<Puntuaciones> readPuntuacionesCSV() {
		List<Puntuaciones> puntuaciones = new ArrayList<>();

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(PATH));

			String line;
			while ((line = br.readLine()) != null) {
				String[] datosL = line.split(",", -1);
				puntuaciones.add(new Puntuaciones(datosL[0], Integer.parseInt(datosL[1])));
			}
			br.close();
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
		return puntuaciones;
	}

	public void actualizarPuntuaciones() {
		List<Puntuaciones> lista = readPuntuacionesCSV();
		lista = ordenarLista(lista);
		model.getPuntuaciones().setAll(lista);
	}

	public void writeCsv(Puntuaciones dato) {
		try {
			FileWriter fw = new FileWriter(PATH, true);
			fw.append(dato.getNombre() + "," + dato.getPuntuaciones());
			fw.append("\n");
			fw.close();
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}

	private List<Puntuaciones> ordenarLista(List<Puntuaciones> lista) {
		Puntuaciones aux;
		for (int i = 1; i < lista.size(); i++) {
			for (int j = 0; j < lista.size() - i; j++) {
				if (lista.get(j).getPuntuaciones() < lista.get(j + 1).getPuntuaciones()) {
					aux = lista.get(j);
					lista.set(j, lista.get(j + 1));
					lista.set(j + 1, aux);
				}
			}
		}
		return lista;
	}

	public HBox getView() {
		return view;
	}
}
