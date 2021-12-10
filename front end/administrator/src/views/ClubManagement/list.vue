<template>
    <div class="app-container">

        <div>
            <el-button type="danger" size="mini" @click="batchDelete()">Batch Delete</el-button>
            
        </div>

        <el-table :data="list" stripe style="width: 100%" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"/>
            <el-table-column type="index" label="No." />
            <el-table-column prop="clubid" label="ClubID" width="70"/>
            <el-table-column prop="clubname" label="ClubName" width="90"/>
            <el-table-column prop="location" label="location"/>
            <el-table-column prop="phone" label="phone" width="120"/>
            <el-table-column prop="email" label="email"/>

            <el-table-column  label="show competitions">
                <template slot-scope="scope">
                    <router-link :to="'/ClubManagement/ClubCompetition/'+scope.row.clubid">
                        <el-button type="primary" size="mini" icon="el-icon-more">show competition</el-button>
                    </router-link>
                </template>  
            </el-table-column>

            <el-table-column  label="show tables">
                <template slot-scope="scope">
                    <router-link :to="'/ClubManagement/ClubTables/'+scope.row.clubid">
                        <el-button type="primary" size="mini" icon="el-icon-more">show tables</el-button>
                    </router-link>
                </template>  
            </el-table-column>

            <el-table-column label="Operation" width="280" align="center">
                <template slot-scope="scope">
                    <el-button type="danger" size="mini"  icon="el-icon-delete" @click="deleteById(scope.row.clubid)">Delete </el-button>
                    <router-link :to="'/ClubManagement/ClubUpdate/'+scope.row.clubid">
                        <el-button type="primary" size="mini" icon="el-icon-edit">Update</el-button>
                    </router-link>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination :current-page="current" :page-size="limit" :total="total" style="padding: 30px 0; text-align: center;" layout="total, prev, pager, next, jumper" @current-change="getList"/>


    </div>
</template>
<script>
import clubmanagement from '@/api/clubmanagement'

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

   methods:{
       getList(page=1){
           this.current=page
           clubmanagement.getClubList(this.current,this.limit)
           .then(response=>{
               this.list=response.data.records
               this.total=response.data.total
               console.log(response)

           })//success
           .catch(error=>{
               console.log(error)
           })//fail
       },

       deleteById(id){
           this.$confirm('This operation will delete this club information permantly, continue?', 'hint', {
               confirmButtonText: 'Yes',
               cancelButtonText: 'No',
               type: 'warning'
               }).then(() => { 
                    clubmanagement.deleteClub(id)
               .then(response => {
                    this.$message({
                        type: 'success',
                        message: 'Delete Successfully'
                    })
                   this.getList(1)
                })
            })
       },

       handleSelectionChange(selection){
           this.select=selection
       },

       batchDelete(){
           this.$confirm('This operation will delete this club information permantly, continue?', 'hint', {
               confirmButtonText: 'Yes',
               cancelButtonText: 'No',
               type: 'warning'
               }).then(() => { 
                   var idList=[]
                   for(var i=0;i<this.select.length;i++){
                       var object=this.select[i]
                       var id=object.clubid
                       idList.push(id)
                   }
                    clubmanagement.batchDelete(idList)
               .then(response => {
                    this.$message({
                        type: 'success',
                        message: 'Delete Successfully'
                    })
                   this.getList(1)
                })
            })
       },



   }

}
</script>