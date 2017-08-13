/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author heynersoza
 */
public class QueueElement implements Comparable{
    
    public Object elemento;
    public int prioridad;
    
    public QueueElement(Object elemento, int prioridad){
        this.elemento = elemento;
        this.prioridad = prioridad;
    }
    
    @Override
    public int compareTo(Object o) {
        QueueElement ps=(QueueElement)o;
        int prioridadOtro = ps.prioridad;
        if(this.prioridad == prioridadOtro){
            if(this.elemento.equals(ps.elemento)){
                return 0;
            }else{
                return -1;
            }
        }else{
            if(this.prioridad < prioridadOtro){
                return -1;
            }else{
                return 1;
            }
        }
    }
    
}
