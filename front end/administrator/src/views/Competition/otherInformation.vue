<template>
    <div class="app-container">

        <el-table :data="list" stripe style="width: 100%">
            <el-table-column type="index" label="No." />
            <el-table-column prop="competitionid" label="ID"/>
            <el-table-column prop="competitionname" label="Name"/>
             <el-table-column prop="date" label="date"/>
             <el-table-column prop="status" label="status">
                <template slot-scope="scope">
                    <span v-if="scope.row.status==0">uncompleted</span>
                    <span v-if="scope.row.status==1">completed</span>
                </template>
            </el-table-column>

            <el-table-column label="change status" width="200" align="center">
                <template slot-scope="scope">
                    <el-button type="danger" size="mini"  icon="el-icon-edit" @click="changeStatus(scope.row.competitionid)">change status </el-button>
                </template>
            </el-table-column>
            
            <el-table-column label="show participants" width="200" align="center">
                <template slot-scope="scope">
                    <router-link :to="'/Competition/Participant/'+scope.row.competitionid">
                        <el-button type="primary" size="mini" icon="el-icon-edit">show participants</el-button>
                    </router-link>
                </template>
            </el-table-column>

            <el-table-column label="show participants" width="200" align="center">
                <template slot-scope="scope">
                    <router-link :to="'/Competition/Result/'+scope.row.competitionid">
                        <el-button type="primary" size="mini" icon="el-icon-edit">Results</el-button>
                    </router-link>
                </template>
            </el-table-column>
            
        </el-table>
        
        <el-pagination :current-page="current" :page-size="limit" :total="total" style="padding: 30px 0; text-align: center;" layout="total, prev, pager, next, jumper" @current-change="getList"/>

    </div>
</template>
<script>
import competition from '@/api/competition'
export default{

   data(){
       return{
           current:1,
           limit:8,
           object:{

           },

           list:[],
           total:0,
           select:[],
       }
   },

   created(){
       this.getList() 
   },
   mounted(){

   },

   methods:{
       getList(page=1){
           this.current=page
           competition.getList2(this.current,this.limit)
           .then(response=>{
               this.list=response.data.records
               this.total=response.data.total
               console.log(this.list)
           })//success
           .catch(error=>{
               console.log(error)
           })//fail
       },

       changeStatus(competitionid){
           competition.changeStatus(competitionid)
           .then()
           this.$router.push({path:'/Competition/Competitionlist'})
       },


       



   }

}
</script>