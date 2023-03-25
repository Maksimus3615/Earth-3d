package com.design.earth;

import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Earth extends WindowProgram {

    public void run() {
        setBackground(Color.gray);
        //центр
        int X0 = getWidth() / 2;
        int Y0 = getHeight() / 2;
        int R = 200;

        //угол поворота шара вокруг оси
        double beta = 0;
        //время работы приложения
        for (int q = 0; q < 500; q++) {
            removeAll();

            // синий круг с зеленым контуром
            GOval o = new GOval(X0 - R, Y0 - R, 2 * R, 2 * R);
            o.setColor(Color.green);
            o.setFillColor(Color.blue);
            o.setFilled(true);
            add(o);

            double alfa = 0;
            // количество точек на двойном мередиане
            int a = 30;
            // количество точек на половине параллели
            int b = 20;
            // шаг по меридиану
            double deltaAlfa = 360d / a * Math.PI / 180;
            // шаг по параллели
            double deltaBeta = 180d / b * Math.PI / 180;
            // угол оси шара
            double teta = 60 * Math.PI / 180;
            for (int j = 0; j < b; j++) {
                for (int i = 0; i < a; i++) {
                    // координаты точек
                    double x = R * Math.sin(alfa) * Math.cos(beta);
                    double y = R * Math.cos(alfa);
                    double z = R * Math.sin(alfa) * Math.sin(beta);
                    // координаты следующих по мередиану точек
                    double x1 = R * Math.sin(alfa + deltaAlfa) * Math.cos(beta);
                    double y1 = R * Math.cos(alfa + deltaAlfa);
                    double z1 = R * Math.sin(alfa + deltaAlfa) * Math.sin(beta);
                    // координаты следующих по паралеллям точек
                    double x2 = R * Math.sin(alfa) * Math.cos(beta + deltaBeta);
                    double y2 = R * Math.cos(alfa);
                    double z2 = R * Math.sin(alfa) * Math.sin(beta + deltaBeta);
                    // проекции точек при наклоне шара
                    double xx = x;
                    double yy = y * Math.cos(teta) + z * Math.sin(teta);
                    double zz = -y * Math.sin(teta) + z * Math.cos(teta);
                    // проекции следующих по мередианам точек
                    double xx1 = x1;
                    double yy1 = y1 * Math.cos(teta) + z1 * Math.sin(teta);
                    double zz1 = -y1 * Math.sin(teta) + z1 * Math.cos(teta);
                    // проекции следующих по паралеллям точек
                    double xx2 = x2;
                    double yy2 = y2 * Math.cos(teta) + z2 * Math.sin(teta);
                    double zz2 = -y2 * Math.sin(teta) + z2 * Math.cos(teta);
                    // строим мередианы
                    if (zz > 0 && zz1 > 0) { //отсечь заднюю часть
                        GLine l = new GLine(X0 + xx, Y0 + yy, X0 + xx1, Y0 + yy1);
                        l.setColor(Color.green);
                        add(l);
                    }
                    //строим параллели
                    if (zz > 0 && zz2 > 0) { //отсечь заднюю часть
                        GLine l1 = new GLine(X0 + xx, Y0 + yy, X0 + xx2, Y0 + yy2);
                        l1.setColor(Color.green);
                        add(l1);
                    }
                    alfa += deltaAlfa;
                }
                beta += deltaBeta;
            }
            pause(30);
            beta += deltaBeta/10;
        }
    }
}
