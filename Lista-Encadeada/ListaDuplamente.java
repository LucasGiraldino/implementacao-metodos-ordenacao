import java.util.Random;

public class ListaDuplamente {
    private NoLista inicio;
    private NoLista fim;
    private int quantidade;

    public ListaDuplamente() {
        this.inicio = null;
        this.fim = null;
        this.quantidade = 0;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public NoLista getInicio() {
        return inicio;
    }

    public void setInicio(NoLista inicio) {
        this.inicio = inicio;
    }

    public NoLista getFim() {
        return fim;
    }

    public void setFim(NoLista fim) {
        this.fim = fim;
    }

    public void inicializaLista() {
        NoLista aux = inicio;
        for (int i = 0; i < quantidade; i++) {
            aux.setInfo(-1);
            aux = aux.getProx();
        }
    }

    public void inserirNoFinal(int info) {
        NoLista novoNo = new NoLista(null, null, info);
        if (inicio == null) {
            inicio = novoNo;
            fim = novoNo;
        } else {
            NoLista noFim = fim;
            noFim.setProx(novoNo);
            novoNo.setAnt(noFim);
            fim = novoNo;
        }
        quantidade++;
    }

    public void geraLista() {
        int numero;
        for (int i = 0; i < 50; i++) {
            numero = new Random().nextInt(100);
            inserirNoFinal(numero);
        }
    }

    public void copiaLista(ListaDuplamente lista) {
        if (lista == null) {
            inicio = null;
        } else {
            NoLista auxLista = lista.getInicio();
            while (auxLista != null) {
                this.inserirNoFinal(auxLista.getInfo());
                auxLista = auxLista.getProx();
            }
        }
    }

    public void imprimirLista() {
        NoLista aux = inicio;
        while (aux != null) {
            System.out.print(aux.getInfo() + " ");
            aux = aux.getProx();
        }
        System.out.println();
    }

    public int contaQuantidadeLista(NoLista noInicio, NoLista noFim) {
        int cont = 1;
        while (noInicio != noFim) {
            noInicio = noInicio.getProx();
            cont++;
        }
        return cont;
    }

    public NoLista getMeioLista(NoLista inicioLista, NoLista fimLista) {
        NoLista auxLista = inicioLista;
        int quant = contaQuantidadeLista(inicioLista, fimLista);
        for (int i = 1; i < (quant * 10) / 20; i++)
            auxLista = auxLista.getProx();
        return auxLista;
    }

    public NoLista buscaBinaria(NoLista noProcura) {
        NoLista noIni = inicio, meio, noFim = noProcura.getAnt();
        meio = getMeioLista(noIni, noFim);
        while (noIni != noFim && noProcura.getInfo() != meio.getInfo()) {
            if (meio.getInfo() < noProcura.getInfo())
                noIni = meio.getProx();
            else
                noFim = meio;
            if (noIni != noFim)
                meio = getMeioLista(noIni, noFim);
        }
        if (noIni == noFim) {
            if (noIni.getInfo() >= noProcura.getInfo())
                return noIni;
            else
                return noProcura;
        }
        return meio;
    }

    public int getMaiorElementoLista() {
        NoLista auxLista = inicio;
        int maior = 0;
        while (auxLista != null) {
            if (auxLista.getInfo() > maior)
                maior = auxLista.getInfo();
            auxLista = auxLista.getProx();
        }
        return maior;
    }

    // ===================================================
    // METODOS DE ORDENAÇÃO
    // ===================================================

    public void SelectionSort() {
        NoLista auxI = inicio, posMenor, auxJ;
        int infoMenor;
        while (auxI.getProx() != null) {
            infoMenor = auxI.getInfo();
            posMenor = auxI;
            auxJ = auxI.getProx();
            while (auxJ != null) {
                if (auxJ.getInfo() < infoMenor) {
                    infoMenor = auxJ.getInfo();
                    posMenor = auxJ;
                }
                auxJ = auxJ.getProx();
            }
            if (auxI.getInfo() != infoMenor) {
                posMenor.setInfo(auxI.getInfo());
                auxI.setInfo(infoMenor);
            }
            auxI = auxI.getProx();
        }
    }

    public void InsertionSort() {
        NoLista auxLista = inicio.getProx(), auxI, auxJ;
        int info;
        while (auxLista != null) {
            auxI = auxLista;
            auxJ = auxLista.getAnt();
            while (auxJ != null && auxI.getInfo() < auxJ.getInfo()) {
                info = auxJ.getInfo();
                auxJ.setInfo(auxI.getInfo());
                auxI.setInfo(info);

                auxI = auxI.getAnt();
                auxJ = auxJ.getAnt();
            }
            auxLista = auxLista.getProx();
        }
    }

    public void InsertionBinary() {
        NoLista auxLista = inicio.getProx(), pontI, pos;
        int aux;
        while (auxLista != null) {
            pos = buscaBinaria(auxLista);
            aux = auxLista.getInfo();
            pontI = auxLista;
            while (pontI != pos) {
                pontI.setInfo(pontI.getAnt().getInfo());
                pontI = pontI.getAnt();
            }
            pontI.setInfo(aux);
            auxLista = auxLista.getProx();
        }

    }

    public void BubbleSort() {
        NoLista pontI, pontFim = fim;
        int aux;
        boolean troca = true;
        while (pontFim != inicio && troca) { // pontFim.getAnt() != null
            pontI = inicio;
            troca = false;
            while (pontI != pontFim) {
                if (pontI.getInfo() > pontI.getProx().getInfo()) {
                    aux = pontI.getInfo();
                    pontI.setInfo(pontI.getProx().getInfo());
                    pontI.getProx().setInfo(aux);
                    troca = true;
                }
                pontI = pontI.getProx();
            }
            pontFim = pontFim.getAnt();
        }
    }

    public void ShakeSort() {
        NoLista pontIni = inicio, pontFim = fim, pontAux;
        int aux;
        boolean troca = true;
        while (pontIni != pontFim && troca) {
            pontAux = pontIni;
            troca = false;
            while (pontAux != pontFim) {
                if (pontAux.getInfo() > pontAux.getProx().getInfo()) {
                    aux = pontAux.getInfo();
                    pontAux.setInfo(pontAux.getProx().getInfo());
                    pontAux.getProx().setInfo(aux);
                    troca = true;
                }
                pontAux = pontAux.getProx();
            }
            pontFim = pontFim.getAnt();
            if (troca) {
                troca = false;
                pontAux = pontFim;
                while (pontAux != pontIni) {
                    if (pontAux.getInfo() < pontAux.getAnt().getInfo()) {
                        aux = pontAux.getInfo();
                        pontAux.setInfo(pontAux.getAnt().getInfo());
                        pontAux.getAnt().setInfo(aux);
                        troca = true;
                    }
                    pontAux = pontAux.getAnt();
                }
                pontIni = pontIni.getProx();
            }
        }
    }

    public void CombSort() {
        int comb = getQuantidade(), aux;
        NoLista pontIni, pontFim;
        while (comb > 0) {
            comb = comb * 10 / 13;
            pontIni = inicio;
            pontFim = pontIni.getProx();
            for (int i = 0; i < comb; i++)
                pontFim = pontFim.getProx();
            while (pontFim != null) {
                if (pontIni.getInfo() > pontFim.getInfo()) {
                    aux = pontIni.getInfo();
                    pontIni.setInfo(pontFim.getInfo());
                    pontFim.setInfo(aux);
                }
                pontFim = pontFim.getProx();
                pontIni = pontIni.getProx();
            }
        }
    }

    public void CountingSort() {
        NoLista pontAux = this.inicio, pontFreq, pontOrdenada;
        int tamanhoLista, pos;
        ListaDuplamente listaFrequencia = new ListaDuplamente();
        ListaDuplamente listaOrdenada = new ListaDuplamente();
        listaOrdenada.copiaLista(this);
        tamanhoLista = getMaiorElementoLista();
        for (int i = 0; i < tamanhoLista; i++)
            listaFrequencia.inserirNoFinal(0);
        while (pontAux != null) {
            pontFreq = listaFrequencia.getInicio();
            for (int i = 1; i < pontAux.getInfo(); i++)
                pontFreq = pontFreq.getProx();
            pontFreq.setInfo(pontFreq.getInfo() + 1);
            pontAux = pontAux.getProx();
        }
        pontFreq = listaFrequencia.getInicio().getProx();
        while (pontFreq != null) {
            if (listaFrequencia.getQuantidade() > 1)
                pontFreq.setInfo(pontFreq.getAnt().getInfo() + pontFreq.getInfo());
            pontFreq = pontFreq.getProx();
        }
        pontAux = this.inicio;
        while (pontAux != null) {
            pontFreq = listaFrequencia.getInicio();
            for (int i = 1; i < pontAux.getInfo(); i++)
                pontFreq = pontFreq.getProx();
            pos = pontFreq.getInfo();
            pontFreq.setInfo(pontFreq.getInfo() - 1);
            pontOrdenada = listaOrdenada.getInicio();
            for (int i = 1; i < pos; i++)
                pontOrdenada = pontOrdenada.getProx();
            pontOrdenada.setInfo(pontAux.getInfo());
            pontAux = pontAux.getProx();
        }
        pontAux = inicio;
        pontOrdenada = listaOrdenada.getInicio();
        while (pontAux != null) {
            pontAux.setInfo(pontOrdenada.getInfo());
            pontAux = pontAux.getProx();
            pontOrdenada = pontOrdenada.getProx();
        }
    }

    public void BucketSort() {
        int maior = getMaiorElementoLista(), bucketoNo;
        NoLista auxLista = inicio, auxBucket;
        ListaDuplamente listaBucket[] = new ListaDuplamente[quantidade];
        while(auxLista != null) {
            bucketoNo = (quantidade * auxLista.getInfo()) / (maior + 1);
            if(listaBucket[bucketoNo] == null) 
                listaBucket[bucketoNo] = new ListaDuplamente();
            listaBucket[bucketoNo].inserirNoFinal(auxLista.getInfo());
            auxLista = auxLista.getProx();
        }
        auxLista = inicio;
        for(int i = 0; i < quantidade; i++) {
            if(listaBucket[i] instanceof ListaDuplamente) {
                listaBucket[i].InsertionSort();
                auxBucket = listaBucket[i].getInicio();
                while(auxBucket != null) {
                    auxLista.setInfo(auxBucket.getInfo());
                    auxBucket = auxBucket.getProx();
                    auxLista = auxLista.getProx();
                }
            }
        }
    }

    public void RadixSort() {
        int maior = getMaiorElementoLista(), mod = 10, div = 1, res;
        NoLista auxLista, auxRadix;
        ListaDuplamente listaAuxiliar[] = new ListaDuplamente[10];
        ListaDuplamente listaRadix = new ListaDuplamente();
        listaRadix.copiaLista(this);
        while(maior > 0) {
            auxRadix = listaRadix.getInicio();
            while(auxRadix != null) {
                res = (auxRadix.getInfo() % mod) / div;
                if(listaAuxiliar[res] == null)
                    listaAuxiliar[res] = new ListaDuplamente();
                listaAuxiliar[res].inserirNoFinal(auxRadix.getInfo());
                auxRadix = auxRadix.getProx();
            }
            auxRadix = listaRadix.getInicio();
            for(int i = 0; i < 10; i++) {
                if(listaAuxiliar[i] != null) {
                    auxLista = listaAuxiliar[i].getInicio();
                    while(auxLista != null) {
                        auxRadix.setInfo(auxLista.getInfo());
                        auxRadix = auxRadix.getProx();
                        auxLista = auxLista.getProx();
                    }
                    listaAuxiliar[i] = null;
                }
            }
            mod *= 10;
            div *= 10;
            maior /= 10;
        }
        setInicio(null);
        copiaLista(listaRadix);
    }

    public void GnomeSort() {
        NoLista auxLista = inicio.getProx(), marca;
        int aux;
        while(auxLista != null) {
            if(auxLista.getInfo() < auxLista.getAnt().getInfo()) {
                aux = auxLista.getInfo();
                auxLista.setInfo(auxLista.getAnt().getInfo());
                auxLista.getAnt().setInfo(aux);
                marca = auxLista;
                auxLista = auxLista.getAnt();
                while(auxLista.getAnt() != null && auxLista.getInfo() < auxLista.getAnt().getInfo()) {
                    aux = auxLista.getInfo();
                    auxLista.setInfo(auxLista.getAnt().getInfo());
                    auxLista.getAnt().setInfo(aux);
                    auxLista = auxLista.getAnt();
                }
                auxLista = marca;
            }
            auxLista = auxLista.getProx();
        }
    }

    public void TimSort() {
        ListaDuplamente listasParciais[] = new ListaDuplamente[quantidade];
        NoLista auxPrincipal = inicio;
        int pos = 0;
        while(auxPrincipal != null) {
            listasParciais[pos] = new ListaDuplamente();
            listasParciais[pos].inserirNoFinal(auxPrincipal.getInfo());
            while(auxPrincipal.getProx() != null && auxPrincipal.getInfo() < auxPrincipal.getProx().getInfo()) {
                listasParciais[pos].inserirNoFinal(auxPrincipal.getProx().getInfo());
                auxPrincipal = auxPrincipal.getProx();
            }
            pos++;
            auxPrincipal = auxPrincipal.getProx();
        }
        while(pos > 1) {
            for(int i = 0; i < pos; i++) {
                if((i+1) < pos) {
                    auxPrincipal = listasParciais[i].getInicio();
                    while(auxPrincipal.getProx() != null)
                        auxPrincipal = auxPrincipal.getProx();
                    auxPrincipal.setProx(listasParciais[i+1].getInicio());
                    listasParciais[i].SelectionSort();
                    for(int j = i+1; j < pos; j++) 
                        listasParciais[j] = listasParciais[j+1];
                    pos--;
                }
            }
        }
        setInicio(null);
        copiaLista(listasParciais[0]);
    }
}
