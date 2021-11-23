package com.company.application.domain.core.usecase;

public interface IUpdateUseCase<T> {
    boolean update(T selected);
}
