package Gra;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class rozgrywka extends JPanel implements ActionListener, KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && poruszaniey!=1) {
            kierunek="gora";
            poruszaniex = 0;
            poruszaniey = -1;
        } else if (e.getKeyCode()==KeyEvent.VK_DOWN && poruszaniey!=-1) {
            kierunek="dol";
            poruszaniex = 0;
            poruszaniey = 1;
        } else if (e.getKeyCode()==KeyEvent.VK_RIGHT && poruszaniex!=-1) {
            kierunek="prawo";
            poruszaniex = 1;
            poruszaniey = 0;
        }else if (e.getKeyCode()==KeyEvent.VK_LEFT && poruszaniex!=1) {
            kierunek="lewo";
            poruszaniex = -1;
            poruszaniey = 0;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {}

    private class Siatka{
        int x;
        int y;
        Siatka (int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    int szerokosc;
    int wysokosc;
    int rozmiar_siatki=32;
    Siatka poczatek_stada;
    ArrayList<Siatka> stado;
    Siatka jablko;
    Random polozenie_jablka;
    //petla
    Timer petla;
    int poruszaniex;
    int poruszaniey;
    private BufferedImage tlo;
    private BufferedImage jezykgora;
    private BufferedImage jezykdol;
    private BufferedImage jezykprawo;
    private BufferedImage jezyklewo;
    public String kierunek="lewo";
    private BufferedImage obrazekJablka;
    int nowaSzerokoscJablka = 96;
    int nowaWysokoscJablka = 96;
    int nowaSzerokoscduzegojezyka = 96;
    int nowaWysokoscduzegojezyka = 96;
    int nowaSzerokoscmalegojezyka = 96;
    int nowaWysokoscmalegojezyka = 96;

    rozgrywka(int szerokosc,int wysokosc){
        this.szerokosc=szerokosc;
        this.wysokosc=wysokosc;
        setPreferredSize(new Dimension(this.szerokosc,this.wysokosc));
        poczatek_stada=new Siatka(5,5);
        stado = new ArrayList<Siatka>();
        jablko=new Siatka(10,10);
        polozenie_jablka= new Random();
        rozmieszczanie_jablka();
        poruszaniex=0;
        poruszaniey=0;
        petla = new Timer(350,this);
        petla.start();
        addKeyListener(this);
        setFocusable(true);
        try{
            tlo = ImageIO.read(new File("tlo.jpg")); // Ładowanie obrazu tła
        }catch(IOException e){
            e.printStackTrace();
        }
        try {
            jezykgora = ImageIO.read(new File("jezykgora.png")); // Ładowanie obrazu języka
            jezykdol = ImageIO.read(new File("jezykdol.png"));
            jezykprawo = ImageIO.read(new File("jezykprawo.png"));
            jezyklewo= ImageIO.read(new File("jezyklewo.png"));
        } catch (IOException e) {
            e.printStackTrace(); // Obsługa wyjątku
        }
    }
    public boolean kolizja(Siatka tile,Siatka tile1){
        return tile.x==tile1.x && tile.y==tile1.y;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(tlo, 0, 0, this);
        draw(g);
    }
    public void draw(Graphics g){

        for (int i = 1; i < stado.size(); i++) {
            Siatka segment = stado.get(i);
            if (segment != null) {
                g.drawImage(jezykprawo, segment.x * rozmiar_siatki, segment.y * rozmiar_siatki, nowaSzerokoscmalegojezyka, nowaWysokoscmalegojezyka, this);
            }
        }


            //jablka
        /*for(int i=0; i < stado.size();i++){
            Siatka malyjezyk = stado.get(i);
            try {
                obrazekJablka = ImageIO.read(new File("jablko.png")); // Zastąp "sciezka_do_obrazu_jablka.png" rzeczywistą ścieżką do pliku
            } catch (IOException e) {
                e.printStackTrace(); // W razie błędu wyświetl ślad stosu
            }

            if (obrazekJablka != null) {

                g.drawImage(obrazekJablka,jablko.x * rozmiar_siatki, jablko.y * rozmiar_siatki, nowaSzerokoscJablka, nowaWysokoscJablka, this);
            }

        }*/
        try {
            obrazekJablka = ImageIO.read(new File("jablko.png")); // Zastąp "sciezka_do_obrazu_jablka.png" rzeczywistą ścieżką do pliku
        } catch (IOException e) {
            e.printStackTrace(); // W razie błędu wyświetl ślad stosu
        }

        if (obrazekJablka != null) {
            g.drawImage(obrazekJablka,jablko.x * rozmiar_siatki, jablko.y * rozmiar_siatki, nowaSzerokoscJablka, nowaWysokoscJablka, this);
        }
        //pocatek stada
        if (jezykgora!= null || jezykdol  != null || jezykprawo  != null || jezyklewo  != null) {


            //g.drawImage(jezyk, poczatek_stada.x * rozmiar_siatki, poczatek_stada.y * rozmiar_siatki, nowaSzerokosc, nowaWysokosc, this);
            switch (kierunek){
                case "gora":
                    //oryginalnaSzerokosc = jezykgora.getWidth();
                    //oryginalnaWysokosc = jezykgora.getHeight();
                    g.drawImage(jezykgora, poczatek_stada.x * rozmiar_siatki, poczatek_stada.y * rozmiar_siatki, nowaSzerokoscduzegojezyka, nowaWysokoscduzegojezyka, this);
                    break;
                case "dol":
                    //oryginalnaSzerokosc = jezykdol.getWidth();
                    //oryginalnaWysokosc = jezykdol.getHeight();
                    g.drawImage(jezykdol, poczatek_stada.x * rozmiar_siatki, poczatek_stada.y * rozmiar_siatki, nowaSzerokoscduzegojezyka, nowaWysokoscduzegojezyka, this);
                    break;
                case "prawo":
                    //oryginalnaSzerokosc = jezykprawo.getWidth();
                    //oryginalnaWysokosc = jezykprawo.getHeight();
                    g.drawImage(jezykprawo, poczatek_stada.x * rozmiar_siatki, poczatek_stada.y * rozmiar_siatki, nowaSzerokoscduzegojezyka, nowaWysokoscduzegojezyka, this);
                    break;
                case "lewo":
                    //oryginalnaSzerokosc = jezyklewo.getWidth();
                    //oryginalnaWysokosc = jezyklewo.getHeight();
                    g.drawImage(jezyklewo, poczatek_stada.x * rozmiar_siatki, poczatek_stada.y * rozmiar_siatki, nowaSzerokoscduzegojezyka, nowaWysokoscduzegojezyka, this);
                    break;
            }

        }
    }
    public void rozmieszczanie_jablka(){
        jablko.x=polozenie_jablka.nextInt(szerokosc/rozmiar_siatki);
        jablko.y=polozenie_jablka.nextInt(wysokosc/rozmiar_siatki);
    }

    public void move() {
        Siatka newHead = new Siatka(poczatek_stada.x+poruszaniex, poczatek_stada.y+poruszaniey);
       // poczatek_stada.x+=poruszaniex;
        //poczatek_stada.y+=poruszaniey;
        //jabluszkamniam
        if(kolizja(poczatek_stada,jablko)){
            stado.add(0,newHead);   //to dziala essa
            rozmieszczanie_jablka();
        }
        else {
            if (!stado.isEmpty()) {
                stado.add(0, newHead); // Dodaj nową "głowę" na początku listy
                stado.remove(stado.size() -1 ); // Usuń ostatni segment
            }
            poczatek_stada = newHead; // Aktualizacja "głowy"
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }


}
