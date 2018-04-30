package hyg.test;


import org.testng.Assert;


import hyg.model.PutInCheck;

public class test4 extends test {   
    //输入合法性测试

//	public test4(String path) {
//		super(path);
//	}

    public  void testcase(String linetype,String start,String end,String jicang,String cusclass,
                          String vip,String luggage,String ticktvalue,String res,String text){
		
        int relres=PutInCheck.incheck(linetype,start,end,jicang,cusclass,vip,luggage,ticktvalue).size();
        int toint=Integer.valueOf(res);
        System.out.println(relres);
        Assert.assertEquals(relres,toint);

    }  
}

/*public class test4 {   
    //输入合法性测试

	@DataProvider(name = "testData")
	public static Object[][] testData() throws Exception{    
		return getData("E:/csv/new4.csv");
    }      
	
	@Test(dataProvider ="testData" )
    public void testcase(String linetype,String start,String end,String jicang,String cusclass,
                          String vip,String luggage,String ticktvalue,String res,String text){
		
        int relres=PutInCheck.incheck(linetype,start,end,jicang,cusclass,vip,luggage,ticktvalue).size();
        int toint=Integer.valueOf(res);
        System.out.println(relres);
        Assert.assertEquals(relres,toint);

    }  
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
} */