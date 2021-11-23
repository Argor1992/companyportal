package com.company.application.core.domain;

import com.company.application.domain.core.data.IHasId;

public interface IClient extends IHasId<Integer> {
    String getName();
    String getRepresentative();
    String getEmail();
    String getPhone();
}
