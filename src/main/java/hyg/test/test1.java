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
