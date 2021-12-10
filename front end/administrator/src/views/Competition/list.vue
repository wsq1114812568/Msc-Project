<template>
    <div class="app-container">

        <div>
            <el-button type="danger" size="mini" @click="batchDelete()">Batch Delete</el-button>
            
        </div>

        <el-table :data="list" stripe style="width: 100%" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"/>
            <el-table-column type="index" label="No." />
            <el-table-column prop="competitionid" label="ID"/>
            <el-table-column prop="competitionname" label="CompetitionName"/>
            <el-table-column prop="date" label="date"/>

            <el-table-column prop="rule" label="Type">
                <template slot-scope="scope">
                    <span v-if="scope.row.type==0">Men Singles</span>
                    <span v-if="scope.row.type==1">Women Singles</span>
                    <span v-if="scope.row.type==2">Men Doubles</span>
                    <span v-if="scope.row.type==3">Women Doubles</span>
                </template>
            </el-table-column>

            <el-table-column prop="status" label="status">
                <template slot-scope="scope">
                    <span v-if="scope.row.status==0">uncompleted</span>
                    <span v-if="scope.row.status==1">completed</span>
                </template>
            </el-table-column>

            <el-table-column prop="clubname" label="club name"/>
            <el-table-column label="Operation" width="280" align="center">
                <template slot-scope="scope">
                    <el-button type="danger" size="mini"  icon="el-icon-delete" @click="deleteById(scope.row.competitionid)">Delete </el-button>
                    <router-link :to="'/Competition/CompetitionUpdate/'+scope.row.competitionid">
                        <el-button type="primary" size="mini" icon="el-icon-edit">Update</el-button>
                    </router-link>
                </template>
            </el-table-column>
        </el-table>
        
        <el-pagination :current-page="current" :page-size="limit" :total="total" style="padding: 30px 0; text-align: center;" layout="total, prev, pager, next, jumper" @current-change="getList"/>

        <div class="el-toolbar">
        <div class="el-toolbar-body" style="justify-content: flex-start;">
        <el-button type="primary" @click="exportData"><i class="fa fa-plus"/> Export Data</el-button>
        <el-button type="primary" @click="importData"><i class="fa fa-plus"/> Import Data</el-button>
        </div>
        </div>

        <el-dialog title="import data" :visible.sync="dialogImportVisible" width="480px">
            <el-form label-position="right" label-width="170px">

            <el-form-item label="File">
            <el-upload
            :multiple="false"
            :on-success="onUploadSuccess"
            :action="'http://localhost:8201/competition/importData'"
            class="upload-demo">
            <el-button size="small" type="primary">click to upload</el-button>
            <div slot="tip" class="el-upload__tip">only upload xls file，less than 500kb</div>
            </el-upload>
            </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
            <el-button @click="dialogImportVisible = false">
                cancel
            </el-button>
            </div>
        </el-dialog>


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
           dialogImportVisible:false,
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


       deleteById(id){
           this.$confirm('This operation will delete this competition information permantly, continue?', 'hint', {
               confirmButtonText: 'Yes',
               cancelButtonText: 'No',
               type: 'warning'
               }).then(() => { 
                    competition.delete(id)
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
           this.$confirm('This operation will delete this competition information permantly, continue?', 'hint', {
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
                    competition.batchDelete(idList)
               .then(response => {
                    this.$message({
                        type: 'success',
                        message: 'Delete Successfully'
                    })
                   this.getList(1)
                })
            })

       },
       exportData(){
           window.location.href='http://localhost:8201/competition/exportData'
       },
       importData(){
           this.dialogImportVisible=true
       },
       onUploadSuccess(){
           this.dialogImportVisible=false
            this.getList(1) 

       }

   }

}
</script>