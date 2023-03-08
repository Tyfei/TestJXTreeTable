package gui;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import javax.swing.*;

public class DragDropJList extends JList<String> implements DragGestureListener, DropTargetListener {
    private int draggedIndex;
    private final DropTarget dropTarget;

    public DragDropJList() {
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setDragEnabled(true);
        setDropMode(DropMode.INSERT);
        setTransferHandler(new ListTransferHandler());
        new DragSource().createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, this);
        dropTarget = new DropTarget(this, this);
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        draggedIndex = getSelectedIndex();
        Transferable transferable = new StringSelection(getSelectedValue());
        dge.startDrag(DragSource.DefaultMoveDrop, transferable, null);
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        try {
            Transferable transferable = dtde.getTransferable();
            if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                int targetIndex = locationToIndex(dtde.getLocation());
                String targetValue = (String) getModel().getElementAt(targetIndex);
                String draggedValue = (String) transferable.getTransferData(DataFlavor.stringFlavor);

                if (draggedIndex != targetIndex) {
                    if (draggedIndex < targetIndex) {
                        ((DefaultListModel<String>) getModel()).add(targetIndex + 1, draggedValue);
                        ((DefaultListModel<String>) getModel()).remove(draggedIndex);
                    } else {
                        ((DefaultListModel<String>) getModel()).remove(draggedIndex);
                        ((DefaultListModel<String>) getModel()).add(targetIndex, draggedValue);
                    }
                }
                dtde.dropComplete(true);
            } else {
                dtde.rejectDrop();
            }
        } catch (Exception e) {
            e.printStackTrace();
            dtde.rejectDrop();
        }
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {}

    @Override
    public void dragExit(DropTargetEvent dte) {}

    @Override
    public void dragOver(DropTargetDragEvent dtde) {}

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {}

    private static class ListTransferHandler extends TransferHandler {
        public boolean canImport(TransferHandler.TransferSupport support) {
            if (!support.isDrop()) {
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DragDropJList Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DragDropJList list = new DragDropJList();
        DefaultListModel<String> model = new DefaultListModel<String>();
        model.addElement("Item 1");
        model.addElement("Item 2");
        model.addElement("Item 3");
        list.setModel(model);
        JScrollPane scrollPane = new JScrollPane(list);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(200, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

