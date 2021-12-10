<template>
  <div class="app-container">
     
     <div>
        <el-button type="danger" size="mini" @click="batchDelete()">Batch Delete</el-button>  
    </div>

       <el-table :data="competition" stripe style="width: 100%" @selection-change="handleSelectionChange">
           <el-table-column type="selection" width="55"/>
            <el-table-column type="index" label="No." />
            <el-table-column prop="competitionid" label="ID"/>
            <el-table-column prop="competitionname" label="Name"/>
            <el-table-column prop="date" label="date"/>

            <el-table-column prop="type" label="type">
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

            <el-table-column label="Operation" width="280" align="center">
                <template slot-scope="scope">
                    <el-button type="danger" size="mini"  icon="el-icon-delete" @click="deleteById(scope.row.competitionid)">Delete </el-button>
                    <router-link :to="'/Competition/CompetitionUpdate/'+scope.row.competitionid">
                        <el-button type="primary" size="mini" icon="el-icon-edit">Update</el-button>
                    </router-link>
                </template>
            </el-table-column>
       </el-table>
       

     
  
   </div>
</template>

<script>
import clubmanagement from '@/api/clubmanagement'
import competition from '@/api/competition'
export default{
    data(){
        return{
            competition:[],
            current:1,
           limit:8,
           object:{},
           total:0,
           select:[],
        }
    },

    created(){
        const clubid=this.$route.params.id
        this.getCompetition(clubid)
    },

    methods:{
        getCompetition(clubid) {
         clubmanagement.findCompetition(clubid)
            .then(response => {
               this.competition = response.data
               console.log(response)
            })
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

    },
}
</script>