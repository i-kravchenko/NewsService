package com.example.NewService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class News implements UserReference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "title is required")
    private String title;
    @ManyToOne
    @NotNull(message = "user_id is required")
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @NotNull(message = "user_id is required")
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    @Builder.Default
    List<Comment> comments = new ArrayList<>();
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;
}
