package je.panse.doro.chartplate;

import java.util.HashMap;
import java.util.Map;

import je.panse.doro.samsara.comm.datetime.Date_current;
import je.panse.doro.soap.cc.EMR_ChangeStringCC;

public class EMR_ChangeString {

    private static final Map<String, String> replacements = new HashMap<>();

    static {
        // Populate the map with replacements
        replacements.put(":d", "diabetes mellitus");
        replacements.put(":c", "Hypercholesterolemia");
		 replacements.put(":call ", "- The patient received a lab results phone call notification from the doctor's office.");
		 replacements.put( ":tt " , "c/w Chronic Thyroiditis on USG ");
		 replacements.put( ":t " , "Hypertension ");
		 replacements.put( ":cd " , "Colonic diverticulum [  ] test ");
		 replacements.put( ":call " , "The patient received a lab results phone call notification from the doctor's office. ");
		 replacements.put( ":dr " , "DM with retinopathy ");
		 replacements.put( ":dn " , "DM with Nephropathy ");
		 replacements.put( ":dnp " , "DM with Peripheral Neuropathy ");
		 replacements.put( ":dna " , "DM with Autonomic Neuropathy ");
		 replacements.put( ":dp " , "Prediabetes ");
		 replacements.put( ":dg " , "Gestational Diabetes Mellitus ");
		 replacements.put( ":fd " , "Diabetes mellitus F/U ");
		 replacements.put( ":ft " , "Hypertension F/U ");
		 replacements.put( ":fc " , "Hypercholesterolemia F/U ");
		 replacements.put( ":fctg " , "HyperTriGlyceridemia F/U ");
		 replacements.put( ":fte " , "Hyperthyroidism F/U ");
		 replacements.put( ":fto " , "Hypothyroidism F/U ");
		 replacements.put( ":fnti " , "Non-Thyroidal Illness F/U ");
		 replacements.put( ":ftep " , "Hyperthyroidism with Pregnancy [ ] weeks F/U ");
		 replacements.put( ":ftop " , "Hypothyroidism with Pregnancy [ ] weeks F/U ");
		 replacements.put( ":do " , "DM without complications ");
		 replacements.put( ":drn " , "DM with Retinopathy : Non-proliferative diabetic retinopathy ");
		 replacements.put( ":drp " , "DM with Retinopathy : Proliferative diabetic retinopathy ");
		 replacements.put( ":drm " , "DM with Retinopathy : Macular edema ");
		 replacements.put( ":dnm " , "DM with Nephropathy with microalbuminuria ");
		 replacements.put( ":dnc " , "DM with Nephropathy with CRF ");
		 replacements.put( ":da " , "DM with Autonomic Neuropathy ");
		 replacements.put( ":pd " , "Prediabetes ");
		 replacements.put( ":pg " , "Gestational Diabetes Mellitus ");
		 replacements.put( ":ctg " , "HyperTriGlyceridemia ");
		 replacements.put( ":te " , "Hyperthyroidism  ");
		 replacements.put( ":to " , "Hypothyroidism  ");
		 replacements.put( ":ts " , "C/W Subacute Thyroiditis ");
		 replacements.put( ":tn " , "Thyroid nodule ");
		 replacements.put( ":tc " , "Thyroid cyst ");
		 replacements.put( ":tsg " , "Simple Goiter ");
		 replacements.put( ":at " , "Atypical Chest pain ");
		 replacements.put( ":ap " , "Angina Pectoris ");
		 replacements.put( ":aps " , "Angina Pectoris with stent insertion ");
		 replacements.put( ":omi " , "R/O Old Myocardial Infarction  ");
		 replacements.put( ":ami " , "Acute Myocardial Infarction ");
		 replacements.put( ":amis " , "Acute Myocardial Infarction with stent insertion ");
		 replacements.put( ":as " , "Artherosclerosis Carotid artery ");
		 replacements.put( ":asa " , "Artherosclerosis Carotid artery and Aorta ");
		 replacements.put( ":af " , "Atrial Fibrillation ");
		 replacements.put( ":afr " , "Atrial Fibrillation with RVR ");
		 replacements.put( ":afl " , "Atrial Flutter ");
		 replacements.put( ":pvc " , "PVC Premature Ventricular Contractions ");
		 replacements.put( ":apc " , "APC atrial premature complexes ");
		 replacements.put( ":gre " , "Reflux esophagitis ");
		 replacements.put( ":gcag " , "Chronic Atrophic Gastritis ");
		 replacements.put( ":gcsg " , "Chronic Superficial Gastritis ");
		 replacements.put( ":geg " , "r/o Erosive Gastritis ");
		 replacements.put( ":gibs " , "r/o Irritable Bowel Syndrome ");
		 replacements.put( ":ggil " , "Gilbert's syndrome ");
		 replacements.put( ":gcon " , "Severe Constipation ");
		 replacements.put( ":nti " , "Non-Thyroidal Illness ");
		 replacements.put( ":tep " , "Hyperthyroidism with Pregnancy [    ] weeks ");
		 replacements.put( ":top " , "Hypothyroidism with Pregnancy [     ] weeks ");
		 replacements.put( ":tco " , "Papillary Thyroid Cancer OP(+) Hypothyroidism ");
		 replacements.put( ":tcor " , "Papillary Thyroid Cancer OP(+) RAI Tx(+) Hypothyroidism ");
		 replacements.put( ":sos " , "Severe Osteoporosis ");
		 replacements.put( ":os " , "Osteoporosis ");
		 replacements.put( ":ospe " , "Osteopenia ");
		 replacements.put( ":cp " , "Colonic Polyp ");
		 replacements.put( ":cpm " , "Colonic Polyps multiple ");
		 replacements.put( ":cps " , "Colonic Polyp single ");
		 replacements.put( ":gp " , "GB polyp ");
		 replacements.put( ":gs " , "GB stone ");
		 replacements.put( ":ggp " , "Gastric Polyp ");
		 replacements.put( ":oc " , "s/p Cholecystectomy d/t GB stone ");
		 replacements.put( ":oa " , "s/p Appendectomy ");
		 replacements.put( ":oco " , "s/p Colon cancer op(+) ");
		 replacements.put( ":ogc " , "s/p Gastric cancer cancer op(+) ");
		 replacements.put( ":oh " , "s/p TAH : Total Abdominal Hysterectomy ");
		 replacements.put( ":oho " , "s/p TAH with BSO ");
		 replacements.put( ":bph " , "BPH ");
		 replacements.put( ":opr " , "Prostate cancer operation(+) ");
		 replacements.put( ":obr " , "s/p Breast Cancer Operation ");
		 replacements.put( ":ot " , "Papillary Thyroid Cancer OP(+)	with Hypothyroidism ");
		 replacements.put( ":oca " , "Cataract OP(+) ");
		 replacements.put( ":hav " , "s/p Hepatitis A infection ");
		 replacements.put( ":hbv " , "HBsAg(+) Carrier ");
		 replacements.put( ":hcv " , "Hepatitis C virus (HCV) chronic infection ");
		 replacements.put( ":hcvp " , "HCV-Ab(Positive) --> PCR(Negative) confirmed ");
		 replacements.put( ":hh " , "Hepatic Hemangioma ");
		 replacements.put( ":hc " , "Hepatic Cyst ");
		 replacements.put( ":hn " , "Hepatic Nodule ");
		 replacements.put( ":hhn " , "Hepatic higher echoic nodule ");
		 replacements.put( ":hf " , "Fatty Liver ");
		 replacements.put( ":hfmi " , "Mild Fatty Liver ");
		 replacements.put( ":hfmo " , "Moderate Fatty Liver ");
		 replacements.put( ":hfse " , "Severe Fatty Liver ");
		 replacements.put( ":rc " , "Renal Cyst ");
		 replacements.put( ":rs " , "Renal Stone ");
		 replacements.put( ":rse " , "Renal Stone s/p ESWL ");
		 replacements.put( ":rn " , "Renal Nodule ");
		 replacements.put( ":rih " , "isolated hematuria ");
		 replacements.put( ":rgh " , "gross hematuria ");
		 replacements.put( ":rip " , "isolated proteinuria ");
		 replacements.put( ":bc " , "Breast Cyst ");
		 replacements.put( ":bn " , "Breast Nodule ");
		 replacements.put( ":bnb " , "Breast Nodule with biopsy ");
		 replacements.put( ":bco " , "s/p Breast Cancer Operation ");
		 replacements.put( ":bcoc " , "s/p Breast Cancer Operation+ ChemoTx(+) ");
		 replacements.put( ":bcor " , "s/p Breast Cancer Operation + RT(+) ");
		 replacements.put( ":bcocr " , "s/p Breast Cancer Operation : ChemoTx(+) + RT(+) ");
		 replacements.put( ":ins " , "Insomnia ");
		 replacements.put( ":epi " , "Epigastric pain ");
		 replacements.put( ":dys " , "Dysuria and frequency ");
		 replacements.put( ":ind " , "Epigastric pain and Indigestion ");
		 replacements.put( ":dia " , "Diarrhea ");
		 replacements.put( ":con " , "Constipation ");
		 replacements.put( ":cov " , "COVID-19 PCR (+) ");
		 replacements.put( ":covc " , "s/p COVID-19 PCR (+) without complications [ ] ");
		 replacements.put( ":covs " , "s/p COVID-19 PCR (+) with complications [ ] ");
		 replacements.put( ":ver " , "Vertigo ");
		 replacements.put( ":hea " , "Headache ");
		 replacements.put( ":wei " , "Weight loss [ ] kg ");
		 replacements.put( ":weig " , "Weight gain [ ] kg ");
		 replacements.put( ":eas " , "Easy fatigue ");
		 replacements.put( ":obe " , "Obesity ");
		 replacements.put( ":obec " , "Central Obesity ");
		 replacements.put( ":gla " , "Glaucoma(+) ");
		 replacements.put( ":cat " , "Cataract(+) ");
		 replacements.put( ":cato " , "Cataract operation (+) [ ] ");
		 replacements.put( ":ida " , "Iron Deficiency Anemia ");
		 replacements.put( ":leu " , "Leukocytopenia ");
		 replacements.put( ":thr " , "Thrombocytopenia ");
		 replacements.put( ":got " , "GOT/GPT/GGT elevation ");
		 replacements.put( ":afp " , "AFP elevation ");
		 replacements.put( ":ca1 " , "CA19-9 elevation ");
		 replacements.put( ":her " , "Herpes Zoster ");
		 replacements.put( ":uti " , "Urinary Tract Infection ");
		 replacements.put( ":uri " , "Upper Respiratory Infection ");
		 replacements.put( ":gou " , "Gout ");
		 replacements.put( ":hiv " , "HIVD : herniated intervertebral disc ");
		 replacements.put( ":dep " , "Depression ");
		 replacements.put( ":anx " , "Anxiety disorder ");
		 replacements.put( ":cog " , "Cognitive Disorder ");
		 replacements.put( ":pa " , "s/p Bronchial Asthma ");
		 replacements.put( ":pc " , "Chronic Cough ");
		 replacements.put( ":pp " , "Pneumonia ");
		 replacements.put( ":pn " , "s/p Pulmonary Nodule ");
		 replacements.put( ":pt " , "s/p Pulmonary Tuberculosis ");
		 replacements.put( ":ntm " , "NTM : Nontuberculous Mycobacterial Pulmonary Disease ");
		 replacements.put( ":gr " , "GDS RC ");
		 replacements.put( ":grr " , "GDSRC Result Consultation ");
		 replacements.put( ":gg " , "공단검진 ");
		 replacements.put( ":ggr " , "공단검진 결과상담 ");
		 replacements.put( ":rr " , "Other clinic RC and Lab result consultation ");
		 replacements.put( ":SxTx " , "Symptomatic treatment and supportive care ");
		 replacements.put( ":teg " , "Hyperthyroidism : Greaves' disease ");
		 replacements.put( ":toh " , "Hypothyroidism : Hashimoto's thyroditis ");
		 replacements.put( ":aa " , "Astrix 100 mg 1 tab po qd ");
		 replacements.put( ":dn- " , "Dm without Nephropathy ");
		 replacements.put( ":migo " , "DR. Koh Jae Joon ");
		 replacements.put( ":jj " , "Migo JJ ");
		 replacements.put( ":df " , "Diabetes Mellitus F/U ");
		 replacements.put( ":tf " , "Hypertension F/U ");
		 replacements.put( ":cf " , "Hypercholwsterolemia F/U ");
		 replacements.put( ":tof " , "Hypothyroidism F/U ");
		 replacements.put( ":tef " , "Hyperthyroisidm F/U ");
		 replacements.put( ":ww " , " with medication. ");
		 replacements.put( ":wq " , " without medication. ");

    }

    public static String EMR_ChangeString(String lines) {
        // Handle special abbreviations
        if (lines.contains(":(")) {
            lines = EMR_ChangeStringCC.EMR_ChangeString_abr(lines);
        } else if (lines.contains(":>")) {
            lines = EMR_ChangeStringCC.EMR_ChangeString_Px(lines);
        }

        // Replace current date placeholder
        String cdate = Date_current.main("d");
        lines = lines.replace(":cd ", cdate);

        // Perform bulk replacements from the map
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            lines = lines.replace(entry.getKey(), entry.getValue());
        }

        return "  " + lines;
    }
}
