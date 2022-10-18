package GUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.util.*;


import java.util.ArrayList;

import static java.nio.file.Files.find;

public class MouseComponent extends JComponent {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    private static final int SIDE_lENGTH = 10;
    private ArrayList<Rectangle2D> squares;
    private Rectangle2D current;

    public MouseComponent(){
        squares = new ArrayList<>();
        current = null;

       addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());

    }

    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public void paintComponent(Graphics g){
        var g2 = (Graphics2D) g;
        //draw squares
        for(Rectangle2D r :squares)
        {
            g2.draw(r);
        }

    }
    public Rectangle2D find(Point2D p){
        for (Rectangle2D r : squares){
        if(r.contains(p)) return r;
        }
        return null;
    }

    public void add(Point2D p){
        double x = p.getX();
        double y = p.getY();

        current = new Rectangle2D.Double(x - SIDE_lENGTH /2, y - SIDE_lENGTH /2, SIDE_lENGTH, SIDE_lENGTH);
        squares.add(current);
        repaint();

    }

    public void remove(Rectangle2D s){
        if(s == null) return;
        if(s == current) current = null;
        squares.remove(s);
        repaint();
    }


    private class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent event){
            //add new square if the cursor isnt inside a square
            current = find(event.getPoint());
            if(current == null) add(event.getPoint());
        }

        public void mouseClicked(MouseEvent event){
            // remove current square if double clicked
            current = find(event.getPoint());
            if(current != null && event.getClickCount() >= 2) remove(current);
        }


    }

    private class MouseMotionHandler implements MouseMotionListener{

        @Override
        public void mouseMoved(MouseEvent e) {
            if(find(e.getPoint()) == null)
                setCursor(Cursor.getDefaultCursor());
            else setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(current != null)
            {
                int x = e.getX();
                int y = e.getY();

                current.setFrame(x - SIDE_lENGTH /2, y - SIDE_lENGTH /2, SIDE_lENGTH, SIDE_lENGTH);
                repaint();
            }

        }
    }



}
