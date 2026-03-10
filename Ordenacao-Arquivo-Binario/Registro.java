
import java.io.IOException;
import java.io.RandomAccessFile;

public class Registro
{
    public final int tf = 1024;
    private int numero;
    private char lixo[] = new char[tf];

    public Registro() {
    }

    public Registro(int numero) {
        this.numero = numero;
        for (int i=0 ; i<tf ; i++)
            lixo[i]='X';
    }

    public int getNumero() {
        return (numero);
    }

    public String getNome() {
        String Snome = new String(lixo);
        return (Snome);
    }

    public void gravaNoArq(RandomAccessFile arquivo){
        try {
            arquivo.writeInt(numero);
            for(int i=0 ; i<tf ; i++)
                arquivo.writeChar(lixo[i]);
        } catch(IOException e){}
    }

    public void leDoArq(RandomAccessFile arquivo) {
        try {
            numero = arquivo.readInt();
            for(int i=0 ; i<tf ; i++)
                lixo[i]=arquivo.readChar();
        } catch(IOException e){}
    }
    public void exibirReg() {
        System.out.print(this.numero + " ");
    }

    static int length()
    {
        return (2048);
    }
}