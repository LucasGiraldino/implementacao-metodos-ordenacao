import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

//... classe Arquivo (onde vai estar o m�todo para ordernar, etc) ....
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

    public void InsertionSort() {
        Registro registroI = new Registro(), registroJ = new Registro();
        int posicao, aux;

        for(int i = 1; i < filesize(); i++) {
            seekArq(i);
            registroI.leDoArq(arquivo);
            seekArq(i - 1);   
            registroJ.leDoArq(arquivo);
            posicao = i;
            while(posicao > 0 && registroI.getNumero() < registroJ.getNumero()) {
                aux = registroJ.getNumero();
                registroJ.setNumero(registroI.getNumero());
                registroI.setNumero(aux);
                seekArq(posicao);
                registroI.gravaNoArq(arquivo);
                seekArq(posicao - 1);
                registroJ.gravaNoArq(arquivo);
                posicao--;

                if(posicao > 0) {
                    seekArq(posicao);
                    registroI.leDoArq(arquivo);
                    seekArq(posicao - 1);
                    registroJ.leDoArq(arquivo);
                }

            }
        }
    }

    public ArquivoJava(String nomearquivo) {
        try {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        } catch (IOException e) {
        }
    }

    public void copiaArquivo(RandomAccessFile arquivoOrigem) {
        try {   
            Registro reg = new Registro();
            while(arquivoOrigem.getFilePointer() < arquivoOrigem.length()) {
                reg.leDoArq(arquivoOrigem);
                reg.gravaNoArq(arquivo);
            }
        } catch (Exception e) {
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

    // insere um Registro no final do arquivo, passado por par�metro
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
            for(int i = 1; i <= 10; i++) {
                Registro reg = new Registro(i);
                reg.gravaNoArq(arquivo);
            }
        } catch (Exception e) {
        }
    }

    public void geraArquivoReverso() {
        try {
            for(int i = 10; i > 0; i--) {
                Registro reg = new Registro(i);
                reg.gravaNoArq(arquivo);
            }
        } catch (Exception e) {
        }
    }

    public void geraArquivoRandom() {
        try {
            for(int i = 1; i <= 10; i++) {
                Registro reg = new Registro(new Random().nextInt(10));
                reg.gravaNoArq(arquivo);
            }
        } catch (Exception e) {

        }
    }

    public void executa()
    {
        // leArq();
        // geraArquivoOrdenado();
        exibirArq();
    }


}
