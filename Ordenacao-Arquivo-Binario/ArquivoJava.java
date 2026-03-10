import java.io.IOException;
import java.io.RandomAccessFile;

//... classe Arquivo (onde vai estar o m�todo para ordernar, etc) ....
public class ArquivoJava {
    private String nomearquivo;
    private RandomAccessFile arquivo;
    private int comp, mov;

    public ArquivoJava(String nomearquivo) {
        try {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        } catch (IOException e) {
        }
    }

    public void copiaArquivo(RandomAccessFile arquivoOrigem) {
        ArquivoJava novoArquivo = new ArquivoJava("copia-arquivo");
        try {   
            Registro reg = new Registro();
            while(!eof()) {
                reg.leDoArq(arquivo);
                novoArquivo.inserirRegNoFinal(reg);
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
        int i;
        Registro aux = new Registro();
        seekArq(0);
        i = 0;
        while (!this.eof()) {
            // System.out.println("Posicao " + i);
            aux.leDoArq(arquivo);
            aux.exibirReg();
            i++;
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
            for(int i = 1; i <= 1024; i++) {
                Registro reg = new Registro(i);
                reg.gravaNoArq(arquivo);
            }
        } catch (Exception e) {
        }
    }

    public void geraArquivoReverso() {
        try {
            for(int i = 1024; i > 0; i--) {
                Registro reg = new Registro(i);
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
