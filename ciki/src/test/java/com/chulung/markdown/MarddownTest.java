package com.chulung.markdown;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.pegdown.PegDownProcessor;

public class MarddownTest {
	public static void main(String[] args) throws IOException {
		String pathname = "src/test/resources/test2.md";
		String s = getFileString(pathname);
		pathname = "src/test/resources/result.md";
		PegDownProcessor pegDownProcessor = new PegDownProcessor();
		String markdownToHtml = pegDownProcessor.markdownToHtml(s);
		System.out.println(markdownToHtml.replaceAll("\\s", ""));
		System.out.println(getFileString(pathname).replaceAll("\\s", ""));
		
	}

	private static String getFileString(String pathname) {
		File file = new File(pathname);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String s = new String(filecontent);
		return s;
	}
}
