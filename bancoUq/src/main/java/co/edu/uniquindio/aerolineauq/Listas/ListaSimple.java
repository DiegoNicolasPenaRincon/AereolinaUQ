package co.edu.uniquindio.aerolineauq.Listas;

import co.edu.uniquindio.aerolineauq.model.Aerolinea;
import co.edu.uniquindio.aerolineauq.model.Equipaje;
import co.edu.uniquindio.aerolineauq.model.Usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ListaSimple<T> implements Iterable<T> , Serializable {
    private static final long serialVersionUID = 1L;

    private Nodo<T> nodoPrimero;
    private Nodo<T> nodoUltimo;
    private int tamanio;


    public ListaSimple() {
        nodoPrimero = null;
        nodoPrimero = null;
        tamanio = 0;
    }


    //Metodos basicos


    //Agregar al inicio de la lista
    public void agregarInicio(T valorNodo) {

        Nodo<T> nuevoNodo = new Nodo<>(valorNodo);

        if (estaVacia()) {
            nodoPrimero = nuevoNodo;
        } else {
            nuevoNodo.setSiguienteNodo(nodoPrimero);
            nodoPrimero = nuevoNodo;
        }
        tamanio++;
    }

    //Agregar al final de la lista
    public void agregar(T valorNodo) {

        Nodo<T> nodo = new Nodo<>(valorNodo);

        if (estaVacia()) {
            nodoPrimero = nodoUltimo = nodo;
        } else {
            nodoUltimo.setSiguienteNodo(nodo);
            nodoUltimo = nodo;
        }

        tamanio++;
    }


    //Obtener Nodo el valor de un Nodo
    public T obtenerValorNodo(int indice) {

        Nodo<T> nodoTemporal = null;
        int contador = 0;

        if (indiceValido(indice)) {
            nodoTemporal = nodoPrimero;

            while (contador < indice) {

                nodoTemporal = nodoTemporal.getSiguienteNodo();
                contador++;
            }
        }

        if (nodoTemporal != null)
            return nodoTemporal.getValorNodo();
        else
            return null;
    }


    //Verificar si indice es valido
    private boolean indiceValido(int indice) {
        if (indice >= 0 && indice < tamanio) {
            return true;
        }
        throw new RuntimeException("�ndice no v�lido");
    }


    //Verificar si la lista esta vacia
    public boolean estaVacia() {
        return (nodoPrimero == null) ? true : false;
    }


    /**
     * Imprime en consola la lista enlazada
     */
    public void imprimirLista() {

        Nodo<T> aux = nodoPrimero;

        while (aux != null) {
            System.out.print(aux.getValorNodo() + "\t");
            aux = aux.getSiguienteNodo();
        }

        System.out.println();
    }

    //Eliminar dado el valor de un nodo
    public T eliminar(T dato) {
        Nodo<T> nodo = nodoPrimero;
        Nodo<T> previo = null;
        Nodo<T> siguiente = null;
        boolean encontrado = false;

        //buscar el nodo previo
        while (nodo != null) {
            if (nodo.getValorNodo() == dato) {
                encontrado = true;
                break;
            }
            previo = nodo;
            nodo = nodo.getSiguienteNodo();
        }

        if (encontrado) {
            siguiente = nodo.getSiguienteNodo();
            if (previo == null) {
                nodoPrimero = siguiente;
            } else {
                previo.setSiguienteNodo(siguiente);
            }

            if (siguiente == null) {
//				nodoUltimo = previo;
            } else {
                nodo.setSiguienteNodo(null);
            }

            nodo = null;
            tamanio--;
            return dato;
        }
        throw new RuntimeException("El elemento no existe");
    }


    //Elimina el primer nodo de la lista
    public T eliminarPrimero() {

        if (!estaVacia()) {
            Nodo<T> n = nodoPrimero;
            T valor = n.getValorNodo();
            nodoPrimero = n.getSiguienteNodo();

            if (nodoPrimero == null) {
//				nodoUltimo = null;
            }

            tamanio--;
            return valor;
        }

        throw new RuntimeException("Lista vac�a");
    }


    private Nodo<T> obtenerNodo(int indice) {

        if (indice >= 0 && indice < tamanio) {

            Nodo<T> nodo = nodoPrimero;

            for (int i = 0; i < indice; i++) {
                nodo = nodo.getSiguienteNodo();
            }

            return nodo;
        }

        return null;
    }


    /**
     * Cambia el valor de un nodo dada su posici�n en la lista
     *
     * @param indice posici�n donde se va a cambiar el dato
     * @param nuevo  nuevo valor por el que se actualizar� el nodo
     */
    public void modificarNodo(int indice, T nuevo) {

        if (indiceValido(indice)) {
            Nodo<T> nodo = obtenerNodo(indice);
            nodo.setValorNodo(nuevo);
        }

    }


    /**
     * Retorna la primera posici�n donde est� guardado el dato
     *
     * @param dato valor a buscar dentro de la lista
     * @return primera posici�n del dato
     */
    public int obtenerPosicionNodo(T dato) {

        int i = 0;

        for (Nodo<T> aux = nodoPrimero; aux != null; aux = aux.getSiguienteNodo()) {
            if (aux.getValorNodo().equals(dato)) {
                return i;
            }
            i++;
        }

        return -1;
    }

    /**
     * Castea la lista a una coleccion
     * @return
     */
    public Collection<T> toCollection() {
        Collection<T> c=new ArrayList<>();
        Nodo<T> aux=nodoPrimero;
        while(aux!=null)
        {
            c.add(aux.getValorNodo());
            aux = aux.getSiguienteNodo();
        }
        return c;
    }


    @Override
    public Iterator<T> iterator() {
        return new IteradorListaSimple(nodoPrimero);
    }


    public void addAll(ArrayList<T> elementos) {
        for (T elemento : elementos) {
            this.agregar(elemento);
        }
    }

    public void addAll(ListaSimple<T> elementos) {
        for (T elemento : elementos) {
            this.agregar(elemento);
        }
    }

    protected class IteradorListaSimple implements Iterator<T> {

        private Nodo<T> nodo;
        private int posicion;

        /**
         * Constructor de la clase Iterador
         *
         * @param nodo Primer Nodo de la lista
         */
        public IteradorListaSimple(Nodo<T> nodo) {
            this.nodo = nodo;
            this.posicion = 0;
        }

        @Override
        public boolean hasNext() {
            return nodo != null;
        }

        @Override
        public T next() {
            T valor = nodo.getValorNodo();
            nodo = nodo.getSiguienteNodo();
            posicion++;
            return valor;
        }

        /**
         * Posici�n actual de la lista
         *
         * @return posici�n
         */
        public int getPosicion() {
            return posicion;
        }

    }


    //Metodos get y set de la clase ListaSimple


    public Nodo getNodoPrimero() {
        return nodoPrimero;
    }


    public void setNodoPrimero(Nodo nodoPrimero) {
        this.nodoPrimero = nodoPrimero;
    }


    public int getTamanio() {
        return tamanio;
    }


    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public Usuario buscarUsuarioPorId(String id) {
        Nodo actual = nodoPrimero;
        while (actual != null) {
            Usuario usuario= (Usuario) actual.getValorNodo();
            if (usuario.getId().equals(id)) {
                return usuario;
            }
            actual = actual.getSiguienteNodo();
        }
        return null;
    }
    // Añadir un elemento en una posición específica
    public void add(int index, T element) {
        if (index < 0 || index > tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        Nodo<T> nuevoNodo = new Nodo<>(element);

        if (index == 0) {
            nuevoNodo.setSiguienteNodo(nodoPrimero);
            nodoPrimero = nuevoNodo;
            if (tamanio == 0) {
                nodoUltimo = nuevoNodo;
            }
        } else {
            Nodo<T> previo = obtenerNodo(index - 1);
            nuevoNodo.setSiguienteNodo(previo.getSiguienteNodo());
            previo.setSiguienteNodo(nuevoNodo);
            if (nuevoNodo.getSiguienteNodo() == null) {
                nodoUltimo = nuevoNodo;
            }
        }

        tamanio++;
    }

    // Agregar todos los elementos de una lista al final de ListaSimple
    public void addAll(List<T> elements) {
        for (T element : elements) {
            this.agregar(element);
        }
    }
    // Tamaño de la lista
    public int size() {
        return tamanio;
    }

    // Verificar si la lista está vacía
    public boolean isEmpty() {
        return tamanio == 0;
    }

    // Verificar si la lista contiene un elemento
    public boolean contains(T dato) {
        for (T elemento : this) {
            if (elemento.equals(dato)) {
                return true;
            }
        }
        return false;
    }

    // Obtener un elemento en una posición específica
    public T get(int index) {
        return obtenerValorNodo(index);
    }

    // Eliminar un elemento en una posición específica
    public T remove(int index) {
        if (index < 0 || index >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        if (index == 0) {
            return eliminarPrimero();
        }

        Nodo<T> previo = obtenerNodo(index - 1);
        Nodo<T> actual = previo.getSiguienteNodo();
        T valor = actual.getValorNodo();
        previo.setSiguienteNodo(actual.getSiguienteNodo());

        if (actual == nodoUltimo) {
            nodoUltimo = previo;
        }
        tamanio--;
        return valor;
    }

    // Vaciar la lista
    public void clear() {
        nodoPrimero = null;
        nodoUltimo = null;
        tamanio = 0;
    }

    // Obtener el índice de un elemento
    public int indexOf(T dato) {
        int index = 0;
        for (T elemento : this) {
            if (elemento.equals(dato)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    // Convertir la lista a un array
    public Object[] toArray() {
        Object[] array = new Object[tamanio];
        int i = 0;
        for (T elemento : this) {
            array[i++] = elemento;
        }
        return array;
    }


}