package com.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.entity.AttendEntity;
import com.project.entity.Competition;
import com.project.entity.CompetitionVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CompetitionService extends IService<Competition> {
    List<Competition> test();
    Page<CompetitionVo> getName(Page<CompetitionVo> page);
    boolean changeStatus(Long competitionid);

    List<AttendEntity> findUser(Long competitionid);

    List<Competition> findByName(String name);

    void exportData(HttpServletResponse response);

    void importData(MultipartFile file);
}
