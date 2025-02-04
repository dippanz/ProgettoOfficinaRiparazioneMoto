package it.officina.OfficinaRiparazioneMoto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Interfaccia base per tutti i mapper.
 * @param <E> Model (Entity)
 * @param <D> DTO
 */
public interface BaseMapper<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
    <T> T map(E entity, Class<T> destinationClass);
    D mapEntityToDto(Object entity);

    default List<D> toDtoList(List<E> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }

    default List<E> toEntityList(List<D> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    
}
