package org.hvdw.jexiftoolgui.renaming;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.hvdw.jexiftoolgui.MyConstants;
import org.hvdw.jexiftoolgui.ProgramTexts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RenamePhotos extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel RenamingGeneralText;
    private JButton renamingInfobutton;
    private JTextField RenamingSourceFoldertextField;
    private JButton RenamingSourceFolderbutton;
    private JPanel RenamingOptions;
    private JPanel RenamingsuffixPanel;
    private JPanel RenamingNumberingPanel;
    private JLabel RenamingDuplicateNames;
    private JPanel RenamingFileExtPanel;
    private JRadioButton prefixDate_timeradioButton;
    private JComboBox prefixDate_timecomboBox;
    private JRadioButton prefixDateradioButton;
    private JComboBox prefixDatecomboBox;
    private JRadioButton prefixStringradioButton;
    private JTextField prefixStringtextField;
    private JRadioButton suffixDonNotUseradioButton;
    private JRadioButton suffixStringradioButton;
    private JTextField suffixStringtextField;
    private JRadioButton suffixDatetimeradioButton;
    private JComboBox suffixDatetimecomboBox;
    private JRadioButton suffixDateradioButton;
    private JComboBox suffixDatecomboBox;
    private JRadioButton suffixCameramodelradioButton;
    private JRadioButton suffixOriginalFilenameradioButton;
    private JComboBox DigitscomboBox;
    private JComboBox StartOnImgcomboBox;
    private JRadioButton extLeaveradioButton;
    private JRadioButton makeLowerCaseRadioButton;
    private JRadioButton makeUpperCaseRadioButton;

    public RenamePhotos() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // button listeners
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        renamingInfobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        RenamingSourceFolderbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }

    // Start of the methods
    public void initDialog() {
        RenamingGeneralText.setText(String.format(ProgramTexts.HTML, 650, ProgramTexts.RenamingGeneralText));
        RenamingDuplicateNames.setText(String.format(ProgramTexts.HTML, 370, ProgramTexts.RenamingDuplicateNames));

        for (String item : MyConstants.DATES_TIMES_STRINGS) {
            prefixDate_timecomboBox.addItem(item);
            suffixDatetimecomboBox.addItem(item);
        }
        for (String item : MyConstants.DATES_STRINGS) {
            prefixDatecomboBox.addItem(item);
            suffixDatecomboBox.addItem(item);
        }
        for (int digit = 2; digit <= 6; digit++) {
            DigitscomboBox.addItem(digit);
        }
        StartOnImgcomboBox.addItem("start counting on 1st image");
        StartOnImgcomboBox.addItem("start counting on 2nd image");
    }


    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void showDialog() {
        pack();
        //setLocationRelativeTo(null);
        setLocationByPlatform(true);
        initDialog();
        setVisible(true);

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        contentPane.setPreferredSize(new Dimension(880, 650));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("OK");
        panel2.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        panel2.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, Font.BOLD | Font.ITALIC, -1, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Name: Prefix_Suffix.extension");
        panel3.add(label1);
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(3, 1, new Insets(10, 0, 10, 0), -1, -1));
        panel4.add(panel5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        RenamingGeneralText = new JLabel();
        RenamingGeneralText.setText("RenamingGeneralText");
        panel5.add(RenamingGeneralText, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, Font.BOLD, -1, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("Source folder:");
        panel5.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel5.add(panel6, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        renamingInfobutton = new JButton();
        renamingInfobutton.setText("Info");
        panel6.add(renamingInfobutton);
        RenamingSourceFoldertextField = new JTextField();
        RenamingSourceFoldertextField.setPreferredSize(new Dimension(600, 30));
        panel6.add(RenamingSourceFoldertextField);
        RenamingSourceFolderbutton = new JButton();
        RenamingSourceFolderbutton.setText("Browse");
        panel6.add(RenamingSourceFolderbutton);
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 2, new Insets(10, 0, 10, 0), -1, -1));
        panel4.add(panel7, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel7.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null));
        RenamingOptions = new JPanel();
        RenamingOptions.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel7.add(RenamingOptions, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$(null, Font.BOLD, -1, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setText("Select rename options:");
        RenamingOptions.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$(null, Font.BOLD, -1, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setText("Prefix:");
        RenamingOptions.add(label4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        RenamingOptions.add(panel8, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        prefixDate_timeradioButton = new JRadioButton();
        prefixDate_timeradioButton.setPreferredSize(new Dimension(140, 19));
        prefixDate_timeradioButton.setSelected(true);
        prefixDate_timeradioButton.setText("Date_time");
        panel8.add(prefixDate_timeradioButton);
        prefixDate_timecomboBox = new JComboBox();
        panel8.add(prefixDate_timecomboBox);
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        RenamingOptions.add(panel9, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        prefixDateradioButton = new JRadioButton();
        prefixDateradioButton.setPreferredSize(new Dimension(140, 19));
        prefixDateradioButton.setRolloverEnabled(false);
        prefixDateradioButton.setText("Date");
        panel9.add(prefixDateradioButton);
        prefixDatecomboBox = new JComboBox();
        panel9.add(prefixDatecomboBox);
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        RenamingOptions.add(panel10, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        prefixStringradioButton = new JRadioButton();
        prefixStringradioButton.setPreferredSize(new Dimension(140, 19));
        prefixStringradioButton.setText("String");
        panel10.add(prefixStringradioButton);
        prefixStringtextField = new JTextField();
        prefixStringtextField.setPreferredSize(new Dimension(200, 30));
        panel10.add(prefixStringtextField);
        RenamingsuffixPanel = new JPanel();
        RenamingsuffixPanel.setLayout(new GridLayoutManager(7, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel7.add(RenamingsuffixPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$(null, Font.BOLD, -1, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setText("Suffix:");
        RenamingsuffixPanel.add(label5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        RenamingsuffixPanel.add(panel11, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        suffixDonNotUseradioButton = new JRadioButton();
        suffixDonNotUseradioButton.setMinimumSize(new Dimension(175, 19));
        suffixDonNotUseradioButton.setSelected(true);
        suffixDonNotUseradioButton.setText("Do not use");
        panel11.add(suffixDonNotUseradioButton);
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        RenamingsuffixPanel.add(panel12, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        suffixStringradioButton = new JRadioButton();
        suffixStringradioButton.setPreferredSize(new Dimension(175, 19));
        suffixStringradioButton.setText("String");
        panel12.add(suffixStringradioButton);
        suffixStringtextField = new JTextField();
        suffixStringtextField.setPreferredSize(new Dimension(200, 30));
        panel12.add(suffixStringtextField);
        final JPanel panel13 = new JPanel();
        panel13.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        RenamingsuffixPanel.add(panel13, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        suffixDatetimeradioButton = new JRadioButton();
        suffixDatetimeradioButton.setPreferredSize(new Dimension(175, 19));
        suffixDatetimeradioButton.setText("Date_time");
        panel13.add(suffixDatetimeradioButton);
        suffixDatetimecomboBox = new JComboBox();
        panel13.add(suffixDatetimecomboBox);
        final JPanel panel14 = new JPanel();
        panel14.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        RenamingsuffixPanel.add(panel14, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        suffixDateradioButton = new JRadioButton();
        suffixDateradioButton.setPreferredSize(new Dimension(175, 19));
        suffixDateradioButton.setText("Date");
        panel14.add(suffixDateradioButton);
        suffixDatecomboBox = new JComboBox();
        panel14.add(suffixDatecomboBox);
        final JPanel panel15 = new JPanel();
        panel15.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        RenamingsuffixPanel.add(panel15, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        suffixCameramodelradioButton = new JRadioButton();
        suffixCameramodelradioButton.setPreferredSize(new Dimension(175, 19));
        suffixCameramodelradioButton.setText("Camera model");
        panel15.add(suffixCameramodelradioButton);
        final JLabel label6 = new JLabel();
        label6.setText("${Exif:Model}");
        panel15.add(label6);
        final JPanel panel16 = new JPanel();
        panel16.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        RenamingsuffixPanel.add(panel16, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        suffixOriginalFilenameradioButton = new JRadioButton();
        suffixOriginalFilenameradioButton.setPreferredSize(new Dimension(175, 19));
        suffixOriginalFilenameradioButton.setText("Original filename");
        panel16.add(suffixOriginalFilenameradioButton);
        final JLabel label7 = new JLabel();
        label7.setText("${filename}");
        panel16.add(label7);
        final JPanel panel17 = new JPanel();
        panel17.setLayout(new GridLayoutManager(1, 2, new Insets(10, 0, 10, 0), -1, -1));
        panel4.add(panel17, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel17.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null));
        RenamingNumberingPanel = new JPanel();
        RenamingNumberingPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel17.add(RenamingNumberingPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        RenamingDuplicateNames = new JLabel();
        Font RenamingDuplicateNamesFont = this.$$$getFont$$$(null, -1, -1, RenamingDuplicateNames.getFont());
        if (RenamingDuplicateNamesFont != null) RenamingDuplicateNames.setFont(RenamingDuplicateNamesFont);
        RenamingDuplicateNames.setText("RenamingDuplicateNames");
        RenamingNumberingPanel.add(RenamingDuplicateNames, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel18 = new JPanel();
        panel18.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        RenamingNumberingPanel.add(panel18, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setPreferredSize(new Dimension(140, 18));
        label8.setText("No. of digits");
        panel18.add(label8);
        DigitscomboBox = new JComboBox();
        panel18.add(DigitscomboBox);
        StartOnImgcomboBox = new JComboBox();
        panel18.add(StartOnImgcomboBox);
        RenamingFileExtPanel = new JPanel();
        RenamingFileExtPanel.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel17.add(RenamingFileExtPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        Font label9Font = this.$$$getFont$$$(null, Font.BOLD, -1, label9.getFont());
        if (label9Font != null) label9.setFont(label9Font);
        label9.setText("File extension:");
        RenamingFileExtPanel.add(label9, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        extLeaveradioButton = new JRadioButton();
        extLeaveradioButton.setSelected(true);
        extLeaveradioButton.setText("Leave as is");
        RenamingFileExtPanel.add(extLeaveradioButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        makeLowerCaseRadioButton = new JRadioButton();
        makeLowerCaseRadioButton.setText("Make lower case");
        RenamingFileExtPanel.add(makeLowerCaseRadioButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        makeUpperCaseRadioButton = new JRadioButton();
        makeUpperCaseRadioButton.setText("Make upper case");
        RenamingFileExtPanel.add(makeUpperCaseRadioButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(suffixDonNotUseradioButton);
        buttonGroup.add(suffixStringradioButton);
        buttonGroup.add(suffixDatetimeradioButton);
        buttonGroup.add(suffixDateradioButton);
        buttonGroup.add(suffixCameramodelradioButton);
        buttonGroup.add(suffixOriginalFilenameradioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(prefixDate_timeradioButton);
        buttonGroup.add(prefixDateradioButton);
        buttonGroup.add(prefixStringradioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(extLeaveradioButton);
        buttonGroup.add(makeLowerCaseRadioButton);
        buttonGroup.add(makeUpperCaseRadioButton);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
