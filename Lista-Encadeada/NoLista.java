public class NoLista {
    public int info;
    public NoLista anterior;
    public NoLista proximo;

    public NoLista(NoLista anterior, NoLista proximo, int info) {
        this.anterior = anterior;
        this.proximo = proximo;
        this.info = info;
    }

    public NoLista getAnt() {
        return this.anterior;
    }

    public void setAnt(NoLista ant) {
        anterior = ant;
    }

    public NoLista getProx() {
        return this.proximo;
    }

    public void setProx(NoLista prox) {
        this.proximo = prox;
    }

    public int getInfo() {
        return this.info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

}