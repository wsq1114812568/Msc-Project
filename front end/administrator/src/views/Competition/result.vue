<template>
  <div class="app-container">
     <div>
     <div id="title">
       Result of  {{competition.competitionname}}
     </div>
     <div  style="margin-bottom:25px">
         <el-form >
             <span>Final Result of this competition </span><br/><br/>
             <el-form-item label="First Place" style="display: inline-block !important;">
                <el-select v-model="result.firstname" @change="getSelected1" >
                    <el-option v-for="item in user" :key="item.userid" :value="item.userName" ></el-option>
                </el-select>
             </el-form-item>
             <el-form-item label="Second Place" style="display: inline-block !important;">
                <el-select v-model="result.secondname"  @change="getSelected2">
                    <el-option v-for="item in user" :key="item.userid" :value="item.userName" ></el-option>
                </el-select>
             </el-form-item>
             <el-form-item label="Third Place" style="display: inline-block !important;" >
                <el-select v-model="result.thirdname" @change="getSelected3">
                    <el-option v-for="item in user" :key="item.userid" :value="item.userName" ></el-option>
                </el-select>
             </el-form-item>
            <el-form-item  >
                <el-button type="primary" @click="save" >Update</el-button>
            </el-form-item>
        </el-form>
    </div>

    <div  style="margin-bottom:25px">
         <el-form >
             <span>Single game result of this competition </span><br/><br/>
             <el-form-item label="Player1" style="display: inline-block !important;">
                <el-select v-model="newResultDetails.user1id" @change="getSelected4" >
                    <el-option v-for="item in user" :key="item.userid" :value="item.userName" ></el-option>
                </el-select>
             </el-form-item>
             <el-form-item label="Player2" style="display: inline-block !important;">
                <el-select v-model="newResultDetails.user2id"  @change="getSelected5">
                    <el-option v-for="item in user" :key="item.userid" :value="item.userName" ></el-option>
                </el-select>
             </el-form-item>
             <el-form-item label="Score" style="display: inline-block !important;">
                 <el-input v-model="newResultDetails.score1" placeholder="score1" style="display: inline-block !important;width:25%"/>
                 <span>:</span>
                 <el-input v-model="newResultDetails.score2" placeholder="score2" style="display: inline-block !important;width:25%"/>
             </el-form-item>    
             
            <el-form-item  >
                <el-button type="primary" @click="save2" >Update</el-button>
            </el-form-item>
        </el-form>
    </div>

    <div id="form" style="margin-bottom:25px">
        <el-table :data="final" stripe style="width: 50%" >
            <el-table-column prop="first" label="First Place" min-width="30%"/>
            <el-table-column prop="second" label="Second Place" min-width="30%"/>
            <el-table-column prop="third" label="Third Place" min-width="30%"/>
        </el-table>
    </div>
    

    <div id="form">
        <el-table :data="resultDetails" stripe style="width: 50%" >
          <el-table-column prop="user1name" label="Player1" min-width="30%"/>
          <el-table-column  min-width="25%">
               <template slot-scope="scope">
                   <span>VS</span>
               </template>   
          </el-table-column>
          <el-table-column prop="user2name" label="Player2" min-width="30%"/>
          <el-table-column label="Score">
               <template slot-scope="scope">
                   <span>{{scope.row.score1}}:{{scope.row.score2}}</span>
               </template>   
          </el-table-column>
        </el-table>
    </div>
      </div>
   </div>
</template>

<script>
import competition from '@/api/competition'
import result from '@/api/result'

export default{

   data(){
       return{
          result:{},
          newResultDetails:{},
          resultDetails:[],
          competition:{},
          user:[],
          final:[{
                    first:null,
                    second:null,
                    third:null,
                }]
       }
   },

   created(){
       const id=this.$route.params.id
       this.getCompetition(id)
       this.getUser(id)
       this.getResult(id)
       this.getResultDetails(id)
   },

   methods:{
       getCompetition(id){
           competition.get(id)
           .then(response=>{
               this.competition=response.data
           })
       },
       getUser(id){
           competition.findUser(id)
           .then(response=>{
               this.user=response.data
               console.log(this.user)
           })
       },
       getResult(id){
           result.getResult(id)
           .then(response=>{
               this.result=response.data
               this.final[0].first=this.result.firstname
                this.final[0].second=this.result.secondname
                 this.final[0].third=this.result.thirdname

           })
       },
       getResultDetails(id){
           result.getResultDetails(id)
           .then(response=>{
               this.resultDetails=response.data
               console.log(this.resultDetails)
           })

       },
       getSelected1(val){
          for(let i=0;i<this.user.length;i++){
             if(this.user[i].userName==val){
                this.result.firstid=this.user[i].userid
             }
          }
       },
       getSelected2(val){
          for(let i=0;i<this.user.length;i++){
             if(this.user[i].userName==val){
                this.result.secondid=this.user[i].userid
             }
          }
       },
       getSelected3(val){
          for(let i=0;i<this.user.length;i++){
             if(this.user[i].userName==val){
                this.result.thirdid=this.user[i].userid
             }
          }
       },
       getSelected4(val){
          for(let i=0;i<this.user.length;i++){
             if(this.user[i].userName==val){
                this.newResultDetails.user1id=this.user[i].userid
             }
          }
       },
       getSelected5(val){
          for(let i=0;i<this.user.length;i++){
             if(this.user[i].userName==val){
                this.newResultDetails.user2id=this.user[i].userid
             }
          }
       },
       save(){
           console.log(this.result)
           result.addOrUpdate(this.result)
                .then(response=>{
                    this.$message({
                        type: 'success',
                        message: 'Update Successfully'
                    })    
            })
            window.location.reload()
       },
       save2(){
           this.newResultDetails.competitionid=this.$route.params.id
           console.log(this.newResultDetails)
           result.add(this.newResultDetails)
           .then(response=>{
               this.$message({
                        type: 'success',
                        message: 'Update Successfully'
                    })  
           })
           window.location.reload()
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