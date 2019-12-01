package dad.javafx.ahorcado.palabras;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PalabrasModel {
	private ListProperty<String> palabras = new SimpleListProperty<String>(FXCollections.observableArrayList());
	private StringProperty seleccionadoPala = new SimpleStringProperty();

	public final ListProperty<String> palabrasProperty() {
		return this.palabras;
	}

	public final ObservableList<String> getPalabras() {
		return this.palabrasProperty().get();
	}

	public final void setPalabras(final ObservableList<String> palabras) {
		this.palabrasProperty().set(palabras);
	}

	public final StringProperty seleccionadoPalaProperty() {
		return this.seleccionadoPala;
	}
	

	public final String getSeleccionadoPala() {
		return this.seleccionadoPalaProperty().get();
	}
	

	public final void setSeleccionadoPala(final String seleccionadoPala) {
		this.seleccionadoPalaProperty().set(seleccionadoPala);
	}
}
