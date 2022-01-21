package org.springframework.samples.notimeforheroes.user;

//Clase auxiliar para la realización de la clasificación de jugadores por partida y obtención del ganador

public class UserGlory {
    public User user;
    public Integer glory;

    public Integer setGlory(Integer glory){
        return this.glory = glory;
    }

    public User setUser(User user){
        return this.user = user;
    }

    public Integer getGlory(){
        return this.glory;
    }

    public User getUser(){
        return this.user;
    }
}
