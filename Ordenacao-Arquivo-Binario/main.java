public class main {
    public static void main(String[] args) {
        // ArquivoJava a = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/arquivo.dat");
        ArquivoJava arqOrd = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/arquivo-ordenado.dat");
        // arqOrd.executa();
        // arqOrd.geraArquivoOrdenado();
        arqOrd.exibirArq();
        ArquivoJava arqRev = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/arquivo-reverso.dat");
        arqRev.geraArquivoReverso();
        arqRev.exibirArq();

    }
}
