package cr.ac.una.tareaprogra.model;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import cr.ac.una.tareaprogra.App;
import cr.ac.una.tareaprogra.util.AppContext;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import javafx.collections.ObservableList;

/**
 *
 * @author Marconi
 */
public class PrintPdf {

    private Long id;
    private String name = "";
    private String invoice = "";
    private LocalDate dateOfBirth;
    private String sex = "";
    private String title = "";
    private String addressPhoto = "resources/IconChildPhoto.png";
    private String logo = "";
    private String crab = "resources/crab.png";
    private String octopus = "resources/octopus.png";
    private String InriaSerif = "cr/ac/una/tareaprogra/resources/InriaSerif-Light.ttf";
    private String Regular = "cr/ac/una/tareaprogra/resources/Rancho-Regular.ttf";
    private Cooperative instanceCooperative;
    
    public void printPdf() throws IOException, DocumentException {
        String temporaryFolder = System.getProperty("java.io.tmpdir"); //Obtiene la ruta de la carpeta temporal
        String fileName = invoice+".pdf";
        File file = new File(temporaryFolder, fileName);
        file.deleteOnExit();
        
        com.itextpdf.text.Rectangle pageSize = new com.itextpdf.text.Rectangle(500f, 400f);
        Document document = new Document(pageSize);
        BaseFont baseFont = BaseFont.createFont(Regular, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        PdfWriter.getInstance(document, new FileOutputStream(file));

        Image imagePhoto = Image.getInstance(addressPhoto);
        imagePhoto.scaleToFit(140, 140);
        imagePhoto.setAbsolutePosition(325, 200);
        
        Image imageLogo;
        if("cr/ac/una/tareaprogra/resources/logo.png".equals(instanceCooperative.getLogoPath())){
            imageLogo = Image.getInstance(App.class.getResource("resources/logo.png"));
        }else{
            imageLogo = Image.getInstance(logo);
        }
        imageLogo.scaleToFit(75, 75);
        imageLogo.setAbsolutePosition(10, 325);

        Image imageCrab = Image.getInstance(App.class.getResource(crab));
        imageCrab.scaleToFit(140, 140);
        imageCrab.setAbsolutePosition(18, 10);

        Image imageOctopus = Image.getInstance(App.class.getResource(octopus));
        imageOctopus.scaleToFit(170, 170);
        imageOctopus.setAbsolutePosition(315, 10);

        Font font = new Font(baseFont, 20, Font.NORMAL, BaseColor.BLACK);
        Font titleFont = new Font(baseFont, 48, Font.NORMAL, BaseColor.BLUE);

        document.open();
        Paragraph titleParagraph = new Paragraph();
        titleParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        titleParagraph.setFont(titleFont);
        titleParagraph.add(title + "\n\n\n");

        document.add(titleParagraph);
        document.add(new Paragraph("Cedula del Cliente: " + id, font));
        document.add(new Paragraph("Nombre del Cliente: " + name, font));
        document.add(new Paragraph("Folio del Cliente: " + invoice, font));
        document.add(new Paragraph("Fecha Nacimiento del Cliente: " + dateOfBirth, font));
        document.add(new Paragraph("Sexo del Cliente: " + sex, font));

        document.add(imagePhoto);
        document.add(imageLogo);
        document.add(imageCrab);
        document.add(imageOctopus);

        document.close();
        Desktop.getDesktop().open(file);
    }

    public void printAsociate(Associate associate) throws IOException, DocumentException {
        this.id = associate.getId();
        this.name = associate.getName() + " " + associate.getLastName1() + " " + associate.getLastName2();
        this.invoice = associate.getInvoice();
        this.dateOfBirth = associate.getDateOfBirth();
        this.sex = associate.getSex();
        File file = new File(associate.getAddressPhoto());
        String localUrl = file.toURI().toString();
        this.addressPhoto = localUrl;
        loadCooperative();
        this.title = instanceCooperative.getNameOfCooperative();
        String logolUrl = instanceCooperative.getLogoPath();
        this.logo = logolUrl;
        printPdf();
    }

    private void loadCooperative() {
        ObservableList<Cooperative> cooperativeList = (ObservableList<Cooperative>) AppContext.getInstance().get("newCooperative");    
        instanceCooperative = new Cooperative();
        for (Cooperative cooperative : cooperativeList) {
            instanceCooperative = cooperative;
        }
    }
}
