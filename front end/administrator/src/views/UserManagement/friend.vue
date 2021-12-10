<template>
  <div class="app-container">
     <div id="main">
     <div id="title">
        Show Friends of {{user.username}}
     </div>
      <div id="form">
      <el-table :data="friend" stripe style="width: 100%" >
          <el-table-column prop="friendid" label="Friend ID"/>
          <el-table-column prop="friendname" label="Friend Name"/>
          <el-table-column prop="friendphone" label="Contact"/>

     </el-table>
      </div>
      </div>
   </div>
</template>

<script>
import usermanagement from '@/api/usermanagement'
export default{
    data(){
        return{
            user:{},
            friend:[],
        }
    },

    created(){
        const id=this.$route.params.id
        this.getUser(id)
        this.getFriend(id)
    },

    methods:{
        getUser(id) {
         usermanagement.getUser(id) 
            .then(response => {
               this.user = response.data
               console.log(this.user)
            })
        },
        getFriend(id){
            usermanagement.findFriend(id)
            .then(response=>{
                
                this.friend=response.data
                console.log(this.friend)

            })
        }

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