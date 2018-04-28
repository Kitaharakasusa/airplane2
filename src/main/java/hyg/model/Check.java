package hyg.model;

import hyg.Unit.InOrOut;
import hyg.Unit.Place;
import hyg.Unit.TickClass;

import java.util.ArrayList;

public class Check {
    //private Air ticker;
    private CusTicker ticker;
    private int addnum;
    private int addweight;
    private ArrayList<String> WarnningInfo=new ArrayList<String>();
    private float overvalue=0;//要多付多少钱;
    public Check(CusTicker ticker)
    {
        this.ticker=ticker;
        this.addnum=ticker.getAddnum();
        this.addweight=ticker.getAddweight();
    }
    //两个检查超重的函数
    private boolean caculateover(int bagnum,float oneoeverweight,float oneovervalue,float twoovervalue,float twooverweight,
                              float overwmin,float overwmax,float oversizevalue,float overwvalue)
    {
        //超件检查
        int num=ticker.getBaglist().size();
        int nowbagnum=bagnum;
        bagnum=bagnum+addnum; //真正的可以托运的件数
        float freeerealweight=oneoeverweight;
        int overnum=ticker.getBaglist().size()-bagnum; //看超了几件
        if (overnum>=1)
        {
           overvalue+=oneovervalue;
        }
        if (overnum>=2)
        {
            for(int i=1;i<overnum;i++)
            overvalue+=twoovervalue;
        }

        //超尺寸
        for (int i=0;i<ticker.getBaglist().size();i++)
        {
            Bag bag=ticker.getBaglist().get(i);
            float size=bag.getSize_height()+bag.getSize_length()+bag.getSize_width();

            if (size>=159&&size<=300)
            {
                overvalue+=oversizevalue;
            }
            else if (size>300)
            {
                WarnningInfo.add("尺寸太大了 托运吧");
                return false;
            }

        }
        //超重检查 这里要区分免费行李和超件行李重量标准不同
        //免费行李
        for(int i=0;i<bagnum;i++)
        {
            if (i<=ticker.getBaglist().size()-1) {
                float nowweight = ticker.getBaglist().get(i).getWeight();
                if (nowweight > overwmin && nowweight < overwmax) {
                    overvalue += overwvalue;
                } else if (nowweight > overwmax) {
                    String nowinfo = String.format("第 %d 个免费行李太重啦,托运去吧", i);
                    WarnningInfo.add(nowinfo);
                    return false;
                }
            }

        }
        //超件行李
        if (bagnum<ticker.getBaglist().size())
        {
            for (int j=bagnum;j<ticker.getBaglist().size();j++)
            {
                float oweight=ticker.getBaglist().get(j).getWeight();
                if (oweight>overwmin&&oweight<=overwmax)
                {
                    overvalue+=overwvalue;
                }
                else if (oweight>overwmax)
                {
                    String str=String.format("第 %d 件超件的行李重的过分啦!",j);
                    WarnningInfo.add(str);
                    return false;
                }
            }
        }
        return true;

    }
    private boolean caculateover(int bagnum,float oneoeverweight,float oneovervalue,float twoovervalue,float twooverweight,
                              float overwmin,float overwmid,float overwmax,float oversizevalue,float overwvalue1,float overwvalue2)
    {
        //超件检查
        int num=ticker.getBaglist().size();
        bagnum=bagnum+addnum; //真正的可以托运的件数
        int overnum=ticker.getBaglist().size()-bagnum; //看超了几件
        if (overnum>=1)
        {
            overvalue+=oneovervalue;

        }
        if (overnum>=2)
        {
            for(int i=1;i<overnum;i++)
                overvalue+=twoovervalue;

        }

        //超尺寸
        for (int i=0;i<ticker.getBaglist().size();i++)
        {
            Bag bag=ticker.getBaglist().get(i);
            float size=bag.getSize_height()+bag.getSize_length()+bag.getSize_width();
//            float weight=bag.getWeight();
            if (size>=159&&size<=300)
            {
                overvalue+=oversizevalue;
            }
            else if (size>300)
            {
                WarnningInfo.add("尺寸太大了 托运吧");
                return false;
            }

        }
        //超重检查 这里要区分免费行李和超件行李重量标准不同
        //免费行李
        for(int i=0;i<bagnum;i++)
        {
            if (i<=ticker.getBaglist().size()-1) {
                float nowweight = ticker.getBaglist().get(i).getWeight();
                if (nowweight > overwmin && nowweight < +overwmid) {
                    overvalue += overwvalue1;
                } else if (nowweight > overwmid && nowweight <= overwmax) {
                    overvalue += overwvalue2;
                } else if (nowweight > overwmax) {
                    WarnningInfo.add("你免费行李里有的太重了,托运吧");
                    return false;
                }
            }
        }
        //超件行李
        if (bagnum<ticker.getBaglist().size())
        {
            for (int j=bagnum;j<ticker.getBaglist().size();j++)
            {
                float oweight=ticker.getBaglist().get(j).getWeight();
                if (oweight>overwmin&&oweight<=overwmid)
                {
                    overvalue+=overwvalue1;
                }
                else if (oweight>overwmid&&oweight<=overwmax)
                {
                    overvalue+=overwvalue2;
                }
                else if (oweight>overwmax)
                {
                    String str=String.format("你第 %d 件超件行李太重了",j);
                    WarnningInfo.add(str);
                    return false;
                }
            }
        }
        return true;
    }
    private boolean bag_num_weightcheck(int num,int weight )
    {
        int count=0;
        for(int i=0;i<ticker.getBaglist().size();i++)
        {
            Bag a=ticker.getBaglist().get(i);
            if (a.getWeight()<=weight)
            {
                count++;
            }
            if (a.getWeight()>weight)
            {
                return false;
            }

        }
        if (count<=num)
        {
            return true;
        }
        return false;
    }
    private boolean bag_sizecheck()
    {
        if (ticker.getBaglist().get(0).getSize_width()<=40&&ticker.getBaglist().get(0).getSize_length()<=60&&
                ticker.getBaglist().get(0).getSize_height()<=100)
        {
            return true;
        }
        else
        {
            WarnningInfo.add("国内行李超尺寸");
            return false;
        }
    }
    private boolean bag_size_check_out()
    {
        for (int i=0;i<ticker.getBaglist().size();i++)
        {
            Bag bag=ticker.getBaglist().get(i);
            if (bag.getSize_height()+bag.getSize_length()+bag.getSize_width()>158)
            {
                return false;
            }
        }
        return true;
    }
    //一般规定
    public boolean generacheck() {

        if (ticker.getLinetype() == InOrOut.InChina) {
            Bag bag = ticker.getBaglist().get(0);
            if (ticker.getBaglist().size() == 1) {
                if (bag.getWeight() > 50 || bag.getSize_width() > 40 || bag.getSize_length() > 60 || bag.getSize_height() > 100) {
                    this.WarnningInfo.add("重量尺寸太大了 走货运吧");
                    return false;
                }
            } else {
                this.WarnningInfo.add("行李有点多,国内只能有一件哦");
                return false;
            }
        } else if (ticker.getLinetype() == InOrOut.OunChina) {
            for (int i = 0; i < ticker.getBaglist().size(); i++) {
                Bag bag = ticker.getBaglist().get(i);
                if (ticker.getStart() == Place.Miguo || ticker.getDestination() == Place.Miguo) {
                    //|| (bag.getSize_width() + bag.getSize_height() + bag.getSize_length() > 158
                    if (bag.getWeight() > 45) {
                        WarnningInfo.add("涉及美国航线有行李太重啦,走货运吧");
                        return false;
                    }
                    //&& (bag.getSize_length() + bag.getSize_height() + bag.getSize_width()<= 158
                } else if (bag.getWeight() > 32) {
                    WarnningInfo.add("不涉及美国航线有行李太重");
                    return false;
                }
            }

        }
        return true;
    }
    //免费行李检查
    public boolean freecheck()
    {
        if(ticker.getLinetype()== InOrOut.InChina) {
            if (ticker.getTickettype()== TickClass.TouDeng) {
                return (bag_num_weightcheck(1,40+addweight))&&bag_sizecheck();
            }
            else if (ticker.getTickettype()== TickClass.Gongwu)
            {
                return (bag_num_weightcheck(1,30+addweight))&&bag_sizecheck();
            }
            else if (ticker.getTickettype()== TickClass.Jingji)
            {
                return (bag_num_weightcheck(1,20+addweight))&&bag_sizecheck();
            }
            else if (ticker.getTickettype()== TickClass.Wuzuoertong)
            {
                return (bag_num_weightcheck(1,10))&&bag_sizecheck();
            }

        }
        else if (ticker.getLinetype()== InOrOut.OunChina)
        {
            if (ticker.getArea().equals("1")||ticker.getArea().equals("3")) {
                if (ticker.getTickettype()== TickClass.TouDeng)
                    return bag_num_weightcheck(3+addnum, 32)&&bag_size_check_out();
                else if (ticker.getTickettype()== TickClass.Gongwu)
                    return bag_num_weightcheck(2+addnum, 32)&&bag_size_check_out();
                else if (ticker.getTickettype()== TickClass.Jingji||ticker.getTickettype()== TickClass.Mingzhu)
                    return bag_num_weightcheck(2+addnum, 23)&&bag_size_check_out();
                else if (ticker.getTickettype()== TickClass.Wuzuoertong)
                {
                    if (ticker.getBaglist().size()==1)
                    {
                        if (ticker.getBaglist().size()!=1)
                            return false;
                        else if (ticker.getBaglist().get(0).getWeight()>10)
                            return false;
                        else if (ticker.getBaglist().get(0).getSize_width()+ticker.getBaglist().get(0
                        ).getSize_height()+ticker.getBaglist().get(0).getSize_length()>115)
                            return false;

                    }
                    return true;
                }
            }
            if (ticker.getArea().equals("2"))
            {
                if (ticker.getTickettype()== TickClass.TouDeng)
                    return bag_num_weightcheck(3+addnum, 32)&&bag_size_check_out();
                else if (ticker.getTickettype()== TickClass.Gongwu)
                    return bag_num_weightcheck(2+addnum, 32)&&bag_size_check_out();
                else if (ticker.getTickettype()== TickClass.Jingji||ticker.getTickettype()== TickClass.Mingzhu)
                    return bag_num_weightcheck(1+addnum, 32)&&bag_size_check_out();
                else if (ticker.getTickettype()== TickClass.Wuzuoertong)
                {
                    if (ticker.getBaglist().size()==1)
                    {
                        if (ticker.getBaglist().size()!=1)
                            return false;
                        else if (ticker.getBaglist().get(0).getWeight()>10)
                            return false;
                        else if (ticker.getBaglist().get(0).getSize_width()+ticker.getBaglist().get(0
                        ).getSize_height()+ticker.getBaglist().get(0).getSize_length()>115)
                            return false;

                    }
                    return true;
                }
            }
            if (ticker.getArea().equals("5"))
            {

                if (ticker.getTickettype()== TickClass.TouDeng)
                    return bag_num_weightcheck(3+addnum, 32)&&bag_size_check_out();
                else if (ticker.getTickettype()== TickClass.Gongwu)
                    return bag_num_weightcheck(2+addnum, 32)&&bag_size_check_out();
                else if (ticker.getTickettype()== TickClass.Mingzhu)
                    return bag_num_weightcheck(1+addnum, 32)&&bag_size_check_out();
                else if (ticker.getTickettype()== TickClass.Jingji)
                    return bag_num_weightcheck(1+addnum, 23)&&bag_size_check_out();
                else if (ticker.getTickettype()== TickClass.Wuzuoertong)
                {
                    if (ticker.getBaglist().size()==1)
                    {
                        if (ticker.getBaglist().size()!=1)
                            return false;
                        else if (ticker.getBaglist().get(0).getWeight()>10)
                            return false;
                        else if (ticker.getBaglist().get(0).getSize_width()+ticker.getBaglist().get(0
                        ).getSize_height()+ticker.getBaglist().get(0).getSize_length()>115)
                            return false;

                    }
                    return true;
                }
            }
            if (ticker.getArea().equals("4"))
            {
                if (ticker.getTickettype()== TickClass.TouDeng)
                    return bag_num_weightcheck(3+addnum, 32)&&bag_size_check_out();
                else if (ticker.getTickettype()== TickClass.Gongwu)
                    return bag_num_weightcheck(3+addnum, 23)&&bag_size_check_out();
                else if (ticker.getTickettype()== TickClass.Jingji)
                    return bag_num_weightcheck(1+addnum, 23)&&bag_size_check_out();
                else if (ticker.getTickettype()== TickClass.Mingzhu)
                    return bag_num_weightcheck(2+addnum,23)&&bag_size_check_out();
                else if (ticker.getTickettype()== TickClass.Wuzuoertong)
                {
                    if (ticker.getBaglist().size()==1)
                    {
                        if (ticker.getBaglist().size()!=1)
                            return false;
                        else if (ticker.getBaglist().get(0).getWeight()>10)
                            return false;
                        else if (ticker.getBaglist().get(0).getSize_width()+ticker.getBaglist().get(0
                        ).getSize_height()+ticker.getBaglist().get(0).getSize_length()>115)
                            return false;

                    }
                    return true;
                }
            }
//            if (ticker.getArea().equals("5"))
//            {
//                if (ticker.getTickettype().equals("头等舱"))
//                    return bag_num_weightcheck(3+addnum, 32+addweight);
//                else if (ticker.getTickettype().equals("公务舱"))
//                    return bag_num_weightcheck(2+addnum, 32+addweight);
//                else if (ticker.getTickettype().equals("经济舱"))
//                    return bag_num_weightcheck(1+addnum, 23+addweight);
//                else if (ticker.getTickettype().equals("不占座婴儿"))
//                {
//                    if (ticker.getBaglist().size()==1)
//                    {
//                        if (ticker.getBaglist().size()!=1)
//                            return false;
//                        else if (ticker.getBaglist().get(0).getWeight()>10)
//                            return false;
//                        else if (ticker.getBaglist().get(0).getSize_width()+ticker.getBaglist().get(0
//                        ).getSize_height()+ticker.getBaglist().get(0).getSize_length()>115)
//                            return false;
//
//                    }
//                    return true;
//                }
//            }
        }
        return false;
    }

