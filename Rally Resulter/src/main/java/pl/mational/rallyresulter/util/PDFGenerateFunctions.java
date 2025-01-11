package pl.mational.rallyresulter.util;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import pl.mational.rallyresulter.model.CrewResults;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class PDFGenerateFunctions {

    public static void generatePDF(String filePath, List<CrewResults> crewResults) {
        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            pdfDoc.setDefaultPageSize(PageSize.A4.rotate()); // Ustawienie orientacji poziomej
            Document document = new Document(pdfDoc);

            // Ustawienia dla tabeli
            float[] columnWidths = {1, 1, 3, 3, 1, 1, 1, 1, 1}; // Szerokości kolumn
            Table table = new Table(columnWidths);
            table.setWidth(UnitValue.createPercentValue(100)); // Ustaw szerokość tabeli na 100%

            PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN, PdfEncodings.CP1250);

            Cell titleCell = new Cell(1, columnWidths.length) // 1 wiersz, szerokość na całą tabelę
                    .add(new Paragraph("KLASYFIKACJA GENERALNA").setBold().setFontSize(16))
                    .setFont(font)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setBackgroundColor(new DeviceGray(0.9f));
            table.addHeaderCell(titleCell);

            // Nagłówki tabeli
            String[] headers = {"M-CE", "NR", "KIEROWCA\nPILOT", "KLUB KIEROWCY\nKLUB PILOTA",
                    "BRD I\nPP", "TEST\nTUR.", "PYT.\nO1", "PKP\nO1", "WYNIK"};

            // Dodanie nagłówków do tabeli
            for (String header : headers) {
                table.addCell(createNewCell(header, font, true)
                        .setBackgroundColor(new DeviceGray(0.9f)));
            }

            // Dodanie danych drużyn do tabeli
            int position = 1;

            System.out.println(crewResults.get(2).crewNames());
            for (CrewResults result : crewResults) {
                table.addCell(createNewCell(String.valueOf(position++),font, true));
                table.addCell(createNewCell(String.valueOf(result.crewId()), font, true));
                table.addCell(createNewCell(String.valueOf(result.crewNames()), font, true));
                table.addCell(createNewCell(String.valueOf(result.crewClubs()), font, true));
                table.addCell(createNewCell(String.valueOf(result.brdPpTestPoints()), font, false));
                table.addCell(createNewCell(String.valueOf(result.touristicTestPoints()), font, false));
                table.addCell(createNewCell(String.valueOf(result.roadTestPoints()), font, false));
                table.addCell(createNewCell(String.valueOf(result.roadCardPoints()), font, false));
                table.addCell(createNewCell(String.valueOf(result.finalResult()), font, true));
            }

            // Dodanie tabeli do dokumentu
            document.add(table);
            document.close();

            System.out.println("PDF wygenerowany: " + filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Cell createNewCell(String paragraphContent, PdfFont font, boolean shouldBeBold) {
        Paragraph paragraph = new Paragraph(paragraphContent);
        if(shouldBeBold) { paragraph.setBold(); }

        return new Cell()
                .add(paragraph)
                .setFont(font)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE);
    }
}
