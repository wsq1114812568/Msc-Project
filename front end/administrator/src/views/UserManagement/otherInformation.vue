<template>
    <div class="app-container">
        <el-form :inline="true" class="demo-form-inline">
            <el-form-item>
                <el-input  v-model="object.username" placeholder="UserName"/>
            </el-form-item>
            <el-form-item>
                <el-input v-model="object.id" placeholder="UserID"/>
            </el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="getList()">Query</el-button>
        </el-form>

        <el-table :data="list" stripe style="width: 100%" @selection-change="handleSelectionChange">
            <el-table-column type="index" label="No." />
            <el-table-column prop="id" label="UserID"/>
            <el-table-column prop="username" label="UserName"/>
            <el-table-column label="Other Information" width="280" align="center">
                <template slot-scope="scope">
                    <router-link :to="'/UserManagement/UserFriend/'+scope.row.id">
                        <el-button type="primary" size="mini" icon="el-icon-more">Show Friends</el-button>
                    </router-link>
                </template>
            </el-table-column>
            <el-table-column label="Other Information" width="280" align="center">
                <template slot-scope="scope">
                    <router-link :to="'/UserManagement/UserCompetition/'+scope.row.id">
                        <el-button type="primary" size="mini" icon="el-icon-more">Show Competition Attended</el-button>
                    </router-link>
                </template>
            </el-table-column>
           
        </el-table>
        <el-pagination :current-page="current" :page-size="limit" :total="total" style="padding: 30px 0; text-align: center;" layout="total, prev, pager, next, jumper" @current-change="getList"/>

    </div>
</template>
<script>
import usermanagement from '@/api/usermanagement'

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
           usermanagement.getUserList(this.current,this.limit,this.object)
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
           this.$confirm('This operation will delete this user information permantly, continue?', 'hint', {
               confirmButtonText: 'Yes',
               cancelButtonText: 'No',
               type: 'warning'
               }).then(() => { 
                    usermanagement.deleteUser(id)
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
           this.$confirm('This operation will delete this user information permantly, continue?', 'hint', {
               confirmButtonText: 'Yes',
               cancelButtonText: 'No',
               type: 'warning'
               }).then(() => { 
                   var idList=[]
                   for(var i=0;i<this.select.length;i++){
                       var object=this.select[i]
                       var id=object.id
                       idList.push(id)
                   }
                    usermanagement.batchDelete(idList)
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