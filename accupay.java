import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.text.DecimalFormat;

class accuFrame extends JFrame {
    
    //list of choices for state selection drop-down
    String[] stateList = {"", "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
    "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
    "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",
    "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",
    "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
    "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
    "West Virginia", "Wisconsin", "Wyoming", "EXEMPT"};
    
    //list of choises for bracket selection drop down
    String[] bracketList = {"", "$0 to $9,950", "$9,951 to $40,525", "$40,526 to $86,375",
    "$86,376 to $164,925", "$164,926 to $209,425", "$209,426 to $523,600", "$523,601 or more", "EXEMPT"};

    //initializing the input variables
    private double payRateInput, hoursWorkedInput;
    private String chosenState, chosenBracket;

    //getters and setters
    public void setPayRate(double payRateIn) { payRateInput = payRateIn; }
    public double getPayRate() { return payRateInput; }
    public void setHoursWorked(double hoursWorkedIn) { hoursWorkedInput = hoursWorkedIn; }
    public double getHoursWorked() { return hoursWorkedInput; }
    public void setChosenState(String chosenStateIn) { chosenState = chosenStateIn; }
    public String getChosenState() {return chosenState; }

    //constructor creates the frame and populates it with all the elements
    public accuFrame() {
    
            JFrame.setDefaultLookAndFeelDecorated(true);
            setBounds(800, 300, 370, 295);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //creates a container with a default Flow Layout
            Container contentPane = getContentPane();
            contentPane.setLayout(new FlowLayout());

            //label and text field elements for pay rate are created
            JLabel payRateLabel = new JLabel("Enter Pay Rate");
            JTextField payRateField = new JTextField(12);

            //panel is added to container and elements are added to the panel
            JPanel payRatePanel = new JPanel();
            contentPane.add(payRatePanel);
            payRatePanel.add(payRateLabel);
            payRatePanel.add(payRateField);

            //label and text field elements for hours worked are created
            JLabel hoursWorkedLabel = new JLabel("Enter Hours Worked");
            JTextField hoursWorkedField = new JTextField(12);

            //panel is added to container and elements are added to the panel
            JPanel hoursWorkedPanel = new JPanel();
            contentPane.add(hoursWorkedPanel);
            hoursWorkedPanel.add(hoursWorkedLabel);
            hoursWorkedPanel.add(hoursWorkedField);

            JLabel stateSelectLabel = new JLabel("Select U.S. State");
            JComboBox<String> stateSelectDropDown = new JComboBox<>(stateList);
            
            JPanel stateSelectPanel = new JPanel();
            contentPane.add(stateSelectPanel);
            stateSelectPanel.add(stateSelectLabel);
            stateSelectPanel.add(stateSelectDropDown);

            JLabel bracketSelectLabel = new JLabel("Select Tax Bracket");
            JComboBox<String> bracketDropDown = new JComboBox<>(bracketList);
            
            JPanel bracketSelectPanel = new JPanel();
            contentPane.add(bracketSelectPanel);
            bracketSelectPanel.add(bracketSelectLabel);
            bracketSelectPanel.add(bracketDropDown);

            JButton calcButton = new JButton("Calculate");
            JButton resetButton = new JButton("Reset");
    
            //the button panel that features both calculate and reset has it's own layout
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BorderLayout(30, 30));
            contentPane.add(buttonPanel);
            buttonPanel.add(calcButton, BorderLayout.EAST);
            buttonPanel.add(resetButton, BorderLayout.WEST);
           
            JTextField grossField = new JTextField(6);
            JTextField taxesField = new JTextField(6);
            JTextField netPayField = new JTextField(6);
            JLabel grossPayLabel = new JLabel("Gross: ");
            JLabel taxesLabel = new JLabel("Taxes: ");
            JLabel netPayLabel = new JLabel("Net: ");
            

            JPanel resultPanel = new JPanel();
            resultPanel.setLayout(new FlowLayout());
            contentPane.add(resultPanel);
            resultPanel.add(grossPayLabel);
            resultPanel.add(grossField);
            resultPanel.add(taxesLabel);
            resultPanel.add(taxesField);
            resultPanel.add(netPayLabel);
            resultPanel.add(netPayField);

            JPanel disclaimerPanel = new JPanel();
            JLabel disclaimerLabel = new JLabel("Disclaimer: Results might not be accurate");
            contentPane.add(disclaimerPanel);
            disclaimerPanel.add(disclaimerLabel);
            
            //focus listener records response into a variable once focus leaves the text field
            payRateField.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent e) {
                }
                public void focusLost(FocusEvent e) {
                    payRateInput = Double.parseDouble(payRateField.getText());
                }
            });
    
            hoursWorkedField.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent e) {
                }
                public void focusLost(FocusEvent e) {
                    hoursWorkedInput = Double.parseDouble(hoursWorkedField.getText());
                }
            });
            
            //action listener calls the calc method passing in the two needed variables.
            calcButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    double netPay = calc.netPay(payRateInput, hoursWorkedInput);
                    double grossAmt = calc.getGross();
                    double taxAmt = calc.getTax();

                    //DecimalFormat library converts the large double value into something more manageable
                    //it also sets the result field to the final calculation
                    DecimalFormat df = new DecimalFormat("###.##");
                    String gross = String.valueOf(df.format(grossAmt));
                    String tax = String.valueOf(df.format(taxAmt));
                    String net = String.valueOf(df.format(netPay));
                    grossField.setText("$" + gross);
                    taxesField.setText("$" + tax);
                    netPayField.setText("$" + net);
                }
            });

            //reset button action listener sets all field empty so a new calculation can be done
            resetButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    payRateField.setText("");
                    hoursWorkedField.setText("");
                    grossField.setText("");
                    taxesField.setText("");
                    netPayField.setText("");
                    payRateInput = 0;
                    hoursWorkedInput = 0;
                    stateSelectDropDown.setSelectedItem(stateList[0]);
                    bracketDropDown.setSelectedItem(bracketList[0]);
                }
            });

            //drop down action listeners send the chosen value to a method in calc
            //so it can be assigned and used
            stateSelectDropDown.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent e) {
                }
                public void focusLost(FocusEvent e) {
                    chosenState = stateSelectDropDown.getSelectedItem().toString();
                    calc.setStateTax(chosenState);
                }
            });

            bracketDropDown.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent e) {
                }
                public void focusLost(FocusEvent e) {
                    chosenBracket = bracketDropDown.getSelectedItem().toString();
                    calc.setFedTax(chosenBracket);
                }
            });
        }
    }
public class accupay {
    public static void main(String[] args) {
        //creates the frame and opens it
        accuFrame myPayFrame = new accuFrame();
        myPayFrame.setTitle("AccuPay (Version 1.0)");
        myPayFrame.setVisible(true);
    }
}
