package iticbcn.xifratge;

public class AlgorismeAes extends AlgorismeFactory{
    @Override
    public Xifrador creaXifrador(){
        return new XifradorAES();
    } 
}
