package my.java.vlong.homework3_refactor.mapping;

import my.java.vlong.homework3_refactor.dto.CourseDTO;
import my.java.vlong.homework3_refactor.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper extends IMapper<Course, CourseDTO> {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
}
