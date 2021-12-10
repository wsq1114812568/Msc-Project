<template>
  <div class="app-container">
     <div id="main">
     <div id="title">
        Add New Administrator
     </div>
      <div id="form">
      <el-form label-width="120px">
         <el-form-item label="Name">
            <el-input v-model="admin.adminname" placeholder="input new administrator name"/>
         </el-form-item>
         <el-form-item label="Password">
            <el-input v-model="admin.password" placeholder="input more than six digit password"/>
         </el-form-item>
         <el-form-item>
            <el-button type="primary" @click="add">Add</el-button>
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

    },

    methods:{
       add(){
           adminmanagement.addUser(this.admin)
          .then(response=>{
             this.$message({
                  type: 'success',
                  message: 'Add Successfully'
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