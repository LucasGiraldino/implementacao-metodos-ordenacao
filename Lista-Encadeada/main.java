public class main {
    public static void main(String[] args) {
        ListaDuplamente lista1 = new ListaDuplamente();
        ListaDuplamente lista2 = new ListaDuplamente();
        ListaDuplamente lista3 = new ListaDuplamente();
        ListaDuplamente lista4 = new ListaDuplamente();
        ListaDuplamente lista5 = new ListaDuplamente();
        ListaDuplamente lista6 = new ListaDuplamente();
        ListaDuplamente lista7 = new ListaDuplamente();

        System.out.println("LISTA 1:");
        lista1.geraLista();
        lista1.imprimirLista();
        System.out.println("");

        lista2.copiaLista(lista1);
        lista3.copiaLista(lista1);
        lista4.copiaLista(lista1);
        lista5.copiaLista(lista1);
        lista6.copiaLista(lista1);
        lista7.copiaLista(lista1);

        System.out.println("LISTA 2 SELECTION SORT:");
        lista2.SelectionSort();
        lista2.imprimirLista();
        System.out.println("");

        System.out.println("LISTA 3 INSERTION SORT:");
        lista3.InsertionSort();
        lista3.imprimirLista();
        System.out.println("");

        System.out.println("LISTA 4 BUBBLE SORT:");
        lista4.BubbleSort();
        lista4.imprimirLista();
        System.out.println("");

        System.out.println("LISTA 5 SHAKE SORT:");
        lista5.ShakeSort();
        lista5.imprimirLista();
        System.out.println("");

        System.out.println("LISTA 6 COMB SORT:");
        lista6.CombSort();
        lista6.imprimirLista();
        System.out.println("");

        System.out.println("LISTA 7 COUTING SORT:");
        lista7.CountingSort();
        lista7.imprimirLista();
        System.out.println("");

        // System.out.println("MEIO:");
        // lista3.InsertionBinary();

    }
}
