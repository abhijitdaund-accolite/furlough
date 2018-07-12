package com.accolite.furlough.dto.mapper;

import com.accolite.furlough.dto.MSEmployeeDTO;
import com.accolite.furlough.entity.MSEmployee;
import org.mapstruct.Mapping;

import java.util.List;

import org.mapstruct.*;
public interface MSEmployeeMapper {

	@Mappings({@Mapping(source="mSID",target="mSID"),
		@Mapping(source="resourceName",target="resourceName"),
		@Mapping(source="vendorName",target="vendorName"),
		@Mapping(source="division",target="division"),
		@Mapping(source="officeLocation",target="officeLocation"),
		@Mapping(source="accoliteEmployee",target="accoliteEmployee")})
	@Named("toMSEmployeeDTO")
	public abstract MSEmployeeDTO toMSEmployeeDTO(MSEmployee msEmployee) throws Exception;
	
	@IterableMapping(qualifiedByName="toMSEmployeeDTO")
	public abstract List<MSEmployeeDTO> toMSEmployeeListDTO(Iterable<MSEmployee> msEmployeeList) throws Exception;
	

	@Mappings({@Mapping(source="mSID",target="mSID"),
		@Mapping(source="resourceName",target="resourceName"),
		@Mapping(source="vendorName",target="vendorName"),
		@Mapping(source="division",target="division"),
		@Mapping(source="officeLocation",target="officeLocation"),
		@Mapping(source="accoliteEmployee",target="accoliteEmployee")})
	@Named("toMSEmployee")
	public abstract MSEmployee toMSEmployee(MSEmployeeDTO msEmployeeDTO) throws Exception;
}
