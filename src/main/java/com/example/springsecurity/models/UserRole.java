package com.example.springsecurity.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Getter
@Setter

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_role")
public class UserRole  {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id"  ,referencedColumnName = "id")
        User user;
        @JoinColumn(name = "role_id"  ,referencedColumnName = "id")
        @ManyToOne(fetch = FetchType.EAGER)
        Role role;
        @Column(name ="is_active")
        Boolean isActive;
        @CreationTimestamp
        @Column(name = "created_at")
        Timestamp createdAt;
        @Column(name = "updated_at")
        Timestamp updatedAt;

        @Override
        public String toString() {
                return "UserRole{" +
                        "id=" + id +
                        ", user=" + user +
                        ", role=" + role +
                        ", isActive=" + isActive +
                        ", createdAt=" + createdAt +
                        ", updatedAt=" + updatedAt +
                        '}';
        }
}
