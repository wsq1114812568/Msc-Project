<template>
  <div class="app-container">
     <div id="main">
     <div id="title">
        {{user.username}} Attends Competitions
     </div>
     <div id="form" style="margin-bottom:25px">
         <el-table  :data="count" stripe style="width: 100%" >
             <el-table-column prop="total" label="Total Competitins"/>
             <el-table-column prop="win" label="Win"/>
             <el-table-column prop="lose" label="Lose"/>
        </el-table>

    </div>
    
      <div id="form">
      <el-table :data="competition" stripe style="width: 100%" >
          <el-table-column prop="competitionid" label="Competition ID"/>
          <el-table-column prop="competitionName" label="Competition Name"/>
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
import usermanagement from '@/api/usermanagement'
export default{
    data(){
        return{
            user:{},
            competition:[],
            count:[{
                    total:0,
                    win:0,
                    lose:0,
                }]
        }
    },

    created(){
        const id=this.$route.params.id
        this.getUser(id)
        this.getCompetition(id)
    },

    methods:{
        getUser(id) {
         usermanagement.getUser(id) 
            .then(response => {
               this.user = response.data
            })
        },
        getCompetition(id){
            usermanagement.findCompetition(id)
            .then(response=>{
                this.competition=response.data
                console.log(this.count[0].total)
                for(var i=0;i<this.competition.length;i++){
                    this.count[0].total+=1
                    if(this.competition[i].result==0){
                        this.count[0].lose+=1
                    }
                    else{
                        this.count[0].win+=1
                    }
                }
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