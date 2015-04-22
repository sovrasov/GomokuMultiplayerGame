package gameclient.ui;

public class GameClientUI {

    public GameClientUI() {
        controller = new GameClientController();
        connectionFrame = new ConnectionFrame(controller);
        controller.setAssociatedView(connectionFrame);
    }

    public void showConnectionFrame() {
        connectionFrame.show();
    }

    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameClientUI ui = new GameClientUI();
                ui.showConnectionFrame();
            }
        });
    }

    private final GameClientController controller;
    private final ConnectionFrame connectionFrame;
}
