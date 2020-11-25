package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class DataProvider {
	

	public static int idGenerator() {
		Random rnd = new Random();
		int n = 10000 + rnd.nextInt(90000);
		return n;
	}

	public static String getConfigData(String key) {	
		Properties pro = null;
		try {
			File file = new File("./Config/config.properties");
			FileInputStream fis = new FileInputStream(file);
			pro = new Properties();
			pro.load(fis);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		return pro.getProperty(key);
	}
	
	public static String getCurrentTime() {
		DateFormat customformat = new SimpleDateFormat("MM_ddy_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		return customformat.format(currentDate);
	}
	
}
