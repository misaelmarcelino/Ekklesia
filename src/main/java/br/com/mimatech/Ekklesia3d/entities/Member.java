package br.com.mimatech.Ekklesia3d.entities;

import br.com.mimatech.Ekklesia3d.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_members",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_members_email", columnNames = "email")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, length = 255)
    private String email;
    @Column(length = 20)
    private String phone;
    private int age;
    private LocalDate dateOfBirth;
    private LocalDate dateOfBaptism;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "fk_members_address"))
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "position_id", foreignKey = @ForeignKey(name = "fk_members_position"))
    private Position position;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Role role;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return "";
    }
}
