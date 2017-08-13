
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author heynersoza
 */
public class ColaPrioridad {
    private TreeSet cola;
    
    
    public ColaPrioridad(){
        cola = new TreeSet();
    }
    
    public void clear(){
        cola.clear();
    }
    
    public boolean esVacio(){
        if(cola.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    
    public int getSize(){
        return cola.size();
    }
    
    public void insertar(Object elemento, int prioridad){
        if(elemento ==null){
            throw new IllegalArgumentException("El elemento no puede ser null");
        }
        if(prioridad < 0){
            throw new IllegalArgumentException("Distancia ilegal: "+prioridad);
        }
        QueueElement queueElement = new QueueElement(elemento,prioridad);
        cola.add(queueElement);
    }
    
    public Object elementoMenorPrioridad(){
        if(!esVacio()){
            QueueElement queueElement =(QueueElement) cola.first();
            Object element = queueElement.elemento;
            cola.remove(queueElement);
            return element;
        }else{
            return null;
        }
    }
    
    
}
