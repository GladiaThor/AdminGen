/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * (>^_^)>
 *
 * @author Claxxess<(^_^<)
 * 
*
 */
package core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public class Populater {

    private PDDocument pdfDocument;

    public void createPDF(String source, String target) throws IOException, COSVisitorException {
        pdfDocument = PDDocument.load(source);
        pdfDocument.save(target);
        pdfDocument.close();
    }

    public void write(String target, ArrayList<PDFField> values) throws IOException, COSVisitorException {
        pdfDocument = PDDocument.load(target);

        for (int i = 0; i < values.size(); i++) {

            setField(values.get(i).getName(), values.get(i).getValue());
        }
        pdfDocument.save(target);
        pdfDocument.close();
    }

    public void populateAndCopy(String source, String target, ArrayList<String> values, ArrayList<String> fields) throws IOException, COSVisitorException {

        pdfDocument = PDDocument.load(source);

        pdfDocument.getNumberOfPages();
        //printFields();  //Uncomment to see the fields in this document in console
        for (int i = 0; i < values.size(); i++) {
            setField(fields.get(i), values.get(i));
        }
        pdfDocument.save(target);
        pdfDocument.close();
    }

    public void populateAndCopy(String source, String target, ArrayList<PDFField> values) throws IOException, COSVisitorException {

        pdfDocument = PDDocument.load(source);

        pdfDocument.getNumberOfPages();
        //printFields();  //Uncomment to see the fields in this document in console
        for (int i = 0; i < values.size(); i++) {
            setField(values.get(i).getName(), values.get(i).getValue());
        }
        pdfDocument.save(target);
        pdfDocument.close();
    }

    public void setField(String name, String value) throws IOException {
        PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();
        try {
            PDField field = acroForm.getField(name);
            if (field != null) {
                field.setValue(value);
            } else {
                System.err.println("No field found with name:" + name);
            }
        } catch (NullPointerException e) {
            System.out.println("Tried to write to field called null");
        }
    }

    @SuppressWarnings("rawtypes")
    public void printFields() throws IOException {
        PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();
        List fields = acroForm.getFields();
        Iterator fieldsIter = fields.iterator();

        System.out.println(new Integer(fields.size()).toString() + " top-level fields were found on the form");

        while (fieldsIter.hasNext()) {
            PDField field = (PDField) fieldsIter.next();
            processField(field, "|--", field.getPartialName());
        }
    }

    @SuppressWarnings("rawtypes")
    private void processField(PDField field, String sLevel, String sParent) throws IOException {
        List kids = field.getKids();
        if (kids != null) {
            Iterator kidsIter = kids.iterator();
            if (!sParent.equals(field.getPartialName())) {
                sParent = sParent + "." + field.getPartialName();
            }

            System.out.println(sLevel + sParent);

            while (kidsIter.hasNext()) {
                Object pdfObj = kidsIter.next();
                if (pdfObj instanceof PDField) {
                    PDField kid = (PDField) pdfObj;
                    processField(kid, "|  " + sLevel, sParent);
                }
            }
        } else {
            String outputString = sLevel + sParent + "." + field.getPartialName() + ",  type=" + field.getClass().getName();
            System.out.println(outputString);
        }
    }
}
