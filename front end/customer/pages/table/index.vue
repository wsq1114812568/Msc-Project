<template>
    <!-- header -->
    <div class="nav-container page-component" style="margin-bottom:120px">
        <!--左侧导航 #start -->
        <div class="nav left-nav">
            <div class="nav-item ">
                  <span class="v-link selected dark" onclick="javascript:window.location='/user'">Personal Information </span>
            </div>
            <div class="nav-item ">
                <span class="v-link selected dark" onclick="javascript:window.location='/friend'"> My Friends </span>
            </div>
             <div class="nav-item ">
                <span class="v-link selected dark" onclick="javascript:window.location='/friend/request'"> Friend Requests </span>
            </div>
            <div class="nav-item ">
                <span class="v-link selected dark" onclick="javascript:window.location='/friend/message'"> My Messages </span>
            </div>
            <div class="nav-item  ">
                <span class="v-link clickable dark" onclick="javascript:window.location='/competition'"> My Competitions </span>
            </div>
            <div class="nav-item ">
                <span class="v-link clickable dark" onclick="javascript:window.location='/results'"> My Results </span>
            </div>
            <div class="nav-item selected">
                <span class="v-link clickable dark" onclick="javascript:window.location='/table'"> My Bookings </span>
            </div>
        </div>

        <!-- 左侧导航 #end -->
        <!-- 右侧内容 #start -->
        <div class="page-container">
        <div class="personal-patient">
        <div class="header-wrapper">
            <div class="title" style="text-align:center">My Booking of Tables</div>
        </div>
        <div >

         <el-table :data="tableBooks" stripe style="width: 50%" >
            <el-table-column prop="clubname" label="Club Name" min-width="30%"/>
            <el-table-column prop="day" label="Date" min-width="30%"/>
            <el-table-column label="Time" min-width="39%" >
                <template slot-scope="scope">
                    <span>{{scope.row.begin}}-----{{scope.row.end}}</span>
                </template>   
            </el-table-column>      
        </el-table>
        </div>
        </div>
    </div>
    </div>
    <!-- footer -->
</template>
<script>
import '~/assets/css/hospital_personal.css'
import '~/assets/css/hospital.css'
import '~/assets/css/personal.css'

import table from '@/api/table'



export default {
    data() {
        return {
            tableBooks:[]
        }
    },
    created() {
        this.getList()
    },
    methods: {
        getList(){
            table.showTablesBook()
            .then(response=>{
                this.tableBooks=response.data
                console.log(this.tableBooks)
            })

        },

        
    }
}
</script>
<style>
  .header-wrapper .title {
    font-size: 16px;
    margin-top: 0;
  }
  .content-wrapper {
    margin-left: 0;
  }
  .patient-card .el-card__header .detail{
    font-size: 14px;
  }
</style>
