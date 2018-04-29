package hyg.test;

import hyg.model.Check;
import hyg.model.CusTicker;
import hyg.model.PutInCheck;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Create by Kitahara on 2018/4/28 11:00
 * email kitaharakasusa@gmail.com
 */
public class TestDemo1 {
    //输入合法性测试
    @Parameters({"linetype","start","end","jicang","cusclass","vip","luggage","ticktvalue","res"})
    @Test
    public void testcase1(String linetype,String start,String end,String jicang,String cusclass,
                          String vip,String luggage,String ticktvalue,String res)
    {
        int relres=PutInCheck.incheck(linetype,start,end,jicang,cusclass,vip,luggage,ticktvalue).size();
        int toint=Integer.valueOf(res);
        Assert.assertEquals(relres,toint);
        System.out.println("testcase1");

    }
    //一般规定测试
//    @Parameters({"linetype","start","end","jicang","cusclass","vip","luggage","ticktvalue","res"})
//    @Test
//    public void testcase2(String linetype,String start,String end,String jicang,String cusclass,
//                          String vip,String luggage,String ticktvalue,boolean res)
//    {
//        CusTicker custicker=PutInCheck.Getticketer(linetype,start,end,jicang,cusclass,vip,luggage,ticktvalue);
//        Check check=new Check(custicker);
//        boolean realres=check.generacheck();
//        Assert.assertEquals(realres,res);
//
//    }
//    //免费测试
//    @Parameters({"linetype","start","end","jicang","cusclass","vip","luggage","ticktvalue","res"})
//    @Test
//    public void testcase3(String linetype,String start,String end,String jicang,String cusclass,
//                          String vip,String luggage,String ticktvalue,boolean res)
//    {
//        CusTicker custicker=PutInCheck.Getticketer(linetype,start,end,jicang,cusclass,vip,luggage,ticktvalue);
//        Check check=new Check(custicker);
//        boolean realres=check.freecheck();
//        Assert.assertEquals(realres,res);
//    }
//    //不符合规定的行李测试
//    @Parameters({"linetype","start","end","jicang","cusclass","vip","luggage","ticktvalue","res"})
//    @Test
//    public void testcase4(String linetype,String start,String end,String jicang,String cusclass,
//                          String vip,String luggage,String ticktvalue,float res)
//    {
//        CusTicker custicker=PutInCheck.Getticketer(linetype,start,end,jicang,cusclass,vip,luggage,ticktvalue);
//        Check check=new Check(custicker);
//        check.checkover();
//        float realres=check.getFinalcost();
//        Assert.assertEquals(realres,res);
//    }



}
