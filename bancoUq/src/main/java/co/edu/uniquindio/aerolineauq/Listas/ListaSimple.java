package co.edu.uniquindio.aerolineauq.Listas;

import co.edu.uniquindio.aerolineauq.model.Aerolinea;
import co.edu.uniquindio.aerolineauq.model.Equipaje;
import co.edu.uniquindio.aerolineauq.model.Usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

public class ListaSimple<T> implements Iterable<T>, Serializable {
    private static final long serialVersionUID = 1L;

    private Nodo<T> nodoPrimero;
    private Nodo<T> nodoUltimo;
    private int tamanio;

    public ListaSimple() {
        nodoPrimero = null;
        nodoUltimo = null;
        tamanio = 0;
    }

    // Verificar si la lista está vacía
    public boolean estaVacia() {
        return nodoPrimero == null;
    }

    // Tamaño de la lista
    public int size() {
        return tamanio;
    }

    // Agregar al final de la lista
    public void agregar(T valorNodo) {
        Nodo<T> nuevoNodo = new Nodo<>(valorNodo);
        if (estaVacia()) {
            nodoPrimero = nodoUltimo = nuevoNodo;
        } else {
            nodoUltimo.setSiguienteNodo(nuevoNodo);
            nodoUltimo = nuevoNodo;
        }
        tamanio++;
    }

    // Obtener un elemento en una posición específica
    public T obtenerValorNodo(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Nodo<T> nodo = nodoPrimero;
        for (int i = 0; i < indice; i++) {
            nodo = nodo.getSiguienteNodo();
        }
        return nodo.getValorNodo();
    }

    // Modificar un elemento según un predicado
    public boolean modificarElemento(Predicate<T> criterio, T nuevoValor) {
        Nodo<T> actual = nodoPrimero;
        while (actual != null) {
            if (criterio.test(actual.getValorNodo())) {
                actual.setValorNodo(nuevoValor);
                return true; // Elemento modificado exitosamente
            }
            actual = actual.getSiguienteNodo();
        }
        return false; // No se encontró el elemento
    }

    // Convertir la lista a un array
    public Object[] toArray() {
        Object[] array = new Object[tamanio];
        Nodo<T> actual = nodoPrimero;
        int i = 0;
        while (actual != null) {
            array[i++] = actual.getValorNodo();
            actual = actual.getSiguienteNodo();
        }
        return array;
    }

    // Convertir la lista a una colección
    public Collection<T> toCollection() {
        Collection<T> coleccion = new ArrayList<>();
        for (T elemento : this) {
            coleccion.add(elemento);
        }
        return coleccion;
    }

    public boolean eliminarElemento(Predicate<T> criterio) {
        Nodo<T> actual = nodoPrimero;
        Nodo<T> anterior = null;

        while (actual != null) {
            if (criterio.test(actual.getValorNodo())) {
                if (anterior == null) {
                    // Eliminar el primer nodo
                    nodoPrimero = actual.getSiguienteNodo();
                    if (nodoPrimero == null) {
                        nodoUltimo = null; // Si la lista queda vacía
                    }
                } else {
                    // Eliminar un nodo en el medio o al final
                    anterior.setSiguienteNodo(actual.getSiguienteNodo());
                    if (actual == nodoUltimo) {
                        nodoUltimo = anterior; // Si se eliminó el último nodo
                    }
                }
                tamanio--;
                return true; // Elemento eliminado exitosamente
            }
            anterior = actual;
            actual = actual.getSiguienteNodo();
        }
        return false; // No se encontró el elemento
    }

    public void addAll(ArrayList<T> elementos) {
        for (T elemento : elementos) {
            this.agregar(elemento);
        }
    }

    // Implementación de `Iterable`
    @Override
    public Iterator<T> iterator() {
        return new IteradorListaSimple(nodoPrimero);
    }

    // Clase iteradora interna
    private class IteradorListaSimple implements Iterator<T> {
        private Nodo<T> nodoActual;

        public IteradorListaSimple(Nodo<T> nodoInicial) {
            this.nodoActual = nodoInicial;
        }

        @Override
        public boolean hasNext() {
            return nodoActual != null;
        }

        @Override
        public T next() {
            T valor = nodoActual.getValorNodo();
            nodoActual = nodoActual.getSiguienteNodo();
            return valor;
        }
    }

    public T obtenerValorNodoPorValor(int indice) {

        Nodo<T> nodoTemporal = null;
        int contador = 0;

        if(indiceValido(indice))
        {
            nodoTemporal = nodoPrimero;

            while (contador < indice) {

                nodoTemporal = nodoTemporal.getSiguienteNodo();
                contador++;
            }
        }

        if(nodoTemporal != null)
            return nodoTemporal.getValorNodo();
        else
            return null;
    }

    private boolean indiceValido(int indice) {
        if( indice>=0 && indice<tamanio ) {
            return true;
        }
        throw new RuntimeException("�ndice no v�lido");
    }

    public T eliminar(T dato){
        Nodo<T> nodo = nodoPrimero;
        Nodo<T> previo = null;
        Nodo<T> siguiente = null;
        boolean encontrado = false;

        //buscar el nodo previo
        while(nodo!=null) {
            if( nodo.getValorNodo() == dato ) {
                encontrado = true;
                break;
            }
            previo = nodo;
            nodo = nodo.getSiguienteNodo();
        }

        if(encontrado) {
            siguiente = nodo.getSiguienteNodo();
            if( previo==null ) {
                nodoPrimero = siguiente;
            }else {
                previo.setSiguienteNodo(siguiente);
            }

            if(siguiente==null) {
//				nodoUltimo = previo;
            }else {
                nodo.setSiguienteNodo(null);
            }

            nodo = null;
            tamanio--;
            return dato;
        }
        throw new RuntimeException("El elemento no existe");
    }

}
