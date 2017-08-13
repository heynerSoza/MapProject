
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author heynersoza
 */
public class Dijkstra {
    private final static int INFINITE = Integer.MAX_VALUE;
    private IGraph grafo;
    private HashSet verticeDeterminado;
    private ColaPrioridad colaVerticesRestantes;
    private HashMap mapaCaminoMasCorto;
    private HashMap mapaPredecesor;
    
   public Dijkstra(IGraph grafo){
       this.grafo = grafo;
       int numeroVertices = grafo.obtenerNumeroVertices();
       
       verticeDeterminado = new HashSet(numeroVertices);
       colaVerticesRestantes = new ColaPrioridad();
       mapaCaminoMasCorto = new HashMap(numeroVertices);
       mapaPredecesor = new HashMap(numeroVertices);
   }
   
   private void correrAlgoritmo(Object verticeOrigen, Object verticeDestino){
       mapaCaminoMasCorto.clear();
       mapaPredecesor.clear();
       verticeDeterminado.clear();
       colaVerticesRestantes.clear();
       
       mapaCaminoMasCorto.put(verticeOrigen, 0);
       
       colaVerticesRestantes.insertar(verticeOrigen,0);
       
       while(!colaVerticesRestantes.esVacio()){
           Object masCercano = colaVerticesRestantes.elementoMenorPrioridad();
           
           if(masCercano.equals(verticeDestino)){
               break;
           }
           
           verticeDeterminado.add(masCercano);
           relax(masCercano);
       
       }
       
   }
   
   public void relax(Object vertice){
       Iterator verticeAdyacente = (Iterator) grafo.getVerticeAdyacente(vertice);
       while(verticeAdyacente.hasNext()){
           Object adyVertice = verticeAdyacente.next();
           
           if(!verticeDeterminado.contains(adyVertice)){
               int distancia = getCaminoMasCortoOrigen(vertice)+grafo.getPesoArco(vertice,adyVertice);
               if(getCaminoMasCortoOrigen(adyVertice)>distancia){
                   this.setCaminoMasCortoPrincipio(adyVertice, distancia);
                   mapaPredecesor.put(adyVertice, vertice);
                   colaVerticesRestantes.insertar(adyVertice,distancia);
               }
           }
       }
   }
   
   private int getCaminoMasCortoOrigen(Object vertex){
       if(mapaCaminoMasCorto.containsKey(vertex)){
          return ((Integer)mapaCaminoMasCorto.get(vertex)).intValue();
       }else{
           return INFINITE;
       }
   }
   
   private void setCaminoMasCortoPrincipio(Object vertice, int path){
       mapaCaminoMasCorto.put(vertice, path);
   }
   
   public int getDistanciaMenosPeso(Object inicio, Object destino){
       Camino masCorto = getCaminoMasCorto(inicio,destino);
       return grafo.getPesoArco(masCorto);
   }
   
   public Camino getCaminoMasCorto(Object inicio,Object destino){
       verticeExiste(inicio);
       verticeExiste(destino);
       
       correrAlgoritmo(inicio,destino);
       if(!inicio.equals(destino)){
           return construirCaminoCorto(inicio,destino);
       }else{
           ColaPrioridad pq = new ColaPrioridad();
           Iterator iter = (Iterator) grafo.getVerticeAdyacente(inicio);
           
           while(iter.hasNext()){
               Object vertice = iter.next();
               int distancia = grafo.getPesoArco(inicio, destino);
               correrAlgoritmo(inicio,destino);
               
               pq.insertar(vertice, distancia+getCaminoMasCortoOrigen(destino));
               
           }
           
           Camino camino = new Camino();
           camino.agregarVertice(inicio);
           
           camino.agregarCamino(construirCaminoCorto(pq.elementoMenorPrioridad(),destino));
           
           return camino;
       }    
   }
   
   private Camino construirCaminoCorto(Object inicio, Object destino){
       Camino camino = new Camino();
       if(getCaminoMasCortoOrigen(destino)!=INFINITE){
           ArrayList caminoLista = new ArrayList();
           Object predecesor = destino;
           do{
               caminoLista.add(predecesor);
               predecesor= mapaPredecesor.get(predecesor);
           }while((predecesor !=null)&& !predecesor.equals(inicio));
           
           caminoLista.add(inicio);
           
           Collections.reverse(caminoLista);
           
           return new Camino(caminoLista);
       
       }
       
       return camino;
   }
   private void verticeExiste(Object vertice){
       if(!grafo.verticeExiste(vertice)){
           throw new IllegalArgumentException("Vertice no existe");
       }
   }
}
