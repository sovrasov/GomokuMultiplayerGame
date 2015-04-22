package gameclient.ui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RegistrationPanel extends JPanel {
    public RegistrationPanel(GameClientController controller) {
        this.controller = controller;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        loginLabel = new JLabel("Your nickname:");
        loginTextField = new JTextField();
        loginButton = new JButton("Log in");
        loginButton.addActionListener(controller.new LoginListener(this));

        this.add(loginLabel);
        this.add(loginTextField);
        this.add(loginButton);
    }

    public String getLoginTextFieldContents() {
        return loginTextField.getText();
    }


    private final GameClientController controller;
    private final JLabel loginLabel;
    private final JTextField loginTextField;
    private final JButton loginButton;
}
