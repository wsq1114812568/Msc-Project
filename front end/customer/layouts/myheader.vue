<template>
    <div class="header-container">
        <div class="wrapper">
        <!-- logo -->
            <div class="left-wrapper v-link selected">
                <img style="width: 52px" width="52" height="52" src="~assets/images/logo.jpeg">
                <span class="v-link selected dark" onclick="javascript:window.location='http://localhost:3000'"><strong >Table Tennis Competition System</strong></span>
            </div>
            <!-- right -->
            <div class="right-wrapper">
                <!-- <span class="v-link clickable">Clubs</span> -->
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span v-if="name == ''" class="v-link clickable" @click="showLogin()" id="loginDialog">Login/Register</span>
                <el-dropdown v-if="name != ''" @command="loginMenu">
                    <span class="el-dropdown-link">{{ name }} </span>
                    <el-dropdown-menu class="user-name-wrapper" slot="dropdown">
                        <el-dropdown-item command="/user" divided>Personal Information</el-dropdown-item>
                        <el-dropdown-item command="/friend" divided>My Friends</el-dropdown-item>
                        <el-dropdown-item command="/friend/request" divided>Friend Requests</el-dropdown-item>
                        <el-dropdown-item command="/friend/message" divided>My Messages</el-dropdown-item>
                        <el-dropdown-item command="/competition" divided>My Competition</el-dropdown-item>
                        <el-dropdown-item command="/results" divided>My Results</el-dropdown-item>
                        <el-dropdown-item command="/logout" divided>logout</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </div>

        <!-- login -->
        <el-dialog :visible.sync="dialogUserFormVisible" v-if=dialogUserFormVisible style="text-align:left;" top="50px" :append-to-body="true"  width="960px" @close="closeDialog()">
            <div class="container">
                <!-- login by phone -->  
                <div class="operate-view" v-if="dialogAtrr.showLoginType === 'weixin'">
                    <div class="wrapper" style="width: 100%">
                        <div class="mobile-wrapper" style="position: static; width: 70%">
                            <span class="title">Login by {{ dialogAtrr.labelTips }}</span>
                            <el-form>
                              <el-form-item>
                                    <el-input v-model="dialogAtrr.inputValue" :placeholder="dialogAtrr.placeholder" :maxlength="dialogAtrr.maxlength" class="input v-input">
                                        <span slot="suffix" class="sendText v-link" v-if="dialogAtrr.second > 0">{{ dialogAtrr.second }}s </span>
                                        <span slot="suffix" class="sendText v-link highlight clickable selected" v-if="dialogAtrr.second == 0" @click="getCodeFun()">resend </span>
                                    </el-input>
                                </el-form-item>  
                            </el-form>
                            <div class="send-button v-button" @click="btnClick()"> {{ dialogAtrr.loginBtn }}
                            </div>
                            <div class="bottom">
                                <div class="wechat-wrapper" @click="phoneLogin()">
                                     <span class="iconfont icon"></span>
                                </div>
                                <span class="third-text"> Login by password </span>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- login by password -->
                <div class="operate-view"  v-if="dialogAtrr.showLoginType === 'phone'" >
                    <div class="wrapper wechat" style="height: 400px">
                        <div class="mobile-wrapper" style="position: static; width:80%">
                        <div>
                            <div id="weixinLogin">
                                <span class="title" style="text-align: center;display:block;">Login by name and password</span>
                                <el-form ref="loginForm" :model="loginForm" :rules="rules">
                                <el-form-item label="username" prop="name">
                                    <el-input v-model="loginForm.name"></el-input>
                                </el-form-item>  
                                <el-form-item label="password" prop="pwd">
                                    <el-input v-model="loginForm.password" type="password"/>
                                </el-form-item>
                                </el-form>
                                <div align="center">
                                <el-button type="primary" @click="loginByPwd">Login</el-button>
                            </div>
                            </div>
                        </div>
                        <div class="bottom wechat" style="margin-top: -20px;">
                            <div class="phone-container">
                                <div class="phone-wrapper"  @click="weixinLogin()">
                                    <span class="iconfont icon"></span>
                                </div>
                                <span class="third-text"> login by phone and verification code</span>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
                <!--register-->
                <div class="operate-view">
                    <div class="wrapper" style="width: 100%">
                        <div class="mobile-wrapper" style="position: static; width: 70%">
                            <span class="title" style="text-align: center;display:block;">Register</span>
                            <el-form ref="registerForm" :model="registerForm" :rules="rules">
                              <el-form-item label="username" prop="name">
                                  <el-input v-model="registerForm.name"></el-input>
                              </el-form-item>  
                              <el-form-item label="password" prop="pwd">
                                  <el-input v-model="registerForm.pwd" type="password"/>
                              </el-form-item>
                              <el-form-item label="phone" prop="phone" >
                                  <el-input v-model="registerForm.phone"/>
                              </el-form-item>
                            </el-form>
                            <div align="center">
                                <el-button type="primary" @click="register">Register</el-button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </el-dialog>


        <div></div>
    </div>
    
    
</template>

<script>
import cookie from 'js-cookie'
import Vue from 'vue'
import userInfoApi from '@/api/user'
import smsApi from '@/api/code'
// import hospitalApi from '@/api/hosp'

const defaultDialogAtrr={
    showLoginType:'phone',
    labelTips:'phone number',
    inputValue:'',//input box object
    placeholder:' input your phone number',
    maxlength:11,//the length of phone number
    loginBtn:'get verification code',
    sending:true,
    second:-1,
    clearSmsTime:null
}

