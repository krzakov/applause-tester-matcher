package pl.mosek.applausematcher.mapper;

import org.mapstruct.Mapper;
import pl.mosek.applausematcher.dto.MatchedTesterResponseDto;
import pl.mosek.applausematcher.dto.TesterBugCountDto;

@Mapper(componentModel = "spring")
public interface TesterMapper {

    MatchedTesterResponseDto mapToResponseDto(TesterBugCountDto testerBugCountDto);
}
