package in.solocrew.cms.util;

public class Util {

    public double convertToDouble(String value){
        try{
            return Double.parseDouble(value);
        }
        catch (Exception e){
            return 0;
        }
    }

    public int convertToInt(String value){
        try{
            return Integer.parseInt(value);
        }
        catch (Exception e){
            return 0;
        }
    }
}
