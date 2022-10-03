package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    static double[] x = new double[2];
    static double[] y = new double[2];
    static double[] x1 = new double[100];
    static double[] y1 = new double[100];
    static int counter = 0, counter2=0, counter3=0;
    static boolean isClicking = false;

    static Canvas canvas = new Canvas(600, 600);
    static GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane root = new AnchorPane();
        root.getChildren().add(canvas);
        canvas.setVisible(true);

        Text text = new Text("two-dot lines");
        text.setX(400);
        text.setY(100);
        text.setFont(Font.font(15));
        root.getChildren().add(text);

        Scene scene = new Scene(root, 600, 600);
        scene.setOnMouseClicked( event -> {
            graphicsContext.setLineWidth(4);
            graphicsContext.setStroke(Color.BLACK);
            graphicsContext.beginPath();
            graphicsContext.moveTo(event.getX(), event.getY());
            graphicsContext.lineTo(event.getX(), event.getY());
            graphicsContext.stroke();
            if (counter2 == 0) {
                if (counter % 2 == 0) {
                    x[0] = event.getX();
                    y[0] = event.getY();
                } else {
                    x[1] = event.getX();
                    y[1] = event.getY();
                    graphicsContext.beginPath();
                    graphicsContext.moveTo(x[0], y[0]);
                    graphicsContext.lineTo(x[1], y[1]);
                    graphicsContext.stroke();
                }
                counter++;
            } else if (counter2 == 1) {
                if (counter == 0) {
                    x[0] = event.getX();
                    y[0] = event.getY();
                    counter++;
                } else {
                    graphicsContext.beginPath();
                    graphicsContext.moveTo(x[0], y[0]);
                    graphicsContext.lineTo(event.getX(), event.getY());
                    graphicsContext.stroke();
                    x[0] = event.getX();
                    y[0] = event.getY();
                }
            } else if (counter2 == 2) {
                x1[counter3] = event.getX();
                y1[counter3] = event.getY();
                counter3++;
                if (counter3 > 1) {
                    for (int i = 0; i < counter3; i++) {
                        graphicsContext.beginPath();
                        graphicsContext.moveTo(x1[counter3 - 1], y1[counter3 - 1]);
                        graphicsContext.lineTo(x1[i], y1[i]);
                        graphicsContext.stroke();
                    }
                }
            } else if (counter2 == 3)
            {
                isClicking=true;
                scene.setOnMouseDragged(event1 -> {
                    if(isClicking)
                    {
                        graphicsContext.beginPath();
                        graphicsContext.lineTo(event1.getX(), event1.getY());
                        graphicsContext.stroke();
                    }
                });
            }
        }
        );

        scene.setOnMouseReleased(event -> isClicking=false);

        scene.setOnKeyPressed(event -> {
            if(event.getCode()==KeyCode.B)
            {
                if(counter2==4)
                {
                    graphicsContext.clearRect(0,0,600,600);
                }
            }

            if(event.getCode()== KeyCode.SPACE)
            {
                if(counter2==0)
                {
                    counter2++;
                }
                else if(counter2==1)
                {
                    counter2++;
                    counter3=0;
                }
                else if(counter2==2) {
                    counter2++;
                }
                else if(counter2==3)
                {
                    counter2++;
                }
                else
                {
                    counter2=0;
                }

                if(counter2==0)
                {text.setText("two dots lines");}
                else if(counter2==1)
                {text.setText("solid line");}
                else if(counter2==2)
                {text.setText("every dot to every dot");}
                else if(counter2==3)
                {text.setText("painter mode");}
                else if(counter2==4)
                {text.setText("dots/Press 'b' to clear");}
                counter=0;
            }
        });

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("carrot");
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
