package com.company.application.domain.core.data;

/**
 * @author Thorsten Zieres, 1297197
 */
public interface IClient extends IHasId<Integer> {
    String getName();
    String getRepresentative();
    String getEmail();
    String getPhone();
}
