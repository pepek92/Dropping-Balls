import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.Button;

public class Java_harjoitustyo extends Application{ // Petteri Kivelä
    
    int points = 0;
    
    @Override
    public void start(Stage stage) {
        
        stage.setTitle("Dropping Balls");
        Random random = new Random();
        Group group = new Group() ;
    	Scene scene = new Scene(group, 600, 600);
        scene.setFill(Color.WHITE);
        
        Text points_text = new Text();  
        points_text.setFont(Font.font("verdana", 20));
        points_text.setX(50); 
        points_text.setY(50);
        points_text.setText("Points: " + points);
        
        Text gameover_text = new Text();  
        gameover_text.setFont(Font.font("verdana", 40));
        gameover_text.setX((scene.getWidth() / 2) - 100);
        gameover_text.setY(300);
        gameover_text.setText("Game Over");
        
        Button button = new Button();
        button.setLayoutX(scene.getWidth() - 150);
        button.setLayoutY(scene.getHeight() - 75);
        button.setText("End Game");
        button.setFont(Font.font("verdana", 15));
              
    	Circle ball = new Circle(35, Color.INDIANRED);
        int randLocation = random.nextInt(530);
        ball.relocate(randLocation, 0);
        
        group.getChildren().add(ball);
        group.getChildren().add(points_text);
    
        ball.setOnMousePressed((MouseEvent event) -> {
            if (event.getButton()  ==  MouseButton.PRIMARY) {
                group.getChildren().remove(ball);
                int randLocation1 = random.nextInt(530);
                ball.relocate(randLocation1, 0);
                group.getChildren().add(ball);
            }
            if (points > 99) {
                points = points + 15;
                points_text.setText("Points: " + points);
            } else {
                points = points + 10;
                points_text.setText("Points: " + points);
            }
        });
        
        button.setOnAction((ActionEvent button1) -> {
                                System.exit(0);
                            });
        
    stage.setScene(scene);
    stage.show();
         
    Timeline timeline; 
        timeline = new Timeline(new KeyFrame(Duration.millis(20), 
                new EventHandler<ActionEvent>() {
                    
                    double y = 9;
                    double y2 = 12;
                    
                    @Override
                    public void handle(ActionEvent t) {
                        
                        if (points > 99) {
                            ball.setLayoutY(ball.getLayoutY() + y2);
                        } else {
                            ball.setLayoutY(ball.getLayoutY() + y);
                        }
                                         
                        if ((ball.getLayoutY() >= (scene.getHeight() + ball.getRadius()))) {
                            group.getChildren().add(gameover_text);
                            group.getChildren().add(button);
                        }
                    }
                }
        ));
    
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play(); 
    }
    
    public static void main(String[] args) {
        launch();
    }
}

