<template>
  <!-- header -->
  <div class="nav-container page-component" style="margin-bottom:110px">
    <!--左侧导航 #start -->
    <div class="nav left-nav">
      <div class="nav-item ">
        <span class="v-link selected dark" :onclick="'javascript:window.location=\'/club/'+club.clubid+'\''"> Club Details </span>
      </div>
      <div class="nav-item">
        <span class="v-link clickable dark" :onclick="'javascript:window.location=\'/club/competition/'+club.clubid+'\''"> Show Competitions </span>
      </div>
      <div class="nav-item" selected>
        <span class="v-link clickable dark" :onclick="'javascript:window.location=\'/club/tables/'+club.clubid+'\''"> Available Tables </span>
      </div>
    </div>
    <!-- 左侧导航 #end -->

    <!-- 右侧内容 #start -->
    <div class="page-container">
      <div class="hospital-detail">
        <div class="common-header">
          <div class="title-wrapper" style="margin-left:300px;margin-bottom:100px;margin-top:50px"><span class="hospital-title">Available Tables of {{club.clubname}}</span>
          </div>
        </div>
        <div class="form-wrapper">
            <div class="inputDeep">
             <el-table :data="tableList" stripe style="width: 100%" >
                <el-table-column label="Club" min-width="20%"><template slot-scope="scope"><span>{{club.clubname}}</span> </template>  </el-table-column>
                <el-table-column prop="day" label="Date" min-width="30%" />
                <el-table-column label="Time" min-width="39%" >
                    <template slot-scope="scope">
                        <span>{{scope.row.begin}}------{{scope.row.end}}</span>
                    </template>   
                </el-table-column>
                <el-table-column  label="Available Tables" align="center"  min-width="30%">
                    <template slot-scope="scope" >
                        <span>{{scope.row.lack}}/{{scope.row.number}}</span>
                    </template>  
                </el-table-column>
                 <el-table-column label="Book" >
                    <template slot-scope="scope">
                        <el-button type="primary" @click="book(scope.$index)">Book</el-button>
                    </template>   
                </el-table-column>

            </el-table>
            </div>
        </div>
      </div>
    </div>
    <!-- 右侧内容 #end -->
  </div>
  <!-- footer -->
</template>

<script>
import cookie from 'js-cookie'
import '~/assets/css/hospital_personal.css'
import '~/assets/css/hospital.css'

import club from '@/api/club'
import table from '@/api/table'

export default{
    data(){
        return{
            clubid:null,
            activeIndex:0,
            competition:[],
            club:{

            },
            tableList:[],
            table:{},
        } 
    },
    created(){
        this.clubid=this.$route.params.clubid
        this.init()
        this.getTableList(this.clubid)

    },
    methods:{
        init(){
            club.getClub(this.clubid).then(response=>{
                this.club=response.data
                
            })
        },
        getTableList(clubid){
           table.showTables(clubid)
           .then(response=>{
               this.tableList=response.data
               console.log(this.tableList)
           })
       },
       book(id){
           let token=cookie.get('token')
            if(!token){
                loginEvent.$emit('loginDialogEvent')
                return
            };
            console.log(id)
           this.table=this.tableList[id]

           console.log(this.table)
           table.book(this.table)
           .then(response=>{
               this.$message({
                        type: 'success',
                        message: 'You book a table successfully!'
                    }) 
                this.clubid=this.$route.params.clubid
                this.getTableList(this.clubid)
                location.reload()
           })
           


       },

    },


}

</script>

<style>
.borderNone input.el-input__inner{
    border:none
}
</style>
