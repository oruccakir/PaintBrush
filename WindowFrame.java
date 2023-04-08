
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.event.MouseInputListener;


public class WindowFrame extends JFrame{

    private Boolean isRectangle = false;

    private Boolean isOval = false;

    private Boolean isMoveWithPencil = false;

    private Boolean isMove = false;

    private HashMap<String,ColorPanel> colors = new HashMap<>();

    private String colorKeys[] = null;

    private HashMap<String,JButton> buttons = new HashMap<>();

    private String buttonKeys[] = null;

    private DrawAreaPanel drawAreaPanel = null;

    private JPanel buttonPanel = null;

    private ColorPanel colorPanel = null;

    private JPanel abovePanel = null;

    private Color chosenColor = Color.BLACK;

    private JPanel middleLine = new JPanel();

    public void loadColourPanels(){

        colorKeys = new String[7];

        ColorPanel bluePanel = new ColorPanel();
        bluePanel.setBackground(Color.BLUE);
        bluePanel.addMouseListener(bluePanel);
        colors.put("bluePanel", bluePanel);
        colorKeys[0] = "bluePanel";

        ColorPanel redPanel = new ColorPanel();
        redPanel.setBackground(Color.RED);
        redPanel.addMouseListener(redPanel);
        colors.put("redPanel", redPanel);
        colorKeys[1] = "redPanel";

        ColorPanel greenPanel = new ColorPanel();
        greenPanel.setBackground(Color.GREEN);
        greenPanel.addMouseListener(greenPanel);
        colors.put("greenPanel", greenPanel);
        colorKeys[2] = "greenPanel";

        ColorPanel yellowPanel = new ColorPanel();
        yellowPanel.setBackground(Color.YELLOW);
        yellowPanel.addMouseListener(yellowPanel);
        colors.put("yellowPanel", yellowPanel);
        colorKeys[3] = "yellowPanel";

        ColorPanel orangePanel = new ColorPanel();
        orangePanel.setBackground(Color.ORANGE);
        orangePanel.addMouseListener(orangePanel);
        colors.put("orangePanel", orangePanel);
        colorKeys[4] = "orangePanel";

        ColorPanel magentaPanel = new ColorPanel();
        magentaPanel.setBackground(Color.MAGENTA);
        magentaPanel.addMouseListener(magentaPanel);
        colors.put("magentaPanel", magentaPanel);
        colorKeys[5] = "magentaPanel";

        ColorPanel blackPanel = new ColorPanel();
        blackPanel.addMouseListener(blackPanel);
        blackPanel.setBackground(Color.BLACK);
        colors.put("blackPanel", blackPanel);
        colorKeys[6] = "blackPanel";

    }

    public void loadButtons(){

        buttonKeys = new String[5];

        Button drawRectangle = new Button("Dikdörtgen Çiz");
        drawRectangle.addActionListener(drawRectangle);
        buttons.put("drawRectangle", drawRectangle);
        buttonKeys[0] = "drawRectangle";

        Button drawOval = new Button("Oval Çiz");
        drawOval.addActionListener(drawOval);
        buttons.put("drawOval", drawOval);
        buttonKeys[1] = "drawOval";

        Button drawWithPencil= new Button("Kalemle Çiz");
        drawWithPencil.addActionListener(drawWithPencil);
        buttons.put("drawWithPencil", drawWithPencil);
        buttonKeys[2] = "drawWithPencil";

        Button move = new Button("Taşı");
        move.addActionListener(move);
        buttons.put("move", move);
        buttonKeys[3] = "move";


        Button clear = new Button("Temizle");
        clear.addActionListener(clear);
        buttons.put("clear",clear);
        buttonKeys[4] = "clear";

    }

    public void fillThePanels(){

        colorPanel = new ColorPanel();

        colorPanel.setLayout(new GridLayout(1,7,55,150));

        buttonPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(1,6,55,150));

        abovePanel = new JPanel();

        abovePanel.setLayout(new GridLayout(4, 0,0,50));

        colorPanel.add(new JPanel());

        for(int i=0; i<colorKeys.length; i++) {
            colorPanel.add(colors.get(colorKeys[i]));
        }

