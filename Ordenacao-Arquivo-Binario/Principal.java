
import java.io.FileWriter;
import java.io.PrintWriter;

public class Principal {
        ArquivoJava arquivoOrdenado, arquivoReverso, arquivoRandomico, auxArqRand, auxArqReverso;
        long tempoInicio, tempoFim, tempoTotalOrd, tempoTotalRev, tempoTotalRand;
        long quantidadeCompOrd, quantidadeCompRev, quantidadeCompRand, quantidadeMovOrd, quantidadeMovRev,
                        quantidadeMovRand;

        public int calculaCompInsDirOrd(int tamanho) {
                return tamanho - 1;
        }

        public int calculaCompInsDirRev(int tamanho) {
                return (tamanho * (tamanho - 1)) / 2;
        }

        public int calculaCompInsDirRand(int tamanho) {
                return (tamanho * (tamanho - 1)) / 4;
        }

        public int calculaMovInsDirOrd(int tamanho) {
                return 0;
        }

        public void gerarCabecalhoTabela(PrintWriter pw) {
                int colMetodo = 18;
                int colDado = 33;
                int colGrupo = colDado * 5; // 60 chars por grupo

                String sep = "-".repeat(colMetodo + 2 + (colGrupo + 2) * 3);

                // Linha 1: títulos dos grupos
                pw.print(
                                "|--------------------------------------------------------------------------------------------------------------------------------|\n");
                pw.printf("|%-" + colMetodo + "s | %-" + colDado + "s | %-" + colDado + "s | %-" + colDado + "s  | %n",
                                "Metodos Ordenacao",
                                "Arquivo Ordenado",
                                "Arquivo em Ordem Reversa",
                                "Arquivo Randomico");
                pw.print(
                                "|-------------------|-----------------------------------|-----------------------------------|------------------------------------|\n");

                // Linha 2: subcolunas
                String subHeader = String.format(
                                "%" + 4 + "s" +
                                                "%" + 4 + "s" +
                                                "%" + 4 + "s" +
                                                "%" + 4 + "s" +
                                                "%" + 4 + "s",
                                "| CP * | ", "CE # | ", "MP + | ", "ME - | ", "Tempo");

                pw.printf("|%-" + colMetodo + "s %s %s %s  |\n",
                                "",
                                subHeader,
                                subHeader,
                                subHeader);

                // Linha separadora
                // pw.println(sep);
        }

        public void gravarLinhaTabela(PrintWriter pw, String metodo,
                        long compProgO, long compEquaO, long movProgO, long movEquaO, long tempoO,
                        long compProgRev, long compEquaRev, long movProgRev, long movEquaRev, long tempoRev,
                        long compProgRand, long compEquaRand, long movProgRand, long movEquaRand, long tempoRand) {

                pw.print(
                                "|-------------------|-----------------------------------|-----------------------------------|------------------------------------|\n");
                pw.printf(
                                "|%-18s | %4d | %4d | %4d | %4d | %4ds | %4d | %4d | %4d | %4d | %4ds | %4d | %4d | %4d | %4d | %4ds  |%n",
                                metodo,
                                compProgO, compEquaO, movProgO, movEquaO, tempoO,
                                compProgRev, compEquaRev, movProgRev, movEquaRev, tempoRev,
                                compProgRand, compEquaRand, movProgRand, movEquaRand, tempoRand);
                pw.print(
                                "|-------------------|-----------------------------------|-----------------------------------|------------------------------------|\n");
        }

