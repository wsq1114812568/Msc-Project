<template>
  <div id="hello">
    <div id="message1"><h1>{{ msg }}</h1></div>
    <div id="message2"><h2>You Are </h2></div>
    <div id="clickbutton">
    <ul>
      <li>
        <el-button type="primary" round  @click="goto1">an administrator</el-button>
      </li>
      <li>
        <el-button type="primary" round  @click="goto2">a normal user</el-button>
      </li>
    </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomePage',
  data () {
    return {
      msg: 'Welcome To The Website'
    }
  },
  methods:{
    goto1(){
      window.location.href = 'http://localhost:9528'
    },
    goto2(){
      window.location.href = 'http://localhost:3000'
    }
  }
  

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
/* a {
  color: #42b983;
} */

#hello{
  text-align: center;
	border-radius: 20px;
	width: 400px;
	height: 450px;
	margin: auto;
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
   background:url("../assets/bj3.jpeg");
  background-size:100% 100%;

}
#clickbutton{
  text-align: center;
   margin-top: 100px;
}
#message1{
   text-align: center;
   margin-top: 50px;
}
#message2{
   text-align: center;
   margin-top: 100px;
}

</style>
