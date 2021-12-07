package com.utilclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	private static FileInputStream fileinputstream(String fileName) throws FileNotFoundException {
		FileInputStream fileinputstream = null;
		fileinputstream = new FileInputStream(new File(fileName));
		return fileinputstream;
	}

	public static String readdata(String filename, String sheetname, int rownum, int colnum) {
		try {
			FileInputStream fileinputstream = fileinputstream(filename);
			Workbook w = WorkbookFactory.create(fileinputstream);
			w.getSheet(sheetname).getRow(rownum).getCell(colnum).getStringCellValue();
		} catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int getRowIndex(String filename, String sheetname, int startPoint, String KeyName) {
		int rowIndex = 8;
		try {
			FileInputStream fileinputstream = fileinputstream(filename);
			Sheet w = WorkbookFactory.create(fileinputstream).getSheet(sheetname);
			for (int i = 0; i <= w.getLastRowNum(); i++) {
				for (int j = 0; j < w.getRow(i).getLastCellNum(); j++) {
					if (w.getRow(i).getCell(j).getStringCellValue().equalsIgnoreCase(KeyName)) {
						rowIndex = i;
						break;
					}
				}
			}
		} catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
			e.printStackTrace();
		}
		return rowIndex;
	}

	public static int getColIndex(String filename, String sheetname, int startPoint, String KeyName) {
		int colIndex = 0;
		try {
			FileInputStream fileinputstream = fileinputstream(filename);
			Sheet w = WorkbookFactory.create(fileinputstream).getSheet(sheetname);
			for (int i = startPoint; i <= w.getLastRowNum(); i++) {
				for (int j = 0; j < w.getRow(i).getLastCellNum(); j++) {
					if (w.getRow(i).getCell(j).getStringCellValue().equalsIgnoreCase(KeyName)) {
						colIndex = j;
						break;
					}
				}
			}
		} catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
			e.printStackTrace();
		}
		return colIndex;
	}

	public static Map<String, String> getValue(String filename, String sheetname, int startPoint, String KeyName,
			String colName) {
		Map<String, String> value = new HashMap<String, String>();
		try {
			FileInputStream fileinputstream = new FileInputStream(new File(filename));
			Sheet w = WorkbookFactory.create(fileinputstream).getSheet(sheetname);
			int keyNamesIndex = getColIndex(filename, sheetname, startPoint, KeyName);
			int valueIndex = getColIndex(filename, sheetname, startPoint, colName);
			for (int i = 1; i < w.getLastRowNum(); i++) {
				value.put(w.getRow(i).getCell(keyNamesIndex).toString(), w.getRow(i).getCell(valueIndex).toString());
			}
			try {
				value.put(w.getRow(w.getLastRowNum()).getCell(keyNamesIndex).toString(),
						w.getRow(w.getLastRowNum()).getCell(valueIndex).toString());
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return value;
	}

	public static Map<String, String> getValueByCol(String filename, String sheetname, int startPoint, String KeyName,
			String colName) {
		Map<String, String> value = new HashMap<String, String>();
		try {
			FileInputStream fileinputstream = fileinputstream(filename);
			Sheet w = WorkbookFactory.create(fileinputstream).getSheet(sheetname);
			int HeadingIndex = getRowIndex(filename, sheetname, startPoint, colName);
			int valueIndex = getColIndex(filename, sheetname, startPoint, colName);
			value.put(w.getRow(HeadingIndex).getCell(valueIndex).toString(),
					w.getRow(HeadingIndex + 1).getCell(valueIndex).toString());
		} catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static Integer getLastRowNum(String filename, String sheetname) {
		int rownum = 0;
		try {
			FileInputStream fileinputstream = fileinputstream(filename);
			Sheet w = WorkbookFactory.create(fileinputstream).getSheet(sheetname);
			rownum = w.getLastRowNum();
		} catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
			e.printStackTrace();
		}
		return rownum;
	}

	public static Integer getLastColNum(String filename, String sheetname, String rowname) {
		int colnum = 0;
		try {
			FileInputStream fileinputstream = fileinputstream(filename);
			Sheet w = WorkbookFactory.create(fileinputstream).getSheet(sheetname);
			int RowIndex = getRowIndex(filename, sheetname, 0, rowname);
			colnum = w.getRow(RowIndex).getLastCellNum();
		} catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
			e.printStackTrace();
		}
		return colnum;
	}
}
