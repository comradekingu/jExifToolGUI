package org.hvdw.jexiftoolgui;

import ch.qos.logback.classic.Logger;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import org.hvdw.jexiftoolgui.controllers.*;
import org.hvdw.jexiftoolgui.datetime.DateTime;
import org.hvdw.jexiftoolgui.editpane.*;
import org.hvdw.jexiftoolgui.facades.IPreferencesFacade;
import org.hvdw.jexiftoolgui.metadata.MetaData;
import org.hvdw.jexiftoolgui.model.FileTreeModel;
import org.hvdw.jexiftoolgui.view.*;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.hvdw.jexiftoolgui.Utils.isOsFromMicrosoft;
import static org.hvdw.jexiftoolgui.controllers.StandardFileIO.checkforjexiftoolguiFolder;
import static org.hvdw.jexiftoolgui.facades.IPreferencesFacade.PreferenceKey.DEFAULT_START_FOLDER;
import static org.hvdw.jexiftoolgui.facades.IPreferencesFacade.PreferenceKey.PREFERRED_FILEDIALOG;


public class mainScreen {
    //private static final ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(mainScreen.class);
    private final static Logger logger = (Logger) LoggerFactory.getLogger(mainScreen.class);

    private IPreferencesFacade prefs = IPreferencesFacade.defaultInstance;
    //private JFrame rootFrame;
    private JMenuBar menuBar;
    private JMenu myMenu;
    private JMenuItem menuItem;
    private JPanel rootPanel;
    private JTabbedPane tabbedPaneRight;
    private JButton buttonLoadImages;
    private JButton buttonShowImage;
    private JRadioButton radioButtonViewAll;
    private JPanel ViewRadiobuttonpanel;
    private JPanel ViewDatapanel;
    private JScrollPane ViewDatascrollpanel;
    private JTree FileTree;
    private JTable tableListfiles;
    private JTable ListexiftoolInfotable;
    private JLabel iconLabel;
    private JLabel MyCommandsText;
    private JTextField CommandsParameterstextField;
    private JButton CommandsclearParameterSFieldButton;
    private JButton CommandsclearOutputFieldButton;
    private JButton CommandsgoButton;
    private JButton CommandshelpButton;
    private JEditorPane YourCommandsOutputText;
    private JTabbedPane tabbedPaneEditfunctions;
    private JTextField ExifMaketextField;
    private JCheckBox ExifMakecheckBox;
    private JTextField ExifModeltextField;
    private JCheckBox ExifModelcheckBox;
    private JTextField ExifModifyDatetextField;
    private JCheckBox ExifModifyDatecheckBox;
    private JTextField ExifDateTimeOriginaltextField;
    private JCheckBox ExifDateTimeOriginalcheckBox;
    private JTextField ExifCreateDatetextField;
    private JCheckBox ExifCreateDatecheckBox;
    private JTextField ExifArtistCreatortextField;
    private JCheckBox ExifArtistCreatorcheckBox;
    private JTextField ExifCopyrighttextField;
    private JCheckBox ExifCopyrightcheckBox;
    private JTextField ExifUsercommenttextField;
    private JCheckBox ExifUsercommentcheckBox;
    private JTextArea ExifDescriptiontextArea;
    private JCheckBox ExifDescriptioncheckBox;
    private JPanel Camera_Equipment;
    private JPanel DateTime;
    private JPanel CreativeTags;
    private JButton ExifcopyFromButton;
    private JButton ExifsaveToButton;
    private JCheckBox ExifBackupOriginalscheckBox;
    private JButton ExifcopyDefaultsButton;
    private JButton resetFieldsButton;
    private JButton ExifhelpButton;
    private JTextField xmpCreatortextField;
    private JCheckBox xmpCreatorcheckBox;
    private JTextField xmpRightstextField;
    private JCheckBox xmpRightscheckBox;
    private JTextArea xmpDescriptiontextArea;
    private JCheckBox xmpDescriptioncheckBox;
    private JTextField xmpLabeltextField;
    private JCheckBox xmpLabelcheckBox;
    private JTextField xmpSubjecttextField;
    private JCheckBox xmpSubjectcheckBox;
    private JTextField xmpTitletextField;
    private JCheckBox xmpTitlecheckBox;
    private JTextField xmpPersontextField;
    private JCheckBox xmpPersoncheckBox;
    private JButton xmpCopyFrombutton;
    private JButton xmpSaveTobutton;
    private JCheckBox xmpBackupOriginalscheckBox;
    private JButton xmpCopyDefaultsbutton;
    private JButton xmpResetFieldsbutton;
    private JButton xmpHelpbutton;
    private JTextField geotaggingImgFoldertextField;
    private JButton geotaggingImgFolderbutton;
    private JTextField geotaggingGPSLogtextField;
    private JButton geotaggingGPSLogbutton;
    private JTextField geotaggingGeosynctextField;
    private JButton geotaggingWriteInfobutton;
    private JButton geotaggingHelpbutton;
    private JLabel GeotaggingLeaveFolderEmptyLabel;
    private JCheckBox geotaggingOverwriteOriginalscheckBox;
    private JPanel ExifEditpanel;
    private JPanel GeotaggingEditpanel;
    private JTextField xmpRegionNametextField;
    private JCheckBox xmpRegionNamecheckBox;
    private JTextField xmpRegionTypetextField;
    private JCheckBox xmpRegionTypecheckBox;
    private JProgressBar progressBar;
    private JLabel OutputLabel;
    private JPanel gpsButtonPanel;
    private JPanel getGpsButtonPanel2;
    private JPanel gpsLocationPanel;
    private JPanel gpsCalculationPanel;
    private JButton gpsCopyFrombutton;
    private JButton gpsSaveTobutton;
    private JCheckBox gpsBackupOriginalscheckBox;
    private JButton gpsResetFieldsbutton;
    private JButton gpsMapcoordinatesbutton;
    private JButton gpsHelpbutton;
    private JLabel gpsCalculatorLabelText;
    private JTextField gpsLocationtextField;
    private JCheckBox gpsLocationcheckBox;
    private JCheckBox SaveLatLonAltcheckBox;
    private JFormattedTextField gpsLatDecimaltextField;
    private JFormattedTextField gpsLonDecimaltextField;
    private JCheckBox gpsAboveSealevelcheckBox;
    private JPanel gpsLatLonAltPanel;
    private JTextField gpsCountrytextField;
    private JCheckBox gpsCountrycheckBox;
    private JTextField gpsStateProvincetextField;
    private JCheckBox gpsStateProvincecheckBox;
    private JTextField gpsCitytextField;
    private JCheckBox gpsCitycheckBox;
    private JButton copyToInputFieldsButton;
    private JLabel CalcLatDecimaltextLabel;
    private JFormattedTextField CalcLatDegtextField;
    private JFormattedTextField CalcLatMintextField;
    private JFormattedTextField CalcLatSectextField;
    private JLabel CalcLonDecimaltextLabel;
    private JFormattedTextField CalcLonDegtextField;
    private JFormattedTextField CalcLonMintextField;
    private JFormattedTextField CalcLonSectextField;
    private JRadioButton CalcNorthRadioButton;
    private JRadioButton CalcSouthRadioButton;
    private JRadioButton CalcEastradioButton;
    private JRadioButton CalcWestRadioButton;
    private JButton decimalToMinutesSecondsButton;
    private JButton minutesSecondsToDecimalButton;
    private JLabel CopyMetaDataUiText;
    private JRadioButton copyAllMetadataRadiobutton;
    private JRadioButton copyAllMetadataSameGroupsRadiobutton;
    private JRadioButton copySelectiveMetadataradioButton;
    private JCheckBox CopyExifcheckBox;
    private JCheckBox CopyXmpcheckBox;
    private JCheckBox CopyIptccheckBox;
    private JCheckBox CopyIcc_profileDataCheckBox;
    private JCheckBox BackupOriginalscheckBox;
    private JCheckBox CopyGpsCheckBox;
    private JCheckBox CopyJfifcheckBox;
    private JButton UseDataFrombutton;
    private JButton CopyDataCopyTobutton;
    private JButton CopyHelpbutton;
    private JRadioButton UseNonPropFontradioButton;
    private JRadioButton UsePropFontradioButton;
    private JRadioButton radioButtonByTagName;
    private JComboBox comboBoxViewByTagName;
    private JRadioButton radioButtoncommonTags;
    private JComboBox comboBoxViewCommonTags;
    private JRadioButton radioButtonCameraMakes;
    private JComboBox comboBoxViewCameraMake;
    private JTextField geotaggingLocationtextfield;
    private JTextField geotaggingCountrytextfield;
    private JCheckBox geotaggingLocationcheckbox;
    private JCheckBox geotaggingCountrycheckbox;
    private JTextField geotaggingStatetextfield;
    private JCheckBox geotaggingStatecheckbox;
    private JTextField geotaggingCitytextfield;
    private JCheckBox geotaggingCitycheckbox;
    private JLabel GeotaggingLocationLabel;
    private JButton resetGeotaggingbutton;
    private JLabel GeotaggingGeosyncExplainLabel;
    private JFormattedTextField gpsAltDecimaltextField;
    private JLabel gPanoTopText;
    private JFormattedTextField gpanoCAIHPtextField;
    private JFormattedTextField gpanoCAIWPtextField;
    private JFormattedTextField gpanoCALPtextField;
    private JFormattedTextField gpanoCATPtextField;
    private JTextField gpanoStitchingSoftwaretextField;
    private JFormattedTextField gpanoFPHPtextField;
    private JFormattedTextField gpanoFPWPtextField;
    private JComboBox gpanoPTcomboBox;
    private JCheckBox checkBox1;
    private JFormattedTextField gpanoPHDtextField;
    private JFormattedTextField gpanoIVHDtextField;
    private JCheckBox gpanoIVHDCheckBox;
    private JFormattedTextField gpanoIVPDtextField;
    private JCheckBox gpanoIVPDCheckBox;
    private JFormattedTextField gpanoIVRDtextField;
    private JCheckBox gpanoIVRDCheckBox;
    private JFormattedTextField gpanoIHFOVDtextField;
    private JCheckBox gpanoIHFOVDtextFieldCheckBox;
    private JButton gpanoResetFieldsbutton;
    private JButton gpanoHelpbutton;
    private JCheckBox gpanoOverwriteOriginalscheckBox;
    private JButton gpanoCopyFrombutton;
    private JButton gpanoCopyTobutton;
    private JLabel gpanoMinVersionText;
    private JCheckBox gpanoStitchingSoftwarecheckBox;
    private JCheckBox gpanoPHDcheckBox;
    private JTextField xmpCredittextField;
    private JCheckBox xmpCreditcheckBox;
    private JLabel xmpTopText;
    private JCheckBox CopyMakernotescheckBox;
    private JTextField lensmaketextField;
    private JCheckBox lensmakecheckBox;
    private JTextField lensmodeltextField;
    private JCheckBox lensmodelcheckBox;
    private JTextField lensserialnumbertextField;
    private JTextField focallengthtextField;
    private JCheckBox lensserialnumbercheckBox;
    private JCheckBox focallengthcheckBox;
    private JTextField focallengthIn35mmformattextField;
    private JCheckBox focallengthIn35mmformatcheckBox;
    private JTextField fnumbertextField;
    private JCheckBox fnumbercheckBox;
    private JTextField maxaperturevaluetextField;
    private JCheckBox maxaperturevaluecheckBox;
    private JComboBox meteringmodecomboBox;
    private JCheckBox meteringmodecheckBox;
    private JTextField focusdistancetextField;
    private JCheckBox focusdistancecheckBox;
    private JTextField lensidtextField;
    private JCheckBox lensidcheckBox;
    private JTextField conversionlenstextField;
    private JCheckBox conversionlenscheckBox;
    private JTextField lenstypetextField;
    private JCheckBox lenstypecheckBox;
    private JTextField lensfirmwareversiontextField;
    private JCheckBox lensfirmwareversioncheckBox;
    private JCheckBox lensOverwriteOriginalscheckBox;
    private JButton lensCopyFrombutton;
    private JButton lensSaveTobutton;
    private JButton lensResetFieldsbutton;
    private JButton lensHelpbutton;
    private JPanel saveloadlensconfigpanel;
    private JButton saveLensConfigurationbutton;
    private JButton loadLensConfigurationButton;
    private JLabel lensSaveLoadConfigLabel;
    private JPanel gpsButtonPanel2;
    private JButton buttonLoadDirectory;
    private JLabel StringsTopText;
    private JTextField StringsKeywordstextField;
    private JCheckBox StringsKeywordsXmpcheckBox;
    private JCheckBox StringsKeywordsIPTCcheckBox;
    private JRadioButton StringsKeywOverwriteradioButton;
    private JRadioButton StringsKeywAppendradioButton;
    private JRadioButton StringsKeywRemoveradioButton;
    private JRadioButton StringsKeywDontSaveradioButton;
    private JTextField StringsSubjecttextField;
    private JRadioButton StringsSubjectOverwriteradioButton;
    private JRadioButton StringsSubjectAppendradioButton;
    private JRadioButton StringsSubjectRemoveradioButton;
    private JRadioButton StringsSubjectDontSaveradioButton;
    private JTextField StringsPIItextField;
    private JLabel StringsIIPXmpcheckBox;
    private JCheckBox StringsIIPIPTCcheckBox;
    private JRadioButton StringsIIPOverwriteradioButton;
    private JRadioButton StringsIIPAppendradioButton;
    private JRadioButton StringsIIPRemoveradioButton;
    private JRadioButton StringsIIPDontSaveradioButton;
    private JCheckBox stringPlusOverwriteOriginalscheckBox;
    private JButton stringPlusCopyFrombutton;
    private JButton stringPlusSaveTobutton;
    private JButton stringPlusResetFieldsbutton;
    private JButton stringPlusHelpbutton;
    private JTextField xmpKeywordstextField;
    private JCheckBox xmpKeywordscheckBox;
    private JButton AddCommandFavoritebutton;
    private JButton LoadCommandFavoritebutton;
    private JButton SaveQuerybutton;
    private JButton loadQuerybutton;
    private JLabel JLabelDropReady;
    private JTable UserCombiTable;
    private JComboBox UserCombiscomboBox;
    private JButton udcCreateNewButton;
    private JLabel UserCombiTopText;
    private JCheckBox udcOverwriteOriginalscheckBox;
    private JButton udcCopyFrombutton;
    private JButton udcSaveTobutton;
    private JButton udcResetFieldsbutton;
    private JButton udcHelpbutton;
    private JScrollPane UserCombiScrollPane;
    private JLabel CustomConfiglabel;
    private JLabel lblLoadedFiles;
    private JRadioButton copyAllMetdataToXMPRadioButton;
    private JTabbedPane CopyDatatabbedPane;
    private JRadioButton CopyArgsRadioButton;
    private JRadioButton tagsToExifradioButton;
    private JRadioButton tagsToXmpradioButton;
    private JRadioButton tagsToIptcradioButton;
    private JRadioButton tagsToGpsradioButton;
    private JRadioButton tagsToPdfradioButton;
    private JCheckBox exif2xmpCheckBox;
    private JCheckBox gps2xmpCheckBox;
    private JCheckBox iptc2xmpCheckBox;
    private JCheckBox pdf2xmpCheckBox;
    private JCheckBox iptc2exifCheckBox;
    private JCheckBox xmp2exifCheckBox;
    private JCheckBox exif2iptcCheckBox;
    private JCheckBox xmp2iptcCheckBox;
    private JCheckBox xmp2gpsCheckBox;
    private JCheckBox xmp2pdfCheckBox;
    private JCheckBox CopyInsideImageMakeCopyOfOriginalscheckBox;
    private JButton copyInsideSaveDataTo;
    private JTabbedPane lefttabbedPane;
    private JButton buttonCompare;
    private JButton buttonSlideShow;
    private JPanel LeftPanel;
    private JPanel LeftbuttonBar;
    private JScrollPane Leftscrollpane;
    private JTree fileTree;
    private ImageIcon icon;


    public File[] files;
    public int[] selectedIndices;
    public List<Integer> selectedIndicesList = new ArrayList<Integer>();
    private int SelectedRow;
    public int SelectedCell;
    private int SelectedCopyFromImageIndex;  // Used for the copy metadata from ..

    public String exiftool_path = "";
    private ListSelectionModel listSelectionModel;

    // Initialize all the helper classes
    PreferencesDialog prefsDialog = new PreferencesDialog();
    private MetaData metaData = new MetaData();
    private DateTime dateTime = new DateTime();
    private EditExifdata EEd = new EditExifdata();
    private EditXmpdata EXd = new EditXmpdata();
    private EditGeotaggingdata EGd = new EditGeotaggingdata();
    private EditGPSdata EGPSd = new EditGPSdata();
    private YourCommands YourCmnds = new YourCommands();
    private EditGpanodata EGpanod = new EditGpanodata();
    private EditLensdata ELd = new EditLensdata();
    //private DatabasePanel DBP = new DatabasePanel();
    private CreateUpdatemyLens CUL = new CreateUpdatemyLens();
    private EditStringdata ESd = new EditStringdata();
    private AddFavorite AddFav = new AddFavorite();
    private MetadataUserCombinations MD = new MetadataUserCombinations();
    private SimpleWebView WV = new SimpleWebView();
    private EditUserDefinedCombis EUDC = new EditUserDefinedCombis();


//////////////////////////////////////////////////////////////////////////////////
    // Define the several arrays for the several Edit panes on the right side. An interface or getter/setter methods would be more "correct java", but also
    // creates way more code which doesn't make it clearer either.

    private JTextField[] getExifFields() {
        return new JTextField[]{ExifMaketextField, ExifModeltextField, ExifModifyDatetextField, ExifDateTimeOriginaltextField, ExifCreateDatetextField,
                ExifArtistCreatortextField, ExifCopyrighttextField, ExifUsercommenttextField};
    }

    private JTextArea[] getExifAreas() {
        return new JTextArea[]{ExifDescriptiontextArea};
    }

    private JCheckBox[] getExifBoxes() {
        return new JCheckBox[]{ExifMakecheckBox, ExifModelcheckBox, ExifModifyDatecheckBox, ExifDateTimeOriginalcheckBox, ExifCreateDatecheckBox,
                ExifArtistCreatorcheckBox, ExifCopyrightcheckBox, ExifUsercommentcheckBox, ExifDescriptioncheckBox, ExifBackupOriginalscheckBox};
    }
    private JTextField[] getXmpFields() {
    return new JTextField[] {xmpCreatortextField, xmpCredittextField, xmpRightstextField, xmpLabeltextField, xmpTitletextField, xmpKeywordstextField, xmpSubjecttextField, xmpPersontextField};
    }
    private JTextArea[] getXmpAreas() {
        return new JTextArea[] {xmpDescriptiontextArea};
    }

    private JCheckBox[] getXmpBoxes() { return new JCheckBox[]{xmpCreatorcheckBox, xmpCreditcheckBox, xmpRightscheckBox, xmpLabelcheckBox, xmpTitlecheckBox , xmpKeywordscheckBox,xmpSubjectcheckBox, xmpPersoncheckBox, xmpDescriptioncheckBox, xmpBackupOriginalscheckBox}; }

    private JTextField[] getGeotaggingFields() { return new JTextField[] {geotaggingImgFoldertextField, geotaggingGPSLogtextField, geotaggingGeosynctextField, geotaggingLocationtextfield, geotaggingCountrytextfield, geotaggingStatetextfield, geotaggingCitytextfield}; }

    private JCheckBox[] getGeotaggingBoxes() {return new JCheckBox[] {geotaggingLocationcheckbox, geotaggingCountrycheckbox, geotaggingStatecheckbox, geotaggingCitycheckbox};}

