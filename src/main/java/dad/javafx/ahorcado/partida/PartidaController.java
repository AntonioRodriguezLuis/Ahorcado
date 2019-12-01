package dad.javafx.ahorcado.partida;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import dad.javafx.ahorcado.puntuaciones.Puntuaciones;
import dad.javafx.ahorcado.utils.Mensaje;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;

public class PartidaController implements Initializable {

	private final String PATH = "./src/main/resources/imagenes";

	@FXML
	private BorderPane view;

	@FXML
	private TextField letraAveriguarText;

	@FXML
	private Button letraButton;

	@FXML
	private Button resolverButton;

	@FXML
	private Label palabraAdivinarLabel;

	@FXML
	private Label letrasLabel;

	@FXML
	private ImageView ahorcadoImage;

	@FXML
	private Label puntuacionLabel;

	@FXML
	private Label vidaLabel;

	private PartidaModel model = new PartidaModel();

	List<String> palabras = new ArrayList<>();
	String palabraPartida = "";
	String palabraPartidaEspacios = "";
	String palabraAdivinar = "";
	String letrasAdivinar = "";
	int intento = 1;
	int vida = 8;
	int puntos = 0;
	
	public ObjectProperty<Puntuaciones> puntuaciones = new SimpleObjectProperty<>();

	public PartidaController(List<String> palabras) throws IOException {
		this.palabras = palabras;
		selectPalabraAleatoria();
		palabraAdivinar();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("PartidaView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Image
		ahorcadoImage.imageProperty().bind(model.imagenViewProperty());
		model.setImagenView(new Image(new File(PATH + "/" + intento + ".png").toURI().toString()));
		// pestaña partida
		palabraAdivinarLabel.textProperty().bind(model.palabraAdivinarLabelProperty());
		letrasLabel.textProperty().bind(model.letrasLabelProperty());
		model.letraTextProperty().bind(letraAveriguarText.textProperty());
		vidaLabel.textProperty().bindBidirectional(model.vidaLabelProperty(), new NumberStringConverter());
		puntuacionLabel.textProperty().bindBidirectional(model.puntuacionLabelProperty(), new NumberStringConverter());
		model.setVidaLabel(vida);

		model.vidaLabelProperty().addListener((o, ov, nv) -> {
			if(nv.intValue() == 0) {
				Mensaje.information("Has Perdido", "", "Se te han acabado las vidas.");
			}
		});
		
		model.palabraAdivinarLabelProperty().addListener((o,ov,nv) ->{
			if(!nv.contains("_")) {
				Optional<String> result = Mensaje.inputDialog("Has Ganado", "", "Introduzca el nombre del jugador");
				String nombreJugador = "";
				if (result.isPresent()){
					nombreJugador = result.get();
				}
				if(nombreJugador.isEmpty()) {
					nombreJugador = "Anonimo";
				}
				puntuaciones.setValue(new Puntuaciones(nombreJugador, model.getPuntuacionLabel()));
			}
		});

		// vida a 0
		letraButton.disableProperty()
				.bind(model.letraTextProperty().isEmpty().or(model.vidaLabelProperty().isEqualTo(0)));
		resolverButton.disableProperty()
				.bind(model.letraTextProperty().isEmpty().or(model.vidaLabelProperty().isEqualTo(0)));

	}

	@FXML
	void onLetrasAction(ActionEvent event) {
		String dato = model.getLetraText().toUpperCase();
		if (!dato.isBlank() && dato.length() == 1 && Pattern.matches("^[a-zA-Z]*$", dato)) {
			boolean isLetra = false;
			StringBuilder palabraAdivinar = new StringBuilder(this.palabraAdivinar);
			for (int i = 0; i < palabraPartidaEspacios.length(); i++) {
				if (palabraPartidaEspacios.charAt(i) == dato.charAt(0)) {
					palabraAdivinar.setCharAt(i, dato.charAt(0));
					model.setPuntuacionLabel(++puntos);
					isLetra = true;
				}
			}

			if (!isLetra) {
				intento++;
				model.setImagenView(new Image(new File(PATH + "/" + intento + ".png").toURI().toString()));
				model.setVidaLabel(--vida);
			}
			this.letrasAdivinar += dato + " ";
			this.palabraAdivinar = palabraAdivinar.toString();
			model.setPalabraAdivinarLabel(this.palabraAdivinar);
			model.setLetrasLabel(letrasAdivinar);
		} else {
			Mensaje.error("Error", "", "El dato introducido no es valido. Solo se puende introducir caracteres no numericos.");
		}

		letraAveriguarText.setText("");
	}

	@FXML
	void onResolverAction(ActionEvent event) {
		String dato = model.getLetraText().toUpperCase();
		if (!dato.isBlank() && dato.length() == palabraPartida.length() && Pattern.matches("^[a-zA-Z]*$", dato)) {
			boolean iguales = true;
			int i = 0;
			while(iguales && i != palabraPartida.length()) {
				if(palabraPartida.charAt(i) != dato.charAt(i)) {
					iguales = false;
				}
				i++;
			}
			
			if(iguales) {
				puntos = palabraPartida.length();
				model.setPuntuacionLabel(puntos);
				model.setPalabraAdivinarLabel(this.palabraPartidaEspacios);
			} else {
				intento++;
				model.setImagenView(new Image(new File(PATH + "/" + intento + ".png").toURI().toString()));
				model.setVidaLabel(--vida);
				
				this.letrasAdivinar += dato + " ";
				model.setLetrasLabel(letrasAdivinar);
			}
		}
	}

	private void palabraAdivinar() {
		for (int i = 0; i < palabraPartidaEspacios.length(); i++) {
			if (palabraPartidaEspacios.charAt(i) != ' ') {
				palabraAdivinar += "_ ";
			}
		}
		model.setPalabraAdivinarLabel(palabraAdivinar.toString());
	}
	

	private void selectPalabraAleatoria() {
		int pos = (int) (Math.random() * palabras.size());
		palabraPartida = palabras.get(pos);
		String r = "";
		for (int i = 0; i < palabraPartida.length(); i++) {
			r += palabraPartida.charAt(i) + " ";
		}
		palabraPartidaEspacios = r;
	}
	

	public BorderPane getView() {
		return view;
	}
}
