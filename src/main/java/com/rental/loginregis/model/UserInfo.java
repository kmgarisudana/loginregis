package com.rental.loginregis.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collections;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Entity
@Table( name = "karyawan",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
        })
public class UserInfo implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @Column(nullable = false, length = 64)
    @NotNull
    @Length(min = 5, max = 64)
    private String password;

    @NotBlank
    @Size(max = 20)
    private String namaKaryawan;

    @NotBlank
    @Size(max = 20)
    private String nikKaryawan;

    @NotBlank
    @Size(max = 20)
    private String telpKaryawan;

    @NotBlank
    @Size(max = 20)
    private String alamatKaryawan;

    @NotBlank
    @Size(max = 20)
    private String roleKaryawan;
    public static String roles1 ="";
    // public static String roles = "";
  

    public UserInfo(){}

    public UserInfo(
        String username, 
        String password,
        String namaKaryawan,
        String nikKaryawan,
        String telpKaryawan,
        String alamatKaryawan,
        String roleKaryawan
    ) {
        this.username = username;
        this.password = password;
        this.namaKaryawan = namaKaryawan;
        this.nikKaryawan = nikKaryawan;
        this.telpKaryawan = telpKaryawan;
        this.alamatKaryawan = alamatKaryawan;
        this.roleKaryawan = roleKaryawan;
        this.roles1 = roleKaryawan;
    }

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamaKaryawan() {
        return namaKaryawan;
    }

    public void setNamaKaryawan(String namaKaryawan) {
        this.namaKaryawan = namaKaryawan;
    }

    public String getNikKaryawan() {
        return nikKaryawan;
    }

    public void setNikKaryawan(String nikKaryawan) {
        this.nikKaryawan = nikKaryawan;
    }

    public String getTelpKaryawan() {
        return telpKaryawan;
    }

    public void setTelpKaryawan(String telpKaryawan) {
        this.telpKaryawan = telpKaryawan;
    }

    public String getAlamatKaryawan() {
        return alamatKaryawan;
    }

    public void setAlamatKaryawan(String alamatKaryawan) {
        this.alamatKaryawan = alamatKaryawan;
    }

    public String getRoleKaryawan() {
        return roleKaryawan;
    }

    public void setRoleKaryawan(String roleKaryawan) {
        // this.getAuthorities().add("ROLE_"+roleKaryawan);
        this.roleKaryawan = roleKaryawan;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName="id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName="id")
    )
    private Set<Role> roles = new HashSet<>();
     
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfo.class);
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        
        for (Role role : roles) {    
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }
        
        return authorities;

    }
 
    public Set<Role> getRoles() {
        return roles;
    }
 
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
     
    public void addRole(Role role) {
        this.roles.add(role);
    }


}

