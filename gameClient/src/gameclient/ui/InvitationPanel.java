package gameclient.ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InvitationPanel extends GameClientView<JPanel> {

    public InvitationPanel(GameClientController controller) {
        super(new JPanel(), controller);

        invitationTemplate =
            "Player %1$ challenged you to a game of Gomoku.\nAccept?";
        invitationLabel = new JLabel();
        acceptButton = new JButton("Accept");
        acceptButton.addActionListener(
            controller.new AcceptInvitationListener());
        rejectButton = new JButton("Reject");
        rejectButton.addActionListener(
            controller.new RejectInvitationListener());

        getComponent().add(invitationLabel);
        getComponent().add(acceptButton);
        getComponent().add(rejectButton);
    }

    public void updateInvitation(String opponentName) {
        invitationLabel.setText(
            String.format(invitationTemplate, opponentName));
    }

    private final String invitationTemplate;
    private final JLabel invitationLabel;
    private final JButton acceptButton;
    private final JButton rejectButton;
}
