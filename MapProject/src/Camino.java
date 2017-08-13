
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author heynersoza
 */
public class Camino implements Cloneable{
    private ArrayList listaVertices;
    
    public Camino(){
        listaVertices = new ArrayList();
    }
    
    public Camino(List list){
        listaVertices = (ArrayList) list;
    }
    
    public int getLenth(){
        return listaVertices.size()-1;
    }
    
    public Camino agregarVertice(Object vertice){
        listaVertices.add(vertice);
        return this;
    }
    
    public Camino agregarCamino(Camino camino){
        listaVertices.addAll(camino.listaVertices);
        return this;
    }
    
    public Object get(int index){
        return listaVertices.get(index);
    }
    
    public Object getLast(){
        return listaVertices.get(listaVertices.size()-1);
    }
    
    public Object clone(){
        Camino newInstance = null;
        try{
            newInstance = (Camino) super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        
        newInstance.listaVertices = (ArrayList)listaVertices.clone();
        return newInstance;
    }
    
     public String toString(){
         String s = new String();
         Iterator iter = listaVertices.iterator();
         if(iter.hasNext()){
             Object item = iter.next();
             s += item.toString();
         }
         while(iter.hasNext()){
             Object item=iter.next();
             s += "-"+item.toString();
         }
         return s;
     }
    
}
