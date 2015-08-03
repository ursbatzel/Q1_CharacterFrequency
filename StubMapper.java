package my;
import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class StubMapper extends Mapper<Object, Text, Text, LongWritable> {

	@Override
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
	
		//A4 P1-1 - Count the number of occurrences of each letter
		String str = value.toString(); // Get the next string to evaluate
		// Remove all non-alphabetic characters and convert to lower case
		str = str.replaceAll("[^a-zA-Z]", "").toLowerCase(); 
		for (int i = 0;i < str.length(); i++){ // For Hadoop processing, write each letter and 1 to file.
			// System.out.println(str.substring(i,i+1) + " " + 1);
			context.write(new Text( str.substring(i,i+1)  ), new LongWritable(1));
		}
	}
}

