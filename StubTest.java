package my;
import static org.junit.Assert.*;

import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
// File Writer


public class StubTest {

	/*
	 * Declare harnesses that let you test a mapper, a reducer, and a mapper and
	 * a reducer working together.
	 */
	MapDriver<Object, Text, Text, LongWritable> mapDriver;
	ReduceDriver<Text, LongWritable, Text, LongWritable> reduceDriver;
	MapReduceDriver<Object, Text, Text, LongWritable, Text, LongWritable> mapReduceDriver;

	/*
	 * Set up the test. This method will be called before every test.
	 */
	@Before
	public void setUp() {

		/*
		 * Set up the mapper test harness.
		 */
		StubMapper mapper = new StubMapper();
		//mapDriver = new MapDriver<Object, Text, Text, LongWritable>();
		//mapDriver.setMapper(mapper);
		/*
		 * Set up the reducer test harness.
		 */
		StubReducer reducer = new StubReducer();
		//reduceDriver = new ReduceDriver<Text, LongWritable, Text, LongWritable>();
		//reduceDriver.setReducer(reducer);

		/*
		 * Set up the mapper/reducer test harness.
		 */
		mapReduceDriver = new MapReduceDriver<Object, Text, Text, LongWritable, Text, LongWritable>();
		mapReduceDriver.setMapper(mapper);
		mapReduceDriver.setReducer(reducer);

	}

	/*
	 * Test the mapper.
	 */
	@Test
	public void testMapper() {}

	/*
	 * Test the reducer.
	 */
	@Test
	public void testReducer() throws IOException {}

	/*
	 * Test the mapper and reducer working together.
	 */
	@Test
	public void testMapReduce() throws IOException {
		//fail("Please implement test.");

		// Read input file and setup mapReduceDriver input
		FileReader fileReader = new FileReader("A4_P1_1_Input.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String str;
		Double i=0.0;
		while ((str = bufferedReader.readLine()) != null) {
			i=i+1;
			mapReduceDriver.addInput(new Pair<Object, Text>(String.valueOf(i), new Text(str)));
		}
		fileReader.close();

		// Call Map/Reduce
		List<Pair<Text, LongWritable>> output = mapReduceDriver.run();
		
		// Print result to screen
		for (Pair<Text, LongWritable> p : output) {
			System.out.println(p.getFirst() + " - " + p.getSecond());
		}

		// Output result to file
		PrintWriter writer = new PrintWriter("A4_P1_1_Output.txt", "UTF-8");
		for(Pair<Text, LongWritable> p:output){
			writer.println(p.getFirst() + " - " + p.getSecond());
		}
		writer.close();
		

	}
}
