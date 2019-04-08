package mvp.main;


import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class StartView extends VBox
{

    public StartView()
    {
        initView();
    }
    
    private void initView()
    {
       
       Image img= new Image(getClass().getResourceAsStream("logo.png"));
       ImageView pic = new ImageView();
       pic.setImage(img);
       
       setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
       getChildren().add(pic);
       setAlignment(Pos.CENTER);
       
    }
}
