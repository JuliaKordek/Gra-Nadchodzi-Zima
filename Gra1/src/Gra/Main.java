package Gra;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) {
        int szerokosc=1024;
        int wysokosc=768;
        JFrame okno = new JFrame("Nadchodzi Zima");
        okno.setSize(szerokosc,wysokosc);
        okno.setLocationRelativeTo(null);
        okno.setResizable(false);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rozgrywka rozgrywka = new rozgrywka(szerokosc,wysokosc);
        okno.add(rozgrywka,BorderLayout.CENTER);
        JPanel panelQuizu = new JPanel(); // Panel dla quizu
        panelQuizu.setPreferredSize(new Dimension(szerokosc, 100)); // Wysokość 100 pikseli dla quizu

        // Dodanie komponentów do panelu z quizem (przykładowy tekst)
        JLabel labelQuiz = new JLabel("TU BEDZIE ZNAJDOWAL SIE QUIZ Z ZAKRESU WIEDZY SZKOLNICTWA WCZESNOSZKOLNEGO");
        panelQuizu.add(labelQuiz);

        // Dodanie panelu z quizem na dole okna
        okno.add(panelQuizu, BorderLayout.SOUTH);

        //okno.pack();
        okno.setVisible(true);

        rozgrywka.requestFocus();


    }
}