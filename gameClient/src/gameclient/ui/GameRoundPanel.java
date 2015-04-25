package gameclient.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameRoundPanel extends GameClientView<JPanel> implements Observer {

    public GameRoundPanel(GameClientController controller) {
        super(new JPanel(), controller);
        controller.getModel().addObserver(this);

        GroupLayout layout = new GroupLayout(getComponent());
        getComponent().setLayout(layout);

        turnLabel = new JLabel(opponentsTurnMessage);
        playerNameLabel = new JLabel();
        playerColorLabel = new JLabel("Your color:");
        playerColorMarker = new ColorMarker();
        playerColorMarker.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        makeAMoveButton = new JButton("Make a move");
        makeAMoveButton.addActionListener(controller.new MakeAMoveListener());
        gameField = new GameFieldPanel(controller);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(
                    GroupLayout.Alignment.LEADING)
                .addComponent(turnLabel)
                .addComponent(makeAMoveButton)
                )
                .addGroup(layout.createParallelGroup(
                    GroupLayout.Alignment.TRAILING)
                .addComponent(playerNameLabel)
                .addComponent(playerColorMarker, GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE, 15)
                )
            )
            .addComponent(gameField)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
                .addComponent(turnLabel)
                .addComponent(playerNameLabel)
            )
            .addGroup(layout.createParallelGroup()
                .addComponent(makeAMoveButton)
                .addComponent(playerColorMarker, GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE, 8)
            )
            .addComponent(gameField)
        );
    }

    @Override
    public void update(Observable o, Object o1) {
        GameClientModel model = (GameClientModel) o;
        playerNameLabel.setText("You are: " + model.getPlayerName());
        playerColorMarker.setColor(model.getPlayerColor());
        playerColorMarker.repaint();

        if (model.isMyTurn()) {
            turnLabel.setText(myTurnMessage);
            makeAMoveButton.setEnabled(true);
        } else {
            turnLabel.setText(opponentsTurnMessage);
            makeAMoveButton.setEnabled(false);
        }

        gameField.repaint();

    }

    private class ColorMarker extends JPanel {

            public void setColor(PieceColor color) {
                if (color == PieceColor.BLACK) {
                    markerColor = Color.BLACK;
                } else {
                    markerColor = Color.WHITE;
                }
            }

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                Rectangle bounds = g.getClipBounds();
                g.setColor(markerColor);
                g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            }

            private Color markerColor;
        };

    private static final String myTurnMessage = "Make a move now";
    private static final String opponentsTurnMessage =
        "Waiting for opponent's move...";

    private final JLabel turnLabel;
    private final JLabel playerNameLabel;
    private final JLabel playerColorLabel;
    private final ColorMarker playerColorMarker;
    private final JButton makeAMoveButton;
    private final GameFieldPanel gameField;

}
