package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.Competition;
import com.project.entity.CompetitionVo;

import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface CompetitionMapper extends BaseMapper<Competition> {
    @Select("select c2.*,c1.clubname from club c1,competition c2 where c1.clubid=c2.clubid")
    List<Competition> getCompetition();

    @Select("select c2.*,c1.clubname from club c1,competition c2 where c1.clubid=c2.clubid")
    List<CompetitionVo> getName(Page page);
}
