package swingui_06;

import javax.swing.SwingUtilities;

import swingui_06.decoration.DynamicTextDecoration;
import swingui_06.decoration.StaticTextDecoration;

public class Startup
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new StaticTextDecoration().test());
        SwingUtilities.invokeLater(() -> new DynamicTextDecoration().test());
    }
}
