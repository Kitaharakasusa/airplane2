package hyg.controller;

import hyg.Unit.InOrOut;
import hyg.Unit.Place;
import hyg.Unit.TickClass;
import hyg.Unit.Vip;
import hyg.model.Bag;
import hyg.model.Check;
import hyg.model.CusTicker;
import hyg.model.PutInCheck;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class Search {
    @RequestMapping("/Search")
    public String tosear()
    {
        return "Search";
    }

    @RequestMapping("/DoSearch")
    public ModelAndView realsearch(String linetype,String start,String end,String jicang,String cusclass,String vip,
                                   String luggage,String ticketvalue)
    {

        ArrayList<String>warninginfo=new ArrayList<String>();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("show");
        warninginfo=PutInCheck.incheck(linetype,start,end,jicang,cusclass,vip,luggage,ticketvalue);
        float allcost=-1;
        if (warninginfo.size()==0) {
            CusTicker cusTicker = PutInCheck.Getticketer(linetype, start, end, jicang, cusclass, vip, luggage, ticketvalue);
            Check check = new Check(cusTicker);
            if (!check.generacheck()) {
                warninginfo=check.getWarnningInfo();
                for (String str:warninginfo)
                {
                    System.out.println("一般"+str);
                }
                modelAndView.addObject("warn", warninginfo);
                modelAndView.addObject("cost",allcost);
                return modelAndView;

            }

            else if (!check.freecheck()) {
                check.checkover();
                warninginfo = check.getWarnningInfo();
                if (warninginfo.size() == 0) {
                    allcost = check.getFinalcost();
                }
                modelAndView.addObject("warn",warninginfo);
                modelAndView.addObject("cost",allcost);
                return modelAndView;
            }

            if (check.freecheck())
            {
                allcost=Float.valueOf(ticketvalue);
                modelAndView.addObject("warn",warninginfo);
                modelAndView.addObject("cost",allcost);
                return modelAndView;
            }


        }
        modelAndView.addObject("cost",allcost);
        modelAndView.addObject("warn",warninginfo);





//
//        if (linetype.equals("temp"))
//        {
//            warninginfo.add("未选择航线类型");
//            modelAndView.addObject("warn",warninginfo);
//            return modelAndView;
//        }
//        if (start.equals("temp"))
//        {
//            warninginfo.add("未选择出发地");
//            modelAndView.addObject("warn",warninginfo);
//            return modelAndView;
//        }
//        if (end.equals("temp"))
//        {
//            warninginfo.add("未选择目的地");
//            modelAndView.addObject("warn",warninginfo);
//            return modelAndView;
//        }
//        if (start.equals(end))
//        {
//            warninginfo.add("出发地目的地相同");
//            modelAndView.addObject("warn",warninginfo);
//            return modelAndView;
//        }
//        if (!start.equals("C")&&!end.equals("C"))
//        {
//            warninginfo.add("出发地目的地必须有一个包含中国");
//            modelAndView.addObject("warn",warninginfo);
//            return modelAndView;
//        }
//        if (jicang.equals("temp"))
//        {
//            warninginfo.add("未选择机舱类型");
//            modelAndView.addObject("warn",warninginfo);
//            return modelAndView;
//        }
//        if (cusclass.equals("temp"))
//        {
//            warninginfo.add("未选择优惠类型");
//            modelAndView.addObject("warn",warninginfo);
//            return modelAndView;
//        }
//        if (luggage.length()==0)
//        {
//            warninginfo.add("未输入行李重量和规格");
//            modelAndView.addObject("warn",warninginfo);
//            return modelAndView;
//        }
//        if (luggage.length()>0)
//        {
//            String []arr=luggage.split("\\s+");
//            for (String str:arr)
//            {
//                String[]nowarr=str.split("-");
//                if (nowarr.length>4)
//                {
//                    warninginfo.add("行李格式过长");
//                    return modelAndView;
//                }
//                else if(nowarr.length<4){
//                    warninginfo.add("行李格式过短");
//                    return modelAndView;
//                }
//                else {
//                    if (nowarr[0].length()==0)
//                    {
//                        warninginfo.add("有行李没有输入重量");
//                    }
//                    if (nowarr[1].length()==0)
//                    {
//                        warninginfo.add("有行李没有输入长度");
//                    }
//                    if (nowarr[2].length()==0)
//                    {
//                        warninginfo.add("有行李没有输入宽度");
//                    }
//                    if (nowarr[3].length()==0)
//                    {
//                        warninginfo.add("有行李没有输入高度");
//                    }
//                    if (!PutInCheck.isNumeric(nowarr[0]))
//                    {
//                        warninginfo.add("重量包含非法字符");
//                    }
//                    if (!PutInCheck.isNumeric(nowarr[1]))
//                    {
//                        warninginfo.add("长度包含非法字符");
//                    }
//                    if (!PutInCheck.isNumeric(nowarr[2]))
//                    {
//                        warninginfo.add("宽度包含非法字符");
//                    }
//                    if (!PutInCheck.isNumeric(nowarr[3]))
//                    {
//                        warninginfo.add("高度包含非法字符");
//                    }
//                    if (Float.valueOf(nowarr[0])<0)
//                    {
//                        warninginfo.add("有行李质量为负数");
//                    }
//                    if (Float.valueOf(nowarr[1])<0)
//                    {
//                        warninginfo.add("有行李长度为负数");
//                    }
//                    if (Float.valueOf(nowarr[2])<0)
//                    {
//                        warninginfo.add("有行李宽度为负数");
//                    }
//                    if (Float.valueOf(nowarr[3])<0)
//                    {
//                        warninginfo.add("有行李高度为负数");
//                    }
//
//
//                }
//            }
//        }
//        if (ticketvalue.length()==0)
//        {
//            warninginfo.add("未输入票价");
//        }
//        else if (!PutInCheck.isNumeric(ticketvalue))
//        {
//            warninginfo.add("票价包含非法字符");
//        }
//        else if (PutInCheck.isNumeric(ticketvalue))
//        {
//            if (Float.valueOf(ticketvalue)<0)
//            {
//                warninginfo.add("输入票价为负值");
//            }
//        }

        return modelAndView;
    }

}
