package hyg.model;

import hyg.Unit.CustomerType;
import hyg.Unit.Place;
import hyg.Unit.TickClass;
import hyg.Unit.Vip;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PutInCheck{
    private ArrayList<String>Luggage;
    private hyg.Unit.InOrOut InOrOut;
    private Place start;
    private Place destination;
    private Vip youhui;//
    private TickClass jicang;
    private CustomerType customer;
    private float tikcervalue;
    public static boolean isNumeric(String str) {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }

        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    public static ArrayList<String> incheck(String linetype,String start,
                                            String end,String jicang,String cusclass,String vip,
                               String luggage,String ticketvalue)
    {
        ArrayList<String>warninginfo=new ArrayList<String>();

        if (linetype.equals("temp"))
        {
            warninginfo.add("未选择航线类型");

        }
        if (start.equals("temp"))
        {
            warninginfo.add("未选择出发地");
        }
        if (end.equals("temp"))
        {
            warninginfo.add("未选择目的地");
        }
        if (start.equals(end)&&(!start.equals("C")&&!end.equals("C")))
        {
            warninginfo.add("出发地目的地相同");
        }
        if (!start.equals("C")&&!end.equals("C"))
        {
            warninginfo.add("出发地目的地必须有一个包含中国");
        }
        if (jicang.equals("temp"))
        {
            warninginfo.add("未选择机舱类型");

        }
        if (vip.equals("temp"))
        {
            warninginfo.add("请选择优惠类型");
        }
        if (cusclass.equals("temp"))
        {
            warninginfo.add("未选择乘客类型");
        }
        if (luggage.length()==0)
        {
            warninginfo.add("未输入行李重量和规格");

        }
        if (luggage.length()>0)
        {
            String []arr=luggage.split("\\s+");
            for (String str:arr)
            {
                String[]nowarr=str.split("/");
                for (String a:nowarr)
                {
                    System.out.println(a);
                }
                if (nowarr.length>4)
                {
                    warninginfo.add("行李格式过长");

                }
                else if(nowarr.length<4){
                    warninginfo.add("行李格式过短");

                }
                else {

                    if (nowarr[0].length()==0)
                    {
                        warninginfo.add("有行李没有输入重量");
                    }
                    else if (!PutInCheck.isNumeric(nowarr[0]))
                    {
                        warninginfo.add("重量包含非法字符");
                    }
                    else if ( Float.valueOf(nowarr[0])<0)
                    {
                        warninginfo.add("有行李质量为负数");
                    }

                    if (nowarr[1].length()==0)
                    {
                        warninginfo.add("有行李没有输入长度");
                    }
                    else if (!PutInCheck.isNumeric(nowarr[1]))
                    {
                        warninginfo.add("长度包含非法字符");
                    }
                    else  if (Float.valueOf(nowarr[1])<0)
                    {
                        warninginfo.add("有行李长度为负数");
                    }

                    if (nowarr[2].length()==0)
                    {
                        warninginfo.add("有行李没有输入宽度");
                    }
                    else if (!PutInCheck.isNumeric(nowarr[2]))
                    {
                        warninginfo.add("宽度包含非法字符");
                    }
                    else if (Float.valueOf(nowarr[2])<0)
                    {
                        warninginfo.add("有行李宽度为负数");
                    }


                    if (nowarr[3].length()==0)
                    {
                        warninginfo.add("有行李没有输入高度");
                    }
                    else if (!PutInCheck.isNumeric(nowarr[3]))
                    {
                        warninginfo.add("高度包含非法字符");
                    }
                    else if (Float.valueOf(nowarr[3])<0)
                    {
                        warninginfo.add("有行李高度为负数");
                    }




                }
            }
        }
        if (ticketvalue.length()==0)
        {
            warninginfo.add("未输入票价");
        }
        else if (!PutInCheck.isNumeric(ticketvalue))
        {
            warninginfo.add("票价包含非法字符");
        }
        else if (PutInCheck.isNumeric(ticketvalue))
        {
            if (Float.valueOf(ticketvalue)<0)
            {
                warninginfo.add("输入票价为负值");
            }
        }

        return warninginfo;
    }

    public static CusTicker Getticketer (String linetype, String start, String end, String jicang, String cusclass, String vip,
                                         String luggage, String ticketvalue)
    {
        String []arr=luggage.split("\\s+");

        ArrayList<Bag>arrayList=new ArrayList<Bag>();
        for (String a:arr)
        {
            String nowarr[]=a.split("/");
            Bag bag=new Bag(Float.valueOf(nowarr[0]),Float.valueOf(nowarr[1]),Float.valueOf(nowarr[2]),
                    Float.valueOf(nowarr[3]));
            arrayList.add(bag);
        }
        hyg.Unit.InOrOut linetyppe;
        Place st=null;
        Place des=null;
        TickClass tic=null;
        CustomerType cus=null;
        Vip vip1=null;
        Float piaojia=Float.valueOf(ticketvalue);
        if (linetype.equals("in"))
        {
            linetyppe= hyg.Unit.InOrOut.InChina;
        }
        else linetyppe= hyg.Unit.InOrOut.OunChina;

        if (start.equals("C"))st= Place.China;
        else if (start.equals("J"))st= Place.Japan;
        else if (start.equals("AM"))st= Place.America;
        else if (start.equals("AME"))st= Place.Miguo;
        else if (start.equals("AC"))st= Place.Auckland;
        else if (start.equals("R"))st= Place.Russia;
        else if (start.equals("D"))st= Place.Dubai;
        else if (start.equals("S"))st= Place.Singapore;
        else if (start.equals("W"))st= Place.MidWestAsia;
        else if (start.equals("N"))st= Place.Nairobi;
        else if (start.equals("CLW"))st=Place.China_LanWU;

        if (end.equals("C"))des= Place.China;
        else if (end.equals("J"))des= Place.Japan;
        else if (end.equals("AM"))des= Place.America;
        else if (end.equals("AME"))des= Place.Miguo;
        else if (end.equals("AC"))des= Place.Auckland;
        else if (end.equals("R"))des= Place.Russia;
        else if (end.equals("D"))des= Place.Dubai;
        else if (end.equals("S"))des= Place.Singapore;
        else if (end.equals("W"))des= Place.MidWestAsia;
        else if (end.equals("N"))des= Place.Nairobi;
        else if (end.equals("CLW"))des=Place.China_LanWU;

        if (jicang.equals("T"))tic= TickClass.TouDeng;
        else if (jicang.equals("G"))tic= TickClass.Gongwu;
        else if (jicang.equals("M"))tic= TickClass.Mingzhu;
        else if (jicang.equals("J"))tic= TickClass.Jingji;
        else if (jicang.equals("W"))tic= TickClass.Wuzuoertong;

        if (cusclass.equals("A"))cus= CustomerType.Adult;
        else if (cusclass.equals("C"))cus= CustomerType.Child;

        if (vip.equals("J"))vip1= Vip.GOLD_SUPPER;
        else if (vip.equals("S"))vip1= Vip.SILIVER;
        else if (vip.equals("L"))vip1= Vip.SILIVER;

        CusTicker cusTicker=new CusTicker(linetyppe,st,des,tic,arrayList,vip1,piaojia);
        return cusTicker;
    }
}
