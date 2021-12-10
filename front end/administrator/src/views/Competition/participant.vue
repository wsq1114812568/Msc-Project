<template>
  <div class="app-container">
     <div id="main">
     <div id="title">
       participants of  {{competition.competitionname}}
     </div>
     <div id="form" style="margin-bottom:25px">
         <el-table  :data="count" stripe style="width: 100%" >
             <el-table-column prop="total" label="Total participants needed"/>
             <el-table-column prop="now" label="Number of applicants"/>
             <el-table-column prop="lack" label="Number of people lack"/>
        </el-table>

    </div>
    
      <div id="form">
      <el-table :data="user" stripe style="width: 100%" >
          <el-table-column prop="userid" label="User ID"/>
          <el-table-column prop="userName" label="User Name"/>
          <el-table-column prop="result" label="Reuslt">
                <template slot-scope="scope">
                    <span v-if="scope.row.result==0">Lose</span>
                    <span v-if="scope.row.result==1">Win</span>
                </template>
            </el-table-column>
     </el-table>
      </div>
      </div>
   </div>
</template>

<script>
import competition from '@/api/competition'

export default{

   data(){
       return{
          competition:{},
          user:[],
          count:[{
                    total:0,
                    now:0,
                    lack:0,
                }]
       }
   },

   created(){
       const id=this.$route.params.id
       this.getCompetition(id)
       this.getUser(id)
   },

   methods:{
       getCompetition(id){
           competition.get(id)
           .then(response=>{
               this.competition=response.data
               this.count[0].total=this.competition.number
               console.log(this.competition)
           })
       },
       getUser(id){
           competition.findUser(id)
           .then(response=>{
               this.user=response.data
               this.count[0].now=this.user.length
               this.count[0].lack=this.count[0].total-this.count[0].now
               console.log(this.user)
           })

       }

       
   }

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