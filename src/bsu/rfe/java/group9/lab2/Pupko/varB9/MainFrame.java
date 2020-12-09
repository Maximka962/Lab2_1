package bsu.rfe.java.group9.lab2.Pupko.varB9;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;а
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MainFrame extends JFrame{

    private static final int WIDTH = 560;
    private static final int HEIGHT = 450;

    private static JTextField textFieldX;
    private static JTextField textFieldY;
    private static JTextField textFieldZ;

    private static JTextField textFieldResult;

    private ButtonGroup radioButtons = new ButtonGroup();
    private ButtonGroup memRadioButtons = new ButtonGroup();

    private Box rboxFormulaType = Box.createHorizontalBox();
    private Box rboxVariables = Box.createHorizontalBox();

    private int funcId = 1;
    private int memId = 1;
    private static final double PI = Math.PI;

    public double Func1(double mem1, double mem2, double mem3){
        return (Math.sqrt(Math.pow(Math.sin(mem2)+Math.pow(mem2,2)+Math.pow(Math.E,Math.cos(mem2)),2))+Math.pow(Math.exp(Math.pow(mem3,2))+Math.sin(PI*Math.pow(mem1,2)),3));
    }

    public double Func2(double mem1, double mem2, double mem3){
        return mem1*(Math.pow(Math.cos(Math.pow(mem2,2)),3))/Math.pow(mem3,1/mem1);
    }

    private void addRadioButton(String buttonName, final int funcId){
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.funcId = funcId;
            }
        });
        radioButtons.add(button);
        rboxFormulaType.add(button);
    }

    private void addRadioButtonForMem(String buttonName, final int memId){
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.memId = memId;
            }
        });
        memRadioButtons.add(button);
        rboxVariables.add(button);
    }

    public MainFrame(){
        super("calculating the formula");
        setSize(WIDTH,HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();

        setLocation((kit.getScreenSize().width-WIDTH)/2,(kit.getScreenSize().height-HEIGHT)/2);

        rboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Function1 ", 1);
        rboxFormulaType.add(Box.createHorizontalStrut(100));
        addRadioButton("Function2 ", 2);
        radioButtons.setSelected(
                radioButtons.getElements().nextElement().getModel(),true
        );
        rboxFormulaType.add(Box.createHorizontalGlue());
        rboxFormulaType.setBorder(
                BorderFactory.createLineBorder(Color.magenta)
        );
        //
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0",10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0",10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0",10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        rboxVariables.setBorder(
                BorderFactory.createLineBorder(Color.red)
        );
        rboxVariables.add(Box.createHorizontalGlue());
        rboxVariables.add(Box.createHorizontalStrut(40));
        addRadioButtonForMem("set ", 1);
        rboxVariables.add(labelForX);
        rboxVariables.add(Box.createHorizontalStrut(10));
        rboxVariables.add(textFieldX);
        rboxVariables.add(Box.createHorizontalStrut(40));
        addRadioButtonForMem("set ",2);
        rboxVariables.add(labelForY);
        rboxVariables.add(Box.createHorizontalStrut(10));
        rboxVariables.add(textFieldY);
        rboxVariables.add(Box.createHorizontalStrut(40));
        addRadioButtonForMem("set ",3);
        rboxVariables.add(labelForZ);
        rboxVariables.add(Box.createHorizontalStrut(10));
        rboxVariables.add(textFieldZ);
        rboxVariables.add(Box.createHorizontalStrut(40));
        memRadioButtons.setSelected(
                memRadioButtons.getElements().nextElement().getModel(),true
        );
        rboxVariables.add(Box.createHorizontalGlue());

        JLabel resultLabel = new JLabel("Result: ");
        textFieldResult = new JTextField("0",20);
        textFieldResult.setMaximumSize(
                textFieldResult.getPreferredSize()
        );
        Box rboxResult = Box.createHorizontalBox();
        rboxResult.add(Box.createHorizontalGlue());
        rboxResult.add(resultLabel);
        rboxResult.add(Box.createHorizontalStrut(10));
        rboxResult.add(textFieldResult);
        rboxResult.add(Box.createHorizontalGlue());
        rboxResult.setBorder(BorderFactory.createLineBorder(Color.blue));

        JButton buttonCalc = new JButton("Calculate");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if(funcId == 1)
                        result = Func1(x,y,z);
                    else
                        result = Func2(x,y,z);
                    textFieldResult.setText(result.toString());
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Mistake in float format reading","Wrong number format",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Reset");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });
        JButton buttonM = new JButton("M+");
        buttonM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result = Double.parseDouble(textFieldResult.getText());
                    if(memId == 1)
                        result += x;
                    else if (memId == 2)
                        result += y;
                    else if (memId == 3)
                        result += z;
                    textFieldResult.setText(result.toString());
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Mistake in float format reading","Wrong number format",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonMReset = new JButton("MC");
        buttonMReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (memId == 1)
                    textFieldX.setText("0");
                else if (memId == 2)
                    textFieldY.setText("0");
                else
                    textFieldZ.setText("0");
            }
        });
        Box rboxButtons = Box.createHorizontalBox();
        rboxButtons.add(Box.createHorizontalGlue());
        rboxButtons.add(buttonM);
        rboxButtons.add(Box.createHorizontalStrut(30));
        rboxButtons.add(buttonMReset);
        rboxButtons.add(Box.createHorizontalStrut(30));
        rboxButtons.add(buttonCalc);
        rboxButtons.add(Box.createHorizontalStrut(30));
        rboxButtons.add(buttonReset);
        rboxButtons.add(Box.createHorizontalGlue());
        rboxButtons.setBorder(
                BorderFactory.createLineBorder(Color.pink)
        );

        Box rboxTitle = Box.createHorizontalBox();
        JLabel mainLable = new JLabel("MATH.H");
        rboxTitle.add(Box.createHorizontalGlue());
        rboxTitle.add(mainLable);
        rboxTitle.add(Box.createHorizontalGlue());


        Box titleBox = Box.createVerticalBox();
        titleBox.add(Box.createVerticalGlue());
        titleBox.add(rboxTitle);
        titleBox.add(Box.createVerticalGlue());
        getContentPane().add(titleBox, BorderLayout.NORTH);

        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(rboxFormulaType);
        contentBox.add(rboxVariables);
        contentBox.add(rboxResult);
        contentBox.add(rboxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
    public static void main(String[] args){
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
