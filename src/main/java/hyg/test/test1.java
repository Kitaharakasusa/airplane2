package hyg.test;



import org.testng.Assert;


import hyg.model.PutInCheck;

public class test1 extends test{
//	public test1(String path) {
//		super(path);
//	}
    public void testcase(String linetype,String start,String end,String jicang,String cusclass,
            String vip,String luggage,String ticktvalue,String res,String text){
		int relres=PutInCheck.incheck(linetype,start,end,jicang,cusclass,vip,luggage,ticktvalue).size();
		int toint=Integer.valueOf(res);
		Assert.assertEquals(relres,toint);
	}  
}
