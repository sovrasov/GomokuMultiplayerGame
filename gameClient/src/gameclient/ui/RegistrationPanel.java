package gameclient.ui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RegistrationPanel extends GameClientView<JPanel> {
    public RegistrationPanel(GameClientController controller) {
        super(new JPanel(), controller);
        getComponent().setLayout(new BoxLayout(getComponent(),
            BoxLayout.Y_AXIS));

        loginLabel = new JLabel("Your nickname:");
        loginTextField = new JTextField();
        loginButton = new JButton("Log in");
        loginButton.addActionListener(controller.new LoginListener(this));

        getComponent().add(loginLabel);
        getComponent().add(loginTextField);
        getComponent().add(loginButton);
    }

    public String getLoginTextFieldContents() {
        return loginTextField.getText();
    }


    private final JLabel loginLabel;
    private final JTextField loginTextField;
    private final JButton loginButton;
}
