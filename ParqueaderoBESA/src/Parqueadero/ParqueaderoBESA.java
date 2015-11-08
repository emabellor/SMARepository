/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parqueadero;

import Mundo.EstadoPiso;
import Mundo.AgentePiso;
import Mundo.GuardaGetPeatonStatus;
import Mundo.GuardaIngresarPeaton;
import Mundo.GuardaGetCarroStatus;
import Mundo.GuardaUpdateCarro;
import Mundo.GuardaRemoverPeaton;
import Mundo.GuardaIngresarCarro;
import Mundo.GuardaUpdatePeaton;
import Mundo.GuardaRemoverCarro;
import Mundo.PisoA.EstadoPisoA;
import Mundo.PisoB.EstadoPisoB;
import Mundo.PisoC.EstadoPisoC;
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
        ClassLogger.LogMsg("Probando commit parqueaderoBesa");
        
        //Constantes
        int numeroPisos = 5;
        int numeroVehiculos = 3;
        int numeroOcupantes = 4;
        int periodoAparicionCarroMs = 2000;
        int periodoActualizacionPeatonMs = 400;
        int periodoActualizacionCarroMs = 200;
        int tiempoFueraPeatonMs = 5000;
        
        int size = 15;
        ClassLogger.LogMsg("Inicio de la aplicacion de parqueaderos");
        
        ClassLogger.LogMsg("Creando administrador BESA");
        AdmBESA admLocal = AdmBESA.getInstance();
                
        if(numeroPisos > 1)
        {
            ClassLogger.LogMsg("Creando Agente PISO tipo-A");
            EstadoPisoA estadoPisoA = new EstadoPisoA(size);
            estadoPisoA.tiempoAparicionCarroMs = periodoAparicionCarroMs;
            StructBESA structPisoA = new StructBESA();
            structPisoA.addBehavior("BehaviorsPisoA");
            structPisoA.bindGuard("BehaviorsPisoA", GuardaUpdateCarro.class);
            structPisoA.bindGuard("BehaviorsPisoA", GuardaUpdatePeaton.class);
            structPisoA.bindGuard("BehaviorsPisoA", GuardaIngresarCarro.class);
            structPisoA.bindGuard("BehaviorsPisoA", GuardaIngresarPeaton.class);
            structPisoA.bindGuard("BehaviorsPisoA", GuardaGetCarroStatus.class);
            structPisoA.bindGuard("BehaviorsPisoA", GuardaGetPeatonStatus.class);
            structPisoA.bindGuard("BehaviorsPisoA", GuardaRemoverCarro.class);
            structPisoA.bindGuard("BehaviorsPisoA", GuardaRemoverPeaton.class);
            AgentePiso agentePisoA = new AgentePiso("WORLD", estadoPisoA, structPisoA, 0.91);
            agentePisoA.start();
            
            if(numeroPisos > 2)
            {
                for(int p = 2; p < numeroPisos; p++)
                {
                    ClassLogger.LogMsg("Creando Agente PISO tipo-B");
                    EstadoPisoB estadoPisoB = new EstadoPisoB(size);
                    StructBESA structPisoB = new StructBESA();
                    structPisoB.addBehavior("BehaviorsPisoA");
                    structPisoB.bindGuard("BehaviorsPisoA", GuardaUpdateCarro.class);
                    structPisoB.bindGuard("BehaviorsPisoA", GuardaUpdatePeaton.class);
                    structPisoB.bindGuard("BehaviorsPisoA", GuardaIngresarCarro.class);
                    structPisoB.bindGuard("BehaviorsPisoA", GuardaIngresarPeaton.class);
                    structPisoB.bindGuard("BehaviorsPisoA", GuardaGetCarroStatus.class);
                    structPisoB.bindGuard("BehaviorsPisoA", GuardaGetPeatonStatus.class);
                    structPisoB.bindGuard("BehaviorsPisoA", GuardaRemoverCarro.class);
                    structPisoB.bindGuard("BehaviorsPisoA", GuardaRemoverPeaton.class);
                    AgentePiso agentePisoB = new AgentePiso("WORLD_"+p, estadoPisoB, structPisoB, 0.91);
                    agentePisoB.start();
                }
            }
            
            ClassLogger.LogMsg("Creando Agente PISO tipo-C");
            EstadoPisoC estadoPisoC = new EstadoPisoC(size);
            StructBESA structPisoC = new StructBESA();
            structPisoC.addBehavior("BehaviorsPisoA");
            structPisoC.bindGuard("BehaviorsPisoA", GuardaUpdateCarro.class);
            structPisoC.bindGuard("BehaviorsPisoA", GuardaUpdatePeaton.class);
            structPisoC.bindGuard("BehaviorsPisoA", GuardaIngresarCarro.class);
            structPisoC.bindGuard("BehaviorsPisoA", GuardaIngresarPeaton.class);
            structPisoC.bindGuard("BehaviorsPisoA", GuardaGetCarroStatus.class);
            structPisoC.bindGuard("BehaviorsPisoA", GuardaGetPeatonStatus.class);
            structPisoC.bindGuard("BehaviorsPisoA", GuardaRemoverCarro.class);
            structPisoC.bindGuard("BehaviorsPisoA", GuardaRemoverPeaton.class);
            AgentePiso agentePisoC = new AgentePiso("WORLD_"+numeroPisos, estadoPisoC, structPisoC, 0.91);
            agentePisoC.start();
        }
        else
        {
            ClassLogger.LogMsg("Creando Agente PISO");
            EstadoPiso estadoPiso = new EstadoPiso(size);
            estadoPiso.tiempoAparicionCarroMs = periodoAparicionCarroMs;
            StructBESA structPiso = new StructBESA();
            structPiso.addBehavior("BehaviorsPiso");
            structPiso.bindGuard("BehaviorsPiso", GuardaUpdateCarro.class);
            structPiso.bindGuard("BehaviorsPiso", GuardaUpdatePeaton.class);
            structPiso.bindGuard("BehaviorsPiso", GuardaIngresarCarro.class);
            structPiso.bindGuard("BehaviorsPiso", GuardaIngresarPeaton.class);
            structPiso.bindGuard("BehaviorsPiso", GuardaGetCarroStatus.class);
            structPiso.bindGuard("BehaviorsPiso", GuardaGetPeatonStatus.class);
            structPiso.bindGuard("BehaviorsPiso", GuardaRemoverCarro.class);
            structPiso.bindGuard("BehaviorsPiso", GuardaRemoverPeaton.class);
            AgentePiso agentePiso = new AgentePiso("WORLD", estadoPiso, structPiso, 0.91);
            agentePiso.start();
        }
        
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
