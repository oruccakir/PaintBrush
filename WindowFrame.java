import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowFrame extends JFrame{

    private HashMap<String,JPanel> colors = new HashMap<>();

    private String colorKeys[] = null;

    private HashMap<String,JButton> buttons = new HashMap<>();

    private String buttonKeys[] = null;

    private JPanel drawAreaPanel = null;

    private JPanel buttonPanel = null;

    private JPanel colorPanel = null;

    private JPanel abovePanel = null;

    public void loadColourPanels(){

        colorKeys = new String[7];

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.BLUE);
        colors.put("bluePanel", bluePanel);
        colorKeys[0] = "bluePanel";

        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        colors.put("redPanel", redPanel);
        colorKeys[1] = "redPanel";

        JPanel greenPanel = new JPanel();
        greenPanel.setBackground(Color.GREEN);
        colors.put("greenPanel", greenPanel);
        colorKeys[2] = "greenPanel";

        JPanel yellowPanel = new JPanel();
        yellowPanel.setBackground(Color.YELLOW);
        colors.put("yellowPanel", yellowPanel);
        colorKeys[3] = "yellowPanel";

        JPanel orangePanel = new JPanel();
        orangePanel.setBackground(Color.ORANGE);
        colors.put("orangePanel", orangePanel);
        colorKeys[4] = "orangePanel";

        JPanel magentaPanel = new JPanel();
        magentaPanel.setBackground(Color.MAGENTA);
        colors.put("magentaPanel", magentaPanel);
        colorKeys[5] = "magentaPanel";

        JPanel blackJPanel = new JPanel();
        blackJPanel.setBackground(Color.BLACK);
        colors.put("blackPanel", blackJPanel);
        colorKeys[6] = "blackPanel";

    }

    public void loadButtons(){

        buttonKeys = new String[4];

        JButton drawRectangle = new JButton("Dikdörten Çiz");
        buttons.put("drawRectangle", drawRectangle);
        buttonKeys[0] = "drawRectangle";

        JButton drawOval = new JButton("Oval Çiz");
        buttons.put("drawOval", drawOval);
        buttonKeys[1] = "drawOval";

        JButton drawWithPencil= new JButton("Kalemle Çiz");
        buttons.put("drawWithPencil", drawWithPencil);
        buttonKeys[2] = "drawWithPencil";

        JButton move = new JButton("Taşı");
        buttons.put("move", move);
        buttonKeys[3] = "move";

    }

    public void fillThePanels(){

        colorPanel = new JPanel();

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
        JPanel blueLine = new JPanel();
        blueLine.setBackground(Color.BLUE);
        abovePanel.add(blueLine);

        

        



    }

    public WindowFrame(){

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(2000,2000);
        this.setTitle("Paint Brush Program");
        this.setLayout(new BorderLayout());

        loadColourPanels();

        loadButtons();

        fillThePanels();

        this.drawAreaPanel = new JPanel();
        this.drawAreaPanel.setSize(500,300);

        
        this.add(abovePanel,BorderLayout.NORTH);
        this.add(drawAreaPanel,BorderLayout.CENTER);

    }




    public static void main(String[] args) {
        
        WindowFrame w = new WindowFrame();

        w.setVisible(true);

    }



}