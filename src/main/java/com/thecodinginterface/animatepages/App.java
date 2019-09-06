
package com.thecodinginterface.animatepages;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {
    static StackPane stackPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stackPane = new StackPane();

        var anchorPane = new AnchorPane(stackPane);
        anchorPane.setPrefSize(800, 600);

        AnchorPane.setTopAnchor(stackPane, 0.0);
        AnchorPane.setBottomAnchor(stackPane, 0.0);
        AnchorPane.setLeftAnchor(stackPane, 0.0);
        AnchorPane.setRightAnchor(stackPane, 0.0);

        var choiceDlg = new ChoiceDialog<String>();
        choiceDlg.getItems().addAll(
            "Slide In From Top",
            "Slide In From Right",
            "Slide In From Bottom",
            "Slide In From Left",
            "Fade In",
            "Fade Out"
        );

        choiceDlg.selectedItemProperty().addListener((obs, ov, nv) -> {
            if (nv != null) {
                switch(nv) {
                    case "Slide In From Top":
                        doSlideInFromTop();
                        break;
                    case "Slide In From Right":
                        doSlideInFromRight();
                        break;
                    case "Slide In From Bottom":
                        doSlideInFromBottom();
                        break;
                    case "Slide In From Left":
                        doSlideInFromLeft();
                        break;
                    case "Fade In":
                        doFadeIn();
                        break;
                    case "Fade Out":
                        doFadeOut();
                        break;
                }
            }
        });

        choiceDlg.setTitle("Transition Animations");
        choiceDlg.setHeaderText(null);
        choiceDlg.setGraphic(null);

        stackPane.getChildren().add(
            // makeSectionPane returns BorderPane with a Label of 
        	  // a specified background color
            makeSectionPane("JavaFX Scene Transition Animation", "orange")
        );

        primaryStage.setOnShown(evt -> {
            choiceDlg.showAndWait();
        });

        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.show();
    }

    static void doSlideInFromBottom() {
        var paneToRemove = stackPane.getChildren().get(0);
        var paneToAdd = makeSectionPane("Slide In From Bottom",  "grey");

        paneToAdd.translateYProperty().set(stackPane.getHeight());
        stackPane.getChildren().add(paneToAdd);
      
        var keyValue = new KeyValue(paneToAdd.translateYProperty(), 0, Interpolator.EASE_IN);
        var keyFrame = new KeyFrame(Duration.millis(900), keyValue);
        var timeline =  new Timeline(keyFrame);
        timeline.setOnFinished(evt -> {
            stackPane.getChildren().remove(paneToRemove);
        });
        timeline.play();
    }

    static void doSlideInFromTop() {
        var paneToRemove = stackPane.getChildren().get(0);
        var paneToAdd = makeSectionPane("Slide In From Top", "brown");

        paneToAdd.translateYProperty().set(-1 * stackPane.getHeight());

        stackPane.getChildren().add(paneToAdd);

        var keyValue = new KeyValue(paneToAdd.translateYProperty(), 0, Interpolator.EASE_IN);
        var keyFrame = new KeyFrame(Duration.millis(900), keyValue);
        var timeline = new Timeline(keyFrame);
        timeline.setOnFinished(evt -> {
            stackPane.getChildren().remove(paneToRemove);
        });
        timeline.play();
    }

    static void doSlideInFromRight() {
        var paneToRemove = stackPane.getChildren().get(0);
        var paneToAdd = makeSectionPane("Slide in From Right", "green");

        paneToAdd.translateXProperty().set(stackPane.getWidth());
        stackPane.getChildren().add(paneToAdd);

        var keyValue = new KeyValue(paneToAdd.translateXProperty(), 0, Interpolator.EASE_IN);
        var keyFrame = new KeyFrame(Duration.millis(900), keyValue);
        var timeline = new Timeline(keyFrame);
        timeline.setOnFinished(evt -> {
            stackPane.getChildren().remove(paneToRemove);
        });
        timeline.play();
    }

    static void doSlideInFromLeft() {
        var paneToRemove = stackPane.getChildren().get(0);
        var paneToAdd = makeSectionPane("Slide in From Left", "pink");

        paneToAdd.translateXProperty().set(-1 * stackPane.getWidth());
        stackPane.getChildren().add(paneToAdd);

        var keyValue = new KeyValue(paneToAdd.translateXProperty(), 0, Interpolator.EASE_IN);
        var keyFrame = new KeyFrame(Duration.millis(900), keyValue);
        var timeline = new Timeline(keyFrame);
        timeline.setOnFinished(evt -> {
            stackPane.getChildren().remove(paneToRemove);
        });
        timeline.play();
    }

    static void doFadeIn() {
        var paneToRemove = stackPane.getChildren().get(0);
        var paneToAdd = makeSectionPane("Fade In", "red");

        stackPane.getChildren().add(paneToAdd);
        var fadeInTransition = new FadeTransition(Duration.millis(600));

        fadeInTransition.setOnFinished(evt -> {
            stackPane.getChildren().remove(paneToRemove);
        });
        fadeInTransition.setNode(paneToAdd);
        fadeInTransition.setFromValue(0);
        fadeInTransition.setToValue(1);
        fadeInTransition.play();
    }

    static void doFadeOut() {
        var paneToRemove = stackPane.getChildren().get(0);
        stackPane.getChildren().add(0, makeSectionPane("Fade Out", "blue"));

        var fadeOutTransition = new FadeTransition(Duration.millis(600));

        fadeOutTransition.setOnFinished(evt -> {
            stackPane.getChildren().remove(paneToRemove);
        });

        fadeOutTransition.setNode(paneToRemove);
        fadeOutTransition.setFromValue(1);
        fadeOutTransition.setToValue(0);
        fadeOutTransition.play();
    }



    static BorderPane makeSectionPane(String sectionLabel, String color) {
        var borderPane = new BorderPane();
        var label = new Label(sectionLabel);
        label.setStyle("-fx-text-fill: white");
        label.setFont(Font.font("cambria", 20));
        var hbox = new HBox(label);
        var vbox = new VBox(hbox);
        vbox.setPadding(new Insets(10));

        hbox.setAlignment(Pos.CENTER);
        borderPane.setCenter(vbox);
        
        vbox.setStyle("-fx-background-color:" + color);
        return borderPane;
    }
}
