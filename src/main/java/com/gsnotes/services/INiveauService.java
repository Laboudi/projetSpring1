package com.gsnotes.services;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.bo.Module;
import com.gsnotes.bo.Niveau;
import com.gsnotes.bo.Utilisateur;
import com.gsnotes.utils.export.ExcelExporter;

import java.util.List;

public interface INiveauService {
    public List<Niveau> getAllNiveau();
    public Niveau getNiveauById(Long id);
    public ExcelExporter prepareExp(List<Etudiant> etudiants, List<Module> modules,Long id);
}
