package com.project.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.project.entity.Competition;
import com.project.entity.CompetitionExcel;
import com.project.mapper.CompetitionMapper;
import org.springframework.beans.BeanUtils;

public class CompetitionListener extends AnalysisEventListener<CompetitionExcel> {

    private CompetitionMapper competitionMapper;
    public CompetitionListener(CompetitionMapper competitionMapper){
        this.competitionMapper=competitionMapper;
    }
    @Override
    public void invoke(CompetitionExcel competitionExcel, AnalysisContext analysisContext) {
        Competition competition=new Competition();
        BeanUtils.copyProperties(competitionExcel,competition);
        competitionMapper.insert(competition);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
