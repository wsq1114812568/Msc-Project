<template>
  <!-- header -->
  <div class="nav-container page-component">
    <!--左侧导航 #start -->
    <div class="nav left-nav">
      <div class="nav-item selected">
        <span class="v-link selected dark" > Competition Details </span>
      </div>
      <div class="nav-item">
        <span class="v-link clickable dark" :onclick="'javascript:window.location=\'/competition/participants/'+competition.competitionid+'\''"> Show participants </span>
      </div>
    </div>
    <!-- 左侧导航 #end -->

    <!-- 右侧内容 #start -->
    <div class="page-container">
      <div class="hospital-detail">
        <div class="common-header">
          <div class="title-wrapper" style="margin-left:300px"><span class="hospital-title">{{competition.competitionname}}</span>
          </div>
        </div>
        <div class="form-wrapper">
            <div class="inputDeep">
            <el-form ref="competition" :model="competition" >
                <el-form-item label="This competition is held by" >
                    <el-input class="borderNone" v-model="competition.clubname" readonly="true" style="width:300px"></el-input>
                </el-form-item>
                <el-form-item label="The time of this competition is" >
                    <el-input class="borderNone" v-model="competition.date" readonly="true" style="width:300px"></el-input>
                </el-form-item>
                 <el-form-item label="The location of this competition is" >
                    <el-input class="borderNone" v-model="competition.location" readonly="true" style="width:300px"></el-input>
                </el-form-item>
                 <el-form-item label="The number of participants is" >
                     <span>{{userNumber}}/{{competition.number}}</span>
                </el-form-item>
                <el-form-item label="The type of this competition is" >
                      <span class="iconfont" v-if="competition.type==0"> Men's singles</span>
                      <span class="iconfont" v-if="competition.type==1">Women's singles</span>
                      <span class="iconfont" v-if="competition.type==2">Men's doubles</span>
                      <span class="iconfont" v-if="competition.type==3">Women's doubles</span>
                </el-form-item>
                <el-form-item label="The status of this competition is" >
                      <span class="iconfont" v-if="competition.status==0"> Uncompleted</span>
                      <span class="iconfont" v-if="competition.type==1">Completed</span>
                </el-form-item>
                <el-button type="primary" style="margin-top:50px;margin-bottom:50px" @click="attend">Attend this Competition</el-button>

                
            </el-form>
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

import competition from '@/api/competition'

export default{
    data(){
        return{
            competitionid:null,
            activeIndex:0,
            competition:{
                params:{}
            },
            user:{
                params:{}
            },
            userNumber:null,
        } 
    },
    created(){
        this.competitionid=this.$route.params.competitionid
        this.init()

    },
    methods:{
        init(){
            competition.findById(this.competitionid).then(response=>{
                console.log(response.data)
                this.competition=response.data
            })
            competition.findUser(this.competitionid).then(response=>{
                console.log(response.data)
                this.user=response.data
                this.userNumber=this.user.length
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
