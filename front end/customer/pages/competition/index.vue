<template>
    <!-- header -->
    <div class="nav-container page-component" style="margin-bottom:120px">
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
            <div class="nav-item ">
                <span class="v-link selected dark" onclick="javascript:window.location='/friend/message'"> My Messages </span>
            </div>
            <div class="nav-item  selected">
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
            <div class="title" style="text-align:center">My Competitions</div>
        </div>
        <div class="content-wrapper">

         <el-table :data="competitionList" stripe style="width: 100%" >
            <el-table-column label="Competition Name">
                <template slot-scope="scope">
                    <span :onclick="'javascript:window.location=\'/competition/'+scope.row.competitionid+'\''">
                        {{scope.row.competitionname}}
                    </span>

                </template>
            </el-table-column>
            <el-table-column prop="clubname" label="Club Name"/>
            <el-table-column prop="date" label="Date"/>
            <el-table-column prop="location" label="Location"/>

            <el-table-column prop="type" label="Type">
                <template slot-scope="scope">
                    <span v-if="scope.row.type==0">Men Singles</span>
                    <span v-if="scope.row.type==1">Women Singles</span>
                    <span v-if="scope.row.type==2">Men Doubles</span>
                    <span v-if="scope.row.type==3">Women Doubles</span>
                </template>
            </el-table-column>

            <el-table-column prop="status" label="Status">
                <template slot-scope="scope">
                    <span v-if="scope.row.status==0">uncompleted</span>
                    <span v-if="scope.row.status==1">completed</span>
                </template>
            </el-table-column>

            
        </el-table>
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
            competitionList:[]
        }
    },
    created() {
        this.getUserInfo()
        this.findFriendList()
        this.getCompetitionList()
    },
    methods: {
        getCompetitionList(){
            user.findCompetition2().then(response=>{
                this.competitionList=response.data
                console.log( this.competitionList)
            })

        },

        getUserInfo() {
            user.getUserInfo().then(response => {
                this.userInfo = response.data
                
            })
        },


        findFriendList() {
            user.findFriend().then(response => {
                this.friendList = response.data
                for(var i=0;i<this.friendList.length;i++){
                    this.$set(this.friendList[i],"content","")
                }
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
