package model;

import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.jsoup.Jsoup;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.pdfcrowd.Client;

public class Tool {

	public static File openJChooseFile(Component main, String string) {
		File file = null;
		
		JFileChooser fc= new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = fc.showDialog(main, string);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            System.out.println("Choose: "+file.getPath());
        }
        
        return file;
	}
	public static void showPagefromHTMLString(String HTML) {
		JEditorPane editor = new JEditorPane();
		editor.setContentType( "text/html" );    
		editor.setText(HTML);
		
		JScrollPane editorScrollPane = new JScrollPane(editor);
		editorScrollPane.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		JFrame page = new JFrame("HTML");
		page.setContentPane(editorScrollPane);
		page.setVisible(true);
		page.setSize(500, 500);
		page.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		page.setLocationRelativeTo(null);

	}
	
	public static void convertFilePDFfromHTMLStringUseCrowdAPI(File file, String HTML) throws IOException {
		Client client = new Client("username", "apikey");

		// convert a web page and save the PDF to a file
		FileOutputStream fileStream = new FileOutputStream(file);
		client.convertHtml(HTML, fileStream);
		fileStream.close();
	}
	
    public static void createPdf(File file, File htmlFile) throws IOException, DocumentException {
    	/*org.jsoup.nodes.Document doc = Tool.getDocToFile(htmlFile);
    	File tem = new File("tem.html");
		Writer out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(tem), "UTF-8"));
		out.write(doc.html());
		out.close();
*/
    	// step 1
        Document document = new Document();
        // step 2
        PdfWriter writer =
        		PdfWriter.getInstance(document, new FileOutputStream(file));
//        writer.setInitialLeading(12);
        // step 3
        document.open();
        // step 4
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(htmlFile));
        // step 5
        document.close();
    }
    
	public static org.jsoup.nodes.Document getDocToFile(File dir){
		org.jsoup.nodes.Document doc =null;

		//***************Chuyen thread de load********************//
		try {
			doc = Jsoup.parse(dir, "UTF-8");
			doc.outputSettings().charset("UTF-8");
			doc.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.html);
			doc.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}

}