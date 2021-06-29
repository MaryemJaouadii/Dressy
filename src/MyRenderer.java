import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyRenderer extends DefaultTableCellRenderer {

public Component getTableCellRendererComponent(JTable table, ImageIcon icon) {

setIcon(icon);
return this;
}
}