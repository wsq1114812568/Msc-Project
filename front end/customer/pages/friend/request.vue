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
             <div class="nav-item selected">
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
        <div class="personal-patient">
        <div class="header-wrapper">
            <div class="title" style="text-align:center">The Friend Requests I received</div>
        </div>
        <div class="content-wrapper">

        <el-card class="patient-card" shadow="always" v-for="item in friendList" :key="item.friendid">
            <div slot="header" class="clearfix">
                <div>
                    <span class="name">This Friend Request is from:  &nbsp;  {{ item.friendname }}</span>
                    <!-- <div  class="detail" @click="show(item.friendid)"> show <span  class="iconfont"></span></div> -->
                    
                </div>
            </div>
            <div class="card SELF_PAY_CARD">
                <div class="info">
                    <span class="card-no">Contact Number:&nbsp;&nbsp;{{ item.friendphone }}</span>
                </div>
                <span class="operate"></span>
            </div>
            <div class="card">
                <div class="text bind-card">
                    <el-button type="success"  @click="agree(item.friendid)">Agree</el-button>
                    <el-button  type="warning" @click="notagree(item.friendid)">Not Agree</el-button>
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

import user from '@/api/user'
export default {
    data() {
        return {
            friendList: [],
            userInfo: {
                param: {}
            },
        }
    },
    created() {
        this.getUserInfo()
        this.findFriendList()
    },
    methods: {

        getUserInfo() {
            user.getUserInfo().then(response => {
                this.userInfo = response.data
            })
        },

        findFriendList() {
            user.findFriendRequest().then(response => {
                this.friendList = response.data
                console.log(this.friendList)
            })
        },
        agree(friendid){
            user.agreeFriend(friendid).then(response=>{
                 this.$message({
                  type: 'success',
                  message: 'You receive this friend request!'
             })
            })
            window.location.reload()
        },
        
        notagree(friendid){
            user.deleteFriend(friendid).then(response=>{
                this.$message({
                  type: 'fail',
                  message: 'You refuse this friend request!'
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