        colorPanel.add(new JPanel());

        buttonPanel.add(new JPanel());

        for(int i=0; i<buttonKeys.length; i++) {
            buttonPanel.add(buttons.get(buttonKeys[i]));
        }

        buttonPanel.add(new JPanel());

        abovePanel.add(new JPanel());
        abovePanel.add(colorPanel);
        abovePanel.add(buttonPanel);
        middleLine.setBackground(chosenColor);
        abovePanel.add(middleLine);

    }

    public WindowFrame(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(2000,2000);

        this.setTitle("Paint Brush Program");

        this.setLayout(new BorderLayout());

        loadColourPanels();

        loadButtons();

        fillThePanels();

        this.drawAreaPanel = new DrawAreaPanel();

        drawAreaPanel.addMouseListener(drawAreaPanel);

        drawAreaPanel.addMouseMotionListener(drawAreaPanel);

        this.drawAreaPanel.setSize(500,300);

        this.add(abovePanel,BorderLayout.NORTH);

        this.add(drawAreaPanel,BorderLayout.CENTER);

    }


    private class DrawAreaPanel extends JPanel implements MouseInputListener{

        private ArrayList<Object> shapes = new ArrayList<>();

        private ArrayList<Color> shapesColor = new ArrayList<>();

        private RectangularShape movedItem = null;

        private Color movedColor = null;

        boolean isFound = false;

        private int startX=0, startY=0, endX=0, endY=0,oldPencilX,oldPencilY,newX,newY;

        @Override
        public void mouseClicked(MouseEvent e) {
        }



        @Override
        public void mousePressed(MouseEvent e) {

            if(isRectangle || isOval) {
                startX = e.getX();
                startY = e.getY();
                endX = startX;
                endY = startY;
            }

            else if(isMove){

                for(int i=shapes.size()-1; i>=0; i--){

                    if(shapes.get(i) instanceof Rectangle || shapes.get(i) instanceof Ellipse2D) {

                        RectangularShape shape = (RectangularShape) shapes.get(i);

                        if (shape.contains(e.getX(), e.getY())) {

                            movedItem = (RectangularShape) shapes.get(i);
                            isFound = true;
                            movedColor = shapesColor.get(i);
                            shapes.remove(i);
                            shapesColor.remove(i);
                            break;

                        }

                    }

                }

            }

        }



        @Override
        public void mouseReleased(MouseEvent e) {

            if(isRectangle){
                shapes.add(new Rectangle(Math.min(startX, endX),Math.min(startY,endY),Math.abs(endX - startX),Math.abs(endY-startY)));
                shapesColor.add(chosenColor);
            }
            else if(isOval){
                shapes.add(new Ellipse2D.Double(Math.min(startX, endX),Math.min(startY,endY),Math.abs(endX - startX),Math.abs(endY-startY)));
                shapesColor.add(chosenColor);
            }
            else if(isMove && isFound){

                if(movedItem instanceof Rectangle)
                   shapes.add(new Rectangle(endX,endY,(int) movedItem.getWidth(),(int) movedItem.getHeight()));
                else if(movedItem instanceof Ellipse2D)
                    shapes.add(new Ellipse2D.Double(endX,endY,(int) movedItem.getWidth(),(int) movedItem.getHeight()));

                shapesColor.add(movedColor);
                movedItem = null;
                isFound = false;
                movedColor = null;

            }

        }



        @Override
        public void mouseEntered(MouseEvent e) {
        }



        @Override
        public void mouseExited(MouseEvent e) {
        }



        @Override
        public void mouseDragged(MouseEvent e) {

            if(!isMoveWithPencil) {

                endX = e.getX();
                endY = e.getY();
                repaint();

            }
            if( isMove && isFound){

                repaint();

            }

            else if(isMoveWithPencil){

                Graphics g = getGraphics();
                Graphics2D g2 = (Graphics2D)g;

                newX = e.getX();
                newY = e.getY();

                ((Graphics2D) g).setStroke(new BasicStroke(5));
                g.setColor(chosenColor);
                g.drawLine(oldPencilX,oldPencilY,newX,newY);

                shapes.add(new MyLine(oldPencilX,oldPencilY,newX,newY));
                shapesColor.add(chosenColor);

                oldPencilX = newX;
                oldPencilY = newY;

            }

        }

        @Override
        public void mouseMoved(MouseEvent e) {

            oldPencilX = e.getX();
            oldPencilY = e.getY();

        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

                for(int i=0; i<shapes.size(); i++){

                    RectangularShape tempShape = null;
                    MyLine line = null;

                    if(shapes.get(i) instanceof Rectangle || shapes.get(i) instanceof Ellipse2D)
                        tempShape = (RectangularShape ) shapes.get(i);

                    else if(shapes.get(i) instanceof MyLine)
                        line = (MyLine) shapes.get(i);

                    if(shapes.get(i) instanceof Rectangle){

                        g.setColor(shapesColor.get(i));
                        g.fillRect((int)tempShape.getX(),(int)tempShape.getY(),(int)tempShape.getWidth(),(int)tempShape.getHeight());

                    }
                    else if(shapes.get(i) instanceof Ellipse2D){

                        g.setColor(shapesColor.get(i));
                        g.fillOval((int)tempShape.getX(), (int)tempShape.getY(),(int) tempShape.getWidth(), (int)tempShape.getHeight());

                    }
                    else if(shapes.get(i) instanceof MyLine){

                        ((Graphics2D) g).setStroke(new BasicStroke(5));

                        g.setColor(shapesColor.get(i));
                        g.drawLine(line.oldx,line.oldy,line.newx,line.newy);

                    }
                }


                if(isRectangle){

                    g.setColor(chosenColor);
                    g.fillRect(Math.min(startX, endX),Math.min(startY,endY),Math.abs(endX - startX),Math.abs(endY-startY));

                }
                else if(isOval){

                    g.setColor(chosenColor);
                    g.fillOval(Math.min(startX, endX),Math.min(startY,endY),Math.abs(endX - startX),Math.abs(endY-startY));

                }
                else if(isMove && isFound){

                    g.setColor(movedColor);

                    if(movedItem instanceof Rectangle)
                       g.fillRect(endX,endY,(int) movedItem.getWidth(), (int) movedItem.getHeight());

                    else if(movedItem instanceof  Ellipse2D.Double)
                        g.fillOval(endX,endY,(int) movedItem.getWidth(), (int) movedItem.getHeight());

                }
               
                
        }

    }

    private class ColorPanel extends JPanel implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {

            chosenColor = this.getBackground();
            middleLine.setBackground(chosenColor);

        }

        @Override
        public void mousePressed(MouseEvent e) { }

        @Override
        public void mouseReleased(MouseEvent e) { }

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) { }

    }

    private class Button extends JButton implements ActionListener{

        public Button(String action){
            super(action);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            String command = e.getActionCommand();

            if(command.equals("Dikdörtgen Çiz")){

                isRectangle = true;
                isOval = false;
                isMove = false;
                isMoveWithPencil = false;

            }
            else if(command.equals("Oval Çiz")){

                isOval = true;
                isRectangle = false;
                isMove = false;
                isMoveWithPencil = false;

            }
            else if(command.equals("Kalemle Çiz")){

                isMoveWithPencil = true;
                isRectangle = false;
                isOval = false;
                isMove = false;

            }
            else if(command.equals("Taşı")){

                isMove = true;
                isRectangle = false;
                isOval = false;
                isMoveWithPencil = false;

            }
            else if(command.equals("Temizle")){

                isMove = false;
                isRectangle = false;
                isOval = false;
                isMoveWithPencil = false;

                for (int i=0; i<drawAreaPanel.shapes.size(); i++){

                    drawAreaPanel.shapes.set(i,null);
                    drawAreaPanel.shapesColor.set(i,null);

                }

                drawAreaPanel.repaint();

            }



        }


    }

    private class MyLine  {

        private int oldx,oldy,newx,newy;

        public MyLine(int oldx,int oldy,int newx,int newy){
            this.newx = newx;
            this.newy = newy;
            this.oldx = oldx;
            this.oldy = oldy;
        }

    }







    public static void main(String[] args) {


        WindowFrame w = new WindowFrame();
        w.setVisible(true);
       

    }



}