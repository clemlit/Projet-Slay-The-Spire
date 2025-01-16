package main.Carte;

public class Effet {
    private int nb;
    private String effet;

    public Effet(int nb, String effet){
        this.nb = nb;
        this.effet = effet;
    }

    public int getNb(){
        return nb;
    }

    public String getEffet(){
        return effet;
    }

    @Override
    public String toString(){
        return effet + " " + nb;
    }
}
