package com.gsnotes.web.controllers;


import com.gsnotes.bo.Etudiant;
import com.gsnotes.bo.Module;
import com.gsnotes.bo.Niveau;
import com.gsnotes.services.INiveauService;
import com.gsnotes.utils.export.ExcelExporter;
import com.gsnotes.web.models.NiveauModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/prof")
public class NoteDeliberationController {
    @Autowired
    private INiveauService niveauService;

    @RequestMapping("choisirniveau")
    public String choisirNiveau(Model model){
        model.addAttribute("niveauModel",new NiveauModel());
        List<Niveau> niveau=niveauService.getAllNiveau();
        model.addAttribute("ListNiveau",niveau);
        return "prof/ExportNote";
    }
    @PostMapping("exportNotes")
    public void exportToExcel(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        String idNiveau=request.getParameter("idNiveau");
        System.out.println(idNiveau);
        Niveau niveau=niveauService.getNiveauById(Long.valueOf(idNiveau));
        System.out.println(niveau);
        List<Etudiant> students=niveau.getEtudiants();
        List<Module> modules=niveau.getModules();
        for(Module md :modules){
            System.out.println("modules : "+md);
        }
        for(Etudiant etd:students){
            System.out.println("students : "+ etd);
        }
        ExcelExporter excelExporter = niveauService.prepareExp(students,modules, Long.valueOf(idNiveau));
        excelExporter.export(response);
    }
}
