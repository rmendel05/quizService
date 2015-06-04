package com.rmendel.framework.test;

import com.rmendel.framework.BaseCsvParser;
import com.rmendel.framework.CsvReader;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CsvReaderTest extends TestCase
{
    public CsvReaderTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( CsvReaderTest.class );
    }

    public void testCsvReader() throws Exception
    {
    	CsvReader reader = new CsvReader("./src/test/java/testdata.csv");
    	TestingParser parser = new TestingParser();
    	parser.setColumnSeparator("|");
    	reader.readAllLines(parser);
    	
        assertTrue("Not all lines processed",
        		reader.getTotalLinesProcessed() == parser.processCount);
        assertTrue("Not all lines successfully processed.", reader.getTotalLinesProcessedSuccessfully() == 
        		reader.getTotalLinesProcessed());
        assertTrue("No values are being processed", parser.areValuesPresent);
    }
    
    public class TestingParser extends BaseCsvParser {
    	public int processCount = 0;
    	public boolean areValuesPresent = false;

		@Override
		public void processValues(String[] values) throws Exception {
			processCount++;
			
			for(String value:values)
				if(value != null && !value.isEmpty())
					areValuesPresent = true;
		}
    }
}