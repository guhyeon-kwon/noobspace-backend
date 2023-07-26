package com.my.noobspace.modules.team;

import com.my.noobspace.modules.team.dto.req.TeamReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamQueryRepository teamQueryRepository;
    @Override
    public Long insert(TeamReqDto dto) {
        return teamQueryRepository.insert(dto);
    }
}

