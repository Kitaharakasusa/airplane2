package hyg.test;



import org.testng.Assert;


import hyg.model.Check;
import hyg.model.CusTicker;
import hyg.model.PutInCheck;


public class test3 extends test{   
//    public test3(String path) {
//		super(path);
//	}

	//输入合法性测试

    public void testcase(String linetype,String start,String end,String jicang,String cusclass,
            String vip,String luggage,String ticktvalue,String res,String text)
	{
		CusTicker custicker= PutInCheck.Getticketer(linetype,start,end,jicang,cusclass,vip,luggage,ticktvalue);
		Check check=new Check(custicker);
		boolean realres=check.freecheck();
		boolean tem=Boolean.valueOf(res);
		Assert.assertEquals(realres,tem);
	}
}