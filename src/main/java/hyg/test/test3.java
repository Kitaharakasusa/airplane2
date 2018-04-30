package hyg.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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