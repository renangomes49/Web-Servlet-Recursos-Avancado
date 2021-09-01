package services;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioService implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// para trabalhar com um sub-relatorio - relacionamento 1 para muitos
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	
	private static final String FOLDER_RELATORIOS = "/relatorios";
	private  String SEPARATOR = File.separator;
	private  String caminhoArquivoRelatorio = null;
	private static JRExporter exporter = null;
	private String caminhoSubReport_Dir = "";
	private File arquivoGerado = null;

	public String gerarRelatório(List<?> listaDataBeanColletion, HashMap parametrosRelatorio
			, String nomeRelatorioJasper, String nomeRelatorioSaida, ServletContext servletContext, String tipoRelatorio) throws Exception{
		
		/*Cria a lista de collectionsDataSource de beans que carregam os dados do relatorio*/
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDataBeanColletion);
		
		/*Fornece o caminho fisico até a pasta que contém os relatórios .jasper*/
		String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);
		
		File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper");
		
		if(caminhoRelatorio == null || (caminhoRelatorio != null && caminhoRelatorio.isEmpty())
				|| !file.exists()) {
			
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			SEPARATOR = "";
		}
		
		/*Caminho para imagens*/
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
		
		/*caminho completo até o relatorio compilado indicado*/
		String caminhoArquivosJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper";
		
		/*Faz o carregamento do relatório*/
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivosJasper);
		
		/*definir os parametros SUBREPORT_DIR com o caminho fisico para subreport*/
		caminhoSubReport_Dir = caminhoRelatorio + SEPARATOR;
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubReport_Dir);
		
		/*Carrega o arquivo*/
		JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio,jrbcds);
		
		
		if(tipoRelatorio.equalsIgnoreCase("pdf")) {
			exporter = new JRPdfExporter();
		}else if(tipoRelatorio.equalsIgnoreCase("xls")){
			exporter = new JRXlsExporter();
		}
		
		
		/*Caminho relatorio Exportado*/
		caminhoArquivoRelatorio = caminhoRelatorio + SEPARATOR + nomeRelatorioSaida + "." + tipoRelatorio;
		
		/*Criar novo arquivos exportado*/
		arquivoGerado = new File(caminhoArquivoRelatorio);
		
		/*Prepara a impressão*/
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
		
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
		
		/*Executa a exportação*/
		exporter.exportReport();
		
		/*Remove o arquivo do servidor após ser feito o download*/
		arquivoGerado.deleteOnExit();
		
		return caminhoArquivoRelatorio;
	}
	
}








