package hyg.test;

import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.List;


import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public abstract class test {   
	private static String FilePath;

	@BeforeSuite
    @Parameters({"filename"})
    public void changefilepath(String filePath)
    {
        FilePath=filePath;
        System.out.println(filePath);
    }

	@DataProvider(name = "testData")
	public static Object[][] testData() throws Exception{    
		return getData(FilePath);
    }      
	
	@Test(dataProvider ="testData" )
    public abstract void testcase(String linetype,String start,String end,String jicang,String cusclass,
                          String vip,String luggage,String ticktvalue,String res,String text);
	
	
	public static Object[][] getData(String filePath) throws Exception {

        String line;
        List<String[]> list = new ArrayList<String[]>();
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(filePath)));
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] fileds = line.split(",");
            list.add(fileds);

        }
        br.close();
        Object[][] result = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
} 