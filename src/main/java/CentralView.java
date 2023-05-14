import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class CentralView extends VBox {
    private final Label display;
    public CentralView (Central central) {
        super(20);
        display = new Label("Disarmed");
        display.setStyle("-fx-border-color: black; -fx-background-color: white");
        display.setMinWidth(200);
        display.setAlignment(Pos.CENTER);
        setCenter(display);
        Font controlFont = new Font("Arial", 24);
        display.setFont(controlFont);
        Button aBtn, pBtn, dBtn;
        aBtn = new Button("A");
        pBtn = new Button("P");
        dBtn = new Button("D");
        aBtn.setFont(controlFont);
        pBtn.setFont(controlFont);
        dBtn.setFont(controlFont);
        aBtn.setOnAction(e ->{
                        central.armAll();
                        display.setText("Armed");
        });
        pBtn.setOnAction(e -> {
            central.armPerimeter();
            display.setText("Armed");
        });
        dBtn.setOnAction(e -> {
            central.disarm();
            display.setText("Disarmed");
        });
        HBox hBox = new HBox();
        hBox.getChildren().addAll(aBtn,pBtn,dBtn);
        hBox.setAlignment(Pos.CENTER);
        getChildren().addAll(display,hBox);
    }

    private void setCenter(Label display) {

    }
    private void setCenter(Label aBtn,Label pBtn,Label dBtn) {

    }

    public void setDisplay (String msg) {

        display.setText(msg);
    }

    public void showError(String msg) {
        display.setText(msg);
        display.setStyle("-fx-border-color: red; -fx-background-color: white");
    }
}
