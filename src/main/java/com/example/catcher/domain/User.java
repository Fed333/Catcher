package com.example.catcher.domain;

import com.example.catcher.algorithms.Pair;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;

import static com.example.catcher.domain.Level.A1;

@Entity
@Table(name="Users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="login", unique = true, length = 64)
    private String login;

    @Column(name="password")
    private String password;

    @Column(name="avatar_name") //назва файлу зображення з аватаркою
    private String avatarName;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    //реляційне відношення один до багатьох
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="user_id")
    private List<ProgressWord> vocabulary;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(mappedBy="user", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
//    @JoinColumn(name="user_id")
    private List<CompletedTest> completedTests;

    @Column(name="name")
    private String name;

    @Column(name="phone", length=13)
    private String phone;

    @Column(name="e_mail")
    private String email;

    @Column(name="level")
    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(name = "score")
    private Integer score;

    @Column(name="date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Transient
    private Map<Pair<String, String>, Integer> cache = new HashMap<>();

    public User() {
        level = A1;
        score = 0;
    }

    public boolean isAdmin(){
        return getRoles().contains(Role.ADMIN);
    }
    public boolean isTeacher(){ return getRoles().contains(Role.TEACHER); }
    public boolean isStudent(){return getRoles().contains(Role.STUDENT);}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return login;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }
    public String getBirthdayString(){
        return String.valueOf(birthday);
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<ProgressWord> getVocabulary() {
        return vocabulary;
    }


    public List<Word> getWords() {
        List<Word> words = new LinkedList<>();
        vocabulary.forEach(pw -> words.add(pw.getWord()));
        return words;
    }

    public void setVocabulary(List<ProgressWord> vocabulary) {
        this.vocabulary = vocabulary;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<CompletedTest> getCompletedTests() {
        return completedTests;
    }

    public void setCompletedTests(List<CompletedTest> completedTests) {
        this.completedTests = completedTests;
    }

    public Map<Pair<String, String>, Integer> getCache() {
        return cache;
    }

    public void setCache(Map<Pair<String, String>, Integer> cache) {
        this.cache = cache;

    }
}
