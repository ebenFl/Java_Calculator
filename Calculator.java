import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener{
	
	JFrame frame;
	JPanel topPanel;
	JPanel middlePanel;
	JPanel bottomPanel;
	JTextField textfield;
	JButton[] numberButtons = new JButton[10];
	JButton[] functionButtons = new JButton[9];
	JButton add, sub, mul, div;
	JButton dec, equ, del, clr, neg;
	JPanel panel;
	
	Font myfont = new Font("Monospaced", Font.BOLD, 30);
	
	double num1=0;
	double num2=0;
	String operator = ""; // holds operation to perform on num1 and num2
	
	Calculator() {
		
		frame = new JFrame("My Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 600);
		frame.setLayout(new BorderLayout(10, 10));
		
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(100, 100));
		
		topPanel.add(new JPanel(), BorderLayout.EAST);
		topPanel.add(new JPanel(), BorderLayout.WEST);

		textfield = new JTextField("0");
		textfield.setFont(myfont);
		textfield.setEditable(false);
		textfield.setBorder(null);
		textfield.setHorizontalAlignment(JTextField.RIGHT);
		topPanel.add(textfield, BorderLayout.CENTER);
		
		middlePanel = new JPanel();
		middlePanel.setLayout(new GridLayout(4, 4, 10, 10));
		
		// create functionality buttons
		add = new JButton("+");
		sub = new JButton("-");
		mul = new JButton("*");
		div = new JButton("/");
		dec = new JButton(".");
		equ = new JButton("=");
		del = new JButton("del");
		clr = new JButton("clr");
		neg = new JButton("(-)");

		functionButtons[0] = add;
		functionButtons[1] = sub;
		functionButtons[2] = mul;
		functionButtons[3] = div;
		functionButtons[4] = dec;
		functionButtons[5] = equ;
		functionButtons[6] = del;
		functionButtons[7] = clr;
		functionButtons[8] = neg;

		for(int i = 0; i < 9; i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(myfont);
			functionButtons[i].setFocusable(false);
		}
		
		for (int i = 0; i < 10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(myfont);
			numberButtons[i].setFocusable(false);
		}
		
		middlePanel.add(numberButtons[1]);
		middlePanel.add(numberButtons[2]);
		middlePanel.add(numberButtons[3]);
		middlePanel.add(add);

		middlePanel.add(numberButtons[4]);
		middlePanel.add(numberButtons[5]);
		middlePanel.add(numberButtons[6]);
		middlePanel.add(sub);

		middlePanel.add(numberButtons[7]);
		middlePanel.add(numberButtons[8]);
		middlePanel.add(numberButtons[9]);
		middlePanel.add(mul);

		middlePanel.add(dec);
		middlePanel.add(numberButtons[0]);
		middlePanel.add(div);
		middlePanel.add(equ);

		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,3, 10, 10));
		
		bottomPanel.add(neg);
		bottomPanel.add(del);
		bottomPanel.add(clr);

		
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator calc = new Calculator();
	}
	
	public void handleOperator(String num, String op) {
		if (operator.equals("")) {
			num1 = Double.parseDouble(num);
			operator = op;
			textfield.setText("0");
		}
	}
	
	public void evaluate(String num) {
		num2 = Double.parseDouble(num);
		switch (operator) {
			case "-":
				num1 = num1 - num2;
				break;
			case "+":
				num1 = num1 + num2;
				break;
			case "*":
				num1 = num1 * num2;
				break;
			case "/":
				num1 = num1 / num2;
				break;
		}
		textfield.setText(Double.toString(num1));
		num2 = 0;
		operator = "";
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// check if number button was clicked
		for (int i=0; i < 10; i++) {
			if(e.getSource() == numberButtons[i]) {
				String curNum = textfield.getText();
				if (curNum.equals("0")) {
					textfield.setText(numberButtons[i].getText());
				}
				else {
					textfield.setText(textfield.getText().concat(numberButtons[i].getText()));
				}
			}
		}
		
		if (e.getSource() == dec) {
			String n = textfield.getText();
			if(!n.equals("") && !n.contains(".")) {
				textfield.setText(textfield.getText().concat("."));
			}
			
		}
		
		if (e.getSource() == del) {
			String cur = textfield.getText();
			if (cur.length() > 1) {
				textfield.setText(cur.substring(0, cur.length() - 1));
				// get rid of negative sign and switch to zero if textfield
				// would be "-"
				if(textfield.getText().equals("-")) {
					textfield.setText("0");
				}
			}
			else if (cur.length() == 1) {
				textfield.setText("0");
			}
		}
		
		if (e.getSource() == add) {
			handleOperator(textfield.getText(), "+");
		}
		
		if (e.getSource() == mul) {
			handleOperator(textfield.getText(),"*");
		}
		
		if (e.getSource() == div) {
			handleOperator(textfield.getText(), "/");
		}
		
		if (e.getSource() == sub) {
			handleOperator(textfield.getText(), "-");
		}
		
		if (e.getSource() == equ) {
			evaluate(textfield.getText());
		}
		
		if (e.getSource() == neg) {
			String n = textfield.getText();
			if(n.length() > 0) {
				if(n.charAt(0) == '-') {
					textfield.setText(n.substring(1));
				}
				else {
					textfield.setText("-" + n);
				}
			}
		}
		
		if (e.getSource() == clr) {
			num1 = 0;
			num2 = 0;
			operator = "";
			textfield.setText("0");
		}
	}
}
