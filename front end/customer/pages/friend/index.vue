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
            <div class="title" style="text-align:center">My Friends</div>
        </div>
        <div class="content-wrapper">

        <el-card class="patient-card" shadow="always" v-for="item in friendList" :key="item.friendid">
            <div slot="header" class="clearfix">
                <div>
                    <span class="name">Friend Name:  &nbsp;&nbsp;  {{ item.friendname }}</span>
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
                     <el-form :inline="true" class="demo-form-inline">
                        <el-form-item>
                            <el-input  v-model="item.content" placeholder="send message to friend"/>
                        </el-form-item>
                        <el-button type="primary" @click="send(item.content,item.friendid)">Send</el-button>
                    </el-form>
                </div>
            </div>
        </el-card>
        <div class="item-add-wrapper v-card clickable" @click="add()">
            <div class="">
                <div>+ Add New Friends</div>
            </div>
        </div>
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
import message from '@/api/message'

export default {
    data() {
        return {
            friendList: [],
            userInfo: {
                param: {}
            },
            object:{},
            current:1,
            limit:4,
            total:0,
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
                console.log(this.userInfo)
                
            })
        },

        // findFriendList(page=1) {
        //     this.current=page
        //     user.findFriendPage(this.current,this.limit).then(response => {
        //         this.friendList = response.data.records
        //         for(var i=0;i<this.friendList.length;i++){
        //             this.$set(this.friendList[i],"content","")
        //         }
        //         console.log(this.friendList)
        //     })
        // },
        findFriendList() {
            user.findFriend().then(response => {
                this.friendList = response.data
                for(var i=0;i<this.friendList.length;i++){
                    this.$set(this.friendList[i],"content","")
                }
                console.log(this.friendList)
            })
        },

        add() {
            window.location.href = '/friend/add'
        },
        send(content,friendid){
            console.log(friendid)
            this.object.content=content
            console.log(this.object.content)
            message.sendMessage(friendid,this.object).then(response=>{
                this.$message({
                  type: 'success',
                  message: 'You have sent a message!'
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
