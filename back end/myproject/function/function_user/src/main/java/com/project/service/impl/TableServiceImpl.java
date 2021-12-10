package com.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.entity.TableEntity;
import com.project.mapper.TableMapper;
import com.project.service.TableService;
import org.springframework.stereotype.Service;

@Service
public class TableServiceImpl extends ServiceImpl<TableMapper, TableEntity> implements TableService {
}
