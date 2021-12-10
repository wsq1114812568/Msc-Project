<template>
  <div class="app-container">
     <div>
     <div id="title">
       Tables of  {{club.clubname}}
     </div>
     <div  style="margin-bottom:25px">
         <el-form >
             <span>Tables available in this club</span><br/><br/>
             <el-form-item label="Number of Available Tables" style="display: inline-block !important;">
                <el-input v-model="table.number"></el-input>
             </el-form-item>
             <el-form-item label="Date" style="display: inline-block !important;">
                <el-date-picker v-model="table.day" type="date" placeholder="choose date" value-format="yyyy-MM-dd"></el-date-picker>
             </el-form-item>
             <el-form-item label="Begin Time" style="display: inline-block !important;">
                <template>
                <el-time-picker v-model="table.begin" value-format="yyyy-MM-dd HH:mm:ss" placeholder="begin"></el-time-picker>
                </template>
             </el-form-item>
             <el-form-item label="End Time" style="display: inline-block !important;">
                <template>
                <el-time-picker v-model="table.end" value-format="yyyy-MM-dd HH:mm:ss" placeholder="end"></el-time-picker>
                </template>
             </el-form-item>
                       
            <el-form-item  >
                <el-button type="primary" @click="addTable" >Update</el-button>
            </el-form-item>
        </el-form>
    </div>
    

    <div id="form">
        <el-table :data="tableList" stripe style="width: 70%" >
          <el-table-column label="Club" min-width="20%"><template slot-scope="scope"><span>{{club.clubname}}</span> </template>  </el-table-column>
          <el-table-column prop="number" label="Available Tables" min-width="30%"/>
          <el-table-column prop="day" label="Date" min-width="30%" />
          <el-table-column label="Time">
               <template slot-scope="scope">
                   <span>{{scope.row.begin}}------{{scope.row.end}}</span>
               </template>   
          </el-table-column>
        </el-table>
    </div>
      </div>
   </div>
</template>

<script>
import club from '@/api/clubmanagement'

export default{

   data(){
       return{
          result:{},
          newResultDetails:{},
          resultDetails:[],
          club:{},
          table:{},
          tableList:[],
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
       this.getClub(id)
       this.getTableList(id)

   },

   methods:{
       getClub(id){
           club.getClub(id)
           .then(response=>{
               this.club=response.data
           })
       },
       getTableList(id){
           club.showTables(id)
           .then(response=>{
               this.tableList=response.data
           })
       },
       addTable(){
           this.table.clubid=this.$route.params.id
           club.addTables(this.table)
           .then(response=>{
               this.$message({
                    type: 'success',
                    message: 'Update Successfully'
                })  

           })
          window.location.reload()
               
       },
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