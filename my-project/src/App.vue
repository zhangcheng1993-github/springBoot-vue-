<template>
  <div id="app">
    <el-row type="flex" justify="center">
        <el-col :span="6"></el-col>
        <el-col :span="12">
            <el-menu :default-active="this.$route.path" mode="horizontal"
                 background-color="#6F6D6D" text-color="#F3F2F2" active-text-color="#EFEB7F" @select="handleSelect">
              <el-menu-item  v-for="(item,i) in navList" :key="i" :index="item.name">
                <template v-if="item.meta.show">
                    <i class="el-icon-location"></i>
                    {{item.meta.title}}
                    <template v-if="carTotal!=0 && item.meta.title=='购物车'">
                      <span>({{carTotal}})</span>
                    </template>
                </template>
              </el-menu-item>
            </el-menu>
          <router-view/>
        </el-col>
        <el-col :span="6"></el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'App',
  computed:{
    navList(){
      return this.$router.options.routes.filter((item)=>{
        if (item.meta.show!= undefined){
          return item;
        }
      });
    },
    carTotal(){
      var carList=this.$store.state.shopStore.carList;
      var num=0;
      carList.forEach((item)=>{
        num+=item.count
      });
      return num;
    }
  },
  mounted(){
    this.getThemes();
  },
  methods:{
    getThemes(){
      var _this=this;
      this.$axios.ajax.post('/getObjectListJson/13',{
      }).then(function (res) {
        console.dir(res);
      })
    },
    handleSelect(key,keyPath){
      this.$router.push({name:key});
    }
  }
}
</script>

<style>
</style>