    private JRadioButton[] getCopyMetaDataRadiobuttons() {return new JRadioButton[] {copyAllMetadataRadiobutton, copyAllMetadataSameGroupsRadiobutton, copySelectiveMetadataradioButton}; }
    private JCheckBox[] getCopyMetaDataCheckBoxes() {return new JCheckBox[] {CopyExifcheckBox, CopyXmpcheckBox, CopyIptccheckBox, CopyIcc_profileDataCheckBox, CopyGpsCheckBox, CopyJfifcheckBox, CopyMakernotescheckBox, BackupOriginalscheckBox}; }
    private JRadioButton[] getInsideImageCopyRadiobuttons() {return new JRadioButton[] {copyAllMetdataToXMPRadioButton, CopyArgsRadioButton}; }
    private JRadioButton[] getInsideImageSubCopyRadiobuttons() {return new JRadioButton[] {tagsToXmpradioButton, tagsToExifradioButton, tagsToIptcradioButton, tagsToGpsradioButton, tagsToPdfradioButton}; }
    private JCheckBox[] getInsideImageCopyCheckboxes() {return new JCheckBox[] {exif2xmpCheckBox, gps2xmpCheckBox, iptc2xmpCheckBox, pdf2xmpCheckBox, iptc2exifCheckBox, xmp2exifCheckBox, exif2iptcCheckBox, xmp2iptcCheckBox, xmp2gpsCheckBox, xmp2pdfCheckBox, CopyInsideImageMakeCopyOfOriginalscheckBox}; }

    private JFormattedTextField[] getNumGPSFields() {
        return new JFormattedTextField[] {gpsLatDecimaltextField, gpsLonDecimaltextField, gpsAltDecimaltextField};
    }
    private JTextField[] getGPSLocationFields() {
        return new JTextField[] {gpsLocationtextField, gpsCountrytextField, gpsStateProvincetextField, gpsCitytextField};
    }
    private JCheckBox[] getGpsBoxes() {
        return new JCheckBox[] {SaveLatLonAltcheckBox, gpsAboveSealevelcheckBox, gpsLocationcheckBox, gpsCountrycheckBox, gpsStateProvincecheckBox, gpsCitycheckBox, gpsBackupOriginalscheckBox};
    }

    private JFormattedTextField[] getGPScalcFields() {
        return new JFormattedTextField[] {CalcLatDegtextField, CalcLatMintextField, CalcLatSectextField, CalcLonDegtextField, CalcLonMintextField, CalcLonSectextField};
    }

    private JFormattedTextField[] getGpanoFields() {
        return new JFormattedTextField[] {gpanoCAIHPtextField, gpanoCAIWPtextField, gpanoCALPtextField, gpanoCATPtextField, gpanoFPHPtextField, gpanoFPWPtextField, gpanoPHDtextField, gpanoIVHDtextField, gpanoIVPDtextField, gpanoIVRDtextField, gpanoIHFOVDtextField};
    }
    private JCheckBox[] getGpanoCheckBoxes() {
        return new JCheckBox[] {gpanoPHDcheckBox, gpanoStitchingSoftwarecheckBox, gpanoIVHDCheckBox, gpanoIVPDCheckBox, gpanoIVRDCheckBox, gpanoIHFOVDtextFieldCheckBox, gpanoOverwriteOriginalscheckBox};
    }

    private JTextField[] getLensFields() {
        return new JTextField[] {lensmaketextField, lensmodeltextField, lensserialnumbertextField, focallengthtextField, focallengthIn35mmformattextField, fnumbertextField, maxaperturevaluetextField, focusdistancetextField, lensidtextField, conversionlenstextField, lenstypetextField, lensfirmwareversiontextField};
    }

    private JCheckBox[] getLensCheckBoxes() {
        return new JCheckBox[] {lensmakecheckBox, lensmodelcheckBox, lensserialnumbercheckBox, focallengthcheckBox, focallengthIn35mmformatcheckBox, fnumbercheckBox, maxaperturevaluecheckBox, focusdistancecheckBox, lensidcheckBox, conversionlenscheckBox, lenstypecheckBox, lensfirmwareversioncheckBox, meteringmodecheckBox, lensOverwriteOriginalscheckBox};
    }

    private JTextField[] getstringPlusFields() {
        return new JTextField[] {StringsKeywordstextField, StringsSubjecttextField, StringsPIItextField};
    }

    private JCheckBox[] getstringPlusBoxes() {
        return new JCheckBox[] {StringsKeywordsXmpcheckBox, StringsKeywordsIPTCcheckBox, stringPlusOverwriteOriginalscheckBox};
    }

    private String getStringPlusRadioButtons(JRadioButton Overwrite, JRadioButton Append, JRadioButton Remove, JRadioButton DontSave) {
        if (Overwrite.isSelected()) {
            return "=";
        }
        if (Append.isSelected()) {
            return "+=";
        }
        if (Remove.isSelected()) {
            return "-=";
        }
        if (DontSave.isSelected()) {
            return "";
//        } else { // Just to finish this method correctly
//            return "";
        }
        // Will not be reached, but we need a correct method
        return "";
    }
    private String[] getSelectedRadioButtons() {
        return new String[]{
                getStringPlusRadioButtons(StringsKeywOverwriteradioButton, StringsKeywAppendradioButton, StringsKeywRemoveradioButton, StringsKeywDontSaveradioButton),
                getStringPlusRadioButtons(StringsSubjectOverwriteradioButton, StringsSubjectAppendradioButton, StringsSubjectRemoveradioButton, StringsSubjectDontSaveradioButton),
                getStringPlusRadioButtons(StringsIIPOverwriteradioButton, StringsIIPAppendradioButton, StringsIIPRemoveradioButton, StringsIIPDontSaveradioButton)
        };
    }

    private JCheckBox[] getCopyMetaDatacheckboxes() {
        return new JCheckBox[] {CopyExifcheckBox, CopyXmpcheckBox, CopyIptccheckBox, CopyIcc_profileDataCheckBox, CopyGpsCheckBox, CopyJfifcheckBox, CopyMakernotescheckBox};
    }

    private JPanel getRootPanel() {
        return rootPanel;
    }

