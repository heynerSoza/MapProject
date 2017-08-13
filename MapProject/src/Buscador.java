
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author heynersoza
 */
public class Buscador {
    private IGraph grafo;
    private Dijkstra dijkstra;
    private Object destino;
    private int maxlength;
    private int maxDistance;
    private ArrayList listaSolucion;
    
    public Buscador(IGraph grafo){
        this.grafo = grafo;
        dijkstra = new Dijkstra(grafo);
        listaSolucion = new ArrayList();
    }
    
    public Camino getCaminoMasCorto(Object inicio, Object destino){
        return dijkstra.getCaminoMasCorto(inicio, destino);
    }
    
    public int getCaminoMenosCosto(Object inicio, Object destino){
        return dijkstra.getDistanciaMenosPeso(inicio, destino);
    }
    

    
    
    
}
