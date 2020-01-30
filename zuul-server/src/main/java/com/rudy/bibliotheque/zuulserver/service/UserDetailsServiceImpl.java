//package com.rudy.bibliotheque.zuulserver.service;
//
//import com.rudy.bibliotheque.zuulserver.model.Role;
//import com.rudy.bibliotheque.zuulserver.model.User;
//import com.rudy.bibliotheque.zuulserver.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserRepository userRepository;
//    @Autowired
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username.toLowerCase())
//                .orElseThrow(() -> new UsernameNotFoundException("Email " + username + " not found"));
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getEncryptedPassword(), true, true,
//                true, true, getAuthorities(user.getRoles()));
//    }
//
//    private static Set<GrantedAuthority> getAuthorities(Set<Role> roles) {
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        return authorities;
//    }
//}
