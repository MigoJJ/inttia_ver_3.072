package je.panse.doro.chartplate.keybutton;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import javax.swing.JOptionPane;
import static je.panse.doro.GDSEMR_frame.*;

import je.panse.doro.GDSEMR_frame;
import je.panse.doro.chartplate.filecontrol.*;
import je.panse.doro.chartplate.keybutton.EMR_Backup_Excute.*;
import je.panse.doro.chartplate.mainpage.EMR_Write_To_Chartplate;
import je.panse.doro.entry.EntryDir;
import je.panse.doro.fourgate.A_editmain.*;
import je.panse.doro.fourgate.diabetes.EMR_dm_mainentry;
import je.panse.doro.fourgate.hypertension.EMR_htn_mainentry;
import je.panse.doro.fourgate.influenza.InjectionApp;
import je.panse.doro.fourgate.osteoporosis.buttons.EMR_Os_buttons;
import je.panse.doro.fourgate.routinecheck.RoutineCheck;
import je.panse.doro.fourgate.thyroid.entry.*;
import je.panse.doro.fourgate.thyroid.pregnancy.EMR_thyroid_Pregnancyentry;
import je.panse.doro.soap.pmh.EMRPMHAllergy;
import je.panse.doro.support.*;
import je.panse.doro.support.sqlite3_manager.abbreviation.MainScreen;

public class EMR_ButtonEntry extends GDSEMR_frame {
    
    public EMR_ButtonEntry() throws Exception { super(); }

    public static void EMR_B_1entryentry(String btn, String location) throws Exception {
        if ("north".equals(location)) {
            switch (btn) {
                case "Rescue":
                    EMR_Write_To_Chartplate.copyToClipboard(tempOutputArea);
                    FileGeditToCilpboard.FileGeditToCilpboard();
                    EMR_B_FileListFrame.main(null);
                    break;
                case "Backup":
                    EMR_InputFrame.main(null);
                    new EMR_B_CopyBackup().saveTextToFile(tempOutputArea.getText());
                    EMR_B_FileListFrame.main(null);
                    break;
                case "Copy":
                    try {
                        Toolkit.getDefaultToolkit().getSystemClipboard()
                               .setContents(new StringSelection(tempOutputArea.getText()), null);
                        JOptionPane.showMessageDialog(null, "Text copied!");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Copy failed: " + e.getMessage());
                    }
                    break;
                case "CE": 
                    for (int i = 1; i <= 7; i++) textAreas[i].setText(titles[i] + "\t");
                    break;
                case "Clear":
                    for (int i = 0; i < textAreas.length; i++) textAreas[i].setText(titles[i] + "\t");
                    break;
                case "Exit": System.exit(0); break;
                case "Abbreviation": MainScreen.main(null); break;
                case "Code":
                    String path = EntryDir.homeDir + "/support/EMR_support_Folder/Etc/";
                    File_open.main(path + "Disease_Code.odt");
                    File_open.main(path + "Code.odt");
                    break;
                case "ittia_support": EMR_ittia_support.main(null); break;
                case "ittia_EMR_AI": je.panse.doro.chartplate.keybutton.ai.Mprompt.main(null); break;
            }
        } else if ("south".equals(location)) {
            switch (btn) {
                case "F/U DM":
                    EMR_FU_diabetesEdit.main(null);
                    EMR_dm_mainentry.main(null);
                    break;
                case "F/U HTN":
                    EMR_FU_hypertensionEdit.main(null);
                    EMR_htn_mainentry.main(null);
                    break;
                case "F/U Chol": EMR_FU_hypercholesterolemiaEdit.main(null); break;
                case "F/U Thyroid":
                    EMR_thyroid_mainentry.main(null);
                    EMR_thyroid_Pregnancyentry.main(null);
                    break;
                case "Osteoporosis": EMR_Os_buttons.main(null); break;
                case "URI": EMR_FU_uriEdit.main(null); break;
                case "Allergy": EMRPMHAllergy.main(null); break;
                case "Injections": InjectionApp.main(null); break;
                case "GDS RC": RoutineCheck.performGDSRoutineCheck(); break;
                case "공단검진": RoutineCheck.performHCRoutineCheck(); break;
                case "F/U Edit": EMR_FU_mainEdit.main(null); break;
            }
        }
    }
}
