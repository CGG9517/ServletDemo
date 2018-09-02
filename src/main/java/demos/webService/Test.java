package demos.webService;

/**
 * @Class: Test
 * @Description:
 * @Author: Jiang Chao
 * @Date: 2018/9/1
 */
public class Test {
    public static void main( String[] args ) {
        MobileCodeWS ws = new MobileCodeWS();


        MobileCodeWSSoap mobileCodeWSSoap = ws.getMobileCodeWSSoap();
        String mobileNum = "15171431527";
        String codeInfo = mobileCodeWSSoap.getMobileCodeInfo(mobileNum, "");

        System.out.println(codeInfo);
    }
}