export default{
    data(){
        return {
            activeIndex: '1',
            activeIndex2: '1',
            userInfo:{
                name:'',
                phone:'',
                code:'',
                openid:'',
                password:''
            },
            dialogUserFormVisible:false,
            dialogAtrr:defaultDialogAtrr,
            name:'',
            registerForm:{
                name:'',
                password:'',
                phone:'',
            },
            loginForm:{
                name:'',
                password:'',
            },
            rules:{
                name:[{
                    required:true,
                    type:'string',
                    message:"input user name",
                    trigger:'blur'
                }],
                phone:[{
                    required:true,
                    message:"Please input a eleven-digit phone number",
                    trigger:'blur'
                }],
            }
        }
    },

    created(){
        this.showInfo()
    },

     //global login
    mounted(){
        window.loginEvent=new Vue();
        loginEvent.$on('loginDialogEvent',function(){
            document.getElementById("loginDialog").click();
        })

    },

    methods:{

        //login button
        btnClick(){
            //judge it is to get code or to login
            if(this.dialogAtrr.loginBtn == 'get verification code'){
                this.userInfo.phone = this.dialogAtrr.inputValue
                this.getCodeFun()
            }
            else{
                //login
                this.login()
            }
        },

        //showLogin
        showLogin(){
            this.dialogUserFormVisible = true
            this.dialogAtrr = { ...defaultDialogAtrr }
        },

        //login
        login(){
            this.userInfo.code=this.dialogAtrr.inputValue
            if(this.dialogAtrr.loginBtn =='submitting...'){
                this.$message.error('repeated submitting')
                return;
            }
            if (this.userInfo.code == '') {
                this.$message.error('The verification code must be entered')
                return;
            }
            if (this.userInfo.code.length!=6) {
                this.$message.error('The verification code format is incorrect')
                return;
            }
            this.dialogAtrr.loginBtn='submitting'
            userInfoApi.login(this.userInfo).then(response=>{
                console.log(response.data)
                this.setCookies(response.data.name,response.data.token)
            }).catch(e=>{
                this.dialogAtrr.loginBtn="loging now"
            })
            
        },

        loginByPwd(){
            this.userInfo.name=this.loginForm.name
            this.userInfo.password=this.loginForm.password
             if (this.userInfo.name == '') {
                this.$message.error('Please input username')
                return;
            }
            if (this.userInfo.password=='') {
                this.$message.error('Please input password')
                return;
            }
            userInfoApi.loginByName(this.userInfo).then(response=>{
                console.log(response.data)
                this.setCookies(response.data.name,response.data.token)
            })
        },

        setCookies(name,token){
            cookie.set('token', token, { domain: 'localhost' })
            cookie.set('name', name, { domain: 'localhost' })
            window.location.reload()
            console.log(name)
            console.log(token)
        },

        //get code
        getCodeFun(){
            if(!(/^1[34578]\d{9}$/.test(this.userInfo.phone))) {
                this.$message.error('phone number is wrong')
                return;
            }
             this.dialogAtrr.inputValue = ''
             this.dialogAtrr.placeholder = 'please input vertification code'
             this.dialogAtrr.maxlength = 6
             this.dialogAtrr.loginBtn = 'login now'
             if (!this.dialogAtrr.sending) return;
             this.timeDown();
             this.dialogAtrr.sending = false;
              smsApi.sendCode(this.userInfo.phone).then(response=>{
                  this.timeDown();
              }).catch(e=>{
                  this.$message.error('fail to send, try again')
                  this.showLogin()
              })
        },

        timeDown(){
            if(this.clearSmsTime){
                clearInterval(this.clearSmsTime);
            }
            this.dialogAtrr.second=60;
            this.dialogAtrr.labelTips = 'vertification code has been sent to' + this.userInfo.phone
            this.clearSmsTime=setInterval(()=>{
                --this.dialogAtrr.second;
                if(this.dialogAtrr.second<1){
                     clearInterval(this.clearSmsTime);
                     this.dialogAtrr.sending = true;
                      this.dialogAtrr.second = 0;
                }
            },1000);
        },

        closeDialog() {
            if(this.clearSmsTime) {
                clearInterval(this.clearSmsTime);
            }
        },

        showInfo() {
            let token = cookie.get('token')
            if (token) {
                this.name = cookie.get('name')
                console.log(this.name)
            }
        },

        loginMenu(command) {
            if('/logout' == command) {
            cookie.set('name', '', {domain: 'localhost'})
            cookie.set('token', '', {domain: 'localhost'})

            //跳转页面
            window.location.href = '/'
             } else {
            window.location.href = command
            }
         },

        handleSelect(item) {
            window.location.href = '/hospital/' + item.hoscode
        },

        weixinLogin() {
             this.dialogAtrr.showLoginType = 'weixin'
        },

        phoneLogin() {
            this.dialogAtrr.showLoginType = 'phone'
            this.showLogin()
        },

        register(){
            this.userInfo.name=this.registerForm.name
            this.userInfo.phone=this.registerForm.phone
            this.userInfo.password=this.registerForm.password
            if (this.userInfo.password.length<=6) {
                this.$message.error('Please input a password more than six digit ')
                return;
            }
            if (this.userInfo.phone.length!=11) {
                this.$message.error('Please input a eleven-digit  phone number')
                return;
            }
            userInfoApi.register(this.userInfo).then(response=>{
                console.log(this.userInfo.name)
                console.log(this.userInfo.phone)
                console.log(this.userInfo.password)
                alert('register success')
                
            })
        },
        handleSelect2(key, keyPath) {
        console.log(key, keyPath);
      },

    }
}
</script>



