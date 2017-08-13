
import java.util.ArrayList;
import java.util.HashMap;
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
public class MatrizAdyacente implements IGraph{
    private int numeroVertices;
    private HashMap verticesMap;
    private Object[] arregloObjetos;
    private int indiceVerticeActual;
    private int [][] matrizAdyacencia;
    private int [][] matrizAdyacenciaInvertida;
    
    public MatrizAdyacente(int numeroVertices){
         if(numeroVertices <1){
             throw new IllegalArgumentException("No se puede crear el grafo, el numero de vertices tiene que ser mayor a 0");
         }
         this.numeroVertices = numeroVertices;
         matrizAdyacencia = new int[numeroVertices][numeroVertices];
         matrizAdyacenciaInvertida = new int[numeroVertices][numeroVertices];
         verticesMap = new HashMap(numeroVertices);
         arregloObjetos = new Object[numeroVertices];
         indiceVerticeActual = 0;
         
    }
    
    
    @Override
    public int obtenerNumeroVertices() {
         //To change body of generated methods, choose Tools | Templates.
        return this.numeroVertices;
    }

    @Override
    public void agregarVertice(Object vertice) {
        if(this.indiceVerticeActual>=this.numeroVertices){
            throw new IllegalArgumentException("No se puede agregar un vertice");
        }
        verticesMap.put(vertice, indiceVerticeActual);
        arregloObjetos[indiceVerticeActual]=vertice;
        indiceVerticeActual++;
                
    }

    @Override
    public void agregarArco(Object verticeInicio, Object verticeDestino, int peso) {
         int inicio = getIndiceVertice(verticeInicio);
         int destino = getIndiceVertice(verticeDestino);
         
         this.matrizAdyacencia[inicio][destino]=peso;
         this.matrizAdyacenciaInvertida[destino][inicio]=peso;
    }

    @Override
    public void removerArco(Object verticeInicio, Object verticeDestino) {
        int inicio = getIndiceVertice(verticeInicio);
        int destino = getIndiceVertice(verticeDestino);
        this.matrizAdyacencia[inicio][destino]=0;
        this.matrizAdyacenciaInvertida[destino][inicio]=0;
    }

    @Override
    public boolean bordeExiste(Object verticeInicio, Object verticeDestino) {
        int inicio = getIndiceVertice(verticeInicio);
        int destino = getIndiceVertice(verticeDestino);
        
        if(matrizAdyacencia[inicio][destino]!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean verticeExiste(Object vertice) {
        if(!this.verticesMap.containsKey(vertice)){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public int getPesoArco(Object verticeInicio, Object verteDestino) {
        int inicio = getIndiceVertice(verticeInicio);
        int destino = getIndiceVertice(verteDestino);
        
        return matrizAdyacencia[inicio][destino];
    }

    @Override
    public int getPesoArco(Camino camino) {
        if(camino.getLenth()<1){
           return 0;
        }
        
        int total =0;
        for(int i=0;i<camino.getLenth();i++){
            int arcoPeso = getPesoArco(camino.get(i), camino.get(i+1));
            
            if(arcoPeso==0){
                return 0;
            }else{
                total += arcoPeso;
            }
        }
        
        return total;
    }

    @Override
    public Iterator getVerticeAdyacente(Object vertice) {
        return getAdyecenciaMatriz(vertice,matrizAdyacencia).iterator();
        
    }

    @Override
    public Iterator getPredecesores(Object vertice) {
        return getAdyecenciaMatriz(vertice,matrizAdyacenciaInvertida).iterator();
    }
    
    private ArrayList getAdyecenciaMatriz(Object vertice,int[][] matriz){
        int inicio = getIndiceVertice(vertice);
        ArrayList verticeAdyacente = new ArrayList();
        for(int i=0; i<matriz.length; i++){
            int peso = matriz[inicio][i];
            if(peso != 0){
                verticeAdyacente.add(getObjetoVertice(i));
            }
        }
        return verticeAdyacente;
    }
    
    private int getIndiceVertice(Object vertice){
        if(!verticesMap.containsKey(vertice)){
            throw new IllegalArgumentException("El vertice ! "+ vertice+"no existe en el grafo");
        }
        return ((Integer)verticesMap.get(vertice)).intValue();
    }
    
    private Object getObjetoVertice(int index){
        return arregloObjetos[index];
    }
}

