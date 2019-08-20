/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lifantou.com.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author mouctar diop
 */
@Entity
@Table(name = "acces_app")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccesApp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAccesApp")
    private Long idAccesApp;
    @Size(max = 45)
    @Column(name = "username")
    private String username;
    @Size(max = 100)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "actived")
    private int actived;
    @JoinColumn(name = "id_user")
    @ManyToOne(optional = false)
    @JsonIgnore
    private User idUser;
    @JoinColumn(name = "id_role")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Role idRole;
    @JsonIgnore
    private String token;
}
