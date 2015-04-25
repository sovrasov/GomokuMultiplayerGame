package gameclient.ui;

import java.awt.GridBagLayout;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RegistrationPanel extends GameClientView<JPanel> {
    public RegistrationPanel(GameClientController controller) {
        super(new JPanel(), controller);
        getComponent().setLayout(new GridBagLayout());

        JPanel controls = new JPanel();
        GroupLayout layout = new GroupLayout(controls);
        controls.setLayout(layout);

        loginLabel = new JLabel("Your nickname:");
        loginTextField = new JTextField();
        loginButton = new JButton("Log in");
        loginButton.addActionListener(controller.new LoginListener(this));

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(loginLabel)
            .addComponent(loginTextField, 200,
                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(loginButton)
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(loginLabel)
            .addComponent(loginTextField, GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(loginButton)
        );

        getComponent().add(controls);
    }

    public String getLoginTextFieldContents() {
        return loginTextField.getText();
    }


    private final JLabel loginLabel;
    private final JTextField loginTextField;
    private final JButton loginButton;
}
