<template>
  <div class="app-container">
     <div id="main">
     <div id="title">
        Update Club Information
     </div>
      <div id="form">
      <el-form label-width="120px">
         <el-form-item label="Name">
            <el-input v-model="competition.competitionname" placeholder="input new competition name"/>
         </el-form-item>
         <el-form-item label="date">
            <el-input type="datetime-local" v-model="competition.date" placeholder="input new competition date"/>
         </el-form-item>
         <el-form-item label="rule">
            <!-- <el-input v-model="competition.rule" placeholder="input new competition rule"/> -->
            <el-select v-model="competition.type" @change="getSelected2" style="width:100%">
               <el-option v-for="item in ruleList" :key="item.label" :value="item.rulename" ></el-option>
            </el-select>
         </el-form-item>
         
         <el-form-item label="people number">
            <el-input v-model="competition.number" placeholder="input new competition people number"/>
         </el-form-item>

        <el-form-item label="club id">
            <!-- <el-input v-model="competition.clubid" placeholder="input new competition club id"/> -->
            <el-select v-model="competition.clubid" @change="getSelected" style="width:100%">
               <el-option v-for="item in clubList" :key="item.label" :value="item.clubname" ></el-option>
            </el-select>
         </el-form-item>

         <el-form-item label="winner id">
            <el-input v-model="competition.winnerid" placeholder="input new competition winner id"/>
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
import competition from '@/api/competition'
import clubmanagement from '@/api/clubmanagement'

export default{
    data(){
        return{
            competition:{},
            clubList:[],
            ruleList:[
                {
                  id:0,
                  rulename:"men singles"
               },
               {
                  id:1,
                  rulename:"women singles"
               },
               {
                  id:2,
                  rulename:"men doubles"
               },
               {
                  id:3,
                  rulename:"women doubles"
               }
            ]
        }
    },

    created(){
        const id=this.$route.params.id
        this.getcompetition(id)
    },

     mounted(){
       this.getClubList()
    },


    methods:{
        getcompetition(id) {
         competition.get(id) 
            .then(response => {
               this.competition= response.data
            })
        },

       update(){
          competition.update(this.competition)
          .then(response=>{
             this.$message({
                  type: 'success',
                  message: 'Update Successfully'
             })
             this.$router.push({path:'/Competition/Competitionlist'})
          })

       },
       getClubList(){
          clubmanagement.getList()
          .then(response=>{
             this.clubList=response.data
              console.log(this.clubList)
          })
          .catch(err => {
                    console.log(err)
                })
       },
       getSelected(val){
          for(let i=0;i<this.clubList.length;i++){
             if(this.clubList[i].clubname==val){
                this.competition.clubid=this.clubList[i].clubid
             }
          }
       },
        getSelected2(val){
          for(let i=0;i<this.ruleList.length;i++){
             if(this.ruleList[i].rulename==val){
                this.competition.type=this.ruleList[i].id
             }
          }
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