/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logging;

public class ClassLogger 
{
    private ClassLogger()
    {
        //Clase Privada
    }
    
    
    public static void LogMsg (String message)
    {
        LogLevel level = LogLevel.DEBUG;
        LogMsg(message, level);
    }
    
    public static void LogMsg (String message, LogLevel level)
    {
        System.out.println(level.name() + ": "+ message);
    }
}
