package com.softserve.actent.utils.modelmapper;


import java.util.List;
import java.util.stream.Collectors;

public interface IModelMapperConverter<E, D> {

    E convertToEntity(D dto);

    D convertToDto(E entity);

    default List<E> convertToEntity(List<D> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    default List<D> convertToDto(List<E> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}
