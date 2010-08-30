package a10;

import java.io.File;
import java.io.IOException;

public class FileIO extends InputOutput {

	public File[] getSystemRoots() {
		return File.listRoots();
	}

	public String createDirRelativePath(String dirname) {
		File newdir = new File(dirname);

		if (!newdir.exists()) {
			newdir.mkdir();
		} else {
			printAlreadyExistError();
		}

		return newdir.getAbsolutePath();
	}

	public void createFileWithPath(String path, String filename) {
		File newfile = new File(path, filename);

		if (!newfile.exists()) {
			try {
				newfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			printAlreadyExistError();
		}

	}

	public File[] getDirContent(String path) {
		File[] dirlist = (new File(path)).listFiles();
		return dirlist;

	}

}
