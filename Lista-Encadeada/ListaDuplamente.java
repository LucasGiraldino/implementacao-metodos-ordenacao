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
            aux.setInfo(0);
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

    public NoLista getMeioLista(NoLista inicioLista, NoLista fim) {
        NoLista auxLista = inicioLista;
        int contador = 0;
        while (auxLista != null) {
            auxLista = auxLista.getProx();
            contador++;
        }
        contador = (int) contador / 2;
        auxLista = inicioLista;
        for (int i = 0; i < contador; i++)
            auxLista = auxLista.getProx();
        return auxLista;
    }

    public NoLista buscaBinaria(int info, NoLista fim) {
        NoLista ini = inicio, meio;
        meio = getMeioLista(ini, fim);
        while (ini.getInfo() < fim.getInfo() && info != meio.getInfo()) {
            if (meio.getInfo() < info) {
                ini = meio.getProx();
            } else {
                fim = meio;
            }
            meio = getMeioLista(ini, fim);
        }
        if (info == meio.getInfo())
            return meio;
        return null;
    }

    public int getMaiorElementoLista() {
        NoLista auxLista = inicio;
        int maior = 0;
        while(auxLista != null) {
            if(auxLista.getInfo() > maior) 
                maior = auxLista.getInfo();
            auxLista = auxLista.getProx();
        }
        return maior;
    }

    // ===================================================
    //              METODOS DE ORDENAÇÃO
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

    // NAO TERMINADO
    public void InsertionBinary() {
        NoLista auxLista = inicio.getProx(), pos, auxJ;
        int aux;
        while (auxLista != null) {
            aux = auxLista.getInfo();
            pos = buscaBinaria(aux, auxLista);
            if (pos != null) {
                System.out.println("ACHOU: " + pos.getInfo());
            } else {
                System.out.println("NAO ACHOU");
            }
            auxLista = auxLista.getProx();
        }

    }

    public void BubbleSort() {
        NoLista pontI, pontFim = fim;
        int aux;
        boolean troca = true;
        while(pontFim != inicio && troca) { // pontFim.getAnt() != null
            pontI = inicio;
            troca = false;
            while(pontI != pontFim) {
                if(pontI.getInfo() > pontI.getProx().getInfo()) {
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
        while(pontIni != pontFim && troca) {
            pontAux = pontIni;
            troca = false;
            while(pontAux != pontFim) {
                if(pontAux.getInfo() > pontAux.getProx().getInfo()) {
                    aux = pontAux.getInfo();
                    pontAux.setInfo(pontAux.getProx().getInfo());
                    pontAux.getProx().setInfo(aux);
                    troca = true;
                }
                pontAux = pontAux.getProx();
            }
            pontFim = pontFim.getAnt();
            if(troca) {
                troca = false;
                pontAux = pontFim;
                while(pontAux != pontIni) {
                    if(pontAux.getInfo() < pontAux.getAnt().getInfo()) {
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
        while(comb > 0) {
            comb = comb * 10 / 13;
            pontIni = inicio;
            pontFim = pontIni.getProx();
            for(int i = 0; i < comb; i++) 
                pontFim = pontFim.getProx();
            while(pontFim != null) {
                if(pontIni.getInfo() > pontFim.getInfo()) {
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
        for(int i = 0; i < tamanhoLista; i++) 
            listaFrequencia.inserirNoFinal(0);
        while(pontAux != null) {
            pontFreq = listaFrequencia.getInicio();
            for(int i = 1; i < pontAux.getInfo(); i++) 
                pontFreq = pontFreq.getProx();
            pontFreq.setInfo(pontFreq.getInfo() + 1);
            pontAux = pontAux.getProx();
        }
        pontFreq = listaFrequencia.getInicio().getProx();
        while(pontFreq != null) {
            pontFreq.setInfo(pontFreq.getAnt().getInfo() + pontFreq.getInfo());
            pontFreq = pontFreq.getProx();
        }
        pontAux = this.inicio;
        while(pontAux != null) {
            pontFreq = listaFrequencia.getInicio();
            for(int i = 1; i < pontAux.getInfo(); i++) 
                pontFreq = pontFreq.getProx();
            pos = pontFreq.getInfo();
            pontFreq.setInfo(pontFreq.getInfo() - 1);
            pontOrdenada = listaOrdenada.getInicio();
            for(int i = 1; i < pos; i++) 
                pontOrdenada = pontOrdenada.getProx();
            pontOrdenada.setInfo(pontAux.getInfo());
            pontAux = pontAux.getProx();
        }
        pontAux = inicio;
        pontOrdenada = listaOrdenada.getInicio();
        while(pontAux != null) {
            pontAux.setInfo(pontOrdenada.getInfo());
            pontAux = pontAux.getProx();
            pontOrdenada = pontOrdenada.getProx();
        }
    }
}
