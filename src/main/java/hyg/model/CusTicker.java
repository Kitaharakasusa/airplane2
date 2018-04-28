package hyg.model;

import hyg.Unit.InOrOut;
import hyg.Unit.Place;
import hyg.Unit.TickClass;
import hyg.Unit.Vip;

import java.util.ArrayList;

public class CusTicker {
    private TickClass tickettype;//票类型,是头等舱还是什么
    private ArrayList<Bag>baglist; //票对应的行李列表
    private Vip vip; //会员类型
    private int addnum; //会员类型对应的可以加的行李数目
    private int addweight;//会员类型可以增加的行李限额
    private InOrOut linetype;//航线类型 国际 国内
    private String Area; //所属区域
    private float tickervalue;//票价
    private Place start;//出发地
    private Place destination;//目的地
    private void setarea() //根据出发地和目的地设置所属区域
    {
        if ((start== Place.Japan||start== Place.America||start== Place.Auckland||start== Place.Dubai
            ||start== Place.Korea||start== Place.Miguo)&&destination== Place.China)
        {
                this.setArea("1");

        }
        else if (start== Place.China&&(destination== Place.Miguo||destination== Place.Japan||destination== Place.America||destination== Place.Auckland||
        destination== Place.Dubai))
        {
            this.setArea("1");
        }
        else if (start== Place.Singapore&&destination== Place.China)
        {
            this.setArea("1");
        }
        else if ((start== Place.MidWestAsia&&destination== Place.China)||(start== Place.China&&destination== Place.MidWestAsia))
        {
            this.setArea("2");
        }
        else if ((destination== Place.Nairobi&&start== Place.China)||(destination== Place.China&&start== Place.Nairobi))
        {
            this.setArea("3");
        }
        else if ((destination==Place.China_LanWU&&start==Place.Dubai)||(destination==Place.Dubai&&start==Place.China_LanWU))
        {
            this.setArea("5");
        }
        else {
            this.setArea("4");
        }

    }
    private void setVip()
    {
       if (vip== Vip.GOLD_SUPPER)
       {
           this.addnum=1;
           this.addweight=20;

       }
       else if (vip== Vip.SILIVER)
       {
           this.addnum=1;
           this.addweight=10;
       }
       else if (vip== Vip.OVERSEA_LABOUR)
       {
           this.addnum=1;
           this.addweight=0;

       }
       else {
           this.addweight=0;
           this.addnum=0;
       }
    }
    public CusTicker(InOrOut linetype, Place start, Place destination, TickClass tickettype, ArrayList<Bag> bagnum, Vip vip, float piaojia)
    {
        this.linetype=linetype; //是国际航线还是国内航线
        //this.Area=area;    //涉及地区
        this.tickettype=tickettype; //是头等舱还是什么类型
        this.baglist=bagnum;//背包列表
        this.vip=vip;
        this.tickervalue=piaojia; //票价
        this.start=start;
        this.destination=destination;
        this.setarea();
        this.setnum_weight();
    }


    public float getTickervalue() {
        return tickervalue;
    }

    public InOrOut getLinetype() {
        return linetype;
    }

    public void setLinetype(InOrOut linetype) {
        this.linetype = linetype;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }
    private void setnum_weight()
    {
        if (vip== Vip.GOLD_SUPPER)
        {
            addnum=0;
            addweight=20;
        }
        else if (vip== Vip.SILIVER)
        {
            addweight=10;
            addnum=0;
        }
        else if (vip== Vip.OVERSEA_LABOUR)
        {
            addnum=1;
            addweight=0;
        }
        else {
            addnum=0;
            addweight=0;
        }
    };
    public Vip getVip() {
        return vip;
    }

    public Place getStart() {
        return start;
    }

    public Place getDestination() {
        return destination;
    }

    public int getAddnum() {
        return addnum;
    }

    public int getAddweight() {
        return addweight;
    }

    public ArrayList<Bag> getBaglist() {
        return baglist;
    }

    public void setBaglist(ArrayList<Bag> baglist) {
        this.baglist = baglist;
    }

    public TickClass getTickettype() {
        return tickettype;
    }
    public void setTickettype(TickClass tickettype) {
        this.tickettype = tickettype;
    }
}
