package gui;

import table.TreeTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShowJXTreeTable {
    public static void main(String[] args) {
        JFrame testFrame = new JFrame();

        List<String[]> content = new ArrayList<>();
        content.add(new String[] {"Heading1"});
        content.add(new String[] {"Sub1", "Sub2", "Sub3"});
        content.add(new String[] {"Sub4", "Sub5", "Sub6"});
        content.add(new String[] {"Heading2"});
        content.add(new String[] {"Sub1", "Sub2", "Sub3"});
        content.add(new String[] {"Sub4", "Sub5", "Sub6"});
        content.add(new String[] {"Heading3"});
        content.add(new String[] {"Sub1", "Sub2", "Sub3"});
        content.add(new String[] {"Sub4", "Sub5", "Sub6"});

        TreeTable treeTable = new TreeTable(content);
        testFrame.setSize(500, 500);
        testFrame.setLayout(new BorderLayout());
        testFrame.add(new JScrollPane(treeTable.getTreeTable()), BorderLayout.CENTER);
        testFrame.setVisible(true);
    }

}
