package com.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.entity.BookTables;
import com.project.mapper.BookTablesMapper;
import com.project.service.BookTableService;
import org.springframework.stereotype.Service;

@Service
public class BookTableServiceImpl extends ServiceImpl<BookTablesMapper, BookTables> implements BookTableService {
}
