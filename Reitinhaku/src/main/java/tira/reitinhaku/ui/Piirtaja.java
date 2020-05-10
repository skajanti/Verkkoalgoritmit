package tira.reitinhaku.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tira.reitinhaku.algot.AStar;
import tira.reitinhaku.algot.Dijkstra;
import tira.reitinhaku.algot.JumpPoint;
import tira.reitinhaku.algot.Verkko;
import tira.reitinhaku.utils.KartanLataaja;

/**
 *
 * @author seppo
 */
public class Piirtaja extends Application {
    
    public void launch() {
        launch(Piirtaja.class);
    }
    
    @Override
    public void start(Stage ikkuna) {
        KartanLataaja lataaja = new KartanLataaja();
        Verkko v = lataaja.lataa("BigGameHunters.map");
        
//        AStar a = new AStar();
//        Dijkstra a = new Dijkstra();
        JumpPoint a = new JumpPoint();
        long alku = System.currentTimeMillis();
        int[] reitti = a.hae(v, 0, 30, 500, 80);
        long loppu = System.currentTimeMillis();
        
        int solmu = 500 + v.getY() * 80;
        int pituus = 0;
        
        boolean[][] boolKartta = v.getBoolkartta();
        
        
        
        
        
        
        WritableImage kuva = new WritableImage(v.getX(), v.getY());
        PixelWriter pw = kuva.getPixelWriter();
        
        Color vari = new Color(0, 0, 1, 1);
        for (int i = 0; i < v.getY(); i++) {
            for (int j = 0; j < v.getX(); j++) {
                if(boolKartta[j][i]) pw.setColor(j, i, vari);
            }
        }
        
        
        vari = new Color(1, 0, 0, 1);
        do {
            int ss = reitti[solmu];
            pw.setColor(ss % v.getY(), ss / v.getY(), vari);
            System.out.println(ss % v.getY() + "," + ss / v.getY());
            solmu = ss;
            pituus++;
        } while (reitti[solmu] % v.getY() != 0 || reitti[solmu] / v.getY() != 30);
        System.out.println(pituus);
        System.out.println(loppu - alku + "ms");
        
        ImageView kuvaNakyma = new ImageView(kuva);
        Pane pane = new Pane();
        pane.getChildren().add(kuvaNakyma);
        
        ikkuna.setScene(new Scene(pane));
        ikkuna.show();
    }
}
