<template>
  <div class="app-container">
     <div id="main">
     <div id="title">
        Add New Club
     </div>
      <div id="form">
      <el-form label-width="120px">
         <el-form-item label="ClubName">
            <el-input v-model="club.clubname" placeholder="input new club name"/>
         </el-form-item>
         <el-form-item label="location">
            <el-input v-model="club.location" placeholder="input new club location"/>
         </el-form-item>
         <el-form-item label="phone">
            <el-input v-model="club.phone" placeholder="input new club phone"/>
         </el-form-item>
         <el-form-item label="email">
            <el-input v-model="club.email" placeholder="input new club email"/>
         </el-form-item>
         <el-form-item label="website">
            <el-input v-model="club.website" placeholder="input new club website"/>
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
import clubmanagement from '@/api/clubmanagement'
export default{
    data(){
        return{
            club:{}
        }
    },

    created(){

    },

    methods:{
       add(){
           clubmanagement.addClub(this.club)
          .then(response=>{
             this.$message({
                  type: 'success',
                  message: 'Add Successfully'
             })
             this.$router.push({path:'/ClubManagement/Clublist'})
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