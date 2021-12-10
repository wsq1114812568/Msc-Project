<template>
  <!-- header -->
  <div class="nav-container page-component" style="margin-bottom:110px">
    <!--左侧导航 #start -->
    <div class="nav left-nav">
      <div class="nav-item selected">
        <span class="v-link selected dark" :onclick="'javascript:window.location=\'/club/'+club.clubid+'\''"> Club Details </span>
      </div>
      <div class="nav-item">
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
          <div class="title-wrapper" style="margin-left:300px;margin-bottom:100px;margin-top:50px"><span class="hospital-title">The Information of {{club.clubname}}</span>
          </div>
        </div>
        <div class="form-wrapper">
            <div class="inputDeep">
             <el-descriptions class="margin-top"  :column="3" :size="size" border style="width:80%">
                 <el-descriptions-item>
                    <template slot="label"><i class="el-icon-user"></i>Club ID</template>
                    {{club.clubid}}
                </el-descriptions-item>
                 <el-descriptions-item>
                    <template slot="label"><i class="el-icon-user"></i>Club Name</template>
                    {{club.clubname}}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template slot="label"><i class="el-icon"></i>Location</template>
                    {{club.location}}
                </el-descriptions-item>
            </el-descriptions>
            <el-descriptions class="margin-top"  :column="2" :size="size" border style="width:80%">
                 <el-descriptions-item>
                    <template slot="label"><i class="el-icon"></i>Contact Phone</template>
                    {{club.phone}}
                </el-descriptions-item>
                 <el-descriptions-item>
                    <template slot="label"><i class="el-icon"></i>Contact Email</template>
                    {{club.email}}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template slot="label"><i class="el-icon"></i>Contact Email</template>
                    {{club.email}}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template slot="label"><i class="el-icon"></i>Website</template>
                    {{club.website}}
                </el-descriptions-item>
            </el-descriptions>
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

            }
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
                console.log(this.club)
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
