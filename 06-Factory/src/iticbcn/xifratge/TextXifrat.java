package iticbcn.xifratge;

public class TextXifrat{
    private byte[] dades;

    public TextXifrat(byte[] dades) {
        this.dades = dades;
    }

    public void setDades(byte[] dades) {
        this.dades = dades;
    }

    public byte[] getDades() {
        return dades;
    }
}
