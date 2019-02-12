package com.venta.web.controladores;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import com.venta.web.controladores.ReportController;
import com.venta.config.ReportGenerator;
import com.venta.servicios.ServicioVenta;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
public class ReportController {
	@Autowired
	ServicioVenta servicio;

    @RequestMapping("/cat/{fmt}")
    public String report(@PathVariable("fmt") String format, Model model) {

        model.addAttribute("format", format);
        model.addAttribute("datasource",servicio.findAllCat());
        model.addAttribute("generator", (ReportGenerator) this::catReport);

        return "cat_DynamicReport";
    }

    private JasperReport catReport(Map params) {
        try {
            FastReportBuilder drb = new FastReportBuilder();
            drb
                    .addColumn("ID", "ID", Integer.class.getName(), 10)
                    .addColumn("DENOMINACION", "DENOMINACION", String.class.getName(), 50)
                    .setTitle("Primer informe con Dynamic Jasper")
                    .setPrintBackgroundOnOddRows(true)
                    .setUseFullPageWidth(true);

            return DynamicJasperHelper.generateJasperReport(
                    drb.build(), new ClassicLayoutManager(), params);

        } catch (ColumnBuilderException | ClassNotFoundException | JRException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
