package gameclient.ui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class LobbyPanel extends GameClientView<JPanel> implements Observer {

    public LobbyPanel(GameClientController controller) {
        super(new JPanel(), controller);
        controller.getModel().addObserver(this);

        getComponent().setLayout(
            new BoxLayout(getComponent(), BoxLayout.Y_AXIS));

        opponentsListModel = new DefaultListModel();
        opponentsList = new JList(opponentsListModel);
        opponentsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        opponentsList.setLayoutOrientation(JList.VERTICAL);
        opponentsList.setVisibleRowCount(-1);

        inviteButton = new JButton("Invite");
        inviteButton.addActionListener(
            controller.new InvitePlayerListener(this));

        getComponent().add(new JScrollPane(opponentsList));
        getComponent().add(inviteButton);
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
