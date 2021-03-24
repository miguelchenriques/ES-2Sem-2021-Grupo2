package es2sem2021.grupo2.codequalityassessor.metrics.extractors;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LOCClass {
	
	
	public static int getClassLOC(File f) throws IOException {
		String[] lines = Files.readString(f.toPath()).split("\r\n");
		int firstLine = 0;
		Pattern patternStart = Pattern.compile("class\\s+", Pattern.CASE_INSENSITIVE);
		for (String l: lines) {
			Matcher matcher = patternStart.matcher(l);
			firstLine++;
			if (matcher.find()) {
				break;
			}
		}
		
		Pattern patternEnd = Pattern.compile("^\\s*\\}$", Pattern.CASE_INSENSITIVE);
		for (int i=lines.length-1; i>firstLine; i--) {
		    Matcher matcher = patternEnd.matcher(lines[i]);
			if (matcher.find()) {
				return i - firstLine + 2;
			}
		}
		return 0;
	}
}
