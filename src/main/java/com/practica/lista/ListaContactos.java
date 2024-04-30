package com.practica.lista;

import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;

public class ListaContactos {
    private NodoTemporal lista;
    private int size;

    public void insertarNodoTemporal(PosicionPersona p) {
        NodoTemporal aux = lista, ant = null;
        boolean salir = false, encontrado = false;

        while (aux != null && !salir) {
            if (aux.getFecha().compareTo(p.getFechaPosicion()) == 0) {
                encontrado = true;
                salir = true;
                insertarPrincipal(aux);
            } else if (aux.getFecha().compareTo(p.getFechaPosicion()) < 0) {
                ant = aux;
                aux = aux.getSiguiente();
            } else if (aux.getFecha().compareTo(p.getFechaPosicion()) > 0) {
                salir = true;
            }
        }

        if (!encontrado) {
            NodoTemporal nuevo = new NodoTemporal();
            nuevo.setFecha(p.getFechaPosicion());
            insertarPrincipal(nuevo);
            if (ant != null) {
                nuevo.setSiguiente(aux);
                ant.setSiguiente(nuevo);
            } else {
                nuevo.setSiguiente(lista);
                lista = nuevo;
            }
            this.size++;
        }
    }

    public int personasEnCoordenadas() {
        NodoPosicion aux = this.lista.getListaCoordenadas();
        if (aux == null)
            return 0;
        else {
            int cont;
            for (cont = 0; aux != null; ) {
                cont += aux.getNumPersonas();
                aux = aux.getSiguiente();
            }
            return cont;
        }
    }

    public int tamanioLista() {
        return this.size;
    }

    public String getPrimerNodo() {
        NodoTemporal aux = lista;
        String cadena = aux.getFecha().getFecha().toString();
        cadena += ";" + aux.getFecha().getHora().toString();
        return cadena;
    }

    public int numPersonasEntreDosInstantes(FechaHora inicio, FechaHora fin) {
        if (this.size == 0)
            return 0;
        NodoTemporal aux = lista;
        int cont = 0;
        cont = 0;
        while (aux != null) {
            if (aux.getFecha().compareTo(inicio) >= 0 && aux.getFecha().compareTo(fin) <= 0) {
                NodoPosicion nodo = aux.getListaCoordenadas();
                while (nodo != null) {
                    cont = cont + nodo.getNumPersonas();
                    nodo = nodo.getSiguiente();
                }
                aux = aux.getSiguiente();
            } else {
                aux = aux.getSiguiente();
            }
        }
        return cont;
    }

    public void insertarPrincipal(NodoTemporal a) {
        NodoPosicion npActual = a.getListaCoordenadas();
        while (npActual != null && !npEncontrado) {
            insertar(npActual);
        }
        if (!npEncontrado) {
            insertarListaCordenada(a, npAnt);
        }
    }

    public void insertar(NodoPosicion npActual) {
        NodoPosicion npAnt = null;
        boolean npEncontrado = false;
        if (npActual.getCoordenada().equals(p.getCoordenada())) {
            npEncontrado = true;
            npActual.setNumPersonas(npActual.getNumPersonas() + 1);
        } else {
            npAnt = npActual;
            npActual = npActual.getSiguiente();
        }
    }

    public void insertarListaCordenada(NodoTemporal aux, NodoPosicion npAnt) {
        NodoPosicion npNuevo = new NodoPosicion(p.getCoordenada(), 1, null);
        if (aux.getListaCoordenadas() == null)
            aux.setListaCoordenadas(npNuevo);
        else
            npAnt.setSiguiente(npNuevo);
    }

    public int numNodosCoordenadaEntreDosInstantes(FechaHora inicio, FechaHora fin) {
        if (this.size == 0)
            return 0;
        NodoTemporal aux = lista;
        int cont = 0;
        cont = 0;
        while (aux != null) {
            if (aux.getFecha().compareTo(inicio) >= 0 && aux.getFecha().compareTo(fin) <= 0) {
                NodoPosicion nodo = aux.getListaCoordenadas();
                while (nodo != null) {
                    cont = cont + 1;
                    nodo = nodo.getSiguiente();
                }
                aux = aux.getSiguiente();
            } else {
                aux = aux.getSiguiente();
            }
        }
        return cont;
    }

    @Override
    public String toString() {
        String cadena = "";
        int cont = 0;
        NodoTemporal aux = lista;
        for (cont = 1; cont < size; cont++) {
            cadena += aux.getFecha().getFecha().toString();
            cadena += ";" + aux.getFecha().getHora().toString() + " ";
            aux = aux.getSiguiente();
        }
        cadena += aux.getFecha().getFecha().toString();
        cadena += ";" + aux.getFecha().getHora().toString();
        return cadena;
    }
    
}
