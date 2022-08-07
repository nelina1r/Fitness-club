package ru.t1.dedov.security.jwt;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.t1.dedov.model.entity.User;
import ru.t1.dedov.model.entity.enums.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class JwtUserFactory {

    public static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                true,
                mapToGrantedAuthorities()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(){
        List<Role> roles = new ArrayList<>();
        roles.add(Role.GUEST);
        roles.add(Role.USER);
        roles.add(Role.TRAINER);
        roles.add(Role.ADMIN);
        return roles.stream()
                    .map(role -> new SimpleGrantedAuthority(role.name()))
                    .collect(Collectors.toList());
    }
}
