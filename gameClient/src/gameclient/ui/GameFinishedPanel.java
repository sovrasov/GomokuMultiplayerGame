package gameclient.ui;

import gamecore.GameResult;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameFinishedPanel extends GameClientView<JPanel>
    implements Observer {

    public GameFinishedPanel(GameClientController controller) {
        super(new JPanel(), controller);
        controller.getModel().addObserver(this);

        getComponent().setLayout(new GridBagLayout());

        JPanel controls = new JPanel();
        GroupLayout layout = new GroupLayout(controls);
        controls.setLayout(layout);

        resultLabel = new JLabel();
        toLobbyButton = new JButton("Back to lobby");
        toLobbyButton.addActionListener(controller.new GoToLobbyListener());

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(resultLabel)
            .addComponent(toLobbyButton)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(resultLabel)
            .addComponent(toLobbyButton)
        );

        getComponent().add(controls);
    }

    @Override
    public void update(Observable o, Object o1) {
        GameClientModel model = (GameClientModel) o;

        GameResult lastGameResult = model.getLastGameResult();
        if (lastGameResult == null) {
            return;
        }

        switch (model.getLastGameResult()) {
        case WIN:
            resultLabel.setText(victoryText);
            break;
        case LOOSE:
            resultLabel.setText(defeatText);
            break;
        case RIVAL_LEAVED:
            resultLabel.setText(dropText);
            break;
        }
    }


    private static final String victoryText = "Congratulations, you're a winner.";
    private static final String defeatText = "You lost, better luck next time.";
    private static final String dropText = "Your opponent has left the game.";

    private final JLabel resultLabel;
    private final JButton toLobbyButton;
}
