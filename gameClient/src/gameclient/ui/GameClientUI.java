package gameclient.ui;

public class GameClientUI {

    public GameClientUI(GameClientController controller) {
        this.controller = controller;
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
                GameClientModel model = new GameClientModel();
                GameClientController controller =
                    new GameClientController(model);
                GameClientUI ui = new GameClientUI(controller);
                ui.showConnectionFrame();
            }
        });
    }

    private final GameClientController controller;
    private final ConnectionFrame connectionFrame;
}
