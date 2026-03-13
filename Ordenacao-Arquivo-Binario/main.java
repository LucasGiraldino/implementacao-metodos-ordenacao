public class main {
    public static void main(String[] args) {
        // ArquivoJava arqOrd = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/arquivo-ordenado.dat");
        // arqOrd.geraArquivoOrdenado();
        // arqOrd.exibirArq();
        // System.out.println("");
        // ArquivoJava arqRev = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/arquivo-reverso.dat");
        // arqRev.geraArquivoReverso();
        // arqRev.exibirArq();
        System.out.println("");
        ArquivoJava arqRand = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/arquivo-randomico.dat");
        ArquivoJava auxArqRand = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/copia-arquivo-randomico.dat");
        arqRand.truncate(0);
        auxArqRand.truncate(0);
        arqRand.geraArquivoRandom();
        arqRand.seekArq(0);
        auxArqRand.copiaArquivo(arqRand.getFile());
        System.out.println("ARQUIVO ORIGINAL: ");
        arqRand.exibirArq();
        System.out.println("");
        System.out.println("ARQUIVO COPIA NAO ORDENADO: ");
        auxArqRand.exibirArq();
        System.out.println("");
        System.out.println("ORDENACAO");
        auxArqRand.InsertionSort();
        auxArqRand.exibirArq();
        

    }
}
