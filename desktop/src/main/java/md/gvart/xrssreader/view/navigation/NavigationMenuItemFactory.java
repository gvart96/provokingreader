package md.gvart.xrssreader.view.navigation;

import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.net.URL;

@RequiredArgsConstructor
public class NavigationMenuItemFactory {
    private final ToggleGroup toggleGroup;

    private static final double ICON_SIZE = 20;
    private static final double WRAPPER_SIZE = 24;

    public ToggleButton createWithIcon(String iconKey, String text, NavigationCallBack onSelect) {
        var icon = new MFXFontIcon(iconKey, ICON_SIZE);
        return createButtonInternally(icon, text, onSelect);
    }

    public ToggleButton createWithImage(String iconUrl, String text, NavigationCallBack onSelect) {
        ImageView icon = loadImage(iconUrl);
        return createButtonInternally(icon, text, onSelect);
    }


    private ToggleButton createButtonInternally(Node node, String text, NavigationCallBack onSelect) {
        var wrapper = new MFXIconWrapper(node, WRAPPER_SIZE);
        MFXRectangleToggleNode toggleNode = new MFXRectangleToggleNode(text, wrapper);
        toggleNode.setToggleGroup(toggleGroup);
        toggleNode.setOnMouseClicked(it -> onSelect.onClick());
        return toggleNode;
    }


    @SneakyThrows
    private static ImageView loadImage(String iconUrl) {
        ImageIO.setUseCache(true);
        var bufferedImage = ImageIO.read(new URL(iconUrl));
        var image = SwingFXUtils.toFXImage(bufferedImage, null);
        var view = new ImageView(image);
        view.setFitHeight(ICON_SIZE);
        view.setFitWidth(ICON_SIZE);
        return view;
    }

    public interface NavigationCallBack {
        void onClick();
    }
}
