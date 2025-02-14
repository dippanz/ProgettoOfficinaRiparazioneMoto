package it.officina.OfficinaRiparazioneMoto.mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Base interface for all mappers.
 * <p>
 * Provides methods to convert between an entity (model) and its corresponding
 * Data Transfer Object (DTO),
 * as well as helper methods for mapping collections of entities and DTOs.
 * </p>
 *
 * @param <E> the type of the entity (model)
 * @param <D> the type of the Data Transfer Object (DTO)
 */
public interface BaseMapper<E, D> {

    /**
     * Converts the given entity to its corresponding DTO.
     *
     * @param entity the entity to convert
     * @return the DTO representation of the given entity
     */
    D toDto(E entity);

    /**
     * Converts the given DTO to its corresponding entity.
     *
     * @param dto the DTO to convert
     * @return the entity representation of the given DTO
     */
    E toEntity(D dto);

    /**
     * Maps the given entity to an instance of the specified destination class.
     *
     * @param <T>              the type of the destination object
     * @param entity           the entity to map
     * @param destinationClass the target class for the mapping
     * @return an instance of the destination class mapped from the given entity
     */
    <T> T map(E entity, Class<T> destinationClass);

    /**
     * Maps the given entity (or object) to its corresponding DTO.
     *
     * @param entity the object to map to a DTO
     * @return the DTO representation of the given object
     */
    D mapEntityToDto(Object entity);

    /**
     * Converts a list of entities to a list of DTOs.
     *
     * @param entityList the list of entities to convert
     * @return a list of DTOs converted from the given entities
     */
    default List<D> toDtoList(List<E> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }

    /**
     * Converts a list of DTOs to a list of entities.
     *
     * @param dtoList the list of DTOs to convert
     * @return a list of entities converted from the given DTOs
     */
    default List<E> toEntityList(List<D> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
