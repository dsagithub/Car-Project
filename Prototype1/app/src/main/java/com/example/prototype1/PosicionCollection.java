package com.example.prototype1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by yifeige on 27/12/14.
 */
public class PosicionCollection
{

    private List<Link> links;
    private List<Posicion> posiciones;
    private String username;

    public PosicionCollection()
    {
        super();
        posiciones=new ArrayList<>();
    }




    public List<Link> getLinks()
    {
        return links;
    }

    /*public void setLinks(List<Link> links)
    {
        this.links = links;
    }*/

    public List<Posicion> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(List<Posicion> posiciones) {
        this.posiciones = posiciones;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addPosicion (Posicion posicion)
    {
        posiciones.add(posicion);
    }








}
