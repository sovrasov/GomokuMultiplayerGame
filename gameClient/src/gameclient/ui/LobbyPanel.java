package gameclient.ui;

import java.awt.GridBagLayout;
import javax.swing.GroupLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class LobbyPanel extends GameClientView<JPanel> implements Observer {

    public LobbyPanel(GameClientController controller) {
        super(new JPanel(), controller);
        controller.getModel().addObserver(this);
        getComponent().setLayout(
            new GridBagLayout());

        JPanel controls = new JPanel();
        GroupLayout layout = new GroupLayout(controls);
        controls.setLayout(layout);

        JLabel opponentsListLabel = new JLabel("Available players:");
        opponentsListModel = new DefaultListModel();
        opponentsList = new JList(opponentsListModel);
        opponentsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        opponentsList.setLayoutOrientation(JList.VERTICAL);
        opponentsList.setVisibleRowCount(7);
        JScrollPane opponentsListScrollPane = new JScrollPane(opponentsList);

        inviteButton = new JButton("Invite");
        inviteButton.addActionListener(
            controller.new InvitePlayerListener(this));

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(opponentsListLabel)
            .addComponent(opponentsListScrollPane)
            .addComponent(inviteButton)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(opponentsListLabel)
            .addComponent(opponentsListScrollPane)
            .addComponent(inviteButton)
        );

        getComponent().add(controls);
    }

    public String getSelectedOpponentsName() {
        return (String) opponentsList.getSelectedValue();
    }

    @Override
    public void update(Observable o, Object o1) {
        GameClientModel gameClientModel = (GameClientModel) o;
        opponentsListModel.removeAllElements();
        for (String playerName : gameClientModel.getOtherPlayersNamesList()) {
            opponentsListModel.addElement(playerName);
        }
    }


    private final JList opponentsList;
    private final DefaultListModel opponentsListModel;
    private final JButton inviteButton;
}
