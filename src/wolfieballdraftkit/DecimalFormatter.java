/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wolfieballdraftkit;

/**
 *
 * @author louis
 */
public class DecimalFormatter {
    
    public static void main(String args[])
    {
        System.out.println(format(410.4));
    }
    public static double format (Double toFormat)
    {
       String string = toFormat.toString();
       String returnString = "";
       for(int i =0;i<string.length();i++)
       {
           returnString += string.charAt(i);
           if(string.charAt(i)=='.')
           {
              try{
                  returnString += string.charAt(i+1);
                  returnString += string.charAt(i+2);
                  break;
              }
              catch(Exception e)
              {
                  break;
              }
           }
       }
       return Double.parseDouble(returnString);
    }
    
        public static double format3 (Double toFormat)
    {
       String string = toFormat.toString();
       String returnString = "";
       for(int i =0;i<string.length();i++)
       {
           returnString += string.charAt(i);
           if(string.charAt(i)=='.')
           {
              try{
                  returnString += string.charAt(i+1);
                  returnString += string.charAt(i+2);
                  returnString += string.charAt(i+3);
                  break;
              }
              catch(Exception e)
              {
                  break;
              }
           }
       }
       return Double.parseDouble(returnString);
    }
}
