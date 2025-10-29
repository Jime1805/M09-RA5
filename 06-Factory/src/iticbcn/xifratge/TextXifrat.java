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
    @Override
    public String toString(){
        return new String(dades, java.nio.charset.StandardCharsets.ISO_8859_1);
    }
}
