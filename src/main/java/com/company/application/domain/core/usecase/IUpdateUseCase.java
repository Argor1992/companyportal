package com.company.application.domain.core.usecase;

/**
 * @author Thorsten Zieres, 1297197
 */
public interface IUpdateUseCase<T> {
    boolean update(T selected);
}
