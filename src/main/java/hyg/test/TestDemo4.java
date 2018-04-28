package hyg.test;

import hyg.model.Check;
import hyg.model.CusTicker;
import hyg.model.PutInCheck;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Create by Kitahara on 2018/4/29 0:25
 * email kitaharakasusa@gmail.com
 */
public class TestDemo4 {
    //免费测试
    @Parameters({"linetype","start","end","jicang","cusclass","vip","luggage","ticktvalue","res"})
    @Test
    public void testcase3(String linetype,String start,String end,String jicang,String cusclass,
                          String vip,String luggage,String ticktvalue,boolean res)
    {
        CusTicker custicker= PutInCheck.Getticketer(linetype,start,end,jicang,cusclass,vip,luggage,ticktvalue);
        Check check=new Check(custicker);
        boolean realres=check.freecheck();
        Assert.assertEquals(realres,res);
    }
}
