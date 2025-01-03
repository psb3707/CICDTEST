package study.mypost.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public static Comment createComment(Post post, Member member, String content) {

        Comment comment = Comment.builder()
                .member(member)
                .post(post)
                .content(content)
                .build();
        comment.setPost(post);
        return comment;
    }

    public void setPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