    //超重行李计算金额
    public void checkover() {
        float over=0;
        Bag nowbag=ticker.getBaglist().get(0);
        float nowweight=nowbag.getWeight();
        if (ticker.getLinetype()== InOrOut.InChina) {
            if (ticker.getTickettype()== TickClass.TouDeng) {
                over=nowweight-(40+addweight);
                if (over>0)
                {
                    overvalue+=over*ticker.getTickervalue()*0.015;
                }
            } else if (ticker.getTickettype()== TickClass.Gongwu) {
                over=nowweight-(30+addweight);
                if (over>0)
                {
                    overvalue+=over*ticker.getTickervalue()*0.015;
                }
            } else if (ticker.getTickettype()== TickClass.Jingji) {
                over=nowweight-(20+addweight);
                if (over>0)
                {
                    overvalue+=over*ticker.getTickervalue()*0.015;
                }

            } else if (ticker.getTickettype()== TickClass.Wuzuoertong) {
                over=nowweight-(10);
                if (over>0)
                {
                    overvalue+=over*ticker.getTickervalue()*0.015;
                }
            }
        } else if (ticker.getLinetype()== InOrOut.OunChina) {
            if (ticker.getArea().equals("1")) {
                if (ticker.getTickettype()== TickClass.TouDeng) {
                    caculateover(3, 32, 1000, 2000, 32,
                            32, 45, 1000, 3000);
                } else if (ticker.getTickettype()== TickClass.Gongwu) {
                    caculateover(2, 32, 1000, 2000, 32,
                            32, 45, 1000, 3000);
                } else if (ticker.getTickettype()== TickClass.Mingzhu || ticker.getTickettype()== TickClass.Jingji) {
                    caculateover( 2, 23, 1000, 2000, 23,
                            23, 32, 45, 1000, 1000, 3000);
                }
            } else if (ticker.getArea().equals("2")) {
                if (ticker.getTickettype()== TickClass.TouDeng) {
                    caculateover(3, 32, 450, 1300, 32,
                            32, 45, 1000, 3000);
                } else if (ticker.getTickettype()== TickClass.Gongwu) {
                    caculateover( 2, 32, 1000, 2000, 32,
                            32, 45, 1000, 3000);
                } else if (ticker.getTickettype()== TickClass.Mingzhu || ticker.getTickettype()== TickClass.Jingji) {
                    caculateover(1, 32, 450, 1300, 32,
                            32, 45, 1000, 3000);
                }
            } else if (ticker.getArea().equals("3")) {

                if (ticker.getTickettype()== TickClass.TouDeng) {
                    caculateover( 3, 32, 1000, 2000, 32,
                            32, 45, 1000, 3000);
                } else if (ticker.getTickettype()== TickClass.Gongwu) {
                    caculateover(2, 32, 1000, 2000, 32,
                            32, 45, 1000, 3000);
                } else if (ticker.getTickettype()== TickClass.Mingzhu || ticker.getTickettype()== TickClass.Jingji) {
                    caculateover(2, 23, 1000, 2000, 23,
                            23, 32, 45, 1000, 2000, 3000);
                }
            } else if (ticker.getArea().equals("4")) {

                if (ticker.getTickettype()== TickClass.TouDeng) {
                    caculateover( 3, 32, 450, 1300, 32,
                            32, 45, 1000, 3000);
                } else if (ticker.getTickettype()== TickClass.Gongwu) {
                    caculateover(3, 32, 450, 1300, 32,
                            32, 45, 1000, 3000);
                } else if (ticker.getTickettype()== TickClass.Mingzhu) {
                    caculateover( 2, 23, 450, 1300, 23,
                            23, 32, 45, 1000, 1000, 3000);
                } else if (ticker.getTickettype()== TickClass.Jingji) {
                    caculateover( 1, 23, 450, 1300, 23,
                            23, 32, 45, 1000, 1000, 3000);
                }
            }
            else if (ticker.getArea().equals("5")) {

                if (ticker.getTickettype()== TickClass.TouDeng) {
                    caculateover( 3, 32, 1000, 2000, 32,
                            32, 45, 1000, 3000);
                } else if (ticker.getTickettype()== TickClass.Gongwu) {
                    caculateover(3, 32, 1000, 2000, 32,
                            32, 45, 1000, 3000);
                } else if (ticker.getTickettype()== TickClass.Mingzhu) {
                    caculateover( 1, 32, 1000, 2000, 23,
                            23, 32, 45, 1000, 1000, 3000);
                } else if (ticker.getTickettype()== TickClass.Jingji) {
                    caculateover( 1, 23, 1000, 2000, 23,
                            23, 32, 45, 1000, 1000, 3000);
                }
            }

        }
    }

    public float getOvervalue() {
        return overvalue;
    }
    public float getFinalcost()
    {
        return ticker.getTickervalue()+overvalue;
    }
    public ArrayList<String> getWarnningInfo() {
        return WarnningInfo;
    }

    public void setAddnum(int addnum) {
        this.addnum = addnum;
    }

    public void setAddweight(int addweight) {
        this.addweight = addweight;
    }




    public static void main(String args[])
    {

    }
}
