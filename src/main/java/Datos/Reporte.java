/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.Pedido;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Luis
 */
public class Reporte {

    public static String generarReporte(Pedido pedido) throws Exception {
        // descarga dentro del mismo proyecto
        HashMap parametros = new HashMap();
        parametros.put("idped", pedido.getIdPedido());
        String ruta = "D:\\Documentos\\Proyectos\\TheFruitEmporium\\TheFruitEmporium\\src\\main\\java\\Reportes\\BillReport.jrxml";
        Class.forName("org.postgresql.Driver").newInstance();
        Connection conn = Conexion.getConnection();
        JasperReport reporteJasper = JasperCompileManager.compileReport(ruta);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporteJasper, parametros, conn);
        int num = numeroR();
        String num2 = String.valueOf(num);
        String pdf = "D:\\Documentos\\Proyectos\\TheFruitEmporium\\TheFruitEmporium\\src\\main\\java\\Reportes\\Facturas\\" + num2;
        JasperExportManager.exportReportToPdfFile(jasperPrint, pdf);
        return pdf;
        // se muestra en una ventana aparte para su descarga
//        JasperPrint jasperPrintWindow = JasperFillManager.fillReport(
//                "C:\\Users\\Ecodeup\\JaspersoftWorkspace\\Reportes Escuela\\BillReport.jasper", null,
//                Conexion.getConnection());
//        JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow);
//        jasperViewer.setVisible(true);
    }

    private static int numeroR() {
        Random r1 = new Random();
        int num = r1.nextInt();
        if (num > 0) {
            return num;
        } else {
            return numeroR();
        }
    }

}