        public void gerarTabela() {
                arquivoOrdenado = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/arquivo-ordenado.dat");
                arquivoOrdenado.truncate(0);
                arquivoOrdenado.geraArquivoOrdenado();
                arquivoReverso = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/arquivo-reverso.dat");
                arquivoReverso.truncate(0);
                arquivoReverso.geraArquivoReverso();
                arquivoRandomico = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/arquivo-randomico.dat");
                arquivoRandomico.truncate(0);
                arquivoRandomico.geraArquivoRandom();

                auxArqRand = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/copia-arquivo-randomico.dat");
                auxArqReverso = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/copia-arquivo-reverso.dat");

                try {
                        PrintWriter pw = new PrintWriter(
                                        new FileWriter("./Ordenacao-Arquivo-Binario/arquivos/tabela.txt"));
                        gerarCabecalhoTabela(pw);

                        // ===========================================
                        // INSERÇÃO DIRETA
                        // ==========================================

                        // ARQUIVO ORDENADO
                        System.out.println("==========================================");
                        System.out.println("INSERÇÃO DIRETA");
                        System.out.println("==========================================");
                        arquivoOrdenado.initComp();
                        arquivoOrdenado.initMov();
                        arquivoOrdenado.exibirArq();
                        System.out.println("");
                        tempoInicio = System.currentTimeMillis();
                        arquivoOrdenado.InsertionSort();
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
                        System.out.println(calculaCompInsDirOrd(arquivoOrdenado.filesize()));

                        // ARQUIVO REVERSO
                        auxArqReverso.truncate(0);
                        auxArqReverso.copiaArquivo(arquivoReverso.getFile());
                        auxArqReverso.initComp();
                        auxArqReverso.initMov();
                        auxArqReverso.exibirArq();
                        System.out.println("");
                        tempoInicio = System.currentTimeMillis();
                        auxArqReverso.InsertionSort();
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
                        System.out.println(calculaCompInsDirRev(auxArqReverso.filesize()));
                        System.out.println("");

                        // ARQUIVO RANDOMICO
                        auxArqRand.truncate(0);
                        auxArqRand.copiaArquivo(arquivoRandomico.getFile());
                        auxArqRand.exibirArq();
                        System.out.println("");
                        auxArqRand.initComp();
                        auxArqRand.initMov();
                        tempoInicio = System.currentTimeMillis();
                        auxArqRand.InsertionSort();
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
                        System.out.println(calculaCompInsDirRand(auxArqRand.filesize()));
                        System.out.println("");
                        gravarLinhaTabela(pw, "Inserção Direta", quantidadeCompOrd,
                                        calculaCompInsDirOrd(arquivoOrdenado.filesize()), quantidadeMovOrd, 0,
                                        tempoTotalOrd,
                                        quantidadeCompRev, calculaCompInsDirRev(auxArqReverso.filesize()),
                                        quantidadeMovRev, calculaCompInsDirRev(auxArqReverso.filesize()),
                                        tempoTotalRev, quantidadeCompRand, calculaCompInsDirRand(auxArqRand.filesize()),
                                        quantidadeMovRand,
                                        calculaCompInsDirRand(auxArqRand.filesize()), tempoTotalRand);

                        // ==========================================
                        // SELEÇÃO DIRETA
                        // ==========================================

                        // ARQUIVO ORDENADO
                        System.out.println("==========================================");
                        System.out.println("SELEÇÃO DIRETA");
                        System.out.println("==========================================");
                        arquivoOrdenado.initComp();
                        arquivoOrdenado.initMov();
                        arquivoOrdenado.exibirArq();
                        System.out.println("");
                        tempoInicio = System.currentTimeMillis();
                        arquivoOrdenado.SelectionSort();
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
                        auxArqReverso.SelectionSort();
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
                        auxArqRand.SelectionSort();
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
                        gravarLinhaTabela(pw, "Seleção Direta", quantidadeCompOrd,
                                        calculaCompInsDirOrd(arquivoOrdenado.filesize()), quantidadeMovOrd, 0,
                                        tempoTotalOrd,
                                        quantidadeCompRev, calculaCompInsDirRev(auxArqReverso.filesize()),
                                        quantidadeMovRev, 0,
                                        tempoTotalRev, quantidadeCompRand, calculaCompInsDirRand(auxArqRand.filesize()),
                                        quantidadeMovRand,
                                        0, tempoTotalRand);

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
                        gravarLinhaTabela(pw, "Inserção Binária", quantidadeCompOrd,
                                        calculaCompInsDirOrd(arquivoOrdenado.filesize()), quantidadeMovOrd, 0,
                                        tempoTotalOrd,
                                        quantidadeCompRev, calculaCompInsDirRev(auxArqReverso.filesize()),
                                        quantidadeMovRev, 0,
                                        tempoTotalRev, quantidadeCompRand, calculaCompInsDirRand(auxArqRand.filesize()),
                                        quantidadeMovRand,
                                        0, tempoTotalRand);

                        // ==========================================
                        // BUBBLE SORT
                        // ==========================================

                        // ARQUIVO ORDENADO
                        System.out.println("==========================================");
                        System.out.println("BUBBLE SORT");
                        System.out.println("==========================================");
                        arquivoOrdenado.initComp();
                        arquivoOrdenado.initMov();
                        arquivoOrdenado.exibirArq();
                        System.out.println("");
                        tempoInicio = System.currentTimeMillis();
                        arquivoOrdenado.BubbleSort();
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
                        auxArqReverso.BubbleSort();
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
                        auxArqRand.BubbleSort();
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
                        gravarLinhaTabela(pw, "Bubble Sort", quantidadeCompOrd,
                                        calculaCompInsDirOrd(arquivoOrdenado.filesize()), quantidadeMovOrd, 0,
                                        tempoTotalOrd,
                                        quantidadeCompRev, calculaCompInsDirRev(auxArqReverso.filesize()),
                                        quantidadeMovRev, 0,
                                        tempoTotalRev, quantidadeCompRand, calculaCompInsDirRand(auxArqRand.filesize()),
                                        quantidadeMovRand,
                                        0, tempoTotalRand);

                        // ==========================================
                        // COMB SORT
                        // ==========================================

                        // ARQUIVO ORDENADO
                        System.out.println("==========================================");
                        System.out.println("COMB SORT");
                        System.out.println("==========================================");
                        arquivoOrdenado.initComp();
                        arquivoOrdenado.initMov();
                        arquivoOrdenado.exibirArq();
                        System.out.println("");
                        tempoInicio = System.currentTimeMillis();
                        arquivoOrdenado.CombSort();
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
                        auxArqReverso.CombSort();
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
                        auxArqRand.CombSort();
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
                        gravarLinhaTabela(pw, "Comb Sort", quantidadeCompOrd,
                                        calculaCompInsDirOrd(arquivoOrdenado.filesize()), quantidadeMovOrd, 0,
                                        tempoTotalOrd,
                                        quantidadeCompRev, calculaCompInsDirRev(auxArqReverso.filesize()),
                                        quantidadeMovRev, 0,
                                        tempoTotalRev, quantidadeCompRand, calculaCompInsDirRand(auxArqRand.filesize()),
                                        quantidadeMovRand,
                                        0, tempoTotalRand);

                        // ==========================================
                        // SHAKE SORT
                        // ==========================================

                        // ARQUIVO ORDENADO
                        System.out.println("==========================================");
                        System.out.println("SHAKE SORT");
                        System.out.println("==========================================");
                        arquivoOrdenado.initComp();
                        arquivoOrdenado.initMov();
                        arquivoOrdenado.exibirArq();
                        System.out.println("");
                        tempoInicio = System.currentTimeMillis();
                        arquivoOrdenado.ShakeSort();
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
                        auxArqReverso.ShakeSort();
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
                        auxArqRand.ShakeSort();
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
                        gravarLinhaTabela(pw, "Shake Sort", quantidadeCompOrd,
                                        calculaCompInsDirOrd(arquivoOrdenado.filesize()), quantidadeMovOrd, 0,
                                        tempoTotalOrd,
                                        quantidadeCompRev, calculaCompInsDirRev(auxArqReverso.filesize()),
                                        quantidadeMovRev, 0,
                                        tempoTotalRev, quantidadeCompRand, calculaCompInsDirRand(auxArqRand.filesize()),
                                        quantidadeMovRand,
                                        0, tempoTotalRand);

                        // ===========================================
                        // COUNTING SORT
                        // ==========================================

                        // ARQUIVO ORDENADO
                        System.out.println("==========================================");
                        System.out.println("COUNTING SORT");
                        System.out.println("==========================================");
                        arquivoOrdenado.initComp();
                        arquivoOrdenado.initMov();
                        arquivoOrdenado.exibirArq();
                        System.out.println("");
                        tempoInicio = System.currentTimeMillis();
                        arquivoOrdenado.CountingSort();
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
                        System.out.println(calculaCompInsDirOrd(arquivoOrdenado.filesize()));

                        // ARQUIVO REVERSO
                        auxArqReverso.truncate(0);
                        auxArqReverso.copiaArquivo(arquivoReverso.getFile());
                        auxArqReverso.initComp();
                        auxArqReverso.initMov();
                        auxArqReverso.exibirArq();
                        System.out.println("");
                        tempoInicio = System.currentTimeMillis();
                        auxArqReverso.CountingSort();
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
                        System.out.println(calculaCompInsDirRev(auxArqReverso.filesize()));
                        System.out.println("");

                        // ARQUIVO RANDOMICO
                        auxArqRand.truncate(0);
                        auxArqRand.copiaArquivo(arquivoRandomico.getFile());
                        auxArqRand.exibirArq();
                        System.out.println("");
                        auxArqRand.initComp();
                        auxArqRand.initMov();
                        tempoInicio = System.currentTimeMillis();
                        auxArqRand.CountingSort();
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
                        System.out.println(calculaCompInsDirRand(auxArqRand.filesize()));
                        System.out.println("");
                        gravarLinhaTabela(pw, "Couting Sort", quantidadeCompOrd,
                                        calculaCompInsDirOrd(arquivoOrdenado.filesize()), quantidadeMovOrd, 0,
                                        tempoTotalOrd,
                                        quantidadeCompRev, calculaCompInsDirRev(auxArqReverso.filesize()),
                                        quantidadeMovRev, calculaCompInsDirRev(auxArqReverso.filesize()),
                                        tempoTotalRev, quantidadeCompRand,
                                        calculaCompInsDirRand(auxArqRand.filesize()), quantidadeMovRand,
                                        calculaCompInsDirRand(auxArqRand.filesize()), tempoTotalRand);

                        // ==========================================
                        // QUICK SORT SEM PIVO
                        // ==========================================

                        // ARQUIVO ORDENADO
                        System.out.println("==========================================");
                        System.out.println("QUICK SORT SEM PIVO");
                        System.out.println("==========================================");
                        arquivoOrdenado.initComp();
                        arquivoOrdenado.initMov();
                        arquivoOrdenado.exibirArq();
                        System.out.println("");
                        tempoInicio = System.currentTimeMillis();
                        arquivoOrdenado.QuickSortSemPivo();
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
                        auxArqReverso.QuickSortSemPivo();
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
                        auxArqRand.QuickSortSemPivo();
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
                        gravarLinhaTabela(pw, "Quick Sort s/ Pivô", quantidadeCompOrd,
                                        calculaCompInsDirOrd(arquivoOrdenado.filesize()), quantidadeMovOrd, 0,
                                        tempoTotalOrd,
                                        quantidadeCompRev, calculaCompInsDirRev(auxArqReverso.filesize()),
                                        quantidadeMovRev, 0,
                                        tempoTotalRev, quantidadeCompRand, calculaCompInsDirRand(auxArqRand.filesize()),
                                        quantidadeMovRand,
                                        0, tempoTotalRand);

                        // ==========================================
                        // HEAP SORT
                        // ==========================================

                        // ARQUIVO ORDENADO
                        System.out.println("==========================================");
                        System.out.println("HEAP SORT");
                        System.out.println("==========================================");
                        arquivoOrdenado.initComp();
                        arquivoOrdenado.initMov();
                        arquivoOrdenado.exibirArq();
                        System.out.println("");
                        tempoInicio = System.currentTimeMillis();
                        arquivoOrdenado.HeapSort();
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
                        auxArqReverso.HeapSort();
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
                        auxArqRand.HeapSort();
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
                        gravarLinhaTabela(pw, "Heap Sort", quantidadeCompOrd,
                                        calculaCompInsDirOrd(arquivoOrdenado.filesize()), quantidadeMovOrd, 0,
                                        tempoTotalOrd,
                                        quantidadeCompRev, calculaCompInsDirRev(auxArqReverso.filesize()),
                                        quantidadeMovRev, 0,
                                        tempoTotalRev, quantidadeCompRand, calculaCompInsDirRand(auxArqRand.filesize()),
                                        quantidadeMovRand,
                                        0, tempoTotalRand);

                        // ==========================================
                        // QUICK SORT
                        // ==========================================

                        // ARQUIVO ORDENADO
                        System.out.println("==========================================");
                        System.out.println("QUICK SORT COM PIVO");
                        System.out.println("==========================================");
                        arquivoOrdenado.initComp();
                        arquivoOrdenado.initMov();
                        arquivoOrdenado.exibirArq();
                        System.out.println("");
                        tempoInicio = System.currentTimeMillis();
                        arquivoOrdenado.QuickSortComPivo();
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
                        auxArqReverso.QuickSortComPivo();
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
                        auxArqRand.QuickSortComPivo();
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
                        gravarLinhaTabela(pw, "Quick Sort c/ Pivo", quantidadeCompOrd,
                                        calculaCompInsDirOrd(arquivoOrdenado.filesize()), quantidadeMovOrd, 0,
                                        tempoTotalOrd,
                                        quantidadeCompRev, calculaCompInsDirRev(auxArqReverso.filesize()),
                                        quantidadeMovRev, 0,
                                        tempoTotalRev, quantidadeCompRand, calculaCompInsDirRand(auxArqRand.filesize()),
                                        quantidadeMovRand,
                                        0, tempoTotalRand);

                        // ==========================================
                        // BUCKET SORT
                        // ==========================================

                        // ARQUIVO ORDENADO
                        System.out.println("==========================================");
                        System.out.println("BUCKET SORT");
                        System.out.println("==========================================");
                        arquivoOrdenado.initComp();
                        arquivoOrdenado.initMov();
                        arquivoOrdenado.exibirArq();
                        System.out.println("");
                        tempoInicio = System.currentTimeMillis();
                        arquivoOrdenado.BucketSort();
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
                        auxArqReverso.BucketSort();
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
                        auxArqRand.BucketSort();
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
                        gravarLinhaTabela(pw, "Bucket Sort", quantidadeCompOrd,
                                        calculaCompInsDirOrd(arquivoOrdenado.filesize()), quantidadeMovOrd, 0,
                                        tempoTotalOrd,
                                        quantidadeCompRev, calculaCompInsDirRev(auxArqReverso.filesize()),
                                        quantidadeMovRev, 0,
                                        tempoTotalRev, quantidadeCompRand, calculaCompInsDirRand(auxArqRand.filesize()),
                                        quantidadeMovRand,
                                        0, tempoTotalRand);

                        // ==========================================
                        // GNOME SORT
                        // ==========================================

                        // ARQUIVO ORDENADO
                        System.out.println("==========================================");
                        System.out.println("GNOME SORT");
                        System.out.println("==========================================");
                        arquivoOrdenado.initComp();
                        arquivoOrdenado.initMov();
                        arquivoOrdenado.exibirArq();
                        System.out.println("");
                        tempoInicio = System.currentTimeMillis();
                        arquivoOrdenado.SelectionSort();
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
                        auxArqReverso.SelectionSort();
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
                        auxArqRand.SelectionSort();
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
                        gravarLinhaTabela(pw, "Gnome Sort", quantidadeCompOrd,
                                        calculaCompInsDirOrd(arquivoOrdenado.filesize()), quantidadeMovOrd, 0,
                                        tempoTotalOrd,
                                        quantidadeCompRev, calculaCompInsDirRev(auxArqReverso.filesize()),
                                        quantidadeMovRev, 0,
                                        tempoTotalRev, quantidadeCompRand, calculaCompInsDirRand(auxArqRand.filesize()),
                                        quantidadeMovRand,
                                        0, tempoTotalRand);

                        pw.flush();
                        pw.close();
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                }
        }
}
