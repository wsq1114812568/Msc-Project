<template>
    <!-- header -->
    <div class="nav-container page-component" style="margin-bottom:110px">
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
            <div class="nav-item selected">
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
        <div class="personal-patient">
        <div class="header-wrapper">
            <div class="title" style="text-align:center">My Messages</div>
        </div>
        <div class="content-wrapper">

        <el-card class="patient-card" shadow="always" v-for="item in messageList" :key="item.id">
            <div slot="header" class="clearfix">
                <div>
                    <span class="name">This message is from:&nbsp; {{ item.username }} &nbsp;&nbsp;&nbsp;&nbsp;{{ item.createTime }}</span>
                </div>
            </div>
            <div class="card SELF_PAY_CARD">
                <div class="info">
                    <span class="card-no">Content:&nbsp;&nbsp;{{ item.content }}</span>  
                </div>
                <span class="operate"></span>
            </div>
            <div class="card">
                <div class="text bind-card">
                     <el-button type="danger" @click="deleteMessage(item.id)">delete</el-button>
                </div>
            </div>
        </el-card>
        
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

import message from '@/api/message'

export default {
    data() {
        return {
            messageList:[],
        }
    },
    created() {
        this.showMessage()
    },
    methods: {
        showMessage(){
            message.showMessage().then(response=>{
                this.messageList=response.data
                console.log(this.messageList)
            })
        },
        deleteMessage(id){
            message.deleteMessage(id).then(response=>{
                this.$message({
                  type: 'success',
                  message: 'Delete this message successfully!'
                })  
            })
            window.location.reload()
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
