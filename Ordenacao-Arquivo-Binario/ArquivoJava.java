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

        while(comb > 0) {
            comb = comb * 10 / 13;
            for(int i = comb, j = 0; i <= TL ; i++, j++) {
                seekArq(i);
                registroFim.leDoArq(arquivo);
                seekArq(j);
                registroInicio.leDoArq(arquivo);
                addComp();
                if(registroInicio.getNumero() > registroFim.getNumero()) {
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

        while(inicio < fim && flag) {
            flag = false;
            for(int i = inicio; i < fim; i++) {
                seekArq(i);
                registroAux.leDoArq(arquivo);
                registroProx.leDoArq(arquivo);
                addComp();
                if(registroAux.getNumero() > registroProx.getNumero()) {
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
            if(flag) {
                flag = false;
                for(int i = fim; i > inicio; i--) {
                    seekArq(i);
                    registroAux.leDoArq(arquivo);
                    seekArq(i-1);
                    registroProx.leDoArq(arquivo);
                    addComp();
                    if(registroAux.getNumero() < registroProx.getNumero()) {
                        aux = registroAux.getNumero();
                        registroAux.setNumero(registroProx.getNumero());
                        registroProx.setNumero(aux);

                        seekArq(i);
                        registroAux.gravaNoArq(arquivo);
                        seekArq(i-1);
                        registroProx.gravaNoArq(arquivo);
                        flag = true;

                        addMov();
                    }
                }
                inicio++;
            }
        }   
    }

}
