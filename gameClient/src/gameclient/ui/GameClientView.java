package gameclient.ui;

import java.awt.Component;

public abstract class GameClientView<T extends Component> {
    public GameClientView(T component,
        GameClientController controller) {
        this.component = component;
        this.controller = controller;
    }

    public T getComponent() {
        return component;
    }

    public GameClientController getController() {
        return controller;
    }

    private final T component;
    private final GameClientController controller;
}
