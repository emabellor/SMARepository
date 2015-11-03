/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueaderobesa;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import BESA.ExceptionBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.PeriodicGuardBESA;
import BESA.Kernell.Agent.StructBESA;
import BESA.Kernell.System.AdmBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Util.PeriodicDataBESA;
import Logging.*;
import Model.*;
import Carro.*;
import Mundo.*;
import Peaton.AgentePeaton;
import Peaton.EstadoPeaton;
import Peaton.GuardaGetPeatonStatusResult;
import Peaton.GuardaIngresarPeatonResult;
import Peaton.GuardaRemoverPeatonResult;
import Peaton.GuardaUpdatePeatonResult;
import Peaton.GuardaWakeUpPeaton;
import Vigilante.AgenteVigilante;
import Vigilante.EstadoVigilante;
import Vigilante.GuardaUpdateVigilanteResult;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author Mauricio
 */
public class ParqueaderoBESA 
{
    public static int GAME_PERIODIC_TIME = 1000;
    public static int GAME_PERIODIC_DELAY_TIME = 100;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        try
        {
            InicializarAgentes();
        }
        catch(BESA.ExceptionBESA ex)
        {
            ShowFatal("ExceptionBESA: " + ex.getMessage());
        } 
        
    }
    
    static void ShowFatal(String message)
    {
        // create a jframe
        JFrame frame = new JFrame("JOptionPane showMessageDialog example");

        // show a joptionpane dialog using showMessageDialog
        JOptionPane.showMessageDialog(frame, message);
        System.exit(0);
    }
    
    static void InicializarAgentes () throws BESA.ExceptionBESA 
    {
        //Constantes
        int numeroVehiculos = 1;
        int numeroOcupantes = 4;
        int periodoAparicionCarroMs = 2000;
        int periodoActualizacionPeatonMs = 400;
        int periodoActualizacionCarroMs = 200;
        int tiempoFueraPeatonMs = 5000;
        
        int size = 15;
        ClassLogger.LogMsg("Inicio de la aplicacion de parqueaderos");
        
        ClassLogger.LogMsg("Creando administrador BESA");
        AdmBESA admLocal = AdmBESA.getInstance();
                
        ClassLogger.LogMsg("Creando Agente Mundo");
        EstadoMundo estadoMundo = new EstadoMundo(size);
        estadoMundo.tiempoAparicionCarroMs = periodoAparicionCarroMs;
        StructBESA structMundo = new StructBESA();
        structMundo.addBehavior("BehaviorsMundo");
        structMundo.bindGuard("BehaviorsMundo", GuardaUpdateCarro.class);
        structMundo.bindGuard("BehaviorsMundo", GuardaUpdatePeaton.class);
        structMundo.bindGuard("BehaviorsMundo", GuardaIngresarCarro.class);
        structMundo.bindGuard("BehaviorsMundo", GuardaIngresarPeaton.class);
        structMundo.bindGuard("BehaviorsMundo", GuardaGetCarroStatus.class);
        structMundo.bindGuard("BehaviorsMundo", GuardaGetPeatonStatus.class);
        structMundo.bindGuard("BehaviorsMundo", GuardaRemoverCarro.class);
        structMundo.bindGuard("BehaviorsMundo", GuardaRemoverPeaton.class);
        AgenteMundo agenteMundo = new AgenteMundo("WORLD", estadoMundo, structMundo, 0.91);
        agenteMundo.start();
        
        
        for (int i = 0; i < numeroVehiculos; i++)
        {
            List<String> listaOcupantes = new ArrayList<>();
            
            for (int j = 0; j < numeroOcupantes; j++)
            {
                ClassLogger.LogMsg("Creando Agente Peaton. Posicion: " + i + "_" + j);
                EstadoPeaton estadoPeaton = new EstadoPeaton();
                estadoPeaton.tiempoFueraMs = tiempoFueraPeatonMs;
                estadoPeaton.actualizacionMs = periodoActualizacionPeatonMs;
                StructBESA structPeaton = new StructBESA();
                String nombreBehavior = "BehaviorsPeaton";
                structPeaton.addBehavior(nombreBehavior);
                structPeaton.bindGuard(nombreBehavior, GuardaGetPeatonStatusResult.class);
                structPeaton.bindGuard(nombreBehavior, GuardaIngresarPeatonResult.class);
                structPeaton.bindGuard(nombreBehavior, GuardaRemoverPeatonResult.class);
                structPeaton.bindGuard(nombreBehavior, GuardaUpdatePeatonResult.class);
                structPeaton.bindGuard(nombreBehavior, GuardaWakeUpPeaton.class);
                String nombrePeaton = "Peaton_" + i + "_" + j;
                AgentePeaton agentePeaton = new AgentePeaton(nombrePeaton, estadoPeaton, structPeaton, 0.91);
                agentePeaton.start();
                listaOcupantes.add(nombrePeaton);
            }
            
            ClassLogger.LogMsg("Creando Agente Carro. Posicion " + i);
            EstadoCarro estadoCarro = new EstadoCarro();
            estadoCarro.listaOcupantes = listaOcupantes;
            estadoCarro.actualizacionMs = periodoActualizacionCarroMs;
            StructBESA structCarro = new StructBESA();
            String nombreBehavior = "BehaviorsCarro";
            structCarro.addBehavior(nombreBehavior);
            structCarro.bindGuard(nombreBehavior, GuardaUpdateCarroResult.class);
            structCarro.bindGuard(nombreBehavior, GuardaIngresarCarroResult.class);
            structCarro.bindGuard(nombreBehavior, GuardaGetCarroStatusResult.class);
            structCarro.bindGuard(nombreBehavior, GuardaRemoverCarroResult.class);
            structCarro.bindGuard(nombreBehavior, GuardaRetornoPeaton.class);
            String nombreCarro = "Carro" + i;
            AgenteCarro agenteCarro = new AgenteCarro(nombreCarro, estadoCarro, structCarro, 0.91);
            agenteCarro.start();
        }
    }
    
    static void ThreadSleep(int ms)
    {
        try
        {
            Thread.sleep(100);
        }
        catch (InterruptedException ex)
        {
            
        }
    }
}
