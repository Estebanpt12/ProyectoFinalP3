package co.edu.uniquindio.casasubastas.controllers;

import co.edu.uniquindio.casasubastas.exceptions.EmptyFieldsException;
import co.edu.uniquindio.casasubastas.model.Chat;
import co.edu.uniquindio.casasubastas.model.Mensaje;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class ChatViewController {

    ModelFactoryController modelFactoryController;

    private List<Label> mensajes = new ArrayList<>();

    private ObservableList<Chat> chats = FXCollections.observableArrayList();

    private Chat chatSelector;

    @FXML
    private TableView<Chat> TablaChats;

    @FXML
    private Button btnEnviar;

    @FXML
    private VBox cajaMensajes;

    @FXML
    private AnchorPane chatsView;

    @FXML
    private ScrollPane contenedorCaja;

    @FXML
    private TextField mensajeAEnviar;

    @FXML
    private TableColumn<Chat, ?> nombreChat;

    @FXML
    void enviarMensaje(ActionEvent event) throws EmptyFieldsException {

        if(chatSelector == null) {
            throw new EmptyFieldsException("Seleccione un chat");
        }
        if(mensajeAEnviar.getText().equals("")){
            throw new EmptyFieldsException("Mensaje vacio");
        }
        modelFactoryController.crearMensaje(mensajeAEnviar.getText(), chatSelector.getUsuarioDestinatario());
        mensajeAEnviar.setText("");
        loadChat();
    }

    private void loadChat() {

        chats.clear();
        mensajes.clear();
        cajaMensajes.getChildren().clear();

        chats.addAll(modelFactoryController.obtenerChats(modelFactoryController.getUsuarioLogeado().getUsuario()));

        TablaChats.setItems(chats);
        if(chatSelector !=null) {
            Chat chat = chatSelector;
            for (Chat chatCargar : chats) {
                if (chatCargar.getListaMensajes() != null && chat.getUsuarioDestinatario().equals(chatCargar.getUsuarioDestinatario())) {
                    for (Mensaje mensaje : chatCargar.getListaMensajes()) {
                        Label mensajeACargar = new Label(mensaje.getMessage());
                        mensajeACargar.setMinWidth(cajaMensajes.getWidth());
                        if (mensaje.getUsuarioRemitente().equals(chatCargar.getUsuario())) {
                            mensajeACargar.setAlignment(Pos.CENTER_RIGHT);
                        } else {
                            mensajeACargar.setAlignment(Pos.CENTER_LEFT);
                        }
                        mensajes.add(mensajeACargar);
                    }
                }
            }
            for (Label label : mensajes) {
                cajaMensajes.getChildren().add(label);
            }
        }
        contenedorCaja.applyCss();
        contenedorCaja.layout();
        contenedorCaja.setVvalue(1.0);
    }

    @FXML
    void initialize() {
        modelFactoryController = ModelFactoryController.getInstance();

        this.TablaChats.setItems(chats);

        this.nombreChat.setCellValueFactory(new PropertyValueFactory<>("usuarioDestinatario"));

        TablaChats.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
                if(newSelection != null) {
                    chatSelector = newSelection;
                    loadChat();
                    return;
                }
        });
        loadChat();
    }
    
}
