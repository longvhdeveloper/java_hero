package my.java.vlong.homework3_refactor.mapping;

import java.util.List;

public interface IMapper<T, DTO> {

    T toEntity(DTO dto);

    DTO toDTO(T t);

    List<T> toEntities(List<DTO> list);

    List<DTO> toDTOs(List<T> list);
}
