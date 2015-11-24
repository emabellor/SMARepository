/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parqueadero;


import BESA.ExceptionBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import javax.swing.JFrame;;
import BESA.Kernell.Agent.StructBESA;
import BESA.Kernell.System.AdmBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import Logging.*;
import Carro.*;
import Peaton.*;
import Mundo.*;
import Mundo.PisoA.*;
import Mundo.PisoB.*;
import Mundo.PisoC.*;
import GUI.*;
import Data.*;
import Viewer.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author Mauricio
 */
public class ParqueaderoBESA 
{
    
    private static int GAME_PERIODIC_TIME = 1000;
    private static int GAME_PERIODIC_DELAY_TIME = 100;
    private static int MAP_SIZE = 15;
    private static int NUMERO_PISOS = 3;
    private static MainFrame mainFrame;
    private static AdmBESA adminBESA;
    
    public interface ICallbackGUI
    {
        void CallbackIniciar(ClassParametrosSimulacion parametros);
        void CallbackUpdatePiso(int numeroPiso);
    }
    
    public interface ICallbackViewer
    {
        void Callback(ClassElemento[][] listaElementos, List<ClassElemento> listaAgentes);
    }
    
    public static void main(String[] args) 
    {
        ClassLogger.LogMsg("Obteniendo administrador BESA");
        adminBESA = AdmBESA.getInstance();
        
        ClassLogger.LogMsg("Inicializando el entorno Grafico");
        ICallbackGUI callback = new ICallbackGUI()
        {
            @Override
            public void CallbackIniciar(ClassParametrosSimulacion parametros) {
                IniciarSimulacion(parametros);
            }
            
            @Override
            public void CallbackUpdatePiso(int numeroPiso)
            {
               CambiarNumeroPiso(numeroPiso);
            }
        };
        
        mainFrame = new MainFrame(callback, MAP_SIZE, NUMERO_PISOS);
        mainFrame.setVisible(true);
    }
    
    static void ShowFatal(String message)
    {
        // create a jframe
        JFrame frame = new JFrame("JOptionPane showMessageDialog example");

        // show a joptionpane dialog using showMessageDialog
        JOptionPane.showMessageDialog(frame, message);
        System.exit(0);
    }
    
    static void IniciarSimulacion(ClassParametrosSimulacion parametros)
    {
        try
        {
            InicializarAgentes(parametros);
        }
        catch(BESA.ExceptionBESA ex)
        {
            ShowFatal("ExceptionBESA: " + ex.getMessage());
        } 
    }
    
    static void CambiarNumeroPiso(int numeroPiso)
    {
        DataChangeMundoReference dataToSend = new DataChangeMundoReference();
        dataToSend.nuevoPisoActual = numeroPiso;      
        EventBESA event = new EventBESA(GuardaChangeMundoReference.class.getName(), dataToSend);
        try
        {
            AgHandlerBESA ah = adminBESA.getHandlerByAlias("Viewer");
            ah.sendEvent(event);
        }
        catch(ExceptionBESA ex)
        {
            ClassLogger.LogMsg(ex.getMessage(), LogLevel.ERROR);
        } 
    }
    
    static void InicializarAgentes (ClassParametrosSimulacion parametros) throws BESA.ExceptionBESA 
    {
        //Constantes
        int periodoAparicionCarroMs = 2000;
        int periodoActualizacionPeatonMs = 400;
        int periodoActualizacionCarroMs = 200;
        int tiempoFueraPeatonMs = 5000;
        
        ClassLogger.LogMsg("Inicio de la aplicacion de parqueaderos");
            
        for (int i = 1; i <= NUMERO_PISOS; i++)
        {
            ClassLogger.LogMsg("Creando Agente PISO");
            EstadoPiso estadoPiso;
            if (i == 1)
                estadoPiso = new EstadoPisoA(MAP_SIZE);
            else if (i == 2)
                estadoPiso = new EstadoPisoB(MAP_SIZE);
            else 
                estadoPiso = new EstadoPisoC(MAP_SIZE);
            
            estadoPiso.tiempoAparicionCarroMs = periodoAparicionCarroMs;
            StructBESA structPisoA = new StructBESA();
            String nombreBehavior = "BehaviorsPiso";
            structPisoA.addBehavior(nombreBehavior);
            structPisoA.bindGuard(nombreBehavior, GuardaUpdateCarro.class);
            structPisoA.bindGuard(nombreBehavior, GuardaUpdatePeaton.class);
            structPisoA.bindGuard(nombreBehavior, GuardaIngresarCarro.class);
            structPisoA.bindGuard(nombreBehavior, GuardaIngresarPeaton.class);
            structPisoA.bindGuard(nombreBehavior, GuardaGetCarroStatus.class);
            structPisoA.bindGuard(nombreBehavior, GuardaGetPeatonStatus.class);
            structPisoA.bindGuard(nombreBehavior, GuardaRemoverCarro.class);
            structPisoA.bindGuard(nombreBehavior, GuardaRemoverPeaton.class);
            structPisoA.bindGuard(nombreBehavior, GuardaGetMundoStatus.class);
            
            String pisoName = "WORLD";
            if (i != 1)
                pisoName += "_" + i;
            
            AgentePiso agentePiso = new AgentePiso(pisoName, estadoPiso, structPisoA, 0.91);
            agentePiso.start();
        }

        for (int i = 0; i < parametros.numeroVehiculos; i++)
        {
            List<String> listaOcupantes = new ArrayList<>();
            
            for (int j = 0; j < parametros.numeroOcupantes; j++)
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
        
        ClassLogger.LogMsg("Creando Agente Viewer");
        ICallbackViewer callbackViewer = new ICallbackViewer()
        {
            @Override
            public void Callback(ClassElemento[][] listaElementos, List<ClassElemento> listaAgentes) {
                mainFrame.RedibujarMapa(listaElementos, listaAgentes);
            }
        };
        
        EstadoViewer estadoViewer = new EstadoViewer(callbackViewer, NUMERO_PISOS);
        StructBESA structViewer = new StructBESA();
        String nombreBehavior = "BehaviorsViewer";
        structViewer.addBehavior(nombreBehavior);
        structViewer.bindGuard(nombreBehavior, GuardaChangeMundoReference.class);
        structViewer.bindGuard(nombreBehavior, GuardaGetMundoStatusResult.class);
        AgenteViewer agenteViewer = new AgenteViewer("Viewer", estadoViewer, structViewer, 0.91);
        agenteViewer.start();
        
    }
}
