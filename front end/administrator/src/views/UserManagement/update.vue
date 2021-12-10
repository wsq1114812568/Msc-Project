<template>
  <div class="app-container">
     <div id="main">
     <div id="title">
        Update User Information
     </div>
      <div id="form">
      <el-form label-width="120px">
         <el-form-item label="UserName">
            <el-input v-model="user.username" placeholder="input new user name"/>
         </el-form-item>
         <el-form-item label="password">
            <el-input v-model="user.password" placeholder="input more than six digit password"/>
         </el-form-item>
         <el-form-item label="phone">
            <el-input v-model="user.phone" placeholder="input eleven digit phone number"/>
         </el-form-item>
         <el-form-item>
            <el-button type="primary" @click="update">Update</el-button>
         </el-form-item>
      </el-form>
      </div>
      </div>
   </div>
</template>

<script>
import usermanagement from '@/api/usermanagement'
export default{
    data(){
        return{
            user:{}
        }
    },

    created(){
        const id=this.$route.params.id
        this.getUser(id)
    },

    methods:{
        getUser(id) {
         usermanagement.getUser(id) 
            .then(response => {
               this.user = response.data
            })
        },

       update(){
          usermanagement.updateUser(this.user)
          .then(response=>{
             this.$message({
                  type: 'success',
                  message: 'Update Successfully'
             })
             this.$router.push({path:'/UserManagement/Userlist'})
          })

       },

    },
}
</script>
<style>
#main{
   width:50%;			
height:100%;	

}
#title{
   text-align: center;
    margin-bottom: 50px;
}
#form{
text-align: center;
		

}
</style>