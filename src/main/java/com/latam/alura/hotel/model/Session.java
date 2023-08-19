package com.latam.alura.hotel.model;

import java.sql.Date;

public class Session {
    private Long idUsuario;
    private Date createSession;


    public Session(){}
    public Session(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getCreateSession() {
        return createSession;
    }

    public void setCreateSession(Date createSession) {
        this.createSession = createSession;
    }
}
