package com.maathru.backend.Domain.entity.eligible;

import com.maathru.backend.Domain.entity.User;

public interface BaseEntity {
    long getId();

    void setId(long id);

    void setCreatedBy(User user);

    void setUser(User user);

    void setUpdatedBy(User user);
}

