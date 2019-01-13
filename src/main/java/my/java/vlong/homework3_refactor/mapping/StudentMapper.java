package my.java.vlong.homework3_refactor.mapping;

import my.java.vlong.homework3_refactor.dto.StudentDTO;
import my.java.vlong.homework3_refactor.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper extends IMapper<Student, StudentDTO> {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mappings({
        @Mapping(target = "dateOfBirth", source = "dto.dateOfBirth", dateFormat = "dd/MM/yyyy")
    })
    @Override
    Student toEntity(StudentDTO dto);
}
