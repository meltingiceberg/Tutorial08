package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.model.CourseModel;
import com.example.model.StudentModel;

@Mapper
public interface CourseMapper {
	@Select("select id_course, name, credits from course where id_course = #{id}")
    @Results(value = {
    		@Result(property="idCourse", column="id_course"),
    		@Result(property="name", column="name"),
    		@Result(property="credits", column="credits"),
    		@Result(property="students", column="id_course",
    				javaType = List.class,
    				many=@Many(select="selectStudentsbyCourse"))
    })
    CourseModel selectCourse(@Param("id") String id);
    
    @Select("select student.npm, name, gpa "
    		+ "from studentcourse join student "
    		+ "on studentcourse.npm = student.npm "
    		+ "where studentcourse.id_course = #{id}")
    List<StudentModel> selectStudentsbyCourse(@Param("id") String id);
    
    @Select("select id_course, name, credits from course")
    @Results(value = {
    		@Result(property="idCourse", column="id_course"),
    		@Result(property="name", column="name"),
    		@Result(property="credits", column="credits"),
    		@Result(property="students", column="id_course",
    				javaType = List.class,
    				many=@Many(select="selectStudentsbyCourse"))
    })
    List<CourseModel> selectAllCourses();
}
