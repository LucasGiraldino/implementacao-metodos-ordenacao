import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class ArquivoJava {
    private String nomeArquivo;
    private RandomAccessFile arquivo;
    private int comp, mov;

    public int getComp() {
        return comp;
    }

    public RandomAccessFile getFile() {
        return arquivo;
    }

    public int getMov() {
        return mov;
    }

    public void setComp(int comp) {
        this.comp = comp;
    }

    public void setMov(int mov) {
        this.mov = mov;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomearquivo) {
        this.nomeArquivo = nomearquivo;
    }

    public void initMov() {
        this.mov = 0;
    }

    public void initComp() {
        this.comp = 0;
    }

    public void addComp() {
        this.comp = this.comp + 1;
    }

    public void addMov() {
        this.mov = this.mov + 2;
    }

    public ArquivoJava(String nomearquivo) {
        try {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        } catch (IOException e) {
        }
    }

    public void copiaArquivo(RandomAccessFile arquivoOrigem) {
        try {
            arquivoOrigem.seek(0);
            Registro reg = new Registro();
            while (arquivoOrigem.getFilePointer() < arquivoOrigem.length()) {
                reg.leDoArq(arquivoOrigem);
                reg.gravaNoArq(arquivo);
            }
        } catch (IOException e) {
        }
    }

    public void truncate(long pos) // desloca eof
    {
        try {
            arquivo.setLength(pos * Registro.length());
        } catch (IOException exc) {
        }
    }

    public boolean eof() {
        boolean retorno = false;
        try {
            if (arquivo.getFilePointer() == arquivo.length())
                retorno = true;
        } catch (IOException e) {
        }
        return (retorno);
    }

    public void inserirRegNoFinal(Registro reg) {
        seekArq(filesize());// ultimo byte
        reg.gravaNoArq(arquivo);
    }

    public void exibirArq() {
        // int i;
        Registro aux = new Registro();
        seekArq(0);
        // i = 0;
        while (!this.eof()) {
            // System.out.println("Posicao " + i);
            aux.leDoArq(arquivo);
            aux.exibirReg();
            // i++;
        }
    }

    public void exibirUmRegistro(int pos) {
        Registro aux = new Registro();
        seekArq(pos);
        System.out.println("Posicao " + pos);
        aux.leDoArq(arquivo);
        aux.exibirReg();
    }

    public void seekArq(int pos) {
        try {
            arquivo.seek(pos * Registro.length());
        } catch (IOException e) {
        }
    }

    public void leArq() {
        int numero;
        numero = Entrada.leInteger("Digite o número");
        while (numero != 0) {
            inserirRegNoFinal(new Registro(numero));
            numero = Entrada.leInteger("Digite o número");
        }
    }

    public int filesize() {
        try {
            return (int) arquivo.length() / Registro.length();
        } catch (IOException e) {
            return -1;
        }
    }

    public void geraArquivoOrdenado() {
        try {
            for (int i = 1; i <= 10; i++) {
                Registro reg = new Registro(i);
                reg.gravaNoArq(arquivo);
            }
        } catch (Exception e) {
        }
    }

    public void geraArquivoReverso() {
        try {
            for (int i = 10; i > 0; i--) {
                Registro reg = new Registro(i);
                reg.gravaNoArq(arquivo);
            }
        } catch (Exception e) {
        }
    }

    public void geraArquivoRandom() {
        try {
            for (int i = 1; i <= 10; i++) {
                Registro reg = new Registro(new Random().nextInt(10));
                reg.gravaNoArq(arquivo);
            }
        } catch (Exception e) {

        }
    }

    public void executa() {
        // leArq();
        // geraArquivoOrdenado();
        exibirArq();
    }

    public int buscaBinaria(int info, int fim) {
        int inicio = 0, meio;
        Registro regMeio = new Registro(), regAux = new Registro();
        while (inicio < fim) {
            meio = (inicio + fim) / 2;
            seekArq(meio);
            regMeio.leDoArq(arquivo);
            addComp();
            if (regMeio.getNumero() < info)
                inicio = meio + 1;
            else
                fim = meio;
        }
        if (inicio < filesize()) {
            seekArq(inicio);
            regAux.leDoArq(arquivo);
            if (regAux.getNumero() >= info)
                return inicio;
        }
        return -1;
    }

    public int getMaiorElementoArq() {
        Registro regAux = new Registro();
        int maior = 0;
        seekArq(0);
        while (!eof()) {
            regAux.leDoArq(arquivo);
            if (regAux.getNumero() > maior)
                maior = regAux.getNumero();
        }
        return maior;
    }

    // ===================================================
    // METODOS DE ORDENAÇÃO
    // ===================================================

    public void InsertionSort() {
        Registro registroI = new Registro(), registroJ = new Registro();
        int posicao, aux;

        for (int i = 1; i < filesize(); i++) {
            seekArq(i);
            registroI.leDoArq(arquivo);
            seekArq(i - 1);
            registroJ.leDoArq(arquivo);
            posicao = i;
            addComp();
            while (posicao > 0 && registroI.getNumero() < registroJ.getNumero()) {
                aux = registroJ.getNumero();
                registroJ.setNumero(registroI.getNumero());
                registroI.setNumero(aux);
                seekArq(posicao);
                registroI.gravaNoArq(arquivo);
                seekArq(posicao - 1);
                registroJ.gravaNoArq(arquivo);
                addMov();
                posicao--;

                if (posicao > 0) {
                    seekArq(posicao);
                    registroI.leDoArq(arquivo);
                    seekArq(posicao - 1);
                    registroJ.leDoArq(arquivo);
                    addComp();
                }

            }
        }
    }

    public void SelectionSort() {
        Registro registorI = new Registro(), registroJ = new Registro();
        int posMenor, infoMenor;

        for (int i = 0; i < filesize() - 1; i++) {
            seekArq(i);
            registorI.leDoArq(arquivo);
            infoMenor = registorI.getNumero();
            posMenor = i;
            for (int j = i + 1; j < filesize(); j++) {
                seekArq(j);
                registroJ.leDoArq(arquivo);
                addComp();
                if (registroJ.getNumero() < infoMenor) {
                    infoMenor = registroJ.getNumero();
                    posMenor = j;
                }
            }
            // addComp();
            if (registorI.getNumero() != infoMenor) {
                seekArq(posMenor);
                registroJ.leDoArq(arquivo);
                seekArq(posMenor);
                registorI.gravaNoArq(arquivo);
                seekArq(i);
                registroJ.gravaNoArq(arquivo);
                addMov();
            }
        }
    }

    public void InsertionBinary() {
        Registro registro = new Registro(), registroAntI = new Registro(), registroGravar;
        int pos, aux, posI;
        for (int i = 1; i < filesize(); i++) {
            seekArq(i);
            registro.leDoArq(arquivo);
            aux = registro.getNumero();
            pos = buscaBinaria(aux, i);
            if (pos == -1)
                pos = i;
            posI = i;
            while (posI > pos) {
                seekArq(posI - 1);
                registroAntI.leDoArq(arquivo);
                seekArq(posI);
                registroAntI.gravaNoArq(arquivo);
                addMov();
                posI--;
            }
            seekArq(pos);
            registroGravar = new Registro(aux);
            registroGravar.gravaNoArq(arquivo);
        }
    }

    public void BubbleSort() {
        int aux;
        Registro regI = new Registro(), regJ = new Registro();
        boolean flag = true;
        for (int TL = filesize(); TL > 0 && flag; TL--) {
            flag = false;
            for (int i = 0; i < TL; i++) {
                seekArq(i);
                regI.leDoArq(arquivo);
                regJ.leDoArq(arquivo);
                addComp();
                if (regI.getNumero() > regJ.getNumero()) {
                    aux = regI.getNumero();
                    regI.setNumero(regJ.getNumero());
                    regJ.setNumero(aux);
                    addMov();
                    seekArq(i);
                    regI.gravaNoArq(arquivo);
                    regJ.gravaNoArq(arquivo);
                    flag = true;
                }
            }
        }
    }

    public void CombSort() {
        Registro registroFim = new Registro(), registroInicio = new Registro();
        int comb = filesize(), aux, TL = filesize();

        while (comb > 0) {
            comb = comb * 10 / 13;
            for (int i = comb, j = 0; i <= TL; i++, j++) {
                seekArq(i);
                registroFim.leDoArq(arquivo);
                seekArq(j);
                registroInicio.leDoArq(arquivo);
                addComp();
                if (registroInicio.getNumero() > registroFim.getNumero()) {
                    aux = registroInicio.getNumero();
                    registroInicio.setNumero(registroFim.getNumero());
                    registroFim.setNumero(aux);

                    seekArq(i);
                    registroFim.gravaNoArq(arquivo);
                    seekArq(j);
                    registroInicio.gravaNoArq(arquivo);

                    addMov();
                }
            }
        }
    }

    public void ShakeSort() {
        boolean flag = true;
        int inicio = 0, fim = filesize(), aux;
        Registro registroAux = new Registro(), registroProx = new Registro();

        while (inicio < fim && flag) {
            flag = false;
            for (int i = inicio; i < fim; i++) {
                seekArq(i);
                registroAux.leDoArq(arquivo);
                registroProx.leDoArq(arquivo);
                addComp();
                if (registroAux.getNumero() > registroProx.getNumero()) {
                    aux = registroAux.getNumero();
                    registroAux.setNumero(registroProx.getNumero());
                    registroProx.setNumero(aux);

                    flag = true;
                    seekArq(i);
                    registroAux.gravaNoArq(arquivo);
                    registroProx.gravaNoArq(arquivo);
                    addMov();
                }
            }
            fim--;
            if (flag) {
                flag = false;
                for (int i = fim; i > inicio; i--) {
                    seekArq(i);
                    registroAux.leDoArq(arquivo);
                    seekArq(i - 1);
                    registroProx.leDoArq(arquivo);
                    addComp();
                    if (registroAux.getNumero() < registroProx.getNumero()) {
                        aux = registroAux.getNumero();
                        registroAux.setNumero(registroProx.getNumero());
                        registroProx.setNumero(aux);

                        seekArq(i);
                        registroAux.gravaNoArq(arquivo);
                        seekArq(i - 1);
                        registroProx.gravaNoArq(arquivo);
                        flag = true;

                        addMov();
                    }
                }
                inicio++;
            }
        }
    }

    public void CountingSort() {
        int maior = getMaiorElementoArq();
        int TLIndice = maior + 1, posArq, vetContagens[] = new int[TLIndice];
        Registro regAux = new Registro();

        for (int i = 0; i < TLIndice; i++) {
            vetContagens[i] = 0;
        }

        for (int i = 0; i < filesize(); i++) {
            seekArq(i);
            regAux.leDoArq(arquivo);
            vetContagens[regAux.getNumero()]++;
        }

        for (int i = 1; i < TLIndice; i++)
            vetContagens[i] += vetContagens[i - 1];
        ArquivoJava aux = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/aux-counting.dat");
        aux.truncate(0);

        for (int i = filesize() - 1; i >= 0; i--) {
            seekArq(i);
            regAux.leDoArq(arquivo);
            posArq = --vetContagens[regAux.getNumero()];

            aux.seekArq(posArq);
            regAux.gravaNoArq(aux.getFile());
            addMov();
        }
        for (int i = 0; i < filesize(); i++) {
            aux.seekArq(i);
            regAux.leDoArq(aux.getFile());

            seekArq(i);
            regAux.gravaNoArq(arquivo);
            addMov();
        }

        try {
            aux.getFile().close();
            java.io.File fileAux = new java.io.File("./Ordenacao-Arquivo-Binario/arquivos/aux-counting.dat");
            fileAux.delete();
        } catch (Exception e) {
        }
    }

    public void BucketSort() {
        int maior = getMaiorElementoArq(), bucketoNo, tamanho = filesize(), posArq;
        Registro regAux = new Registro(), regBucket = new Registro();
        ArquivoJava listaBucket[] = new ArquivoJava[tamanho];

        for (int i = 0; i < tamanho; i++) {
            seekArq(i);
            regAux.leDoArq(arquivo);

            bucketoNo = (tamanho * regAux.getNumero()) / (maior + 1);
            if (listaBucket[bucketoNo] == null) {
                listaBucket[bucketoNo] = new ArquivoJava(
                        "./Ordenacao-Arquivo-Binario/arquivos/bucket" + bucketoNo + ".dat");
                listaBucket[bucketoNo].truncate(0);
            }
            listaBucket[bucketoNo].inserirRegNoFinal(regAux);
            addMov();
        }

        posArq = 0;
        for (int i = 0; i < tamanho; i++) {
            if (listaBucket[i] != null) {
                listaBucket[i].InsertionSort();

                this.comp += listaBucket[i].getComp();
                this.mov += listaBucket[i].getMov();

                int bucketSize = listaBucket[i].filesize();
                for (int j = 0; j < bucketSize; j++) {
                    listaBucket[i].seekArq(j);
                    regBucket.leDoArq(listaBucket[i].getFile());

                    seekArq(posArq);
                    regBucket.gravaNoArq(arquivo);
                    addMov();
                    posArq++;
                }

            }
        }
    }

    public void HeapSort() {
        int TL = filesize(), posPai, pos, maior, aux, posMaior;
        Registro regPai = new Registro(), regFilho1 = new Registro(), regFilho2 = new Registro();

        while (TL > 1) {
            posPai = TL / 2 - 1;
            while (posPai >= 0) {
                seekArq(posPai);
                regPai.leDoArq(arquivo);
                pos = posPai * 2 + 1;
                seekArq(pos);
                regFilho1.leDoArq(arquivo);
                maior = regFilho1.getNumero();
                posMaior = pos;
                if (pos + 1 < TL) {
                    seekArq(pos + 1);
                    regFilho2.leDoArq(arquivo);
                    addComp();
                    if (regFilho2.getNumero() > regFilho1.getNumero()) {
                        maior = regFilho2.getNumero();
                        posMaior = pos + 1;
                    }
                }
                addComp();
                if (maior > regPai.getNumero()) {
                    aux = regPai.getNumero();
                    regPai.setNumero(maior);
                    seekArq(posPai);
                    regPai.gravaNoArq(arquivo);
                    seekArq(posMaior);
                    regPai.leDoArq(arquivo);
                    regPai.setNumero(aux);
                    seekArq(posMaior);
                    regPai.gravaNoArq(arquivo);
                    addMov();
                }
                posPai--;
            }
            seekArq(TL - 1);
            regFilho1.leDoArq(arquivo);
            seekArq(0);
            regFilho2.leDoArq(arquivo);
            seekArq(TL - 1);
            regFilho2.gravaNoArq(arquivo);
            seekArq(0);
            regFilho1.gravaNoArq(arquivo);
            addMov();
            TL--;
        }
    }

    public void QuickSortSemPivo() {
        QuickSP(0, filesize());
    }

    public void QuickSP(int ini, int fim) {
        int aux, auxIni = ini, auxFim = fim;
        boolean flag = true;
        Registro regIni = new Registro(), regFim = new Registro();
        while (auxIni < auxFim) {
            seekArq(auxIni);
            regIni.leDoArq(arquivo);
            seekArq(auxFim);
            regFim.leDoArq(arquivo);
            if (flag)
                while (auxIni < auxFim && regIni.getNumero() <= regFim.getNumero()) {
                    addComp();
                    auxIni++;
                    seekArq(auxIni);
                    regIni.leDoArq(arquivo);
                }
            else
                while (auxIni < auxFim && regFim.getNumero() >= regIni.getNumero()) {
                    addComp();
                    auxFim--;
                    seekArq(auxFim);
                    regFim.leDoArq(arquivo);
                }
            aux = regIni.getNumero();
            regIni.setNumero(regFim.getNumero());
            regFim.setNumero(aux);

            seekArq(auxIni);
            regIni.gravaNoArq(arquivo);
            seekArq(auxFim);
            regFim.gravaNoArq(arquivo);
            addMov();
        }
        if (ini < auxIni - 1)
            QuickSP(ini, auxIni - 1);
        if (auxFim + 1 < fim)
            QuickSP(auxFim + 1, fim);
    }

    public void QuickSortComPivo() {
        QuickCP(0, filesize() - 1);
    }

    public void QuickCP(int ini, int fim) {
        int i = ini, j = fim, pivo, aux;
        Registro regI = new Registro(), regJ = new Registro(), regPivo = new Registro();

        seekArq((ini + fim) / 2);
        regPivo.leDoArq(arquivo);
        pivo = regPivo.getNumero();

        while (i <= j) {
            seekArq(i);
            regI.leDoArq(arquivo);
            addComp();
            while (regI.getNumero() < pivo) {
                i++;
                seekArq(i);
                regI.leDoArq(arquivo);
                addComp();
            }

            seekArq(j);
            regJ.leDoArq(arquivo);
            addComp();
            while (regJ.getNumero() > pivo) {
                j--;
                seekArq(j);
                regJ.leDoArq(arquivo);
                addComp();
            }

            if (i <= j) {
                aux = regI.getNumero();
                regI.setNumero(regJ.getNumero());
                regJ.setNumero(aux);

                seekArq(i);
                regI.gravaNoArq(arquivo);
                seekArq(j);
                regJ.gravaNoArq(arquivo);
                addMov();

                i++;
                j--;
            }
        }

        if (ini < j)
            QuickCP(ini, j);
        if (i < fim)
            QuickCP(i, fim);
    }

    public void GnomeSort() {
        int pos = 1, aux, marca;
        boolean flag;
        Registro regAux = new Registro(), regAnt = new Registro();
        while (pos < filesize()) {
            seekArq(pos);
            regAux.leDoArq(arquivo);
            seekArq(pos - 1);
            regAnt.leDoArq(arquivo);
            addComp();
            if (regAux.getNumero() < regAnt.getNumero()) {
                aux = regAux.getNumero();
                regAux.setNumero(regAnt.getNumero());
                regAnt.setNumero(aux);
                seekArq(pos);
                regAux.gravaNoArq(arquivo);
                seekArq(pos - 1);
                regAnt.gravaNoArq(arquivo);
                addMov();
                marca = pos;
                pos--;
                flag = true;
                while (pos > 0 && flag) {
                    seekArq(pos);
                    regAux.leDoArq(arquivo);
                    seekArq(pos - 1);
                    regAnt.leDoArq(arquivo);
                    addComp();
                    flag = true;
                    if (regAux.getNumero() < regAnt.getNumero()) {
                        aux = regAux.getNumero();
                        regAux.setNumero(regAnt.getNumero());
                        regAnt.setNumero(aux);
                        seekArq(pos);
                        regAux.gravaNoArq(arquivo);
                        seekArq(pos - 1);
                        regAnt.gravaNoArq(arquivo);
                        addMov();
                        flag = false;
                    }
                    pos--;
                }
                pos = marca;
            }
            pos++;
        }
    }

    public void ShellSort() {
        int intervalo = 1, posicaoI, aux, posicaoj;
        Registro regI = new Registro(), regJ = new Registro();
        boolean flag;
        while (intervalo < filesize())
            intervalo = intervalo * 3 + 1;
        intervalo /= 3;
        while (intervalo > 0) {
            posicaoI = intervalo;
            while (posicaoI < filesize()) {
                seekArq(posicaoI);
                regI.leDoArq(arquivo);
                aux = regI.getNumero();
                posicaoj = posicaoI;
                flag = true;
                while (posicaoj >= intervalo && flag) {
                    seekArq(posicaoj - intervalo);
                    regJ.leDoArq(arquivo);
                    addComp();
                    flag = false;
                    if (aux < regJ.getNumero()) {
                        seekArq(posicaoj);
                        regI.setNumero(regJ.getNumero());
                        regI.gravaNoArq(arquivo);
                        addMov();
                        posicaoj -= intervalo;
                        seekArq(posicaoj);

                        flag = true;
                    }
                }
                seekArq(posicaoj);
                regI.setNumero(aux);
                regI.gravaNoArq(arquivo);
                addMov();
                posicaoI++;
            }
            intervalo /= 3;
        }
    }

    public void RadixSort() {
        int maior = getMaiorElementoArq(), mod = 10, div = 1, res, posArq, TL = filesize();
        ArquivoJava listaAuxiliar[] = new ArquivoJava[10];
        Registro regRadix = new Registro(), regAux = new Registro();

        while (maior > 0) {
            for (int i = 0; i < TL; i++) {
                seekArq(i);
                regRadix.leDoArq(arquivo);

                res = (regRadix.getNumero() % mod) / div;
                if (listaAuxiliar[res] == null) {
                    listaAuxiliar[res] = new ArquivoJava(
                            "./Ordenacao-Arquivo-Binario/arquivos/radix-bucket" + res + ".dat");
                    listaAuxiliar[res].truncate(0);
                }
                listaAuxiliar[res].inserirRegNoFinal(regRadix);
                addMov();
            }

            posArq = 0;
            for (int i = 0; i < 10; i++) {
                if (listaAuxiliar[i] != null) {
                    int sizeList = listaAuxiliar[i].filesize();
                    for (int j = 0; j < sizeList; j++) {
                        listaAuxiliar[i].seekArq(j);
                        regAux.leDoArq(listaAuxiliar[i].getFile());

                        seekArq(posArq);
                        regAux.gravaNoArq(arquivo);
                        addMov();
                        posArq++;
                    }
                    listaAuxiliar[i] = null;
                }
            }
            mod *= 10;
            div *= 10;
            maior /= 10;
        }
    }

    public void TimSort() {
        int tamanho = filesize(), pos = 0, info, p = 0;
        ArquivoJava listasParciais[] = new ArquivoJava[tamanho];
        Registro regAtual = new Registro(), regProx = new Registro();
        Registro regAuxI = new Registro(), regAuxJ = new Registro();
        boolean continua;

        while (p < tamanho) {
            listasParciais[pos] = new ArquivoJava("./Ordenacao-Arquivo-Binario/arquivos/tim-parcial" + pos + ".dat");
            listasParciais[pos].truncate(0);

            seekArq(p);
            regAtual.leDoArq(arquivo);
            listasParciais[pos].inserirRegNoFinal(regAtual);
            addMov();

            continua = true;
            if (p + 1 < tamanho) {
                seekArq(p + 1);
                regProx.leDoArq(arquivo);
                addComp();
            } else {
                continua = false;
            }

            while (continua && regAtual.getNumero() < regProx.getNumero()) {
                listasParciais[pos].inserirRegNoFinal(regProx);
                addMov();
                p++;

                seekArq(p);
                regAtual.leDoArq(arquivo);
                if (p + 1 < tamanho) {
                    seekArq(p + 1);
                    regProx.leDoArq(arquivo);
                    addComp();
                } else {
                    continua = false;
                }
            }
            pos++;
            p++;
        }

        while (pos > 1) {
            for (int i = 0; i < pos; i++) {
                if ((i + 1) < pos) {
                    int sizeProxList = listasParciais[i + 1].filesize();
                    for (int k = 0; k < sizeProxList; k++) {
                        listasParciais[i + 1].seekArq(k);
                        regProx.leDoArq(listasParciais[i + 1].getFile());

                        listasParciais[i].inserirRegNoFinal(regProx);
                        addMov();
                    }

                    int sizeMerged = listasParciais[i].filesize();
                    for (int idxLista = 0; idxLista < sizeMerged; idxLista++) {
                        int idxI = idxLista + 1;
                        int idxJ = idxLista;
                        boolean continuaSwap = true;

                        while (idxJ >= 0 && idxI >= 0 && idxI < sizeMerged && continuaSwap) {
                            listasParciais[i].seekArq(idxI);
                            regAuxI.leDoArq(listasParciais[i].getFile());

                            listasParciais[i].seekArq(idxJ);
                            regAuxJ.leDoArq(listasParciais[i].getFile());

                            addComp();
                            if (regAuxI.getNumero() < regAuxJ.getNumero()) {
                                info = regAuxJ.getNumero();
                                regAuxJ.setNumero(regAuxI.getNumero());
                                regAuxI.setNumero(info);

                                listasParciais[i].seekArq(idxJ);
                                regAuxJ.gravaNoArq(listasParciais[i].getFile());

                                listasParciais[i].seekArq(idxI);
                                regAuxI.gravaNoArq(listasParciais[i].getFile());

                                addMov();

                                idxI--;
                                idxJ--;
                            } else {
                                continuaSwap = false;
                            }
                        }
                    }

                    try {
                        String oldName = listasParciais[i + 1].getNomeArquivo();
                        listasParciais[i + 1].getFile().close();
                        java.io.File fileAux = new java.io.File(oldName);
                        fileAux.delete();
                    } catch (Exception e) {
                    }

                    for (int j = i + 1; j < pos; j++) {
                        if (j + 1 < pos) {
                            listasParciais[j] = listasParciais[j + 1];
                        }
                    }
                    pos--;
                }
            }
        }

        if (pos > 0 && listasParciais[0] != null) {
            this.truncate(0);
            int finalSize = listasParciais[0].filesize();
            for (int k = 0; k < finalSize; k++) {
                listasParciais[0].seekArq(k);
                regAtual.leDoArq(listasParciais[0].getFile());

                this.seekArq(k);
                regAtual.gravaNoArq(arquivo);
                addMov();
            }

            try {
                String oldName = listasParciais[0].getNomeArquivo();
                listasParciais[0].getFile().close();
                java.io.File fileAux = new java.io.File(oldName);
                fileAux.delete();
            } catch (Exception e) {
            }
        }
    }

    public void MergeSort() {
        int seq = 1;
        int TL = filesize();
        String tempFile1 = "./Ordenacao-Arquivo-Binario/arquivos/merge1.dat";
        String tempFile2 = "./Ordenacao-Arquivo-Binario/arquivos/merge2.dat";

        while (seq < TL) {
            ArquivoJava lista1 = new ArquivoJava(tempFile1);
            lista1.setNomeArquivo(tempFile1);
            lista1.truncate(0);

            ArquivoJava lista2 = new ArquivoJava(tempFile2);
            lista2.setNomeArquivo(tempFile2);
            lista2.truncate(0);

            particaoMerge(lista1, lista2, seq);
            fusaoMerge(lista1, lista2, seq);

            try {
                lista1.getFile().close();
                new java.io.File(tempFile1).delete();
            } catch (Exception e) {}

            try {
                lista2.getFile().close();
                new java.io.File(tempFile2).delete();
            } catch (Exception e) {}

            seq *= 2;
        }
    }

    public void particaoMerge(ArquivoJava lista1, ArquivoJava lista2, int seq) {
        int pos = 0;
        int TL = filesize();
        Registro regAux = new Registro();
        
        while (pos < TL) {
            for (int i = 0; i < seq && pos < TL; i++) {
                seekArq(pos);
                regAux.leDoArq(arquivo);
                
                lista1.inserirRegNoFinal(regAux);
                addMov();
                pos++;
            }
            for (int i = 0; i < seq && pos < TL; i++) {
                seekArq(pos);
                regAux.leDoArq(arquivo);
                
                lista2.inserirRegNoFinal(regAux);
                addMov();
                pos++;
            }
        }
    }

    public void fusaoMerge(ArquivoJava lista1, ArquivoJava lista2, int seq) {
        int aux1 = 0, aux2 = 0;
        int pos = 0;
        int TL1 = lista1.filesize();
        int TL2 = lista2.filesize();
        Registro regAux1 = new Registro(), regAux2 = new Registro(), regAux = new Registro();
        
        while (aux1 < TL1 || aux2 < TL2) {
            int i = 0, j = 0;
            while (i < seq && j < seq && aux1 < TL1 && aux2 < TL2) {
                lista1.seekArq(aux1);
                regAux1.leDoArq(lista1.getFile());
                
                lista2.seekArq(aux2);
                regAux2.leDoArq(lista2.getFile());
                
                addComp();
                if (regAux1.getNumero() < regAux2.getNumero()) {
                    regAux.setNumero(regAux1.getNumero());
                    aux1++;
                    i++;
                } else {
                    regAux.setNumero(regAux2.getNumero());
                    aux2++;
                    j++;
                }
                
                seekArq(pos);
                regAux.gravaNoArq(arquivo);
                addMov();
                pos++;
            }
            
            while (i < seq && aux1 < TL1) {
                lista1.seekArq(aux1);
                regAux1.leDoArq(lista1.getFile());
                
                regAux.setNumero(regAux1.getNumero());
                aux1++;
                i++;
                
                seekArq(pos);
                regAux.gravaNoArq(arquivo);
                addMov();
                pos++;
            }
            
            while (j < seq && aux2 < TL2) {
                lista2.seekArq(aux2);
                regAux2.leDoArq(lista2.getFile());
                
                regAux.setNumero(regAux2.getNumero());
                aux2++;
                j++;
                
                seekArq(pos);
                regAux.gravaNoArq(arquivo);
                addMov();
                pos++;
            }
        }
    }
}
