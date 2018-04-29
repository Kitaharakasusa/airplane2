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
    //不符合规定的行李测试
    @Parameters({"linetype","start","end","jicang","cusclass","vip","luggage","ticktvalue","res"})
    @Test
    public void testcase4(String linetype,String start,String end,String jicang,String cusclass,
                          String vip,String luggage,String ticktvalue,String res)
    {
        CusTicker custicker=PutInCheck.Getticketer(linetype,start,end,jicang,cusclass,vip,luggage,ticktvalue);
        Check check=new Check(custicker);
        check.checkover();
        float realres=check.getFinalcost();
        float tofloat=Float.valueOf(res);
        Assert.assertEquals(realres,tofloat);
    }

}
