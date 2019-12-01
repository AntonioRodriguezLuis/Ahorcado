package dad.javafx.ahorcado.partida;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class PartidaModel {
	private IntegerProperty puntuacionLabel = new SimpleIntegerProperty();
	private IntegerProperty vidaLabel = new SimpleIntegerProperty();
	private StringProperty palabraAdivinarLabel = new SimpleStringProperty();
	private StringProperty letrasLabel = new SimpleStringProperty();
	private StringProperty letraText = new SimpleStringProperty();
	private ObjectProperty<Image> imagenView = new SimpleObjectProperty<>();
	public final IntegerProperty puntuacionLabelProperty() {
		return this.puntuacionLabel;
	}
	
	public final int getPuntuacionLabel() {
		return this.puntuacionLabelProperty().get();
	}
	
	public final void setPuntuacionLabel(final int puntuacionLabel) {
		this.puntuacionLabelProperty().set(puntuacionLabel);
	}
	
	public final IntegerProperty vidaLabelProperty() {
		return this.vidaLabel;
	}
	
	public final int getVidaLabel() {
		return this.vidaLabelProperty().get();
	}
	
	public final void setVidaLabel(final int vidaLabel) {
		this.vidaLabelProperty().set(vidaLabel);
	}
	
	public final StringProperty palabraAdivinarLabelProperty() {
		return this.palabraAdivinarLabel;
	}
	
	public final String getPalabraAdivinarLabel() {
		return this.palabraAdivinarLabelProperty().get();
	}
	
	public final void setPalabraAdivinarLabel(final String palabraAdivinarLabel) {
		this.palabraAdivinarLabelProperty().set(palabraAdivinarLabel);
	}
	
	public final StringProperty letrasLabelProperty() {
		return this.letrasLabel;
	}
	
	public final String getLetrasLabel() {
		return this.letrasLabelProperty().get();
	}
	
	public final void setLetrasLabel(final String letrasLabel) {
		this.letrasLabelProperty().set(letrasLabel);
	}
	
	public final StringProperty letraTextProperty() {
		return this.letraText;
	}
	
	public final String getLetraText() {
		return this.letraTextProperty().get();
	}
	
	public final void setLetraText(final String letraText) {
		this.letraTextProperty().set(letraText);
	}
	
	public final ObjectProperty<Image> imagenViewProperty() {
		return this.imagenView;
	}
	
	public final Image getImagenView() {
		return this.imagenViewProperty().get();
	}
	
	public final void setImagenView(final Image imagenView) {
		this.imagenViewProperty().set(imagenView);
	}
}
