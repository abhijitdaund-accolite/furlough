package com.accolite.furlough.dto.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.accolite.furlough.dto.FurloughLogDTO;
import com.accolite.furlough.entity.FurloughLog;

public interface FurloughLogMapper {

	@Mappings({@Mapping(source="requestID",target="requestID"),
		@Mapping(source="mSID", target="mSID"),
		@Mapping(source="furloughDate",target="furloughDate"),
		@Mapping(source="furloughStatus",target="furloughStatus"),
		@Mapping(source="logTime",target="logTime")})
	@Named("toFurloughLogDTO")
	public abstract FurloughLogDTO toFurloughLogDTO(FurloughLog log) throws Exception;
	
	@IterableMapping(qualifiedByName="toFurloughLogDTO")
	public abstract List<FurloughLogDTO> tofurloughLogDTOList(Iterable<FurloughLog> logList) throws Exception;
	

	@Mappings({@Mapping(source="requestID",target="requestID"),
		@Mapping(source="mSID", target="mSID"),
		@Mapping(source="furloughDate",target="furloughDate"),
		@Mapping(source="furloughStatus",target="furloughStatus"),
		@Mapping(source="logTime",target="logTime")})
	@Named("toFurloughLog")
	public abstract FurloughLog toFurloughLog(FurloughLogDTO furloughLogDTO) throws Exception;
}
