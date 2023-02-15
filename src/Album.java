import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Album {
    private String artista;
    private String nombre;
    private ArrayList<Cancion> canciones;

    public Album(String titulo, String artista) {
        this.artista = artista;
        this.nombre = nombre;
        canciones = new ArrayList<Cancion>();
    }

    private Cancion findSong(String titulo) {
        for (int i = 0; i < canciones.size(); i++) {
            if (canciones.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                return canciones.get(i);
            }
        }
        return null;
    }

    public boolean addSong(String titulo, double duracion) {
        findSong(titulo);
        Cancion cancion = new Cancion(titulo, duracion);
        if (findSong(titulo) != null) {
            System.out.println("La canción no existe, por lo tanto no se puede añadir.");
            return false;
        } else {
            canciones.add(cancion);
            System.out.println("La canción " + cancion + " se ha añadido.");
            return true;
        }
    }

    public boolean addtoPlayList(int numPista, LinkedList<Cancion> reproduccion) {
        if (numPista < canciones.size() && numPista >= 0) {
            Cancion c = canciones.get(numPista);
            System.out.println("La canción " + numPista + " se ha añadido.");
            reproduccion.add(c);
            return true;
        }
        System.out.println("La canción no se ha podido añadir");
        return false;
    }

    public boolean addtoPlayList(String titulo, LinkedList<Cancion> reproduccion) {
        Cancion cancion = findSong(titulo);
        ListIterator<Cancion> listIterator = reproduccion.listIterator();
        if (cancion != null) {
            while (listIterator.hasNext()) {
                if (listIterator.next().getTitulo().equalsIgnoreCase(cancion.getTitulo())) {
                    return false;
                }
            }
            System.out.println("La canción " + titulo + " se ha añadido.");
            reproduccion.add(cancion);
            return true;
        } else {
            System.out.println("La canción no se ha podido añadir.");
        }
        return false;
    }


}

