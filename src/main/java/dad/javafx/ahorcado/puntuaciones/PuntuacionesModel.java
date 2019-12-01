package dad.javafx.ahorcado.puntuaciones;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PuntuacionesModel {
	private ListProperty<Puntuaciones> puntuaciones = new SimpleListProperty<Puntuaciones>(
			FXCollections.observableArrayList());
	private IntegerProperty seleccionadoPunt = new SimpleIntegerProperty();

	public final ListProperty<Puntuaciones> puntuacionesProperty() {
		return this.puntuaciones;
	}

	public final ObservableList<Puntuaciones> getPuntuaciones() {
		return this.puntuacionesProperty().get();
	}

	public final void setPuntuaciones(final ObservableList<Puntuaciones> puntuaciones) {
		this.puntuacionesProperty().set(puntuaciones);
	}

	public final IntegerProperty seleccionadoPuntProperty() {
		return this.seleccionadoPunt;
	}

	public final int getSeleccionadoPunt() {
		return this.seleccionadoPuntProperty().get();
	}

	public final void setSeleccionadoPunt(final int seleccionadoPunt) {
		this.seleccionadoPuntProperty().set(seleccionadoPunt);
	}
}
