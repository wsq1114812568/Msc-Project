<template>
    <div class="app-container">

        <div>
            <el-button type="danger" size="mini" @click="batchDelete()">Batch Delete</el-button>
            
        </div>

        <el-table :data="list" stripe style="width: 100%" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"/>
            <el-table-column type="index" label="No." />
            <el-table-column prop="id" label="AdminID"/>
            <el-table-column prop="adminname" label="AdminName"/>
            <el-table-column prop="password" label="password"/>
            <el-table-column prop="createTime" label="createtime"/>
            <el-table-column prop="updateTime" label="updatetime"/>
            <el-table-column label="Operation" width="280" align="center">
                <template slot-scope="scope">
                    <el-button type="danger" size="mini"  icon="el-icon-delete" @click="deleteById(scope.row.id)">Delete </el-button>
                    <router-link :to="'/AdminManagement/AdminUpdate/'+scope.row.id">
                        <el-button type="primary" size="mini" icon="el-icon-edit">Update</el-button>
                    </router-link>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination :current-page="current" :page-size="limit" :total="total" style="padding: 30px 0; text-align: center;" layout="total, prev, pager, next, jumper" @current-change="getList"/>

    </div>
</template>
<script>
import adminmanagement from '@/api/adminmanagement'

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
           adminmanagement.getUserList(this.current,this.limit,this.object)
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
                    adminmanagement.deleteUser(id)
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
                    adminmanagement.batchDelete(idList)
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