
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
public interface IGraph {
    public int obtenerNumeroVertices();
    public void agregarVertice(Object vertice);
    public void agregarArco(Object verticeInicio, Object verticeDestino, int peso);
    public void removerArco(Object verticeInicio,Object verticeDestino);
    public boolean bordeExiste(Object verticeInicio, Object verticeDestino);
    public boolean verticeExiste(Object vertice);
    public int getPesoArco(Object verticeIncio, Object verteDestino);
    public int getPesoArco(Camino camino);
    public Iterator getVerticeAdyacente(Object vertice);
    public Iterator getPredecesores(Object vertice);
    
}

