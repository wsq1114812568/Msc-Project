<template>
  <!-- header -->

  <div class="nav-container page-component" style="margin-bottom:110px">
    <!--左侧导航 #start -->
    <div class="nav left-nav">
      <div class="nav-item selected">
        <span class="v-link selected dark" onclick="javascript:window.location='/user'">Personal Information </span>
      </div>
      <div class="nav-item">
        <span class="v-link selected dark" onclick="javascript:window.location='/friend'"> My Friends </span>
      </div>
      <div class="nav-item ">
        <span class="v-link selected dark" onclick="javascript:window.location='/friend/request'"> Friend Requests </span>
      </div>
       <div class="nav-item ">
        <span class="v-link selected dark" onclick="javascript:window.location='/friend/message'"> My Messages </span>
      </div>
      <div class="nav-item ">
        <span class="v-link clickable dark" onclick="javascript:window.location='/competition'"> My Competitions </span>
      </div>
      <div class="nav-item ">
        <span class="v-link clickable dark" onclick="javascript:window.location='/results'"> My Results </span>
      </div>
       <div class="nav-item ">
        <span class="v-link clickable dark" onclick="javascript:window.location='/table'"> My Bookings </span>
      </div>
     
    </div>

    <!-- 左侧导航 #end -->
    <!-- 右侧内容 #start -->
    <div class="page-container">
      <div>
        <div class="title" style="text-align:center">Personal Information</div>
        <div class="form-wrapper">
            <el-form :model="userInfo" label-width="110px" label-position="left" :rules="rules">
                  <el-form-item prop="username" label="username：" class="form-normal">
                      <div class="name-input">
                          <el-input v-model="userInfo.username" placeholder="input new username" class="input v-input"/>
                      </div>
                   </el-form-item>
                   <el-form-item prop="password" label="password：" class="form-normal">
                      <div class="name-input">
                          <el-input v-model="userInfo.password" placeholder="input new password" class="input v-input"/>
                      </div>
                   </el-form-item>
                    <el-form-item prop="phone" label="phone：" class="form-normal">
                      <div class="name-input">
                          <el-input v-model="userInfo.phone" placeholder="input new phone" class="input v-input"/>
                      </div>
                   </el-form-item>
                   <el-form-item prop="gender" label="gender：" class="form-normal" style="margin-bottom:150px">
                      <div class="name-input">
                        <el-select v-model="userInfo.gender" @change="getSelected" >
                          <el-option v-for="item in genderList" :key="item.label" :value="item.gendername" ></el-option>
                        </el-select>
                          <!-- <el-input v-model="userInfo.gender" placeholder="input your gender" class="input v-input"/> -->
                      </div>
                   </el-form-item>
                   <el-button type="primary" @click="save" style="width:100%"> Update</el-button>
            </el-form>
        </div>
        <!-- <div class="context-container" >
            <div class="button-wrapper">

            </div>
        </div> -->

      </div>
    </div>
  </div>
  <!-- footer -->
</template>

<script>
import '~/assets/css/hospital_personal.css'
import '~/assets/css/hospital.css'
import '~/assets/css/personal.css'

import user from '@/api/user'

export default {
  data() {
    return {
      userInfo: {
        param: {}
      },
      submitBnt: 'update',
      rules:{
                username:[{
                    required:true,
                    type:'string',
                    message:"input user name",
                    trigger:'blur'
                }],
                password:[{
                    required:true,
                    message:"Please input a password more than six digit  ",
                    trigger:'blur'
                }],
                phone:[{
                    required:true,
                    message:"Please input a eleven-digit phone number",
                    trigger:'blur'
                }],
      },
     genderList:[
               {
                  id:0,
                  gendername:"Male"
               },
               {
                  id:1,
                  gendername:"Female"
               },
            ]
    }
  },

  created() {
    this.init()
    
  },

  methods: {

    init() {
      this.getUserInfo()
  
    },

    getUserInfo() {
      user.getUserInfo().then(response => {
        this.userInfo = response.data
        console.log(this.userInfo)
      })
    },


   save(){
       console.log(this.userInfo.username)
       if(this.userInfo.username!=""&&this.userInfo.phone!=""&&this.userInfo.password!=""){
           user.updateUser(this.userInfo)
          .then(response=>{
             this.$message({
                  type: 'success',
                  message: 'Update Successfully'
             })
             window.location.reload()
          })
       }
       else{
            this.$message({
                  type: 'fail',
                  message: 'Update failed'
             })
       }       
    },
    getSelected(val){
          for(let i=0;i<this.genderList.length;i++){
             if(this.genderList[i].gendername==val){
                this.userInfo.gender=this.genderList[i].id
             }
          }
       }

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
  .patient-card .el-card__header .detail {
    font-size: 14px;
  }
  .page-container .title {
    letter-spacing: 1px;
    font-weight: 700;
    color: #333;
    font-size: 16px;
    margin-top: 0;
  }
  .page-container .tips {
    width: 100%;
    padding-left: 0;
  }
  .page-container .form-wrapper {
    padding-left: 92px;
    width: 580px;
  }
  .form-normal {
    height: 40px;
    
  }
  .bottom-wrapper{
    width: 100%;
    padding: 0;
    margin-top: 0;
  }
</style>
