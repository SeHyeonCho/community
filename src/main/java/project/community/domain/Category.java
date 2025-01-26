package project.community.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.community.dto.CategoryName;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoryName name;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "category")
    private List<Post> postList = new ArrayList<>();

    public static Category createCategory(CategoryName name) {
        Category category = new Category();
        category.name = name;
        return category;
    }
}
