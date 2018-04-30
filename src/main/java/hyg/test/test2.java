package hyg.test;



import org.testng.Assert;

import hyg.model.Check;
import hyg.model.CusTicker;
import hyg.model.PutInCheck;

public class test2 extends test {
    //一般规定测试
//	public test2(String path) {
//		super(path);
//	}
	public void testcase(String linetype,String start,String end,String jicang,String cusclass,
            String vip,String luggage,String ticktvalue,String res,String text)
	{
		CusTicker custicker= PutInCheck.Getticketer(linetype,start,end,jicang,cusclass,vip,luggage,ticktvalue);
		Check check=new Check(custicker);
		boolean realres=check.generacheck();
		boolean tem=Boolean.valueOf(res);
		Assert.assertEquals(realres,tem);
	
	}
    
} 