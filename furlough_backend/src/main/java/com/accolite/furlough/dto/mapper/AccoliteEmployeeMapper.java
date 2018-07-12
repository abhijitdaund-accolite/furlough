package com.accolite.furlough.dto.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.accolite.furlough.dto.AccoliteEmployeeDTO;
import com.accolite.furlough.dto.FurloughRequestDTO;
import com.accolite.furlough.entity.AccoliteEmployee;
import com.accolite.furlough.entity.FurloughRequests;

public interface AccoliteEmployeeMapper {

	@Mappings({@Mapping(source="employeeID",target="employeeID"),
		@Mapping(source="employeeName",target="employeeName"),
		@Mapping(source="employeeEmail",target="employeeEmail"),
		@Mapping(source="employeeContact",target="employeeContact"),
		@Mapping(source="mSID",target="mSID")
		})
	@Named("toAccoliteEmployeeDTO")
	public abstract AccoliteEmployeeDTO toAccoliteEmployeeDTO(AccoliteEmployee accoliteEmployee) throws Exception;
	
	@IterableMapping(qualifiedByName = "toAccoliteEmployeeDTO")
	public abstract List<AccoliteEmployeeDTO> toAccoliteEmployeeDTOList(Iterable<AccoliteEmployee> employeeList) throws Exception;
	

	@Mappings({@Mapping(source="employeeID",target="employeeID"),
		@Mapping(source="employeeName",target="employeeName"),
		@Mapping(source="employeeEmail",target="employeeEmail"),
		@Mapping(source="employeeContact",target="employeeContact"),
		@Mapping(source="mSID",target="mSID")
		})
	@Named("toAccoliteEmployee")
	public abstract AccoliteEmployee toAccoliteEmployee(AccoliteEmployeeDTO accoliteEmployeeDTO) throws Exception;
}
