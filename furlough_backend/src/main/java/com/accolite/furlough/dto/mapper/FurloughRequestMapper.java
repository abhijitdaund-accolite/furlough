package com.accolite.furlough.dto.mapper;

import org.springframework.stereotype.Component;

import com.accolite.furlough.dto.FurloughRequestDTO;
import com.accolite.furlough.entity.FurloughRequests;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
@Component
public interface FurloughRequestMapper {

	@Mappings({@Mapping(source="furloughId",target="furloughId"),
		@Mapping(source="furloughStatus",target="furloughStatus"),
		@Mapping(source="mailsent",target="mailSent"),
		@Mapping(source="resourceConfirmed",target="resourceConfirmed")})
	@Named("toFurloughRequestDTO")
    public abstract FurloughRequestDTO toFurloughRequestDTO(FurloughRequests request) throws Exception;

    @IterableMapping(qualifiedByName = "toFurloughRequestDTO")
    public abstract List<FurloughRequestDTO> toFurloughRequestDTOList(Iterable<FurloughRequests> requestList) throws Exception;
    
    @Mappings({@Mapping(source="furloughId",target="furloughId"),
		@Mapping(source="furloughStatus",target="furloughStatus"),
		@Mapping(source="mailsent",target="mailSent"),
		@Mapping(source="resourceConfirmed",target="resourceConfirmed")})
    @Named("toFurloughRequests")
    public abstract FurloughRequests toFurloughRequests(FurloughRequestDTO furloughRequestDTO) throws Exception;
}
