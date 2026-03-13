public class Principal {
    ArquivoJava arqOrd, arqRev, arqRand, auxArqRand;
    public void gerarTabela() {

        // ================
        // INSERÇÃO DIRETA
        // ================
        arqOrd = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/arquivo-ordenado.dat");
        arqOrd.geraArquivoOrdenado();
        arqOrd.exibirArq();
        System.out.println("");
        arqRev = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/arquivo-reverso.dat");
        arqRev.geraArquivoReverso();
        arqRev.exibirArq();
        System.out.println("");
        arqRand = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/arquivo-randomico.dat");
        auxArqRand = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/copia-arquivo-randomico.dat");
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
