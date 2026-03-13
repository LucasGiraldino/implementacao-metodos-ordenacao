
public class Principal {
    ArquivoJava arquivoOrdenado, arquivoReverso, arquivoRandomico, auxArqRand, auxArqReverso;
    long tempoInicio, tempoFim, tempoTotalOrd, tempoTotalRev, tempoTotalRand;
    int quantidadeCompOrd, quantidadeCompRev, quantidadeCompRand, quantidadeMovOrd, quantidadeMovRev, quantidadeMovRand;

    public int calculaCompInsDirOrd(int tamanho) {
        return tamanho-1;
    }

    public int calculaCompInsDirRev(int tamanho) {
        return (tamanho * (tamanho - 1)) / 2;
    }

    public int calculaCompInsDirRand(int tamanho) {
        return (tamanho * (tamanho - 1)) / 4;
    }

    // public void gerarCabecalhoTabela(PrintWriter pw) {
    //     int colMetodo = 20;
    //     int colDado   = 12;
    //     int colGrupo  = colDado * 5; // 60 chars por grupo

    //     String sep = "-".repeat(colMetodo + 2 + (colGrupo + 2) * 3);

    //     // Linha 1: títulos dos grupos
    //     pw.printf("%-" + colMetodo + "s | %-" + colGrupo + "s | %-" + colGrupo + "s | %-" + colGrupo + "s%n",
    //         "Metodos Ordenacao",
    //         "Arquivo Ordenado",
    //         "Arquivo em Ordem Reversa",
    //         "Arquivo Randomico");

    //     // Linha 2: subcolunas
    //     String subHeader = String.format(
    //         "%"  + colDado + "s" +
    //         "%"  + colDado + "s" +
    //         "%"  + colDado + "s" +
    //         "%"  + colDado + "s" +
    //         "%"  + colDado + "s",
    //         "Comp.Prog", "Comp.Equa", "Mov.Prog", "Mov.Equa", "Tempo"
    //     );

    //     pw.printf("%-" + colMetodo + "s | %s | %s | %s%n",
    //         "",
    //         subHeader,
    //         subHeader,
    //         subHeader);

    //     // Linha separadora
    //     pw.println(sep);
    // }


