<template>
  <!-- header -->
  <div class="nav-container page-component">
    <!--左侧导航 #start -->
    <div class="nav left-nav">
      <div class="nav-item ">
        <span class="v-link selected dark" :onclick="'javascript:window.location=\'/competition/'+competition.competitionid+'\''"> Competition Details </span>
      </div>
      <div class="nav-item selected">
        <span class="v-link clickable dark" :onclick="'javascript:window.location=\'/competition/participants/'+competition.competitionid+'\''"> Show participants </span>
      </div>
    </div>
    <!-- 左侧导航 #end -->

    <!-- 右侧内容 #start -->
    <div class="page-container" style="margin-bottom:120px">
      <div class="hospital-detail">
        <div class="common-header">
          <div class="title-wrapper" style="margin-left:300px"><span class="hospital-title">The participants of  {{competition.competitionname}}</span>
          </div>
        </div>
        <div class="form-wrapper">

             <div id="form" style="margin-bottom:25px">
                <el-table  :data="count" stripe style="width: 60%" >
                    <el-table-column prop="total" label="Total participants needed"/>
                    <el-table-column prop="now" label="Number of applicants"/>
                    <el-table-column prop="lack" label="Number of people lack"/>
                </el-table>
            </div>

             <el-table :data="user" stripe style="width: 60%" >
                  <el-table-column label="Participant Name" prop="username"/>  
                  <el-table-column label="Gender" prop="gender">  
                      <template slot-scope="scope">
                        <span v-if="scope.row.gender==0">Male</span>
                        <span v-if="scope.row.gender==1">Female</span>
                     </template>
                </el-table-column>
                  <el-table-column label="Contact Information" prop="phone"/>  
             </el-table>
            
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

import competition from '@/api/competition'

export default{
    data(){
        return{
            competitionid:null,
            activeIndex:0,
            competition:{
                params:{}
            },
            user:[],
            userNumber:null,
            count:[{
                    total:0,
                    now:0,
                    lack:0,
                }]
        } 
    },
    created(){
        this.competitionid=this.$route.params.competitionid
        this.init()

    },
    methods:{
        init(){
            competition.findById(this.competitionid).then(response=>{
                this.competition=response.data
                this.count[0].total=this.competition.number
            })
            competition.findUser2(this.competitionid).then(response=>{
                this.user=response.data
                this.userNumber=this.user.length
                this.count[0].now=this.userNumber
                this.count[0].lack=this.count[0].total-this.count[0].now
                console.log(this.user)
            })
        },
        attend(){
            let token=cookie.get('token')
            if(!token){
                loginEvent.$emit('loginDialogEvent')
                return
            };
            competition.attend(this.competitionid).then(response=>{
                console.log(response)
                if(response.code==200){
                    this.$message({
                        type: 'success',
                        message: 'You attend this competition successfully!'
                    })  
                    window.location.href ='/competition/'
                }
                else{
                    this.$message({
                        type: 'fail',
                        message: response.message
                    })

                }
            })
        }

    },


}

</script>

<style>
.borderNone input.el-input__inner{
    border:none
}
</style>
