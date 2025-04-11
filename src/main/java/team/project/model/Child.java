package team.project.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "childs")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false)
    private LocalDate birth;
    @Column(nullable = false)
    private String genderName;
    private String image;

    @OneToMany(mappedBy = "child", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Height> heights = new ArrayList<>();

    @OneToMany(mappedBy = "child", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Weight> weights = new ArrayList<>();

    @OneToMany(mappedBy = "child", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Foot> foots = new ArrayList<>();

    @OneToOne(mappedBy = "child", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Eye eye;

    @OneToMany(mappedBy = "child", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Vaccine> vaccines = new ArrayList<>();
}