    public void loadImages(String loadingType) {
        String prefFileDialog = prefs.getByKey(PREFERRED_FILEDIALOG, "jfilechooser");
        if ("images".equals(loadingType)) {
            logger.debug("load images pushed or menu load images");
            OutputLabel.setText(ResourceBundle.getBundle("translations/program_strings").getString("pt.loadingimages"));
            if ("jfilechooser".equals(prefFileDialog)) {
                logger.debug("load images using jfilechooser");
                files = StandardFileIO.getFileNames(rootPanel);
                logger.debug("AFTER load images using jfilechooser");
            } else {
                logger.debug("load images pushed or menu load images using AWT file dialog");
                files = StandardFileIO.getFileNamesAwt(rootPanel);
                logger.debug("AFTER load images using AWT file dialog");
            }
        } else if ("folder".equals(loadingType)) { // loadingType = folder
            OutputLabel.setText(ResourceBundle.getBundle("translations/program_strings").getString("pt.loadingdirectory"));
            logger.debug("load folder pushed or menu load folder");
            if ("jfilechooser".equals(prefFileDialog)) {
                logger.debug("load folder using jfilechooser");
                files = StandardFileIO.getFolderFiles(rootPanel);
                logger.debug("AFTER load folder using jfilechooser");
            } else {
                logger.debug("load folder using AWT file dialog");
                files = StandardFileIO.getFolderFilesAwt(rootPanel);
                logger.debug("AFTER load folder using AWT file dialog");
            }
        } else { // files dropped onto our app
            OutputLabel.setText("Loading files dropped onto the app");
            files = MyVariables.getSelectedFiles();
        }
        if (files != null) {
            lblLoadedFiles.setText(String.valueOf(files.length));
            logger.debug("After loading images, loading files or dropping files: no. of files > 0");
            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    int jpegcounter = 0;
                    String filename;
                    for (File file : files) {
                        filename = file.getName().replace("\\", "/");
                        logger.debug("Checking on extension for JPG extraction on image: " +filename);
                        String filenameExt = Utils.getFileExtension(filename);
                        if ( (filenameExt.toLowerCase().equals("jpg")) || (filenameExt.toLowerCase().equals("jpeg")) ) {
                            jpegcounter++;
                        }
                    }
                    if (jpegcounter >= 3) {
                        // Always try to extract thumbnails and previews of selected images. This normally only works for JPGs and RAWs
                        ImageFunctions.extractThumbnails();
                    }
                    Utils.displayFiles(mainScreen.this.tableListfiles, mainScreen.this.ListexiftoolInfotable, mainScreen.this.iconLabel);
                    MyVariables.setSelectedRow(0);
                    //tableListfiles.getSelectionBackground();
                    // Select first row and repaint table
                    /*tableListfiles.setRowSelectionInterval(0,0);
                    tableListfiles.setSelectionModel(listSelectionModel);
                    tableListfiles.repaint(); */
                    //tableListfiles.addRowSelectionInterval(0,0);
                    //DefaultTableModel model = (DefaultTableModel) tableListfiles.getModel();
                    //model.setRowColour(0, tableListfiles.getSelectionBackground());
                    //tableListfiles.set
                    /*Component prepareRenderer(TableCellRenderer, int row, int column){
                        if (tableListfiles.isRowSelected(0)) {
                            Component c = super.prepareRenderer(renderer, row, column);
                        }
                    }*/
                    String[] params = whichRBselected();
                    Utils.getImageInfoFromSelectedFile(params, files, mainScreen.this.ListexiftoolInfotable);
                    mainScreen.this.buttonShowImage.setEnabled(true);
                    mainScreen.this.buttonCompare.setEnabled(true);
                    mainScreen.this.buttonSlideShow.setEnabled(true);
                    //OutputLabel.setText(" Images loaded ...");
                    OutputLabel.setText("");
                    // progressbar enabled immedately after this void run starts in the InvokeLater, so I disable it here at the end of this void run
                    Utils.progressStatus(progressBar, false);
                }
            });
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    progressBar.setVisible(true);
                }
            });
        } else {
            logger.debug("no files loaded. User pressed cancel.");
            lblLoadedFiles.setText("");
            OutputLabel.setText("");
        }
        selectedIndicesList = new ArrayList<>();
        MyVariables.setselectedIndicesList(selectedIndicesList);
    }



    private void fillAllComboboxes() {
        //Combobox on User combi edit tab; do as first as we need it again
        String sqlsets = SQLiteJDBC.getdefinedCustomSets();
        String[] views = sqlsets.split("\\r?\\n"); // split on new lines
        // use setter to be later use it for common tags in Utils.getWhichCommonTagSelected
        MyVariables.setCustomCombis(views);
        UserCombiscomboBox.setModel(new DefaultComboBoxModel(views));

        // Fill all combo boxes in the View panel
        String TagNames = StandardFileIO.readTextFileAsStringFromResource("texts/CommonTags.txt");
        String[] Tags = TagNames.split("\\r?\\n"); // split on new lines
        // Now combine Tags[] and above views[] into one array
        int length1 = Tags.length;
        int length2 = views.length;
        String[] allTags = new String[length1 + length2];
        //Using arraycopy method to merge two arrays
        System.arraycopy(Tags, 0, allTags, 0, length1);
        System.arraycopy(views, 0, allTags, length1, length2);
        Arrays.sort(allTags);
        comboBoxViewCommonTags.setModel(new DefaultComboBoxModel(allTags));

        String sqlGroups = SQLiteJDBC.getGroups();
        Tags = sqlGroups.split("\\r?\\n"); // split on new lines
        comboBoxViewByTagName.setModel(new DefaultComboBoxModel(Tags));
        //comboBoxQueryByTagName.setModel(new DefaultComboBoxModel(Tags));


        TagNames = StandardFileIO.readTextFileAsStringFromResource("texts/CameraTagNames.txt");
        Tags = TagNames.split("\\r?\\n"); // split on new lines
        comboBoxViewCameraMake.setModel(new DefaultComboBoxModel(Tags));

        // fill combobox in Lens panel
        TagNames = StandardFileIO.readTextFileAsStringFromResource("texts/lensmeteringmodes.txt");
        Tags = TagNames.split("\\r?\\n"); // split on new lines
        meteringmodecomboBox.setModel(new DefaultComboBoxModel(Tags));

        /*TagNames = StandardFileIO.readTextFileAsStringFromResource("texts/CameraTagNames.txt");
        Tags = TagNames.split("\\r?\\n"); // split on new lines
        comboBoxQueryCameraMake.setModel(new DefaultComboBoxModel(Tags)); */

        //Combobox on Gpano edit tab
        for (String item : MyConstants.GPANO_PROJECTIONS) {
            gpanoPTcomboBox.addItem(item);
        }
    }

    // region IntelliJ GUI Code Generated

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(2, 2, new Insets(10, 10, 10, 10), -1, -1));
        rootPanel.setMinimumSize(new Dimension(1200, 720));
        rootPanel.setPreferredSize(new Dimension(1350, 800));
        rootPanel.setRequestFocusEnabled(true);
        tabbedPaneRight = new JTabbedPane();
        rootPanel.add(tabbedPaneRight, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(700, -1), new Dimension(850, -1), null, 2, false));
        ViewDatapanel = new JPanel();
        ViewDatapanel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        ViewDatapanel.setMinimumSize(new Dimension(-1, -1));
        ViewDatapanel.setPreferredSize(new Dimension(-1, -1));
        tabbedPaneRight.addTab(this.$$$getMessageFromBundle$$$("translations/program_strings", "maintab.viewdata"), ViewDatapanel);
        ViewRadiobuttonpanel = new JPanel();
        ViewRadiobuttonpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 5));
        ViewDatapanel.add(ViewRadiobuttonpanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonViewAll = new JRadioButton();
        radioButtonViewAll.setSelected(true);
        this.$$$loadButtonText$$$(radioButtonViewAll, this.$$$getMessageFromBundle$$$("translations/program_strings", "vdtab.allradiobutton"));
        ViewRadiobuttonpanel.add(radioButtonViewAll);
        radioButtoncommonTags = new JRadioButton();
        this.$$$loadButtonText$$$(radioButtoncommonTags, this.$$$getMessageFromBundle$$$("translations/program_strings", "vdtab.commontags"));
        ViewRadiobuttonpanel.add(radioButtoncommonTags);
        comboBoxViewCommonTags = new JComboBox();
        ViewRadiobuttonpanel.add(comboBoxViewCommonTags);
        radioButtonByTagName = new JRadioButton();
        this.$$$loadButtonText$$$(radioButtonByTagName, this.$$$getMessageFromBundle$$$("translations/program_strings", "vdtab.bygroup"));
        ViewRadiobuttonpanel.add(radioButtonByTagName);
        comboBoxViewByTagName = new JComboBox();
        ViewRadiobuttonpanel.add(comboBoxViewByTagName);
        radioButtonCameraMakes = new JRadioButton();
        radioButtonCameraMakes.setLabel("By Camera");
        this.$$$loadButtonText$$$(radioButtonCameraMakes, this.$$$getMessageFromBundle$$$("translations/program_strings", "vdtab.bycamera"));
        ViewRadiobuttonpanel.add(radioButtonCameraMakes);
        comboBoxViewCameraMake = new JComboBox();
        ViewRadiobuttonpanel.add(comboBoxViewCameraMake);
        ViewDatascrollpanel = new JScrollPane();
        ViewDatapanel.add(ViewDatascrollpanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        ListexiftoolInfotable = new JTable();
        ListexiftoolInfotable.setAutoResizeMode(0);
        ViewDatascrollpanel.setViewportView(ListexiftoolInfotable);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPaneRight.addTab(this.$$$getMessageFromBundle$$$("translations/program_strings", "maintab.editdata"), panel1);
        tabbedPaneEditfunctions = new JTabbedPane();
        panel1.add(tabbedPaneEditfunctions, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        ExifEditpanel = new JPanel();
        ExifEditpanel.setLayout(new GridLayoutManager(5, 1, new Insets(0, 20, 0, 20), -1, -1));
        ExifEditpanel.setMinimumSize(new Dimension(750, 511));
        ExifEditpanel.setPreferredSize(new Dimension(800, 575));
        tabbedPaneEditfunctions.addTab(this.$$$getMessageFromBundle$$$("translations/program_strings", "ed.exiftab"), ExifEditpanel);
        Camera_Equipment = new JPanel();
        Camera_Equipment.setLayout(new GridLayoutManager(3, 3, new Insets(5, 0, 5, 0), -1, -1));
        ExifEditpanel.add(Camera_Equipment, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setPreferredSize(new Dimension(150, 18));
        this.$$$loadLabelText$$$(label1, this.$$$getMessageFromBundle$$$("translations/program_strings", "exif.make"));
        Camera_Equipment.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ExifMaketextField = new JTextField();
        ExifMaketextField.setPreferredSize(new Dimension(500, 25));
        Camera_Equipment.add(ExifMaketextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ExifMakecheckBox = new JCheckBox();
        ExifMakecheckBox.setSelected(true);
        ExifMakecheckBox.setText("");
        Camera_Equipment.add(ExifMakecheckBox, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, Font.BOLD, -1, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        this.$$$loadLabelText$$$(label2, this.$$$getMessageFromBundle$$$("translations/program_strings", "label.save"));
        Camera_Equipment.add(label2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$(null, Font.BOLD, -1, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setPreferredSize(new Dimension(650, 18));
        this.$$$loadLabelText$$$(label3, this.$$$getMessageFromBundle$$$("translations/program_strings", "exif.cameraequip"));
        Camera_Equipment.add(label3, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setPreferredSize(new Dimension(150, 18));
        this.$$$loadLabelText$$$(label4, this.$$$getMessageFromBundle$$$("translations/program_strings", "exif.model"));
        Camera_Equipment.add(label4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ExifModeltextField = new JTextField();
        ExifModeltextField.setPreferredSize(new Dimension(500, 25));
        Camera_Equipment.add(ExifModeltextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ExifModelcheckBox = new JCheckBox();
        ExifModelcheckBox.setSelected(true);
        ExifModelcheckBox.setText("");
        Camera_Equipment.add(ExifModelcheckBox, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        DateTime = new JPanel();
        DateTime.setLayout(new GridLayoutManager(4, 3, new Insets(5, 0, 5, 0), -1, -1));
        ExifEditpanel.add(DateTime, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$(null, Font.BOLD, -1, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setPreferredSize(new Dimension(150, 18));
        this.$$$loadLabelText$$$(label5, this.$$$getMessageFromBundle$$$("translations/program_strings", "exif.datetime"));
        DateTime.add(label5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setPreferredSize(new Dimension(500, 18));
        this.$$$loadLabelText$$$(label6, this.$$$getMessageFromBundle$$$("translations/program_strings", "exif.dtformat"));
        DateTime.add(label6, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setPreferredSize(new Dimension(150, 18));
        this.$$$loadLabelText$$$(label7, this.$$$getMessageFromBundle$$$("translations/program_strings", "exif.modifydate"));
        DateTime.add(label7, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ExifModifyDatetextField = new JTextField();
        ExifModifyDatetextField.setPreferredSize(new Dimension(500, 25));
        DateTime.add(ExifModifyDatetextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ExifModifyDatecheckBox = new JCheckBox();
        ExifModifyDatecheckBox.setSelected(true);
        ExifModifyDatecheckBox.setText("");
        DateTime.add(ExifModifyDatecheckBox, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setPreferredSize(new Dimension(150, 18));
        this.$$$loadLabelText$$$(label8, this.$$$getMessageFromBundle$$$("translations/program_strings", "exif.datetimeoriginal"));
        DateTime.add(label8, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ExifDateTimeOriginaltextField = new JTextField();
        ExifDateTimeOriginaltextField.setPreferredSize(new Dimension(500, 25));
        DateTime.add(ExifDateTimeOriginaltextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ExifDateTimeOriginalcheckBox = new JCheckBox();
        ExifDateTimeOriginalcheckBox.setSelected(true);
        ExifDateTimeOriginalcheckBox.setText("");
        DateTime.add(ExifDateTimeOriginalcheckBox, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setPreferredSize(new Dimension(150, 18));
        this.$$$loadLabelText$$$(label9, this.$$$getMessageFromBundle$$$("translations/program_strings", "exif.createdate"));
        DateTime.add(label9, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ExifCreateDatetextField = new JTextField();
        ExifCreateDatetextField.setPreferredSize(new Dimension(500, 25));
        DateTime.add(ExifCreateDatetextField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ExifCreateDatecheckBox = new JCheckBox();
        ExifCreateDatecheckBox.setSelected(true);
        ExifCreateDatecheckBox.setText("");
        DateTime.add(ExifCreateDatecheckBox, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        CreativeTags = new JPanel();
        CreativeTags.setLayout(new GridLayoutManager(5, 3, new Insets(5, 0, 5, 0), -1, -1));
        ExifEditpanel.add(CreativeTags, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setPreferredSize(new Dimension(150, 18));
        this.$$$loadLabelText$$$(label10, this.$$$getMessageFromBundle$$$("translations/program_strings", "exif.artist"));
        CreativeTags.add(label10, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ExifArtistCreatortextField = new JTextField();
        ExifArtistCreatortextField.setPreferredSize(new Dimension(500, 25));
        CreativeTags.add(ExifArtistCreatortextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ExifArtistCreatorcheckBox = new JCheckBox();
        ExifArtistCreatorcheckBox.setSelected(true);
        ExifArtistCreatorcheckBox.setText("");
        CreativeTags.add(ExifArtistCreatorcheckBox, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        Font label11Font = this.$$$getFont$$$(null, Font.BOLD, -1, label11.getFont());
        if (label11Font != null) label11.setFont(label11Font);
        this.$$$loadLabelText$$$(label11, this.$$$getMessageFromBundle$$$("translations/program_strings", "exif.creatags"));
        CreativeTags.add(label11, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label12 = new JLabel();
        label12.setMaximumSize(new Dimension(250, 18));
        label12.setPreferredSize(new Dimension(150, 18));
        this.$$$loadLabelText$$$(label12, this.$$$getMessageFromBundle$$$("translations/program_strings", "exif.copyright"));
        CreativeTags.add(label12, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ExifCopyrighttextField = new JTextField();
        ExifCopyrighttextField.setPreferredSize(new Dimension(500, 25));
        CreativeTags.add(ExifCopyrighttextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ExifCopyrightcheckBox = new JCheckBox();
        ExifCopyrightcheckBox.setSelected(true);
        ExifCopyrightcheckBox.setText("");
        CreativeTags.add(ExifCopyrightcheckBox, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label13 = new JLabel();
        label13.setPreferredSize(new Dimension(150, 18));
        this.$$$loadLabelText$$$(label13, this.$$$getMessageFromBundle$$$("translations/program_strings", "exif.usercomm"));
        CreativeTags.add(label13, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ExifUsercommenttextField = new JTextField();
        ExifUsercommenttextField.setPreferredSize(new Dimension(500, 25));
        CreativeTags.add(ExifUsercommenttextField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ExifUsercommentcheckBox = new JCheckBox();
        ExifUsercommentcheckBox.setSelected(true);
        ExifUsercommentcheckBox.setText("");
        CreativeTags.add(ExifUsercommentcheckBox, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label14 = new JLabel();
        label14.setPreferredSize(new Dimension(150, 18));
        this.$$$loadLabelText$$$(label14, this.$$$getMessageFromBundle$$$("translations/program_strings", "exif.description"));
        CreativeTags.add(label14, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ExifDescriptiontextArea = new JTextArea();
        ExifDescriptiontextArea.setPreferredSize(new Dimension(500, 80));
        ExifDescriptiontextArea.setWrapStyleWord(true);
        CreativeTags.add(ExifDescriptiontextArea, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ExifDescriptioncheckBox = new JCheckBox();
        ExifDescriptioncheckBox.setSelected(true);
        ExifDescriptioncheckBox.setText("");
        CreativeTags.add(ExifDescriptioncheckBox, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        ExifEditpanel.add(panel2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ExifcopyFromButton = new JButton();
        this.$$$loadButtonText$$$(ExifcopyFromButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.copyfrom"));
        panel2.add(ExifcopyFromButton);
        ExifsaveToButton = new JButton();
        this.$$$loadButtonText$$$(ExifsaveToButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.saveto"));
        panel2.add(ExifsaveToButton);
        ExifBackupOriginalscheckBox = new JCheckBox();
        this.$$$loadButtonText$$$(ExifBackupOriginalscheckBox, this.$$$getMessageFromBundle$$$("translations/program_strings", "chkbox.makebackup"));
        panel2.add(ExifBackupOriginalscheckBox);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        ExifEditpanel.add(panel3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ExifcopyDefaultsButton = new JButton();
        this.$$$loadButtonText$$$(ExifcopyDefaultsButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.copydefaults"));
        panel3.add(ExifcopyDefaultsButton);
        resetFieldsButton = new JButton();
        this.$$$loadButtonText$$$(resetFieldsButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.resetfields"));
        panel3.add(resetFieldsButton);
        ExifhelpButton = new JButton();
        this.$$$loadButtonText$$$(ExifhelpButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.help"));
        panel3.add(ExifhelpButton);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(13, 3, new Insets(10, 20, 10, 20), -1, -1));
        panel4.setMinimumSize(new Dimension(750, 464));
        panel4.setPreferredSize(new Dimension(800, 575));
        tabbedPaneEditfunctions.addTab(this.$$$getMessageFromBundle$$$("translations/program_strings", "ed.xmptab"), panel4);
        final JLabel label15 = new JLabel();
        label15.setPreferredSize(new Dimension(150, 18));
        this.$$$loadLabelText$$$(label15, this.$$$getMessageFromBundle$$$("translations/program_strings", "xmp.creator"));
        panel4.add(label15, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        xmpCreatortextField = new JTextField();
        xmpCreatortextField.setPreferredSize(new Dimension(500, 25));
        xmpCreatortextField.setText("");
        panel4.add(xmpCreatortextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        xmpCreatorcheckBox = new JCheckBox();
        xmpCreatorcheckBox.setSelected(true);
        xmpCreatorcheckBox.setText("");
        panel4.add(xmpCreatorcheckBox, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label16 = new JLabel();
        label16.setPreferredSize(new Dimension(150, 18));
        this.$$$loadLabelText$$$(label16, this.$$$getMessageFromBundle$$$("translations/program_strings", "xmp.rights"));
        panel4.add(label16, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        xmpRightstextField = new JTextField();
        xmpRightstextField.setPreferredSize(new Dimension(500, 25));
        panel4.add(xmpRightstextField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        xmpRightscheckBox = new JCheckBox();
        xmpRightscheckBox.setSelected(true);
        xmpRightscheckBox.setText("");
        panel4.add(xmpRightscheckBox, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label17 = new JLabel();
        label17.setPreferredSize(new Dimension(150, 18));
        this.$$$loadLabelText$$$(label17, this.$$$getMessageFromBundle$$$("translations/program_strings", "xmp.description"));
        panel4.add(label17, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        xmpDescriptiontextArea = new JTextArea();
        xmpDescriptiontextArea.setPreferredSize(new Dimension(500, 80));
        xmpDescriptiontextArea.setWrapStyleWord(true);
        panel4.add(xmpDescriptiontextArea, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        xmpDescriptioncheckBox = new JCheckBox();
        xmpDescriptioncheckBox.setSelected(true);
        xmpDescriptioncheckBox.setText("");
        panel4.add(xmpDescriptioncheckBox, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label18 = new JLabel();
        Font label18Font = this.$$$getFont$$$(null, Font.BOLD, -1, label18.getFont());
        if (label18Font != null) label18.setFont(label18Font);
        this.$$$loadLabelText$$$(label18, this.$$$getMessageFromBundle$$$("translations/program_strings", "label.save"));
        panel4.add(label18, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label19 = new JLabel();
        label19.setPreferredSize(new Dimension(150, 18));
        this.$$$loadLabelText$$$(label19, this.$$$getMessageFromBundle$$$("translations/program_strings", "xmp.label"));
        panel4.add(label19, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        xmpLabeltextField = new JTextField();
        xmpLabeltextField.setPreferredSize(new Dimension(500, 25));
        panel4.add(xmpLabeltextField, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        xmpLabelcheckBox = new JCheckBox();
        xmpLabelcheckBox.setSelected(true);
        xmpLabelcheckBox.setText("");
        panel4.add(xmpLabelcheckBox, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label20 = new JLabel();
        label20.setPreferredSize(new Dimension(150, 18));
        this.$$$loadLabelText$$$(label20, this.$$$getMessageFromBundle$$$("translations/program_strings", "xmp.subject"));
        panel4.add(label20, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        xmpSubjecttextField = new JTextField();
        xmpSubjecttextField.setPreferredSize(new Dimension(500, 25));
        panel4.add(xmpSubjecttextField, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        xmpSubjectcheckBox = new JCheckBox();
        xmpSubjectcheckBox.setSelected(true);
        xmpSubjectcheckBox.setText("");
        panel4.add(xmpSubjectcheckBox, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label21 = new JLabel();
        label21.setPreferredSize(new Dimension(150, 18));
        this.$$$loadLabelText$$$(label21, this.$$$getMessageFromBundle$$$("translations/program_strings", "xmp.pim"));
        panel4.add(label21, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        xmpPersontextField = new JTextField();
        xmpPersontextField.setPreferredSize(new Dimension(500, 25));
        panel4.add(xmpPersontextField, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        xmpPersoncheckBox = new JCheckBox();
        xmpPersoncheckBox.setSelected(true);
        xmpPersoncheckBox.setText("");
        panel4.add(xmpPersoncheckBox, new GridConstraints(10, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel4.add(panel5, new GridConstraints(11, 0, 1, 3, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        xmpCopyFrombutton = new JButton();
        this.$$$loadButtonText$$$(xmpCopyFrombutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.copyfrom"));
        panel5.add(xmpCopyFrombutton);
        xmpSaveTobutton = new JButton();
        this.$$$loadButtonText$$$(xmpSaveTobutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.saveto"));
        panel5.add(xmpSaveTobutton);
        xmpBackupOriginalscheckBox = new JCheckBox();
        this.$$$loadButtonText$$$(xmpBackupOriginalscheckBox, this.$$$getMessageFromBundle$$$("translations/program_strings", "chkbox.makebackup"));
        panel5.add(xmpBackupOriginalscheckBox);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel4.add(panel6, new GridConstraints(12, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        xmpCopyDefaultsbutton = new JButton();
        this.$$$loadButtonText$$$(xmpCopyDefaultsbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.copydefaults"));
        panel6.add(xmpCopyDefaultsbutton);
        xmpResetFieldsbutton = new JButton();
        this.$$$loadButtonText$$$(xmpResetFieldsbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.resetfields"));
        panel6.add(xmpResetFieldsbutton);
        xmpHelpbutton = new JButton();
        this.$$$loadButtonText$$$(xmpHelpbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.help"));
        panel6.add(xmpHelpbutton);
        final JLabel label22 = new JLabel();
        this.$$$loadLabelText$$$(label22, this.$$$getMessageFromBundle$$$("translations/program_strings", "xmp.credline"));
        panel4.add(label22, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(150, 18), null, 0, false));
        xmpCredittextField = new JTextField();
        panel4.add(xmpCredittextField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(500, 25), null, 0, false));
        xmpCreditcheckBox = new JCheckBox();
        xmpCreditcheckBox.setSelected(true);
        xmpCreditcheckBox.setText("");
        panel4.add(xmpCreditcheckBox, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        xmpTopText = new JLabel();
        xmpTopText.setText("xmpTopText");
        panel4.add(xmpTopText, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(500, 18), null, 0, false));
        final JLabel label23 = new JLabel();
        label23.setPreferredSize(new Dimension(150, 18));
        this.$$$loadLabelText$$$(label23, this.$$$getMessageFromBundle$$$("translations/program_strings", "xmp.title"));
        panel4.add(label23, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        xmpTitletextField = new JTextField();
        xmpTitletextField.setPreferredSize(new Dimension(500, 25));
        panel4.add(xmpTitletextField, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        xmpTitlecheckBox = new JCheckBox();
        xmpTitlecheckBox.setSelected(true);
        xmpTitlecheckBox.setText("");
        panel4.add(xmpTitlecheckBox, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label24 = new JLabel();
        this.$$$loadLabelText$$$(label24, this.$$$getMessageFromBundle$$$("translations/program_strings", "xmp.keywords"));
        panel4.add(label24, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        xmpKeywordstextField = new JTextField();
        xmpKeywordstextField.setPreferredSize(new Dimension(500, 25));
        panel4.add(xmpKeywordstextField, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        xmpKeywordscheckBox = new JCheckBox();
        xmpKeywordscheckBox.setSelected(true);
        xmpKeywordscheckBox.setText("");
        panel4.add(xmpKeywordscheckBox, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(6, 2, new Insets(5, 5, 5, 10), -1, -1));
        panel7.setMinimumSize(new Dimension(750, 500));
        panel7.setPreferredSize(new Dimension(800, 550));
        tabbedPaneEditfunctions.addTab(this.$$$getMessageFromBundle$$$("translations/program_strings", "ed.gpstab"), panel7);
        gpsLocationPanel = new JPanel();
        gpsLocationPanel.setLayout(new GridLayoutManager(5, 3, new Insets(5, 5, 5, 5), -1, -1));
        panel7.add(gpsLocationPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 1, false));
        gpsLocationPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label25 = new JLabel();
        Font label25Font = this.$$$getFont$$$(null, Font.BOLD, -1, label25.getFont());
        if (label25Font != null) label25.setFont(label25Font);
        this.$$$loadLabelText$$$(label25, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.xmpiptcloc"));
        gpsLocationPanel.add(label25, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label26 = new JLabel();
        this.$$$loadLabelText$$$(label26, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.whereis"));
        gpsLocationPanel.add(label26, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label27 = new JLabel();
        Font label27Font = this.$$$getFont$$$(null, Font.BOLD, -1, label27.getFont());
        if (label27Font != null) label27.setFont(label27Font);
        this.$$$loadLabelText$$$(label27, this.$$$getMessageFromBundle$$$("translations/program_strings", "label.save"));
        gpsLocationPanel.add(label27, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label28 = new JLabel();
        label28.setPreferredSize(new Dimension(75, 18));
        this.$$$loadLabelText$$$(label28, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.location"));
        gpsLocationPanel.add(label28, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        gpsLocationtextField = new JTextField();
        gpsLocationtextField.setPreferredSize(new Dimension(300, 30));
        gpsLocationPanel.add(gpsLocationtextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(450, 25), null, 0, false));
        gpsLocationcheckBox = new JCheckBox();
        gpsLocationcheckBox.setSelected(true);
        gpsLocationcheckBox.setText("");
        gpsLocationPanel.add(gpsLocationcheckBox, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label29 = new JLabel();
        this.$$$loadLabelText$$$(label29, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.country"));
        gpsLocationPanel.add(label29, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gpsCountrytextField = new JTextField();
        gpsLocationPanel.add(gpsCountrytextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(450, 25), null, 0, false));
        gpsCountrycheckBox = new JCheckBox();
        gpsCountrycheckBox.setSelected(true);
        gpsCountrycheckBox.setText("");
        gpsLocationPanel.add(gpsCountrycheckBox, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label30 = new JLabel();
        this.$$$loadLabelText$$$(label30, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.statprov"));
        gpsLocationPanel.add(label30, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gpsStateProvincetextField = new JTextField();
        gpsStateProvincetextField.setText("");
        gpsLocationPanel.add(gpsStateProvincetextField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(450, 25), null, 0, false));
        gpsStateProvincecheckBox = new JCheckBox();
        gpsStateProvincecheckBox.setSelected(true);
        gpsStateProvincecheckBox.setText("");
        gpsLocationPanel.add(gpsStateProvincecheckBox, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label31 = new JLabel();
        this.$$$loadLabelText$$$(label31, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.city"));
        gpsLocationPanel.add(label31, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gpsCitytextField = new JTextField();
        gpsLocationPanel.add(gpsCitytextField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(450, 25), null, 0, false));
        gpsCitycheckBox = new JCheckBox();
        gpsCitycheckBox.setSelected(true);
        gpsCitycheckBox.setText("");
        gpsLocationPanel.add(gpsCitycheckBox, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gpsLatLonAltPanel = new JPanel();
        gpsLatLonAltPanel.setLayout(new GridLayoutManager(5, 6, new Insets(5, 5, 5, 5), -1, -1));
        panel7.add(gpsLatLonAltPanel, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 1, false));
        gpsLatLonAltPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        SaveLatLonAltcheckBox = new JCheckBox();
        SaveLatLonAltcheckBox.setSelected(true);
        this.$$$loadButtonText$$$(SaveLatLonAltcheckBox, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.savella"));
        gpsLatLonAltPanel.add(SaveLatLonAltcheckBox, new GridConstraints(0, 0, 1, 6, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label32 = new JLabel();
        this.$$$loadLabelText$$$(label32, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.gps"));
        gpsLatLonAltPanel.add(label32, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label33 = new JLabel();
        this.$$$loadLabelText$$$(label33, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.decimal"));
        gpsLatLonAltPanel.add(label33, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label34 = new JLabel();
        this.$$$loadLabelText$$$(label34, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.latitude"));
        gpsLatLonAltPanel.add(label34, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gpsLatDecimaltextField = new JFormattedTextField();
        gpsLatLonAltPanel.add(gpsLatDecimaltextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 25), null, 0, false));
        final JLabel label35 = new JLabel();
        this.$$$loadLabelText$$$(label35, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.altitude"));
        gpsLatLonAltPanel.add(label35, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label36 = new JLabel();
        this.$$$loadLabelText$$$(label36, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.longitude"));
        gpsLatLonAltPanel.add(label36, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gpsLonDecimaltextField = new JFormattedTextField();
        gpsLatLonAltPanel.add(gpsLonDecimaltextField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 25), null, 0, false));
        gpsAltDecimaltextField = new JFormattedTextField();
        gpsLatLonAltPanel.add(gpsAltDecimaltextField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 25), null, 0, false));
        gpsAboveSealevelcheckBox = new JCheckBox();
        gpsAboveSealevelcheckBox.setSelected(true);
        this.$$$loadButtonText$$$(gpsAboveSealevelcheckBox, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.above"));
        gpsLatLonAltPanel.add(gpsAboveSealevelcheckBox, new GridConstraints(4, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gpsCalculationPanel = new JPanel();
        gpsCalculationPanel.setLayout(new GridLayoutManager(5, 8, new Insets(5, 5, 5, 5), -1, -1));
        panel7.add(gpsCalculationPanel, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 1, false));
        gpsCalculationPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        gpsCalculatorLabelText = new JLabel();
        gpsCalculatorLabelText.setText("Label");
        gpsCalculationPanel.add(gpsCalculatorLabelText, new GridConstraints(1, 0, 3, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, -1), null, 0, false));
        final JLabel label37 = new JLabel();
        this.$$$loadLabelText$$$(label37, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.decdegrees"));
        label37.setToolTipText(this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.decdegrees"));
        gpsCalculationPanel.add(label37, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label38 = new JLabel();
        this.$$$loadLabelText$$$(label38, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.degrees"));
        gpsCalculationPanel.add(label38, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label39 = new JLabel();
        this.$$$loadLabelText$$$(label39, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.minutes"));
        gpsCalculationPanel.add(label39, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label40 = new JLabel();
        this.$$$loadLabelText$$$(label40, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.seconds"));
        gpsCalculationPanel.add(label40, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label41 = new JLabel();
        this.$$$loadLabelText$$$(label41, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.latitude"));
        gpsCalculationPanel.add(label41, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CalcLatDecimaltextLabel = new JLabel();
        CalcLatDecimaltextLabel.setText("00.00000");
        gpsCalculationPanel.add(CalcLatDecimaltextLabel, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(75, 25), null, 0, false));
        CalcLatDegtextField = new JFormattedTextField();
        gpsCalculationPanel.add(CalcLatDegtextField, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(30, 25), new Dimension(40, -1), 0, false));
        CalcLatMintextField = new JFormattedTextField();
        gpsCalculationPanel.add(CalcLatMintextField, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(30, 25), new Dimension(40, -1), 0, false));
        CalcLatSectextField = new JFormattedTextField();
        gpsCalculationPanel.add(CalcLatSectextField, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(30, 25), new Dimension(40, -1), 0, false));
        final JLabel label42 = new JLabel();
        this.$$$loadLabelText$$$(label42, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.longitude"));
        gpsCalculationPanel.add(label42, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CalcLonDecimaltextLabel = new JLabel();
        CalcLonDecimaltextLabel.setText("00.00000");
        gpsCalculationPanel.add(CalcLonDecimaltextLabel, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(75, 25), null, 0, false));
        CalcLonDegtextField = new JFormattedTextField();
        gpsCalculationPanel.add(CalcLonDegtextField, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(30, 25), new Dimension(40, -1), 0, false));
        CalcLonMintextField = new JFormattedTextField();
        gpsCalculationPanel.add(CalcLonMintextField, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(30, 25), new Dimension(40, -1), 0, false));
        CalcLonSectextField = new JFormattedTextField();
        gpsCalculationPanel.add(CalcLonSectextField, new GridConstraints(3, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(30, 25), new Dimension(40, -1), 0, false));
        CalcNorthRadioButton = new JRadioButton();
        CalcNorthRadioButton.setSelected(true);
        this.$$$loadButtonText$$$(CalcNorthRadioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.n"));
        gpsCalculationPanel.add(CalcNorthRadioButton, new GridConstraints(2, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CalcSouthRadioButton = new JRadioButton();
        this.$$$loadButtonText$$$(CalcSouthRadioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.s"));
        gpsCalculationPanel.add(CalcSouthRadioButton, new GridConstraints(2, 7, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CalcEastradioButton = new JRadioButton();
        CalcEastradioButton.setSelected(true);
        this.$$$loadButtonText$$$(CalcEastradioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.e"));
        gpsCalculationPanel.add(CalcEastradioButton, new GridConstraints(3, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CalcWestRadioButton = new JRadioButton();
        this.$$$loadButtonText$$$(CalcWestRadioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.w"));
        gpsCalculationPanel.add(CalcWestRadioButton, new GridConstraints(3, 7, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        decimalToMinutesSecondsButton = new JButton();
        decimalToMinutesSecondsButton.setEnabled(false);
        decimalToMinutesSecondsButton.setText("Decimal to minutes-seconds =>");
        decimalToMinutesSecondsButton.setVisible(false);
        gpsCalculationPanel.add(decimalToMinutesSecondsButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minutesSecondsToDecimalButton = new JButton();
        minutesSecondsToDecimalButton.setLabel("Convert to decimal degrees");
        this.$$$loadButtonText$$$(minutesSecondsToDecimalButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.btnconvert"));
        gpsCalculationPanel.add(minutesSecondsToDecimalButton, new GridConstraints(4, 2, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        copyToInputFieldsButton = new JButton();
        this.$$$loadButtonText$$$(copyToInputFieldsButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.btncopytoinp"));
        gpsCalculationPanel.add(copyToInputFieldsButton, new GridConstraints(4, 5, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label43 = new JLabel();
        Font label43Font = this.$$$getFont$$$(null, Font.BOLD, -1, label43.getFont());
        if (label43Font != null) label43.setFont(label43Font);
        this.$$$loadLabelText$$$(label43, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.calc"));
        gpsCalculationPanel.add(label43, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel7.add(spacer1, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(2, 1, new Insets(5, 5, 5, 5), -1, -1));
        panel7.add(panel8, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        gpsButtonPanel = new JPanel();
        gpsButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel8.add(gpsButtonPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        gpsCopyFrombutton = new JButton();
        this.$$$loadButtonText$$$(gpsCopyFrombutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.copyfrom"));
        gpsButtonPanel.add(gpsCopyFrombutton);
        gpsSaveTobutton = new JButton();
        this.$$$loadButtonText$$$(gpsSaveTobutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.saveto"));
        gpsButtonPanel.add(gpsSaveTobutton);
        gpsBackupOriginalscheckBox = new JCheckBox();
        this.$$$loadButtonText$$$(gpsBackupOriginalscheckBox, this.$$$getMessageFromBundle$$$("translations/program_strings", "chkbox.makebackup"));
        gpsButtonPanel.add(gpsBackupOriginalscheckBox);
        gpsButtonPanel2 = new JPanel();
        gpsButtonPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel8.add(gpsButtonPanel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        gpsResetFieldsbutton = new JButton();
        this.$$$loadButtonText$$$(gpsResetFieldsbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.resetfields"));
        gpsButtonPanel2.add(gpsResetFieldsbutton);
        gpsMapcoordinatesbutton = new JButton();
        this.$$$loadButtonText$$$(gpsMapcoordinatesbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.btnopenmapcoords"));
        gpsButtonPanel2.add(gpsMapcoordinatesbutton);
        gpsHelpbutton = new JButton();
        this.$$$loadButtonText$$$(gpsHelpbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.help"));
        gpsButtonPanel2.add(gpsHelpbutton);
        GeotaggingEditpanel = new JPanel();
        GeotaggingEditpanel.setLayout(new GridLayoutManager(8, 1, new Insets(10, 20, 10, 20), -1, -1));
        GeotaggingEditpanel.setMinimumSize(new Dimension(750, 500));
        GeotaggingEditpanel.setPreferredSize(new Dimension(800, 550));
        tabbedPaneEditfunctions.addTab(this.$$$getMessageFromBundle$$$("translations/program_strings", "ed.geotaggingtab"), GeotaggingEditpanel);
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 20, 0), -1, -1));
        GeotaggingEditpanel.add(panel9, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label44 = new JLabel();
        Font label44Font = this.$$$getFont$$$(null, Font.BOLD, -1, label44.getFont());
        if (label44Font != null) label44.setFont(label44Font);
        this.$$$loadLabelText$$$(label44, this.$$$getMessageFromBundle$$$("translations/program_strings", "geo.imagefolder"));
        panel9.add(label44, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel9.add(panel10, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        geotaggingImgFoldertextField = new JTextField();
        geotaggingImgFoldertextField.setPreferredSize(new Dimension(500, 25));
        panel10.add(geotaggingImgFoldertextField);
        geotaggingImgFolderbutton = new JButton();
        this.$$$loadButtonText$$$(geotaggingImgFolderbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "geo.browsebutton"));
        panel10.add(geotaggingImgFolderbutton);
        GeotaggingLeaveFolderEmptyLabel = new JLabel();
        this.$$$loadLabelText$$$(GeotaggingLeaveFolderEmptyLabel, this.$$$getMessageFromBundle$$$("translations/program_strings", "geo.folderexplanation"));
        panel9.add(GeotaggingLeaveFolderEmptyLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(650, -1), null, 0, false));
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        GeotaggingEditpanel.add(panel11, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label45 = new JLabel();
        Font label45Font = this.$$$getFont$$$(null, Font.BOLD, -1, label45.getFont());
        if (label45Font != null) label45.setFont(label45Font);
        this.$$$loadLabelText$$$(label45, this.$$$getMessageFromBundle$$$("translations/program_strings", "geo.gpslogfile"));
        panel11.add(label45, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel11.add(panel12, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        geotaggingGPSLogtextField = new JTextField();
        geotaggingGPSLogtextField.setPreferredSize(new Dimension(500, 25));
        panel12.add(geotaggingGPSLogtextField);
        geotaggingGPSLogbutton = new JButton();
        this.$$$loadButtonText$$$(geotaggingGPSLogbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "geo.browsebutton"));
        panel12.add(geotaggingGPSLogbutton);
        final JPanel panel13 = new JPanel();
        panel13.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        GeotaggingEditpanel.add(panel13, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label46 = new JLabel();
        Font label46Font = this.$$$getFont$$$(null, Font.BOLD, -1, label46.getFont());
        if (label46Font != null) label46.setFont(label46Font);
        this.$$$loadLabelText$$$(label46, this.$$$getMessageFromBundle$$$("translations/program_strings", "geo.geosynctime"));
        panel13.add(label46, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel14 = new JPanel();
        panel14.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel13.add(panel14, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        geotaggingGeosynctextField = new JTextField();
        geotaggingGeosynctextField.setPreferredSize(new Dimension(500, 25));
        geotaggingGeosynctextField.setText("0000:00:00 00:00:00");
        panel14.add(geotaggingGeosynctextField);
        final JLabel label47 = new JLabel();
        this.$$$loadLabelText$$$(label47, this.$$$getMessageFromBundle$$$("translations/program_strings", "geo.dtformat"));
        panel14.add(label47);
        GeotaggingGeosyncExplainLabel = new JLabel();
        this.$$$loadLabelText$$$(GeotaggingGeosyncExplainLabel, this.$$$getMessageFromBundle$$$("translations/program_strings", "geo.geosyncexpl"));
        panel13.add(GeotaggingGeosyncExplainLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel15 = new JPanel();
        panel15.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        GeotaggingEditpanel.add(panel15, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        geotaggingWriteInfobutton = new JButton();
        this.$$$loadButtonText$$$(geotaggingWriteInfobutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "geo.btnwritegeoinfo"));
        panel15.add(geotaggingWriteInfobutton);
        resetGeotaggingbutton = new JButton();
        this.$$$loadButtonText$$$(resetGeotaggingbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.resetfields"));
        panel15.add(resetGeotaggingbutton);
        geotaggingHelpbutton = new JButton();
        this.$$$loadButtonText$$$(geotaggingHelpbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.help"));
        panel15.add(geotaggingHelpbutton);
        geotaggingOverwriteOriginalscheckBox = new JCheckBox();
        geotaggingOverwriteOriginalscheckBox.setSelected(false);
        this.$$$loadButtonText$$$(geotaggingOverwriteOriginalscheckBox, this.$$$getMessageFromBundle$$$("translations/program_strings", "chkbox.makebackup"));
        GeotaggingEditpanel.add(geotaggingOverwriteOriginalscheckBox, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel16 = new JPanel();
        panel16.setLayout(new GridLayoutManager(6, 3, new Insets(5, 5, 5, 5), -1, -1));
        GeotaggingEditpanel.add(panel16, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel16.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label48 = new JLabel();
        Font label48Font = this.$$$getFont$$$(null, Font.BOLD, -1, label48.getFont());
        if (label48Font != null) label48.setFont(label48Font);
        this.$$$loadLabelText$$$(label48, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.xmpiptcloc"));
        panel16.add(label48, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label49 = new JLabel();
        this.$$$loadLabelText$$$(label49, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.whereis"));
        panel16.add(label49, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label50 = new JLabel();
        Font label50Font = this.$$$getFont$$$(null, Font.BOLD, -1, label50.getFont());
        if (label50Font != null) label50.setFont(label50Font);
        this.$$$loadLabelText$$$(label50, this.$$$getMessageFromBundle$$$("translations/program_strings", "label.save"));
        panel16.add(label50, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label51 = new JLabel();
        label51.setPreferredSize(new Dimension(75, 18));
        this.$$$loadLabelText$$$(label51, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.location"));
        panel16.add(label51, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        geotaggingLocationtextfield = new JTextField();
        geotaggingLocationtextfield.setPreferredSize(new Dimension(300, 30));
        panel16.add(geotaggingLocationtextfield, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(400, 25), null, 0, false));
        geotaggingLocationcheckbox = new JCheckBox();
        geotaggingLocationcheckbox.setSelected(false);
        geotaggingLocationcheckbox.setText("");
        panel16.add(geotaggingLocationcheckbox, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label52 = new JLabel();
        this.$$$loadLabelText$$$(label52, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.country"));
        panel16.add(label52, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        geotaggingCountrytextfield = new JTextField();
        panel16.add(geotaggingCountrytextfield, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(400, 25), null, 0, false));
        geotaggingCountrycheckbox = new JCheckBox();
        geotaggingCountrycheckbox.setSelected(false);
        geotaggingCountrycheckbox.setText("");
        panel16.add(geotaggingCountrycheckbox, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label53 = new JLabel();
        this.$$$loadLabelText$$$(label53, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.statprov"));
        panel16.add(label53, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        geotaggingStatetextfield = new JTextField();
        panel16.add(geotaggingStatetextfield, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(400, 25), null, 0, false));
        geotaggingStatecheckbox = new JCheckBox();
        geotaggingStatecheckbox.setSelected(false);
        geotaggingStatecheckbox.setText("");
        panel16.add(geotaggingStatecheckbox, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label54 = new JLabel();
        this.$$$loadLabelText$$$(label54, this.$$$getMessageFromBundle$$$("translations/program_strings", "gps.city"));
        panel16.add(label54, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        geotaggingCitytextfield = new JTextField();
        geotaggingCitytextfield.setText("");
        panel16.add(geotaggingCitytextfield, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(400, 25), null, 0, false));
        geotaggingCitycheckbox = new JCheckBox();
        geotaggingCitycheckbox.setSelected(false);
        geotaggingCitycheckbox.setText("");
        panel16.add(geotaggingCitycheckbox, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        GeotaggingLocationLabel = new JLabel();
        this.$$$loadLabelText$$$(GeotaggingLocationLabel, this.$$$getMessageFromBundle$$$("translations/program_strings", "geo.geotagexpl"));
        panel16.add(GeotaggingLocationLabel, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel17 = new JPanel();
        panel17.setLayout(new GridLayoutManager(6, 1, new Insets(10, 0, 0, 0), -1, -1));
        panel17.setMinimumSize(new Dimension(750, 500));
        panel17.setPreferredSize(new Dimension(800, 550));
        tabbedPaneEditfunctions.addTab(this.$$$getMessageFromBundle$$$("translations/program_strings", "ed.gpanotab"), panel17);
        gPanoTopText = new JLabel();
        this.$$$loadLabelText$$$(gPanoTopText, this.$$$getMessageFromBundle$$$("translations/program_strings", "gpano.toptext"));
        panel17.add(gPanoTopText, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final JPanel panel18 = new JPanel();
        panel18.setLayout(new GridLayoutManager(5, 5, new Insets(5, 5, 5, 5), -1, -1));
        panel17.add(panel18, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 1, false));
        panel18.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label55 = new JLabel();
        this.$$$loadLabelText$$$(label55, this.$$$getMessageFromBundle$$$("translations/program_strings", "gpano.croppedareaimageheightpixels"));
        panel18.add(label55, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, -1), null, 0, false));
        gpanoCAIHPtextField = new JFormattedTextField();
        gpanoCAIHPtextField.setColumns(0);
        gpanoCAIHPtextField.setPreferredSize(new Dimension(75, 30));
        panel18.add(gpanoCAIHPtextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(75, 25), null, 0, false));
        final JLabel label56 = new JLabel();
        label56.setPreferredSize(new Dimension(200, 18));
        this.$$$loadLabelText$$$(label56, this.$$$getMessageFromBundle$$$("translations/program_strings", "gpano.croppedareaimagewidthpixels"));
        panel18.add(label56, new GridConstraints(0, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        gpanoCAIWPtextField = new JFormattedTextField();
        gpanoCAIWPtextField.setPreferredSize(new Dimension(75, 30));
        panel18.add(gpanoCAIWPtextField, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(75, 25), null, 0, false));
        final JLabel label57 = new JLabel();
        label57.setPreferredSize(new Dimension(200, 18));
        this.$$$loadLabelText$$$(label57, this.$$$getMessageFromBundle$$$("translations/program_strings", "gpano.fullpanoheightpixels"));
        panel18.add(label57, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        gpanoFPHPtextField = new JFormattedTextField();
        gpanoFPHPtextField.setPreferredSize(new Dimension(75, 30));
        panel18.add(gpanoFPHPtextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(75, 25), null, 0, false));
        final JLabel label58 = new JLabel();
        label58.setPreferredSize(new Dimension(200, 18));
        this.$$$loadLabelText$$$(label58, this.$$$getMessageFromBundle$$$("translations/program_strings", "gpano.fullpanowidthpixels"));
        panel18.add(label58, new GridConstraints(2, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        gpanoFPWPtextField = new JFormattedTextField();
        gpanoFPWPtextField.setPreferredSize(new Dimension(75, 30));
        panel18.add(gpanoFPWPtextField, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(75, 25), null, 0, false));
        final JLabel label59 = new JLabel();
        this.$$$loadLabelText$$$(label59, this.$$$getMessageFromBundle$$$("translations/program_strings", "gpano.projectiontype"));
        panel18.add(label59, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        gpanoPTcomboBox = new JComboBox();
        panel18.add(gpanoPTcomboBox, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label60 = new JLabel();
        this.$$$loadLabelText$$$(label60, this.$$$getMessageFromBundle$$$("translations/program_strings", "gpano.usepanoramaviewer"));
        panel18.add(label60, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBox1 = new JCheckBox();
        checkBox1.setEnabled(false);
        checkBox1.setSelected(true);
        checkBox1.setText("");
        panel18.add(checkBox1, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label61 = new JLabel();
        label61.setPreferredSize(new Dimension(200, 18));
        this.$$$loadLabelText$$$(label61, this.$$$getMessageFromBundle$$$("translations/program_strings", "gpano.croppedarealeftpixels"));
        panel18.add(label61, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label62 = new JLabel();
        label62.setPreferredSize(new Dimension(200, 18));
        this.$$$loadLabelText$$$(label62, this.$$$getMessageFromBundle$$$("translations/program_strings", "gpano.croppedareatoppixels"));
        panel18.add(label62, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        gpanoCATPtextField = new JFormattedTextField();
        gpanoCATPtextField.setPreferredSize(new Dimension(75, 30));
        panel18.add(gpanoCATPtextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(75, 25), null, 0, false));
        gpanoCALPtextField = new JFormattedTextField();
        gpanoCALPtextField.setPreferredSize(new Dimension(75, 30));
        panel18.add(gpanoCALPtextField, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(75, 25), null, 0, false));
        final JPanel panel19 = new JPanel();
        panel19.setLayout(new GridLayoutManager(7, 3, new Insets(5, 5, 5, 5), -1, -1));
        panel17.add(panel19, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 1, false));
        panel19.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label63 = new JLabel();
        this.$$$loadLabelText$$$(label63, this.$$$getMessageFromBundle$$$("translations/program_strings", "gpano.initialviewheadingdegrees"));
        panel19.add(label63, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, -1), null, 0, false));
        gpanoIVHDtextField = new JFormattedTextField();
        panel19.add(gpanoIVHDtextField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(75, 25), null, 0, false));
        gpanoIVHDCheckBox = new JCheckBox();
        gpanoIVHDCheckBox.setText("");
        panel19.add(gpanoIVHDCheckBox, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label64 = new JLabel();
        this.$$$loadLabelText$$$(label64, this.$$$getMessageFromBundle$$$("translations/program_strings", "gpano.initialviewpitchdegrees"));
        panel19.add(label64, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gpanoIVPDtextField = new JFormattedTextField();
        panel19.add(gpanoIVPDtextField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(75, 25), null, 0, false));
        gpanoIVPDCheckBox = new JCheckBox();
        gpanoIVPDCheckBox.setText("");
        panel19.add(gpanoIVPDCheckBox, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label65 = new JLabel();
        this.$$$loadLabelText$$$(label65, this.$$$getMessageFromBundle$$$("translations/program_strings", "gpano.initialviewrolldegrees"));
        panel19.add(label65, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gpanoIVRDtextField = new JFormattedTextField();
        panel19.add(gpanoIVRDtextField, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(75, 25), null, 0, false));
        gpanoIVRDCheckBox = new JCheckBox();
        gpanoIVRDCheckBox.setText("");
        panel19.add(gpanoIVRDCheckBox, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label66 = new JLabel();
        this.$$$loadLabelText$$$(label66, this.$$$getMessageFromBundle$$$("translations/program_strings", "gpano.initialhorizontalfovdegrees"));
        panel19.add(label66, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gpanoIHFOVDtextField = new JFormattedTextField();
        panel19.add(gpanoIHFOVDtextField, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(75, 25), null, 0, false));
        gpanoIHFOVDtextFieldCheckBox = new JCheckBox();
        gpanoIHFOVDtextFieldCheckBox.setText("");
        panel19.add(gpanoIHFOVDtextFieldCheckBox, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label67 = new JLabel();
        label67.setPreferredSize(new Dimension(200, 18));
        this.$$$loadLabelText$$$(label67, this.$$$getMessageFromBundle$$$("translations/program_strings", "gpano.stitchingsoftware"));
        panel19.add(label67, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, -1), null, 0, false));
        gpanoStitchingSoftwaretextField = new JTextField();
        gpanoStitchingSoftwaretextField.setPreferredSize(new Dimension(450, 25));
        panel19.add(gpanoStitchingSoftwaretextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(400, 25), null, 0, false));
        gpanoStitchingSoftwarecheckBox = new JCheckBox();
        gpanoStitchingSoftwarecheckBox.setText("");
        panel19.add(gpanoStitchingSoftwarecheckBox, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label68 = new JLabel();
        this.$$$loadLabelText$$$(label68, this.$$$getMessageFromBundle$$$("translations/program_strings", "gpano.poseheadingdegrees"));
        panel19.add(label68, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gpanoPHDtextField = new JFormattedTextField();
        panel19.add(gpanoPHDtextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(75, 25), null, 0, false));
        gpanoPHDcheckBox = new JCheckBox();
        gpanoPHDcheckBox.setText("");
        panel19.add(gpanoPHDcheckBox, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label69 = new JLabel();
        Font label69Font = this.$$$getFont$$$(null, Font.BOLD, -1, label69.getFont());
        if (label69Font != null) label69.setFont(label69Font);
        label69.setRequestFocusEnabled(false);
        this.$$$loadLabelText$$$(label69, this.$$$getMessageFromBundle$$$("translations/program_strings", "label.save"));
        panel19.add(label69, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel20 = new JPanel();
        panel20.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel17.add(panel20, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        gpanoCopyFrombutton = new JButton();
        this.$$$loadButtonText$$$(gpanoCopyFrombutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.copyfrom"));
        panel20.add(gpanoCopyFrombutton);
        gpanoCopyTobutton = new JButton();
        this.$$$loadButtonText$$$(gpanoCopyTobutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.saveto"));
        panel20.add(gpanoCopyTobutton);
        gpanoResetFieldsbutton = new JButton();
        this.$$$loadButtonText$$$(gpanoResetFieldsbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.resetfields"));
        panel20.add(gpanoResetFieldsbutton);
        gpanoHelpbutton = new JButton();
        gpanoHelpbutton.setLabel("Help");
        this.$$$loadButtonText$$$(gpanoHelpbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.help"));
        panel20.add(gpanoHelpbutton);
        gpanoOverwriteOriginalscheckBox = new JCheckBox();
        this.$$$loadButtonText$$$(gpanoOverwriteOriginalscheckBox, this.$$$getMessageFromBundle$$$("translations/program_strings", "chkbox.makebackup"));
        panel17.add(gpanoOverwriteOriginalscheckBox, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 1, false));
        gpanoMinVersionText = new JLabel();
        this.$$$loadLabelText$$$(gpanoMinVersionText, this.$$$getMessageFromBundle$$$("translations/program_strings", "gpano.googlemapsfields"));
        gpanoMinVersionText.setVisible(true);
        panel17.add(gpanoMinVersionText, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final JPanel panel21 = new JPanel();
        panel21.setLayout(new GridLayoutManager(20, 3, new Insets(10, 20, 10, 20), -1, -1));
        panel21.setMinimumSize(new Dimension(750, 500));
        panel21.setPreferredSize(new Dimension(800, 550));
        tabbedPaneEditfunctions.addTab(this.$$$getMessageFromBundle$$$("translations/program_strings", "ed.lenstab"), panel21);
        final JLabel label70 = new JLabel();
        this.$$$loadLabelText$$$(label70, this.$$$getMessageFromBundle$$$("translations/program_strings", "lens.lensmake"));
        panel21.add(label70, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lensmaketextField = new JTextField();
        panel21.add(lensmaketextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel22 = new JPanel();
        panel22.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel21.add(panel22, new GridConstraints(16, 0, 1, 2, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lensCopyFrombutton = new JButton();
        this.$$$loadButtonText$$$(lensCopyFrombutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.copyfrom"));
        panel22.add(lensCopyFrombutton);
        lensSaveTobutton = new JButton();
        this.$$$loadButtonText$$$(lensSaveTobutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.saveto"));
        panel22.add(lensSaveTobutton);
        lensResetFieldsbutton = new JButton();
        this.$$$loadButtonText$$$(lensResetFieldsbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.resetfields"));
        panel22.add(lensResetFieldsbutton);
        lensHelpbutton = new JButton();
        lensHelpbutton.setLabel("Help");
        this.$$$loadButtonText$$$(lensHelpbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.help"));
        panel22.add(lensHelpbutton);
        final JLabel label71 = new JLabel();
        this.$$$loadLabelText$$$(label71, this.$$$getMessageFromBundle$$$("translations/program_strings", "lens.lensmodel"));
        panel21.add(label71, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lensmodeltextField = new JTextField();
        panel21.add(lensmodeltextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lensmodelcheckBox = new JCheckBox();
        lensmodelcheckBox.setSelected(true);
        lensmodelcheckBox.setText("");
        panel21.add(lensmodelcheckBox, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label72 = new JLabel();
        Font label72Font = this.$$$getFont$$$(null, Font.BOLD, -1, label72.getFont());
        if (label72Font != null) label72.setFont(label72Font);
        this.$$$loadLabelText$$$(label72, this.$$$getMessageFromBundle$$$("translations/program_strings", "label.save"));
        panel21.add(label72, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lensmakecheckBox = new JCheckBox();
        lensmakecheckBox.setSelected(true);
        lensmakecheckBox.setText("");
        panel21.add(lensmakecheckBox, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label73 = new JLabel();
        this.$$$loadLabelText$$$(label73, this.$$$getMessageFromBundle$$$("translations/program_strings", "lens.serialnumber"));
        panel21.add(label73, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lensserialnumbertextField = new JTextField();
        panel21.add(lensserialnumbertextField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label74 = new JLabel();
        this.$$$loadLabelText$$$(label74, this.$$$getMessageFromBundle$$$("translations/program_strings", "lens.focallength"));
        panel21.add(label74, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        focallengthtextField = new JTextField();
        panel21.add(focallengthtextField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lensserialnumbercheckBox = new JCheckBox();
        lensserialnumbercheckBox.setSelected(true);
        lensserialnumbercheckBox.setText("");
        panel21.add(lensserialnumbercheckBox, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        focallengthcheckBox = new JCheckBox();
        focallengthcheckBox.setSelected(true);
        focallengthcheckBox.setText("");
        panel21.add(focallengthcheckBox, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label75 = new JLabel();
        this.$$$loadLabelText$$$(label75, this.$$$getMessageFromBundle$$$("translations/program_strings", "lens.focallength35mm"));
        panel21.add(label75, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        focallengthIn35mmformattextField = new JTextField();
        panel21.add(focallengthIn35mmformattextField, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        focallengthIn35mmformatcheckBox = new JCheckBox();
        focallengthIn35mmformatcheckBox.setSelected(true);
        focallengthIn35mmformatcheckBox.setText("");
        panel21.add(focallengthIn35mmformatcheckBox, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label76 = new JLabel();
        this.$$$loadLabelText$$$(label76, this.$$$getMessageFromBundle$$$("translations/program_strings", "lens.fnumber"));
        panel21.add(label76, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fnumbertextField = new JTextField();
        panel21.add(fnumbertextField, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        fnumbercheckBox = new JCheckBox();
        fnumbercheckBox.setSelected(true);
        fnumbercheckBox.setText("");
        panel21.add(fnumbercheckBox, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label77 = new JLabel();
        this.$$$loadLabelText$$$(label77, this.$$$getMessageFromBundle$$$("translations/program_strings", "lens.maxaperturevalue"));
        panel21.add(label77, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maxaperturevaluetextField = new JTextField();
        panel21.add(maxaperturevaluetextField, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        maxaperturevaluecheckBox = new JCheckBox();
        maxaperturevaluecheckBox.setSelected(true);
        maxaperturevaluecheckBox.setText("");
        panel21.add(maxaperturevaluecheckBox, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label78 = new JLabel();
        this.$$$loadLabelText$$$(label78, this.$$$getMessageFromBundle$$$("translations/program_strings", "lens.meteringmode"));
        panel21.add(label78, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        meteringmodecomboBox = new JComboBox();
        panel21.add(meteringmodecomboBox, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        meteringmodecheckBox = new JCheckBox();
        meteringmodecheckBox.setSelected(true);
        meteringmodecheckBox.setText("");
        panel21.add(meteringmodecheckBox, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label79 = new JLabel();
        this.$$$loadLabelText$$$(label79, this.$$$getMessageFromBundle$$$("translations/program_strings", "lens.focusdistance"));
        panel21.add(label79, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        focusdistancetextField = new JTextField();
        panel21.add(focusdistancetextField, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        focusdistancecheckBox = new JCheckBox();
        focusdistancecheckBox.setSelected(true);
        focusdistancecheckBox.setText("");
        panel21.add(focusdistancecheckBox, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label80 = new JLabel();
        this.$$$loadLabelText$$$(label80, this.$$$getMessageFromBundle$$$("translations/program_strings", "lens.lensid"));
        panel21.add(label80, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lensidtextField = new JTextField();
        panel21.add(lensidtextField, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lensidcheckBox = new JCheckBox();
        lensidcheckBox.setSelected(true);
        lensidcheckBox.setText("");
        panel21.add(lensidcheckBox, new GridConstraints(10, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label81 = new JLabel();
        this.$$$loadLabelText$$$(label81, this.$$$getMessageFromBundle$$$("translations/program_strings", "lens.conversionlens"));
        panel21.add(label81, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        conversionlenstextField = new JTextField();
        panel21.add(conversionlenstextField, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        conversionlenscheckBox = new JCheckBox();
        conversionlenscheckBox.setSelected(true);
        conversionlenscheckBox.setText("");
        panel21.add(conversionlenscheckBox, new GridConstraints(11, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label82 = new JLabel();
        this.$$$loadLabelText$$$(label82, this.$$$getMessageFromBundle$$$("translations/program_strings", "lens.lenstype"));
        panel21.add(label82, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lenstypetextField = new JTextField();
        panel21.add(lenstypetextField, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lenstypecheckBox = new JCheckBox();
        lenstypecheckBox.setSelected(true);
        lenstypecheckBox.setText("");
        panel21.add(lenstypecheckBox, new GridConstraints(12, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label83 = new JLabel();
        this.$$$loadLabelText$$$(label83, this.$$$getMessageFromBundle$$$("translations/program_strings", "lens.firmwarevers"));
        panel21.add(label83, new GridConstraints(13, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lensfirmwareversiontextField = new JTextField();
        panel21.add(lensfirmwareversiontextField, new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lensfirmwareversioncheckBox = new JCheckBox();
        lensfirmwareversioncheckBox.setSelected(true);
        lensfirmwareversioncheckBox.setText("");
        panel21.add(lensfirmwareversioncheckBox, new GridConstraints(13, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lensOverwriteOriginalscheckBox = new JCheckBox();
        this.$$$loadButtonText$$$(lensOverwriteOriginalscheckBox, this.$$$getMessageFromBundle$$$("translations/program_strings", "chkbox.makebackup"));
        panel21.add(lensOverwriteOriginalscheckBox, new GridConstraints(15, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 1, false));
        final Spacer spacer2 = new Spacer();
        panel21.add(spacer2, new GridConstraints(14, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 25), new Dimension(-1, 25), null, 0, false));
        saveloadlensconfigpanel = new JPanel();
        saveloadlensconfigpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel21.add(saveloadlensconfigpanel, new GridConstraints(19, 0, 1, 2, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        saveloadlensconfigpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        saveLensConfigurationbutton = new JButton();
        this.$$$loadButtonText$$$(saveLensConfigurationbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "lens.btnconfigsave"));
        saveLensConfigurationbutton.setToolTipText("This allows you to save lens configurations for future use");
        saveloadlensconfigpanel.add(saveLensConfigurationbutton);
        loadLensConfigurationButton = new JButton();
        this.$$$loadButtonText$$$(loadLensConfigurationButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "lens.btnconfigload"));
        saveloadlensconfigpanel.add(loadLensConfigurationButton);
        final Spacer spacer3 = new Spacer();
        panel21.add(spacer3, new GridConstraints(17, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        lensSaveLoadConfigLabel = new JLabel();
        this.$$$loadLabelText$$$(lensSaveLoadConfigLabel, this.$$$getMessageFromBundle$$$("translations/program_strings", "lens.saveloadconfig"));
        panel21.add(lensSaveLoadConfigLabel, new GridConstraints(18, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel23 = new JPanel();
        panel23.setLayout(new GridLayoutManager(7, 1, new Insets(10, 20, 10, 20), -1, -1));
        panel23.setMinimumSize(new Dimension(750, 397));
        panel23.setPreferredSize(new Dimension(800, 397));
        tabbedPaneEditfunctions.addTab(this.$$$getMessageFromBundle$$$("translations/program_strings", "ed.xmpiptcstringtab"), panel23);
        StringsTopText = new JLabel();
        StringsTopText.setText("StringstopText");
        panel23.add(StringsTopText, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel24 = new JPanel();
        panel24.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        panel23.add(panel24, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel24.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label84 = new JLabel();
        this.$$$loadLabelText$$$(label84, this.$$$getMessageFromBundle$$$("translations/program_strings", "xis.keywords"));
        panel24.add(label84, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(140, -1), null, 0, false));
        StringsKeywordstextField = new JTextField();
        panel24.add(StringsKeywordstextField, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        StringsKeywordsXmpcheckBox = new JCheckBox();
        StringsKeywordsXmpcheckBox.setText("XMP");
        panel24.add(StringsKeywordsXmpcheckBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        StringsKeywordsIPTCcheckBox = new JCheckBox();
        StringsKeywordsIPTCcheckBox.setText("IPTC");
        panel24.add(StringsKeywordsIPTCcheckBox, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel25 = new JPanel();
        panel25.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel24.add(panel25, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label85 = new JLabel();
        this.$$$loadLabelText$$$(label85, this.$$$getMessageFromBundle$$$("translations/program_strings", "xis.action"));
        panel25.add(label85);
        StringsKeywOverwriteradioButton = new JRadioButton();
        this.$$$loadButtonText$$$(StringsKeywOverwriteradioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "xis.overwrite"));
        panel25.add(StringsKeywOverwriteradioButton);
        StringsKeywAppendradioButton = new JRadioButton();
        this.$$$loadButtonText$$$(StringsKeywAppendradioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "xis.append"));
        panel25.add(StringsKeywAppendradioButton);
        StringsKeywRemoveradioButton = new JRadioButton();
        this.$$$loadButtonText$$$(StringsKeywRemoveradioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "xis.remove"));
        panel25.add(StringsKeywRemoveradioButton);
        StringsKeywDontSaveradioButton = new JRadioButton();
        StringsKeywDontSaveradioButton.setSelected(true);
        this.$$$loadButtonText$$$(StringsKeywDontSaveradioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "xis.dontsave"));
        panel25.add(StringsKeywDontSaveradioButton);
        final JPanel panel26 = new JPanel();
        panel26.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        panel23.add(panel26, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel26.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label86 = new JLabel();
        this.$$$loadLabelText$$$(label86, this.$$$getMessageFromBundle$$$("translations/program_strings", "xis.subject"));
        panel26.add(label86, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(140, -1), null, 0, false));
        StringsSubjecttextField = new JTextField();
        panel26.add(StringsSubjecttextField, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label87 = new JLabel();
        this.$$$loadLabelText$$$(label87, this.$$$getMessageFromBundle$$$("translations/program_strings", "xis.onlyxmp"));
        panel26.add(label87, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel27 = new JPanel();
        panel27.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel26.add(panel27, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label88 = new JLabel();
        this.$$$loadLabelText$$$(label88, this.$$$getMessageFromBundle$$$("translations/program_strings", "xis.action"));
        panel27.add(label88);
        StringsSubjectOverwriteradioButton = new JRadioButton();
        this.$$$loadButtonText$$$(StringsSubjectOverwriteradioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "xis.overwrite"));
        panel27.add(StringsSubjectOverwriteradioButton);
        StringsSubjectAppendradioButton = new JRadioButton();
        this.$$$loadButtonText$$$(StringsSubjectAppendradioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "xis.append"));
        panel27.add(StringsSubjectAppendradioButton);
        StringsSubjectRemoveradioButton = new JRadioButton();
        this.$$$loadButtonText$$$(StringsSubjectRemoveradioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "xis.remove"));
        panel27.add(StringsSubjectRemoveradioButton);
        StringsSubjectDontSaveradioButton = new JRadioButton();
        StringsSubjectDontSaveradioButton.setSelected(true);
        this.$$$loadButtonText$$$(StringsSubjectDontSaveradioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "xis.dontsave"));
        panel27.add(StringsSubjectDontSaveradioButton);
        final JPanel panel28 = new JPanel();
        panel28.setLayout(new GridLayoutManager(2, 4, new Insets(5, 5, 5, 5), -1, -1));
        panel23.add(panel28, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel28.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label89 = new JLabel();
        this.$$$loadLabelText$$$(label89, this.$$$getMessageFromBundle$$$("translations/program_strings", "xis.pim"));
        panel28.add(label89, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(140, -1), null, 0, false));
        StringsPIItextField = new JTextField();
        panel28.add(StringsPIItextField, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        StringsIIPXmpcheckBox = new JLabel();
        this.$$$loadLabelText$$$(StringsIIPXmpcheckBox, this.$$$getMessageFromBundle$$$("translations/program_strings", "xis.onlyxmp"));
        panel28.add(StringsIIPXmpcheckBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel29 = new JPanel();
        panel29.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel28.add(panel29, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label90 = new JLabel();
        this.$$$loadLabelText$$$(label90, this.$$$getMessageFromBundle$$$("translations/program_strings", "xis.action"));
        panel29.add(label90);
        StringsIIPOverwriteradioButton = new JRadioButton();
        this.$$$loadButtonText$$$(StringsIIPOverwriteradioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "xis.overwrite"));
        panel29.add(StringsIIPOverwriteradioButton);
        StringsIIPAppendradioButton = new JRadioButton();
        this.$$$loadButtonText$$$(StringsIIPAppendradioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "xis.append"));
        panel29.add(StringsIIPAppendradioButton);
        StringsIIPRemoveradioButton = new JRadioButton();
        this.$$$loadButtonText$$$(StringsIIPRemoveradioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "xis.remove"));
        panel29.add(StringsIIPRemoveradioButton);
        StringsIIPDontSaveradioButton = new JRadioButton();
        StringsIIPDontSaveradioButton.setSelected(true);
        this.$$$loadButtonText$$$(StringsIIPDontSaveradioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "xis.dontsave"));
        panel29.add(StringsIIPDontSaveradioButton);
        stringPlusOverwriteOriginalscheckBox = new JCheckBox();
        this.$$$loadButtonText$$$(stringPlusOverwriteOriginalscheckBox, this.$$$getMessageFromBundle$$$("translations/program_strings", "chkbox.makebackup"));
        panel23.add(stringPlusOverwriteOriginalscheckBox, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 1, false));
        final JPanel panel30 = new JPanel();
        panel30.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel23.add(panel30, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        stringPlusCopyFrombutton = new JButton();
        this.$$$loadButtonText$$$(stringPlusCopyFrombutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.copyfrom"));
        panel30.add(stringPlusCopyFrombutton);
        stringPlusSaveTobutton = new JButton();
        this.$$$loadButtonText$$$(stringPlusSaveTobutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.saveto"));
        panel30.add(stringPlusSaveTobutton);
        stringPlusResetFieldsbutton = new JButton();
        this.$$$loadButtonText$$$(stringPlusResetFieldsbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.resetfields"));
        panel30.add(stringPlusResetFieldsbutton);
        stringPlusHelpbutton = new JButton();
        stringPlusHelpbutton.setLabel("Help");
        stringPlusHelpbutton.setText("Help");
        stringPlusHelpbutton.setVisible(false);
        panel30.add(stringPlusHelpbutton);
        final Spacer spacer4 = new Spacer();
        panel23.add(spacer4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel31 = new JPanel();
        panel31.setLayout(new GridLayoutManager(2, 1, new Insets(10, 20, 10, 20), -1, -1));
        panel31.setMinimumSize(new Dimension(750, 204));
        panel31.setPreferredSize(new Dimension(800, 550));
        tabbedPaneEditfunctions.addTab(this.$$$getMessageFromBundle$$$("translations/program_strings", "ed.usercombis"), panel31);
        final JPanel panel32 = new JPanel();
        panel32.setLayout(new GridLayoutManager(5, 1, new Insets(0, 5, 0, 5), -1, -1));
        panel31.add(panel32, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel33 = new JPanel();
        panel33.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel32.add(panel33, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label91 = new JLabel();
        this.$$$loadLabelText$$$(label91, this.$$$getMessageFromBundle$$$("translations/program_strings", "udc.sets"));
        panel33.add(label91);
        UserCombiscomboBox = new JComboBox();
        panel33.add(UserCombiscomboBox);
        udcCreateNewButton = new JButton();
        this.$$$loadButtonText$$$(udcCreateNewButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "udc.crbutton"));
        panel33.add(udcCreateNewButton);
        CustomConfiglabel = new JLabel();
        CustomConfiglabel.setText("");
        CustomConfiglabel.setVisible(false);
        panel33.add(CustomConfiglabel);
        UserCombiScrollPane = new JScrollPane();
        panel32.add(UserCombiScrollPane, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(700, -1), new Dimension(800, -1), null, 0, false));
        UserCombiTable = new JTable();
        UserCombiScrollPane.setViewportView(UserCombiTable);
        final JPanel panel34 = new JPanel();
        panel34.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel32.add(panel34, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        udcCopyFrombutton = new JButton();
        this.$$$loadButtonText$$$(udcCopyFrombutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.copyfrom"));
        panel34.add(udcCopyFrombutton);
        udcSaveTobutton = new JButton();
        this.$$$loadButtonText$$$(udcSaveTobutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.saveto"));
        panel34.add(udcSaveTobutton);
        udcResetFieldsbutton = new JButton();
        this.$$$loadButtonText$$$(udcResetFieldsbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.resetfields"));
        udcResetFieldsbutton.setVisible(false);
        panel34.add(udcResetFieldsbutton);
        udcHelpbutton = new JButton();
        udcHelpbutton.setLabel("Help");
        this.$$$loadButtonText$$$(udcHelpbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.help"));
        panel34.add(udcHelpbutton);
        UserCombiTopText = new JLabel();
        this.$$$loadLabelText$$$(UserCombiTopText, this.$$$getMessageFromBundle$$$("translations/program_strings", "udc.toptext"));
        panel32.add(UserCombiTopText, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel35 = new JPanel();
        panel35.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel32.add(panel35, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        udcOverwriteOriginalscheckBox = new JCheckBox();
        this.$$$loadButtonText$$$(udcOverwriteOriginalscheckBox, this.$$$getMessageFromBundle$$$("translations/program_strings", "chkbox.makebackup"));
        panel35.add(udcOverwriteOriginalscheckBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel35.add(spacer5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panel31.add(spacer6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel36 = new JPanel();
        panel36.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPaneRight.addTab(this.$$$getMessageFromBundle$$$("translations/program_strings", "maintab.copydata"), panel36);
        CopyDatatabbedPane = new JTabbedPane();
        panel36.add(CopyDatatabbedPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel37 = new JPanel();
        panel37.setLayout(new GridLayoutManager(1, 1, new Insets(10, 0, 0, 0), -1, -1));
        CopyDatatabbedPane.addTab(this.$$$getMessageFromBundle$$$("translations/program_strings", "cd.copyfromto"), panel37);
        final JPanel panel38 = new JPanel();
        panel38.setLayout(new GridLayoutManager(6, 1, new Insets(10, 10, 5, 5), -1, -1));
        panel38.setPreferredSize(new Dimension(800, 500));
        panel37.add(panel38, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        copyAllMetadataRadiobutton = new JRadioButton();
        this.$$$loadButtonText$$$(copyAllMetadataRadiobutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "copyd.copyallmetadatasame"));
        panel38.add(copyAllMetadataRadiobutton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        copyAllMetadataSameGroupsRadiobutton = new JRadioButton();
        copyAllMetadataSameGroupsRadiobutton.setText("<html>Copy the values of all writable tags from the source image to the target image(s), preserving the original tag groups</html>");
        panel38.add(copyAllMetadataSameGroupsRadiobutton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        copySelectiveMetadataradioButton = new JRadioButton();
        copySelectiveMetadataradioButton.setSelected(true);
        copySelectiveMetadataradioButton.setText("Copy metadata using below mentioned selective group options");
        panel38.add(copySelectiveMetadataradioButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel39 = new JPanel();
        panel39.setLayout(new GridLayoutManager(7, 1, new Insets(10, 0, 15, 0), -1, -1));
        panel38.add(panel39, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 1, false));
        panel39.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        CopyExifcheckBox = new JCheckBox();
        this.$$$loadButtonText$$$(CopyExifcheckBox, this.$$$getMessageFromBundle$$$("translations/program_strings", "copyd.copyexifcheckbox"));
        panel39.add(CopyExifcheckBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CopyXmpcheckBox = new JCheckBox();
        this.$$$loadButtonText$$$(CopyXmpcheckBox, this.$$$getMessageFromBundle$$$("translations/program_strings", "copyd.copyxmpcheckbox"));
        panel39.add(CopyXmpcheckBox, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CopyIptccheckBox = new JCheckBox();
        this.$$$loadButtonText$$$(CopyIptccheckBox, this.$$$getMessageFromBundle$$$("translations/program_strings", "copyd.copyiptccheckbox"));
        panel39.add(CopyIptccheckBox, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CopyIcc_profileDataCheckBox = new JCheckBox();
        CopyIcc_profileDataCheckBox.setActionCommand("");
        this.$$$loadButtonText$$$(CopyIcc_profileDataCheckBox, this.$$$getMessageFromBundle$$$("translations/program_strings", "copyd.copyicc_profilecheckbox"));
        panel39.add(CopyIcc_profileDataCheckBox, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CopyGpsCheckBox = new JCheckBox();
        this.$$$loadButtonText$$$(CopyGpsCheckBox, this.$$$getMessageFromBundle$$$("translations/program_strings", "copyd.copygpscheckbox"));
        panel39.add(CopyGpsCheckBox, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CopyJfifcheckBox = new JCheckBox();
        this.$$$loadButtonText$$$(CopyJfifcheckBox, this.$$$getMessageFromBundle$$$("translations/program_strings", "copyd.copyjfifcheckbox"));
        panel39.add(CopyJfifcheckBox, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CopyMakernotescheckBox = new JCheckBox();
        this.$$$loadButtonText$$$(CopyMakernotescheckBox, this.$$$getMessageFromBundle$$$("translations/program_strings", "copyd.copymakernotescheckbox"));
        panel39.add(CopyMakernotescheckBox, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        BackupOriginalscheckBox = new JCheckBox();
        this.$$$loadButtonText$$$(BackupOriginalscheckBox, this.$$$getMessageFromBundle$$$("translations/program_strings", "chkbox.makebackup"));
        panel38.add(BackupOriginalscheckBox, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel40 = new JPanel();
        panel40.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel38.add(panel40, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        UseDataFrombutton = new JButton();
        this.$$$loadButtonText$$$(UseDataFrombutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.copyfrom"));
        panel40.add(UseDataFrombutton);
        CopyDataCopyTobutton = new JButton();
        this.$$$loadButtonText$$$(CopyDataCopyTobutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.saveto"));
        panel40.add(CopyDataCopyTobutton);
        CopyHelpbutton = new JButton();
        this.$$$loadButtonText$$$(CopyHelpbutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.help"));
        panel40.add(CopyHelpbutton);
        final JPanel panel41 = new JPanel();
        panel41.setLayout(new GridLayoutManager(4, 1, new Insets(10, 10, 10, 10), -1, -1));
        CopyDatatabbedPane.addTab(this.$$$getMessageFromBundle$$$("translations/program_strings", "cd.copyinimage"), panel41);
        copyAllMetdataToXMPRadioButton = new JRadioButton();
        copyAllMetdataToXMPRadioButton.setSelected(false);
        this.$$$loadButtonText$$$(copyAllMetdataToXMPRadioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "copyd.copyallmetadatatoxmpformat"));
        panel41.add(copyAllMetdataToXMPRadioButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final JPanel panel42 = new JPanel();
        panel42.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel41.add(panel42, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 1, false));
        panel42.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        CopyArgsRadioButton = new JRadioButton();
        CopyArgsRadioButton.setSelected(true);
        this.$$$loadButtonText$$$(CopyArgsRadioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "copyd.fromgrouptogroup"));
        panel42.add(CopyArgsRadioButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel43 = new JPanel();
        panel43.setLayout(new GridLayoutManager(6, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel42.add(panel43, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 2, false));
        final JPanel panel44 = new JPanel();
        panel44.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel43.add(panel44, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel44.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        tagsToXmpradioButton = new JRadioButton();
        tagsToXmpradioButton.setSelected(true);
        this.$$$loadButtonText$$$(tagsToXmpradioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "copyd.tagstoxmp"));
        panel44.add(tagsToXmpradioButton);
        final Spacer spacer7 = new Spacer();
        panel44.add(spacer7);
        final Spacer spacer8 = new Spacer();
        panel44.add(spacer8);
        exif2xmpCheckBox = new JCheckBox();
        exif2xmpCheckBox.setText("exif2xmp");
        panel44.add(exif2xmpCheckBox);
        gps2xmpCheckBox = new JCheckBox();
        gps2xmpCheckBox.setText("gps2xmp");
        panel44.add(gps2xmpCheckBox);
        iptc2xmpCheckBox = new JCheckBox();
        iptc2xmpCheckBox.setText("iptc2xmp");
        panel44.add(iptc2xmpCheckBox);
        pdf2xmpCheckBox = new JCheckBox();
        pdf2xmpCheckBox.setText("pdf2xmp");
        panel44.add(pdf2xmpCheckBox);
        final Spacer spacer9 = new Spacer();
        panel43.add(spacer9, new GridConstraints(5, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel45 = new JPanel();
        panel45.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel43.add(panel45, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel45.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        tagsToExifradioButton = new JRadioButton();
        this.$$$loadButtonText$$$(tagsToExifradioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "copyd.tagstoexif"));
        panel45.add(tagsToExifradioButton);
        final Spacer spacer10 = new Spacer();
        panel45.add(spacer10);
        final Spacer spacer11 = new Spacer();
        panel45.add(spacer11);
        iptc2exifCheckBox = new JCheckBox();
        iptc2exifCheckBox.setText("iptc2exif");
        panel45.add(iptc2exifCheckBox);
        xmp2exifCheckBox = new JCheckBox();
        xmp2exifCheckBox.setText("xmp2exif");
        panel45.add(xmp2exifCheckBox);
        final JPanel panel46 = new JPanel();
        panel46.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel43.add(panel46, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel46.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        tagsToIptcradioButton = new JRadioButton();
        this.$$$loadButtonText$$$(tagsToIptcradioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "copyd.tagstoiptc"));
        panel46.add(tagsToIptcradioButton);
        final Spacer spacer12 = new Spacer();
        panel46.add(spacer12);
        final Spacer spacer13 = new Spacer();
        panel46.add(spacer13);
        exif2iptcCheckBox = new JCheckBox();
        exif2iptcCheckBox.setText("exif2iptc");
        panel46.add(exif2iptcCheckBox);
        xmp2iptcCheckBox = new JCheckBox();
        xmp2iptcCheckBox.setText("xmp2iptc");
        panel46.add(xmp2iptcCheckBox);
        final JPanel panel47 = new JPanel();
        panel47.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel43.add(panel47, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel47.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        tagsToGpsradioButton = new JRadioButton();
        this.$$$loadButtonText$$$(tagsToGpsradioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "copyd.tagstogps"));
        panel47.add(tagsToGpsradioButton);
        final Spacer spacer14 = new Spacer();
        panel47.add(spacer14);
        final Spacer spacer15 = new Spacer();
        panel47.add(spacer15);
        xmp2gpsCheckBox = new JCheckBox();
        xmp2gpsCheckBox.setText("xmp2gps");
        panel47.add(xmp2gpsCheckBox);
        final JPanel panel48 = new JPanel();
        panel48.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel43.add(panel48, new GridConstraints(4, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel48.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        tagsToPdfradioButton = new JRadioButton();
        this.$$$loadButtonText$$$(tagsToPdfradioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "copyd.tagstopdf"));
        panel48.add(tagsToPdfradioButton);
        final Spacer spacer16 = new Spacer();
        panel48.add(spacer16);
        final Spacer spacer17 = new Spacer();
        panel48.add(spacer17);
        xmp2pdfCheckBox = new JCheckBox();
        xmp2pdfCheckBox.setText("xmp2pdf");
        panel48.add(xmp2pdfCheckBox);
        CopyInsideImageMakeCopyOfOriginalscheckBox = new JCheckBox();
        this.$$$loadButtonText$$$(CopyInsideImageMakeCopyOfOriginalscheckBox, this.$$$getMessageFromBundle$$$("translations/program_strings", "chkbox.makebackup"));
        panel41.add(CopyInsideImageMakeCopyOfOriginalscheckBox, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel49 = new JPanel();
        panel49.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel41.add(panel49, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        copyInsideSaveDataTo = new JButton();
        this.$$$loadButtonText$$$(copyInsideSaveDataTo, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.saveto"));
        panel49.add(copyInsideSaveDataTo);
        final JButton button1 = new JButton();
        this.$$$loadButtonText$$$(button1, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.help"));
        panel49.add(button1);
        final JPanel panel50 = new JPanel();
        panel50.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        panel50.setPreferredSize(new Dimension(800, 253));
        tabbedPaneRight.addTab(this.$$$getMessageFromBundle$$$("translations/program_strings", "maintab.yourcommands"), panel50);
        final JPanel panel51 = new JPanel();
        panel51.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel50.add(panel51, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        CommandsParameterstextField = new JTextField();
        panel51.add(CommandsParameterstextField, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label92 = new JLabel();
        Font label92Font = this.$$$getFont$$$(null, Font.BOLD, -1, label92.getFont());
        if (label92Font != null) label92.setFont(label92Font);
        this.$$$loadLabelText$$$(label92, this.$$$getMessageFromBundle$$$("translations/program_strings", "yc.parameters"));
        panel51.add(label92, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel52 = new JPanel();
        panel52.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel51.add(panel52, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        CommandsclearParameterSFieldButton = new JButton();
        this.$$$loadButtonText$$$(CommandsclearParameterSFieldButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "yc.btnclrparamfield"));
        panel52.add(CommandsclearParameterSFieldButton);
        CommandsclearOutputFieldButton = new JButton();
        this.$$$loadButtonText$$$(CommandsclearOutputFieldButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "yc.btnclroutput"));
        panel52.add(CommandsclearOutputFieldButton);
        CommandsgoButton = new JButton();
        this.$$$loadButtonText$$$(CommandsgoButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "yc.btngo"));
        panel52.add(CommandsgoButton);
        CommandshelpButton = new JButton();
        this.$$$loadButtonText$$$(CommandshelpButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "button.help"));
        panel52.add(CommandshelpButton);
        final Spacer spacer18 = new Spacer();
        panel52.add(spacer18);
        final JPanel panel53 = new JPanel();
        panel53.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        panel52.add(panel53);
        final Spacer spacer19 = new Spacer();
        panel53.add(spacer19);
        AddCommandFavoritebutton = new JButton();
        this.$$$loadButtonText$$$(AddCommandFavoritebutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "yc.btnaddfav"));
        panel53.add(AddCommandFavoritebutton);
        LoadCommandFavoritebutton = new JButton();
        this.$$$loadButtonText$$$(LoadCommandFavoritebutton, this.$$$getMessageFromBundle$$$("translations/program_strings", "yc.btnloadfav"));
        panel53.add(LoadCommandFavoritebutton);
        final JPanel panel54 = new JPanel();
        panel54.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel51.add(panel54, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label93 = new JLabel();
        this.$$$loadLabelText$$$(label93, this.$$$getMessageFromBundle$$$("translations/program_strings", "yc.labeloutput"));
        label93.setVerticalTextPosition(1);
        panel54.add(label93, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel54.add(scrollPane1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        YourCommandsOutputText = new JEditorPane();
        YourCommandsOutputText.setText("");
        scrollPane1.setViewportView(YourCommandsOutputText);
        final JPanel panel55 = new JPanel();
        panel55.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));
        panel54.add(panel55, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        UseNonPropFontradioButton = new JRadioButton();
        UseNonPropFontradioButton.setSelected(true);
        this.$$$loadButtonText$$$(UseNonPropFontradioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "yc.radbtnmonospace"));
        panel55.add(UseNonPropFontradioButton);
        UsePropFontradioButton = new JRadioButton();
        this.$$$loadButtonText$$$(UsePropFontradioButton, this.$$$getMessageFromBundle$$$("translations/program_strings", "yc.radbtnproportional"));
        panel55.add(UsePropFontradioButton);
        MyCommandsText = new JLabel();
        MyCommandsText.setText("MyCommandsText");
        panel50.add(MyCommandsText, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel56 = new JPanel();
        panel56.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        rootPanel.add(panel56, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        JLabelDropReady = new JLabel();
        JLabelDropReady.setText("");
        JLabelDropReady.setVisible(false);
        panel56.add(JLabelDropReady);
        OutputLabel = new JLabel();
        OutputLabel.setText("");
        panel56.add(OutputLabel);
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setPreferredSize(new Dimension(100, 15));
        progressBar.setStringPainted(false);
        panel56.add(progressBar);
        final JPanel panel57 = new JPanel();
        panel57.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
        rootPanel.add(panel57, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 2, false));
        final JLabel label94 = new JLabel();
        this.$$$loadLabelText$$$(label94, this.$$$getMessageFromBundle$$$("translations/program_strings", "pt.filesloaded"));
        panel57.add(label94);
        lblLoadedFiles = new JLabel();
        lblLoadedFiles.setText("");
        panel57.add(lblLoadedFiles);
        lefttabbedPane = new JTabbedPane();
        rootPanel.add(lefttabbedPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(320, -1), new Dimension(450, -1), null, 0, false));
        final JPanel panel58 = new JPanel();
        panel58.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        lefttabbedPane.addTab("Preview mode", panel58);
        LeftPanel = new JPanel();
        LeftPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel58.add(LeftPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(320, -1), new Dimension(450, -1), null, 0, false));
        LeftbuttonBar = new JPanel();
        LeftbuttonBar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        LeftPanel.add(LeftbuttonBar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonLoadDirectory = new JButton();
        buttonLoadDirectory.setIcon(new ImageIcon(getClass().getResource("/icons/outline_folder_black_24.png")));
        buttonLoadDirectory.setIconTextGap(2);
        buttonLoadDirectory.setPreferredSize(new Dimension(26, 26));
        buttonLoadDirectory.setText("");
        buttonLoadDirectory.setToolTipText(this.$$$getMessageFromBundle$$$("translations/program_strings", "btn.loaddirectory"));
        LeftbuttonBar.add(buttonLoadDirectory);
        buttonLoadImages = new JButton();
        buttonLoadImages.setIcon(new ImageIcon(getClass().getResource("/icons/outline_collections_black_24dp.png")));
        buttonLoadImages.setIconTextGap(2);
        buttonLoadImages.setMaximumSize(new Dimension(26, 26));
        buttonLoadImages.setMinimumSize(new Dimension(26, 26));
        buttonLoadImages.setPreferredSize(new Dimension(26, 26));
        buttonLoadImages.setText("");
        buttonLoadImages.setToolTipText(this.$$$getMessageFromBundle$$$("translations/program_strings", "btn.loadimages"));
        LeftbuttonBar.add(buttonLoadImages);
        buttonShowImage = new JButton();
        buttonShowImage.setEnabled(false);
        buttonShowImage.setIcon(new ImageIcon(getClass().getResource("/icons/outline_open_in_new_black_24dp.png")));
        buttonShowImage.setIconTextGap(2);
        buttonShowImage.setMaximumSize(new Dimension(26, 26));
        buttonShowImage.setMinimumSize(new Dimension(26, 26));
        buttonShowImage.setPreferredSize(new Dimension(26, 26));
        buttonShowImage.setText("");
        buttonShowImage.setToolTipText(this.$$$getMessageFromBundle$$$("translations/program_strings", "btn.displayimages"));
        buttonShowImage.putClientProperty("hideActionText", Boolean.FALSE);
        LeftbuttonBar.add(buttonShowImage);
        buttonCompare = new JButton();
        buttonCompare.setEnabled(false);
        buttonCompare.setIcon(new ImageIcon(getClass().getResource("/icons/outline_compare_arrows_black_24dp.png")));
        buttonCompare.setMaximumSize(new Dimension(26, 26));
        buttonCompare.setMinimumSize(new Dimension(26, 26));
        buttonCompare.setPreferredSize(new Dimension(26, 26));
        buttonCompare.setText("");
        buttonCompare.setToolTipText(this.$$$getMessageFromBundle$$$("translations/program_strings", "btn.compareimgs"));
        LeftbuttonBar.add(buttonCompare);
        buttonSlideShow = new JButton();
        buttonSlideShow.setEnabled(false);
        buttonSlideShow.setIcon(new ImageIcon(getClass().getResource("/icons/outline_slideshow_black_24dp.png")));
        buttonSlideShow.setMaximumSize(new Dimension(26, 26));
        buttonSlideShow.setMinimumSize(new Dimension(26, 26));
        buttonSlideShow.setPreferredSize(new Dimension(26, 26));
        buttonSlideShow.setText("");
        buttonSlideShow.setToolTipText(this.$$$getMessageFromBundle$$$("translations/program_strings", "btn.slideshow"));
        LeftbuttonBar.add(buttonSlideShow);
        Leftscrollpane = new JScrollPane();
        LeftPanel.add(Leftscrollpane, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableListfiles = new JTable();
        tableListfiles.setAutoResizeMode(0);
        tableListfiles.setPreferredScrollableViewportSize(new Dimension(-1, -1));
        tableListfiles.setShowHorizontalLines(true);
        tableListfiles.setShowVerticalLines(false);
        tableListfiles.setToolTipText(this.$$$getMessageFromBundle$$$("translations/program_strings", "lp.tooltip"));
        Leftscrollpane.setViewportView(tableListfiles);
        final JPanel panel59 = new JPanel();
        panel59.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        lefttabbedPane.addTab("FileTree", panel59);
        final JScrollPane scrollPane2 = new JScrollPane();
        panel59.add(scrollPane2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        fileTree = new JTree();
        scrollPane2.setViewportView(fileTree);
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(CalcNorthRadioButton);
        buttonGroup.add(CalcSouthRadioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(CalcEastradioButton);
        buttonGroup.add(CalcWestRadioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(copyAllMetadataSameGroupsRadiobutton);
        buttonGroup.add(copySelectiveMetadataradioButton);
        buttonGroup.add(copyAllMetadataRadiobutton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(UseNonPropFontradioButton);
        buttonGroup.add(UsePropFontradioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonViewAll);
        buttonGroup.add(radioButtoncommonTags);
        buttonGroup.add(radioButtonByTagName);
        buttonGroup.add(radioButtonCameraMakes);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(StringsKeywOverwriteradioButton);
        buttonGroup.add(StringsKeywAppendradioButton);
        buttonGroup.add(StringsKeywRemoveradioButton);
        buttonGroup.add(StringsKeywDontSaveradioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(StringsSubjectOverwriteradioButton);
        buttonGroup.add(StringsSubjectAppendradioButton);
        buttonGroup.add(StringsSubjectRemoveradioButton);
        buttonGroup.add(StringsSubjectDontSaveradioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(StringsIIPOverwriteradioButton);
        buttonGroup.add(StringsIIPAppendradioButton);
        buttonGroup.add(StringsIIPRemoveradioButton);
        buttonGroup.add(StringsIIPDontSaveradioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(copyAllMetdataToXMPRadioButton);
        buttonGroup.add(CopyArgsRadioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(tagsToExifradioButton);
        buttonGroup.add(tagsToXmpradioButton);
        buttonGroup.add(tagsToIptcradioButton);
        buttonGroup.add(tagsToGpsradioButton);
        buttonGroup.add(tagsToPdfradioButton);
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

    private static Method $$$cachedGetBundleMethod$$$ = null;

    private String $$$getMessageFromBundle$$$(String path, String key) {
        ResourceBundle bundle;
        try {
            Class<?> thisClass = this.getClass();
            if ($$$cachedGetBundleMethod$$$ == null) {
                Class<?> dynamicBundleClass = thisClass.getClassLoader().loadClass("com.intellij.DynamicBundle");
                $$$cachedGetBundleMethod$$$ = dynamicBundleClass.getMethod("getBundle", String.class, Class.class);
            }
            bundle = (ResourceBundle) $$$cachedGetBundleMethod$$$.invoke(null, path, thisClass);
        } catch (Exception e) {
            bundle = ResourceBundle.getBundle(path);
        }
        return bundle.getString(key);
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadLabelText$$$(JLabel component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setDisplayedMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadButtonText$$$(AbstractButton component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    // endregion

    // region Action Listeners and radio button groups
    /*
    / The SpecialMenuActionListener is a menu linstener for the special function loadImages that is so tightly integrated
    / with the Gui that we can't take it out.
     */
    public class SpecialMenuActionListener implements ActionListener {

        public void actionPerformed(ActionEvent ev) {
            String[] dummy = null;
            logger.info("Selected: {}", ev.getActionCommand());

            switch (ev.getActionCommand()) {
                case "Load Images":
                    logger.debug("menu File -> Load Images pressed");
                    // identical to button "Load Images"
                    loadImages("images");
                    break;
                case "Load Directory":
                    logger.debug("menu File -> Load Folder pressed");
                    // identical to button "Load Directory"
                    loadImages("folder");
                    break;
                default:
                    break;
            }
        }
    }


    /*
    / the programButtonListeners functions brings all buttons from the main screen together in one method.
    / Where possible, the actionlistener will be put externally to the GuiActionListeners class.
    / Only the buttons that are too "intimately" coupled to the Gui and can't be moved, will be dealt with
    / directly in this method. Unfortunately that are quite some buttons.
     */
    private void programButtonListeners() {

        ButtonsActionListener gal = new ButtonsActionListener(rootPanel, OutputLabel, CommandsParameterstextField, geotaggingImgFoldertextField, geotaggingGPSLogtextField, UserCombiscomboBox);
        selectedIndicesList = MyVariables.getselectedIndicesList();

        // Main screen left panel
        buttonLoadImages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                logger.debug("button buttonLoadImages pressed");
                //File opener: Load the images; identical to Menu option Load Images.
                loadImages("images");
            }
        });
        buttonLoadDirectory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                logger.debug("button buttonLoadFolder pressed");
                //File opener: Load folder with images; identical to Menu option Load Directory.
                loadImages("folder");
            }
        });
        buttonShowImage.setActionCommand("bSI");
        buttonShowImage.addActionListener(gal);

        // Your Commands pane buttons
        CommandsclearParameterSFieldButton.setActionCommand("CommandsclearPSFB");
        CommandsclearParameterSFieldButton.addActionListener(gal);
        CommandsclearOutputFieldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                YourCommandsOutputText.setText("");
            }
        });
        CommandsgoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (selectedIndicesList.size() > 0) {
                    if (CommandsParameterstextField.getText().length() > 0) {
                        OutputLabel.setText(ResourceBundle.getBundle("translations/program_strings").getString("pt.yourcommands"));
                        YourCmnds.executeCommands(CommandsParameterstextField.getText(), YourCommandsOutputText, UseNonPropFontradioButton, progressBar);
                        OutputLabel.setText(ResourceBundle.getBundle("translations/program_strings").getString("pt.yourcommandsoutput"));
                    } else {
                        JOptionPane.showMessageDialog(rootPanel, ResourceBundle.getBundle("translations/program_strings").getString("msd.nocommandparams"), ResourceBundle.getBundle("translations/program_strings").getString("msd.nocommandparams"), JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPanel, String.format(ProgramTexts.HTML, 200, ResourceBundle.getBundle("translations/program_strings").getString("msd.noimgslong")), ResourceBundle.getBundle("translations/program_strings").getString("msd.noimgs"), JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        CommandshelpButton.setActionCommand("CommandshB");
        CommandshelpButton.addActionListener(gal);
        AddCommandFavoritebutton.setActionCommand("ACommFavorb");
        AddCommandFavoritebutton.addActionListener(gal);
        LoadCommandFavoritebutton.setActionCommand("LCommFavb");
        LoadCommandFavoritebutton.addActionListener(gal);


        // Edit Exif pane buttons
        ExifcopyFromButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EEd.copyExifFromSelected(getExifFields(), ExifDescriptiontextArea);
            }
        });
        ExifsaveToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (selectedIndicesList.size() > 0) {
                    EEd.writeExifTags(getExifFields(), ExifDescriptiontextArea, getExifBoxes(), progressBar);
                } else {
                    JOptionPane.showMessageDialog(rootPanel, String.format(ProgramTexts.HTML, 200, ResourceBundle.getBundle("translations/program_strings").getString("msd.noimgslong")), ResourceBundle.getBundle("translations/program_strings").getString("msd.noimgs"), JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        ExifcopyDefaultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setArtistCreditsCopyrightDefaults();
            }
        });
        resetFieldsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EEd.resetFields(getExifFields(), ExifDescriptiontextArea);
            }
        });
        ExifhelpButton.setActionCommand("ExifhB");
        ExifhelpButton.addActionListener(gal);


        // Edit xmp buttons
        xmpCopyFrombutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EXd.copyXmpFromSelected(getXmpFields(), xmpDescriptiontextArea);
            }
        });
        xmpSaveTobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (selectedIndicesList.size() > 0) {
                    EXd.writeXmpTags(getXmpFields(), xmpDescriptiontextArea, getXmpBoxes(), progressBar);
                } else {
                    JOptionPane.showMessageDialog(rootPanel, String.format(ProgramTexts.HTML, 200, ResourceBundle.getBundle("translations/program_strings").getString("msd.noimgslong")), ResourceBundle.getBundle("translations/program_strings").getString("msd.noimgs"), JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        xmpCopyDefaultsbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setArtistCreditsCopyrightDefaults();
            }
        });
        xmpResetFieldsbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EXd.resetFields(getXmpFields(), xmpDescriptiontextArea);
            }
        });
        xmpHelpbutton.setActionCommand("xmpHB");
        xmpHelpbutton.addActionListener(gal);


        // Edit geotagging buttons
        geotaggingImgFolderbutton.setActionCommand("geoIFb");
        geotaggingImgFolderbutton.addActionListener(gal);
        geotaggingGPSLogbutton.setActionCommand("geoGPSLb");
        geotaggingGPSLogbutton.addActionListener(gal);
        geotaggingWriteInfobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (("".equals(geotaggingImgFoldertextField.getText())) && (selectedIndicesList.size() == 0)) {
                    JOptionPane.showMessageDialog(rootPanel, ResourceBundle.getBundle("translations/program_strings").getString("msd.geonoimgpathlong"), ResourceBundle.getBundle("translations/program_strings").getString("msd.geonoimgpath"), JOptionPane.WARNING_MESSAGE);
                } else {
                    if ("".equals(geotaggingGPSLogtextField.getText())) {
                        JOptionPane.showMessageDialog(rootPanel, ResourceBundle.getBundle("translations/program_strings").getString("msd.geonolog"), ResourceBundle.getBundle("translations/program_strings").getString("msd.geonolog"), JOptionPane.WARNING_MESSAGE);
                    } else {
                        if ((selectedIndicesList.size() == 0)) { //we have no images selected but the folder is selected
                            EGd.writeInfo(false, getGeotaggingFields(), getGeotaggingBoxes(), geotaggingOverwriteOriginalscheckBox.isSelected(), progressBar);
                        } else {
                            EGd.writeInfo(true, getGeotaggingFields(), getGeotaggingBoxes(), geotaggingOverwriteOriginalscheckBox.isSelected(), progressBar);
                        }

                    }
                }
                OutputLabel.setText("");
            }
        });
        resetGeotaggingbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EGd.ResetFields(getGeotaggingFields(), getGeotaggingBoxes());
            }
        });
        geotaggingHelpbutton.setActionCommand("geotHb");
        geotaggingHelpbutton.addActionListener(gal);

        // Edit gps buttons
        gpsCopyFrombutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EGPSd.copyGPSFromSelected(getNumGPSFields(), getGPSLocationFields(), getGpsBoxes());
            }
        });
        gpsSaveTobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (selectedIndicesList.size() > 0) {
                    EGPSd.writeGPSTags(getNumGPSFields(), getGPSLocationFields(), getGpsBoxes(), progressBar);
                } else {
                    JOptionPane.showMessageDialog(rootPanel, String.format(ProgramTexts.HTML, 200, ResourceBundle.getBundle("translations/program_strings").getString("msd.noimgslong")), ResourceBundle.getBundle("translations/program_strings").getString("msd.noimgs"), JOptionPane.WARNING_MESSAGE);
                }
            }

        });
        gpsResetFieldsbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EGPSd.resetFields(getNumGPSFields(), getGPSLocationFields());
            }
        });
        gpsMapcoordinatesbutton.setActionCommand("gpsMcb");
        gpsMapcoordinatesbutton.addActionListener(gal);
        gpsHelpbutton.addActionListener(gal);
        gpsHelpbutton.setActionCommand("gpsHb");

        decimalToMinutesSecondsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // We will not use this one

            }
        });
        minutesSecondsToDecimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Here is where we calculate the degrees-minutes-seconds to decimal values
                String[] calc_lat_lon = new String[2];
                String[] input_lat_lon = new String[8];

                for (JFormattedTextField calcfield : getGPScalcFields()) {
                    if (calcfield.getText().isEmpty()) {
                        calcfield.setText("0");
                    }
                }
                input_lat_lon[0] = CalcLatDegtextField.getText();
                input_lat_lon[1] = CalcLatMintextField.getText();
                input_lat_lon[2] = CalcLatSectextField.getText();
                if (CalcNorthRadioButton.isSelected()) {
                    input_lat_lon[3] = "N";
                } else {
                    input_lat_lon[3] = "S";
                }
                input_lat_lon[4] = CalcLonDegtextField.getText();
                input_lat_lon[5] = CalcLonMintextField.getText();
                input_lat_lon[6] = CalcLonSectextField.getText();
                if (CalcEastradioButton.isSelected()) {
                    input_lat_lon[7] = "E";
                } else {
                    input_lat_lon[7] = "W";
                }

                calc_lat_lon = Utils.gpsCalculator(rootPanel, input_lat_lon);
                //Utils.gpsCalculator(CalcLatDegtextField.getText(), CalcLatMintextField.getText(), CalcLatSectextField.getText(), CalcLonDegtextField.getText(), CalcLonDegtextField.getText(), CalcLonMintextField.getText(), CalcLonSectextField.getText());
                if (!"error".equals(calc_lat_lon[0])) {
                    CalcLatDecimaltextLabel.setText(calc_lat_lon[0]);
                    CalcLonDecimaltextLabel.setText(calc_lat_lon[1]);
                }
            }
        });
        copyToInputFieldsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // This will copy the calculated values to the lat/lon input fields
                gpsLatDecimaltextField.setText( CalcLatDecimaltextLabel.getText() );
                gpsLonDecimaltextField.setText( CalcLonDecimaltextLabel.getText() );
            }
        });

        // Copy metadata buttons from first copy data tab
        copyAllMetadataRadiobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Utils.setCopyMetaDatacheckboxes(false, getCopyMetaDatacheckboxes());
            }
        });
        copyAllMetadataSameGroupsRadiobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Utils.setCopyMetaDatacheckboxes(false, getCopyMetaDatacheckboxes());
            }
        });
        copySelectiveMetadataradioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Utils.setCopyMetaDatacheckboxes(true, getCopyMetaDatacheckboxes());
            }
        });
        UseDataFrombutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SelectedCopyFromImageIndex = SelectedRow;
            }
        });
        CopyDataCopyTobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //metaData.copyMetaData(getCopyMetaDataRadiobuttons(), getCopyMetaDataCheckBoxes(), SelectedCopyFromImageIndex, selectedIndices, files, progressBar);
                metaData.copyMetaData(rootPanel, getCopyMetaDataRadiobuttons(), getCopyMetaDataCheckBoxes(), SelectedCopyFromImageIndex, progressBar);
            }
        });
        CopyHelpbutton.setActionCommand("CopyHb");
        CopyHelpbutton.addActionListener(gal);
        //Copydata 2nd tab, copy inside image
        copyAllMetdataToXMPRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Utils.setInsideCopyCheckboxesRadiobuttons(false, getInsideImageSubCopyRadiobuttons(), getInsideImageCopyCheckboxes());
            }
        });
        CopyArgsRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Utils.setInsideCopyCheckboxesRadiobuttons(true, getInsideImageSubCopyRadiobuttons(), getInsideImageCopyCheckboxes());
            }
        });
        copyInsideSaveDataTo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (selectedIndicesList.size() > 0) {
                    metaData.copyInsideMetaData(rootPanel, getInsideImageCopyRadiobuttons(), getInsideImageSubCopyRadiobuttons(), getInsideImageCopyCheckboxes(), OutputLabel);
                    OutputLabel.setText("");
                } else {
                    JOptionPane.showMessageDialog(rootPanel, String.format(ProgramTexts.HTML, 200, ResourceBundle.getBundle("translations/program_strings").getString("msd.noimgslong")), ResourceBundle.getBundle("translations/program_strings").getString("msd.noimgs"), JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        // The buttons from the Gpano edit tab
        gpanoCopyFrombutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EGpanod.copyGpanoFromSelected(getGpanoFields(), gpanoStitchingSoftwaretextField, gpanoPTcomboBox, getGpanoCheckBoxes());
                JOptionPane.showMessageDialog(rootPanel, ProgramTexts.GpanoSetSaveCheckboxes, "Set Save checkboxes", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        gpanoCopyTobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (selectedIndicesList.size() > 0) {
                    // Now we need to check on completeness of mandatory fields
                    boolean allFieldsFilled = EGpanod.checkFieldsOnNotBeingEmpty(getGpanoFields(), gpanoPTcomboBox);
                    if (allFieldsFilled) {
                        EGpanod.writeGpanoTags(getGpanoFields(), getGpanoCheckBoxes(), gpanoStitchingSoftwaretextField, gpanoPTcomboBox, progressBar);
                    } else {
                        JOptionPane.showMessageDialog(rootPanel, ProgramTexts.NotAllMandatoryFields, "Not all manadatory fields complete", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPanel, String.format(ProgramTexts.HTML, 200, ResourceBundle.getBundle("translations/program_strings").getString("msd.noimgslong")), ResourceBundle.getBundle("translations/program_strings").getString("msd.noimgs"), JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        gpanoResetFieldsbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EGpanod.resetFields(getGpanoFields(), gpanoStitchingSoftwaretextField, getGpanoCheckBoxes());
            }
        });
        gpanoHelpbutton.setActionCommand("gpanoHb");
        gpanoHelpbutton.addActionListener(gal);

        // The buttons from the lens tab
        lensCopyFrombutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (selectedIndicesList.size() > 0) {
                    ELd.copyLensDataFromSelected(getLensFields(), meteringmodecomboBox, getLensCheckBoxes());
                } else {
                    JOptionPane.showMessageDialog(rootPanel, String.format(ProgramTexts.HTML, 200, ResourceBundle.getBundle("translations/program_strings").getString("msd.noimgslong")), ResourceBundle.getBundle("translations/program_strings").getString("msd.noimgs"), JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        lensSaveTobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (selectedIndicesList.size() > 0) {
                    ELd.writeLensTags(getLensFields(), getLensCheckBoxes(), meteringmodecomboBox, progressBar);
                } else {
                    JOptionPane.showMessageDialog(rootPanel, String.format(ProgramTexts.HTML, 200, ResourceBundle.getBundle("translations/program_strings").getString("msd.noimgslong")), ResourceBundle.getBundle("translations/program_strings").getString("msd.noimgs"), JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        lensResetFieldsbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ELd.resetFields(getLensFields(), getLensCheckBoxes(), meteringmodecomboBox);
            }
        });
        lensHelpbutton.setActionCommand("lensHb");
        lensHelpbutton.addActionListener(gal);

        saveLensConfigurationbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ELd.saveLensconfig(getLensFields(), meteringmodecomboBox, rootPanel);
            }
        });
        loadLensConfigurationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ELd.loadLensconfig(getLensFields(), meteringmodecomboBox, rootPanel);
            }
        });

        // The buttons from the XMP_IPTC_String+ tab
        stringPlusCopyFrombutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ESd.copyStringPlusFromSelected(getstringPlusFields(), getstringPlusBoxes());
            }
        });
        stringPlusSaveTobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ESd.writeStringPlusTags(getstringPlusFields(), getstringPlusBoxes(), getSelectedRadioButtons(), progressBar);
            }
        });
        stringPlusResetFieldsbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ESd.resetFields(getstringPlusFields(), getstringPlusBoxes() );
            }
        });
        stringPlusHelpbutton.setActionCommand("sPHb");
        stringPlusHelpbutton.addActionListener(gal);

        // Button listeners for the User defined metadata combinations tab
        udcCreateNewButton.setActionCommand("udcCNB");
        udcCreateNewButton.addActionListener(gal);
        UserCombiscomboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EUDC.UpdateTable(rootPanel, UserCombiscomboBox, UserCombiScrollPane);
                EUDC.UpdateCustomConfigLabel(UserCombiscomboBox, CustomConfiglabel);
            }
        });
        udcCopyFrombutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EUDC.UpdateTable(rootPanel, UserCombiscomboBox, UserCombiScrollPane);
                EUDC.CopyFromSelectedImage();
            }
        });
        udcSaveTobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (selectedIndicesList.size() > 0) {
                    EUDC.SaveTableValues(udcOverwriteOriginalscheckBox, progressBar);
                } else {
                    JOptionPane.showMessageDialog(rootPanel, String.format(ProgramTexts.HTML, 200, ResourceBundle.getBundle("translations/program_strings").getString("msd.noimgslong")), ResourceBundle.getBundle("translations/program_strings").getString("msd.noimgs"), JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        udcResetFieldsbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Defined here for program consistency but not used
                // as the user can simply "reset" from the dropdown.
            }
        });
        udcHelpbutton.setActionCommand("udcHb");
        udcHelpbutton.addActionListener(gal);
    }

    private void ViewRadiobuttonListener() {

        //Add listeners
        radioButtonViewAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.trace("radiobutton selected: {}", radioButtonViewAll.getText());
                Utils.getImageInfoFromSelectedFile(MyConstants.ALL_PARAMS, files, mainScreen.this.ListexiftoolInfotable);
            }
        });
        radioButtoncommonTags.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String[] params = Utils.getWhichCommonTagSelected(comboBoxViewCommonTags);
                //Utils.selectImageInfoByTagName(comboBoxViewCommonTags, SelectedRow, files, mainScreen.this.ListexiftoolInfotable);
                Utils.getImageInfoFromSelectedFile(params, files, mainScreen.this.ListexiftoolInfotable);
            }
        });
        comboBoxViewCommonTags.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (radioButtoncommonTags.isSelected()) {
                    String[] params = Utils.getWhichCommonTagSelected(comboBoxViewCommonTags);
                    //Utils.selectImageInfoByTagName(comboBoxViewCommonTags, SelectedRow, files, mainScreen.this.ListexiftoolInfotable);
                    Utils.getImageInfoFromSelectedFile(params, files, mainScreen.this.ListexiftoolInfotable);
                }
            }
        });
        radioButtonByTagName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utils.selectImageInfoByTagName(comboBoxViewByTagName, files, mainScreen.this.ListexiftoolInfotable);
            }
        });
        comboBoxViewByTagName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (radioButtonByTagName.isSelected()) {
                    Utils.selectImageInfoByTagName(comboBoxViewByTagName, files, mainScreen.this.ListexiftoolInfotable);
                }
            }
        });
        radioButtonCameraMakes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Utils.selectImageInfoByTagName(comboBoxViewCameraMake, files, mainScreen.this.ListexiftoolInfotable);
            }
        });
        comboBoxViewCameraMake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (radioButtonCameraMakes.isSelected()) {
                    Utils.selectImageInfoByTagName(comboBoxViewCameraMake, files, mainScreen.this.ListexiftoolInfotable);
                }
            }
        });
    }



    private String[] whichRBselected() {
        // just to make sure anything is returned we defaultly return all
        String[] params = MyConstants.ALL_PARAMS;
        // Very simple if list
        if (mainScreen.this.radioButtonViewAll.isSelected()) {
            params = MyConstants.ALL_PARAMS;
        } else if (radioButtoncommonTags.isSelected()) {
            params = Utils.getWhichCommonTagSelected(comboBoxViewCommonTags);
        } else if (radioButtonByTagName.isSelected()) {
            params = Utils.getWhichTagSelected(comboBoxViewByTagName);
        } else if (radioButtonCameraMakes.isSelected()) {
            params = Utils.getWhichTagSelected(comboBoxViewCameraMake);
        }
        return params;
    }


    // This is the general table listener that also enables multi row selection
    class SharedListSelectionHandler implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            // Perfectly working row selection method of first program
            List<Integer> tmpselectedIndices = new ArrayList<>();
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();

            if (lsm.isSelectionEmpty()) {
                logger.debug("no index selected");
            } else {
                // Find out which indexes are selected.
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                for (int i = minIndex; i <= maxIndex; i++) {
                    if (lsm.isSelectedIndex(i)) {
                        tmpselectedIndices.add(i);
                        SelectedRow = i;
                        MyVariables.setSelectedRow(i);
                    }
                }
                String[] params = whichRBselected();
                Utils.getImageInfoFromSelectedFile(params, files, ListexiftoolInfotable);

                selectedIndices = tmpselectedIndices.stream().mapToInt(Integer::intValue).toArray();
                logger.trace("Selected indices: {}", tmpselectedIndices);
                selectedIndicesList = tmpselectedIndices;
                MyVariables.setselectedIndicesList(selectedIndicesList);
                MyVariables.setSelectedFilenamesIndices(selectedIndices);
            }

        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    public void rootPanelDropListener() {
        //Listen to drop events

        rootPanel.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>)
                            evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    for (File file : droppedFiles) {
                        logger.debug("File path is: {}", file.getPath());
                    }
                    File[] droppedFilesArray = (File[]) droppedFiles.toArray(new File[droppedFiles.size()]);
                    MyVariables.setSelectedFiles(droppedFilesArray);
                    loadImages("dropped files");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    logger.error("Drag drop on rootpanel error {}", ex);
                }
            }
        });
    }


    // endregion

    /*
    / This creates the menu with the listener in its own external MenuActionListener class
     */
    private void createMenuBar(JFrame frame) {
        menuBar = new JMenuBar();


        // Due to the Load Iamge method with its own ActionListener we do the first menu items here
        // File menu
        myMenu = new JMenu(ResourceBundle.getBundle("translations/program_strings").getString("menu.file"));
        myMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(myMenu);
        menuItem = new JMenuItem(ResourceBundle.getBundle("translations/program_strings").getString("fmenu.loadimages"));
        myMenu.setMnemonic(KeyEvent.VK_L);
        menuItem.setActionCommand("Load Images");
        //menuItem.addActionListener(mal);
        menuItem.addActionListener(new SpecialMenuActionListener());
        myMenu.add(menuItem);
        menuItem = new JMenuItem(ResourceBundle.getBundle("translations/program_strings").getString("fmenu.loaddirectory"));
        menuItem.setActionCommand("Load Directory");
        myMenu.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener(new SpecialMenuActionListener());
        myMenu.add(menuItem);

        // Now we continue with the rest of the entire menu outside the mainScreen in the external CreateMenu class
        CreateMenu crMenu = new CreateMenu();
        crMenu.CreateMenuBar(frame, rootPanel, menuBar, myMenu, OutputLabel, progressBar, UserCombiscomboBox);
    }


    // Sets the necessary screen texts. We choose this way as we have now more control over width
    // without bothering the translators with <html></html> and/or <br> codes or length of total string(s).
    private void setProgramScreenTexts() {
        String version = "";
        MyCommandsText.setText(String.format(ProgramTexts.HTML, 600,ResourceBundle.getBundle("translations/program_strings").getString("yc.toptext")));
        xmpTopText.setText(String.format(ProgramTexts.HTML, 600,ResourceBundle.getBundle("translations/program_strings").getString("xmp.toptext")));
        GeotaggingLeaveFolderEmptyLabel.setText(String.format(ProgramTexts.HTML, 600, ResourceBundle.getBundle("translations/program_strings").getString("geo.folderexplanation")));
        GeotaggingLocationLabel.setText(String.format(ProgramTexts.HTML, 600, ResourceBundle.getBundle("translations/program_strings").getString("geo.geotagexpl")));
        GeotaggingGeosyncExplainLabel.setText(String.format(ProgramTexts.HTML, 600, ResourceBundle.getBundle("translations/program_strings").getString("geo.geosyncexpl")));
        gpsCalculatorLabelText.setText(String.format(ProgramTexts.HTML, 200, ResourceBundle.getBundle("translations/program_strings").getString("gps.calculatortext")));
        gPanoTopText.setText(String.format(ProgramTexts.HTML, 600, ResourceBundle.getBundle("translations/program_strings").getString("gpano.toptext")));
        copyAllMetadataRadiobutton.setText(String.format(ProgramTexts.HTML, 600, ResourceBundle.getBundle("translations/program_strings").getString("copyd.copyallmetadatasame")));
        copyAllMetadataSameGroupsRadiobutton.setText(String.format(ProgramTexts.HTML, 600, ResourceBundle.getBundle("translations/program_strings").getString("copyd.copyallsamegroup")));
        copySelectiveMetadataradioButton.setText(String.format(ProgramTexts.HTML, 600, ResourceBundle.getBundle("translations/program_strings").getString("copyd.copybyselection")));
        lensSaveLoadConfigLabel.setText(String.format(ProgramTexts.HTML, 600, ResourceBundle.getBundle("translations/program_strings").getString("lens.saveloadconfig")));
        //exiftoolDBText.setText(String.format(ProgramTexts.HTML, 600, ResourceBundle.getBundle("translations/program_strings").getString("edb.toptext")));
        StringsTopText.setText(String.format(ProgramTexts.HTML, 600, ResourceBundle.getBundle("translations/program_strings").getString("xis.toptext")));
        UserCombiTopText.setText(String.format(ProgramTexts.HTML, 600, ResourceBundle.getBundle("translations/program_strings").getString("udc.toptext")));

        // database version
        //exiftoolDBversion.setText(String.format(ProgramTexts.HTML,100,"exiftool DB version:<br>" + SQLiteJDBC.getDBversion()));
        // Special dynamic version string
        logger.trace("check for exiftool version");
        String exiftool = Utils.platformExiftool();
        List<String> cmdparams = new ArrayList<>();
        Application.OS_NAMES currentOsName = Utils.getCurrentOsName();
        logger.info("OS name {}", currentOsName);

        cmdparams.add(exiftool.trim());
        cmdparams.add("-ver");

        try {
            logger.trace("Get exiftool version");
            version = (CommandRunner.runCommand(cmdparams));
            logger.trace("raw exiftool version: {}", version);
        } catch (IOException | InterruptedException ex) {
            logger.debug("Error executing command");
            version = "I cannot determine the exiftool version";
        }
        Float floatversion = Float.parseFloat(version.trim());
        Float minversion = new Float("9.09");
        int retval = floatversion.compareTo(minversion);
        logger.trace("returned version for exiftool (>0: OK; <=0: No gpano): {}", retval);
        if (retval >0) { // cureent exiftool > 9.09
            gpanoMinVersionText.setText(String.format(ProgramTexts.HTML, 600, ResourceBundle.getBundle("translations/program_strings").getString("gpano.googlemapsfields")));
        } else {
            gpanoMinVersionText.setText(String.format(ProgramTexts.HTML, 600, ResourceBundle.getBundle("translations/program_strings").getString("gpano.disabledfields")));
            gpanoPHDtextField.setEnabled(false);
            gpanoStitchingSoftwaretextField.setEnabled(false);
            gpanoIVHDtextField.setEnabled(false);
            gpanoIVPDtextField.setEnabled(false);
            gpanoIVRDtextField.setEnabled(false);
            gpanoIHFOVDtextField.setEnabled(false);
        }

    }

    private void setArtistCreditsCopyrightDefaults() {
        // Try to set the defaults for artist, credit and copyrights in the edit exif/xmp panes if prefs available
        String[] ArtCredCopyPrefs = Utils.checkPrefsArtistCreditsCopyRights();
        ExifArtistCreatortextField.setText(ArtCredCopyPrefs[0]);
        ExifCopyrighttextField.setText(ArtCredCopyPrefs[2]);
        xmpCreatortextField.setText(ArtCredCopyPrefs[0]);
        xmpCredittextField.setText(ArtCredCopyPrefs[1]);
        xmpRightstextField.setText(ArtCredCopyPrefs[2]);
    }

    // This is where it all starts
    // initialisation of the Application
    // private mainScreen(JFrame frame) {
    public mainScreen(JFrame frame) throws IOException, InterruptedException {
        boolean preferences = false;

        $$$setupUI$$$();
        Utils.progressStatus(progressBar, false);

        createMenuBar(frame);
        //CreateMenu CM = new CreateMenu();
        //menuBar = new JMenuBar();
        //CM.createMenuBar(frame, rootPanel, menuBar);

        // Check if our custom folder exists and create it
        String check_result = checkforjexiftoolguiFolder();
        if (check_result.contains("Error creating")) {
            JOptionPane.showMessageDialog(rootPanel, "Could not create the data folder " + MyConstants.MY_DATA_FOLDER + "  or one of its files", "error creating folder/files", JOptionPane.ERROR_MESSAGE);
        } else { // Set database to variable
            logger.info("string for DB: " + MyVariables.getjexiftoolguiDBPath());
        }
        // Delete and recreate {tmp dir}/jexiftoolgui
        check_result = StandardFileIO.RecreateOurTempFolder();
        if (!"Success".equals(check_result)) {
            JOptionPane.showMessageDialog(rootPanel, "Could not (re)create our temporary working folder", "error (re)creating temp folder", JOptionPane.ERROR_MESSAGE);
        }
        // Now check the preferences
        CheckPreferences CP = new CheckPreferences();
        preferences = CP.checkPreferences(rootPanel, OutputLabel);
        if (!preferences) {
            ExifTool.checkExifTool(mainScreen.this.rootPanel);
        }

        // Set the text areas correctly
        ExifDescriptiontextArea.setWrapStyleWord(true);
        ExifDescriptiontextArea.setLineWrap(true);
        xmpDescriptiontextArea.setWrapStyleWord(true);
        xmpDescriptiontextArea.setLineWrap(true);

        // Do necessary updates when moving from older versions to newer versions
        UpdateActions.Updates();

        ViewRadiobuttonListener();
        fillAllComboboxes();


        // Use the mouselistener for the double-click to display the image
        // on both the filetree and the filenamestable
        MouseListeners.fileTreeAndFileNamesTableMouseListener(tableListfiles, ListexiftoolInfotable, fileTree, whichRBselected());
        //Use the table listener for theselection of multiple cells
        listSelectionModel = tableListfiles.getSelectionModel();
        tableListfiles.setRowSelectionAllowed(true);
        tableListfiles.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
        //cellSelectionModel.addListSelectionListener(new SharedListSelectionListener());

        //Listen to drop events
        rootPanelDropListener();

        //File root = new File(System.getProperty("user.home"));
        String defaultStartFolder = prefs.getByKey(DEFAULT_START_FOLDER, "");
        if ("".equals(defaultStartFolder)) {
            defaultStartFolder = System.getProperty("user.home");
        }
        File treeStartFolder = new File(defaultStartFolder);
        FileTreeModel model = new FileTreeModel(treeStartFolder);
        fileTree.setModel(model);
        fileTree.setRootVisible(false);
        fileTree.setShowsRootHandles(false); // will not show the "thingy"  in front of the root folder

        // Make left "tableListfiles" and right "ListexiftoolInfotable" tables read-only (un-editable)
        // This also fixes the double-click bug on the image where it retrieves the object name of the images on double-click
        tableListfiles.setDefaultEditor(Object.class, null);
        ListexiftoolInfotable.setDefaultEditor(Object.class, null);
        // Make all tables read-only unless ....
        //DBResultsTable.setDefaultEditor(Object.class, null);

        // icon for my dialogs
        InputStream stream = StandardFileIO.getResourceAsStream("icons/jexiftoolgui-64.png");
        try {
            icon = new ImageIcon(ImageIO.read(stream));
        } catch (IOException ex) {
            logger.debug("Error executing command");
        }

        setArtistCreditsCopyrightDefaults();


        programButtonListeners();
        // Some texts
        setProgramScreenTexts();
        // Set formatting for the JFormatted textFields
        EGpanod.setFormattedFieldFormats(getGpanoFields());
        // Set jFormattedFields
        EGPSd.setFormattedFieldMasks(getNumGPSFields(), getGPScalcFields());


        Utils.checkForNewVersion("startup");
        //JLabelDropReady.addPropertyChangeListener(new PropertyChangeListener() {
        //});

    }

    static void createAndShowGUI() {

        JFrame frame = new JFrame("jExifToolGUI V" + ProgramTexts.Version + ResourceBundle.getBundle("translations/program_strings").getString("application.title"));
        frame.setIconImage(Utils.getFrameIcon());
        try {
            frame.setContentPane(new mainScreen(frame).rootPanel);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            logger.error("InterruptedException or IOException: {}", e);
        }
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Do not simply exit on closing the window. First delete our temp stuff
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                StandardFileIO.deleteDirectory(new File (MyVariables.gettmpWorkFolder()) );
                System.exit(0);
            }
        });

        // Should work, but doesn't work
        Application.OS_NAMES os = Utils.getCurrentOsName();
        try {
            if (os == Application.OS_NAMES.APPLE) {
                logger.info("running on Apple. set correct menu");
                // take the menu bar off the jframe and put it in the MacOS menu
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                // set the name of the application menu item
                System.setProperty("com.apple.mrj.application.apple.menu.about.name", "jExifToolGUI V" + ProgramTexts.Version + "   (for ExifTool by Phil Harvey)");
            }
            // Significantly improves the look of the output in
            // terms of the folder/file icons and file names returned by FileSystemView!
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            //UIManager.setLookAndFeel(GTKLookAndFeel);
        } catch (Exception weTried) {
           logger.error("Could not start GUI.", weTried);
        }

        if (os == Application.OS_NAMES.APPLE) {
            frame.setSize(1450,850);
        } else {
            frame.pack();
        }

        //frame.setLocationRelativeTo(null);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
