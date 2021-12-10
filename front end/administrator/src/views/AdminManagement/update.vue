<template>
  <div class="app-container">
     <div id="main">
     <div id="title">
        Update Administrator Information
     </div>
      <div id="form">
      <el-form label-width="120px">
         <el-form-item label="adminName">
            <el-input v-model="admin.adminname" placeholder="input new administrator name"/>
         </el-form-item>
         <el-form-item label="password">
            <el-input v-model="admin.password" placeholder="input more than six digit password"/>
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
import adminmanagement from '@/api/adminmanagement'
export default{
    data(){
        return{
            admin:{}
        }
    },

    created(){
        const id=this.$route.params.id
        this.getUser(id)
    },

    methods:{
        getUser(id) {
         adminmanagement.getUser(id) 
            .then(response => {
               this.admin = response.data
            })
        },

       update(){
          adminmanagement.updateUser(this.admin)
          .then(response=>{
             this.$message({
                  type: 'success',
                  message: 'Update Successfully'
             })
             this.$router.push({path:'/AdminManagement/Adminlist'})
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