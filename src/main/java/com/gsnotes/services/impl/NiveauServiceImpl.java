package com.gsnotes.services.impl;

import com.gsnotes.bo.*;
import com.gsnotes.bo.Module;
import com.gsnotes.dao.INiveauDao;
import com.gsnotes.services.INiveauService;
import com.gsnotes.utils.export.ExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NiveauServiceImpl implements INiveauService {
    @Autowired
    private INiveauDao niveauDao;
    @Override
    public List<Niveau> getAllNiveau() {
        return niveauDao.findAll();
    }

    @Override
    public Niveau getNiveauById(Long id) {
        return niveauDao.getById(id);
    }

    @Override
    public ExcelExporter prepareExp(List<Etudiant> etudiants, List<Module> modules,Long id) {
        List<String> columnNames =new ArrayList<>();
        columnNames.add("IDEtudiant");
        columnNames.add("CNE");
        columnNames.add("NOM");
        columnNames.add("PRENOM");
        for (Module md:modules){
            columnNames.add(md.getTitre());
            columnNames.add("  Moyenne  ");
            columnNames.add("  Validation ");
        }
        columnNames.add("Moyenne Generale");
        columnNames.add("Validation");
        columnNames.add("Rang");
        String[] columns = new String[columnNames.size()];
        columnNames.toArray(columns);

        String[][] data = new String[etudiants.size()][50];
        String [][] dataModule=new String[modules.size()][];

        int i = 0;
        for (Etudiant etd : etudiants) {
            data[i][0] = String.valueOf(etd.getIdUtilisateur());
            data[i][1] = etd.getCne();
            data[i][2] = etd.getNom();
            data[i][3] = etd.getPrenom();
            i++;
        }

        /*
        int j=0;
        for(Module md:modules){
            List<InscriptionModule> ins=md.getInscriptionModules();
            int l=5;
            for (int k = 0; k < ins.size(); k++,l++) {

                data[k][l]= String.valueOf(ins.get(k).getNoteFinale());
                data[k][l+1]=ins.get(k).getValidation();


            }





        }*/


        return new ExcelExporter(columns, data, "noteDeliberation");
    }
}
