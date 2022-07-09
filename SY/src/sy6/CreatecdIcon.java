package sy6;

import javax.swing.*;
import java.net.URL;

public class CreatecdIcon {
    public static ImageIcon add(String ImageName) {
        URL IconUrl = BookFrame.class.getResource("/" + ImageName);
        ImageIcon icon = new ImageIcon("book.jpg");
        return icon;
    }
}
