package capa_presentacion;

import capa_logica.Camino;
import capa_logica.IGraph;
import capa_logica.NodoVertice;
import capa_logica.Buscador;
import capa_logica.MatrizAdyacente;
import java.util.Iterator;


public class main {

    public static void main(String[] args) {
        
        int cantVertices = 7;
        MatrizAdyacente m = new MatrizAdyacente(cantVertices);
       
        Object v1 = new NodoVertice(1);
        Object v2 = new NodoVertice(2);
        Object v3 = new NodoVertice(3);
        Object v4 = new NodoVertice(4);
        Object v5 = new NodoVertice(5);
        Object v6 = new NodoVertice(6);
        Object v7 = new NodoVertice(7);
        
        m.agregarVertice(v1);
        m.agregarVertice(v2);
        m.agregarVertice(v3);
        m.agregarVertice(v4);
        m.agregarVertice(v5);
        m.agregarVertice(v6);
        m.agregarVertice(v7);
        
        m.agregarArco(v1, v2, 200);
        m.agregarArco(v1, v4, 150);
        m.agregarArco(v2, v3, 100);
        m.agregarArco(v3, v7, 25);
        m.agregarArco(v4, v3, 500);
        m.agregarArco(v4, v5, 200);
        m.agregarArco(v5, v6, 175);
        m.agregarArco(v6, v3, 250);
        m.agregarArco(v7, v5, 300);
        
        //Pruebas vertices adyacentes
        Iterator itr = m.getVerticeAdyacente(v1);
        String resul = "Vertices adyacentes: ";
        
        while (itr.hasNext()) {
            Object adyVertice = itr.next();
            resul += adyVertice.toString() + "-";
        }
        System.out.print(resul + "\n");
        
        //Pruebas caminos mas corto y el costo minimo
        IGraph ig = m;
        Buscador b = new Buscador(ig);
        
        Camino c = b.getCaminoMasCorto(v1, v7);
        
        System.out.println("Camino mas corto: " + c.toString());
        System.out.println("Costo: " + b.getCaminoMenosCosto(v1, v7));
        
    }
    
}