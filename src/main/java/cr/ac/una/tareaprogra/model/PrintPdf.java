package cr.ac.una.tareaprogra.model;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

/**
 *
 * @author PC
 */
public class PrintPdf {

    private int id = 604700092;
    private String name = "Breiner Munoz Fallas";
    private String invoice = "M0511";
    private LocalDate dateOfBirth = LocalDate.of(2001, 11, 05);
    private String sex = "Masculino";
    private String addressPhoto = "src/main/resources/cr/ac/una/tareaprogra/resources/IconChildPhoto.png";
    private String title = "Coopetoy";
    private String logo = "src/main/resources/cr/ac/una/tareaprogra/resources/logo.png";
    private String InriaSerif = "src/main/resources/cr/ac/una/tareaprogra/resources/InriaSerif-Light.ttf";
    private Color backgroundColor = new DeviceRgb(106, 202, 216);
    private Color titleColor = new DeviceRgb(30, 43, 105);
    private Style titleStyle = new Style().setFontSize(30).setBold().setFontColor(titleColor).setTextAlignment(TextAlignment.CENTER);
    private Style paragraphText = new Style().setFontSize(15).setTextAlignment(TextAlignment.LEFT);

    public void printAsociate()throws FileNotFoundException, IOException {
        File file = new File(invoice + ".pdf");

        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(file));
        Document document = new Document(pdfDocument);
        document.setBackgroundColor(backgroundColor);
        
        PdfFont font = PdfFontFactory.createFont(InriaSerif);
        titleStyle.setFont(font);
        paragraphText.setFont(font);
        Paragraph prueba = new Paragraph().add("prueba");
        Paragraph paragraphTitle = new Paragraph().add(title + "\n");
        Paragraph paragraphAsociate = new Paragraph()
                .add(new Text("Cedula del Cliente: ").setBold())
                .add(id + "\n")
                .add(new Text("Nombre del Cliente: ").setBold())
                .add(name + "\n")
                .add(new Text("Folio del Cliente: ").setBold())
                .add(invoice + "\n")
                .add(new Text("Fecha Nacimiento del Cliente: ").setBold())
                .add(dateOfBirth + "\n")
                .add(new Text("Sexo del Cliente: ").setBold())
                .add(sex + "\n");

        Image imagePhoto = new Image(ImageDataFactory.create(addressPhoto));
        imagePhoto.scaleToFit(150, 150);      
        imagePhoto.setFixedPosition(390, 605);
        
        Image imageLogo = new Image(ImageDataFactory.create(logo));
        imageLogo.scaleToFit(40, 40);
        imageLogo.setFixedPosition(25, 755);
        
        paragraphTitle.addStyle(titleStyle);
        paragraphAsociate.addStyle(paragraphText);

        document.add(paragraphTitle);
        document.add(imageLogo);
        document.add(paragraphAsociate);
        document.add(imagePhoto);
        document.close();
        Desktop.getDesktop().open(file);
    }
}
