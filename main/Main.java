package mvp.main;

import mvp.calculated.*;
import mvp.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage stage)
    {

        Model model = new Model();
        MainPresenter mainPresenter = initApplication(model);
        Scene scene = new Scene(mainPresenter.getView(), 800, 500);
        stage.setScene(scene);
        stage.setTitle("Survarium Calculator");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("Survarium.png")));
        mainPresenter.showStartView();
        stage.show();
    }

    private MainPresenter initApplication(Model model)
    {

        MainPresenter mainPresenter = new MainPresenter();
        MainView mainView = new MainView();
        StartView startView = new StartView();
        CalculatePresenter calPres = new CalculatePresenter();

        mainPresenter.setMainView(mainView);
        mainPresenter.setStartView(startView);
        mainPresenter.setModel(model);
        mainView.setMainPresenter(mainPresenter);
        calPres.setMain(mainPresenter);
        CalculateView calView = new CalculateView(calPres.getFaktions());
        calPres.setCalView(calView);
        mainPresenter.setCalView(calView);
        calView.setCalPres(calPres);

        return mainPresenter;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
