package org.dalvarez.shop.core.shared.infrastructure.hibernate_persistence;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class BaseEntity {

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    protected Timestamp createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    protected Timestamp updatedAt;

    @Column(nullable = false, updatable = false, unique = true, length = 36)
    protected String uuid;

    public BaseEntity() {

    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public String getUuid() {
        return uuid;
    }

    public static final class FieldNames {

        public static final String ID = "id";

        public static final String UUID = "uuid";

    }

}
