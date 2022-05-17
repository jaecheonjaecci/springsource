package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.company.domain.PersonDTO;

//DAO를 따로 생성하지 않고, 이 클래스의 DAO의 역할을 담당함
public interface PersonMapper {
	
//	//입력
//	//인터페이스의 insertPerson이 호출되면 위에 있는 sql문을 실행시키라는 의미
//	@Insert("insert into person(id,name) values(#{id},#{name})")
//	
//	//@Param은 정확하게 뭘 의미하는지 지정하는 의미
//	public int insertPerson(@Param("id") String id,@Param("name") String name);
//	
//	
//	@Select("select name from person where id=#{id}")
//	public String selectPerson(String id);
//	
//	@Update("update person set name=#{name} where id=#{id}")
//	public int updatePerson(@Param("id") String id,@Param("name") String name);
//	//결과값이 int로 넘어오니까 int로 설정
	
	
	
	
	//인터페이스 + XML 조합의 형태
	//xml의 아이디와 반드시 일치하는 것을 불러야함
	public int insertPerson(@Param("id") String id,@Param("name") String name);
	public String selectPerson(String id);
	public int updatePerson(@Param("id") String id,@Param("name") String name);
	public int deletePerson(String id);
	public List<PersonDTO> all();
}
