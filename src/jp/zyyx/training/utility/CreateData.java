package jp.zyyx.training.utility;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.zyyx.training.model.ArticleService;

import com.opencsv.CSVWriter;
public class CreateData {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		CSVWriter writer = new CSVWriter(new FileWriter("D:\\data.csv"));
		String[] entries = new String[4];
		DateFormat dateFormat = new SimpleDateFormat(ArticleService.dateFormat);
		
		for (int id = 30; id > 0; id--) {
			//Thread.sleep(3000);
			entries[0] = Integer.toString(id);
			Date date = new Date();
			entries[1] = dateFormat.format(date);
			entries[2] = "のんのんびより";
			entries[3] = "あふぁｈふぉあふぁｆ　あｆっはｆはｆ　あっふぁｈｆはｆだ　ふぁｆはふぁｆｓｄｓ";
			writer.writeNext(entries);
		}
		System.out.println("End!\n");
		
		writer.close();
	}

}
