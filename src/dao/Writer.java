package dao;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {
		private FileWriter fw;
	
		public Writer(String filepath, boolean overwrite) {
				try {
						fw=new FileWriter (filepath, overwrite);
				} catch (IOException e) {
						e.printStackTrace();
				}
		}

		public void writeOnFile(String output) {
				try {
						fw.write(output);
				} catch (IOException e) {
						e.printStackTrace();
				}
		}

		public void close() {
				try {
						fw.flush();
						fw.close();
				} catch (IOException e) {
						e.printStackTrace();
				}
		}
}
