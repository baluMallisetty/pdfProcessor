package pdfProcessorManger;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class imageOCREngine {
public static void main(String args[]) {
	 Tesseract tesseract = new Tesseract();
	 try {
		 System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		tesseract.setDatapath("D:\\pdfProcessor\\TrainingData");
		String text = tesseract.doOCR(new File("C:\\Users\\balu\\wildfly-14.0.0.Final\\bin\\ocrEngine\\input3.png"));		
		System.out.print(text);
	 } catch (TesseractException e) {		
		e.printStackTrace();
	}
}

public String getSampleOCR() {
	 Tesseract tesseract = new Tesseract();
	 try {
		 System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		tesseract.setDatapath("D:\\pdfProcessor\\TrainingData");
		String text = tesseract.doOCR(new File("D:\\pdfProcessor\\input\\3.png"));		
		System.out.print(text);
		return text;
	 } catch (TesseractException e) {		
		e.printStackTrace();
	}
	 return null;
}

}
