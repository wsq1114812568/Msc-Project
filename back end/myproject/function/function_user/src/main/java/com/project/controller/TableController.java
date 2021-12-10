package com.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.common.result.Result;
import com.project.common.util.AuthContextHolder;
import com.project.entity.BookTables;
import com.project.entity.ClubEntity;
import com.project.entity.ResultEntity;
import com.project.entity.TableEntity;
import com.project.service.BookTableService;
import com.project.service.ClubService;
import com.project.service.TableService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/table")
@CrossOrigin
public class TableController {
    @Autowired
    private TableService tableService;
    @Autowired
    private BookTableService bookTableService;
    @Autowired
    private ClubService clubService;

    @GetMapping("show/{clubid}")
    public Result show(@PathVariable Long clubid){
        QueryWrapper<TableEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("clubid",clubid);
        List<TableEntity> tables=tableService.list(wrapper);
        return Result.ok(tables);
    }
    @GetMapping("show2/{clubid}")
    public Result show2(@PathVariable Long clubid,HttpServletRequest request){
        Long userid=AuthContextHolder.getUserId(request);
        QueryWrapper<TableEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("clubid",clubid);
        List<TableEntity> tables=tableService.list(wrapper);
        for(TableEntity t:tables){
            QueryWrapper<BookTables> wrapper1=new QueryWrapper<>();
            wrapper1.eq("userid",userid);
            wrapper1.eq("clubid",clubid);
            wrapper1.eq("begin",t.getBegin());
            wrapper1.eq("end",t.getEnd());
            List<BookTables> list=bookTableService.list(wrapper1);
            Long now=new Long((long)list.size());
            t.setNow(now);
            Long lack=t.getNumber()-now;
            t.setLack(lack);
        }
        return Result.ok(tables);
    }

    @PostMapping("add")
    public Result add(@RequestBody TableEntity tableEntity){
        String begin=tableEntity.getBegin();
        begin=begin.substring(begin.indexOf(" ")+1,begin.indexOf(" ")+9);
        String end=tableEntity.getEnd();
        end=end.substring(end.indexOf(" ")+1,end.indexOf(" ")+9);
        tableEntity.setBegin(begin);
        tableEntity.setEnd(end);
        boolean add=tableService.save(tableEntity);

        if(add==true){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

    @PostMapping("book")
    public Result book(@RequestBody TableEntity tableEntity, HttpServletRequest request){
        Long userid= AuthContextHolder.getUserId(request);
        BookTables bookTables=new BookTables();
        bookTables.setClubid(tableEntity.getClubid());
        bookTables.setUserid(userid);
        bookTables.setDay(tableEntity.getDay());
        bookTables.setBegin(tableEntity.getBegin());
        bookTables.setEnd(tableEntity.getEnd());
        boolean book=bookTableService.save(bookTables);
        if(book==true){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

    @GetMapping("showTablesBook")
    public Result showTablesBook(HttpServletRequest request){
        Long userid= AuthContextHolder.getUserId(request);
        QueryWrapper<BookTables> wrapper=new QueryWrapper<>();
        wrapper.eq("userid",userid);
        List<BookTables> list=bookTableService.list(wrapper);
        for(BookTables b:list){
            Long clubid=b.getClubid();
            QueryWrapper<ClubEntity> wrapper1=new QueryWrapper<>();
            wrapper1.eq("clubid",clubid);
            String clubname=clubService.getOne(wrapper1).getClubname();
            b.setClubname(clubname);
        }
        return Result.ok(list);
    }


}
