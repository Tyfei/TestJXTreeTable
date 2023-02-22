package table;

import org.jdesktop.swingx.treetable.AbstractMutableTreeTableNode;


public class Node extends AbstractMutableTreeTableNode{

    public Node(Object[] data) {
        super(data);
    }

    @Override
    public Object getValueAt(int column) {
        return getData()[column];
    }

    @Override
    public int getColumnCount() {
        return getData().length;
    }

    public Object[] getData() {
        return (Object[]) getUserObject();
    }
}
