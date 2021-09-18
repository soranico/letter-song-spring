package com.kanozz.mvc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TomcatUtil {

	public static final File createTempDir(String prefix) {
		try {
			File tempDir = Files.createTempDirectory(prefix + "." + 9090 + ".").toFile();
			tempDir.deleteOnExit();
			return tempDir;
		}
		catch (IOException ex) {
			throw  new RuntimeException();
		}
	}
}
