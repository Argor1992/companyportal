package com.company.application.domain.core.usecase;

import java.util.List;
import java.util.Optional;

/**
 * @author Thorsten Zieres, 1297197
 */
public interface IListUseCase<T> {
    List<T> getList();
    Optional<T> getObject(int id);
    boolean showUpdateMenu();
}
