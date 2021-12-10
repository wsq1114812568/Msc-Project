<template>
  <div class="home page-component">
    <el-carousel indicator-position="outside">
      <el-carousel-item v-for="item in 1" :key="item.id">
        <img src="~assets/images/picture2.jpeg" alt="">
      </el-carousel-item>
      <el-carousel-item v-for="item in 1" :key="item.id">
        <img src="~assets/images/picture1.jpeg" alt="">
      </el-carousel-item>
      <el-carousel-item v-for="item in 1" :key="item.id">
        <img src="~assets/images/picture3.jpeg" alt="">
      </el-carousel-item>
    </el-carousel>

    
    <div class="search-container">
    <div class="search-wrapper">
    <div class="competition-search">
      <el-autocomplete
      class="search-input"
      prefix-icon="el-icon-search"
      v-model="state"
      :fetch-suggestions="query"
      placeholder="Input Competition Name"
      @select="handleSelect"
      >
        <span slot="suffix" class="search-btn v-link highlight clickable selected">Search</span>
      </el-autocomplete>
    </div>
    </div>
    </div>


    <!-- bottom -->
    <div class="bottom">
      <div class="left">
        <div class="home-filter-wrapper">
          <div class="title"> Competitions
          </div>
        <div>
          <div class="filter-wrapper">
            <span class="label">Club
            </span>
            <div class="condition-wrapper">
              <span class="item v-link clickable" :class="hostypeActiveIndex == index ? 'selected' : ''"   v-for="(item,index) in clubList" :key="index"  @click="clubSelect(item.clubid,index)">{{item.clubname}} </span>
            </div>
          </div>

            <div class="filter-wrapper">
              <span class="label">Competition Type</span>
              <div class="condition-wrapper">
                <span class="item v-link clickable" :class="provinceActiveIndex == index ? 'selected' : ''"  v-for="(item,index) in typeList" :key="index"  @click="ruleSelect(item.value,index)">{{item.name}} </span>
              </div>
            </div>
          </div>
        </div>
        <div class="v-scroll-list hospital-list">

          <div v-for="(item,index) in list" :key="index" class="v-card clickable list-item" @click="show(item.competitionid)">
            <div class="">
              <div class="hospital-list-item hos-item" index="0">
                <div class="wrapper">
                  <div class="hospital-title" > {{item.competitionname}}
                  </div>
                  <div class="bottom-container">
                    <div class="icon-wrapper">
                      <span class="iconfont" v-if="item.type==0"> Men's singles</span>
                      <span class="iconfont" v-if="item.type==1">Women's singles</span>
                      <span class="iconfont" v-if="item.type==2">Men's doubles</span>
                      <span class="iconfont" v-if="item.type==3">Women's doubles</span>
                    </div>
                    <div class="icon-wrapper">
                      <span class="iconfont"></span>{{item.date}}
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <el-pagination :current-page="current" :page-size="limit" :total="total" style="padding: 30px 0; text-align: center;" layout="total, prev, pager, next, jumper" @current-change="getPageList"/>
        </div>
      </div>

      <div class="right">

        

        <div class="common-dept">
          <div class="header-wrapper">
            <div class="title">Table Tennis Club
            </div>
            <!-- <div class="all-wrapper">
              <span>All</span>
              <span class="iconfont icon"></span>
            </div> -->
          </div>
          <div class="content-wrapper">
            <span v-for="(item,index) in clubList" :key="index" class="item v-link clickable dark"  :onclick="'javascript:window.location=\'/club/'+item.clubid+'\''">{{item.clubname}} </span>
          </div>
        </div>

        <div class="space">
    
        </div>
      </div>
    </div>
  </div>   
</template>

<script>
import cookie from 'js-cookie'
import club from '@/api/club'
import competition from '@/api/competition'

export default {

  data(){
    return{
      current:1,
      limit:4,
      object:{},
      total:0,
      list:[],
      clubList:[],
      state:"",
      typeList:[{name:'All',value:'4'},{name:'men singles',value:'0'},{name:'women singles',value:'1'},{name:'men doubles',value:'2'},{name:'women doubles',value:'3'}
      ],

      provinceActiveIndex:0,
      hostypeActiveIndex:0,
    }

  },

  created(){
    this.init()
    this.getPageList()

  },

  methods:{
    test(){
      let token=cookie.get('token')
      if(!token){
        loginEvent.$emit('loginDialogEvent')
        return
      };
      window.location.href='/test'
    },

    init(){
      club.getClubList()
      .then(response=>{
        this.clubList=[]
        for(var i=0;i<response.data.length;i++){
          this.clubList.push(response.data[i])
        }
        console.log(this.clubList)
      })
    },

     getPageList(page=1){
           this.current=page
           competition.getPageList(this.current,this.limit,this.object)
           .then(response=>{
               this.list=response.data.records
               this.total=response.data.total
               console.log(this.list)
           })//success
           .catch(error=>{
               console.log(error)
           })//fail
       },
       getList(){
         competition.getPageList(this.current,this.limit,this.object)
         .then(response=>{
           this.list=response.data.records
           this.total=response.data.total
         })

       },
      clubSelect(clubid,index){
        this.list=[]
        this.current=1
        this.hostypeActiveIndex=index
        if(clubid!=0){
          this.object.clubid=clubid
        }
        else{
          this.object.clubid=null
        }
        this.getList()
      },
      ruleSelect(type,index){
         this.list=[]
         this.current=1
         this.provinceActiveIndex=index
         if(type!=4){
          this.object.type=type
        }
        else{
          this.object.type=null
        }
         this.getList()
      },

      query(queryString, cb) {
        this. object = []
        if(queryString == '') return
        competition.findByName(queryString).then(response => {
          for (let i = 0, len = response.data.length; i <len; i++) {
            response.data[i].value = response.data[i].competitionname
          }
          cb(response.data)
        })
      },

      handleSelect(item) {
      window.location.href = '/competition/'+item.competitionid
      },

      show(competitionid) {

      window.location.href ='/competition/'+competitionid
    },


  }

}
</script>