    public void gerarTabela() {
        arquivoOrdenado = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/arquivo-ordenado.dat");
        arquivoOrdenado.truncate(0);
        arquivoOrdenado.geraArquivoOrdenado();
        arquivoReverso = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/arquivo-reverso.dat");
        // arquivoReverso.truncate(0);
        // arquivoReverso.geraArquivoReverso();
        arquivoRandomico = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/arquivo-randomico.dat");
        // arquivoRandomico.truncate(0);
        // arquivoRandomico.geraArquivoRandom();
        
        auxArqRand = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/copia-arquivo-randomico.dat");
        auxArqReverso = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/copia-arquivo-reverso.dat");
        
        
        // ==========================================
        // INSERÇÃO DIRETA
        // ==========================================

        // ARQUIVO ORDENADO
        // System.out.println("==========================================");
        // System.out.println("INSERÇÃO DIRETA");
        // System.out.println("==========================================");
        // arquivoOrdenado.initComp();
        // arquivoOrdenado.initMov();  
        // arquivoOrdenado.exibirArq();
        // System.out.println("");
        // tempoInicio = System.currentTimeMillis();
        // arquivoOrdenado.InsertionSort();
        // tempoFim = System.currentTimeMillis();
        // quantidadeCompOrd = arquivoOrdenado.getComp();
        // quantidadeMovOrd = arquivoOrdenado.getMov();
        // tempoTotalOrd = tempoFim - tempoInicio;
        // System.out.println("QUANTIDADE DE COMPARACOES: " + quantidadeCompOrd);
        // System.out.println("QUANTIDADE DE MOVIMENTACOES: " + quantidadeMovOrd);
        // System.out.println("TEMPO INICIAL: " + tempoInicio);
        // System.out.println("TEMPO FIM: " + tempoFim);
        // System.out.println("TEMPO TOTAL: " + tempoTotalOrd);
        // arquivoOrdenado.exibirArq();
        // System.out.println("");
        // System.out.println(calculaCompInsDirOrd(arquivoOrdenado.filesize()));

        // // ARQUIVO REVERSO
        // auxArqReverso.truncate(0);
        // auxArqReverso.copiaArquivo(arquivoReverso.getFile());
        // auxArqReverso.initComp();
        // auxArqReverso.initMov();
        // auxArqReverso.exibirArq();
        // System.out.println("");
        // tempoInicio = System.currentTimeMillis();
        // auxArqReverso.InsertionSort();
        // tempoFim = System.currentTimeMillis();
        // quantidadeCompRev = auxArqReverso.getComp();
        // quantidadeMovRev = auxArqReverso.getMov();
        // tempoTotalRev = tempoFim - tempoInicio;
        // System.out.println("QUANTIDADE DE COMPARACOES: " + quantidadeCompRev);
        // System.out.println("QUANTIDADE DE MOVIMENTACOES: " + quantidadeMovRev);
        // System.out.println("TEMPO INICIAL: " + tempoInicio);
        // System.out.println("TEMPO FIM: " + tempoFim);
        // System.out.println("TEMPO TOTAL: " + tempoTotalRev);
        // auxArqReverso.exibirArq();
        // System.out.println("");
        // System.out.println(calculaCompInsDirRev(auxArqReverso.filesize()));
        // System.out.println("");

        // // ARQUIVO RANDOMICO
        // auxArqRand.truncate(0);
        // auxArqRand.copiaArquivo(arquivoRandomico.getFile());
        // auxArqRand.exibirArq();
        // System.out.println("");
        // auxArqRand.initComp();
        // auxArqRand.initMov();
        // tempoInicio = System.currentTimeMillis();
        // auxArqRand.InsertionSort();
        // tempoFim = System.currentTimeMillis();
        // quantidadeCompRand = auxArqRand.getComp();
        // quantidadeMovRand = auxArqRand.getMov();
        // tempoTotalRand = tempoFim - tempoInicio;
        // System.out.println("QUANTIDADE DE COMPARACOES: " + quantidadeCompRand);
        // System.out.println("QUANTIDADE DE MOVIMENTACOES: " + quantidadeMovRand);
        // System.out.println("TEMPO INICIAL: " + tempoInicio);
        // System.out.println("TEMPO FIM: " + tempoFim);
        // System.out.println("TEMPO TOTAL: " + tempoTotalRand);
        // auxArqRand.exibirArq();
        // System.out.println("");
        // System.out.println(calculaCompInsDirRand(auxArqRand.filesize()));
        // System.out.println("");


        // // ==========================================
        // // SELEÇÃO DIRETA
        // // ==========================================

        // // ARQUIVO ORDENADO
        // System.out.println("==========================================");
        // System.out.println("SELEÇÃO DIRETA");
        // System.out.println("==========================================");
        // arquivoOrdenado.initComp();
        // arquivoOrdenado.initMov();  
        // arquivoOrdenado.exibirArq();
        // System.out.println("");
        // tempoInicio = System.currentTimeMillis();
        // arquivoOrdenado.SelectionSort();
        // tempoFim = System.currentTimeMillis();
        // quantidadeCompOrd = arquivoOrdenado.getComp();
        // quantidadeMovOrd = arquivoOrdenado.getMov();
        // tempoTotalOrd = tempoFim - tempoInicio;
        // System.out.println("QUANTIDADE DE COMPARACOES: " + quantidadeCompOrd);
        // System.out.println("QUANTIDADE DE MOVIMENTACOES: " + quantidadeMovOrd);
        // System.out.println("TEMPO INICIAL: " + tempoInicio);
        // System.out.println("TEMPO FIM: " + tempoFim);
        // System.out.println("TEMPO TOTAL: " + tempoTotalOrd);
        // arquivoOrdenado.exibirArq();
        // System.out.println("");
        // // System.out.println(calculaCompInsDirOrd(arquivoOrdenado.filesize()));

        // // ARQUIVO REVERSO
        // auxArqReverso.truncate(0);
        // auxArqReverso.copiaArquivo(arquivoReverso.getFile());
        // auxArqReverso.initComp();
        // auxArqReverso.initMov();
        // auxArqReverso.exibirArq();
        // System.out.println("");
        // tempoInicio = System.currentTimeMillis();
        // auxArqReverso.SelectionSort();
        // tempoFim = System.currentTimeMillis();
        // quantidadeCompRev = auxArqReverso.getComp();
        // quantidadeMovRev = auxArqReverso.getMov();
        // tempoTotalRev = tempoFim - tempoInicio;
        // System.out.println("QUANTIDADE DE COMPARACOES: " + quantidadeCompRev);
        // System.out.println("QUANTIDADE DE MOVIMENTACOES: " + quantidadeMovRev);
        // System.out.println("TEMPO INICIAL: " + tempoInicio);
        // System.out.println("TEMPO FIM: " + tempoFim);
        // System.out.println("TEMPO TOTAL: " + tempoTotalRev);
        // auxArqReverso.exibirArq();
        // System.out.println("");
        // // System.out.println(calculaCompInsDirRev(auxArqReverso.filesize()));
        // System.out.println("");

        // // ARQUIVO RANDOMICO
        // auxArqRand.truncate(0);
        // auxArqRand.copiaArquivo(arquivoRandomico.getFile());
        // auxArqRand.exibirArq();
        // System.out.println("");
        // auxArqRand.initComp();
        // auxArqRand.initMov();
        // tempoInicio = System.currentTimeMillis();
        // auxArqRand.SelectionSort();
        // tempoFim = System.currentTimeMillis();
        // quantidadeCompRand = auxArqRand.getComp();
        // quantidadeMovRand = auxArqRand.getMov();
        // tempoTotalRand = tempoFim - tempoInicio;
        // System.out.println("QUANTIDADE DE COMPARACOES: " + quantidadeCompRand);
        // System.out.println("QUANTIDADE DE MOVIMENTACOES: " + quantidadeMovRand);
        // System.out.println("TEMPO INICIAL: " + tempoInicio);
        // System.out.println("TEMPO FIM: " + tempoFim);
        // System.out.println("TEMPO TOTAL: " + tempoTotalRand);
        // auxArqRand.exibirArq();
        // System.out.println("");
        // // System.out.println(calculaCompInsDirRand(auxArqRand.filesize()));
        // System.out.println("");

        // ==========================================
        // INSERÇÃO BINÁRIA
        // ==========================================

        // ARQUIVO ORDENADO
        System.out.println("==========================================");
        System.out.println("INSERÇÃO BINÁRIA");
        System.out.println("==========================================");
        arquivoOrdenado.initComp();
        arquivoOrdenado.initMov();  
        arquivoOrdenado.exibirArq();
        System.out.println("");
        tempoInicio = System.currentTimeMillis();
        arquivoOrdenado.InsertionBinary();
        tempoFim = System.currentTimeMillis();
        quantidadeCompOrd = arquivoOrdenado.getComp();
        quantidadeMovOrd = arquivoOrdenado.getMov();
        tempoTotalOrd = tempoFim - tempoInicio;
        System.out.println("QUANTIDADE DE COMPARACOES: " + quantidadeCompOrd);
        System.out.println("QUANTIDADE DE MOVIMENTACOES: " + quantidadeMovOrd);
        System.out.println("TEMPO INICIAL: " + tempoInicio);
        System.out.println("TEMPO FIM: " + tempoFim);
        System.out.println("TEMPO TOTAL: " + tempoTotalOrd);
        arquivoOrdenado.exibirArq();
        System.out.println("");
        // System.out.println(calculaCompInsDirOrd(arquivoOrdenado.filesize()));

        // ARQUIVO REVERSO
        auxArqReverso.truncate(0);
        auxArqReverso.copiaArquivo(arquivoReverso.getFile());
        auxArqReverso.initComp();
        auxArqReverso.initMov();
        auxArqReverso.exibirArq();
        System.out.println("");
        tempoInicio = System.currentTimeMillis();
        auxArqReverso.InsertionBinary();
        tempoFim = System.currentTimeMillis();
        quantidadeCompRev = auxArqReverso.getComp();
        quantidadeMovRev = auxArqReverso.getMov();
        tempoTotalRev = tempoFim - tempoInicio;
        System.out.println("QUANTIDADE DE COMPARACOES: " + quantidadeCompRev);
        System.out.println("QUANTIDADE DE MOVIMENTACOES: " + quantidadeMovRev);
        System.out.println("TEMPO INICIAL: " + tempoInicio);
        System.out.println("TEMPO FIM: " + tempoFim);
        System.out.println("TEMPO TOTAL: " + tempoTotalRev);
        auxArqReverso.exibirArq();
        System.out.println("");
        // System.out.println(calculaCompInsDirRev(auxArqReverso.filesize()));
        System.out.println("");

        // ARQUIVO RANDOMICO
        auxArqRand.truncate(0);
        auxArqRand.copiaArquivo(arquivoRandomico.getFile());
        auxArqRand.exibirArq();
        System.out.println("");
        auxArqRand.initComp();
        auxArqRand.initMov();
        tempoInicio = System.currentTimeMillis();
        auxArqRand.InsertionBinary();
        tempoFim = System.currentTimeMillis();
        quantidadeCompRand = auxArqRand.getComp();
        quantidadeMovRand = auxArqRand.getMov();
        tempoTotalRand = tempoFim - tempoInicio;
        System.out.println("QUANTIDADE DE COMPARACOES: " + quantidadeCompRand);
        System.out.println("QUANTIDADE DE MOVIMENTACOES: " + quantidadeMovRand);
        System.out.println("TEMPO INICIAL: " + tempoInicio);
        System.out.println("TEMPO FIM: " + tempoFim);
        System.out.println("TEMPO TOTAL: " + tempoTotalRand);
        auxArqRand.exibirArq();
        System.out.println("");
        // System.out.println(calculaCompInsDirRand(auxArqRand.filesize()));
        System.out.println("");

        // System.out.println("ARQUIVO ORIGINAL: ");
        // arquivoRandomico.exibirArq();
        // System.out.println("");
        // System.out.println("ARQUIVO COPIA NAO ORDENADO: ");
        // auxArqRand.exibirArq();
        // System.out.println("");
        // System.out.println("ORDENACAO");
        // auxArqRand.InsertionSort();
        // auxArqRand.exibirArq();
    }
}
