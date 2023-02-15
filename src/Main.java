import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    public static void ImprimirLista(LinkedList<Cancion> list){
        Iterator<Cancion> cancionIterator = list.iterator();
        while (cancionIterator.hasNext()) {
            System.out.println("Canción: " + cancionIterator.next().getTitulo());
        }
        System.out.println("------------");
    }
    public static void ImprimirMenu(){
        System.out.println("0 – Salir de la lista de reproducción");
        System.out.println("1 – Reproducir siguiente canción en la lista");
        System.out.println("2 – Reproducir la canción previa de la lista");
        System.out.println("3 – Repetir la canción actual");
        System.out.println("4 – Imprimir la lista de canciones en la playlist");
        System.out.println("5 – Volver a imprimir el menú");
        System.out.println("6 – Eliminar canción actual");
    }
    public static void play(LinkedList<Cancion> lista){
        Scanner sc = new Scanner(System.in);
        ListIterator<Cancion> it = lista.listIterator();
        boolean y = true;
        if (lista.size() == 0){
            System.out.println("No hay canciones");
        }else {
            System.out.println("Estás escuchando " + it.next().getTitulo());
        }
        boolean avanzar = true;
        while (y){
            ImprimirMenu();
            System.out.println("Introduzca la opción que quiere ejecutar: ");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 0:
                    System.out.println("No hay canciones.");
                    y = false;
                    break;
                case 1:
                    if (!avanzar) {
                        if (it.hasNext())
                            it.next();
                        avanzar = true;
                    }
                    if (it.hasNext()) {
                        System.out.println("Escuchando " + it.next().getTitulo());
                    } else {
                        System.out.println("No hay más canciones.");
                        avanzar = false;
                    }
                    break;
                case 2:
                    if (avanzar) {
                        if (it.hasPrevious())
                            it.previous();
                        avanzar = false;
                    }
                    if (it.hasPrevious()) {
                        System.out.println("Escuchando " + it.previous().getTitulo());
                    } else {
                        System.out.println("Primera canción: " + lista.getFirst().getTitulo());
                        avanzar = true;
                    }
                    break;
                case 3:
                    if (avanzar) {
                        if (it.hasPrevious()) {
                            System.out.println("Estás escuchando " + it.previous().getTitulo());
                            avanzar = false;
                        } else {
                            System.out.println("Estás escuchando la primera canción llamada " + lista.getFirst().getTitulo());
                        }
                    } else {
                        if (it.hasNext()) {
                            System.out.println("Estás escuchando " + it.next().getTitulo());
                            avanzar = true;
                        } else {
                            if (lista.size() == 0) {
                                System.out.println("La lista está vacía.");
                            } else {
                                System.out.println("Estás escuchando la última canción llamada " + lista.getLast().getTitulo());
                            }
                        }
                    }
                    break;
                case 4:
                    ImprimirLista(lista);
                    break;
                case 5:
                    ImprimirMenu();
                    break;
                case 6:
                    if (avanzar){
                        int index = it.previousIndex();
                        lista.remove(index);
                        it = lista.listIterator();
                        for (int i = 0; i <= index; i++){
                            it.next();
                        }
                    }else{
                        int index = it.nextIndex();
                        lista.remove(index);
                        it = lista.listIterator();
                        for (int i = 0; i <= index; i++){
                            it.next();
                        }
                        it.previous();
                    }
                    break;
            }
        }
    }
    public static void main(String[] args){
        LinkedList<Cancion> lista = new LinkedList<Cancion>();
        LinkedList<String> listaSt = new LinkedList<String>();
        ArrayList<Album> albums = new ArrayList<Album>();


        Album obj1 = new Album("EED1HS", "Israel B");
        obj1.addSong("Dollar bills", 2.35);
        obj1.addSong("Bobby Flicher", 2.24);
        albums.add(obj1);

        Album obj2 = new Album("Magua Con Miel", "Choclock");
        obj2.addSong("Malvasía", 3.38);
        obj2.addSong("Aguilera", 3.57);
        albums.add(obj2);

        albums.get(0).addtoPlayList(0, lista);
        albums.get(1).addtoPlayList(0, lista);
        albums.get(0).addtoPlayList(1, lista);
        albums.get(1).addtoPlayList(1, lista);


        albums.get(0).addtoPlayList("Dollar bills", lista);
        albums.get(1).addtoPlayList("Bobby Flicher", lista);
        albums.get(0).addtoPlayList("Malvasía", lista);
        albums.get(1).addtoPlayList("Aguilera", lista);

        play(lista);


    }
}
