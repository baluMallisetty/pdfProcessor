/**
 * 
 */
package pdfProcessorManger;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * @author balu
 *
 */
public class OcrEngine1 extends imageOCREngine{
	public String getSampleOCR(String path) {
		 Tesseract tesseract = new Tesseract();
		 try {
			 System.out.println("Working Directory = " +
		              System.getProperty("user.dir"));
			tesseract.setDatapath("D:\\pdfProcessor\\TrainingData");
			String text = tesseract.doOCR(new File(path));		
			System.out.print(text);
			return text;
		 } catch (TesseractException e) {		
			e.printStackTrace();
		}
		 return null;
	}
}
