<template>
    <!-- header -->
    <div class="nav-container page-component">
        <!--左侧导航 #start -->
        <div class="nav left-nav">
            <div class="nav-item ">
                  <span class="v-link selected dark" onclick="javascript:window.location='/user'">Personal Information </span>
            </div>
            <div class="nav-item selected">
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
        <div class="personal-patient">
        <div class="header-wrapper">

             <div class="title" style="text-align:center">There are some users, you can add them as your new friends</div>
        </div>
        <div class="content-wrapper">

        <el-card class="patient-card" shadow="always" v-for="item in unfriendList" :key="item.friendid">
            <div slot="header" class="clearfix">
                <div>
                    <span class="name">User Name:  &nbsp;&nbsp;  {{ item.username }}</span>
                    <!-- <div  class="detail" @click="add()"> Add <span  class="iconfont"></span></div> -->
                     
                </div>
            </div>
            <div class="card SELF_PAY_CARD">
                <div class="info">
                    <span class="card-no">Contact Number:&nbsp;&nbsp;{{ item.phone }}</span>
                </div>
                <span class="operate"></span>
            </div>
            <div class="card">
                <div class="text bind-card">
                    <el-button  type="primary"  @click="add(item.id)">Add This Friend</el-button>
                </div>
            </div>
        </el-card>
        <el-pagination :current-page="current" :page-size="limit" :total="total" style="padding: 30px 0; text-align: center;" layout="total, prev, pager, next, jumper" @current-change="findUnFriendList"/>
        <!-- <div class="item-add-wrapper v-card clickable" @click="add()">
            <div class="">
                <div>+ Add New Friends</div>
            </div>
        </div> -->
        </div>
        </div>
    </div>
    <!-- 右侧内容 #end -->
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
            unfriendList: [],
            userInfo: {
                param: {}
            },
            current:1,
            limit:4,
            object:{
            },
            total:0,
        }
    },
    created() {
        this.getUserInfo()
        this.findUnFriendList()
    },
    methods: {

        getUserInfo() {
            user.getUserInfo().then(response => {
                this.userInfo = response.data
                console.log(this.userInfo)
            })
        },

        findUnFriendList(page=1) {
            this.current=page
            user.findUnFriend(this.current,this.limit,this.object).then(response => {
                this.unfriendList = response.data.records
                this.total=response.data.total
            })
        },

        add(friendid) {
            user.addFriend(friendid).then(response=>{
                 this.$message({
                  type: 'success',
                  message: 'The request of adding a friend has been sent!'
             })
             window.location.reload()
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