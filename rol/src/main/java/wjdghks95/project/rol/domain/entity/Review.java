package wjdghks95.project.rol.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wjdghks95.project.rol.domain.BaseTimeEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thumbnail_image_id")
    private Image thumbnail;

    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private int rating = 0;

    private int countVisit;

    private int likeCount;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewTag> reviewTag = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikeEntity> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Review(String title, String description, Category category, int rating) {
        this.title = title;
        this.description = description;
        this.category = category;
        category.getReviewList().add(this);
        this.rating = rating;
        this.countVisit = 0;
        this.likeCount = 0;
        this.createdDate = LocalDateTime.now();
        this.lastModifiedDate = createdDate;
    }

    /**
     * ???????????? ?????????
     */
    public void setImage(Image image) {
        this.images.add(image);
        image.setReview(this);
    }

    public void setMember(Member member) {
        this.member = member;
        member.getReviewList().add(this);
    }

    public void setThumbnail(List<Image> images) {
        this.thumbnail = images.get(0);
    }

    /**
     * ?????? ?????????
     */
    public void updateLikeCount() {
        this.likeCount = this.likeList.size();
    }

    public void discountLike(LikeEntity likeEntity) {
        this.likeList.remove(likeEntity);
    }

    public void updateVisit() {
        ++this.countVisit;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
