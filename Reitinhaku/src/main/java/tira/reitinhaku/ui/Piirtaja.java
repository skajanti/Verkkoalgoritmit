package tira.reitinhaku.ui;

import java.util.Scanner;
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
import tira.reitinhaku.algot.ReitinHakuAlgo;
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
        Scanner lukija = new Scanner(System.in);
        Verkko v;
        KartanLataaja lataaja = new KartanLataaja();
        String karttaNimi;
        while (true) {
            System.out.println("Syötä kartan nimi: ");
            karttaNimi = lukija.nextLine();
            v = lataaja.lataa(karttaNimi);
            if (v != null) break;
        }

        ReitinHakuAlgo algoritmi;
        while (true) {
            System.out.println("Valitse algoritmi: Dijkstra(d), A*(A*) tai Jump Point(jp)");
            String algoString = lukija.nextLine();
            if (algoString.equals("d")) {
                algoritmi = new Dijkstra();
                break;
            } else if (algoString.equals("A*")) {
                algoritmi = new AStar();
                break;
            } else if (algoString.equals("jp")) {
                algoritmi = new JumpPoint();
                break;
            } else {
                System.out.println("Virheellinen syöte, vastaa \"d\" tai \"A*\" tai \"jp\"");
            }
        }

        int aX, aY, lX, lY;
        while (true) {
            System.out.println("Syötä lähtöpisteen x-koordinaatti: ");
            String aXString = lukija.nextLine();
            try {
                aX = Integer.parseInt(aXString);
                break;
            } catch (Exception e) {
                System.out.println("Virheellinen syöte, täytyy olla kokonaisluku");
            }
        }

        while (true) {
            System.out.println("Syötä lähtöpisteen y-koordinaatti: ");
            String aYString = lukija.nextLine();
            try {
                aY = Integer.parseInt(aYString);
                break;
            } catch (Exception e) {
                System.out.println("Virheellinen syöte, täytyy olla kokonaisluku");
            }
        }

        while (true) {
            System.out.println("Syötä maalipisteen x-koordinaatti: ");
            String lXString = lukija.nextLine();
            try {
                lX = Integer.parseInt(lXString);
                break;
            } catch (Exception e) {
                System.out.println("Virheellinen syöte, täytyy olla kokonaisluku");
            }
        }

        while (true) {
            System.out.println("Syötä maalipisteen y-koordinaatti: ");
            String lYString = lukija.nextLine();
            try {
                lY = Integer.parseInt(lYString);
                break;
            } catch (Exception e) {
                System.out.println("Virheellinen syöte, täytyy olla kokonaisluku");
            }
        }
        
        long alku = System.currentTimeMillis();
        int[] reitti = algoritmi.hae(v, aX, aY, lX, lY);
        long loppu = System.currentTimeMillis();
        
        int solmu = lX + v.getY() * lY;
        int pituus = 0;
        
        boolean[][] boolKartta = v.getBoolkartta();
        
        if (reitti[lX + lY * v.getY()] == 0) {
            System.out.println("Ei reittiä");
            return;
        }
        
        
        
        
        WritableImage kuva = new WritableImage(v.getX(), v.getY());
        PixelWriter pw = kuva.getPixelWriter();
        
        Color vari = new Color(0, 0, 1, 1);
        for (int i = 0; i < v.getY(); i++) {
            for (int j = 0; j < v.getX(); j++) {
                if (boolKartta[j][i]) pw.setColor(j, i, vari);
            }
        }
        
        
        vari = new Color(1, 0, 0, 1);
        do {
            int ss = reitti[solmu];
            pw.setColor(ss % v.getY(), ss / v.getY(), vari);
            System.out.println(ss % v.getY() + "," + ss / v.getY());
            solmu = ss;
            pituus++;
        } while (reitti[solmu] % v.getY() != 2 || reitti[solmu] / v.getY() != 30);
        System.out.println(pituus);
        System.out.println(loppu - alku + "ms");
        
        ImageView kuvaNakyma = new ImageView(kuva);
        Pane pane = new Pane();
        pane.getChildren().add(kuvaNakyma);
        
        ikkuna.setScene(new Scene(pane));
        ikkuna.show();
    }
}
