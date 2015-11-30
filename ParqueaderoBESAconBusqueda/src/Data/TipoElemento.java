/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

public enum TipoElemento
{
    Auto,
    Peaton,
    Calle,
    Anden,
    Bahia,
    Vigilante,
    Taquilla,
    Ninguno;
    
    public static boolean IsAgent(TipoElemento agente)
    {
        switch(agente)
        {
            case Auto:
            case Peaton:
            case Vigilante:
                return true;
            default:
                return false;
        }
    }
}