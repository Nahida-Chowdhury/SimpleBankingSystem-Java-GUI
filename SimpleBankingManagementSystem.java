import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class SimpleBankingManagementSystem extends JFrame implements ActionListener{
    private Container c;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JTextField emailTextField;
    private JPasswordField passwordTextField;
    private JButton loginButton;
    private JButton signupButton;
    private JLabel messageLabel;

    SimpleBankingManagementSystem()
    {
        initComponents();
    }
    public void initComponents()
    {
        emailLabel = new JLabel();
        passwordLabel = new JLabel();
        emailTextField = new JTextField();
        passwordTextField = new JPasswordField();
        loginButton = new JButton("Login");
        signupButton = new JButton("SignUp");
        messageLabel = new JLabel();

        c = this.getContentPane();
        c.setLayout(null);
        c.add(emailLabel);
        c.add(passwordLabel);
        c.add(emailTextField);
        c.add(passwordTextField);
        c.add(loginButton);
        c.add(signupButton);
        c.add(messageLabel);

        emailLabel.setBounds(0, 0, 150, 50);
        emailLabel.setHorizontalAlignment(JTextField.CENTER);
        passwordLabel.setBounds(0, 0, 150, 150);
        passwordLabel.setHorizontalAlignment(JTextField.CENTER);

        emailTextField.setBounds(115, 5, 205, 30);
        passwordTextField.setBounds(115, 60, 205, 30);
        loginButton.setBounds(115, 110, 100, 30);
        signupButton.setBounds(220, 110, 100, 30);
        messageLabel.setBounds(100, 200, 250, 30);

        c.setBackground(Color.lightGray);

        emailLabel.setText("Email: ");
        passwordLabel.setText("Password: ");
        loginButton.addActionListener(this);
        signupButton.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String email = emailTextField.getText();
            String password = new String(passwordTextField.getPassword());

            if ("user@example.com".equals(email) && "password".equals(password)) {
                messageLabel.setText("Login successful!");
                AccountInfoPage page = new AccountInfoPage();
                page.setVisible(true);

                double accountBalance = 5000.0;
                String accountInfo = "Account Holder: John Doe\n" +
                        "Account Number: 123456\n" +
                        "Account Type: Savings";

                page.displayAccountInfo(accountBalance, accountInfo);
            } else {
                messageLabel.setText("Invalid email or password.");
            }
        } else if (e.getSource() == signupButton) {
            SignupPage signupPage = new SignupPage();
            signupPage.setVisible(true);
            messageLabel.setText("Signup successful!");
        }
}
    public static void main(String[] args) {
        SimpleBankingManagementSystem frame = new SimpleBankingManagementSystem();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 50, 400, 350);
        frame.setTitle("BankingManagementSystem");
        frame.setResizable(false);
    }
}


class AccountInfoPage extends JFrame implements ActionListener {
    private JLabel balanceLabel;
    private JLabel infoLabel;
    private JButton depositButton;
    private JButton withdrawButton;

    private double currentBalance = 0.0;

    AccountInfoPage() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Account Information");
        setSize(400, 250);
        setLayout(null);

        balanceLabel = new JLabel();
        infoLabel = new JLabel();
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");

        balanceLabel.setBounds(20, 20, 300, 30);
        infoLabel.setBounds(20, 60, 300, 80);
        depositButton.setBounds(20, 150, 150, 30);
        withdrawButton.setBounds(180, 150, 150, 30);

        add(balanceLabel);
        add(infoLabel);
        add(depositButton);
        add(withdrawButton);

        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
    }

    public void displayAccountInfo(double balance, String accountInfo) {
        currentBalance = balance;
        balanceLabel.setText("Account Balance: $" + balance);
        infoLabel.setText("Account Information:\n" + accountInfo);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == depositButton) {
            String depositAmountString = JOptionPane.showInputDialog(this, "Enter deposit amount:");
            if (depositAmountString != null && !depositAmountString.isEmpty()) {
                try {
                    double depositAmount = Double.parseDouble(depositAmountString);
                    if (depositAmount > 0) {
                        currentBalance += depositAmount;
                        balanceLabel.setText("Account Balance: $" + currentBalance);
                        JOptionPane.showMessageDialog(this, "Deposit successful. New balance: $" + currentBalance, "Deposit", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid deposit amount. Please enter a positive number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid deposit amount. Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == withdrawButton) {
            String withdrawAmountString = JOptionPane.showInputDialog(this, "Enter withdraw amount:");
            if (withdrawAmountString != null && !withdrawAmountString.isEmpty()) {
                try {
                    double withdrawAmount = Double.parseDouble(withdrawAmountString);
                    if (withdrawAmount > 0 && withdrawAmount <= currentBalance) {
                        currentBalance -= withdrawAmount;
                        balanceLabel.setText("Account Balance: $" + currentBalance);
                        JOptionPane.showMessageDialog(this, "Withdrawal successful. New balance: $" + currentBalance, "Withdraw", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid withdraw amount. Please enter a valid amount within your balance.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid withdraw amount. Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}

class SignupPage extends JFrame implements ActionListener {
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JTextField emailTextField;
    private JPasswordField passwordTextField;
    private JButton signupButton;

    SignupPage() {
        setTitle("Signup");
        setSize(300, 200);
        setLayout(null);

        emailLabel = new JLabel("Email:");
        passwordLabel = new JLabel("Password:");
        emailTextField = new JTextField();
        passwordTextField = new JPasswordField();
        signupButton = new JButton("Sign Up");

        emailLabel.setBounds(50, 30, 80, 25);
        passwordLabel.setBounds(50, 60, 80, 25);
        emailTextField.setBounds(120, 30, 150, 25);
        passwordTextField.setBounds(120, 60, 150, 25);
        signupButton.setBounds(100, 100, 100, 25);

        add(emailLabel);
        add(passwordLabel);
        add(emailTextField);
        add(passwordTextField);
        add(signupButton);

        signupButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            String email = emailTextField.getText();
            String password = new String(passwordTextField.getPassword());

            JOptionPane.showMessageDialog(this, "Email: " + email + "\nPassword: " + password, "Signup Successful", JOptionPane.INFORMATION_MESSAGE);
            emailTextField.setText("");
            passwordTextField.setText("");

            dispose();
        }
    }
}
