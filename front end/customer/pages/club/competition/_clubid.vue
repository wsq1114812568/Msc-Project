<template>
  <!-- header -->
  <div class="nav-container page-component" style="margin-bottom:110px">
    <!--左侧导航 #start -->
    <div class="nav left-nav">
      <div class="nav-item ">
        <span class="v-link selected dark" :onclick="'javascript:window.location=\'/club/'+club.clubid+'\''"> Club Details </span>
      </div>
      <div class="nav-item selected">
        <span class="v-link clickable dark" :onclick="'javascript:window.location=\'/club/competition/'+club.clubid+'\''"> Show Competitions </span>
      </div>
      <div class="nav-item">
        <span class="v-link clickable dark" :onclick="'javascript:window.location=\'/club/tables/'+club.clubid+'\''"> Available Tables </span>
      </div>
    </div>
    <!-- 左侧导航 #end -->

    <!-- 右侧内容 #start -->
    <div class="page-container">
      <div class="hospital-detail">
        <div class="common-header">
          <div class="title-wrapper" style="margin-left:300px;margin-bottom:100px;margin-top:50px"><span class="hospital-title">The Competitions of {{club.clubname}}</span>
          </div>
        </div>
        <div class="form-wrapper">
            <div class="inputDeep">
                <el-table :data="competition" stripe  >
                <el-table-column prop="competitionid" label="ID" />
                <el-table-column prop="competitionname" label="Name"/>
                <el-table-column prop="date" label="Date"/>

                <el-table-column prop="rule" label="Type">
                    <template slot-scope="scope">
                        <span v-if="scope.row.type==0">Men Singles</span>
                        <span v-if="scope.row.type==1">Women Singles</span>
                        <span v-if="scope.row.type==2">Men Doubles</span>
                        <span v-if="scope.row.type==3">Women Doubles</span>
                    </template>
                </el-table-column>

                <el-table-column prop="status" label="status">
                    <template slot-scope="scope">
                        <span v-if="scope.row.status==0">uncompleted</span>
                        <span v-if="scope.row.status==1">completed</span>
                    </template>
                </el-table-column>

                <el-table-column prop="number" label=" participants">
                    <template slot-scope="scope">
                        <span v-if="scope.row.number!=scope.row.now">{{scope.row.now}}/{{scope.row.number}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="Show Details" width="280" align="center">
                <template slot-scope="scope">
                    <el-button type="primary" size="mini"   @click="show(scope.row.competitionid)">Show </el-button>
                    
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

export default{
    data(){
        return{
            clubid:null,
            activeIndex:0,
            competition:[],
            club:{

            },
        } 
    },
    created(){
        this.clubid=this.$route.params.clubid
        this.init()

    },
    methods:{
        init(){
            club.getClub(this.clubid).then(response=>{
                this.club=response.data

            })
            club.findCompetition(this.clubid).then(response=>{
                this.competition=response.data
                console.log(this.competition)
            })
        },
        show(competitionid){
             let token=cookie.get('token')
                if(!token){
                    loginEvent.$emit('loginDialogEvent')
                    return
                };
             window.location.href ='/competition/'+competitionid
        }

    },


}

</script>

<style>
.borderNone input.el-input__inner{
    border:none
}
</style>
