public class StringSplitter {
    public static void main(String args[]) {
      String testStr  = "[ REQUEST : GfaStripRequest( reqid=123, accountclass=TNT, eventtype=INTR)][ RESPONSE : Gfastripresponse(responseStatus=Succ, errorcode=, interest=0.000)]";
      //System.out.println(testStr);
      String[] arrOfStr = testStr.split("\\[ REQUEST : GfaStripRequest\\( ", -2);
      String a = arrOfStr[1];
      //System.out.println(a);
      
      String[] arrOfStr1 = a.split("\\)\\]\\[ RESPONSE : Gfastripresponse\\(", -2);
      String req = arrOfStr1[0];
      String res = arrOfStr1[1];
      
      System.out.println(req);
      
      res = res.replaceAll("\\)\\]", "");
      System.out.println(res);
      
    }
}
