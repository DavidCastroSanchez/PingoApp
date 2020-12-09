package com.hsd.contest.spain.pingoapp;

public class Carta {
    public String name,palo,redondasPicudas;
    public int poster,valor;



    public Carta(){

    }
    public Carta(String name, int poster,int valor,String palo,String redondasPicudas) {
        this.name = name;
        this.poster = poster;
        this.valor=valor;
        this.redondasPicudas=redondasPicudas;
    }

    public String getRedondasPicudas() {
        return redondasPicudas;
    }

    public void setRedondasPicudas(String redondasPicudas) {
        this.redondasPicudas = redondasPicudas;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getPalo() {
        return palo;
    }

    public int getValor() {
        return valor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }
}


