package hyg.model
//package hyg.model
//
//
//import spock.lang.Specification
//import spock.lang.Unroll
//
//class PutInCheckTest extends Specification {
//    @Unroll
//    def "输入合法测试#linetype and #start be #end"() {
//        expect:
//        PutInCheck.incheck(linetype,start,end,jicang,cusclass,vip,luggage,ticktvalue).size()==res
//        where:
//        linetype|start|end|jicang|cusclass|vip|luggage|ticktvalue||res
//        "temp"|"C"|"AM"|"T"|"A"|"J"|"20/20/20/20"|"1000"||1 //乘客不进行航班选择
//        "out"|"C"|"J"|"T"|"A"|"J"|"20/20/20/20"|"1000"||1  //起点和终点都不包含中国
//        "out"|"C"|"J"|"temp"|"A"|"J"|"20/20/20/20"|"1000"||0 //没有输入舱位类型
//        "out"|"C"|"J"|"T"|"temp"|"J"|"20/20/20/20"|"1000"||0 //未选择乘客类型
//        "out"|"C"|"J"|"T"|"A"|"temp"|"20/20/20/20"|"1000"||0 //没选择优惠类型
//        "out"|"C"|"J"|"T"|"A"|"J"|"-20/20/20/20"|"1000"||0 //行李质量为负数
//        "out"|"C"|"J"|"T"|"A"|"J"|"20*/20/20/20"|"1000"||0 //重量包含非法字符
//        "out"|"C"|"J"|"T"|"A"|"J"|"/20/20/20"|"1000"||0  //未输入重量
//        "out"|"C"|"J"|"T"|"A"|"J"|"20/20*/20/20"|"1000"||0  //长度包含非法字符
//        "out"|"C"|"J"|"T"|"A"|"J"|"20/-20/20/20"|"1000"||0   //长度为负数
//        "out"|"C"|"J"|"T"|"A"|"J"|"20//20/20"|"1000"||0   //未输入长度
//        "out"|"C"|"J"|"T"|"A"|"J"|"20/20/-20/20"|"1000"||0   //宽度为负数
//        "out"|"C"|"J"|"T"|"A"|"J"|"20/20/20*/20"|"1000"||0   //宽度包含非法字符
//        "out"|"C"|"J"|"T"|"A"|"J"|"20/20/20/-20"|"1000"||0   //高度为负数
//        "out"|"C"|"J"|"T"|"A"|"J"|"20/20/20/20*"|"1000"||0   //高度包含非法字符
//        "out"|"C"|"J"|"T"|"A"|"J"|"20/20/20/"|"1000"||0   //未输入高度
//        "out"|"C"|"J"|"T"|"A"|"J"|"20/20/20/20"|""||0    //未输入票价
//        "out"|"C"|"J"|"T"|"A"|"J"|"20/20/20/20"|"-1000"||0   //票价为负数
//        "out"|"C"|"J"|"T"|"A"|"J"|"20/20/20/20"|"1000**"||0   //票价包含非法字符
//
//    }
////    def "理解上下文提示"()
////    {
////        when "测试没有选择航班,没有选择舱位的测试用例":
////            ArrayList<String str=PutInCheck.incheck(linetype,start,end,jicang,cusclass,vip,luggage,ticktvalue)
////
////
////
////    }
//    @Unroll
//    def "一般规定测试 #linetype 起点#start终点#end 行李 #luggage"()
//    {
//        expect:
//        CusTicker custicker=PutInCheck.Getticketer(linetype,start,end,jicang,cusclass,vip,luggage,ticktvalue)
//        Check check=new Check(custicker)
//        check.generacheck()==res
//
//        where:
//        linetype|start|end|jicang|cusclass|vip|luggage|ticktvalue||res
//        "in"|"C"|"C"|"T"|"A"|"J"|"60/20/20/20"|"1000"||false //国内无法托运超重
//        "in"|"C"|"C"|"T"|"A"|"J"|"30/20/20/110"|"1000"||false //国内无法托运 超尺寸
//
//        "out"|"C"|"AME"|"T"|"A"|"J"|"46/20/20/20"|"1000"||false //涉及美国航线 超重
//        "out"|"C"|"AME"|"T"|"A"|"J"|"45/20/20/20"|"1000"||true //涉及美国航线 不超重
//
//        "out"|"C"|"J"|"T"|"A"|"J"|"33/20/20/20"|"1000"||false //不涉及美国航线超重
//        "out"|"C"|"J"|"T"|"A"|"J"|"32/20/20/20"|"1000"||true //不涉及美国航线超重
//
//    }
//
////    def "区域设置测试"()
////    {
////        CusTicker cusTicker=Mock()
////        cusTicker.
////    }
//
//    @Unroll
//    def "免费测试机舱 #jicang 会员#vip 行李#luggage"()
//    {
//        expect:
//        CusTicker custicker=PutInCheck.Getticketer(linetype,start,end,jicang,cusclass,vip,luggage,ticktvalue)
//        Check check=new Check(custicker)
//        check.freecheck()==res
//        where:
//        linetype|start|end|jicang|cusclass|vip|luggage|ticktvalue||res
////        "out"|"C"|"AME"|"T"|"A"|"W"|"20/20/20/20 20/20/20/20 20/20/20/20"|"1000"||true //区域一公务舱无卡全符合
////        "out"|"C"|"AME"|"J"|"A"|"W"|"20/20/20/20 20/20/20/20"|"1000"||true  //经济舱全符合
////        "out"|"C"|"AME"|"J"|"A"|"W"|"20/20/20/20 20/20/20/20 20/20/20/20"|"1000"||true //超一件试试
////        "out"|"C"|"AME"|"T"|"A"|"J"|"20/20/20/20 20/20/20/20 20/20/20/20 20/20/20/20"|"1000"||true//金卡算上加一件
////        "out"|"C"|"AME"|"T"|"A"|"J"|"20/20/20/20 20/20/20/20 20/20/20/20"|"1000"||true
//        // "in"|"C"|"C"|"T"|"A"|"J"|"60/20/20/20"|"1000"||false //国内无法托运超重
////        "in"|"C"|"C"|"T"|"A"|"J"|"30/20/20/110"|"1000"||false //国内无法托运 超尺寸
//
////        "out"|"C"|"AME"|"T"|"A"|"J"|"46/20/20/20"|"1000"||false //涉及美国航线 超重
////        "out"|"C"|"AME"|"T"|"A"|"J"|"45/20/20/20"|"1000"||true //涉及美国航线 不超重
////
////        "out"|"C"|"J"|"T"|"A"|"J"|"33/20/20/20"|"1000"||false //不涉及美国航线超重
////        "out"|"C"|"J"|"T"|"A"|"J"|"32/20/20/20"|"1000"||false //不涉及美国航线超重
//        "out"|"C"|"AME"|"T"|"A"|"W"|"40/20/20/20"|"1000"||true
//    }
//
//    @Unroll
//    def"不符合规定的行李测试会员#vip 行李规格数目 #luggage"()
//    {
//        expect:
//        CusTicker custicker=PutInCheck.Getticketer(linetype,start,end,jicang,cusclass,vip,luggage,ticktvalue)
//        Check check=new Check(custicker)
//        check.checkover()
//        check.getFinalcost()==res
//        print(check.getWarnningInfo())
//        where:
//        linetype|start|end|jicang|cusclass|vip|luggage|ticktvalue||res
//        "out"|"C"|"AME"|"T"|"A"|"W"|"40/20/20/20"|"1000"||2000
//        //试着输出错误的和正确的
////        "out"|"C"|"AME"|"T"|"A"|"W"|"20/20/20/20 20/20/20/20 20/20/20/20  20/20/20/20 20/20/20/20"|"1000"||4000 //区域头等舱超两件
////        "out"|"C"|"AME"|"T"|"A"|"W"|"20/20/20/20 20/20/20/20 20/20/20/20  20/20/20/20 20/20/20/20"|"1000"||3000 //区域头等舱超两件
////
////        //看看区域一超重和超尺寸
////        "out"|"C"|"AME"|"T"|"A"|"W"|"20/20/20/20 20/20/20/20 20/20/20/20  35/20/20/20"|"1000"||1000  //超重一件
////        "out"|"C"|"AME"|"T"|"A"|"W"|"20/20/20/20 20/20/20/20 20/20/20/20  30/100/100/20"|"1000"||1000 //超尺寸
////
////        "out"|"C"|"AME"|"T"|"A"|"W"|"20/20/20/20 20/20/20/20 35/20/20/20  35/20/20/20"|"1000"||1000  //超重两件 并且超件一件
//
//        //看看国内的情况
////        "in"|"C"|"C"|"G"|"A"|"W"|"35/20/20/20"|"1000"||1000  //公务舱超重5千克
////        //换成头等舱看看
////        "in"|"C"|"C"|"T"|"A"|"W"|"35/20/20/20"|"1000"||1000  //同重量头等
////        //弄个金卡看看
////        "in"|"C"|"C"|"G"|"A"|"J"|"35/20/20/20"|"1000"||1000  //公务舱超重5千克
//
//
//
//    }
////    def "从csv读取"()
////    {
////        given:
////            CSVParser csvParser=new CSVParser()
////
////        expect:
////        def fields = csvParser.parseLine(line as String)
////
////        def linetype=fields[0].toString()
////
////        def start=fields[1].toString()
////        def end =fields[2].toString()
////        def jicang=fields[3].toString()
////        def cusclass=fields[4].toString()
////        def vip=fields[5].toString()
////        def luggage=fields[6].toString()
////        def ticktvalue=(fields[7]).toString()
////        def exres=Integer.valueOf(fields[8])
////        CusTicker custicker=PutInCheck.Getticketer(linetype,start,end,jicang,cusclass,vip,luggage,ticktvalue)
////        Check check=new Check(custicker)
////        check.generacheck()==exres
////
////        where:
////        line << new Iterable() {
////            @Override
////            Iterator iterator() {
////                return new Scanner("")
////            }
////        }
////
////    }
////    def "duqu"()
////    {
////        given:
////        File file = new File("D:\\IdeaProjects\\ll\\src\\test\\groovy\\hyg\\model\\test.csv");
////        FileReader fReader = new FileReader(file);
////        def reader = new CSVReader(fReader)
////        def fields
////        def linetype
////        def start
////        def end
////        def jicang
////        def cusclass
////        def vip
////        def luggage
////        def ticktvalue
////        def exres
////        expect:
////        while ((fields = reader.readNext()) != null) {
////            linetype=fields[0].toString()
////
////            start=fields[1].toString()
////            end =fields[2].toString()
////            jicang=fields[3].toString()
////            cusclass=fields[4].toString()
////            vip=fields[5].toString()
////            luggage=fields[6].toString()
////            ticktvalue=(fields[7]).toString()
////            exres=Integer.valueOf(fields[8])
////            PutInCheck.incheck(linetype,start,end,jicang,cusclass,vip,luggage,ticktvalue).size()==exres
////
////        }
////        given:
////        lineList = new File("D:\\IdeaProjects\\ll\\src\\test\\groovy\\hyg\\model\\test.csv").readLines();
////        expect:
////
////        lineList.each {
////            println it.toUpperCase();
////            lineList[0]=="out"
////        }eck(linetype,start,end,jicang,cusclass,vip,luggage,ticktvalue).size()==exres
////
//
//
//}
